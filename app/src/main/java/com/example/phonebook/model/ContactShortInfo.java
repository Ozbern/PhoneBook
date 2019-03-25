package com.example.phonebook.model;

public class ContactShortInfo {
    private final String id;
    private final String name;
    private final String phoneNo;

    public ContactShortInfo(String id, String name, String phoneNo) {
        this.id = id;
        this.name = name;
        this.phoneNo = phoneNo;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }
}
