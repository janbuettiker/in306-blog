package ch.hftm.blog.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
public class Entry extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String title;
    public String description;
    public String content;
    public Date creationDate;
    public Date lastEditDate;

    public Entry() {
        this.creationDate = new Date();
    }

    public Entry(String title, String content) {
        this();
        this.title = title;
        this.content = content;

    }

    public static Entry findByTitle(String title) {
        return find("title", title).firstResult();
    }

}
