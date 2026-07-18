package org.example.phonebookspring.controller;

import jakarta.validation.Valid;
import org.example.phonebookspring.dto.EntryDto;
import org.example.phonebookspring.entity.Entry;
import org.example.phonebookspring.mapper.EntryMapper;
import org.example.phonebookspring.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/phone")
public class PhoneController {
    @Autowired
    PhoneService phoneService;
    @Autowired
    EntryMapper entryMapper;

    @GetMapping("/{phoneNumber}")
    public ResponseEntity<EntryDto> getEntry(@PathVariable String phoneNumber) {
        Entry entry = phoneService.get(phoneNumber);
        if (entry == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entryMapper.toDto(entry));
    }

    @GetMapping
    public ResponseEntity<Collection<EntryDto>> getEntries() {
        Collection<Entry> entries = phoneService.getAllEntries();
        return ResponseEntity.ok(entryMapper.toDtos(entries));
    }

    @PostMapping
    public ResponseEntity<EntryDto> addEntry(@Valid @RequestBody EntryDto dto) {
        Entry entry = entryMapper.toEntity(dto);
        Entry savedEntry = phoneService.addEntry(entry);
        return ResponseEntity.status(HttpStatus.CREATED).body(entryMapper.toDto(savedEntry));
    }

    @PutMapping("/{phoneNumber}/groups/{groupName}")
    public ResponseEntity<EntryDto> linkGroup(@PathVariable String phoneNumber, @PathVariable String groupName) {
        Entry linkedEntry = phoneService.linkEntryToGroup(phoneNumber, groupName);
        if (linkedEntry == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entryMapper.toDto(linkedEntry));
    }

    @DeleteMapping("/{phoneNumber}/groups/{groupName}")
    public ResponseEntity<EntryDto> unlinkGroup(@PathVariable String phoneNumber, @PathVariable String groupName) {
        Entry unlinkedEntry = phoneService.unlinkEntryFromGroup(phoneNumber, groupName);
        if (unlinkedEntry == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entryMapper.toDto(unlinkedEntry));
    }

    @DeleteMapping("/{phoneNumber}")
    public ResponseEntity<Void> deleteEntry(@PathVariable String phoneNumber) {
        Entry entry = phoneService.get(phoneNumber);
        if (entry == null) {
            return ResponseEntity.notFound().build();
        }
        phoneService.deleteEntry(phoneNumber);
        return ResponseEntity.noContent().build();
    }

}
