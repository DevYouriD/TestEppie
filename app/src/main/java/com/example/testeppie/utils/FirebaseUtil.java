package com.example.testeppie.utils;

import android.content.Context;
import android.content.res.Resources;

import com.example.testeppie.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Firebase util used to access firebase instances.
 */
public class FirebaseUtil {

    /**
     * The constructor.
     */
    private FirebaseUtil() {
        // Do nothing.
    }

    /**
     * Initialise the firebase application.
     */
    public static void initializeFirebaseApp() {
        FirebaseApp.getInstance();
    }

    /**
     * Get the firebase authentication instance.
     *
     * @return The firebase authentication instance.
     */
    public static FirebaseAuth getFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    /**
     * Get the firebase database instance.
     *
     * @return The firebase database instance.
     */
    public static FirebaseDatabase getFirebaseDatabase(Context context) {
        String dbUrl = getFirebaseDatabaseUrl(context);

        // Only for `us-central1` you don't need a URL argument.
        return FirebaseDatabase.getInstance(dbUrl);
    }

    /**
     * Get the firebase database url.
     * <p>This is needed to get the instance of the database when this isn't located in `us-central1`</p>
     *
     * @return The database URL.
     */
    private static String getFirebaseDatabaseUrl(Context context) {
        Properties properties = loadSecretProperties(context);
        return properties.getProperty("fire_base_url");
    }

    /**
     * Get the properties form the `secrets.properties` file.
     *
     * @return The properties.
     */
    private static Properties loadSecretProperties(Context context) {
        Properties properties = new Properties();
        Resources resources = context.getResources();

        try {
            // secrets.properties contents
            // fire_base_url=http://testeppie-default-rtdb.europe-west1.firebasedatabase.app/
            //
            // Test User
            // Username: testday2@gmail.com
            // Password: Welkom01!
            InputStream inputStream = resources.openRawResource(R.raw.secrets);
            properties.load(inputStream);

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }
}
