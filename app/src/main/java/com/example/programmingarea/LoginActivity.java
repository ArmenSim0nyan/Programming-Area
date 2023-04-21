package com.example.programmingarea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText emailInput = findViewById(R.id.emailRegisterInput);
        EditText passwordInput = findViewById(R.id.passwordRegisterInput);
        View loginSubmitActivity = findViewById(R.id.loginSubmitButton);

        Objects.requireNonNull(getSupportActionBar()).hide();


        loginSubmitActivity.setOnClickListener(v -> {

            String email = emailInput.getText().toString();
            String password = passwordInput.getText().toString();

            if(email.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
            } else {
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
//                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent activityChangeIntent = new Intent(LoginActivity.this, DashboardActivity.class);
                                startActivity(activityChangeIntent);
                            } else {
                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });

            }

        });
    }
}