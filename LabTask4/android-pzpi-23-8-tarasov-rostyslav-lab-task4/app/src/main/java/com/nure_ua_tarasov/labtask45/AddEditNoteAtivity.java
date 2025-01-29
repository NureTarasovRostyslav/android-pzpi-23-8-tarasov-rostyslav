package com.nure_ua_tarasov.labtask45;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class AddEditNoteActivity extends AppCompatActivity {
    private EditText titleEditText, descriptionEditText;
    private Spinner importanceSpinner;
    private Button dateButton, timeButton, saveButton;
    private NoteRepository noteRepository;
    private int noteId = -1;
    private String date, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_note);

        titleEditText = findViewById(R.id.title_edit_text);
        descriptionEditText = findViewById(R.id.description_edit_text);
        importanceSpinner = findViewById(R.id.importance_spinner);
        dateButton = findViewById(R.id.date_button);
        timeButton = findViewById(R.id.time_button);
        saveButton = findViewById(R.id.save_button);

        noteRepository = new NoteRepository(this);

        // Ініціалізація спінера важливості
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.importance_levels, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        importanceSpinner.setAdapter(adapter);

        // Обробка дати та часу
        dateButton.setOnClickListener(v -> showDatePicker());
        timeButton.setOnClickListener(v -> showTimePicker());

        // Перевірка, чи це редагування існуючої нотатки
        noteId = getIntent().getIntExtra("noteId", -1);
        if (noteId != -1) {
            loadNoteForEditing(noteId);
        }

        saveButton.setOnClickListener(v -> saveNote());
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            date = dayOfMonth + "/" + (month + 1) + "/" + year;
            dateButton.setText(date);
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void showTimePicker() {
        Calendar calendar = Calendar.getInstance();
        new TimePickerDialog(this, (view, hourOfDay, minute) -> {
            time = hourOfDay + ":" + (minute < 10 ? "0" + minute : minute);
            timeButton.setText(time);
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
    }

    private void loadNoteForEditing(int id) {
        Note note = noteRepository.getNoteById(id);
        if (note != null) {
            titleEditText.setText(note.getTitle());
            descriptionEditText.setText(note.getDescription());
            date = note.getDateTime().split(" ")[0];
            time = note.getDateTime().split(" ")[1];
            dateButton.setText(date);
            timeButton.setText(time);

            String[] importanceLevels = getResources().getStringArray(R.array.importance_levels);
            int position = 0;
            for (int i = 0; i < importanceLevels.length; i++) {
                if (importanceLevels[i].equals(note.getImportance())) {
                    position = i;
                    break;
                }
            }
            importanceSpinner.setSelection(position);
        }
    }

    private void saveNote() {
        String title = titleEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();
        String importance = importanceSpinner.getSelectedItem().toString();
        String dateTime = date + " " + time;

        if (title.isEmpty()) {
            Toast.makeText(this, "Title is required", Toast.LENGTH_SHORT).show();
            return;
        }

        Note note = new Note();
        note.setTitle(title);
        note.setDescription(description);
        note.setImportance(importance);
        note.setDateTime(dateTime);

        if (noteId == -1) {
            noteRepository.addNote(note);
        } else {
            note.setId(noteId);
            noteRepository.updateNote(note);
        }

        finish();
    }
}
