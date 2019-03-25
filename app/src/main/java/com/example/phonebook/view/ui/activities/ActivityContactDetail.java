package com.example.phonebook.view.ui.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phonebook.App;
import com.example.phonebook.R;
import com.example.phonebook.controller.data.repository.IRepository;
import com.example.phonebook.model.Constants;
import com.example.phonebook.view.presenter.IPresenterDetails;

import javax.inject.Inject;

public class ActivityContactDetail extends ActivityBase implements IViewContactDetails{
    private static final int REQUEST_CODE_CONTACTS = 1;

    public TextView nameText;
    public TextView phoneText;
    public ImageView imageView;

    @Inject
    IRepository repository;
    @Inject
    IPresenterDetails presenterContactDetails;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initViewsId();

        App.getComponent().inject(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)
                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CODE_CONTACTS);
        }

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String contact_id = extras.getString(Constants.CONTACT_ID_EXTRA);
            String contact_phone = extras.getString(Constants.CONTACT_PHONE_EXTRA);
            presenterContactDetails.onCreate(this);
            presenterContactDetails.setContactIdentifiers(contact_id, contact_phone);
        } else {
            finish();
        }

    }

    private void initViewsId() {
        nameText = findViewById(R.id.name);
        phoneText = findViewById(R.id.phone);
        imageView = findViewById(R.id.image);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenterContactDetails.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenterContactDetails.onStop();
    }

    @Override

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_CONTACTS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                Toast.makeText(this, R.string.please_grant_camera, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void setCustomTitle(String title) {
         setTitle(title);
    }

    @Override
    public void setName(String name) {
        nameText.setText(name);
    }

    @Override
    public void setPhone(String phone) {
        phoneText.setText(phone);
    }

    @Override
    public void setImageUri(Uri uri) {
        if (uri != null) {
            imageView.setImageURI(uri);
        }
    }
}
