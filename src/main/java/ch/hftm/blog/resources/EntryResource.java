package ch.hftm.blog.resources;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.Operation;

import ch.hftm.blog.dtos.EntryDto;
import ch.hftm.blog.models.Entry;
import ch.hftm.blog.services.EntryService;
import io.quarkus.security.Authenticated;

import java.util.List;

@Path("/entries")
public class EntryResource {

    @Inject
    EntryService entryService;

    @GET
    @Authenticated
    public List<Entry> getEntries(@QueryParam("search") String searchString) {
        if (searchString == null || searchString.isEmpty()) {
            return this.entryService.getEntries();
        } else {
            return this.entryService.searchEntries(searchString);
        }
    }

    @GET
    @Authenticated
    @Path("{id}")
    public Response findById(@PathParam("id") Long id) {
        var entry = this.entryService.findById(id);
        if (entry == null) {
            throw new WebApplicationException("Entry with id of " + id + " does not exist.", 404);
        }

        EntryDto entryDto = new EntryDto(entry.title, entry.content, entry.description);

        return Response.ok(entryDto).build();
    }

    @POST
    @RolesAllowed("author")
    @Operation(summary = "POST a new Entry", description = "This will create a new entry on the service.")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    // Make sure that the Entry is valdi with javax.validation.Valid
    public Response addEntry(@Valid EntryDto userEntryDto, @Context UriInfo uriInfo) {
        var entry = new Entry(userEntryDto);

        this.entryService.addEntry(entry);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(entry.id));
        return Response.created(builder.build()).entity(userEntryDto).build();
    }

    @PATCH
    @Path("{id}")
    @Authenticated
    @Consumes(MediaType.APPLICATION_JSON)
    public Entry putEntry(@PathParam("id") Long id, Entry entry) {
        return this.entryService.patchEntry(id, entry);
    }

    @DELETE
    @Path("{id}")
    @Authenticated
    public Response removeEntryById(@PathParam("id") Long id, @HeaderParam("auth") String authKeyString) {
        if (authKeyString == null || authKeyString.isEmpty() || !authKeyString.startsWith("elevated")) {
            return Response.status(Status.FORBIDDEN).build();
        } else {
            this.entryService.removeEntryById(id);
            return Response.status(Status.ACCEPTED).build();
        }

    }

}