package com.example.phonebook.view.presenter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.phonebook.App;
import com.example.phonebook.R;
import com.example.phonebook.controller.data.memorycache.ICacheContactList;
import com.example.phonebook.controller.data.repository.IRepository;
import com.example.phonebook.service.ExportService;
import com.example.phonebook.view.presenter.adapterCallbacks.IPresenterAdapterContactsListener;
import com.example.phonebook.model.ContactShortInfo;
import com.example.phonebook.view.ui.activities.IViewContactList;
import com.example.phonebook.view.ui.navigator.INavigator;

import java.lang.ref.WeakReference;
import java.util.Observable;

import javax.inject.Inject;

public class PresenterContactList implements IPresenterContactList {
    @Inject
    WeakReference<App> appReference;
    @Inject
    IRepository repository;
    @Inject
    ICacheContactList cacheContactList;
    @Inject
    INavigator navigator;

    private IViewContactList activity;
    private IPresenterAdapterContactsListener listenerPresenter;

    public PresenterContactList() {
        App.getComponent().inject(this);
    }

    @Override
    public void onCreate(IViewContactList activity) {
        this.activity = activity;
        App app = appReference.get();
        if (app == null) {
            return;
        }
        activity.setCustomTitle(app.getString(R.string.contact_list));
        repository.request();
    }

    @Override
    public void onStop() {
        cacheContactList.deleteObserver(this);
    }

    @Override
    public void onStart() {
        cacheContactList.addObserver(this);
        refreshData();
    }

    @Override
    public int getItemCount() {
        return cacheContactList.getItemCount();
    }

    @Override
    public void setAdapterListener(IPresenterAdapterContactsListener listenerPresenter) {
        this.listenerPresenter = listenerPresenter;
    }

    @Nullable
    @Override
    public ContactShortInfo getContactListItem(int position) {
        return cacheContactList.getContactListItem(position);
    }

    @Override
    public void onExportButtonClick() {
        App appContext;
        if ((appContext = appReference.get()) != null) {
            Intent intent = new Intent(appContext, ExportService.class);
            appContext.startService(intent);
        }
    }

    @Override
    public void onListItemClick(int position) {
        ContactShortInfo contactListItem = cacheContactList.getContactListItem(position);
        if (contactListItem != null) {
            navigator.showContactDetailsActivity(contactListItem.getId(), contactListItem.getPhoneNo());
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        refreshData();
    }

    private void refreshData() {
        if (listenerPresenter != null) {
            listenerPresenter.onUpdate();
        }
    }
}
