package com.example.phonebook.middle_domain_2.memorycache;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.phonebook.inner_model_3.ContactShortInfo;

import java.util.ArrayList;

public class CacheContactList extends CacheBase implements ICacheContactList {
    private ArrayList<ContactShortInfo> contactList = new ArrayList<>();

    @Override
    public void setData(@NonNull ArrayList<ContactShortInfo> result) {
        contactList = result;
        setChanged();
        notifyObservers();
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Nullable
    @Override
    public ContactShortInfo getContactListItem(int position) {
        if (position < contactList.size())
            return contactList.get(position);
        else
            return null;
    }

    @Override
    public ArrayList<ContactShortInfo> getContactList() {
        return contactList;
    }
}
