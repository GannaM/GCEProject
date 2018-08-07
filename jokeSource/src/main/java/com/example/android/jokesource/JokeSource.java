package com.example.android.jokesource;

import java.util.Random;

public class JokeSource {

    private static String[] mJokesArray = {
            "Microsoft broke Volkswagen\'s world record: Volkswagen only made 22 million bugs!",
            "Windows Vista supports real multitasking - it can boot and crash simultaneously.",
            "Q. What creature has the best aptitude for engineering? \n\nA. The spider — It has its own website.",
            "Q. What is the biggest lie in the entire universe? \n\nA. I have read and agree to the Terms & Conditions.",
            "Q: Why do programmers always mix up Halloween and Christmas? \n\nA: Because 31 OCT = 25 DEC."
    };


    public static String getJoke() {

        Random randomGenerator = new Random(System.currentTimeMillis());
        int randomNumber = randomGenerator.nextInt(mJokesArray.length);

        String joke = mJokesArray[randomNumber];

        return joke;
    }

}
