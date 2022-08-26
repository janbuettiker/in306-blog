package ch.hftm.blog.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;

import ch.hftm.blog.models.Comment;
import ch.hftm.blog.models.Entry;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class DataInitializationService {

    @Inject
    EntryService entryService;

    @Transactional
    public void init(@Observes StartupEvent event) {

        // Initialize Data only if there is no Data around
        if (Entry.count() < 1) {
            // ... here you can create and persist your entities

            Entry e = new Entry("Mein erster Blogbeitrag", "Hier ist wertvoller Inhalt vorhanden", "Ein Blog Beitrag");

            entryService.addEntry(e);

            Comment c1 = new Comment();
            c1.content = "Wow das ist ein wertvoller Blogpost";
            c1.entry = e;
            c1.persist();
            e.commentList.add(c1);

        }
    }
}
