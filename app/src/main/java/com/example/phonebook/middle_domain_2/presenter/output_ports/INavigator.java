package com.example.phonebook.middle_domain_2.presenter.output_ports;

import com.example.phonebook.outer_platform_dependent_1.ui.activities.ActivityBase;

public interface INavigator {

    void set(ActivityBase activity);

    void showContactDetailsActivity(String contact_id, String phoneNumber);
}
