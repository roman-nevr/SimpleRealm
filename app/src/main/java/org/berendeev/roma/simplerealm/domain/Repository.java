package org.berendeev.roma.simplerealm.domain;

import org.berendeev.roma.simplerealm.domain.model.Note;

import java.util.List;

import io.reactivex.Observable;

public interface Repository {
    boolean saveNote(String name, String field);

    Note readNote(String name);

    Observable<List<Note>> getNotesObservable();

    void deleteNote(String name);
}
