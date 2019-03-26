package com.example.phonebook;

import com.example.phonebook.middle_domain_2.repository.ContactsRepository;
import com.example.phonebook.outer_platform_dependent_1.service.ExportService;
import com.example.phonebook.middle_domain_2.presenter.PresenterContactList;
import com.example.phonebook.middle_domain_2.presenter.PresenterDetails;
import com.example.phonebook.outer_platform_dependent_1.service.ServiceHelper;
import com.example.phonebook.outer_platform_dependent_1.ui.activities.ActivityContactList;
import com.example.phonebook.outer_platform_dependent_1.ui.activities.ActivityContactDetail;
import com.example.phonebook.outer_platform_dependent_1.ui.adapters_recycler_view.ContactListAdapter;
import com.example.phonebook.outer_platform_dependent_1.ui.navigator.Navigator;

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

    void inject(ExportService exportService);

    void inject(ServiceHelper serviceHelper);
}
