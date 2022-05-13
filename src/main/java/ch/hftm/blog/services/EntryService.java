package ch.hftm.blog.services;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import ch.hftm.blog.models.Entry;

@ApplicationScoped
public class EntryService {

    private List<Entry> entries = new ArrayList<>();

    public void addDummyEntry() {
        this.entries.add(new Entry("Dummy", "Entry"));
    }

    public List<Entry> getEntries() {
        return this.entries;
    }
}
