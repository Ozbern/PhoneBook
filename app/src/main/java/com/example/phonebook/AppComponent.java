package com.example.phonebook;

import com.example.phonebook.controller.data.repository.ContactsRepository;
import com.example.phonebook.view.presenter.PresenterContactList;
import com.example.phonebook.view.presenter.PresenterDetails;
import com.example.phonebook.view.ui.activities.ActivityContactList;
import com.example.phonebook.view.ui.activities.ActivityContactDetail;
import com.example.phonebook.view.ui.adapters.ContactListAdapter;
import com.example.phonebook.view.ui.navigator.Navigator;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {

    void inject(ActivityContactList activityContactList);

    void inject(ContactsRepository contactsRepository);

    void inject(PresenterContactList presenterActivityMain);

    void inject(ContactListAdapter contactListAdapter);

    void inject(Navigator navigator);

    void inject(ActivityContactDetail activityContactDetail);

    void inject(PresenterDetails presenterDetails);
}
