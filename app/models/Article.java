package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Ebean;
import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

/**
 * UserController entity managed by Ebean
 */
@Entity
@Table(name="article")
public class Article extends Model {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Constraints.Required
    @Formats.NonEmpty
    public String title;

    @Constraints.Required
    @Column(columnDefinition = "TEXT")
    public String content;

    @Formats.DateTime(pattern="dd.MM.yyyy")
    @Constraints.Required
    public Date publicationDate;

    public String language;

    public String validate() {
        return null;
    }

    public static Article create(Article article) {
        article.save();
        return article;
    }

    // -- Queries

    public static Finder<String,Article> find = new Finder<String,Article>(String.class, Article.class);

    /**
     * Retrieve all articles.
     */
    public static List<Article> findAll() {
        return find.all();
    }

    public static void deleteAll(){
        Ebean.delete(findAll());
    }

    // --

    public String toString() {
        return "Article(" + title + " Veröffentlicht am: " + publicationDate + " mit dem Inhalt " + content + ")";
    }

}

