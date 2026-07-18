package org.example.phonebookspring.repository;

import org.example.phonebookspring.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
    Group findByGroupName(String GroupName);
    @Transactional
    @Modifying
    void deleteGroupByGroupName(String groupName);
}
