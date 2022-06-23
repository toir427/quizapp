package com.tuychiev.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class QuestionsActivity extends AppCompatActivity {
    TextView tv;
    Button submitBtn, quitBtn;
    RadioGroup choice;
    RadioButton rb1, rb2, rb3, rb4;
    Question cQuestion;
    List<Question> questions;

    int flag = 0;
    public static int cAnswers = 0, wAnswers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        final TextView score = (TextView) findViewById(R.id.textView4);
        TextView greetingUser = (TextView) findViewById(R.id.DispName);

        submitBtn = (Button) findViewById(R.id.button3);
        quitBtn = (Button) findViewById(R.id.buttonquit);
        tv = (TextView) findViewById(R.id.tvque);

        choice = (RadioGroup) findViewById(R.id.answersgrp);
        rb1 = (RadioButton) findViewById(R.id.radioButton);
        rb2 = (RadioButton) findViewById(R.id.radioButton2);
        rb3 = (RadioButton) findViewById(R.id.radioButton3);
        rb4 = (RadioButton) findViewById(R.id.radioButton4);


        Intent intent = getIntent();
        String name = intent.getStringExtra("myname");

        if (name.trim().equals("")) {
            greetingUser.setText("Hello User");
        } else {
            greetingUser.setText("Hello " + name);
        }

        Quiz quiz = new Quiz(getApplicationContext());
        questions = quiz.getQuestions();
        Collections.shuffle(questions);
        setCurrentQuestion(0);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (choice.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Please select one choice", Toast.LENGTH_SHORT).show();
                    return;
                }
                RadioButton radioBtnId = (RadioButton) findViewById(choice.getCheckedRadioButtonId());
                String ansText = radioBtnId.getText().toString();

                if (ansText.equals(cQuestion.getAnswer())) {
                    cAnswers++;
                    Toast.makeText(getApplicationContext(), "Correct Answers", Toast.LENGTH_SHORT).show();
                } else {
                    wAnswers++;
                    Toast.makeText(getApplicationContext(), "Wrong Answers", Toast.LENGTH_SHORT).show();
                }

                flag++;
                if (score != null)
                    score.setText(String.valueOf(cAnswers));

                if (flag < questions.size()) {
                    setCurrentQuestion(flag);
                } else {
                    Intent resultIntent = new Intent(getApplicationContext(), ResultActivity.class);
                    resultIntent.putExtra("CORRECT_ANSWERS", String.valueOf(cAnswers));
                    resultIntent.putExtra("WRONG_ANSWERS", String.valueOf(wAnswers));
                    resultIntent.putExtra("ANSWERED_QUESTIONS", String.valueOf(flag));
                    resultIntent.putExtra("TOTAL_QUESTIONS", String.valueOf(questions.size()));

                    startActivity(resultIntent);
                }
                choice.clearCheck();
            }
        });

        quitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setCurrentQuestion(int num) {
        cQuestion = questions.get(num);
        List<String> options = cQuestion.getOptions();
        Collections.shuffle(options);

        tv.setText(cQuestion.getQuestion());
        rb1.setText(options.get(0));
        rb2.setText(options.get(1));
        rb3.setText(options.get(2));
        rb4.setText(options.get(3));
    }

}