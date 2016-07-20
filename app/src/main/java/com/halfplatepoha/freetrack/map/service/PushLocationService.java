package com.halfplatepoha.freetrack.map.service;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.angelhack.freetrack.map.util.IMapConstants;
import com.angelhack.freetrack.network.ResponseListener;
import com.angelhack.freetrack.network.TFApi;
import com.angelhack.freetrack.network.TFServiceGenerator;
import com.angelhack.freetrack.network.request.SendGPSRequest;
import com.angelhack.freetrack.utils.IPref;
import com.angelhack.freetrack.utils.TFPreference;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.halfplatepoha.freetrack.network.api.FTApi;

/**
 * Created by surajkumarsau on 16/07/16.
 */
public class PushLocationService extends Service implements LocationListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    private static final String TAG = PushLocationService.class.getSimpleName();

    private FTApi mTFApi;

    private LocationRequest mLocationRequest;

    private GoogleApiClient mApiClient;

    private String tId;

    private SendGPSRequest request;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "service started");

        tId = TFPreference.getFromPref(IPref.TRACKING_ID, "");

        initGoogleApiClient();

        initNetwork();

        initLocationRequest();

        request = new SendGPSRequest();
        request.setTid(tId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "on destroy");
        mApiClient.disconnect();

        super.onDestroy();
    }

    private void initLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(IMapConstants.PRODUCER_LOCATION_REQUEST_IN_MILLIS);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        Log.d(TAG, "producer location request set");
    }

    private void initNetwork() {
        mTFApi = TFServiceGenerator.createService(TFApi.class);
    }

    private void initGoogleApiClient() {
        mApiClient = new GoogleApiClient.Builder(this)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();

        mApiClient.connect();

        Log.d(TAG, "GoogleApiClient connection initiated");
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.e(TAG, "current location: " + location.getLatitude() + ", " + location.getLongitude());
        request.setLatitude(String.valueOf(location.getLatitude()));
        request.setLongitude(String.valueOf(location.getLongitude()));

        mTFApi.sendGpsLocation(request).enqueue(pushLocationResponse);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.e(TAG, "GoogleApiClient connected");
        requestForCurrentLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.e(TAG, "GoogleApiClient suspended");
        mApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e(TAG, "GoogleApiClient failed");
        mApiClient.connect();
    }

    private void requestForCurrentLocation() {
        if(!mApiClient.isConnected() && !mApiClient.isConnecting()) {
            mApiClient.connect();
            return;
        }

        try {
            LocationServices.FusedLocationApi.requestLocationUpdates(mApiClient, mLocationRequest, this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private ResponseListener<Void> pushLocationResponse = new ResponseListener<Void>() {
        @Override
        public void parseNetworkResponse(Void response) {

        }
    };
}
