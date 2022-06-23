package com.tuychiev.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startBtn = (Button) findViewById(R.id.button);
        Button aboutBtn = (Button) findViewById(R.id.button2);
        final EditText nameText = (EditText) findViewById(R.id.editName);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                Intent intent = new Intent(getApplicationContext(), QuestionsActivity.class);
                intent.putExtra("myname", name);
                startActivity(intent);
            }
        });

        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DeveloperActivity.class);
                startActivity(intent);
            }
        });
    }
}
