package com.maurocerbai.genericapp;

import android.app.Activity;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

/**
 * Copyright (C) 2016 Mauro Cerbai
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */
public class GoogleAPIsLastLocation {
    private String TAG = "GoogleAPIsLastLocation";
    private int REQUEST_FINELOC = 20151;
    private GoogleApiClient mGoogleApiClient;
    private Activity CallingActivity;

    //1 Call it on OnCreate of your Activity
    public void buildGoogleApiClient(Activity act) {
        CallingActivity = act;
        mGoogleApiClient = new GoogleApiClient.Builder(CallingActivity)
                .addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks)CallingActivity)
                .addOnConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) CallingActivity)
                .addApi(LocationServices.API)
                .build();
    }

    //2 Call it on onStart of your Activity
    public void onStart() {
        mGoogleApiClient.connect();
    }

    //3 Call it on onStop of your Activity
    public void onStop() {
        mGoogleApiClient.disconnect();
    }

    public void onConnectionSuspended(int i) {
        Log.i(TAG,"GoogleApiClient connection has been suspended");
        mGoogleApiClient.connect();
    }

    public GoogleApiClient getGoogleApiClient() {
        return mGoogleApiClient;
    }
}