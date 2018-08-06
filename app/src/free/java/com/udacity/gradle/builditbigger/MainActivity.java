package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.android.jokedisplay.JokeDisplay;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


public class MainActivity extends AppCompatActivity implements EndpointsAsyncTask.AsyncResponse {

    private InterstitialAd mInterstitialAd;

//    private String mJoke;
//    private Intent mJokeDisplayIntent;
//
//    @Nullable private SimpleIdlingResource mIdlingResource;
//
//    @VisibleForTesting
//    @NonNull
//    public IdlingResource getIdlingResource() {
//        if (mIdlingResource == null) {
//            mIdlingResource = new SimpleIdlingResource();
//        }
//        return mIdlingResource;
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getIdlingResource();


        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {

//            @Override
//            public void onAdOpened() {
//                super.onAdOpened();
////                if (mIdlingResource != null) {
////                    mIdlingResource.setIdleState(false);
////                }
//            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                mInterstitialAd.loadAd(new AdRequest.Builder().build());

//                if (mJokeDisplayIntent != null) {
//                    startActivity(mJokeDisplayIntent);
//                }

            }
        });

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

//        EndpointsAsyncTask asyncTask = new EndpointsAsyncTask(this);
//        asyncTask.execute(new Pair<Context, SimpleIdlingResource>(this, mIdlingResource));

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();

        }
        EndpointsAsyncTask asyncTask = new EndpointsAsyncTask(this);
        asyncTask.execute();

        //new EndpointsAsyncTask().execute(new Pair<Context, SimpleIdlingResource>(this, mIdlingResource));
    }


    @Override
    public void fetchResult(String result) {
//        if (mIdlingResource != null) {
//            mIdlingResource.setIdleState(true);
//        }

        Intent intent = new Intent(this, JokeDisplay.class);
        intent.putExtra(JokeDisplay.JOKE_EXTRA, result);
        startActivity(intent);

    }
}
