package com.example.phonebook.middle_domain_2.repository;

import android.os.Environment;

import com.example.phonebook.inner_model_3.ContactShortInfo;
import com.example.phonebook.middle_domain_2.repository.input_ports.IFileStorage;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class FileStorage implements IFileStorage {
    private static final String DIR_CONTACTS_NAME = "contacts";
    private static final String FILE_NAME = "contacts.json";

    @Override
    public void saveJsonFile(ArrayList<ContactShortInfo> contactList) {
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

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
