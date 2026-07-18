package org.example.phonebookspring.service;

import org.example.phonebookspring.entity.Group;

import java.util.Collection;

public interface GroupService {
    Group addGroup(Group group);
    void deleteGroup(String GroupName);
    Group get(String GroupName);
    Collection<Group> getAllGroups();
}
