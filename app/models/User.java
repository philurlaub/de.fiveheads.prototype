package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

/**
 * UserController entity managed by Ebean
 */
@Entity 
@Table(name="account")
public class User extends Model {

    private static final long serialVersionUID = 1L;

	@Id
    @Constraints.Required
    @Formats.NonEmpty
    public String email;
    

    public String name;
    
    @Constraints.Required
    public String password;

    public long points = 10;

    private String role = "user";
    
    // -- Queries
    
    public static Finder<String,User> find = new Finder<String,User>(String.class, User.class);

    public static User create(User user) {
        user.save();
        return user;
    }

    public String validate() {
        if(User.findByEmail(email) != null) {
            return "E-Mail Adresse ist bereits registriert";
        }

        return null;
    }
    
    /**
     * Retrieve all users.
     */
    public static List<User> findAll() {
        return find.all();
    }

    /**
     * Retrieve a UserController from email.
     */
    public static User findByEmail(String email) {
        return find.where().eq("email", email).findUnique();
    }
    
    /**
     * Authenticate a UserController.
     */
    public static User authenticate(String email, String password) {
        return find.where()
            .eq("email", email)
            .eq("password", password)
            .findUnique();
    }

    
    // --
    
    public String toString() {
        return "UserController(" + email + ")";
    }

}

