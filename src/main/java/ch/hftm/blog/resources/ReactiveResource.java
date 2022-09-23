package ch.hftm.blog.resources;

import java.time.Duration;
import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.reactive.RestStreamElementType;

import ch.hftm.blog.models.Entry;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@Path("/reactive")
public class ReactiveResource {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Blocking
    @Path("blocking")
    public Uni<String> blockingExample() {
        Optional<Entry> entry = Entry.findAll().firstResultOptional();
        if (entry.isPresent()) {
            return Uni.createFrom().item(entry.get().title);
        }
        return Uni.createFrom().item("No entry around");
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("uni/{name}")
    public Uni<String> greeting(String name) {
        return Uni.createFrom().item(name)
                .onItem().transform(n -> String.format("hello %s", name));
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/multi/{name}/{count}")
    public Multi<String> greetings(String name, int count) {
        return Multi.createFrom().ticks().every(Duration.ofSeconds(1))
                .onItem().transform(n -> String.format("hello %s - %d", name, n))
                .select().first(count);
    }

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @RestStreamElementType(MediaType.TEXT_PLAIN)
    @Path("/stream/{name}/{count}")
    public Multi<String> greetingsAsStream(int count, String name) {
        return Multi.createFrom().ticks().every(Duration.ofSeconds(1))
                .onItem().transform(n -> String.format("hello %s - %d", name, n))
                .select().first(count);
    }
}