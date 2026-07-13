package org.example.phonebookspring.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class EntryResponseDto {
    @NotBlank
    @Length(min = 5, max = 20)
    private String name;
    @NotBlank
    @Length(min = 7, max = 20)
    private String phoneNumber;
    @NotBlank
    @Length(min = 3, max = 15)
    private String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
