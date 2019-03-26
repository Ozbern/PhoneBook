package com.example.phonebook.inner_model_3;

import java.net.URI;

public class ContactFullInfo {
    private final String id;
    private final String name;
    private final String phoneNo;
    private final URI uri;
    private Long last_update;

    public ContactFullInfo(String id, String name, String phoneNo, URI u, Long last_update) {
        this.id = id;
        this.name = name;
        this.phoneNo = phoneNo;
        this.uri = u;
        this.last_update = last_update;
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

    public Long getLast_update() {
        return last_update;
    }
}
