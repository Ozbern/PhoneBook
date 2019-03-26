package com.example.phonebook.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.example.phonebook.App;
import com.example.phonebook.R;
import com.example.phonebook.controller.data.memorycache.ICacheContactList;
import com.example.phonebook.model.ContactShortInfo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;

import javax.inject.Inject;

public class ExportService extends IntentService {
    private static final int NOTIFICATION_ID = 1;
    private static final String NOTIFICATION_CHANNEL_ID = "NOTIFICATION_CHANNEL_ID";
    private static final String SERVICE_NAME = "ExportService";
    private static final String  DIR_CONTACTS_NAME = "contacts";
    private static final String FILE_NAME = "contacts.json";

    @Inject
    ICacheContactList cacheContactList;

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
        saveJson(contactList);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void saveJson(ArrayList<ContactShortInfo> contactList) {
        String directoryName = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator + DIR_CONTACTS_NAME;
        File dir = new File(directoryName);
        if (!dir.getParentFile().exists()
                || (!dir.exists() && !dir.mkdirs())
        )
            return;
        File file = new File(dir, FILE_NAME);
        try {
            if (file.exists())
                file.delete();
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(new Gson().toJson(contactList).getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
