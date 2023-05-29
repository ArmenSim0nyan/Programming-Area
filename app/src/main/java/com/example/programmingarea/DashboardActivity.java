package com.example.programmingarea;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.programmingarea.dataclass.User;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class DashboardActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        SharedPreferences sharedPreferences = getSharedPreferences("my_prefs", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();


        Button checkButton = findViewById(R.id.button);
        TextView fullName = findViewById(R.id.textView);
        TextView programmingLanguage = findViewById(R.id.textView5);
        TextView points = findViewById(R.id.text6);
        TextView win = findViewById(R.id.text2);
        TextView lose = findViewById(R.id.text4);

        DocumentReference user = db.collection("data").document(sharedPreferences.getString("documentId", "invalid"));

        user.get().addOnSuccessListener(documentSnapshot -> {
            User userData = documentSnapshot.toObject(User.class);

            myEdit.putString("fullName", userData.getName());
            myEdit.apply();

            fullName.setText(userData.getName());
            programmingLanguage.setText(String.format("Programming language: %s", userData.getLanguage()));
            points.setText(String.valueOf(userData.getPoints()));
            win.setText(String.valueOf(userData.getWin()));
            lose.setText(String.valueOf(userData.getLose()));
        });


        checkButton.setOnClickListener(v -> {
            Intent activityChangeIntent = new Intent(DashboardActivity.this, CreateGameActivity.class);
            startActivity(activityChangeIntent);
            finish();
        });
    }
}