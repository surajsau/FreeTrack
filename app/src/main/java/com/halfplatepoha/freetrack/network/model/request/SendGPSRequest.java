package com.halfplatepoha.freetrack.network.model.request;

/**
 * Created by surajkumarsau on 17/07/16.
 */
public class SendGPSRequest {

    private String tid;
    private String latitude;
    private String longitude;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
