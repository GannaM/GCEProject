package com.udacity.gradle.builditbigger;


import android.os.AsyncTask;

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

            /*
            Deploying the backend to local host
            *
            *
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            */

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

        if (mCallback != null) {
            mCallback.fetchResult(result);
        }
    }

}