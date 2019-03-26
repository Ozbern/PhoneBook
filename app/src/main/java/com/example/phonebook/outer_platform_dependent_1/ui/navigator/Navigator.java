package com.example.phonebook.outer_platform_dependent_1.ui.navigator;

import android.content.Intent;
import android.os.Bundle;

import com.example.phonebook.App;
import com.example.phonebook.inner_model_3.Constants;
import com.example.phonebook.middle_domain_2.presenter.output_ports.INavigator;
import com.example.phonebook.outer_platform_dependent_1.ui.activities.ActivityBase;
import com.example.phonebook.outer_platform_dependent_1.ui.activities.ActivityContactDetail;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

public class Navigator implements INavigator {
    @Inject
    WeakReference<App> appReference;

    private WeakReference<ActivityBase> refActivity;


    public Navigator() {
        App.getComponent().inject(this);
    }

    @Override
    public void showContactDetailsActivity(String contact_id, String phoneNumber) {
        if (refActivity == null || refActivity.get() == null) return;

        Intent intent = new Intent(refActivity.get(), ActivityContactDetail.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.CONTACT_ID_EXTRA, contact_id);
        bundle.putString(Constants.CONTACT_PHONE_EXTRA, phoneNumber);
        intent.putExtras(bundle);
        refActivity.get().startActivity(intent);
    }

    @Override
    public void set(ActivityBase activity) {
        this.refActivity = new WeakReference<>(activity);
    }
}
