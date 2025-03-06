package com.bridgelabz.addressbookapp.service;
import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.model.AddressBookModel;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class AddressBookService implements IAddressBookService {
    private final Map<Integer, AddressBookModel> contactMap = new HashMap<>();
    private int contactIdCounter = 1;

    @Override
    public Map<Integer, AddressBookModel> getAllEntries() {
        return contactMap;
    }

    @Override
    public AddressBookModel getEntryById(int id) {
        return contactMap.get(id);
    }

    @Override
    public AddressBookModel createEntry(AddressBookDTO dto) {
        AddressBookModel contact = new AddressBookModel(contactIdCounter++, dto.name);
        contactMap.put(contact.getId(), contact);
        return contact;
    }

    @Override
    public AddressBookModel updateEntry(int id, AddressBookDTO dto) {
        if (contactMap.containsKey(id)) {
            contactMap.get(id).setName(dto.name);
            return contactMap.get(id);
        }
        return null;
    }

    @Override
    public boolean deleteEntry(int id) {
        return contactMap.remove(id) != null;
    }
}