package ch.hftm.blog.resources;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import ch.hftm.blog.models.Entry;
import ch.hftm.blog.services.EntryService;

import java.util.List;

@Path("/entries")
public class EntryResource {

    @Inject
    EntryService entryService;

    @GET
    public List<Entry> getEntries() {
        return this.entryService.getEntries();
    }

    @GET
    @Path("/add")
    public void addEntry() {
        this.entryService.addDummyEntry();
    }

    @GET
    @Path("/find/{title}")
    public Entry findByTitle(@PathParam("title") String title) {
        return this.entryService.findByTitle(title);
    }

    @GET
    @Path("/remove/{title}")
    public void removeEntryByTitle(@PathParam("title") String title) {
        this.entryService.removeEntryByTitle(title);
    }
}