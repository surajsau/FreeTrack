package com.halfplatepoha.freetrack.network.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * Created by surajsau on 16/7/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RouteEntity implements Serializable{

    int status;
    List<Entity> entity;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Entity> getEntity() {
        return entity;
    }

    public void setEntity(List<Entity> entity) {
        this.entity = entity;
    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    public  static class   Entity implements  Serializable{

        long id;
        long userId;
        String trackingEntity;
        String status;
        String startTime;
        String created;
        String updated;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public String getTrackingEntity() {
            return trackingEntity;
        }

        public void setTrackingEntity(String trackingEntity) {
            this.trackingEntity = trackingEntity;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }
    }
}
