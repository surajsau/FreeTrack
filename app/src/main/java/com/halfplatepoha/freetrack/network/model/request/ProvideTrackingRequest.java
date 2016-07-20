package com.halfplatepoha.freetrack.network.model.request;

/**
 * Created by surajkumarsau on 17/07/16.
 */
public class ProvideTrackingRequest {

    private String handle;
    private String trackentity;

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getTrackentity() {
        return trackentity;
    }

    public void setTrackentity(String trackentity) {
        this.trackentity = trackentity;
    }
}
