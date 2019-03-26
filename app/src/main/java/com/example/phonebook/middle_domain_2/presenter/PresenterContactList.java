package com.example.phonebook.middle_domain_2.presenter;

import android.support.annotation.Nullable;

import com.example.phonebook.App;
import com.example.phonebook.R;
import com.example.phonebook.middle_domain_2.memorycache.ICacheContactList;
import com.example.phonebook.middle_domain_2.presenter.output_ports.IServiceHelper;
import com.example.phonebook.middle_domain_2.repository.input_ports.IRepository;
import com.example.phonebook.middle_domain_2.presenter.input_ports.IPresenterContactList;
import com.example.phonebook.middle_domain_2.presenter.output_ports.IAdapterContactsListener;
import com.example.phonebook.middle_domain_2.presenter.output_ports.IViewContactList;
import com.example.phonebook.middle_domain_2.presenter.output_ports.INavigator;
import com.example.phonebook.inner_model_3.ContactShortInfo;

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
    @Inject
    IServiceHelper serviceHelper;

    private IViewContactList activity;
    private IAdapterContactsListener listenerPresenter;

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
        requestDataLoad();
    }

    @Override
    public void onStop() {
        cacheContactList.deleteObserver(this);
    }

    @Override
    public void requestDataLoad() {
        repository.requestDataLoad();
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
    public void setAdapterListener(IAdapterContactsListener listenerPresenter) {
        this.listenerPresenter = listenerPresenter;
    }

    @Nullable
    @Override
    public ContactShortInfo getContactListItem(int position) {
        return cacheContactList.getContactListItem(position);
    }

    @Override
    public void onExportButtonClick() {
        serviceHelper.startExportService();
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
