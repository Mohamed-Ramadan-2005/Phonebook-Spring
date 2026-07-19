package org.example.phonebookspring.service;

import org.example.phonebookspring.entity.Entry;
import org.example.phonebookspring.entity.Group;
import org.example.phonebookspring.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class GroupServiceImpl implements  GroupService {
    @Autowired
    GroupRepository groupRepository;

    @Override
    public Group addGroup(Group group) {
        return groupRepository.save(group);
    }
    @Transactional
    @Override
    public void deleteGroup(String GroupName) {
        Group group = groupRepository.findByGroupName(GroupName);
        if (group == null) {
            return;
        }
        for(Entry entry: group.getEntries()){
            group.removeFromEntry(entry);
        }
        groupRepository.deleteGroupByGroupName(GroupName);
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
