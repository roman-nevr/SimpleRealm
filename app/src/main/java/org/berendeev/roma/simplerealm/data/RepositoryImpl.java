package org.berendeev.roma.simplerealm.data;

import org.berendeev.roma.simplerealm.domain.LivecycleStage;
import org.berendeev.roma.simplerealm.domain.Repository;
import org.berendeev.roma.simplerealm.domain.model.Note;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import static org.berendeev.roma.simplerealm.domain.LivecycleStage.destruction;


public class RepositoryImpl implements Repository {
    private final Disposable disposable;

    public RepositoryImpl(Observable<LivecycleStage> livecycleobservable) {
        disposable = livecycleobservable.subscribe(livecycleStage -> {
            if (livecycleStage == destruction) {
                close();
            }
        });
    }

    private void close() {
        disposable.dispose();
    }

    @Override public void saveNote(Note note) {

    }

    @Override public Note readNote(String name) {
        return null;
    }

    @Override public List<Note> readAllNotes() {
        return null;
    }

    @Override public void deleteNote(String name) {

    }
}
