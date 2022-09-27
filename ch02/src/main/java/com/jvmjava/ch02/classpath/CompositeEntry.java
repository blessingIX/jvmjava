package com.jvmjava.ch02.classpath;

import java.io.File;
import java.util.Arrays;
import java.util.StringJoiner;

public class CompositeEntry extends AbstractEntry {

    Entry[] entries;

    public CompositeEntry() {
        this.entries = new Entry[0];
    }

    public CompositeEntry(String pathList) {
        String[] paths = pathList.split(Entry.pathListSeparator);
        this.entries = new Entry[paths.length];
        for (int i = 0; i < paths.length; i++) {
            String path = paths[i];
            entries[i] = Entry.newEntry(path);
        }
    }

    @Override
    public Entry readClass(String className) {
        for (Entry entry : this.entries) {
            Entry currentEntry;
            currentEntry = entry.readClass(className);
            if (currentEntry != null && currentEntry.getData() != null) {
                return currentEntry;
            }
        }
        return null;
    }

    @Override
    public String string() {
        StringJoiner joiner = new StringJoiner(File.separator);
        Arrays.stream(this.entries).map(Entry::string).forEach(joiner::add);
        return joiner.toString();
    }

    public void append(Entry entry) {
        Entry[] newEntries = new Entry[this.entries.length + 1];
        System.arraycopy(entries, 0, newEntries, 0, this.entries.length);
        newEntries[newEntries.length - 1] = entry;
        this.entries = newEntries;
    }

    @Override
    public String toString() {
        return "CompositeEntry{" +
                "entries=" + Arrays.toString(entries) +
                '}';
    }
}
