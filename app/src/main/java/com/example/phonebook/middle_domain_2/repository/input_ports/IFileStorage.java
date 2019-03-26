package com.example.phonebook.middle_domain_2.repository.input_ports;

import com.example.phonebook.inner_model_3.ContactShortInfo;

import java.util.ArrayList;

public interface IFileStorage {
    void saveJsonFile(ArrayList<ContactShortInfo> contactList);
}
