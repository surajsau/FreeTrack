package com.halfplatepoha.freetrack.map.util;

import android.Manifest;

/**
 * Created by surajkumarsau on 16/07/16.
 */
public interface IMapConstants {
    int LOCATION_PERMISSION_REQUEST = 101;
    String[] LOCATION_PERMISSIONS = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};

    int LOCATION_REQUEST_INTERVAL_MILLIS = 10000;
    int PRODUCER_LOCATION_REQUEST_IN_MILLIS = 5000;

    int CHECK_GPS_REQUEST = 102;
}
