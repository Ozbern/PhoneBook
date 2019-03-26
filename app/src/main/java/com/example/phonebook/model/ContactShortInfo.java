package com.example.phonebook.model;

import android.net.Uri;

public class ContactShortInfo {
    private final String id;
    private final String name;
    private final String phoneNo;

    private final Uri uri;

    public ContactShortInfo(String id, String name, String phoneNo, Uri uri) {
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

    public Uri getUri() {
        return uri;
    }

}
