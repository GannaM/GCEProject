package com.udacity.gradle.builditbigger;

import android.app.Activity;
import android.app.Instrumentation;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.android.jokedisplay.JokeDisplay;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;

import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;

import static android.support.test.espresso.intent.matcher.BundleMatchers.hasEntry;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtras;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;

import static android.support.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;


/*
Tests that Async task successfully retrieves a non-empty string and sends it as an extra
to the next Activity
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public IntentsTestRule<MainActivity> mActivityTestRule = new IntentsTestRule<>(MainActivity.class);


    @Before
    public void stubAllExternalIntents() {
        intending(not(isInternal())).respondWith(
                new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }

    @Test
    public void buttonStartsActivityWithNonEmptyStringTest() {
        onView(withId(R.id.telljoke_button)).perform(click());

        intended(allOf(
                hasComponent(JokeDisplay.class.getName()),
                hasExtras(
                        hasEntry(equalTo("joke_extra"), not(equalTo(""))))
                )
        );

        //onView(withId(R.id.joke_textView)).check(matches(not(withText(""))));
    }
}
