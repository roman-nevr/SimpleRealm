package org.berendeev.roma.simplerealm.presentation.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import org.berendeev.roma.simplerealm.R;
import org.berendeev.roma.simplerealm.presentation.mvp.presenter.InputPresenter;
import org.berendeev.roma.simplerealm.presentation.mvp.view.InputView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InputFragment extends MvpAppCompatFragment implements InputView {

    @BindView(R.id.name) EditText etName;
    @BindView(R.id.field) EditText etField;
    @BindView(R.id.add_button) Button btnAdd;

    @InjectPresenter InputPresenter presenter;

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.input, container, false);
        initUi(view);
        return view;
    }

    private void initUi(View view) {
        ButterKnife.bind(this, view);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                String name = etName.getText().toString();
                String field = etField.getText().toString();
                presenter.onAddButtonPressed(name, field);
            }
        });
    }

    public static Fragment getInstance() {
        return new InputFragment();
    }

    @Override public void showInputError(String error) {
        etName.setError(error);
    }

    @Override public void hideInputError() {
        etName.setError(null);
    }
}
