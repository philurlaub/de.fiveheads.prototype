package models;

import java.sql.Timestamp;
import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.annotation.CreatedTimestamp;
import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

/**
 * RatingController entity managed by Ebean
 */
@Entity
@Table(name="rating")
public class Rating extends Model {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    // Speichert den Zeitpunkt der Bewertung
    @Formats.DateTime(pattern="yyyy-MM-dd HH:mm:ss")
    @CreatedTimestamp
    protected Date ratingTimestamp;

    // Enthält die vom Benutzer vergebene Bewertung
    public int score = 0;

    // Datenbankrelation zu der Tabelle "account" festlegen
    @ManyToOne
    public User user;

    // Datenbankrelation zu der Tabelle "article" festlegen
    @ManyToOne
    public Article article;

}

