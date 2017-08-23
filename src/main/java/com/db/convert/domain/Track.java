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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author chanu1993@gmail.com
 */
@Entity
@Table(name = "track")
public class Track implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "track_id")
    private Integer trackId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "version")
    private String version;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "short_name")
    private String shortName;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "long_name")
    private String longName;
    
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "description")
    private String description;
    
    @ManyToMany(mappedBy = "trackList", fetch = FetchType.LAZY)
    private List<Release> releaseList;
    
    @JoinColumn(name = "genome_id", referencedColumnName = "genome_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Genome genomeId;
    
    @JoinColumn(name = "location_id", referencedColumnName = "location_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Location locationId;
    
    @JoinColumn(name = "track_type_id", referencedColumnName = "track_type_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TrackType trackTypeId;

    public Track() {
    }

    public Track(Integer trackId) {
        this.trackId = trackId;
    }

    public Track(Integer trackId, String version, String shortName, String longName, String description) {
        this.trackId = trackId;
        this.version = version;
        this.shortName = shortName;
        this.longName = longName;
        this.description = description;
    }

    public Integer getTrackId() {
        return trackId;
    }

    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public List<Release> getReleaseList() {
        return releaseList;
    }

    public void setReleaseList(List<Release> releaseList) {
        this.releaseList = releaseList;
    }

    public Genome getGenomeId() {
        return genomeId;
    }

    public void setGenomeId(Genome genomeId) {
        this.genomeId = genomeId;
    }

    public Location getLocationId() {
        return locationId;
    }

    public void setLocationId(Location locationId) {
        this.locationId = locationId;
    }

    public TrackType getTrackTypeId() {
        return trackTypeId;
    }

    public void setTrackTypeId(TrackType trackTypeId) {
        this.trackTypeId = trackTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (trackId != null ? trackId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Track)) {
            return false;
        }
        Track other = (Track) object;
        if ((this.trackId == null && other.trackId != null) || (this.trackId != null && !this.trackId.equals(other.trackId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Track{" + "genomeId=" + genomeId + ", locationId=" + locationId + ", trackTypeId=" + trackTypeId + '}';
    }
}
