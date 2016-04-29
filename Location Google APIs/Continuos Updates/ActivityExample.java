package com.maurocerbai.genericapp;

import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;

/**
 * Copyright (C) 2016 Mauro Cerbai
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */
public class ActivityExample extends AppCompatActivity implements LocationListener, GoogleApiClient.OnConnectionFailedListener {
    private String TAG = "ActivityExample";
    GoogleAPIsContinuosLocation mGoogleAPIsContinuosLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGoogleAPIsContinuosLocation = new GoogleAPIsContinuosLocation();
        mGoogleAPIsContinuosLocation.buildGoogleApiClient(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleAPIsContinuosLocation.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleAPIsContinuosLocation.onStop();
        super.onStop();
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG,"Location update: "+location.toString());
        //Do what you need with your location
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i(TAG,"GoogleApiClient connection has failed");
        //Handle the error as you prefer
    }
}

