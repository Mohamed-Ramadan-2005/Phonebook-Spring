package org.example.phonebookspring.service;

import org.example.phonebookspring.entity.Group;
import org.example.phonebookspring.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GroupServiceImpl implements  GroupService {
    @Autowired
    GroupRepository groupRepository;

    @Override
    public Group addGroup(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public void deleteGroup(String GroupName) {
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
