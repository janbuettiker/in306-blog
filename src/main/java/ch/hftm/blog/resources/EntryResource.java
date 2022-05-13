package ch.hftm.blog.resources;

import javax.inject.Inject;
import javax.transaction.Transactional;
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
        return this.entryService.getEntries();
    }

    @GET
    @Path("/add")
    @Transactional
    public void addEntry() {
        this.entryService.addDummyEntry();
    }
}