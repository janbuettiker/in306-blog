package ch.hftm.blog.services;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;

import ch.hftm.blog.models.Entry;
import io.quarkus.security.Authenticated;

@ApplicationScoped
public class EntryService {

    public List<Entry> getEntries() {
        return Entry.findAll().list();
    }

    public List<Entry> searchEntries(String searchString) {
        return Entry.list("title LIKE ?1 or content LIKE ?1", "%" + searchString + "%");
    }

    public Entry findById(Long id) {
        return Entry.findById(id);
    }

    @RolesAllowed("author")
    @Transactional
    public void addEntry(Entry entry) {
        entry.persist();
    }

    @Transactional
    public Entry patchEntry(Long id, Entry entry) {
        Entry existing = Entry.findById(id);

        if (existing == null) {
            throw new WebApplicationException("Entry with id of " + id + " does not exist.", 404);
        }
        existing.title = entry.title;
        existing.content = entry.content;
        return existing;
    }

    @Transactional
    public void removeEntryById(Long id) {
        Entry.deleteById(id);
    }
}
