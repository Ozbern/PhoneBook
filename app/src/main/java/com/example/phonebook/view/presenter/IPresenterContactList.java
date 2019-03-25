package com.example.phonebook.view.presenter;

import com.example.phonebook.view.presenter.adapterCallbacks.IPresenterAdapterContactsListener;
import com.example.phonebook.model.ContactShortInfo;
import com.example.phonebook.view.ui.activities.IViewContactList;

public interface IPresenterContactList extends IBasePresenter {
    void onCreate(IViewContactList activity);

    int getItemCount();

    void setAdapterListener(IPresenterAdapterContactsListener listenerPresenter);

    ContactShortInfo getContactListItem(int position);

    void onExportButtonClick();

    void onListItemClick(int position);
}
