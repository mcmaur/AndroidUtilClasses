package com.maurocerbai.genericapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;

/**
 * Copyright (C) 2016 Mauro Cerbai
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */
public class ActivityExample extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private String TAG = "ActivityExample";
    private int REQUEST_FINELOC = 138746;
    GoogleAPIsLastLocation mGoogleAPIsContinuosLocation;
    private Location mLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGoogleAPIsContinuosLocation = new GoogleAPIsLastLocation();
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
    public void onConnected(@Nullable Bundle bundle) {
        Log.i(TAG,"GoogleApiClient onConnected");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                    Toast.makeText(this, "We need gps data come on", Toast.LENGTH_LONG);
                }
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_FINELOC);
            } else {
                mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleAPIsContinuosLocation.getGoogleApiClient());
            }
        } else {
            mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleAPIsContinuosLocation.getGoogleApiClient());
        }

        if (mLocation != null) {
            Log.d(TAG,"Location: "+mLocation.toString());
            //String.valueOf(mLocation.getLatitude()));
            //String.valueOf(mLocation.getLongitude()));
        } else {
            Toast.makeText(this, "Location not Detected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleAPIsContinuosLocation.onConnectionSuspended(i);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i(TAG,"GoogleApiClient connection has failed");
        //Handle the error as you prefer
    }
}
