package com.example.programmingarea;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.programmingarea.dataclass.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class DashboardActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        SharedPreferences sharedPreferences = getSharedPreferences("my_prefs", MODE_PRIVATE);

        Button checkButton = findViewById(R.id.button);
        TextView fullName = findViewById(R.id.textView);
        TextView programmingLanguage = findViewById(R.id.textView5);
        TextView points = findViewById(R.id.text6);
        TextView win = findViewById(R.id.text2);
        TextView lose = findViewById(R.id.text4);

        DocumentReference user = db.collection("data").document(sharedPreferences.getString("documentId", "invalid"));

        user.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User userData = documentSnapshot.toObject(User.class);

                fullName.setText(userData.getName());
                programmingLanguage.setText(String.format("Programming language: %s", userData.getLanguage()));
                points.setText(String.valueOf(userData.getPoints()));
                win.setText(String.valueOf(userData.getWin()));
                lose.setText(String.valueOf(userData.getLose()));
            }
        });

//        db.collection("user_data").whereEqualTo("");

        checkButton.setOnClickListener(v -> {
            System.out.println(sharedPreferences.getString("documentId", "invalid"));
        });
    }
}