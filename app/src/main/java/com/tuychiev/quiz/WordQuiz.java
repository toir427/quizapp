package com.tuychiev.quiz;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class WordQuiz {
    static final String LOG_D = "WORD_QUIZ: ";
    static final String FILE_NAME = "dj.json";

    private List<Word> words;

    public WordQuiz(Context context) {
        String json = loadJSONFromAsset(context);
        Log.d(LOG_D, json);
        Gson gson = new Gson(); // Or use new GsonBuilder().create();
        WordQuiz quiz = gson.fromJson(json, WordQuiz.class); // deserializes json into target2

        Log.d(LOG_D, quiz.toString());
        setQuestions(quiz.getWords());
    }

    public List<Word> getWords() {
        return words;
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

    public void setQuestions(List<Word> words) {
        this.words = words;
    }
}
