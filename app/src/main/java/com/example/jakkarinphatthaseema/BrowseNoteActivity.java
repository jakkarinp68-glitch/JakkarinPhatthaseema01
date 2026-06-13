package com.example.jakkarinphatthaseema;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class BrowseNoteActivity extends AppCompatActivity {

    private TextView showNoteFromDB;
    private EditText etSearchUser;
    private Button btnSearch;
    private ProgressBar pbSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_browse_note);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        showNoteFromDB = findViewById(R.id.textView2);
        etSearchUser = findViewById(R.id.etSearchUser);
        btnSearch = findViewById(R.id.btnSearch);
        pbSearch = findViewById(R.id.pbSearch);

        btnSearch.setOnClickListener(v -> {
            performSearch();
        });

        // Initial load (mock multitasking)
        loadAllNotes();
    }

    private void loadAllNotes() {
        Executors.newSingleThreadExecutor().execute(() -> {
            List<NoteEntity> entities = AppDatabase.getInstance(this).noteDao().getAll();
            List<Note> notes = new ArrayList<>();
            for (NoteEntity e : entities) {
                notes.add(NoteMapper.fromEntity(e));
            }

            runOnUiThread(() -> {
                if (notes.isEmpty()) {
                    showNoteFromDB.setText("No notes found in database.");
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (Note n : notes) {
                        sb.append(n.display()).append("\n------------------\n");
                    }
                    showNoteFromDB.setText(sb.toString());
                }
            });
        });
    }

    private void performSearch() {
        String query = etSearchUser.getText().toString();
        
        pbSearch.setVisibility(View.VISIBLE);
        btnSearch.setEnabled(false);
        showNoteFromDB.setText("Searching...");

        new Thread(() -> {
            try {
                // Mock delay for 2 seconds
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            runOnUiThread(() -> {
                pbSearch.setVisibility(View.GONE);
                btnSearch.setEnabled(true);
                showNoteFromDB.setText("ไม่พบข้อมูล");
            });
        }).start();
    }
}
