package controllers;

import models.Article;
import models.User;
import models.WekaTest;
import play.api.Routes;
import play.api.templates.Html;
import play.mvc.*;

import static play.data.Form.*;
import play.data.Form;

import views.html.*;
import views.html.user.*;

import java.util.Date;


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
                return "Ungültige E-Mail Adresse oder Passwort";
            }
            return null;
        }
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
     * Login page.
     */
    public static Result login() {
        return ok(
                login.render(form(Login.class))
        );
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

    //Erzeugt eine Reihe von Demo-Daten
    public static Result demoData() {
        User dummy1 = new User(); dummy1.email = "dummy1@dummy.de"; dummy1.role = "user"; dummy1.registrationDate = new Date(); dummy1.save();
        User dummy2 = new User(); dummy2.email = "dummy2@dummy.de"; dummy2.role = "user"; dummy2.registrationDate = new Date(); dummy2.save();
        User dummy3 = new User(); dummy3.email = "dummy3@dummy.de"; dummy3.role = "user"; dummy3.registrationDate = new Date(); dummy3.save();
        User dummy4 = new User(); dummy4.email = "dummy4@dummy.de"; dummy4.role = "user"; dummy4.registrationDate = new Date(); dummy4.save();
        User dummy5 = new User(); dummy5.email = "dummy5@dummy.de"; dummy5.role = "user"; dummy5.registrationDate = new Date(); dummy5.save();
        User dummy6 = new User(); dummy6.email = "dummy6@dummy.de"; dummy6.role = "user"; dummy6.registrationDate = new Date(); dummy6.save();
        User dummy7 = new User(); dummy7.email = "dummy7@dummy.de"; dummy7.role = "user"; dummy7.registrationDate = new Date(); dummy7.save();
        User dummy8 = new User(); dummy8.email = "dummy8@dummy.de"; dummy8.role = "user"; dummy8.registrationDate = new Date(); dummy8.save();
        User dummy9 = new User(); dummy9.email = "dummy9@dummy.de"; dummy9.role = "user"; dummy9.registrationDate = new Date(); dummy9.save();
        User dummy10 = new User(); dummy10.email = "dummy10@dummy.de"; dummy10.role = "user"; dummy10.registrationDate = new Date(); dummy10.save();
        User dummy11 = new User(); dummy11.email = "dummy11@dummy.de"; dummy11.role = "user"; dummy11.registrationDate = new Date(); dummy11.save();
        User dummy12 = new User(); dummy12.email = "dummy12@dummy.de"; dummy12.role = "user"; dummy12.registrationDate = new Date(); dummy12.save();
        User dummy13 = new User(); dummy13.email = "dummy13@dummy.de"; dummy13.role = "user"; dummy13.registrationDate = new Date(); dummy13.save();
        User dummy14 = new User(); dummy14.email = "dummy14@dummy.de"; dummy14.role = "user"; dummy14.registrationDate = new Date(); dummy14.save();
        User dummy15 = new User(); dummy15.email = "dummy15@dummy.de"; dummy15.role = "user"; dummy15.registrationDate = new Date(); dummy15.save();
        User dummy16 = new User(); dummy16.email = "dummy16@dummy.de"; dummy16.role = "user"; dummy16.registrationDate = new Date(); dummy16.save();
        User dummy17 = new User(); dummy17.email = "dummy17@dummy.de"; dummy17.role = "user"; dummy17.registrationDate = new Date(); dummy17.save();
        User dummy18 = new User(); dummy18.email = "dummy18@dummy.de"; dummy18.role = "user"; dummy18.registrationDate = new Date(); dummy18.save();
        User dummy19 = new User(); dummy19.email = "dummy19@dummy.de"; dummy19.role = "user"; dummy19.registrationDate = new Date(); dummy19.save();
        User dummy20 = new User(); dummy20.email = "dummy20@dummy.de"; dummy20.role = "user"; dummy20.registrationDate = new Date(); dummy20.save();
        User dummy21 = new User(); dummy21.email = "dummy21@dummy.de"; dummy21.role = "user"; dummy21.registrationDate = new Date(); dummy21.save();
        User dummy22 = new User(); dummy22.email = "dummy22@dummy.de"; dummy22.role = "user"; dummy22.registrationDate = new Date(); dummy22.save();
        User dummy23 = new User(); dummy23.email = "dummy23@dummy.de"; dummy23.role = "user"; dummy23.registrationDate = new Date(); dummy23.save();
        User dummy24 = new User(); dummy24.email = "dummy24@dummy.de"; dummy24.role = "user"; dummy24.registrationDate = new Date(); dummy24.save();
        User dummy25 = new User(); dummy25.email = "dummy25@dummy.de"; dummy25.role = "user"; dummy25.registrationDate = new Date(); dummy25.save();
        User dummy26 = new User(); dummy26.email = "dummy26@dummy.de"; dummy26.role = "user"; dummy26.registrationDate = new Date(); dummy26.save();
        User dummy27 = new User(); dummy27.email = "dummy27@dummy.de"; dummy27.role = "user"; dummy27.registrationDate = new Date(); dummy27.save();
        User dummy28 = new User(); dummy28.email = "dummy28@dummy.de"; dummy28.role = "user"; dummy28.registrationDate = new Date(); dummy28.save();
        User dummy29 = new User(); dummy29.email = "dummy29@dummy.de"; dummy29.role = "user"; dummy29.registrationDate = new Date(); dummy29.save();

        Article demo1 = new Article(); demo1.title = "Titel 1"; demo1.content = "Content 1"; demo1.publicationDate = new Date(); demo1.save();
        Article demo2 = new Article(); demo2.title = "Titel 2"; demo2.content = "Content 2"; demo2.publicationDate = new Date(); demo2.save();
        Article demo3 = new Article(); demo3.title = "Titel 3"; demo3.content = "Content 3"; demo3.publicationDate = new Date(); demo3.save();
        Article demo4 = new Article(); demo4.title = "Titel 4"; demo4.content = "Content 4"; demo4.publicationDate = new Date(); demo4.save();
        Article demo5 = new Article(); demo5.title = "Titel 5"; demo5.content = "Content 5"; demo5.publicationDate = new Date(); demo5.save();
        Article demo6 = new Article(); demo6.title = "Titel 6"; demo6.content = "Content 6"; demo6.publicationDate = new Date(); demo6.save();
        Article demo7 = new Article(); demo7.title = "Titel 7"; demo7.content = "Content 7"; demo7.publicationDate = new Date(); demo7.save();
        Article demo8 = new Article(); demo8.title = "Titel 8"; demo8.content = "Content 8"; demo8.publicationDate = new Date(); demo8.save();
        Article demo9 = new Article(); demo9.title = "Titel 9"; demo9.content = "Content 9"; demo9.publicationDate = new Date(); demo9.save();
        Article demo10 = new Article(); demo10.title = "Titel 10"; demo10.content = "Content 10"; demo10.publicationDate = new Date(); demo10.save();
        Article demo11 = new Article(); demo11.title = "Titel 11"; demo11.content = "Content 11"; demo11.publicationDate = new Date(); demo11.save();
        Article demo12 = new Article(); demo12.title = "Titel 12"; demo12.content = "Content 12"; demo12.publicationDate = new Date(); demo12.save();
        Article demo13 = new Article(); demo13.title = "Titel 13"; demo13.content = "Content 13"; demo13.publicationDate = new Date(); demo13.save();
        Article demo14 = new Article(); demo14.title = "Titel 14"; demo14.content = "Content 14"; demo14.publicationDate = new Date(); demo14.save();
        Article demo15 = new Article(); demo15.title = "Titel 15"; demo15.content = "Content 15"; demo15.publicationDate = new Date(); demo15.save();
        Article demo16 = new Article(); demo16.title = "Titel 16"; demo16.content = "Content 16"; demo16.publicationDate = new Date(); demo16.save();
        Article demo17 = new Article(); demo17.title = "Titel 17"; demo17.content = "Content 17"; demo17.publicationDate = new Date(); demo17.save();
        Article demo18 = new Article(); demo18.title = "Titel 18"; demo18.content = "Content 18"; demo18.publicationDate = new Date(); demo18.save();
        Article demo19 = new Article(); demo19.title = "Titel 19"; demo19.content = "Content 19"; demo19.publicationDate = new Date(); demo19.save();
        Article demo20 = new Article(); demo20.title = "Titel 20"; demo20.content = "Content 20"; demo20.publicationDate = new Date(); demo20.save();
        Article demo21 = new Article(); demo21.title = "Titel 21"; demo21.content = "Content 21"; demo21.publicationDate = new Date(); demo21.save();
        Article demo22 = new Article(); demo22.title = "Titel 22"; demo22.content = "Content 22"; demo22.publicationDate = new Date(); demo22.save();
        Article demo23 = new Article(); demo23.title = "Titel 23"; demo23.content = "Content 23"; demo23.publicationDate = new Date(); demo23.save();
        Article demo24 = new Article(); demo24.title = "Titel 24"; demo24.content = "Content 24"; demo24.publicationDate = new Date(); demo24.save();
        Article demo25 = new Article(); demo25.title = "Titel 25"; demo25.content = "Content 25"; demo25.publicationDate = new Date(); demo25.save();
        Article demo26 = new Article(); demo26.title = "Titel 26"; demo26.content = "Content 26"; demo26.publicationDate = new Date(); demo26.save();
        Article demo27 = new Article(); demo27.title = "Titel 27"; demo27.content = "Content 27"; demo27.publicationDate = new Date(); demo27.save();
        Article demo28 = new Article(); demo28.title = "Titel 28"; demo28.content = "Content 28"; demo28.publicationDate = new Date(); demo28.save();
        Article demo29 = new Article(); demo29.title = "Titel 29"; demo29.content = "Content 29"; demo29.publicationDate = new Date(); demo29.save();

        return redirect(
                controllers.routes.Application.index()
        );
    }

}



