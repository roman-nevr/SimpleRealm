package org.berendeev.roma.simplerealm.data;

import org.berendeev.roma.simplerealm.domain.LivecycleObject.LivecycleListener;
import org.berendeev.roma.simplerealm.domain.Repository;
import org.berendeev.roma.simplerealm.domain.model.Note;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmResults;

import static org.berendeev.roma.simplerealm.domain.model.Note.NAME;


public class RepositoryCallbackImpl implements Repository, LivecycleListener {

    private final Realm realm;

    public RepositoryCallbackImpl() {
        realm = Realm.getDefaultInstance();
    }

    @Override public void onActive() {

    }

    @Override public void onDisable() {
        realm.close();
    }

    @Override public boolean saveNote(String name, String field) {

        if (realm.where(Note.class).equalTo(NAME, name) == null){
            realm.beginTransaction();
            Note note = realm.createObject(Note.class, name);
            note.setField(field);
            realm.commitTransaction();
            return true;
        }else {
            return false;
        }
    }

    @Override public Note readNote(String name) {
        Note first = realm.where(Note.class).equalTo(NAME, name).findFirst();
        return first;
    }

    @Override public Observable<List<Note>> getNotesObservable() {
        return Observable.create(emitter -> {

            RealmResults<Note> all = realm.where(Note.class).findAll();
            all.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<Note>>() {
                @Override public void onChange(RealmResults<Note> collection, OrderedCollectionChangeSet changeSet) {
                    emitter.onNext(Collections.unmodifiableList(all));
                }
            });
            emitter.onNext(Collections.unmodifiableList(all));
        });
    }

    @Override public void deleteNote(String name) {
        realm.beginTransaction();
        Note first = realm.where(Note.class).equalTo(NAME, name).findFirst();
        if (first != null){
            first.deleteFromRealm();
        }
        realm.commitTransaction();
    }
}
