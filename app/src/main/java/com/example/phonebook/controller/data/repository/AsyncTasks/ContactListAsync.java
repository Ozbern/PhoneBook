package com.example.phonebook.controller.data.repository.AsyncTasks;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;

import com.example.phonebook.App;
import com.example.phonebook.controller.data.memorycache.ICacheContactList;
import com.example.phonebook.model.ContactShortInfo;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class ContactListAsync extends AsyncTask<Void, Void, ArrayList<ContactShortInfo>> {
    private final WeakReference<App> appReference;
    private final ICacheContactList cacheContactList;

    public ContactListAsync(WeakReference<App> appReference, ICacheContactList cacheContactList) {
        this.appReference = appReference;
        this.cacheContactList = cacheContactList;
    }

    @Override
    protected ArrayList<ContactShortInfo> doInBackground(Void... voids) {
        return getContactList();
    }

    @Override
    protected void onPostExecute(@NonNull ArrayList<ContactShortInfo> contactShortInfos) {
        super.onPostExecute(contactShortInfos);
        cacheContactList.setData(contactShortInfos);
    }

    @NonNull
    private ArrayList<ContactShortInfo> getContactList() {
        if (appReference.get() == null || !hasPermissions()) {
            return new ArrayList<>();
        }

        ArrayList<ContactShortInfo> result = new ArrayList<ContactShortInfo>();
        ContentResolver cr = appReference.get().getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));

                if (cur.getInt(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    if (pCur != null) {
                        while (pCur.moveToNext()) {
                            String phoneNo = pCur.getString(pCur.getColumnIndex(
                                    ContactsContract.CommonDataKinds.Phone.NUMBER));
                            result.add(new ContactShortInfo(id, name, phoneNo));
                        }
                        pCur.close();
                    }
                }
            }
        }
        if(cur!=null){
            cur.close();
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