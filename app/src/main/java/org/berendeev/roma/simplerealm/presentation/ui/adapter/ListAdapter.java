package org.berendeev.roma.simplerealm.presentation.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpDelegate;

import org.berendeev.roma.simplerealm.R;
import org.berendeev.roma.simplerealm.domain.model.Note;
import org.berendeev.roma.simplerealm.presentation.mvp.view.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ListAdapter extends MvpBaseAdapter<ListAdapter.ItemViewHolder> {

    private List<Note> noteList;

    public ListAdapter(MvpDelegate<?> parentDelegate, List<Note> noteList) {
        super(parentDelegate, String.valueOf(0));
        this.noteList = noteList;
        hasStableIds();
    }

    @Override public long getItemId(int position) {
        return noteList.get(position).hashCode();
    }

    @Override public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflate(R.layout.list_item, parent);
        return new ItemViewHolder(view);
    }

    @Override public void onBindViewHolder(ItemViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.text.setText(note.getName() + ", " + note.getField());
    }

    @Override public int getItemCount() {
        return noteList.size();
    }

    public void update(List<Note> noteList){
        this.noteList = noteList;
        notifyDataSetChanged();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.text) TextView text;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
