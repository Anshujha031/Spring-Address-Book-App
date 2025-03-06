package com.bridgelabz.addressbookapp.model;

import jakarta.persistence.*;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data  // Generates getters, setters, toString, equals, and hashCode
@AllArgsConstructor  // Generates a constructor with all fields
@NoArgsConstructor   // Generates a default constructor
@Entity
@Table(name = "address_book")
public class AddressBookModel {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
    private String name;
}
