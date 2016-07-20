package com.halfplatepoha.freetrack.network.model.response;

import com.halfplatepoha.freetrack.network.request.ValueObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by surajsau on 17/7/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsumerLatestEntity extends ValueObject implements Serializable {

    int status;

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    Entity entity;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }




    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Entity implements Serializable {

        String tid;
        String latitude;
        String longitude;

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
}