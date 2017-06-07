package org.berendeev.roma.simplerealm.domain.model;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Parent extends RealmObject {
    @SuppressWarnings("unused")
    private RealmList<Note> notes;

    public RealmList<Note> getNotes() {
        return notes;
    }

}
