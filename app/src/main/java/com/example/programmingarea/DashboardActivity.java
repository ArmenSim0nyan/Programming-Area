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

        Button checkButton = findViewById(R.id.Rowanchik);
        TextView fullName = findViewById(R.id.textView);

        DocumentReference user = db.collection("user_data").document(sharedPreferences.getString("documentId", "invalid"));

        user.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User userData = documentSnapshot.toObject(User.class);

                fullName.setText(userData.getName());
            }
        });

//        db.collection("user_data").whereEqualTo("");

        checkButton.setOnClickListener(v -> {
            System.out.println(sharedPreferences.getString("documentId", "invalid"));
        });
    }
}