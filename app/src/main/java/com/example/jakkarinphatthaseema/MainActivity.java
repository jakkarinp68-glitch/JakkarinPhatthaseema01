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

    Button addBtn, browseBtn;
    ProgressBar loadData;

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

        addBtn = findViewById(R.id.button2);
        browseBtn = findViewById(R.id.button3);
        loadData = findViewById(R.id.progressBar);

        addBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
            startActivity(intent);
        });

        browseBtn.setOnClickListener(v -> {
            loadData.setVisibility(View.VISIBLE);

            new Thread(() -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                runOnUiThread(() -> {
                    loadData.setVisibility(View.GONE);
                    Intent intent = new Intent(MainActivity.this, BrowseNoteActivity.class);
                    startActivity(intent);
                    finish();
                });

            }).start();
        });
    }
}