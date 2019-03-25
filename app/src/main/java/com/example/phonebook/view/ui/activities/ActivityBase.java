package com.example.phonebook.view.ui.activities;

import android.support.v7.app.AppCompatActivity;

public abstract class ActivityBase extends AppCompatActivity {
    public void setCustomTitle(String title) {
        setTitle(title);
    }
}
