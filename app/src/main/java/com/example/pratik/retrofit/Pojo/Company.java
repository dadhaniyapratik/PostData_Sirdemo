package com.example.pratik.retrofit.Pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pratik on 29-Nov-16.
 */

public class Company {

    private String name;
    private String catchPhrase;
    private String bs;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The catchPhrase
     */
    public String getCatchPhrase() {
        return catchPhrase;
    }

    /**
     *
     * @param catchPhrase
     * The catchPhrase
     */
    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    /**
     *
     * @return
     * The bs
     */
    public String getBs() {
        return bs;
    }

    /**
     *
     * @param bs
     * The bs
     */
    public void setBs(String bs) {
        this.bs = bs;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
