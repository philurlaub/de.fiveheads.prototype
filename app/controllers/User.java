package controllers;


import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import static play.data.Form.form;

import models.*;
import views.html.*;

public class User extends Controller {


    public static Result adduser() {
        Form<models.User> dataForm = form(models.User.class).bindFromRequest();
        //return ok(adduserPanel.render(form(models.User.class)));
        return ok("USER FORM");
    }
}