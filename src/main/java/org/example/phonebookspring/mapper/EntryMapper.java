package org.example.phonebookspring.mapper;

import org.example.phonebookspring.dto.EntryDto;
import org.example.phonebookspring.entity.Entry;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface EntryMapper {
    Entry toEntity(EntryDto dto);
    EntryDto toDto(Entry entry);
    Collection<Entry> toEntities(Collection<EntryDto> dtos);
    Collection<EntryDto> toDtos(Collection<Entry> entries);

}
