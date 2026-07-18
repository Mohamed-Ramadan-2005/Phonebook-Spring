package org.example.phonebookspring.service;

import org.example.phonebookspring.entity.Entry;

import java.util.Collection;

public interface PhoneService {
    Entry addEntry(Entry entry);
    void deleteEntry(String phoneNumber);
    Entry get(String phoneNumber);
    Collection<Entry> getAllEntries();
}
