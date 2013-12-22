package controllers;

import play.*;
import play.api.templates.Html;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.data.Form;

import models.*;
import views.html.*;


public class Application extends Controller {

    @Security.Authenticated(Secured.class)
    public static Result index() {
        return ok(index.render());
    }


    // -- Authentication

    public static class Login {

        public String email;
        public String password;

        public String validate() {
            if(models.User.authenticate(email, password) == null) {
                return "Invalid user or password";
            }
            return null;
        }

    }

    /**
     * Login page.
     */
    public static Result login() {
        return ok(
                login.render(form(Login.class))
        );
    }

    /**
     * Handle login form submission.
     */
    public static Result authenticate() {
        Form<Login> loginForm = form(Login.class).bindFromRequest();
        if(loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));
        } else {
            session("email", loginForm.get().email);
            return redirect(
                controllers.routes.Application.index()
            );
        }
    }

    /**
     * Logout and clean the session.
     */
    public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(
                controllers.routes.Application.login()
        );
    }

}



