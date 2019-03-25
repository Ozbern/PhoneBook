package com.example.phonebook.controller.data.memorycache;

import android.support.annotation.Nullable;

import com.example.phonebook.model.ContactShortInfo;

import java.util.ArrayList;
import java.util.Observer;

public interface ICacheContactList {
    void setData(ArrayList<ContactShortInfo> result);

    void addObserver(Observer o);

    void deleteObserver(Observer o);

    void notifyObservers();

    int getItemCount();

    @Nullable
    ContactShortInfo getContactListItem(int position);
}
