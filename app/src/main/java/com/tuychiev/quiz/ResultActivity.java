package com.tuychiev.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView tv, tv2, tv3, tv4;
    Button btnRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tv = (TextView) findViewById(R.id.tvres);
        tv2 = (TextView) findViewById(R.id.tvres2);
        tv3 = (TextView) findViewById(R.id.tvres3);
        tv4 = (TextView) findViewById(R.id.tvres4);
        btnRestart = (Button) findViewById(R.id.btnRestart);

        tv.setText("Correct answers: " + getIntent().getStringExtra("CORRECT_ANSWERS"));
        tv2.setText("Wrong answers: " + getIntent().getStringExtra("WRONG_ANSWERS"));
        tv3.setText("Answered questions: " + getIntent().getStringExtra("ANSWERED_QUESTIONS"));
        tv4.setText("Total questions: " + getIntent().getStringExtra("TOTAL_QUESTIONS"));

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(in);
            }
        });
    }

}
