package com.example.jakkarinphatthaseema;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

        Note notel = new Note();
        notel.title = "Lab3";
        notel.content = "Do and sent Lab3 before due date";
        notel.createdDate = "22/94/2026";
        notel.getSummary();

        User ai = new User();
        ai.name = "Mail";
        ai.sex = "Male";
        ai.age = "21";
        ai.Print();
        ai.Read();

    }

}