package com.example.testeppie.views;

import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testeppie.R;
import com.example.testeppie.utils.FirebaseUtil;

/**
 * The view responsible of the splash animation.
 */
public class ActivitySplash extends AppCompatActivity {

    private static final long SPLASH_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        FirebaseUtil.initializeFirebaseApp();

        hideSystemUI();

        new Handler().postDelayed(() -> {
            openWelcomeActivity();

            finish();
        }, SPLASH_DELAY);
    }

    /**
     * Open and go to the welcome activity.
     */
    private void openWelcomeActivity() {
        startActivity(new Intent(ActivitySplash.this, ActivityWelcome.class));
    }

    /**
     * Hide the system UI.
     */
    private void hideSystemUI() {
        getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);
    }
}