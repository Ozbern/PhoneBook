package com.example.phonebook;

import com.example.phonebook.middle_domain_2.memorycache.CacheContactList;
import com.example.phonebook.middle_domain_2.memorycache.ICacheContactList;
import com.example.phonebook.middle_domain_2.presenter.output_ports.IServiceHelper;
import com.example.phonebook.middle_domain_2.repository.ContactsRepository;
import com.example.phonebook.middle_domain_2.repository.FileStorage;
import com.example.phonebook.middle_domain_2.repository.input_ports.IFileStorage;
import com.example.phonebook.middle_domain_2.repository.input_ports.IRepository;
import com.example.phonebook.middle_domain_2.presenter.input_ports.IPresenterContactList;
import com.example.phonebook.middle_domain_2.presenter.input_ports.IPresenterDetails;
import com.example.phonebook.middle_domain_2.presenter.PresenterContactList;
import com.example.phonebook.middle_domain_2.presenter.PresenterDetails;
import com.example.phonebook.middle_domain_2.presenter.output_ports.INavigator;
import com.example.phonebook.outer_platform_dependent_1.service.ServiceHelper;
import com.example.phonebook.outer_platform_dependent_1.ui.navigator.Navigator;

import java.lang.ref.WeakReference;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private WeakReference<App> refApplication;

    public AppModule(App refApplication) {
        this.refApplication = new WeakReference<>(refApplication);
    }

    @Provides
    @Singleton
    WeakReference<App> applicationWeakReference(){
        return refApplication;
    }

    @Provides
    @Singleton
    IRepository repository() {
        return new ContactsRepository();
    }

    @Provides
    @Singleton
    ICacheContactList cacheContactList() {
        return new CacheContactList();
    }

    @Provides
    @Singleton
    IPresenterContactList presenterContactList() {
        return new PresenterContactList();
    }

    @Provides
    @Singleton
    IPresenterDetails presenterDetails() {
        return new PresenterDetails();
    }

    @Provides
    @Singleton
    INavigator navigator() {
        return new Navigator();
    }

    @Provides
    @Singleton
    IServiceHelper serviceHelper(){
        return new ServiceHelper();
    }

    @Provides
    @Singleton
    IFileStorage fileStorage(){
        return new FileStorage();
    }
}
