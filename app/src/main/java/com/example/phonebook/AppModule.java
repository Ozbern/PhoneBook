package com.example.phonebook;

import com.example.phonebook.controller.data.memorycache.CacheContactList;
import com.example.phonebook.controller.data.memorycache.ICacheContactList;
import com.example.phonebook.controller.data.repository.ContactsRepository;
import com.example.phonebook.controller.data.repository.IRepository;
import com.example.phonebook.view.presenter.IPresenterContactList;
import com.example.phonebook.view.presenter.IPresenterDetails;
import com.example.phonebook.view.presenter.PresenterContactList;
import com.example.phonebook.view.presenter.PresenterDetails;
import com.example.phonebook.view.ui.navigator.INavigator;
import com.example.phonebook.view.ui.navigator.Navigator;

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
}
