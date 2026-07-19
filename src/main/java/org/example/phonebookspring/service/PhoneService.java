package org.example.phonebookspring.service;

import org.example.phonebookspring.dto.GroupDto;
import org.example.phonebookspring.entity.Entry;

import java.util.Collection;
import java.util.Set;

public interface PhoneService {
    Entry addEntry(Entry entry, Set<GroupDto> groupDtos);
    void deleteEntry(String phoneNumber);
    Entry get(String phoneNumber);
    Collection<Entry> getAllEntries();
    Entry linkEntryToGroup(String phoneNumber, String groupName);
    Entry unlinkEntryFromGroup(String phoneNumber, String groupName);
}
