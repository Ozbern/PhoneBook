package com.example.phonebook.middle_domain_2.presenter.input_ports;

import com.example.phonebook.middle_domain_2.presenter.output_ports.IViewContactDetails;

public interface IPresenterDetails extends IBasePresenter{
    void onCreate(IViewContactDetails activity);

    void setContactIdentifiers(String contact_id, String contact_phone);
}
