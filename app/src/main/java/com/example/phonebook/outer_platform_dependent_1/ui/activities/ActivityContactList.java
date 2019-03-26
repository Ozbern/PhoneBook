package com.example.phonebook.outer_platform_dependent_1.ui.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toast;

import com.example.phonebook.App;
import com.example.phonebook.R;
import com.example.phonebook.middle_domain_2.presenter.input_ports.IPresenterContactList;
import com.example.phonebook.middle_domain_2.presenter.output_ports.IViewContactList;
import com.example.phonebook.outer_platform_dependent_1.ui.adapters_recycler_view.ContactListAdapter;
import com.example.phonebook.middle_domain_2.presenter.output_ports.INavigator;

import javax.inject.Inject;

public class ActivityContactList extends ActivityBase implements IViewContactList {
    private static final int REQUEST_CODE_PERMISSION = 1;

    @Inject
    INavigator navigator;
    @Inject
    IPresenterContactList presenterContactList;

    public RecyclerView rvContactList;
    public Button export_button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewsId();

        App.getComponent().inject(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION);
        }

        presenterContactList.onCreate(this);
        navigator.set(this);
        initViews();
        setClickListeners();
    }

    private void initViewsId() {
        rvContactList = findViewById(R.id.rvContactList);
        export_button = findViewById(R.id.export_button);
    }

    private void setClickListeners() {
        export_button.setOnClickListener(v -> presenterContactList.onExportButtonClick());
    }

    private void initViews() {
        rvContactList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvContactList.setAdapter(new ContactListAdapter());

    }

    @Override
    protected void onStart() {
        super.onStart();
        presenterContactList.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenterContactList.onStop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.length != 2
                    || grantResults[0] != PackageManager.PERMISSION_GRANTED
                    || grantResults[1] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, R.string.please_grant_camera, Toast.LENGTH_SHORT).show();
            } else {
                presenterContactList.requestDataLoad();
            }
        }
    }

    @Override
    public void setCustomTitle(String title) {
        setTitle(title);
    }
}

