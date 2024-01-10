package com.example.testeppie.utils;

import android.content.Context;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Activity util class.
 */
public class ActivityStateUtil {

    /**
     * The constructor.
     */
    private ActivityStateUtil() {
        // DO nothing
    }

    /**
     * Handle the sign in action.
     *
     * @param message     The message that is presented.
     * @param loaderState The state of the loader.
     */
    public static void handleSignIn(Context context, ProgressBar progressBar, int loaderState, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        progressBar.setVisibility(loaderState);
    }
}
