package com.example.phonebook.controller.data.repository;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;

import com.example.phonebook.App;
import com.example.phonebook.controller.data.memorycache.ICacheContactList;
import com.example.phonebook.controller.data.repository.AsyncTasks.ContactListAsync;
import com.example.phonebook.model.ContactFullInfo;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

public class ContactsRepository implements IRepository {
    private static final String TAG = "ContactsRepositoryLog";
    @Inject
    WeakReference<App> appReference;

    @Inject
    ICacheContactList cacheContactList;
    private ContactListAsync asyncDetailTask;

    public ContactsRepository() {
        App.getComponent().inject(this);
    }

    @Override
    public void request() {
        if (asyncDetailTask != null)
            asyncDetailTask.cancel(true);

        asyncDetailTask = new ContactListAsync(appReference, cacheContactList);

        asyncDetailTask.execute();
    }

    @Override
    public ContactFullInfo requestContactDetail(String filter_id, String filter_phone) {
        if (appReference.get() == null || !hasPermissions()) {
            return null;
        }

        Uri u = getPhotoUri(filter_id);

        ContentResolver cr = appReference.get().getContentResolver();
        Cursor pCur = cr.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                new String[]{filter_id}, null);
        ContactFullInfo result = null;
        if (pCur != null) {
            while (pCur.moveToNext()) {
                String phoneNo = pCur.getString(pCur.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER));
                if (!filter_phone.equals(phoneNo))
                    continue;
                String name = pCur.getString(pCur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));
                result = new ContactFullInfo(filter_id, name, phoneNo, u);
                break;
            }
            pCur.close();
        }
        return result;

    }

    private Uri getPhotoUri(String filter_id) {
        try {
            Cursor cur = appReference.get().getContentResolver().query(
                    ContactsContract.Data.CONTENT_URI,
                    null,
                    ContactsContract.Data.CONTACT_ID + "=" + filter_id + " AND "
                            + ContactsContract.Data.MIMETYPE + "='"
                            + ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE + "'", null,
                    null);
            if (cur != null) {
                if (!cur.moveToFirst()) {
                    return null; // no photo
                }
            } else {
                return null; // error in cursor process
            }
            cur.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        Uri person = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long
                .parseLong(filter_id));
        return Uri.withAppendedPath(person, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);
    }

    private boolean hasPermissions() {
        App appContext = appReference.get();
        if (appContext == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return appContext.checkSelfPermission(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED;
        }

        return true;
    }

}
