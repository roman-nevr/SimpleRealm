package org.berendeev.roma.simplerealm.presentation;

import android.app.Application;

import org.berendeev.roma.simplerealm.domain.model.Parent;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class App extends Application {

    @Override public void onCreate() {
        super.onCreate();
        initRealm();
    }

    private void initRealm(){
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .initialData(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.createObject(Parent.class);
                    }})
                .build();
        Realm.deleteRealm(realmConfig); // Delete Realm between app restarts.
        Realm.setDefaultConfiguration(realmConfig);
    }
}
