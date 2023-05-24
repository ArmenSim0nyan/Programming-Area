package com.example.programmingarea;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizModeActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private int progressStatus = 100;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        progressBar = findViewById(R.id.progressBar);

        // Run a background thread to update the progress
        new Thread(() -> {
            while (progressStatus > 0) {
                progressStatus -= 1;
                // Update the progress bar and sleep for a short duration
                handler.post(() -> progressBar.setProgress(progressStatus));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
