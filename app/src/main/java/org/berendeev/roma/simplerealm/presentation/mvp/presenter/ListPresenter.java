package org.berendeev.roma.simplerealm.presentation.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.berendeev.roma.simplerealm.domain.LivecycleObject;
import org.berendeev.roma.simplerealm.domain.Repository;
import org.berendeev.roma.simplerealm.domain.model.Note;
import org.berendeev.roma.simplerealm.presentation.App;
import org.berendeev.roma.simplerealm.presentation.mvp.view.ListView;

import io.reactivex.disposables.Disposable;

@InjectViewState
public class ListPresenter extends MvpPresenter<ListView> {
    private Repository repository;
    private LivecycleObject livecycleObject;
    private Disposable disposable;

    @Override protected void onFirstViewAttach() {
        livecycleObject = new LivecycleObject();
        repository = App.getMainComponent(livecycleObject).repository();
        subscribeOnNotes();
    }

    private void subscribeOnNotes() {
        disposable = repository.getNotesObservable()
                .subscribe(notes -> {
                    getViewState().showList(notes);
                });
    }

    @Override public void onDestroy() {
        livecycleObject.disable();
        disposable.dispose();
    }

    public void onDeleteButtonPressed(String name){
        repository.deleteNote(name);
    }

    public void onTextInput(String string) {

        Note note = repository.readNote(string);

        if (note != null){
            getViewState().showNote(note);
        }else {
            getViewState().hideNote();
        }
    }
}
