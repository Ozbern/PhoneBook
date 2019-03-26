package com.example.phonebook.outer_platform_dependent_1.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.example.phonebook.App;
import com.example.phonebook.R;
import com.example.phonebook.middle_domain_2.memorycache.ICacheContactList;
import com.example.phonebook.inner_model_3.ContactShortInfo;

import java.util.ArrayList;

import com.example.phonebook.middle_domain_2.repository.input_ports.IFileStorage;

import javax.inject.Inject;

public class ExportService extends IntentService {
    private static final int NOTIFICATION_ID = 1;
    private static final String NOTIFICATION_CHANNEL_ID = "NOTIFICATION_CHANNEL_ID";
    private static final String SERVICE_NAME = "ExportService";

    @Inject
    ICacheContactList cacheContactList;
    @Inject
    IFileStorage fileStorage;

    public ExportService() {
        super(SERVICE_NAME);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        App.getComponent().inject(this);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        builder.setContentText(getText(R.string.notification_export))
                .setSmallIcon(R.drawable.mobile_network);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, getText(R.string.channel_name), NotificationManager.IMPORTANCE_DEFAULT);
            getSystemService(NotificationManager.class).createNotificationChannel(notificationChannel);
        }

        Notification notification = builder.build();
        startForeground(NOTIFICATION_ID, notification);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        ArrayList<ContactShortInfo> contactList = cacheContactList.getContactList();
        fileStorage.saveJsonFile(contactList);
    }
}
