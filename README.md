#### Project Overview 

App with multiple flavors (free and paid versions) that uses multiple libraries and Google Cloud Endpoints. It consists of four modules. A Java library that provides jokes, a Google Cloud Endpoints (GCE) project that serves those jokes, an Android Library containing an activity for displaying jokes, and an Android app that fetches jokes from the GCE module and passes them to the Android Library for display.

The **free version** contains an activity with a banner ad and a button that purports to tell a joke. After a user presses the button, an interstitial ad is shown, and only then a new screen with a joke.
The **paid flavor** works the same way only without ads.

The project is built upon this initial repository 
https://github.com/udacity/ud867/tree/master/FinalProject

#### Java Library

The library that provides jokes for the app via project dependency between the app and the Library.

#### Android Library

An Android Library contains an Activity that displays a joke passed to it as an intent extra. Project dependencies  are wired up so that the button passes the joke from the Java Library to the Android Library.

#### GCE

Instead of pulling jokes directly from our Java library, a Google Cloud Endpoints development server is setup to pull jokes from there. 

To be able to run the project you will need to be able to run a local instance of the GCE server. In order to do that you will have to install the Cloud SDK:

https://cloud.google.com/sdk/docs/

Once installed, you will need to follow the instructions in the Setup Cloud SDK section at:

https://cloud.google.com/endpoints/docs/frameworks/java/migrating-android

Note: You do not need to follow the rest of steps in the migration guide, only the Setup Cloud SDK.

Start or stop your local server by using the gradle tasks as shown in the following screenshot:

[![Screenshot](/FinalProject/GCE-server-gradle-tasks.png")]()

Once your local GCE server is started you should see the following at 
[localhost:8080](http://localhost:8080)

[![Sreenshot](https://raw.githubusercontent.com/GoogleCloudPlatform/gradle-appengine-templates/77e9910911d5412e5efede5fa681ec105a0f02ad/doc/img/devappserver-endpoints.png")]()

#### Functional Tests

An instrumented JUnit test checks that Async task successfully retrieves a non-empty string.