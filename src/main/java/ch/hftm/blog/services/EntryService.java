package ch.hftm.blog.services;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;

import ch.hftm.blog.models.Entry;

@ApplicationScoped
public class EntryService {

    public List<Entry> getEntries() {
        return Entry.listAll();
    }

    public Entry findByTitle(String title) {
        return Entry.findByTitle(title);
    }

    @Transactional
    public void addDummyEntry() {
        var entry = new Entry("Dummy", "Entry");
        entry.persist();
    }

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
    public void removeEntryByTitle(String title) {
        var entry = this.findByTitle(title);
        entry.delete();
    }
}
