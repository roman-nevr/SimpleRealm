package org.berendeev.roma.simplerealm.presentation.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface InputView extends MvpView {
    void showInputError(String error);

    void hideInputError();
}
