/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.convert.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author chanu1993@gmail.com
 */
@Entity
@Table(name = "location")
public class Location implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "location_id")
    private Integer locationId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "location_type")
    private String locationType;
    
    @Size(max = 100)
    @Column(name = "location_object_type")
    private String locationObjectType;
    
    @Size(max = 100)
    @Column(name = "location_species")
    private String locationSpecies;
    
    @Size(max = 100)
    @Column(name = "location_dbtype")
    private String locationDbtype;
    
    @Size(max = 255)
    @Column(name = "location_uri")
    private String locationUri;
    
    @ManyToMany(mappedBy = "locationList", fetch = FetchType.LAZY)
    private List<LocationLogicName> locationLogicNameList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "locationId", fetch = FetchType.LAZY)
    private List<Track> trackList;

    public Location() {
    }

    public Location(Integer locationId) {
        this.locationId = locationId;
    }

    public Location(Integer locationId, String locationType) {
        this.locationId = locationId;
        this.locationType = locationType;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getLocationObjectType() {
        return locationObjectType;
    }

    public void setLocationObjectType(String locationObjectType) {
        this.locationObjectType = locationObjectType;
    }

    public String getLocationSpecies() {
        return locationSpecies;
    }

    public void setLocationSpecies(String locationSpecies) {
        this.locationSpecies = locationSpecies;
    }

    public String getLocationDbtype() {
        return locationDbtype;
    }

    public void setLocationDbtype(String locationDbtype) {
        this.locationDbtype = locationDbtype;
    }

    public String getLocationUri() {
        return locationUri;
    }

    public void setLocationUri(String locationUri) {
        this.locationUri = locationUri;
    }

    @XmlTransient
    public List<LocationLogicName> getLocationLogicNameList() {
        return locationLogicNameList;
    }

    public void setLocationLogicNameList(List<LocationLogicName> locationLogicNameList) {
        this.locationLogicNameList = locationLogicNameList;
    }

    @XmlTransient
    public List<Track> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (locationId != null ? locationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Location)) {
            return false;
        }
        Location other = (Location) object;
        if ((this.locationId == null && other.locationId != null) || (this.locationId != null && !this.locationId.equals(other.locationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.dbconvert.model.Location[ locationId=" + locationId + " ]";
    }
    
}
