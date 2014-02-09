package controllers;

import models.WekaTest;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.keywordstats;


/**
 * Created by Philipp Paul on 23.01.14.
 */

@Security.Authenticated(Secured.class)
public class KeywordController extends Controller {
    public static Result keywordStats (){
        return ok(
                keywordstats.render()
        );
    }

}
