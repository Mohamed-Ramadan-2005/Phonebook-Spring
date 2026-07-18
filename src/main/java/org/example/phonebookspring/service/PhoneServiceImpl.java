package org.example.phonebookspring.service;


import org.example.phonebookspring.entity.Entry;
import org.example.phonebookspring.entity.Group;
import org.example.phonebookspring.repository.GroupRepository;
import org.example.phonebookspring.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;

@Service
public class PhoneServiceImpl implements PhoneService {
    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Override
    public Entry addEntry(Entry entry) {
        return phoneRepository.save(entry);
    }
    @Override
    @Transactional
    public void deleteEntry(String phoneNumber) {
        Entry entry = phoneRepository.findByPhoneNumber(phoneNumber);
        if (entry == null) {
            return;
        }
        for (Group group : new HashSet<>(entry.getGroups())) {
            entry.removeGroup(group);
        }
        phoneRepository.delete(entry);
    }

    @Override
    @Transactional
    public Entry linkEntryToGroup(String phoneNumber, String groupName) {
        Entry entry = phoneRepository.findByPhoneNumber(phoneNumber);
        Group group = groupRepository.findByGroupName(groupName);
        if (entry == null || group == null) {
            return null;
        }
        entry.addGroup(group);
        return phoneRepository.save(entry);
    }

    @Override
    @Transactional
    public Entry unlinkEntryFromGroup(String phoneNumber, String groupName) {
        Entry entry = phoneRepository.findByPhoneNumber(phoneNumber);
        Group group = groupRepository.findByGroupName(groupName);
        if (entry == null || group == null) {
            return null;
        }
        entry.removeGroup(group);
        return phoneRepository.save(entry);
    }
    @Override
    public Entry get(String phoneNumber){
        return phoneRepository.findByPhoneNumber(phoneNumber);
    }
    @Override
    public Collection<Entry> getAllEntries() {
        return phoneRepository.findAll();
    }
}
