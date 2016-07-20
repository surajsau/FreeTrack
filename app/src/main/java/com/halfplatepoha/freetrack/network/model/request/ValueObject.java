package com.halfplatepoha.freetrack.network.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by surajkumarsau on 16/07/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ValueObject implements Serializable {

    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
