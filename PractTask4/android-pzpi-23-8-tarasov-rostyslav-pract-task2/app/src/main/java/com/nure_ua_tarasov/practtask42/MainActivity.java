package com.nure_ua_tarasov.practtask42;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextAge;
    private Button buttonAdd;
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        buttonAdd = findViewById(R.id.buttonAdd);
        recyclerView = findViewById(R.id.recyclerView);

        databaseHelper = new DatabaseHelper(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadUsers();

        buttonAdd.setOnClickListener(v -> addUser());
    }
    private void addUser() {
        String name = editTextName.getText().toString();
        String ageStr = editTextAge.getText().toString();
        int age = Integer.parseInt(ageStr);

        User user = new User(name, age);
        databaseHelper.addUser(user);
        loadUsers();
    }

    private void loadUsers() {
        List<User> userList = databaseHelper.getAllUsers();
        userAdapter = new UserAdapter(userList);
        recyclerView.setAdapter(userAdapter);
    }
}