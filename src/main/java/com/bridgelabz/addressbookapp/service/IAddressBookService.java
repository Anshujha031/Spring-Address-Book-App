package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.model.AddressBookModel;

import java.util.List;

public interface IAddressBookService {
    List<AddressBookModel> getAllEntries();
    AddressBookModel getEntryById(int id);
    AddressBookModel createEntry(AddressBookDTO dto);
    AddressBookModel updateEntry(int id, AddressBookDTO dto);
    boolean deleteEntry(int id);
}
