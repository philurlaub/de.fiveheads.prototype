package controllers;


import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import static play.data.Form.form;

import models.*;
import play.mvc.Security;
import views.html.*;
import views.html.user.register;
import views.html.user.userlist;
import views.html.user.adminuserlist;

public class UserController extends Controller {

    // Zeige Registrierungsformular
    public static Result register() {
        return ok(
                register.render(form(User.class))
        );
    }

    // Neuen Benutzer anmelden
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

    // Zeige Benutzertabelle (unternehmensspezifisch)
    @Security.Authenticated(Secured.class)
    public static Result userlist() {
        return ok(
                userlist.render(
                        User.findAll())
        );
    }

    // Zeige Benutzertabelle (Alle Benutzer - nur für Administratoren -> fiveheads)
    @Security.Authenticated(Secured.class)
    public static Result adminuserlist() {
        return ok(
                adminuserlist.render(
                        User.findAll())
        );
    }

    // User löschen
    @Security.Authenticated(Secured.class)
    public static Result delete(Long id){
        User.findById(id).delete();
        return redirect(
                controllers.routes.UserController.userlist()
        );
    }

    // Punktestand setzten
    @Security.Authenticated(Secured.class)
    public static Result setPoints(Long id, Long points){
        User user = User.findById(id);
        user.points = points;
        user.save();
        return redirect(
                controllers.routes.UserController.userlist()
        );
    }

    // Rolle setzten
    @Security.Authenticated(Secured.class)
    public static Result setRole(Long id, String role){
        User user = User.findById(id);
        user.role = role;
        user.save();
        return ok();
    }
}
