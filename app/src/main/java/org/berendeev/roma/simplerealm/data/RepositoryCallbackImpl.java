package org.berendeev.roma.simplerealm.data;

import org.berendeev.roma.simplerealm.domain.LivecycleObject.LivecycleListener;
import org.berendeev.roma.simplerealm.domain.Repository;
import org.berendeev.roma.simplerealm.domain.model.Note;
import org.berendeev.roma.simplerealm.domain.model.Parent;

import java.util.Collections;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

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
        Parent parent = realm.where(Parent.class).findFirst();
        RealmList<Note> notes = parent.getNotes();
        if (realm.where(Note.class).findAllSorted(NAME).first() == null){
            Note note = realm.createObject(Note.class, name);
            notes.add(note);
            return true;
        }else {
            return false;
        }

    }

    @Override public Note readNote(String name) {
        Note first = realm.where(Note.class).equalTo(NAME, name).findFirst();
        return first;
    }

    @Override public List<Note> readAllNotes() {
        Parent parent = realm.where(Parent.class).findFirst();
        return Collections.unmodifiableList(parent.getNotes());
    }

    @Override public void deleteNote(String name) {
        realm.where(Note.class).equalTo(NAME, name).findFirst().deleteFromRealm();
    }
}
