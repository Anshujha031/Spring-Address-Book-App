package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.model.AddressBookModel;
import com.bridgelabz.addressbookapp.repository.AddressBookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return repository.findById(id).orElse(null);
    }

    @Override
    public AddressBookModel createEntry(AddressBookDTO dto) {
        log.info("Creating new entry with name: {}", dto.getName());
        AddressBookModel contact = new AddressBookModel(0, dto.getName()); // Use 0 or let JPA handle ID
        return repository.save(contact);
    }

    @Override
    public AddressBookModel updateEntry(int id, AddressBookDTO dto) {
        log.info("Updating entry with ID: {}", id);
        Optional<AddressBookModel> contactOpt = repository.findById(id);

        if (contactOpt.isPresent()) {
            AddressBookModel contact = contactOpt.get();
            contact.setName(dto.getName());
            return repository.save(contact);
        }
        log.warn("Update failed: No entry found with ID: {}", id);
        return null;
    }

    @Override
    public boolean deleteEntry(int id) {
        log.info("Deleting entry with ID: {}", id);
        if (repository.existsById(id)) {
            repository.deleteById(id);
            log.info("Successfully deleted entry with ID: {}", id);
            return true;
        } else {
            log.warn("Deletion failed: No entry found with ID: {}", id);
            return false;
        }
    }
}
