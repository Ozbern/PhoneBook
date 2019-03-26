package com.example.phonebook.model;

import java.net.URI;

public class ContactShortInfo {
    private final String id;
    private final String name;
    private final String phoneNo;
    private final URI uri;

    public ContactShortInfo(String id, String name, String phoneNo, URI uri) {
        this.id = id;
        this.name = name;
        this.phoneNo = phoneNo;
        this.uri = uri;
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

    public URI getUri() {
        return uri;
    }

}
