package com.udacity.gradle.builditbigger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.assertThat;


@RunWith(JUnit4.class)
public class AsyncTaskTest {

    @Test
    public void testAsyncTaskDeliversJoke() throws Throwable {

        final CountDownLatch signal = new CountDownLatch(1);

        final EndpointsAsyncTask myTask = new EndpointsAsyncTask(new EndpointsAsyncTask.AsyncResponse() {
            @Override
            public void fetchResult(String result) {

                assertThat(result, not(isEmptyString()));

                signal.countDown();
            }

        });

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                myTask.execute();
            }
        });

        signal.await(30, TimeUnit.SECONDS);// wait for callback
    }
}