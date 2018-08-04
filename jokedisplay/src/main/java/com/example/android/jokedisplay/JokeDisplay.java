package com.example.android.jokedisplay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeDisplay extends AppCompatActivity {

    public static final String JOKE_EXTRA = "joke_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);

        TextView textView = findViewById(R.id.joke_textView);

        Intent intent = getIntent();
        if (intent != null) {
            String joke = intent.getStringExtra(JOKE_EXTRA);

            if (!joke.isEmpty()) {
                textView.setText(joke);
            }
        }
    }
}
