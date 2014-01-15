package controllers;

import models.*;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.*;


import java.util.Date;

import static play.data.Form.form;


@Security.Authenticated(Secured.class)
public class RatingController extends Controller {


    // GET Rating
    public static Result rating() {
        User user = User.findByEmail(session("email")) ;
        return ok(
                rating.render(Article.getUnratedToUserEmail(user.id))
        );
    }

    // POST Rating
    public static Result saveRating(Long article, Integer rating) {
        Rating newRating = new Rating();
        newRating.user = User.findByEmail(session("email"));
        newRating.article = Article.findById(article);
        newRating.score = rating;
        newRating.save();

        return redirect(routes.RatingController.rating());
    }
}
