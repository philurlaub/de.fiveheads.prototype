package controllers;

import models.WekaTest;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.wekacontrols;


/**
 * Created by paul on 23.01.14.
 */

public class WekaController extends Controller {


    @Security.Authenticated(Secured.class)
    public static Result options() {

        if ("true".equals(request().getQueryString("import"))) {
            WekaTest.arffImporter(ctx());
        }

        if ("true".equals(request().getQueryString("learn"))) {
            String message = WekaTest.learn();
            ctx().args.put("alert-success", message);
        }

        return ok(wekacontrols.render());
    }

    public static Result predictArticle(Long id){
        int rating = WekaTest.predictSingleArticleRating(id);
        return redirect(
                controllers.routes.ArticleController.show(id)
        );
    }

}
