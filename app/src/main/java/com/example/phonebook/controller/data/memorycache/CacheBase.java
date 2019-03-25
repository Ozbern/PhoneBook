package com.example.phonebook.controller.data.memorycache;

import java.util.Observable;
import java.util.Observer;

abstract class CacheBase extends Observable implements ICacheContactList{
    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
        super.notifyObservers(null);
    }

    @Override
    public void deleteObserver(Observer o) {
        super.deleteObserver(o);
    }

    @Override
    public void notifyObservers(){
        super.notifyObservers();
    }

}
