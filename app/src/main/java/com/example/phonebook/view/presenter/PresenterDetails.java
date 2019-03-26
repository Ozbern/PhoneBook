package com.example.phonebook.view.presenter;

import com.example.phonebook.App;
import com.example.phonebook.R;
import com.example.phonebook.controller.data.memorycache.ICacheContactList;
import com.example.phonebook.controller.data.repository.IRepository;
import com.example.phonebook.model.ContactFullInfo;
import com.example.phonebook.view.ui.activities.ActivityBase;
import com.example.phonebook.view.ui.activities.IViewContactDetails;

import java.lang.ref.WeakReference;
import java.util.Observable;

import javax.inject.Inject;

public class PresenterDetails implements IPresenterDetails {
    @Inject
    WeakReference<App> appReference;
    @Inject
    IRepository repository;

    private IViewContactDetails activity;
    private String contact_id;
    private String contact_phone;

    public PresenterDetails() {
        App.getComponent().inject(this);
    }

    @Override
    public void onCreate(IViewContactDetails activity) {
        this.activity = activity;
        App app = appReference.get();
        if (app == null) {
            return;
        }
        activity.setCustomTitle(app.getString(R.string.contact_details));
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
        activity.setName(contactFullInfo.getName());
        activity.setLastUpdate(contactFullInfo.getLast_update());
        activity.setPhone(contactFullInfo.getPhoneNo());
        activity.setImageUri(contactFullInfo.getUri());


    }

    @Override
    public void setContactIdentifiers(String contact_id, String contact_phone) {
        this.contact_id = contact_id;
        this.contact_phone = contact_phone;
    }
}
