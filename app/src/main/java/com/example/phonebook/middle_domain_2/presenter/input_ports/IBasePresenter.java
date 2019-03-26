package com.example.phonebook.middle_domain_2.presenter.input_ports;

import java.util.Observer;

interface IBasePresenter extends Observer {
    void onStop();

    void onStart();

}
