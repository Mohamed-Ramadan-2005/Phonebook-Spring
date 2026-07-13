package org.example.phonebookspring.controller;

import jakarta.validation.Valid;
import org.example.phonebookspring.dto.EntryResponseDto;
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
    public ResponseEntity<EntryResponseDto> getEntry(@PathVariable String phoneNumber) {
        Entry entry = phoneService.get(phoneNumber);
        if(entry == null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entryMapper.toDto(entry));
    }
    @GetMapping
    public ResponseEntity<Collection<EntryResponseDto>> getEntries() {
        Collection<Entry> entries = phoneService.getAllEntries();
        return ResponseEntity.ok(entryMapper.toDtos(entries));
    }
    @PostMapping
    public ResponseEntity<Void> addEntry(@Valid @RequestBody EntryResponseDto dto) {
        Entry entry = entryMapper.toEntity(dto);
        phoneService.addEntry(entry);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @DeleteMapping("/{phoneNumber}")
    public ResponseEntity<Void> deleteEntry(@PathVariable String phoneNumber) {
        phoneService.deleteEntry(phoneNumber);
        return ResponseEntity.noContent().build();
    }

}
