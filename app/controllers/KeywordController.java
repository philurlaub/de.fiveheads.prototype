package controllers;

import models.Keyword;
import models.WekaTest;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import views.html.keywordstats;
import views.html.keywordlist;


/**
 * Created by Philipp Paul on 23.01.14.
 */

@Security.Authenticated(Secured.class)
public class KeywordController extends Controller {

    // GET -- Stichwortstatistik für Unternehmen
    public static Result keywordStats (){
        return ok(
                keywordstats.render(Keyword.findAll())
        );
    }

    // GET -- Stichwörter verwalten für Administratoren
    public static Result manageKeywords (){
        return ok(
                keywordlist.render(Keyword.findAll())
        );
    }

    // Keywords speichern
    public static Result save(){
        String input = request().body().asFormUrlEncoded().get("keywordlist")[0];
        String[] keywords = input.split("\\r?\\n");
        for(int i=0; i<keywords.length; i++){
            Keyword newkeyword = new Keyword();
            newkeyword.name = keywords[i];
            newkeyword.save();
        }

        return redirect(
                controllers.routes.KeywordController.manageKeywords()
        );
    }

    // Keyword löschen
    public static Result delete(Long id){
        Keyword.findById(id).delete();
        return redirect(
                controllers.routes.KeywordController.manageKeywords()
        );
    }


}