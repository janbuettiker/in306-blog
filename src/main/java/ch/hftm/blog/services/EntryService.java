package ch.hftm.blog.services;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import ch.hftm.blog.models.Entry;

@ApplicationScoped
public class EntryService {

    public List<Entry> getEntries() {
        return Entry.listAll();
    }

    @Transactional
    public void addDummyEntry() {
        var entry = new Entry("Dummy", "Entry");
        entry.persist();
    }
}
