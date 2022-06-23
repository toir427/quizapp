package com.tuychiev.quiz;

import android.content.Context;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Quiz {
    static final String LOG_D = "QUIZ: ";
    static final String FILE_NAME = "questions.json";

    private List<Question> questions;

    public Quiz(Context context) {
        String json = loadJSONFromAsset(context);
        Gson gson = new Gson(); // Or use new GsonBuilder().create();
        Quiz quiz = gson.fromJson(json, Quiz.class); // deserializes json into target2

        //Log.d(LOG_D, quiz.toString());
        setQuestions(quiz.getQuestions());
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(FILE_NAME);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
