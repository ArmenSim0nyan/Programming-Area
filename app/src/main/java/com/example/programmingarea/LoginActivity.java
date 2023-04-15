package com.example.programmingarea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        View emailInput = findViewById(R.id.emailRegisterInput);
        View passwordInput = findViewById(R.id.passwordRegisterInput);
        View loginSubmitActivity = findViewById(R.id.loginSubmitButton);


        loginSubmitActivity.setOnClickListener(v -> {
            Intent activityChangeIntent = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(activityChangeIntent);
        });
    }
}