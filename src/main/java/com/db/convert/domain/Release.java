/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.convert.domain;

import com.db.convert.service.ReleasDivision;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author chanu1993@gmail.com
 */
@Entity
@Table(name = "`release`")
public class Release implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "release_id")
    private Integer releaseId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "release_division")
    @Enumerated(EnumType.STRING)
    private ReleasDivision releaseDivision;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "release_version")
    private int releaseVersion;
    
    @JsonIgnore
    @JoinTable(name = "track_has_release", joinColumns = {
        @JoinColumn(name = "release_id", referencedColumnName = "release_id")}, inverseJoinColumns = {
        @JoinColumn(name = "track_id", referencedColumnName = "track_id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Track> trackList;

    public Release() {
    }

    public Release(Integer releaseId) {
        this.releaseId = releaseId;
    }

    public Release(Integer releaseId, ReleasDivision releaseDivision, int releaseVersion) {
        this.releaseId = releaseId;
        this.releaseDivision = releaseDivision;
        this.releaseVersion = releaseVersion;
    }

    public Integer getReleaseId() {
        return releaseId;
    }

    public void setReleaseId(Integer releaseId) {
        this.releaseId = releaseId;
    }

    public ReleasDivision getReleaseDivision() {
        return releaseDivision;
    }

    public void setReleaseDivision(ReleasDivision releaseDivision) {
        this.releaseDivision = releaseDivision;
    }

    public int getReleaseVersion() {
        return releaseVersion;
    }

    public void setReleaseVersion(int releaseVersion) {
        this.releaseVersion = releaseVersion;
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
        hash += (releaseId != null ? releaseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Release)) {
            return false;
        }
        Release other = (Release) object;
        if ((this.releaseId == null && other.releaseId != null) || (this.releaseId != null && !this.releaseId.equals(other.releaseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Release{" + "releaseId=" + releaseId + ", releaseDivision=" + releaseDivision + ", releaseVersion=" + releaseVersion + ", trackList=" + trackList + '}';
    }
    
}
