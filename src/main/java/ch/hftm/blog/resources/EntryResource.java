package ch.hftm.blog.resources;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import ch.hftm.blog.models.Entry;
import ch.hftm.blog.services.EntryService;

import java.util.List;

@Path("/entries")
public class EntryResource {

    @Inject
    EntryService entryService;

    @GET
    public List<Entry> getEntries() {
        this.entryService.addDummyEntry();
        return this.entryService.getEntries();
    }
}