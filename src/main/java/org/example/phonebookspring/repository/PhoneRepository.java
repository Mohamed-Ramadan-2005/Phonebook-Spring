package org.example.phonebookspring.repository;

import org.example.phonebookspring.entity.Entry;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Entry,Integer> {
    @Transactional
    @Modifying
    void deleteByPhoneNumber(String phoneNumber);
    @EntityGraph(attributePaths = {"groups"})
    List<Entry> findAll();
    @EntityGraph(attributePaths = {"groups"})
    Entry findByPhoneNumber(String phoneNumber);
}
