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
    @GeneratedValue
    public Long id;

    // Enthält die Benutzer E-Mail-Adresse
    // Wird als Loginname verwendet
    @Constraints.Required
    @Formats.NonEmpty
    public String email;

    // NOCH NICHT VERWENDET
    public String name;

    // Benutzerpasswort
    @Constraints.Required
    public String password;

    // Enthält den Punktestand
    // n Punkte für den ersten Login
    public long points = 10;

    // Enthält die Rolle eines Benutzers ->
    // Nach Registrierung immer normaler Benutzer
    public String role = "user";

    // Enthält den Zeitpunkt der Registrierung
    @Formats.DateTime(pattern="dd.MM.yyyy")
    public Date registrationDate;

    // Datenbankrelation zu den Bewetungen festlegen
    @OneToMany
    private List<Rating> ratingList;
    
    // Oueryobjekt
    public static Finder<String,User> find = new Finder<String,User>(String.class, User.class);

    // Beutzer erstellen
    public static User create(User user) {
        user.registrationDate = new Date();
        user.save();
        return user;
    }

    // Benutzerdaten überprüfen
    public String validate() {
        if(User.findByEmail(email) != null) {
            return "E-Mail Adresse ist bereits registriert";
        }
        return null;
    }
    
    // Alle Benutzer finden
    public static List<User> findAll() {
        return find.all();
    }

    // Suche Benutzer anhand der ID
    public static User findById(Long id) {
        return find.where().eq("id", id).findUnique();
    }

    // Suche Benutzer anhand der E-Mail Adresse
    public static User findByEmail(String email) {
        return find.where().eq("email", email).findUnique();
    }
    
    // User authentifizieren
    public static User authenticate(String email, String password) {
        return find.where()
            .eq("email", email)
            .eq("password", password)
            .findUnique();
    }

    // String repräsentation eines Users festlegen
    public String toString() {
        return "User(" + email + ")";
    }

}

