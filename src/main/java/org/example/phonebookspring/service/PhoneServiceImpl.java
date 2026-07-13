package org.example.phonebookspring.service;


import org.example.phonebookspring.entity.Entry;
import org.example.phonebookspring.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class PhoneServiceImpl implements PhoneService {
    @Autowired
    private PhoneRepository phoneRepository;
    @Override
    public void addEntry(Entry entry) {
        phoneRepository.save(entry);
    }
    @Override
    public void deleteEntry(String phoneNumber) {
        phoneRepository.deleteByPhoneNumber(phoneNumber);
    }
    @Override
    public Entry get(String phoneNumber){
        return phoneRepository.findByPhoneNumber(phoneNumber);
    }
    @Override
    public Collection<Entry> getAllEntries() {
        return phoneRepository.findAll();
    }
}
