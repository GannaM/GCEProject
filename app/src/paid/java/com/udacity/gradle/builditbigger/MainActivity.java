package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;


import com.example.android.jokedisplay.JokeDisplay;


public class MainActivity extends AppCompatActivity implements EndpointsAsyncTask.AsyncResponse {

    private ProgressBar mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpinner = findViewById(R.id.pb_loading_indicator);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {

        mSpinner.setVisibility(View.VISIBLE);

        EndpointsAsyncTask asyncTask = new EndpointsAsyncTask(this);
        asyncTask.execute();

    }


    @Override
    public void fetchResult(String result) {

        mSpinner.setVisibility(View.INVISIBLE);

        Intent intent = new Intent(this, JokeDisplay.class);
        intent.putExtra(JokeDisplay.JOKE_EXTRA, result);
        startActivity(intent);

    }
}
