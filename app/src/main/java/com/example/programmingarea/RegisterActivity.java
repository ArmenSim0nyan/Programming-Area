package com.example.programmingarea;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.programmingarea.dataclass.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;


public class RegisterActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

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

        SharedPreferences sharedPreferences = getSharedPreferences("my_prefs", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        Objects.requireNonNull(getSupportActionBar()).hide();

        programmingLanguageDropDown.setAdapter(spinnerArrayAdapter);

        registerSubmitButton.setOnClickListener(v -> {
            String email = emailRegisterInput.getText().toString();
            String name = nameRegisterInput.getText().toString();
            String password = passwordRegisterInput.getText().toString();
            String programmingLanguage = programmingLanguageDropDown.getSelectedItem().toString();

            if(email.isEmpty() || name.isEmpty() || password.isEmpty()) {
                Toast.makeText(RegisterActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
            } else {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                User user = new User(name, programmingLanguage, 0, 0, 0);
                                db.collection("data")
                                        .add(user)
                                        .addOnSuccessListener(e -> {
                                            myEdit.putString("documentId", e.getId());
                                            myEdit.apply();
                                        })
                                        .addOnFailureListener(e -> {
                                            Log.w(TAG, "Error adding user", e);
                                            // Handle error here, if needed
                                        });
                                Intent activityChangeIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(activityChangeIntent);
                                finish();
                            } else {
                                Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}