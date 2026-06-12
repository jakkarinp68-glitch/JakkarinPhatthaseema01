package com.example.jakkarinphatthaseema;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddNoteActivity extends AppCompatActivity {

    EditText etTitle, etContent, etUserName, etUserSex, etUserAge;
    RadioGroup rgNoteType;
    RadioButton rbTextNote, rbChecklistNote;
    Button btnSave;
    TextView tvSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_note);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        etTitle = findViewById(R.id.etTitle);
        etContent = findViewById(R.id.etContent);
        etUserName = findViewById(R.id.etUserName);
        etUserSex = findViewById(R.id.etUserSex);
        etUserAge = findViewById(R.id.etUserAge);
        rgNoteType = findViewById(R.id.rgNoteType);
        rbTextNote = findViewById(R.id.rbTextNote);
        rbChecklistNote = findViewById(R.id.rbChecklistNote);
        btnSave = findViewById(R.id.btnSave);
        tvSummary = findViewById(R.id.tvSummary);

        rgNoteType.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbTextNote) {
                etContent.setHint("Content");
            } else {
                etContent.setHint("Items (comma separated)");
            }
        });

        btnSave.setOnClickListener(v -> {
            saveNote();
        });
    }

    private void saveNote() {
        String title = etTitle.getText().toString();
        String content = etContent.getText().toString();
        String userName = etUserName.getText().toString();
        String userSex = etUserSex.getText().toString();
        String userAge = etUserAge.getText().toString();

        if (title.isEmpty()) {
            Toast.makeText(this, "Please enter a title", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new GeneralUser(userName, userSex, userAge);
        Note note;

        if (rbTextNote.isChecked()) {
            note = new TextNote(title, new Date(), content);
        } else {
            CheckListNote checklist = new CheckListNote();
            checklist.title = title;
            checklist.createdDate = new Date();
            String[] items = content.split(",");
            checklist.item = new ArrayList<>(Arrays.asList(items));
            note = checklist;
        }

        note.setUser(user);

        // Display summary
        if (note != null) {
            tvSummary.setText(note.display());
        }

        // Save to DB
        NoteEntity entity = NoteMapper.toEntity(note);
        if (entity != null) {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
                AppDatabase.getInstance(AddNoteActivity.this).noteDao().insert(entity);
            });
            executor.shutdown();
            Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show();
        }
    }
}
