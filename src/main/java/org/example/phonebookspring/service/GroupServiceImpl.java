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
import java.util.Set;

@Service
public class GroupServiceImpl implements  GroupService {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    PhoneRepository phoneRepository;

    @Override
    @Transactional
    public Group addGroup(Group group) {
        Set<Entry> requestedEntries = new HashSet<>(group.getEntries());
        group.setEntries(new HashSet<>());
        Group savedGroup = groupRepository.save(group);

        for (Entry requestedEntry : requestedEntries) {
            Entry managedEntry = phoneRepository.findByPhoneNumber(requestedEntry.getPhoneNumber());
            if (managedEntry == null) {
                managedEntry = new Entry();
                managedEntry.setName(requestedEntry.getName());
                managedEntry.setPhoneNumber(requestedEntry.getPhoneNumber());
                managedEntry.setCity(requestedEntry.getCity());
            }
            managedEntry.addGroup(savedGroup);
            phoneRepository.save(managedEntry);
        }

        return groupRepository.findByGroupName(savedGroup.getGroupName());
    }

    @Override
    @Transactional
    public void deleteGroup(String GroupName) {
        Group group = groupRepository.findByGroupName(GroupName);
        if (group == null) {
            return;
        }
        for (Entry entry : new HashSet<>(group.getEntries())) {
            group.removeEntry(entry);
        }
        groupRepository.delete(group);
    }

    @Override
    public Group get(String GroupName) {
        return groupRepository.findByGroupName(GroupName);
    }

    @Override
    public Collection<Group> getAllGroups() {
        return groupRepository.findAll();
    }
}
