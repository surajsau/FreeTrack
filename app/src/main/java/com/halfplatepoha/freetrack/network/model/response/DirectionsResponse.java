package com.halfplatepoha.freetrack.network.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by surajkumarsau on 16/07/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DirectionsResponse implements Serializable {

    private ArrayList<Route> routes;

    public ArrayList<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(ArrayList<Route> routes) {
        this.routes = routes;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Route implements Serializable {

        private Bounds bounds;
        private ArrayList<Leg> legs;
        private OverviewPolyline overview_polyline;

        public Bounds getBounds() {
            return bounds;
        }

        public void setBounds(Bounds bounds) {
            this.bounds = bounds;
        }

        public ArrayList<Leg> getLegs() {
            return legs;
        }

        public void setLegs(ArrayList<Leg> legs) {
            this.legs = legs;
        }

        public OverviewPolyline getOverview_polyline() {
            return overview_polyline;
        }

        public void setOverview_polyline(OverviewPolyline overview_polyline) {
            this.overview_polyline = overview_polyline;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Bounds implements Serializable {

            private NorthEast northeast;
            private SouthWest southwest;

            public NorthEast getNortheast() {
                return northeast;
            }

            public void setNortheast(NorthEast northeast) {
                this.northeast = northeast;
            }

            public SouthWest getSouthwest() {
                return southwest;
            }

            public void setSouthwest(SouthWest southwest) {
                this.southwest = southwest;
            }

            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class NorthEast implements Serializable {
                double lat;
                double lng;

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }
            }

            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class SouthWest implements Serializable {
                double lat;
                double lng;

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }
            }
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Leg implements Serializable {

            private DurationInTraffic duration_in_traffic;
            private Distance distance;

            public DurationInTraffic getDuration_in_traffic() {
                return duration_in_traffic;
            }

            public void setDuration_in_traffic(DurationInTraffic duration_in_traffic) {
                this.duration_in_traffic = duration_in_traffic;
            }

            public Distance getDistance() {
                return distance;
            }

            public void setDistance(Distance distance) {
                this.distance = distance;
            }

            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class DurationInTraffic implements Serializable {
                private long value;

                public long getValue() {
                    return value;
                }

                public void setValue(long value) {
                    this.value = value;
                }
            }

            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Distance implements Serializable {
                private long value;

                public long getValue() {
                    return value;
                }

                public void setValue(long value) {
                    this.value = value;
                }
            }

        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class OverviewPolyline implements Serializable {

            private String points;

            public String getPoints() {
                return points;
            }

            public void setPoints(String points) {
                this.points = points;
            }
        }

    }

}
