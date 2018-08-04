package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;


import com.example.android.jokedisplay.JokeDisplay;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;
import java.lang.ref.WeakReference;

public class EndpointsAsyncTask extends AsyncTask<Pair<Context,SimpleIdlingResource>, Void, String> {

    private static MyApi myApiService = null;
    private WeakReference<Context> contextReference;
    private SimpleIdlingResource mIdlingResource;

    @Override
    protected String doInBackground(Pair<Context, SimpleIdlingResource>... params) {

        contextReference = new WeakReference<>(params[0].first);
        mIdlingResource = params[0].second;

        if (mIdlingResource != null) {
            mIdlingResource.setIdleState(false);
        }

        if(myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://my-project-1499036877475.appspot.com/_ah/api/");

            myApiService = builder.build();
        }


        try {
            return myApiService.retrieveJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {

        Context context = contextReference.get();

        if (mIdlingResource != null) {
            mIdlingResource.setIdleState(true);
        }

        //if (!result.isEmpty()) {
            Intent intent = new Intent(context, JokeDisplay.class);
            intent.putExtra(JokeDisplay.JOKE_EXTRA, result);
            context.startActivity(intent);
        //}


    }

}