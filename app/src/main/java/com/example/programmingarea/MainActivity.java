package com.example.programmingarea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View loginButton = findViewById(R.id.loginButton);
        View registerButton = findViewById(R.id.registerButton);


        Objects.requireNonNull(getSupportActionBar()).hide();

        loginButton.setOnClickListener(v -> {
            Intent activityChangeIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(activityChangeIntent);
        });

        registerButton.setOnClickListener(v -> {
            Intent activityChangeIntent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(activityChangeIntent);
        });
    }

}