package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.exception.AddressBookException;
import com.bridgelabz.addressbookapp.model.AddressBookModel;
import com.bridgelabz.addressbookapp.repository.AddressBookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AddressBookService implements IAddressBookService {

    @Autowired
    private AddressBookRepository repository;

    @Override
    public List<AddressBookModel> getAllEntries() {
        log.debug("Fetching all address book entries from DB");
        return repository.findAll();
    }

    @Override
    public AddressBookModel getEntryById(int id) {
        log.info("Fetching entry with ID: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> new AddressBookException("Address Book entry with ID " + id + " not found."));
    }

    @Override
    public AddressBookModel createEntry(AddressBookDTO dto) {
        log.info("Creating new entry with name: {}", dto.getName());
        AddressBookModel contact = new AddressBookModel(0, dto.getName()); // Let JPA handle ID
        return repository.save(contact);
    }

    @Override
    public AddressBookModel updateEntry(int id, AddressBookDTO dto) {
        log.info("Updating entry with ID: {}", id);
        AddressBookModel contact = repository.findById(id)
                .orElseThrow(() -> new AddressBookException("Cannot update. Address Book entry with ID " + id + " not found."));
        contact.setName(dto.getName());
        return repository.save(contact);
    }

    @Override
    public boolean deleteEntry(int id) {
        log.info("Deleting entry with ID: {}", id);
        if (!repository.existsById(id)) {
            log.warn("Deletion failed: No entry found with ID: {}", id);
            throw new AddressBookException("Cannot delete. Address Book entry with ID " + id + " not found.");
        }
        repository.deleteById(id);
        log.info("Successfully deleted entry with ID: {}", id);
        return true;
    }
}
