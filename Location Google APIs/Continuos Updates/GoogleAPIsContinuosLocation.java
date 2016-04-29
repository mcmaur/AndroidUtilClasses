package com.maurocerbai.genericapp;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by mauro on 29/04/16.
 */
public class GoogleAPIsContinuosLocation implements GoogleApiClient.ConnectionCallbacks {
    private String TAG = "GoogleAPIsContinuosLocation";
    private int REQUEST_FINELOC = 20151;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private Activity CallingActivity;

    //1 Call it on OnCreate of your Activity
    public void buildGoogleApiClient(Activity act) {
        CallingActivity = act;
        mGoogleApiClient = new GoogleApiClient.Builder(CallingActivity)
                .addConnectionCallbacks(this)
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

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.i(TAG,"GoogleApiClient onConnected");
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(1000);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(CallingActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(CallingActivity, Manifest.permission.ACCESS_FINE_LOCATION)) {
                    Toast.makeText(CallingActivity, "We need gps data come on", Toast.LENGTH_LONG);
                }
                ActivityCompat.requestPermissions(CallingActivity,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_FINELOC);
            } else {
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, (LocationListener)CallingActivity);
            }
        } else {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, (LocationListener)CallingActivity);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG,"GoogleApiClient connection has been suspended");
        mGoogleApiClient.connect();
    }

}
