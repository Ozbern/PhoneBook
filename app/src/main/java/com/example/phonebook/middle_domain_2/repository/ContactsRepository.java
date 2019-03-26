package com.example.phonebook.middle_domain_2.repository;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;

import com.example.phonebook.App;
import com.example.phonebook.middle_domain_2.memorycache.ICacheContactList;
import com.example.phonebook.middle_domain_2.repository.AsyncTasks.ContactListAsync;
import com.example.phonebook.inner_model_3.ContactFullInfo;
import com.example.phonebook.middle_domain_2.repository.input_ports.IRepository;

import java.lang.ref.WeakReference;
import java.net.URI;
import java.net.URISyntaxException;

import javax.inject.Inject;

public class ContactsRepository implements IRepository {
    @Inject
    WeakReference<App> appReference;

    @Inject
    ICacheContactList cacheContactList;
    private ContactListAsync asyncDetailTask;

    public ContactsRepository() {
        App.getComponent().inject(this);
    }

    @Override
    public void requestDataLoad() {
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

        ContentResolver cr = appReference.get().getContentResolver();
        Cursor pCur = cr.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                new String[]{filter_id}, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC");
        ContactFullInfo result = null;
        if (pCur != null) {
            while (pCur.moveToNext()) {
                String phoneNo = pCur.getString(pCur.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER));
                if (!filter_phone.equals(phoneNo))
                    continue;
                String name = pCur.getString(pCur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));
                Long last_update = pCur.getLong(pCur.getColumnIndex(
                        ContactsContract.Contacts.CONTACT_LAST_UPDATED_TIMESTAMP));
                String uriString = pCur.getString(pCur.getColumnIndex(
                        ContactsContract.Contacts.PHOTO_URI));
                URI uri = null;
                try {
                    if (uriString != null) {
                        uri = new URI(uriString);
                    }
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                result = new ContactFullInfo(filter_id, name, phoneNo, uri, last_update);
                break;
            }
            pCur.close();
        }
        return result;
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
