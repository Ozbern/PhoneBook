package com.example.phonebook.view.ui.navigator;

import android.support.v4.app.FragmentManager;

import com.example.phonebook.view.ui.activities.ActivityBase;

public interface INavigator {

    void set(ActivityBase activity);

    void showContactDetailsActivity(String contact_id, String phoneNumber);
}
