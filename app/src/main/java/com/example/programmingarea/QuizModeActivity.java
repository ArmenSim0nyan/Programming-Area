package com.example.programmingarea;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;

import com.example.programmingarea.dataclass.Questions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class QuizModeActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private int progressStatus = 100;
    private Handler handler = new Handler();

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    List<Questions> list = new ArrayList<>();

    int index1 = 0;
    int index2 = 1;
    int index3 = 2;
    int index4 = 3;

    int whichQuestion = 0;

    int getQuestionByStage() {
        switch (whichQuestion) {
            case 0:
                return index1;
            case 1:
                return index2;
            case 2:
                return index3;
            case 3:
                return index4;
            default:
                return 0;
        }
    }

    String getKey() {
        if(whichQuestion == 0) {
            return "firstAnswer";
        } else if(whichQuestion == 1) {
            return "secondAnswer";
        } else if(whichQuestion == 2) {
            return "thirdAnswer";
        }

        return "fourthAnswer";
    }


    String uuid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        progressBar = findViewById(R.id.progressBar);

        Button firstQuestion = findViewById(R.id.button2);
        Button secondQuestion = findViewById(R.id.button3);
        Button thirdQuestion = findViewById(R.id.button4);
        Button fourthQuestion = findViewById(R.id.button5);

        ImageView firstCirce = findViewById(R.id.first);
        ImageView secondCircle = findViewById(R.id.second);
        ImageView thirdCircle = findViewById(R.id.third);
        ImageView fourthCircle = findViewById(R.id.fourth);

        List<ImageView> circleList = Arrays.asList(firstCirce, secondCircle, thirdCircle, fourthCircle);


        TextView question = findViewById(R.id.textView7);

        firstQuestion.setOnClickListener(v -> {
            whichQuestion += 1;
            System.out.println(whichQuestion);
        });


        SharedPreferences sharedPreferences = getSharedPreferences("my_prefs", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

//        firestore.collection("questions").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        Questions question = document.toObject(Questions.class);
//                        System.out.println(question.getRightId());
//                        list.add(question);
//                    }
//                    String quest = list.get(getQuestionByStage()).getTitle();
//
//                    if(!list.get(getQuestionByStage()).getCode().equals("")) {
//                        quest += "\n" + list.get(getQuestionByStage()).getCode();
//                    }
//                    question.setText(quest);
//
//                    firstQuestion.setText(list.get(getQuestionByStage()).getQuestions().get(0));
//                    secondQuestion.setText(list.get(getQuestionByStage()).getQuestions().get(1));
//                    thirdQuestion.setText(list.get(getQuestionByStage()).getQuestions().get(2));
//                    fourthQuestion.setText(list.get(getQuestionByStage()).getQuestions().get(3));
//
//                    firstQuestion.setOnClickListener(v -> {
//
//
//
//                        if(list.get(getQuestionByStage()).getRightId() == 0) {
//                            Drawable drawable = getResources().getDrawable(R.drawable.circle);
//                            drawable = DrawableCompat.wrap(drawable);
//                            DrawableCompat.setTint(drawable, getResources().getColor(R.color.deep_green));
//
//                            String gettedName = sharedPreferences.getString("fullName", "invalid");
//
//                            circleList.get(whichQuestion).setImageDrawable(drawable);
//
//                            updateQuestion(uuid, "firstAnswer", gettedName);
//                        } else {
//                            Drawable drawable = getResources().getDrawable(R.drawable.circle);
//                            drawable = DrawableCompat.wrap(drawable);
//                            DrawableCompat.setTint(drawable, getResources().getColor(R.color.red));
//
//                            circleList.get(whichQuestion).setImageDrawable(drawable);
//                        }
//                        progressStatus = 100;
//                        startProgessBar();
//                        if(whichQuestion < 3)
//                            whichQuestion += 1;
//
//
//
//                        String qe = list.get(getQuestionByStage()).getTitle();
//
//                        if(!list.get(getQuestionByStage()).getCode().equals("")) {
//                            qe += "\n" + list.get(getQuestionByStage()).getCode();
//                        }
//                        question.setText(qe);
//
//                        firstQuestion.setText(list.get(getQuestionByStage()).getQuestions().get(0));
//                        secondQuestion.setText(list.get(getQuestionByStage()).getQuestions().get(1));
//                        thirdQuestion.setText(list.get(getQuestionByStage()).getQuestions().get(2));
//                        fourthQuestion.setText(list.get(getQuestionByStage()).getQuestions().get(3));
//                    });
//
//                    secondQuestion.setOnClickListener(v -> {
//
//                        String gettedName = sharedPreferences.getString("fullName", "invalid");
//
//                        if(list.get(getQuestionByStage()).getRightId() == 1) {
//                            Drawable drawable = getResources().getDrawable(R.drawable.circle);
//                            drawable = DrawableCompat.wrap(drawable);
//                            DrawableCompat.setTint(drawable, getColor(R.color.deep_green));
//
//                            circleList.get(whichQuestion).setImageDrawable(drawable);
//
//                            updateQuestion(uuid, "secondAnswer", gettedName);
//                        } else {
//                            Drawable drawable = getResources().getDrawable(R.drawable.circle);
//                            drawable = DrawableCompat.wrap(drawable);
//                            DrawableCompat.setTint(drawable, getColor(R.color.red));
//
//                            circleList.get(whichQuestion).setImageDrawable(drawable);
//                        }
//                        progressStatus = 100;
//                        startProgessBar();
//                        if(whichQuestion < 3)
//                            whichQuestion += 1;
//
//                        String qe = list.get(getQuestionByStage()).getTitle();
//
//                        if(!list.get(getQuestionByStage()).getCode().equals("")) {
//                            qe += "\n" + list.get(getQuestionByStage()).getCode();
//                        }
//                        question.setText(qe);
//
//
//                        firstQuestion.setText(list.get(getQuestionByStage()).getQuestions().get(0));
//                        secondQuestion.setText(list.get(getQuestionByStage()).getQuestions().get(1));
//                        thirdQuestion.setText(list.get(getQuestionByStage()).getQuestions().get(2));
//                        fourthQuestion.setText(list.get(getQuestionByStage()).getQuestions().get(3));
//
//                        System.out.println(whichQuestion);
//                    });
//
//                    thirdQuestion.setOnClickListener(v -> {
//                        if(list.get(getQuestionByStage()).getRightId() == 2) {
//                            Drawable drawable = getResources().getDrawable(R.drawable.circle);
//                            drawable = DrawableCompat.wrap(drawable);
//                            DrawableCompat.setTint(drawable, getColor(R.color.deep_green));
//
//                            circleList.get(whichQuestion).setImageDrawable(drawable);
//
//                            String gettedName = sharedPreferences.getString("fullName", "invalid");
//
//                            updateQuestion(uuid, "thirdAnswer", gettedName);
//                        } else {
//                            Drawable drawable = getResources().getDrawable(R.drawable.circle);
//                            drawable = DrawableCompat.wrap(drawable);
//                            DrawableCompat.setTint(drawable, getColor(R.color.red));
//
//                            circleList.get(whichQuestion).setImageDrawable(drawable);
//                        }
//
//                        progressStatus = 100;
//                        startProgessBar();
//                        if(whichQuestion < 3)
//                            whichQuestion += 1;
//
//                        String qe = list.get(getQuestionByStage()).getTitle();
//
//                        if(!list.get(getQuestionByStage()).getCode().equals("")) {
//                            qe += "\n" + list.get(getQuestionByStage()).getCode();
//                        }
//                        question.setText(qe);
//
//                        firstQuestion.setText(list.get(getQuestionByStage()).getQuestions().get(0));
//                        secondQuestion.setText(list.get(getQuestionByStage()).getQuestions().get(1));
//                        thirdQuestion.setText(list.get(getQuestionByStage()).getQuestions().get(2));
//                        fourthQuestion.setText(list.get(getQuestionByStage()).getQuestions().get(3));
//
//                        System.out.println(whichQuestion);
//                    });
//
//                    fourthQuestion.setOnClickListener(v -> {
//                        if(list.get(getQuestionByStage()).getRightId() == 3) {
//                            Drawable drawable = getResources().getDrawable(R.drawable.circle);
//                            drawable = DrawableCompat.wrap(drawable);
//                            DrawableCompat.setTint(drawable, getColor(R.color.deep_green));
//
//                            circleList.get(whichQuestion).setImageDrawable(drawable);
//
//                            String gettedName = sharedPreferences.getString("fullName", "invalid");
//
//                            updateQuestion(uuid, "fourthAnswer", gettedName);
//                        } else {
//                            Drawable drawable = getResources().getDrawable(R.drawable.circle);
//                            drawable = DrawableCompat.wrap(drawable);
//                            DrawableCompat.setTint(drawable, getColor(R.color.red));
//
//                            circleList.get(whichQuestion).setImageDrawable(drawable);
//                        }
//                    });
//
//                    Log.d(TAG, list.toString());
//                } else {
//                    Log.d(TAG, "Error getting documents: ", task.getException());
//                }
//            }
//        });

//        final Map<String, Object>[] ourgame = new Map[]{new HashMap<>()};

        firstQuestion.setOnClickListener(v -> {

            if(list.get(getQuestionByStage()).getRightId() == 0) {
                Drawable drawable = getResources().getDrawable(R.drawable.circle);
                drawable = DrawableCompat.wrap(drawable);
                DrawableCompat.setTint(drawable, getResources().getColor(R.color.deep_green));

                String gettedName = sharedPreferences.getString("fullName", "invalid");

                circleList.get(whichQuestion).setImageDrawable(drawable);

                updateQuestion(uuid, getKey(), gettedName);
            } else {
                Drawable drawable = getResources().getDrawable(R.drawable.circle);
                drawable = DrawableCompat.wrap(drawable);
                DrawableCompat.setTint(drawable, getResources().getColor(R.color.red));

                circleList.get(whichQuestion).setImageDrawable(drawable);
            }
            progressStatus = 100;
            startProgessBar();
            if(whichQuestion < 3)
                whichQuestion += 1;



            String qe = list.get(getQuestionByStage()).getTitle();

            if(!list.get(getQuestionByStage()).getCode().equals("")) {
                qe += "\n" + list.get(getQuestionByStage()).getCode();
            }
            question.setText(qe);

            firstQuestion.setText(list.get(getQuestionByStage()).getQuestions().get(0));
            secondQuestion.setText(list.get(getQuestionByStage()).getQuestions().get(1));
            thirdQuestion.setText(list.get(getQuestionByStage()).getQuestions().get(2));
            fourthQuestion.setText(list.get(getQuestionByStage()).getQuestions().get(3));
        });

        secondQuestion.setOnClickListener(v -> {

            String gettedName = sharedPreferences.getString("fullName", "invalid");

            if(list.get(getQuestionByStage()).getRightId() == 1) {
                Drawable drawable = getResources().getDrawable(R.drawable.circle);
                drawable = DrawableCompat.wrap(drawable);
                DrawableCompat.setTint(drawable, getColor(R.color.deep_green));

                circleList.get(whichQuestion).setImageDrawable(drawable);

                updateQuestion(uuid, getKey(), gettedName);
            } else {
                Drawable drawable = getResources().getDrawable(R.drawable.circle);
                drawable = DrawableCompat.wrap(drawable);
                DrawableCompat.setTint(drawable, getColor(R.color.red));

                circleList.get(whichQuestion).setImageDrawable(drawable);
            }
            progressStatus = 100;
            startProgessBar();
            if(whichQuestion < 3)
                whichQuestion += 1;

            String qe = list.get(getQuestionByStage()).getTitle();

            if(!list.get(getQuestionByStage()).getCode().equals("")) {
                qe += "\n" + list.get(getQuestionByStage()).getCode();
            }
            question.setText(qe);


            firstQuestion.setText(list.get(getQuestionByStage()).getQuestions().get(0));
            secondQuestion.setText(list.get(getQuestionByStage()).getQuestions().get(1));
            thirdQuestion.setText(list.get(getQuestionByStage()).getQuestions().get(2));
            fourthQuestion.setText(list.get(getQuestionByStage()).getQuestions().get(3));

            System.out.println(whichQuestion);
        });

        thirdQuestion.setOnClickListener(v -> {
            if(list.get(getQuestionByStage()).getRightId() == 2) {
                Drawable drawable = getResources().getDrawable(R.drawable.circle);
                drawable = DrawableCompat.wrap(drawable);
                DrawableCompat.setTint(drawable, getColor(R.color.deep_green));

                circleList.get(whichQuestion).setImageDrawable(drawable);

                String gettedName = sharedPreferences.getString("fullName", "invalid");

                updateQuestion(uuid, getKey(), gettedName);
            } else {
                Drawable drawable = getResources().getDrawable(R.drawable.circle);
                drawable = DrawableCompat.wrap(drawable);
                DrawableCompat.setTint(drawable, getColor(R.color.red));

                circleList.get(whichQuestion).setImageDrawable(drawable);
            }

            progressStatus = 100;
            startProgessBar();
            if(whichQuestion < 3)
                whichQuestion += 1;

            String qe = list.get(getQuestionByStage()).getTitle();

            if(!list.get(getQuestionByStage()).getCode().equals("")) {
                qe += "\n" + list.get(getQuestionByStage()).getCode();
            }
            question.setText(qe);

            firstQuestion.setText(list.get(getQuestionByStage()).getQuestions().get(0));
            secondQuestion.setText(list.get(getQuestionByStage()).getQuestions().get(1));
            thirdQuestion.setText(list.get(getQuestionByStage()).getQuestions().get(2));
            fourthQuestion.setText(list.get(getQuestionByStage()).getQuestions().get(3));

            System.out.println(whichQuestion);
        });

        fourthQuestion.setOnClickListener(v -> {
            if(list.get(getQuestionByStage()).getRightId() == 3) {
                Drawable drawable = getResources().getDrawable(R.drawable.circle);
                drawable = DrawableCompat.wrap(drawable);
                DrawableCompat.setTint(drawable, getColor(R.color.deep_green));

                circleList.get(whichQuestion).setImageDrawable(drawable);

                String gettedName = sharedPreferences.getString("fullName", "invalid");

                updateQuestion(uuid, getKey(), gettedName);
            } else {
                Drawable drawable = getResources().getDrawable(R.drawable.circle);
                drawable = DrawableCompat.wrap(drawable);
                DrawableCompat.setTint(drawable, getColor(R.color.red));

                circleList.get(whichQuestion).setImageDrawable(drawable);
            }
        });


        firestore.collection("questions").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    System.out.println("Change My Mind");
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Questions question = document.toObject(Questions.class);
                        System.out.println(question.getRightId());
                        list.add(question);
                    }

                    String quest = list.get(getQuestionByStage()).getTitle();

                    if (!list.get(getQuestionByStage()).getCode().equals("")) {
                        quest += "\n" + list.get(getQuestionByStage()).getCode();
                    }
                    question.setText(quest);

                    firstQuestion.setText(list.get(getQuestionByStage()).getQuestions().get(0));
                    secondQuestion.setText(list.get(getQuestionByStage()).getQuestions().get(1));
                    thirdQuestion.setText(list.get(getQuestionByStage()).getQuestions().get(2));
                    fourthQuestion.setText(list.get(getQuestionByStage()).getQuestions().get(3));
                    Log.d(TAG, list.toString());
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });


        reference.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String, Map<String, Object>> all_games = (HashMap<String, Map<String, Object>>) snapshot.getValue();

                Set<String> keys = all_games.keySet();

                System.out.println("Hello Change MY mIND");




                for(String key: keys) {
                    Map<String, Object> game = all_games.get(key);

                    uuid = key;

                    String gettedName = sharedPreferences.getString("fullName", "invalid");

                    if(game.get("firstPlayer").toString().equals(gettedName) || game.get("secondPlayer").toString().equals(gettedName)) {
                        index1 = Integer.parseInt(game.get("firstQuestId").toString());
                        index2 = Integer.parseInt(game.get("secondQuestId").toString());
                        index3 = Integer.parseInt(game.get("thirdQuestId").toString());
                        index4 = Integer.parseInt(game.get("fourthQuestId").toString());
                    }


                    if(game.get("firstAnswer").equals(game.get("firstPlayer").toString()) && game.get("secondAnswer").equals(game.get("firstPlayer").toString()) && game.get("thirdAnswer").equals(game.get("firstPlayer").toString()) && game.get("fourthAnswer").equals(game.get("firstPlayer").toString())) {
                        Intent winnerPage = new Intent(QuizModeActivity.this, WinnerPage.class);

                        winnerPage.putExtra("name", game.get("firstPlayer").toString());
                        startActivity(winnerPage);
                        finish();
                    }
                    else if(game.get("firstAnswer").equals(game.get("firstPlayer").toString()) && game.get("secondAnswer").equals(game.get("firstPlayer").toString()) && game.get("thirdAnswer").equals(game.get("firstPlayer").toString())) {
                        Intent winnerPage = new Intent(QuizModeActivity.this, WinnerPage.class);

                        winnerPage.putExtra("name", game.get("firstPlayer").toString());
                        startActivity(winnerPage);
                        finish();
                    } else if(game.get("firstAnswer").equals(game.get("firstPlayer").toString()) && game.get("secondAnswer").equals(game.get("firstPlayer").toString())) {
                        Intent winnerPage = new Intent(QuizModeActivity.this, WinnerPage.class);

                        winnerPage.putExtra("name", game.get("firstPlayer").toString());
                        startActivity(winnerPage);
                        finish();
                    } else if(game.get("firstAnswer").equals(game.get("secondPlayer").toString()) && game.get("secondAnswer").equals(game.get("secondPlayer").toString()) && game.get("thirdAnswer").equals(game.get("secondPlayer").toString())) {
                        Intent winnerPage = new Intent(QuizModeActivity.this, WinnerPage.class);

                        winnerPage.putExtra("name", game.get("secondPlayer").toString());
                        startActivity(winnerPage);
                        finish();
                    } else if(game.get("firstAnswer").equals(game.get("secondPlayer").toString()) && game.get("secondAnswer").equals(game.get("secondPlayer").toString())) {
                        Intent winnerPage = new Intent(QuizModeActivity.this, WinnerPage.class);

                        winnerPage.putExtra("name", game.get("secondPlayer").toString());
                        startActivity(winnerPage);
                        finish();
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        System.out.println(ourgame[0]);
//
//
//        int index = Integer.parseInt(ourgame[0].get("firstQuestId").toString());



        startProgessBar();

        // Run a background thread to update the progres
    }

    public void startProgessBar()  {
        new Thread(() -> {
            while (progressStatus > 0) {
                progressStatus -= 1;
                // Update the progress bar and sleep for a short duration
                handler.post(() -> progressBar.setProgress(progressStatus));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void updateQuestion(String uuid, String key, String value) {
        Map<String, Object> updateValue = new HashMap<>();

        updateValue.put(key, value);

        reference.child(uuid).updateChildren(updateValue);
    }
}
