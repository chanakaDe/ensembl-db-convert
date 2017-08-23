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
@Table(name = "track_type")
public class TrackType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "track_type_id")
    private Integer trackTypeId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "track_type_name")
    private String trackTypeName;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trackTypeId", fetch = FetchType.LAZY)
    private List<Track> trackList;

    public TrackType() {
    }

    public TrackType(Integer trackTypeId) {
        this.trackTypeId = trackTypeId;
    }

    public TrackType(Integer trackTypeId, String trackTypeName) {
        this.trackTypeId = trackTypeId;
        this.trackTypeName = trackTypeName;
    }

    public Integer getTrackTypeId() {
        return trackTypeId;
    }

    public void setTrackTypeId(Integer trackTypeId) {
        this.trackTypeId = trackTypeId;
    }

    public String getTrackTypeName() {
        return trackTypeName;
    }

    public void setTrackTypeName(String trackTypeName) {
        this.trackTypeName = trackTypeName;
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
        hash += (trackTypeId != null ? trackTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrackType)) {
            return false;
        }
        TrackType other = (TrackType) object;
        if ((this.trackTypeId == null && other.trackTypeId != null) || (this.trackTypeId != null && !this.trackTypeId.equals(other.trackTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.dbconvert.model.TrackType[ trackTypeId=" + trackTypeId + " ]";
    }
    
}
