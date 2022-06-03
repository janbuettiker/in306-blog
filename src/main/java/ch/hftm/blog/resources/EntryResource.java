package ch.hftm.blog.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

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

    @POST
    @Path("/add-dummy-data")
    public void addDummyEntry() {
        this.entryService.addDummyEntry();
    }

    @GET
    @Path("{title}")
    public Entry findByTitle(@PathParam("title") String title) {
        return this.entryService.findByTitle(title);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addEntry(Entry entry) {
        this.entryService.addEntry(entry);
    }

    @PATCH
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Entry putEntry(@PathParam("id") Long id, Entry entry) {
        return this.entryService.patchEntry(id, entry);
    }

    @DELETE
    @Path("{title}")
    public void removeEntryByTitle(@PathParam("title") String title) {
        this.entryService.removeEntryByTitle(title);
    }
}