package org.example.phonebookspring.controller;

import jakarta.validation.Valid;
import org.example.phonebookspring.dto.GroupDto;
import org.example.phonebookspring.entity.Group;
import org.example.phonebookspring.mapper.GroupMapper;
import org.example.phonebookspring.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    GroupService groupService;
    @Autowired
    GroupMapper groupMapper;

    @GetMapping("/{groupName}")
    public ResponseEntity<GroupDto> getGroup(@PathVariable String groupName) {
        Group group = groupService.get(groupName);
        if (group == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(groupMapper.toDto(group));
    }

    @GetMapping
    public ResponseEntity<Collection<GroupDto>> getAllGroups() {
        Collection<Group> groups = groupService.getAllGroups();
        return ResponseEntity.ok(groupMapper.toDtos(groups));
    }

    @PostMapping
    public ResponseEntity<GroupDto> addGroup(@Valid @RequestBody GroupDto dto) {
        Group group = groupMapper.toEntity(dto);
        Group savedGroup = groupService.addGroup(group);
        return ResponseEntity.status(HttpStatus.CREATED).body(groupMapper.toDto(savedGroup));
    }

    @DeleteMapping("/{groupName}")
    public ResponseEntity<Void> deleteGroup(@PathVariable String groupName) {
        Group group = groupService.get(groupName);
        if (group == null) {
            return ResponseEntity.notFound().build();
        }
        groupService.deleteGroup(groupName);
        return ResponseEntity.noContent().build();
    }
}
