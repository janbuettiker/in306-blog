package ch.hftm.blog.models;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.Length;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
public class Comment extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Length(min = 15, message = "Minimum comment length wanted is 15 characters ;)")
    public String content;

    @JsonbTransient
    @ManyToOne
    public Entry entry;

    public Comment() {
        // Empty Panache Constructor
    }

}
