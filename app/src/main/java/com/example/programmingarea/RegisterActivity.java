package com.example.programmingarea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Objects;


public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        String[] items = getResources().getStringArray(R.array.programming_languages);


        View nameRegisterInput = findViewById(R.id.nameRegisterInput);
        Spinner programmingLanguageDropDown = findViewById(R.id.spinner);
        View emailRegisterInput = findViewById(R.id.emailRegisterInput);
        View passwordRegisterInput = findViewById(R.id.passwordRegisterInput);
        View registerSubmitButton = findViewById(R.id.registerSubmitButton);

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
            Intent activityChangeIntent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(activityChangeIntent);
        });
    }
}