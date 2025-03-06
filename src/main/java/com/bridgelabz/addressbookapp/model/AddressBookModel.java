package com.bridgelabz.addressbookapp.model;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data  // Generates getters, setters, toString, equals, and hashCode
@AllArgsConstructor  // Generates a constructor with all fields
@NoArgsConstructor   // Generates a default constructor
public class AddressBookModel {

    private int id;
    private String name;
}
