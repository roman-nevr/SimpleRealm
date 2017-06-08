package org.berendeev.roma.simplerealm.presentation;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import org.berendeev.roma.simplerealm.di.DaggerMainComponent;
import org.berendeev.roma.simplerealm.di.MainComponent;
import org.berendeev.roma.simplerealm.di.MainModule;
import org.berendeev.roma.simplerealm.domain.LivecycleObject;
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
        RealmConfiguration realmConfig = new RealmConfiguration.Builder().build();
//                .initialData(new Realm.Transaction() {
//                    @Override
//                    public void execute(Realm realm) {
//                        realm.createObject(Parent.class);
//                    }})
//                .build();
//        Realm.deleteRealm(realmConfig); // Delete Realm between app restarts.
        Realm.setDefaultConfiguration(realmConfig);
        initRealStetho();
    }

    private void initRealStetho(){
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());
    }

    public static MainComponent getMainComponent(LivecycleObject livecycleObject){
        return DaggerMainComponent.builder().mainModule(new MainModule(livecycleObject)).build();
    }
}
