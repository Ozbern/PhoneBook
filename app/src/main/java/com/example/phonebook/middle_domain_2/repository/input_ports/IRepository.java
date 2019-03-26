package com.example.phonebook.middle_domain_2.repository.input_ports;

import com.example.phonebook.inner_model_3.ContactFullInfo;

public interface IRepository{
    void requestDataLoad();
    ContactFullInfo requestContactDetail(String id, String phone);
}
