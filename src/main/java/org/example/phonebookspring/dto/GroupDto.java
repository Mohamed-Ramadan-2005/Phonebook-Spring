package org.example.phonebookspring.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

public class GroupDto {
    @NotBlank
    @Length(min = 3, max = 20)
    private String groupName;
    private Set<EntrySummaryDto> entries;

    public Set<EntrySummaryDto> getEntries() {
        return entries;
    }

    public void setEntries(Set<EntrySummaryDto> entries) {
        this.entries = entries;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
