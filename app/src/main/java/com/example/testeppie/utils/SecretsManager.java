package com.example.testeppie.utils;

import android.content.Context;
import android.content.res.Resources;

import com.example.testeppie.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SecretsManager {
    public static Properties loadSecretProperties(Context context) {
        Properties properties = new Properties();
        Resources resources = context.getResources();

        try {
            InputStream inputStream = resources.openRawResource(R.raw.secrets);
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }
}
