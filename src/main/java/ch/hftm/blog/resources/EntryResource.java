package ch.hftm.blog.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import ch.hftm.blog.models.Entry;
import ch.hftm.blog.services.EntryService;

import java.util.List;

@Path("/entries")
public class EntryResource {

    @Inject
    EntryService entryService;

    @GET
    public List<Entry> getEntries(@QueryParam("search") String searchString) {
        if (searchString == null || searchString.isEmpty()) {
            return this.entryService.getEntries();
        } else {
            return this.entryService.searchEntries(searchString);
        }
    }

    @GET
    @Path("{id}")
    public Entry findById(@PathParam("id") Long id) {
        var entry = this.entryService.findById(id);
        if (entry == null) {
            throw new WebApplicationException("Entry with id of " + id + " does not exist.", 404);
        }

        return entry;
    }

    @POST
    @Path("/add-dummy-data")
    public void addDummyEntry() {
        this.entryService.addDummyEntry();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEntry(Entry entry, @Context UriInfo uriInfo) {
        this.entryService.addEntry(entry);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(entry.id));
        return Response.created(builder.build()).build();
    }

    @PATCH
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Entry putEntry(@PathParam("id") Long id, Entry entry) {
        return this.entryService.patchEntry(id, entry);
    }

    @DELETE
    @Path("{id}")
    public Response removeEntryById(@PathParam("id") Long id, @HeaderParam("auth") String authKeyString) {
        if (authKeyString == null || authKeyString.isEmpty() || !authKeyString.startsWith("elevated")) {
            return Response.status(Status.FORBIDDEN).build();
        } else {
            this.entryService.removeEntryById(id);
            return Response.status(Status.ACCEPTED).build();
        }

    }

}