package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.*;
import com.avaje.ebean.Query;
import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

/**
 * UserController entity managed by Ebean
 */
@Entity
@Table(name="article")
public class Article extends Model {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Constraints.Required
    @Formats.NonEmpty
    public String title;

    @Constraints.Required
    @Column(columnDefinition = "TEXT")
    public String content;

    @Formats.DateTime(pattern="dd.MM.yyyy")
    @Constraints.Required
    public Date publicationDate;

    public String language;

    @OneToMany
    private List<Rating> ratingList;

    // Notwendig für Formularverarbeitung -> null = keine Fehler
    // play framework 2.2 Konvention
    public String validate() {
        return null;
    }

    // Speichern eines neuen Artikels
    public static Article create(Article article) {
        article.save();
        return article;
    }

    // Objekt für query Abfragen
    public static Finder<String,Article> find = new Finder<String,Article>(String.class, Article.class);

    // Liefert alle Artikel in der Datenbank als Liste
    public static List<Article> findAll() {
        return find.all();
    }

    // Finde alle Artikel zu einem User, die noch nicht bewertet wurden
    public static Article getUnratedToUserEmail(String email) {

        // Da ebean die query nicht out of the box liefert muss eine Abfrage von Hand erfolgen
        String sql = "SELECT article.id, article.title, article.content, article.publication_date, article.language  FROM article " +
                     "LEFT JOIN rating ON rating.article_id = article.id " +
                     "WHERE user_email not like '"+ email +"' or rating.article_id is null;";
        // Rückgabewerte auf Objekt mappen
        RawSql rawSql =
            RawSqlBuilder
                    .parse(sql)
                    .columnMapping("article.id",                  "id")
                    .columnMapping("article.title",               "title")
                    .columnMapping("article.content",             "content")
                    .columnMapping("article.publication_date",    "publicationDate")
                    .columnMapping("article.language",            "language")
                    .create();
        // Query vorbereiten
        Query<Article> query = Ebean.find(Article.class);
        query.setRawSql(rawSql);

        // Rückgabewerte in Liste speichern
        List<Article> list = query.findList();
        // mischen ...
        Collections.shuffle(list);
        if (!list.isEmpty())
            return list.get(0);
        else
            // Liste leer -> alles Bewertet ?!
            return null;
    }

   // Alle Artikel löschen
   public static void deleteAll(){
        Ebean.delete(findAll());
    }

    // Stringrepräsentation eines Objektes
    public String toString() {
        return "Article(" + title + " Veröffentlicht am: " + publicationDate + " mit dem Inhalt " + content + ")";
    }


}

