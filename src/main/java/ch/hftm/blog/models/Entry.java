package ch.hftm.blog.models;

import java.util.Date;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Entry extends PanacheEntity {

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

}
