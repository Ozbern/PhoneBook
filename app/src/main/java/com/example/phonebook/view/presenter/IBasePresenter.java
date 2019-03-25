package com.example.phonebook.view.presenter;

import com.example.phonebook.view.ui.activities.ActivityBase;

import java.util.Observer;

interface IBasePresenter extends Observer {
    void onStop();

    void onStart();

}
