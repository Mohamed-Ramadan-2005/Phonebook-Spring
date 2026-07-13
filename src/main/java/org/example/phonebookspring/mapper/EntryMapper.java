package org.example.phonebookspring.mapper;

import org.example.phonebookspring.dto.EntryResponseDto;
import org.example.phonebookspring.entity.Entry;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EntryMapper {
    Entry toEntity(EntryResponseDto dto);
    EntryResponseDto toDto(Entry entry);
    Collection<Entry> toEntities(Collection<EntryResponseDto> dtos);
    Collection<EntryResponseDto> toDtos(Collection<Entry> entries);
}
