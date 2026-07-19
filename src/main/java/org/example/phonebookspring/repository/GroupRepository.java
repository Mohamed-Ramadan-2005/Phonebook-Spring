package org.example.phonebookspring.repository;

import org.example.phonebookspring.entity.Group;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
    @EntityGraph(attributePaths = {"entries"})
    Group findByGroupName(String GroupName);
    @EntityGraph(attributePaths = {"entries"})
    java.util.List<Group> findAll();
    @Transactional
    @Modifying
    void deleteGroupByGroupName(String groupName);
}
