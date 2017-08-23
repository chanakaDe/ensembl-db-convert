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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "genome")
public class Genome implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "genome_id")
    private Integer genomeId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "genome_species")
    private String genomeSpecies;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "genome_assembly")
    private String genomeAssembly;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "genome_strain")
    private boolean genomeStrain;
    
    @JoinColumn(name = "species_id", referencedColumnName = "species_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Species speciesId;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "genomeId", fetch = FetchType.LAZY)
    private List<Track> trackList;

    public Genome() {
    }

    public Genome(Integer genomeId) {
        this.genomeId = genomeId;
    }

    public Genome(Integer genomeId, String genomeSpecies, String genomeAssembly, boolean genomeStrain) {
        this.genomeId = genomeId;
        this.genomeSpecies = genomeSpecies;
        this.genomeAssembly = genomeAssembly;
        this.genomeStrain = genomeStrain;
    }

    public Integer getGenomeId() {
        return genomeId;
    }

    public void setGenomeId(Integer genomeId) {
        this.genomeId = genomeId;
    }

    public String getGenomeSpecies() {
        return genomeSpecies;
    }

    public void setGenomeSpecies(String genomeSpecies) {
        this.genomeSpecies = genomeSpecies;
    }

    public String getGenomeAssembly() {
        return genomeAssembly;
    }

    public void setGenomeAssembly(String genomeAssembly) {
        this.genomeAssembly = genomeAssembly;
    }

    public boolean getGenomeStrain() {
        return genomeStrain;
    }

    public void setGenomeStrain(boolean genomeStrain) {
        this.genomeStrain = genomeStrain;
    }

    public Species getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(Species speciesId) {
        this.speciesId = speciesId;
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
        hash += (genomeId != null ? genomeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Genome)) {
            return false;
        }
        Genome other = (Genome) object;
        if ((this.genomeId == null && other.genomeId != null) || (this.genomeId != null && !this.genomeId.equals(other.genomeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Genome{" + "genomeId=" + genomeId + ", genomeSpecies=" + genomeSpecies + ", genomeAssembly=" + genomeAssembly + ", genomeStrain=" + genomeStrain + ", speciesId=" + speciesId + '}';
    }
    
}
