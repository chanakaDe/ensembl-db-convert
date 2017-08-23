/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.common.dto;

/**
 *
 * @author chanu1993@gmail.com
 */
public class Meta {

    private Integer meta_id;
    private String species_id;
    private String meta_key;
    private String meta_value;

    public Meta() {
    }

    public Meta(Integer meta_id, String species_id, String meta_key, String meta_value) {
        this.meta_id = meta_id;
        this.species_id = species_id;
        this.meta_key = meta_key;
        this.meta_value = meta_value;
    }

    public Integer getMeta_id() {
        return meta_id;
    }

    public void setMeta_id(Integer meta_id) {
        this.meta_id = meta_id;
    }

    public String getSpecies_id() {
        return species_id;
    }

    public void setSpecies_id(String species_id) {
        this.species_id = species_id;
    }

    public String getMeta_key() {
        return meta_key;
    }

    public void setMeta_key(String meta_key) {
        this.meta_key = meta_key;
    }

    public String getMeta_value() {
        return meta_value;
    }

    public void setMeta_value(String meta_value) {
        this.meta_value = meta_value;
    }

    @Override
    public String toString() {
        return "Meta{" + "meta_id=" + meta_id + ", species_id=" + species_id + ", meta_key=" + meta_key + ", meta_value=" + meta_value + '}';
    }
    
}
