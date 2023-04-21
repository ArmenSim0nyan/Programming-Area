package com.example.programmingarea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;


public class RegisterActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        String[] items = getResources().getStringArray(R.array.programming_languages);

        mAuth = FirebaseAuth.getInstance();


        EditText nameRegisterInput = findViewById(R.id.nameRegisterInput);
        Spinner programmingLanguageDropDown = findViewById(R.id.spinner);
        EditText emailRegisterInput = findViewById(R.id.emailRegisterInput);
        EditText passwordRegisterInput = findViewById(R.id.passwordRegisterInput);
        Button registerSubmitButton = findViewById(R.id.registerSubmitButton);

        ArrayAdapter<String> spinnerArrayAdapter
                = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                items
        ){
            @Override
            public boolean isEnabled(int position){
                return position != 0;
            }
        };


        spinnerArrayAdapter.setDropDownViewResource(
                android.R.layout.simple_dropdown_item_1line
        );

        Objects.requireNonNull(getSupportActionBar()).hide();

        programmingLanguageDropDown.setAdapter(spinnerArrayAdapter);

        registerSubmitButton.setOnClickListener(v -> {
            String email = emailRegisterInput.getText().toString();
            String name = nameRegisterInput.getText().toString();
            String password = passwordRegisterInput.getText().toString();
            String programmingLanguage = programmingLanguageDropDown.getAutofillValue().toString();

            if(email.isEmpty() || name.isEmpty() || password.isEmpty()) {
                Toast.makeText(RegisterActivity.this, "All fileds are required", Toast.LENGTH_SHORT).show();
            } else {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Intent activityChangeIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(activityChangeIntent);
                                finish();
                            } else {
                                Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }

//            Intent activityChangeIntent = new Intent(RegisterActivity.this, LoginActivity.class);
//            startActivity(activityChangeIntent);
        });
    }
}