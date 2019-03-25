package com.example.phonebook.model;

import android.net.Uri;

public class ContactFullInfo {
    private final String filter_id;
    private final String name;
    private final String phoneNo;
    private final Uri uri;

    public ContactFullInfo(String filter_id, String name, String phoneNo, Uri u) {

        this.filter_id = filter_id;
        this.name = name;
        this.phoneNo = phoneNo;
        this.uri = u;
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

    public Uri getUri() {
        return uri;
    }
}
