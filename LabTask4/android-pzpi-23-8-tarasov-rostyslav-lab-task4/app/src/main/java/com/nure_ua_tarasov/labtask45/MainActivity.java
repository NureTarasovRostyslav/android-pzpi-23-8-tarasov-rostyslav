package com.nure_ua_tarasov.labtask45;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_PICK = 1;
    private Uri selectedImageUri;
    private RecyclerView recyclerView;
    private NoteAdapter adapter;
    private List<Note> notes;
    private EditText searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notes = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerViewNotes);
        adapter = new NoteAdapter(notes, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        setupActionBar();
    }

    private void setupActionBar() {
        Objects.requireNonNull(getSupportActionBar()).setTitle("Title");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_add) {
            showNoteDialog(null, -1);
            return true;
        } else if (item.getItemId() == R.id.menu_filter) {
            showFilterDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showFilterDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_filter_notes, null);
        EditText inputFilterTitle = dialogView.findViewById(R.id.inputFilterTitle);
        EditText inputFilterDescription = dialogView.findViewById(R.id.inputFilterDescription);
        Spinner spinnerFilterImportance = dialogView.findViewById(R.id.spinnerFilterImportance);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        builder.setTitle(R.string.filter_notes);
        builder.setPositiveButton(R.string.ok, (dialog, which) -> {
            String filterTitle = inputFilterTitle.getText().toString().toLowerCase();
            String filterDescription = inputFilterDescription.getText().toString().toLowerCase();
            String filterImportance = spinnerFilterImportance.getSelectedItem().toString();

            adapter.filterByMultipleCriteria(filterTitle, filterDescription, filterImportance.equals("All") ? null : filterImportance);
        });
        builder.setNegativeButton(R.string.cancel, null);
        builder.create().show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Search functionality
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        assert searchView != null;
        searchView.setQueryHint(getString(R.string.search_hint));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return true;
            }
        });

        return true;
    }

    void showNoteDialog(Note noteToEdit, int position) {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_note, null);
        EditText inputTitle = dialogView.findViewById(R.id.inputNoteTitle);
        EditText inputDescription = dialogView.findViewById(R.id.inputNoteDescription);
        Spinner spinnerImportance = dialogView.findViewById(R.id.spinnerImportance);
        Button buttonSelectImage = dialogView.findViewById(R.id.buttonSelectImage);
        ImageView imagePreview = dialogView.findViewById(R.id.imagePreview);

        if (noteToEdit != null) {
            inputTitle.setText(noteToEdit.getTitle());
            inputDescription.setText(noteToEdit.getDescription());
            selectedImageUri = Uri.parse(noteToEdit.getImageUri());
            imagePreview.setImageURI(selectedImageUri);
        }

        buttonSelectImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, REQUEST_IMAGE_PICK);
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        builder.setTitle(noteToEdit == null ? R.string.add_note_title : R.string.edit_note_title);
        builder.setPositiveButton(R.string.ok, (dialog, which) -> {
            String title = inputTitle.getText().toString();
            String description = inputDescription.getText().toString();
            String importance = spinnerImportance.getSelectedItem().toString();
            String timestamp = String.valueOf(System.currentTimeMillis());
            String imageUri = selectedImageUri != null ? selectedImageUri.toString() : null;

            Note note = new Note(title, description, importance, timestamp, imageUri);
            if (position >= 0) {
                notes.set(position, note);
            } else {
                notes.add(note);
            }
            adapter.notifyDataSetChanged();
        });
        builder.setNegativeButton(R.string.cancel, null);
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
        }
    }

    public void showDeleteConfirmDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.delete_note_title);
        builder.setMessage(R.string.delete_note_message);
        builder.setPositiveButton(R.string.ok, (dialog, which) -> {
            notes.remove(position);
            adapter.notifyDataSetChanged();
        });
        builder.setNegativeButton(R.string.cancel, null);
        builder.create().show();
    }

}
