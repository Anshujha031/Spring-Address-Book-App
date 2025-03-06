package com.bridgelabz.addressbookapp.controller;

import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.model.AddressBookModel;
import com.bridgelabz.addressbookapp.service.IAddressBookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    @Autowired
    private IAddressBookService addressBookService;

    @GetMapping
    public ResponseEntity<List<AddressBookModel>> getAllEntries() {
        return ResponseEntity.ok(addressBookService.getAllEntries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressBookModel> getEntryById(@PathVariable int id) {
        AddressBookModel contact = addressBookService.getEntryById(id);
        return contact != null ? ResponseEntity.ok(contact) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<AddressBookModel> createEntry(@Valid @RequestBody AddressBookDTO dto) {
        AddressBookModel createdContact = addressBookService.createEntry(dto);
        return ResponseEntity.ok(createdContact);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressBookModel> updateEntry(@PathVariable int id, @Valid @RequestBody AddressBookDTO dto) {
        AddressBookModel updatedContact = addressBookService.updateEntry(id, dto);
        return updatedContact != null ? ResponseEntity.ok(updatedContact) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEntry(@PathVariable int id) {
        return addressBookService.deleteEntry(id)
                ? ResponseEntity.ok("Contact deleted successfully!")
                : ResponseEntity.notFound().build();
    }
}
