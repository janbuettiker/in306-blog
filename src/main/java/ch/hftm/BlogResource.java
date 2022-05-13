package ch.hftm;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import java.util.List;

import ch.hftm.Model.Entry;

@Path("/entries")
public class BlogResource {

    @GET
    public List<Entry> getEntries() {
        return List.of(new Entry("Mein erster Blog Post", "Das ist mein Spannender Inhalt"),
                new Entry("Why Quarkus", "Because it is the best!"));
    }
}