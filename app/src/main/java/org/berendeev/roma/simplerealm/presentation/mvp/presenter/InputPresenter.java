package org.berendeev.roma.simplerealm.presentation.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.berendeev.roma.simplerealm.domain.LivecycleObject;
import org.berendeev.roma.simplerealm.domain.Repository;
import org.berendeev.roma.simplerealm.presentation.App;
import org.berendeev.roma.simplerealm.presentation.mvp.view.InputView;

@InjectViewState
public class InputPresenter extends MvpPresenter<InputView> {

    private Repository repository;
    private LivecycleObject livecycleObject;

    @Override protected void onFirstViewAttach() {
        livecycleObject = new LivecycleObject();
        repository = App.getMainComponent(livecycleObject).repository();
    }

    public void onAddButtonPressed(String name, String field) {
        if (!repository.saveNote(name, field)){
            getViewState().showInputError("name must be unique");
        }else {
            getViewState().hideInputError();
        }
    }

    @Override public void onDestroy() {
        livecycleObject.disable();
    }
}
