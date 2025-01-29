package com.nure_ua_tarasov.labtask45;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private List<Note> notes;
    private final NoteClickListener clickListener;

    public interface NoteClickListener {
        void onNoteClick(Note note);
    }

    public NoteAdapter(List<Note> notes, NoteClickListener clickListener) {
        this.notes = notes;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.title.setText(note.getTitle());
        holder.dateTime.setText(note.getDateTime());

        // Set importance icon
        switch (note.getImportance()) {
            case "High":
                holder.importanceIcon.setImageResource(R.drawable.icon_importance_high);
                break;
            case "Medium":
                holder.importanceIcon.setImageResource(R.drawable.icon_importance_medium);
                break;
            case "Low":
                holder.importanceIcon.setImageResource(R.drawable.icon_importance_low);
                break;
        }

        holder.itemView.setOnClickListener(v -> clickListener.onNoteClick(note));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void updateNotes(List<Note> updatedNotes) {
        this.notes = updatedNotes;
        notifyDataSetChanged();
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView dateTime;
        ImageView importanceIcon;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.note_title);
            dateTime = itemView.findViewById(R.id.note_date_time);
            importanceIcon = itemView.findViewById(R.id.note_importance_icon);
        }
    }
}
