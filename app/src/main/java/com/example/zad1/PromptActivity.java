package com.example.zad1;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PromptActivity extends AppCompatActivity {
    private boolean correctAnswer;
    private Button showCorrectAnswerButton;
    private Button backButton;
    private TextView answerTextView;
    public static final String KEY_EXTRA_ANSWER_SHOWN="answerShown";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt);

        correctAnswer=getIntent().getBooleanExtra(MainActivity.KEY_EXTRA_ANSWER,true);

        showCorrectAnswerButton = findViewById(R.id.button_show_hint);

        answerTextView = findViewById(R.id.answer_text_view);

        backButton = findViewById(R.id.button_hint_back);

        showCorrectAnswerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int answer=correctAnswer ? R.string.button_true : R.string.button_false;
                answerTextView.setText(answer);
                setAnswerShownResult(true);
            }
        });


        backButton.setOnClickListener((v)->{
            Intent intent = new Intent(PromptActivity.this,MainActivity.class);
            Log.d("MainActivity", "start");
            finish();
        });
    }
    private void setAnswerShownResult(boolean answerWasShown){
        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_EXTRA_ANSWER_SHOWN,answerWasShown);
        setResult(RESULT_OK,resultIntent);

    }
}