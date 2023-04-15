package com.example.programmingarea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        View nameRegisterInput = findViewById(R.id.nameRegisterInput);
        View regionRegisterInput = findViewById(R.id.regionRegisterInput);
        View emailRegisterInput = findViewById(R.id.emailRegisterInput);
        View passwordRegisterInput = findViewById(R.id.passwordRegisterInput);
        View registerSubmitButton = findViewById(R.id.registerSubmitButton);

        registerSubmitButton.setOnClickListener(v -> {
            Intent activityChangeIntent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(activityChangeIntent);
        });
    }
}