package org.berendeev.roma.simplerealm.presentation.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import org.berendeev.roma.simplerealm.domain.model.Note;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface ListView extends MvpView {

    void showList(List<Note> notes);

    void showNote(Note note);

    void hideNote();

    void showNotFound();
}
