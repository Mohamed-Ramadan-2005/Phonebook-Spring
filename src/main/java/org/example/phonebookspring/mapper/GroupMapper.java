package org.example.phonebookspring.mapper;

import org.example.phonebookspring.dto.EntrySummaryDto;
import org.example.phonebookspring.dto.GroupDto;
import org.example.phonebookspring.entity.Entry;
import org.example.phonebookspring.entity.Group;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    GroupDto toDto(Group group);
    Group toEntity(GroupDto groupDto);
    Collection<Group> toEntities(Collection<GroupDto> dtos);
    Collection<GroupDto> toDtos(Collection<Group> groups);
    EntrySummaryDto toEntrySummaryDto(Entry entry);
}
