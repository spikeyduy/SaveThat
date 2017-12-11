package com.exponentialsight.savethat;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class UsersCouponsActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "SavedCoupons";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Restore preferences
        SharedPreferences preferencesArray = getSharedPreferences(PREFS_NAME, 0);
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Use Editor object to make preference changes
        SharedPreferences preferencesArray = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = preferencesArray.edit();

        // Need to commit after an edits
        editor.apply();
    }
    
}
