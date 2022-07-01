package ch.hftm.blog.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import ch.hftm.blog.dtos.EntryDto;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
public class Entry extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Length(min = 5, message = "Minimum title length wanted is 5 characters ;)")
    public String title;
    public String description;
    // quarkus-hibernate-validator extension enables to set constraints through
    // annotations.
    @NotBlank
    public String content;
    public Date creationDate;
    public Date lastEditDate;

    public int likes;

    @OneToMany(mappedBy = "entry")
    public List<Comment> commentList;

    public Entry(EntryDto entryDto) {
        this(entryDto.title(), entryDto.content(), entryDto.description());
    }

    public Entry() {
        this.creationDate = new Date();
    }

    public Entry(String title, String content, String description) {
        this();
        this.title = title;
        this.content = content;
        this.description = description;

    }

}
