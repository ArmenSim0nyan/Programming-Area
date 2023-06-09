package com.example.programmingarea;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WinnerPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_winner);

        Intent intent = getIntent();

        String extraValue = intent.getStringExtra("name");

        TextView winnerName = findViewById(R.id.textView8);

        winnerName.setText(extraValue);
    }
}
