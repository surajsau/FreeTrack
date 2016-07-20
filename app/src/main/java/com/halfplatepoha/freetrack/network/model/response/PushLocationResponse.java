package com.halfplatepoha.freetrack.network.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by surajkumarsau on 16/07/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PushLocationResponse {

    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
