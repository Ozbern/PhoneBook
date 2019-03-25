package com.example.phonebook.view.presenter;

import com.example.phonebook.view.ui.activities.IViewContactDetails;

public interface IPresenterDetails extends IBasePresenter{
    void onCreate(IViewContactDetails activity);

    void setContactIdentifiers(String contact_id, String contact_phone);
}
