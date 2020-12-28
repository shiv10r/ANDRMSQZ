package com.shr.maingoquizagain;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    TextView txtHighScore;
    TextView txtTotalQuizQues, txtCorrectQues,txtWrongQues;

    Button btStartQuiz;
    Button btMainMenu;

    private int highScore;
    public static final String SHARED_PREFERENCE = "shread_preference";
    public static final String SHARED_PREFERENCE_HIGH_SCORE = "shread_preference_high_score";

    private long  backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        btMainMenu = findViewById(R.id.result_bt_mainmenu);
        btStartQuiz = findViewById(R.id.result_bt_playAgain);
        txtHighScore = findViewById(R.id.result_text_High_Score);
        txtTotalQuizQues = findViewById(R.id.result_total_Ques);
        txtCorrectQues = findViewById(R.id.result_Correct_Ques);
        txtWrongQues = findViewById(R.id.result_Wrong_Ques);

        btMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, PlayActivity.class);
                startActivity(intent);
            }
        });

        btStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();

        int score = intent.getIntExtra("UserScore", 0);
        int totalQuestion = intent.getIntExtra("CorrectQues", 0);
        int correctQues = intent.getIntExtra("CorrectQues", 0);
        int wrongQues = intent.getIntExtra("WrongQues", 0);


        txtTotalQuizQues.setText("toatlQue: " + String.valueOf(totalQuestion));
        txtCorrectQues.setText("Correct" + String.valueOf(correctQues));
        txtWrongQues.setText("Wrong" + String.valueOf(wrongQues));

        if (score > highScore) {
            updateHighScore(score);
        }
    }

    private void updateHighScore(int newHighScore){
        highScore = newHighScore;
        txtHighScore.setText("HighScore" + String.valueOf(highScore));

        SharedPreferences sharedPreferences = getsharedPreference(SHARED_PREFERENCE,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SHARED_PREFERENCE_HIGH_SCORE,highScore);
        editor.apply();
    }
    public void loadHighScore(){
        SharedPreferences sharedPreferences =  getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        highScore = sharedPreferences .getInt(SHARED_PREFERENCE_HIGH_SCORE);
        txtHighScore.setText("High Score" + String.valueOf(highScore));
    }

    @Override
    public void onBackpressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            Intent intent = new Intent(ResultActivity.this, PlayActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Press again to Exit", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}
