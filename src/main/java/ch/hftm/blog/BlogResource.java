package ch.hftm.blog;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import ch.hftm.blog.model.Entry;

import java.util.List;

@Path("/entries")
public class BlogResource {

    @GET
    public List<Entry> getEntries() {
        return List.of(new Entry("Mein erster Blog Post", "Das ist mein Spannender Inhalt"),
                new Entry("Why Quarkus", "Because it is the best!"));
    }
}