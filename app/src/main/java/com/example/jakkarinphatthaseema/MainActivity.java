package com.example.jakkarinphatthaseema;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnBrowse, btnAddNote;
    private ProgressBar progressBar;

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

        btnBrowse = findViewById(R.id.btnBrowse);
        btnAddNote = findViewById(R.id.btnAddNote);
        progressBar = findViewById(R.id.progressBar);

        btnBrowse.setOnClickListener(v -> {
            loadBrowseActivity();
        });

        btnAddNote.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
            startActivity(intent);
        });
    }

    private void loadBrowseActivity() {
        // Show progress bar and disable buttons
        progressBar.setVisibility(View.VISIBLE);
        btnBrowse.setEnabled(false);
        btnAddNote.setEnabled(false);

        // Multitasking using Thread
        new Thread(() -> {
            try {
                // Mock delay for 2 seconds
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Update UI on UI Thread
            runOnUiThread(() -> {
                progressBar.setVisibility(View.GONE);
                btnBrowse.setEnabled(true);
                btnAddNote.setEnabled(true);
                
                Intent intent = new Intent(MainActivity.this, BrowseNoteActivity.class);
                startActivity(intent);
            });
        }).start();
    }
}
