package com.example.phonebook.view.ui.navigator;

import com.example.phonebook.view.ui.activities.ActivityBase;

public interface INavigator {
    void showContactDetailsActivity(ActivityBase activityBase, String contact_id, String phoneNumber);
}
