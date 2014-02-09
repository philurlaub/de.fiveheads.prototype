package models;

import play.mvc.Http;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.core.tokenizers.NGramTokenizer;
import weka.experiment.InstanceQuery;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NominalToString;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.attribute.StringToWordVector;
import weka.filters.unsupervised.attribute.Standardize;

import java.io.*;
import java.sql.Timestamp;

/**
 * Created by paul on 20.01.14.
 */
public class WekaTest {
    //Doku
    //http://jmgomezhidalgo.blogspot.de/2013/06/sample-code-for-text-indexing-with-weka.html
    //http://www.unal.edu.co/diracad/einternacional/Weka.pdf

    // WEKA Neuronales Netz
    //http://web.uvic.ca/~maryam/DM1392/Labs/6_WekaANN.pdf


    private static MultilayerPerceptron neuronalNetwork;
    private static Instances trainInstances;


    public static Instances filter(Instances instances){

        try {
            // ==> Ablauf: Aus der Datenbank kommt Attributtyp "Nominal"
            //             Konvertierung zu "String" damit dann "StringToWordVector" angewendet werden kann
            NominalToString makeString = new NominalToString();
            makeString.setAttributeIndexes("1");

            makeString.setInputFormat(instances);

            /**
             * Filter Anwenden (NominalToString)
             */
            instances = Filter.useFilter(instances, makeString);

            //System.out.println(trainInstances.toSummaryString());
            //System.out.println(instances.toSummaryString());

            NGramTokenizer tokenizer = new NGramTokenizer();
            tokenizer.setNGramMinSize(1);
            tokenizer.setNGramMaxSize(1);
            tokenizer.setDelimiters("\\W");

            StringToWordVector makeVector = new StringToWordVector();
            makeVector.setInputFormat(instances);
            makeVector.setTokenizer(tokenizer);
            makeVector.setWordsToKeep(10);
            makeVector.setLowerCaseTokens(true);
            makeVector.setOutputWordCounts(true);
            makeVector.setIDFTransform(true);
            makeVector.setUseStoplist(true);
            makeVector.setNormalizeDocLength(new SelectedTag(StringToWordVector.FILTER_NORMALIZE_ALL, StringToWordVector.TAGS_FILTER));

            /**
             * Filter StringToWordVector
             */
            System.out.println("Filter anwenden ...");
            instances = Filter.useFilter(instances, makeVector);

            return instances;

        } catch (Exception e){
            System.out.println("Fehler beim filtern der Daten!");
            e.printStackTrace(new PrintStream(System.out));
            return null;
        }
    }


    public static String learn() {
        String output ="";
        try {

            /**
             * Trainingsinstanzen aus Datenbank laden
             * DB String = jdbc:mysql://localhost:3306/fiveheads
             */
            InstanceQuery query = new InstanceQuery();
            query.setUsername("root");
            query.setPassword("admin");
            query.setQuery("SELECT content, classification FROM article WHERE type_switch = 'user';");
            trainInstances = query.retrieveInstances();


            trainInstances = filter(trainInstances);

            //Setzen des Attributes für die Vorhersage
            trainInstances.setClassIndex(1);

            //Remove removeAttr = new Remove();
            output = "Neuronales Netz erfolgreich trainiert!";

            /**
             * Neuronales Netz
             */
            neuronalNetwork = new MultilayerPerceptron();
            neuronalNetwork.buildClassifier(trainInstances);
        }
        catch(Exception e){
            output = e.getMessage();
        }

        return output;

    }

    public static int predictSingleArticleRating(Long articleId){
        learn();
        try {
            InstanceQuery query = new InstanceQuery();
            query.setUsername("root");
            query.setPassword("admin");
            query.setQuery("SELECT content FROM article WHERE id =" + articleId + ";");
            Instances predictInstances = query.retrieveInstances();

            // Create vector to hold nominal values "first", "second", "third"
            FastVector my_nominal_values = new FastVector(2);
            my_nominal_values.addElement("pos");
            my_nominal_values.addElement("neg");

            predictInstances.insertAttributeAt(new Attribute("classification", my_nominal_values), 0);
            predictInstances.setClassIndex(1);

            System.out.println(predictInstances.toSummaryString());

            //############################################
            //predictInstances = filter(predictInstances);
            try {
                // ==> Ablauf: Aus der Datenbank kommt Attributtyp "Nominal"
                //             Konvertierung zu "String" damit dann "StringToWordVector" angewendet werden kann
                NominalToString makeString = new NominalToString();
                makeString.setAttributeIndexes("2");

                makeString.setInputFormat(predictInstances);

                /**
                 * Filter Anwenden (NominalToString)
                 */
                predictInstances = Filter.useFilter(predictInstances, makeString);

                //System.out.println(trainInstances.toSummaryString());
                //System.out.println(instances.toSummaryString());

                NGramTokenizer tokenizer = new NGramTokenizer();
                tokenizer.setNGramMinSize(1);
                tokenizer.setNGramMaxSize(1);
                tokenizer.setDelimiters("\\W");

                StringToWordVector makeVector = new StringToWordVector();
                makeVector.setInputFormat(predictInstances);
                makeVector.setTokenizer(tokenizer);
                makeVector.setWordsToKeep(10);
                makeVector.setLowerCaseTokens(true);
                makeVector.setOutputWordCounts(true);
                makeVector.setIDFTransform(true);
                makeVector.setUseStoplist(true);
                makeVector.setNormalizeDocLength(new SelectedTag(StringToWordVector.FILTER_NORMALIZE_ALL, StringToWordVector.TAGS_FILTER));

                /**
                 * Filter StringToWordVector
                 */

                predictInstances = Filter.useFilter(predictInstances, makeVector);



            } catch (Exception e){
                System.out.println("Fehler beim filtern der Daten!");
                e.printStackTrace(new PrintStream(System.out));

            }
            //############################################

            //System.out.println(trainInstances.toSummaryString());
            System.out.println(predictInstances.toSummaryString());

            Standardize standFilter = new Standardize();
            standFilter.setInputFormat(trainInstances);  // initializing the filter once with training set
            Instances newTrain = Filter.useFilter(trainInstances, standFilter);  // configures the Filter based on train instances and returns filtered instances
            Instances newTest = Filter.useFilter(predictInstances, standFilter);    // create new test set

            System.out.println("Filter fertig");

            Evaluation eTest = new Evaluation(newTest);
            eTest.evaluateModel(neuronalNetwork, newTest);
            //System.out.println(predict.toSummaryString());
            //System.out.println(eTest.toSummaryString());


            return 1;

        }
        catch (Exception e){
            System.out.println("Fehler bei Artikelbewertung durch NN:");
            e.printStackTrace(new PrintStream(System.out));

            return 0;
        }
    }

    public static void arffImporter(Http.Context ctx){

        long offset = Timestamp.valueOf("2012-01-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2013-01-01 00:00:00").getTime();
        long diff = end - offset + 1;

        try {
            BufferedReader br = new BufferedReader(new FileReader("import/IMDB-PP-TEST.arff"));
            String line;

            br.readLine();
            br.readLine();
            br.readLine();
            br.readLine();
            br.readLine();
            br.readLine();
            int i = 0;
            while((line = br.readLine()) != null) {
                String classification = line.substring(line.length()-3, line.length());
                String content = line.substring(1, line.length()-5);
                Article article = new Article();
                article.title = "Demo Bewertung " + i;
                article.content = content;
                article.classification = classification;
                article.publicationDate = new Timestamp(offset + (long)(Math.random() * diff));
                article.typeSwitch = "user";
                article.save();
                if (i%10 == 0)
                    System.out.println(i + " Artikel importiert!");
                i++;
            }
            ctx.args.put("alert-success", "Es wurden "+ i +" Artikel importiert!");


        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}
