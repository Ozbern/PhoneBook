package com.example.phonebook.outer_platform_dependent_1.service;

import android.content.Intent;

import com.example.phonebook.App;
import com.example.phonebook.middle_domain_2.presenter.output_ports.IServiceHelper;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

public class ServiceHelper implements IServiceHelper {
    @Inject
    WeakReference<App> appReference;

    @Override
    public void startExportService() {
        App.getComponent().inject(this);
        App appContext;
        if ((appContext = appReference.get()) != null) {
            Intent intent = new Intent(appContext, ExportService.class);
            appContext.startService(intent);
        }

    }
}
