package controllers;


import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import static play.data.Form.form;

import models.*;
import views.html.*;
import views.html.user.register;

public class UserController extends Controller {

    // -- Registration

    public static Result register() {
        return ok(
                register.render(form(User.class))
        );
    }

    /**
     * Handle registration form submission.
     */

    public static Result registerNewUser() {
        Form<User> userForm = form(User.class).bindFromRequest();
        if(userForm.hasErrors()) {
            return badRequest(register.render(userForm));
        } else {
            User.create(userForm.get());
            return redirect(
                    controllers.routes.Application.login()
            );
        }
    }
}
