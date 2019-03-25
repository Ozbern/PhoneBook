package com.example.phonebook.view.presenter;

import com.example.phonebook.controller.data.repository.callbacks.IPresenterAdapterContactsListener;
import com.example.phonebook.model.ContactShortInfo;
import com.example.phonebook.view.ui.activities.ActivityBase;

import java.util.Observer;

public interface IPresenterContactList extends IBasePresenter {
    int getItemCount();

    void setAdapterListener(IPresenterAdapterContactsListener listenerPresenter);

    ContactShortInfo getContactListItem(int position);

    void onExportButtonClick();

    void onListItemClick(int position);
}
