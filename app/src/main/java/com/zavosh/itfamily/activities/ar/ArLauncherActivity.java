package com.zavosh.itfamily.activities.ar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.ar.core.ArCoreApk;
import com.zavosh.itfamily.R;

public class ArLauncherActivity extends AppCompatActivity {

    Button imageButtonEnterAR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar_launcher);
        imageButtonEnterAR = findViewById(R.id.imageButtonEnterAR);

        addListeners();
    }

    @Override
    protected void onResume() {

        super.onResume();

        ar_setupAvailability();
    }

    private void ar_setupAvailability() {
        ArSystem.activityStackNumber = 0;

        // Needed for result-caching purposes - Do not delete it.
        ArCoreApk.Availability availabilityCheck1 = ArCoreApk.getInstance().checkAvailability(ArLauncherActivity.this);

        if(ArSystem.isEmulator()) {
            Log.d("availability", "Emulator Mode.");
            availabilityCheck1 = ArCoreApk.Availability.SUPPORTED_INSTALLED;}

        if(availabilityCheck1 == ArCoreApk.Availability.SUPPORTED_APK_TOO_OLD) {
            ArSystem.gotARCodeApkTooOld = true;
            ArSystem.saveToDevicePref_GotApkTooOld(getApplicationContext(), true);
        }

        // Checking the availability from the device pref (history from recent app runs)
        if(ArSystem.gotARCodeApkTooOld==false)
        {
            ArSystem.gotARCodeApkTooOld = ArSystem.readFromDevicePref_GotApkTooOld(getApplicationContext());
        }


        Log.d("availability", "AvailabilityCheck1: "+ availabilityCheck1.toString());

        // Start AR immediately for fast itterations
        //if(ArSystem.isInDevelopmentDebug)
        //ar_startArSplash();
    }

    private void addListeners() {

        imageButtonEnterAR.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                ar_startArSplash();
            }

        });
    }

    private void ar_startArSplash() {
        //https://developer.android.com/reference/android/content/Intent.html#FLAG_ACTIVITY_PREVIOUS_IS_TOP
        //https://developer.android.com/reference/android/content/Intent.html#FLAG_ACTIVITY_NO_HISTORY
        //https://developer.android.com/reference/android/content/Intent.html#FLAG_ACTIVITY_NO_USER_ACTION

        ArSystem.activityStackNumber = 1;
        Intent arIntentSplash = new Intent(ArLauncherActivity.this, ArActivitySplash.class);
        startActivity(arIntentSplash);
    }
}
