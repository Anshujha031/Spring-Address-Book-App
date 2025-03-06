package com.bridgelabz.addressbookapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {
    private final Map<Integer, Contact> contactMap = new HashMap<>();
    private int contactIdCounter = 1;

    @GetMapping
    public ResponseEntity<Map<Integer, Contact>> getAllEntries() {
        return ResponseEntity.ok(contactMap);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getEntryById(@PathVariable int id) {
        return contactMap.containsKey(id)
                ? ResponseEntity.ok(contactMap.get(id))
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> createEntry(@RequestBody Contact contact) {
        contact.setId(contactIdCounter++);
        contactMap.put(contact.getId(), contact);
        return ResponseEntity.ok("Contact created successfully with ID: " + contact.getId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEntry(@PathVariable int id, @RequestBody Contact updatedContact) {
        if (contactMap.containsKey(id)) {
            updatedContact.setId(id);
            contactMap.put(id, updatedContact);
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

class Contact {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;
    private String email;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getZip() { return zip; }
    public void setZip(String zip) { this.zip = zip; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
