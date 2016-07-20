package com.halfplatepoha.freetrack.network.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by surajkumarsau on 17/07/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProvideTrackingResponse {

    private String entity;

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }
}
