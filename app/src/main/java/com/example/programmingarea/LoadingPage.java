package com.example.programmingarea;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.programmingarea.dataclass.QuizGame;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.lang.reflect.Field;


public class LoadingPage extends AppCompatActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_quiz_loading);

        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        SharedPreferences sharedPreferences = getSharedPreferences("my_prefs", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        QuizGame newGame = new QuizGame(sharedPreferences.getString("fullName", "invalid"), "", "python", "", "", "");


        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String, Map<String, Object>> all_games = (HashMap<String, Map<String, Object>>) snapshot.getValue();


                if(all_games == null) {
                    createNewGame(newGame);
                } else {
                    Set<String> keys = all_games.keySet();

                    for(String key: keys) {
                        Map<String, Object> game = all_games.get(key);

                        Map<String, Object> updatedData = new HashMap<>();

                        assert game != null;

                        String gettedName = sharedPreferences.getString("fullName", "invalid");
                        String recievedName = game.get("firstPlayer").toString();

                        if(!Objects.requireNonNull(game.get("secondPlayer")).toString().equals("") && gettedName.equals(recievedName)) {
                            Intent activityChangeIntent = new Intent(LoadingPage.this, QuizModeActivity.class);
                            startActivity(activityChangeIntent);
                            finish();
                        }

//                        System.out.println(Objects.requireNonNull(game.get("secondPlayer")).toString().equals("") && sharedPreferences.getString("fullName", "invalid") != game.get("firstPlayer"));
//                        System.out.println(!Objects.requireNonNull(game.get("secondPlayer")).toString().equals("") && sharedPreferences.getString("fullName", "invalid") == game.get("firstPlayer"));
//                        System.out.println(!Objects.requireNonNull(game.get("secondPlayer")).toString().equals(""));
//                        System.out.println(sharedPreferences.getString("fullName", "invalid"));
//                        System.out.println(game.get("firstPlayer"));
//                        System.out.println(sharedPreferences.getString("fullName", "invalid") == game.get("firstPlayer").toString());


//                        System.out.println(gettedName.equals(recievedName));

                        if(Objects.requireNonNull(game.get("secondPlayer")).toString().equals("") && !gettedName.equals(recievedName)) {
                            updatedData.put("secondPlayer", sharedPreferences.getString("fullName", "invalid"));
                            reference.child(key).updateChildren(updatedData);

                            Intent activityChangeIntent = new Intent(LoadingPage.this, QuizModeActivity.class);
                            startActivity(activityChangeIntent);
                            finish();
                        }

                        if(!Objects.requireNonNull(game.get("secondPlayer")).toString().equals("") && gettedName.equals(recievedName)) {
                            Intent activityChangeIntent = new Intent(LoadingPage.this, QuizModeActivity.class);
                            startActivity(activityChangeIntent);
                            finish();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }


    public void createNewGame(QuizGame quizGame) {

//        String key = reference.child("list").child(list_id).push().getKey();

        String uuid = UUID.randomUUID().toString();
        String listUuid = UUID.randomUUID().toString();

//        temp.get(quizGame.firstPlayer.getClass().getName())

//        String key = reference.child("list").child(listUuid).push().getKey();
        reference.child(uuid).setValue(quizGame);
    }
}
