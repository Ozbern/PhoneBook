package com.example.phonebook.middle_domain_2.presenter.output_ports;

import java.net.URI;

public interface IViewContactDetails{
    void setCustomTitle(String title);

    void setName(String name);

    void setPhone(String phone);

    void setImageUri(URI uri);

    void setLastUpdate(String last_update);
}
