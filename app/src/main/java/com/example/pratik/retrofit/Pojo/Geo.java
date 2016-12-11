package com.example.pratik.retrofit.Pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pratik on 29-Nov-16.
 */

public class Geo {

    private String lat;
    private String lng;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The lat
     */
    public String getLat() {
        return lat;
    }

    /**
     * @param lat The lat
     */
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     * @return The lng
     */
    public String getLng() {
        return lng;
    }

    /**
     * @param lng The lng
     */
    public void setLng(String lng) {
        this.lng = lng;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}