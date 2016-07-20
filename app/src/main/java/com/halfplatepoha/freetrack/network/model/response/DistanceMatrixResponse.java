package com.halfplatepoha.freetrack.network.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by surajkumarsau on 17/07/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DistanceMatrixResponse {

    ArrayList<Row> rows;

    public ArrayList<Row> getRows() {
        return rows;
    }

    public void setRows(ArrayList<Row> rows) {
        this.rows = rows;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Row implements Serializable{

        private ArrayList<Element> elements;

        public ArrayList<Element> getElements() {
            return elements;
        }

        public void setElements(ArrayList<Element> elements) {
            this.elements = elements;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Element implements Serializable {

            private Distance distance;
            private Duration duration;

            public Distance getDistance() {
                return distance;
            }

            public void setDistance(Distance distance) {
                this.distance = distance;
            }

            public Duration getDuration() {
                return duration;
            }

            public void setDuration(Duration duration) {
                this.duration = duration;
            }

            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Distance implements Serializable {
                private String text;

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }
            }

            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Duration implements Serializable {
                private String text;

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }
            }
        }
    }

}
