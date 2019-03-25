package com.example.phonebook.controller.data.repository;

import com.example.phonebook.model.ContactFullInfo;

public interface IRepository{
    void request();
    ContactFullInfo requestContactDetail(String id, String phone);
}
