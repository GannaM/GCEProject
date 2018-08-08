package com.udacity.gradle.builditbigger;


import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;


public class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {

    public interface AsyncResponse {
        void fetchResult(String result);
    }

    public AsyncResponse mCallback;

    public EndpointsAsyncTask(AsyncResponse callback) {
        mCallback = callback;
    }

    private static MyApi myApiService = null;

    @Override
    protected String doInBackground(Void... voids) {

        if(myApiService == null) {

            /*
            Deploying the backend live to App Engine
             */
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://my-project-1499036877475.appspot.com/_ah/api/");

            myApiService = builder.build();
        }


        try {
            return myApiService.retrieveJoke().execute().getData();
        } catch (IOException e) {
            Log.i("ERROR RETRIEVING JOKES:", e.getMessage());
            return null;
        }
    }


    @Override
    protected void onPostExecute(String result) {

        if (mCallback != null) {
            mCallback.fetchResult(result);
        }
    }

}