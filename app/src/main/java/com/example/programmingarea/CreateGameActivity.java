package com.example.programmingarea;

import static android.service.controls.ControlsProviderService.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.logging.Logger;

public class CreateGameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_submit);


        Button playButton = findViewById(R.id.button);
        Spinner programmingLanguageDropDown = findViewById(R.id.type);
        Spinner gameType = findViewById(R.id.spinner);

        playButton.setOnClickListener(v -> {
            String selectedGameType = gameType.getSelectedItem().toString();
            String programmingLanguage = programmingLanguageDropDown.getSelectedItem().toString();

            if(selectedGameType.equals("Quiz Mode") && !programmingLanguage.equals("Programming Language")) {
                Intent activityChangeIntent = new Intent(CreateGameActivity.this, LoadingPage.class);
                startActivity(activityChangeIntent);
                finish();
            } else if(selectedGameType.equals("Code Fight Mode") && !programmingLanguage.equals("Programming Language")) {
                Intent activityChangeIntent = new Intent(CreateGameActivity.this, CodeFightActivity.class);
                activityChangeIntent.putExtra("language", programmingLanguage);
                startActivity(activityChangeIntent);
                finish();
            } else {
                Toast.makeText(CreateGameActivity.this, "Fill required fields.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
