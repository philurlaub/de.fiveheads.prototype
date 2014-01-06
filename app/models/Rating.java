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
@Table(name="rating")
public class Rating extends Model {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Formats.DateTime(pattern="dd.MM.yyyy")
    @Constraints.Required
    public Formats.DateTime dateTime;

    private Long score = 0L;

    @ManyToOne
    private User user;

    @ManyToOne
    private Article article;

}

