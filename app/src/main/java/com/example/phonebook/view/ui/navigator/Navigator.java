package com.example.phonebook.view.ui.navigator;

import android.content.Intent;
import android.os.Bundle;

import com.example.phonebook.App;
import com.example.phonebook.model.Constants;
import com.example.phonebook.view.ui.activities.ActivityBase;
import com.example.phonebook.view.ui.activities.ActivityContactDetail;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

public class Navigator implements INavigator {
    @Inject
    WeakReference<App> appReference;

    public Navigator() {
        App.getComponent().inject(this);
    }

    @Override
    public void showContactDetailsActivity(ActivityBase activityBase, String contact_id, String phoneNumber) {
        if (appReference.get() == null) {
            return;
        }
        Intent intent = new Intent(activityBase, ActivityContactDetail.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.CONTACT_ID_EXTRA, contact_id);
        bundle.putString(Constants.CONTACT_PHONE_EXTRA, phoneNumber);
        intent.putExtras(bundle);
        activityBase.startActivity(intent);
    }
}
