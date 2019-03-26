package com.example.phonebook.model;

import android.net.Uri;

import java.net.URI;

public class ContactFullInfo {
    private final String filter_id;
    private final String name;
    private final String phoneNo;
    private final URI uri;
    private String last_update;

    public ContactFullInfo(String filter_id, String name, String phoneNo, URI u, String last_update) {
        this.filter_id = filter_id;
        this.name = name;
        this.phoneNo = phoneNo;
        this.uri = u;
        this.last_update = last_update;
    }

    public String getFilter_id() {
        return filter_id;
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

    public String getLast_update() {
        return last_update;
    }
}
