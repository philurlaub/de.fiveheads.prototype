package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.annotation.CreatedTimestamp;
import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

/**
 * Keyword entity managed by Ebean
 */
@Entity
@Table(name="keyword")
public class Keyword extends Model {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    public Long id;

    public String name;

    // Datenbankrelation zu der Tabelle "account" festlegen
    @ManyToMany
    public List<Article> articleList;

    // Oueryobjekt
    public static Finder<String,Keyword> find = new Finder<String,Keyword>(String.class, Keyword.class);

    // Alle Keywords finden
    public static List<Keyword> findAll() {
        return find.all();
    }

    // Suche Keyword anhand der ID
    public static Keyword findById(Long id) {
        return find.where().eq("id", id).findUnique();
    }

}

