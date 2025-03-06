package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.model.AddressBookModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j  // Lombok annotation for logging
@Service
public class AddressBookService implements IAddressBookService {
    private final List<AddressBookModel> contactList = new ArrayList<>();
    private int contactIdCounter = 1;

    @Override
    public List<AddressBookModel> getAllEntries() {
        log.info("Fetching all address book entries");
        return contactList;
    }

    @Override
    public AddressBookModel getEntryById(int id) {
        log.info("Fetching entry with ID: {}", id);
        return contactList.stream()
                .filter(contact -> contact.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public AddressBookModel createEntry(AddressBookDTO dto) {
        log.info("Creating new entry with name: {}", dto.getName());
        AddressBookModel contact = new AddressBookModel(contactIdCounter++, dto.getName());
        contactList.add(contact);
        return contact;
    }

    @Override
    public AddressBookModel updateEntry(int id, AddressBookDTO dto) {
        log.info("Updating entry with ID: {}", id);
        Optional<AddressBookModel> contactOpt = contactList.stream()
                .filter(contact -> contact.getId() == id)
                .findFirst();

        if (contactOpt.isPresent()) {
            contactOpt.get().setName(dto.getName());
            log.info("Updated entry with ID: {}", id);
            return contactOpt.get();
        }
        log.warn("Entry with ID: {} not found", id);
        return null;
    }

    @Override
    public boolean deleteEntry(int id) {
        log.info("Deleting entry with ID: {}", id);
        boolean removed = contactList.removeIf(contact -> contact.getId() == id);
        if (removed) {
            log.info("Successfully deleted entry with ID: {}", id);
        } else {
            log.warn("Failed to delete entry. ID: {} not found", id);
        }
        return removed;
    }
}
