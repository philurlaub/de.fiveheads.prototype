package controllers;

import static play.data.Form.*;

import play.data.Form;
import play.mvc.*;
import models.*;
import views.html.*;


@Security.Authenticated(Secured.class)
public class ArticleController extends Controller{

    // GET Artikelliste
    public static Result articlelist() {
        return ok(
            articlelist.render(
                Article.findAll())
        );
    }

    // GET Neuer Artikel
    public static Result newarticle() {
        return ok(
            addarticle.render(form(Article.class))
        );
    }

    // GET Artikel Anzeigen
    public static Result show(Long id){
        return ok(
            article.render(Article.findById(id))
        );
    }


    // POST Alle Artikel löschen
    public static Result deleteAll(){
        Article.deleteAll();
        return redirect(routes.ArticleController.articlelist());
    }

    // POST Artikel über id löschen
    public static Result delete(Long id){
        Article.delete(id);
        return redirect(routes.ArticleController.articlelist());
    }

    // POST  Neuer Artikel
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
