package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.model.AddressBookModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressBookService implements IAddressBookService {
    private final List<AddressBookModel> contactList = new ArrayList<>();
    private int contactIdCounter = 1;

    @Override
    public List<AddressBookModel> getAllEntries() {
        return contactList;
    }

    @Override
    public AddressBookModel getEntryById(int id) {
        return contactList.stream()
                .filter(contact -> contact.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public AddressBookModel createEntry(AddressBookDTO dto) {
        AddressBookModel contact = new AddressBookModel(contactIdCounter++, dto.getName());
        contactList.add(contact);
        return contact;
    }

    @Override
    public AddressBookModel updateEntry(int id, AddressBookDTO dto) {
        Optional<AddressBookModel> contactOpt = contactList.stream()
                .filter(contact -> contact.getId() == id)
                .findFirst();

        if (contactOpt.isPresent()) {
            contactOpt.get().setName(dto.getName());
            return contactOpt.get();
        }
        return null;
    }

    @Override
    public boolean deleteEntry(int id) {
        return contactList.removeIf(contact -> contact.getId() == id);
    }
}
