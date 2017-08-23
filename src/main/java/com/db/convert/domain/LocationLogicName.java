/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.convert.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author chanu1993@gmail.com
 */
@Entity
@Table(name = "location_logic_name")
public class LocationLogicName implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "lln_id")
    private Integer llnId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    
    @JoinTable(name = "location_has_location_logic_names", joinColumns = {
        @JoinColumn(name = "lln_id", referencedColumnName = "lln_id")}, inverseJoinColumns = {
        @JoinColumn(name = "location_id", referencedColumnName = "location_id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Location> locationList;

    public LocationLogicName() {
    }

    public LocationLogicName(Integer llnId) {
        this.llnId = llnId;
    }

    public LocationLogicName(Integer llnId, String name) {
        this.llnId = llnId;
        this.name = name;
    }

    public Integer getLlnId() {
        return llnId;
    }

    public void setLlnId(Integer llnId) {
        this.llnId = llnId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<Location> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (llnId != null ? llnId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocationLogicName)) {
            return false;
        }
        LocationLogicName other = (LocationLogicName) object;
        if ((this.llnId == null && other.llnId != null) || (this.llnId != null && !this.llnId.equals(other.llnId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.dbconvert.model.LocationLogicName[ llnId=" + llnId + " ]";
    }
    
}
