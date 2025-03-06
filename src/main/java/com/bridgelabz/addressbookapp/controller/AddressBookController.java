package com.bridgelabz.addressbookapp.controller;

import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.model.AddressBookModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {
    private final Map<Integer, AddressBookModel> contactMap = new HashMap<>();
    private int contactIdCounter = 1;

    @GetMapping
    public ResponseEntity<Map<Integer, AddressBookModel>> getAllEntries() {
        return ResponseEntity.ok(contactMap);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressBookModel> getEntryById(@PathVariable int id) {
        return contactMap.containsKey(id)
                ? ResponseEntity.ok(contactMap.get(id))
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> createEntry(@RequestBody AddressBookDTO dto) {
        AddressBookModel contact = new AddressBookModel(contactIdCounter++, dto.name);
        contactMap.put(contact.getId(), contact);
        return ResponseEntity.ok("Contact created successfully with ID: " + contact.getId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEntry(@PathVariable int id, @RequestBody AddressBookDTO dto) {
        if (contactMap.containsKey(id)) {
            contactMap.get(id).setName(dto.name);
            return ResponseEntity.ok("Contact updated successfully!");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEntry(@PathVariable int id) {
        return contactMap.remove(id) != null
                ? ResponseEntity.ok("Contact deleted successfully!")
                : ResponseEntity.notFound().build();
    }
}
