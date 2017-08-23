/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 *
 * @author chanu1993@gmail.com
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebData implements Serializable {

    private String caption;
    private String colour_key;
    private Object defaults;
    private String key;
    private String label_key;
    private String multi_name;
    private String name;

    public WebData() {
    }

    public WebData(String caption, String colour_key, Object defaults, String key, String label_key, String multi_name, String name) {
        this.caption = caption;
        this.colour_key = colour_key;
        this.defaults = defaults;
        this.key = key;
        this.label_key = label_key;
        this.multi_name = multi_name;
        this.name = name;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getColour_key() {
        return colour_key;
    }

    public void setColour_key(String colour_key) {
        this.colour_key = colour_key;
    }

    @JsonProperty("defaults")
    public Object getDefaults() {
        return defaults;
    }

    public void setDefaults(Object defaults) {
        this.defaults = defaults;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLabel_key() {
        return label_key;
    }

    public void setLabel_key(String label_key) {
        this.label_key = label_key;
    }

    public String getMulti_name() {
        return multi_name;
    }

    public void setMulti_name(String multi_name) {
        this.multi_name = multi_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
