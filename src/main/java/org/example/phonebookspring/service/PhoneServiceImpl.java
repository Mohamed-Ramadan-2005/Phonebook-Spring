package org.example.phonebookspring.service;


import org.example.phonebookspring.dto.GroupDto;
import org.example.phonebookspring.entity.Entry;
import org.example.phonebookspring.entity.Group;
import org.example.phonebookspring.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;

@Service
public class PhoneServiceImpl implements PhoneService {
    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private GroupService groupService;
    @Transactional
    @Override
    public Entry addEntry(Entry entry, Set<GroupDto> groupDtos) {
        for (GroupDto groupDto : groupDtos) {
            Group group = groupService.get(groupDto.getGroupName());
            if (group != null) {
                group.addToEntry(entry);
            }
        }
        return phoneRepository.save(entry);
    }
    @Transactional
    @Override
    public void deleteEntry(String phoneNumber) {
        Entry entry = phoneRepository.findByPhoneNumber(phoneNumber);
        if (entry == null) {
            return;
        }
        for(Group group : entry.getGroups()) {
            entry.removeFromGroup(group);
        }
        phoneRepository.deleteByPhoneNumber(phoneNumber);
    }
    @Override
    public Entry get(String phoneNumber){
        return phoneRepository.findByPhoneNumber(phoneNumber);
    }
    @Override
    public Collection<Entry> getAllEntries() {
        return phoneRepository.findAll();
    }
    @Transactional
    @Override
    public Entry linkEntryToGroup(String phoneNumber, String groupName) {
        Entry entry = phoneRepository.findByPhoneNumber(phoneNumber);
        Group group = groupService.get(groupName);
        if (entry == null || group == null) {
            return null;
        }
        entry.addToGroup(group);
        return phoneRepository.save(entry);
    }
    @Transactional
    @Override
    public Entry unlinkEntryFromGroup(String phoneNumber, String groupName) {
        Entry entry = phoneRepository.findByPhoneNumber(phoneNumber);
        Group group = groupService.get(groupName);
        if (entry == null || group == null) {
            return null;
        }
        entry.removeFromGroup(group);
        return phoneRepository.save(entry);
    }

}
