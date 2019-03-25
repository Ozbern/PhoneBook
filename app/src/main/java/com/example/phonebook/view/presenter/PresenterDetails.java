package com.example.phonebook.view.presenter;

import com.example.phonebook.App;
import com.example.phonebook.R;
import com.example.phonebook.controller.data.memorycache.ICacheContactList;
import com.example.phonebook.controller.data.repository.IRepository;
import com.example.phonebook.model.ContactFullInfo;
import com.example.phonebook.view.ui.activities.ActivityBase;
import com.example.phonebook.view.ui.activities.IViewContactDetails;

import java.util.Observable;

import javax.inject.Inject;

public class PresenterDetails implements IPresenterDetails {
    @Inject
    IRepository repository;

    private ActivityBase activity;
    private String contact_id;
    private String contact_phone;
    private IViewContactDetails viewDependency;

    public PresenterDetails() {
        App.getComponent().inject(this);
    }

    @Override
    public void onCreate(ActivityBase activity) {
        this.activity = activity;
        activity.setCustomTitle(activity.getString(R.string.contact_details));
        repository.request();
    }

    @Override
    public void onStop() {
    }

    @Override
    public void onStart() {
        refreshData();
    }

    @Override
    public void update(Observable o, Object arg) {
        refreshData();
    }

    private void refreshData() {
        ContactFullInfo contactFullInfo = repository.requestContactDetail(contact_id, contact_phone);
        viewDependency.setName(contactFullInfo.getName());
        viewDependency.setPhone(contactFullInfo.getPhoneNo());
        viewDependency.setImageUri(contactFullInfo.getUri());


    }

    @Override
    public void setContactIdentifiers(IViewContactDetails viewDependency, String contact_id, String contact_phone) {
        this.contact_id = contact_id;
        this.contact_phone = contact_phone;
        this.viewDependency = viewDependency;
    }
}
