package com.bridgelabz.addressbookapp.model;

public class AddressBookModel {

    private int id;
    private String name;

    public AddressBookModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
