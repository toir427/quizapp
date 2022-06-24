package com.tuychiev.quiz;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class WordsActivity extends AppCompatActivity {
    TextView tv, score, greetingUser;
    Button submitBtn, resultBtn;
    RadioGroup choice;
    RadioButton rb1, rb2, rb3, rb4;
    Word cQuestion;
    List<Word> questions;
    String userName;

    int cAnswers = 0, wAnswers = 0, flag = 0;

    @SuppressLint("SetTextI18n")
    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        Intent intent = getIntent();
        userName = intent.getStringExtra("username");
        score = (TextView) findViewById(R.id.textView4);
        greetingUser = (TextView) findViewById(R.id.DispName);

        submitBtn = (Button) findViewById(R.id.button3);
        resultBtn = (Button) findViewById(R.id.resultBtn);
        tv = (TextView) findViewById(R.id.tvque);

        choice = (RadioGroup) findViewById(R.id.answersgrp);
        rb1 = (RadioButton) findViewById(R.id.radioButton);
        rb2 = (RadioButton) findViewById(R.id.radioButton2);
        rb3 = (RadioButton) findViewById(R.id.radioButton3);
        rb4 = (RadioButton) findViewById(R.id.radioButton4);

        WordQuiz quiz = new WordQuiz(getApplicationContext());
        //questions = quiz.getWords();
        List<Word> words = quiz.getWords();
        Collections.shuffle(words);
        questions = words.subList(words.size() - 50, words.size());
        ///questions = quiz.getWords().stream().limit(100).collect(Collectors.toList());
        score.setText("0 - 0" + " of " + questions.size());
        setUsername("- 0 / " + questions.size());
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

                if (ansText.equals(cQuestion.getDef())) {
                    cAnswers++;
                    Toast.makeText(getApplicationContext(), "Correct Answers", Toast.LENGTH_SHORT).show();
                } else {
                    wAnswers++;
                    Toast.makeText(getApplicationContext(), "Wrong Answers", Toast.LENGTH_SHORT).show();
                }

                flag++;
                if (score != null)
                    score.setText(String.valueOf(cAnswers) + " - " + flag + " of " + questions.size());

                //setUsername(" - " + flag + "/" + questions.size());
                if (flag < questions.size()) {
                    setCurrentQuestion(flag);
                } else {
                    startResultActivity();
                }
                choice.clearCheck();
            }
        });

        resultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startResultActivity();
            }
        });
    }

    public void startResultActivity() {
        Intent resultIntent = new Intent(getApplicationContext(), ResultActivity.class);
        resultIntent.putExtra("CORRECT_ANSWERS", String.valueOf(cAnswers));
        resultIntent.putExtra("WRONG_ANSWERS", String.valueOf(wAnswers));
        resultIntent.putExtra("ANSWERED_QUESTIONS", String.valueOf(flag));
        resultIntent.putExtra("TOTAL_QUESTIONS", String.valueOf(questions.size()));
        startActivity(resultIntent);
    }

    @SuppressLint("SetTextI18n")
    private void setUsername(String plus) {
        plus = "";
        if (!userName.trim().equals("")) {
            greetingUser.setText("Hello, " + userName + plus);
        } else {
            greetingUser.setText("Hello, User" + plus);
        }
    }

    private void setCurrentQuestion(int num) {
        cQuestion = questions.get(num);
        List<Word> options = new ArrayList<Word>();
        options.add(cQuestion);

        for (int i = 0; i < 3; i++) {
            Random rand = new Random();
            int r = rand.nextInt(questions.size());
            if (r == num)
                r = rand.nextInt(questions.size());

            Word word = questions.get(r);
            options.add(word);
        }

        Collections.shuffle(options);

        tv.setText(cQuestion.getWord());
        rb1.setText(options.get(0).getDef());
        rb2.setText(options.get(1).getDef());
        rb3.setText(options.get(2).getDef());
        rb4.setText(options.get(3).getDef());
    }

}