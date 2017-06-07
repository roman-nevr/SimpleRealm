package org.berendeev.roma.simplerealm.domain;

import org.berendeev.roma.simplerealm.domain.model.Note;

import java.util.List;

public interface Repository {
    boolean saveNote(String name, String field);

    Note readNote(String name);

    List<Note> readAllNotes();

    void deleteNote(String name);
}
