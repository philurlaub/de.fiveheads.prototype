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
        Article article = Article.getUnratedToUserEmail(user.id);
        if (article != null)
            return ok(rating.render(article));
        else
            return ok(nothingtorate.render(user));
    }

    // POST Rating
    public static Result saveRating(Long article, Integer rating) {
        User user = User.findByEmail(session("email"));
        Article articleObject = Article.findById(article);

        Rating newRating = new Rating();
        newRating.user = user;
        newRating.article = articleObject;
        newRating.score = rating;
        newRating.save();

        articleObject.updateAVGScore();
        user.addPoints(articleObject);

        return redirect(routes.RatingController.rating());
    }

    // GET Reward Page
    public static Result getReward() {
        return ok(
                getreward.render()
        );
    }
}
