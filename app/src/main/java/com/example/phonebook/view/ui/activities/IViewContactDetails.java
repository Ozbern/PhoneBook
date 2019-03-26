package com.example.phonebook.view.ui.activities;

import android.net.Uri;

import java.net.URI;

public interface IViewContactDetails{
    void setCustomTitle(String title);

    void setName(String name);

    void setPhone(String phone);

    void setImageUri(URI uri);

    void setLastUpdate(String last_update);
}
