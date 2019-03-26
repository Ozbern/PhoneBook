package com.example.phonebook.middle_domain_2.presenter.input_ports;

import com.example.phonebook.middle_domain_2.presenter.output_ports.IAdapterContactsListener;
import com.example.phonebook.inner_model_3.ContactShortInfo;
import com.example.phonebook.middle_domain_2.presenter.output_ports.IViewContactList;

public interface IPresenterContactList extends IBasePresenter {
    void onCreate(IViewContactList activity);

    int getItemCount();

    void setAdapterListener(IAdapterContactsListener listenerPresenter);

    ContactShortInfo getContactListItem(int position);

    void onExportButtonClick();

    void onListItemClick(int position);

    void requestDataLoad();
}
