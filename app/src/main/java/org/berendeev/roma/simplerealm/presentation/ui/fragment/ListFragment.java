package org.berendeev.roma.simplerealm.presentation.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.MvpFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import org.berendeev.roma.simplerealm.R;
import org.berendeev.roma.simplerealm.domain.model.Note;
import org.berendeev.roma.simplerealm.presentation.mvp.presenter.InputPresenter;
import org.berendeev.roma.simplerealm.presentation.mvp.presenter.ListPresenter;
import org.berendeev.roma.simplerealm.presentation.mvp.view.InputView;
import org.berendeev.roma.simplerealm.presentation.mvp.view.ListView;
import org.berendeev.roma.simplerealm.presentation.ui.adapter.ListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListFragment extends MvpAppCompatFragment implements ListView {

    @BindView(R.id.name) EditText etName;
    @BindView(R.id.details) TextView tvDetails;
    @BindView(R.id.delete_button) Button btnDelete;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    @InjectPresenter ListPresenter presenter;

    private ListAdapter adapter;

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_layout, container, false);
        initUi(view);
        return view;
    }

    private void initUi(View view) {
        ButterKnife.bind(this, view);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                String name = etName.getText().toString();
                presenter.onDeleteButtonPressed(name);
            }
        });

        initEditText();
    }

    private void initEditText() {
        etName.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.onTextInput(s.toString());
            }

            @Override public void afterTextChanged(Editable s) {

            }
        });
    }

    public static Fragment getInstance() {
        return new ListFragment();
    }

    @Override public void showList(List<Note> notes) {
        if (adapter == null){
            adapter = new ListAdapter(getMvpDelegate(), notes);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }else {
            adapter.update(notes);
        }
    }

    @Override public void showNote(Note note) {
        tvDetails.setText(note.getName() + ", " + note.getField());
    }

    @Override public void hideNote() {
        tvDetails.setText("");
    }

    @Override public void showNotFound() {
        tvDetails.setText("not Found");
    }
}
