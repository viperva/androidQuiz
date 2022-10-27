package com.example.zad1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button promptButton;
    private TextView questionTextView;

    private String QUIZ_TAG = "MainActivity";
    public static final String KEY_EXTRA_ANSWER = "com.example.quiz.correctAnswer";
    private static final String KEY_CURRENT_INDEX = "currentIndex";
    private static final int REQUEST_CODE_PROMPT = 0;
    private boolean answerWasShown;

    public Question[] questions = new Question[]{
            new Question(R.string.q_activity, true),
            new Question(R.string.q_resources, true),
            new Question(R.string.q_find_resources, false),
            new Question(R.string.q_listener, true),
            new Question(R.string.q_version, false)
    };

    private int currentIndex = 0;

    private void checkAnswer(boolean userAnswer){
        boolean correctAswer = questions[currentIndex].isTrueAnswer();
        int resultMessageId = 0;

        if(userAnswer == correctAswer){
            resultMessageId = R.string.correct;
        }else{
            resultMessageId = R.string.incorrect;
        }
        Toast.makeText(this, resultMessageId, Toast.LENGTH_SHORT).show();
    }

    private void setNextQuestion() {
        questionTextView.setText(questions[currentIndex].getQuestionId());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) { return; }
        if (requestCode == REQUEST_CODE_PROMPT) {
            if (data == null) { return; }
            answerWasShown = data.getBooleanExtra(PromptActivity.KEY_EXTRA_ANSWER_SHOWN, false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MainActivity", "Wywotana zostala metoda cyklu zycia: onCreate");
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_CURRENT_INDEX);
        }

        trueButton = findViewById(R.id.trueButton);
        falseButton = findViewById(R.id.falseButton);
        nextButton = findViewById(R.id.nextButton);
        promptButton = findViewById(R.id.hint_button);
        questionTextView = findViewById(R.id.questionTextView);

        trueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkAnswer(true);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkAnswer(false);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                currentIndex = (currentIndex + 1) % questions.length;
                setNextQuestion();
            }
        });
        promptButton.setOnClickListener((v)->{
            Intent intent = new Intent(MainActivity.this,PromptActivity.class);
            boolean correctAnswer=questions[currentIndex].isTrueAnswer();
            intent.putExtra(KEY_EXTRA_ANSWER,correctAnswer);
            startActivityForResult(intent, REQUEST_CODE_PROMPT);
        });
        setNextQuestion();
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.d(QUIZ_TAG, "stop");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(QUIZ_TAG, "resume");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(QUIZ_TAG, "pause");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(QUIZ_TAG, "start");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(QUIZ_TAG, "destroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.d(QUIZ_TAG,"Wywolana zostala metoda: onSaveInstanceState");
        outState.putInt(KEY_CURRENT_INDEX, currentIndex);
    }

}