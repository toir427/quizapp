package com.tuychiev.quiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView tv, tv2, tv3, tv4;
    Button btnRestart;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tv = (TextView) findViewById(R.id.tvres);
        tv2 = (TextView) findViewById(R.id.tvres2);
        tv3 = (TextView) findViewById(R.id.tvres3);
        tv4 = (TextView) findViewById(R.id.tvres4);
        btnRestart = (Button) findViewById(R.id.btnRestart);

        String correct = getIntent().getStringExtra("CORRECT_ANSWERS");
        String wrong = getIntent().getStringExtra("WRONG_ANSWERS");
        String answered = getIntent().getStringExtra("ANSWERED_QUESTIONS");
        String total = getIntent().getStringExtra("TOTAL_QUESTIONS");

        tv.setText("Correct answers: " + (correct == null ? "0" : correct));
        tv2.setText("Wrong answers: " + (wrong == null ? "0" : wrong));
        tv3.setText("Answered questions: " + (answered == null ? "0" : answered));
        tv4.setText("Total questions: " + (total == null ? "0" : total));

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(in);
            }
        });
    }
}