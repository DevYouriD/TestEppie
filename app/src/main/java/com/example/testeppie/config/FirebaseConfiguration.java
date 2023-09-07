package com.example.testeppie.config;

import android.content.Context;
import android.content.res.Resources;

import com.example.testeppie.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FirebaseConfiguration {

    private FirebaseConfiguration() {
        // Do nothing.
    }

    public static void initializeFirebaseApp() {
        FirebaseApp.getInstance();
    }

    public static FirebaseAuth getFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    public static FirebaseDatabase getFirebaseDatabase(Context context) {
        String dbUrl = getFirebaseDatabaseUrl(context);
        return FirebaseDatabase.getInstance(dbUrl);
    }

    private static String getFirebaseDatabaseUrl(Context context) {
        Properties properties = loadSecretProperties(context);

        // Get the database connection URL (only for `us-central1` you don't need a URL argument)
        return properties.getProperty("fire_base_url");
    }

    private static Properties loadSecretProperties(Context context) {
        Properties properties = new Properties();
        Resources resources = context.getResources();

        try {
            InputStream inputStream = resources.openRawResource(R.raw.secrets);
            properties.load(inputStream);

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }
}
