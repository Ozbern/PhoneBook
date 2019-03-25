package com.example.phonebook.view.ui.activities;

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
import com.example.phonebook.controller.data.repository.IRepository;
import com.example.phonebook.view.presenter.IPresenterContactList;
import com.example.phonebook.view.ui.adapters.ContactListAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityContactList extends ActivityBase implements IViewContactList{
    private static final int REQUEST_CODE_CONTACTS = 1;

    @Inject
    IRepository repository;
    @Inject
    IPresenterContactList presenterContactList;

    @BindView(R.id.rvContactList)
    public RecyclerView rvContactList;
    @BindView(R.id.export_button)
    public Button export_button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        App.getComponent().inject(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)
                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CODE_CONTACTS);
        }

        presenterContactList.onCreate(this);
        initViews();
        setClickListeners();
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
        if (requestCode == REQUEST_CODE_CONTACTS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                repository.request();
            } else {
                Toast.makeText(this, R.string.please_grant_camera, Toast.LENGTH_SHORT).show();
            }
        }
    }
}

