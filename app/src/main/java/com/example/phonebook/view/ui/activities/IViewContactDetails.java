package com.example.phonebook.view.ui.activities;

import android.net.Uri;

public interface IViewContactDetails{
    void setCustomTitle(String title);

    void setName(String name);

    void setPhone(String phone);

    void setImageUri(Uri uri);
}
