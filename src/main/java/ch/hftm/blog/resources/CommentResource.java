package ch.hftm.blog.resources;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ch.hftm.blog.models.Comment;
import ch.hftm.blog.services.EntryService;

@Path("/entries/{id}/comment")
public class CommentResource {

    @Inject
    EntryService entryService;

    @GET
    public List<Comment> getComments() {
        return Comment.findAll().list();

    }

    @GET
    @Path("{id}")
    public Comment findById(@PathParam("id") Long id) {
        return Comment.findById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addComment(@Valid Comment comment, @PathParam("id") Long id) {
        comment.entry = entryService.findById(id);
        System.out.println(comment.entry);
        comment.persist();
        return Response.ok(comment).build();
    }

    @PATCH
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Comment patchComment(@PathParam("id") Long id, Comment comment) {
        Comment c = Comment.findById(id);
        if (c == null) {
            throw new WebApplicationException("Entry with id of " + id + " does not exist.", 404);
        }

        c.content = comment.content;
        return c;
    }
}
