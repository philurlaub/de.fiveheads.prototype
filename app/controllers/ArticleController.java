package controllers;

import static play.data.Form.*;

import play.data.Form;
import play.mvc.*;
import models.*;
import views.html.article;
import views.html.addarticle;
import views.html.articlelist;


@Security.Authenticated(Secured.class)
public class ArticleController extends Controller{


    public static Result articlelist() {
        return ok(
            articlelist.render(
                Article.findAll())
        );
    }

    public static Result newarticle () {
        return ok(
            addarticle.render(form(Article.class))
        );
    }

    public static Result deleteAll(){
        Article.deleteAll();
        return redirect(routes.ArticleController.articlelist());
    }

    public static Result add() {
        Form<Article> articleForm = form(Article.class).bindFromRequest();
        if(articleForm.hasErrors()) {
            return badRequest(addarticle.render(articleForm));
        }
        else {
            Article.create(articleForm.get());
            return redirect(routes.ArticleController.articlelist());
        }
    }
}
