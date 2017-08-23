/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.convert.service;

import com.db.common.dto.Main;
import com.db.common.dto.Meta;
import com.db.common.dto.WebData;
import com.db.convert.domain.Genome;
import com.db.convert.domain.Location;
import com.db.convert.domain.LocationLogicName;
import com.db.convert.domain.Release;
import com.db.convert.domain.Species;
import com.db.convert.domain.Track;
import com.db.convert.domain.TrackType;
import com.db.convert.repository.GenomeRepository;
import com.db.convert.repository.LocationLogicNameRepository;
import com.db.convert.repository.LocationRepository;
import com.db.convert.repository.ReleaseRepository;
import com.db.convert.repository.SpeciesRepository;
import com.db.convert.repository.TrackRepository;
import com.db.convert.repository.TrackTypeRepository;
import com.db.receive.repository.DataReceiveRepostitory;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author chanu1993@gmail.com
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class DBConverterService {

    @Autowired
    private TrackTypeRepository trackTypeRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationLogicNameRepository locationLogicNameRepository;

    @Autowired
    private SpeciesRepository speciesRepository;

    @Autowired
    private GenomeRepository genomeRepository;

    @Autowired
    private ReleaseRepository releaseRepository;

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private DataReceiveRepostitory dataReceiveRepostitory;

    public List<Main> getDetails() {
        ObjectMapper mapper = new ObjectMapper();

        List<Object[]> details = dataReceiveRepostitory.getDetails();
        List<Main> testItemList = new ArrayList<>();
        for (Object[] detail : details) {
            Main mainOb = new Main();
            mainOb.setAnalysisId(Integer.parseInt(detail[0].toString()));
            mainOb.setLogicName(detail[1].toString());
            mainOb.setDescription(detail[2].toString());
            mainOb.setDisplayLabel(detail[3].toString());

            String webDataString = detail[4].toString().replaceAll("=>", ":");
            String webDataFormatString = webDataString.replaceAll("default", "defaults");

            try {
                WebData obj = mapper.readValue(webDataFormatString, WebData.class);
                mainOb.setWebData(obj);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            testItemList.add(mainOb);
        }
        return testItemList;
    }

    public List<Meta> getDetailsOne() {
        List<Object[]> details = dataReceiveRepostitory.getDetailsOne();
        List<Meta> metaItemList = new ArrayList<>();

        for (Object[] detail : details) {
            Meta metaOb = new Meta();
            metaOb.setMeta_id(Integer.parseInt(detail[0].toString()));
            if (null == detail[1]) {
                metaOb.setSpecies_id(null);
            } else {
                metaOb.setSpecies_id(detail[1].toString());
            }
            metaOb.setMeta_key(detail[2].toString());
            metaOb.setMeta_value(detail[3].toString());
            metaItemList.add(metaOb);
        }
        return metaItemList;
    }

    @Transactional
    public Integer mainSave() {

        List<Main> dataList = getDetails();
        List<Meta> metDataList = getDetailsOne();

        //save data get object
        Genome genomeSaveData = new Genome();
        Location locationSaveData = new Location();
        TrackType trackTypeSaveData = new TrackType();
        Release releaseSaveData = new Release();

        //save location
        List<Location> dbLocationList = locationRepository.findAll();
        if (dbLocationList.isEmpty()) {
            Location location = new Location();
            location.setLocationType("mysql");
            location.setLocationObjectType("gene ");
            location.setLocationSpecies("homo_sapiens");
            location.setLocationDbtype("core");
            location.setLocationUri(null);
            locationSaveData = locationRepository.save(location);
        }

        List<String> logicNameList = new ArrayList<>();
        for (Main mainObjectData : dataList) {
            logicNameList.add(mainObjectData.getLogicName());
        }

        //save logic names and location_has_location_logic_names
        List<Location> dbLocationListOne = locationRepository.findAll();
        for (String name : logicNameList) {
            List<LocationLogicName> findByNameGetData = locationLogicNameRepository.findByName(name);
            if (findByNameGetData.isEmpty()) {
                LocationLogicName locationLogicName = new LocationLogicName();
                locationLogicName.setName(name);
                locationLogicName.setLocationList(dbLocationListOne);
                locationLogicNameRepository.save(locationLogicName);
            }
        }

        Species species = new Species();
        Release release = new Release();
        Genome genome = new Genome();

        for (Meta meta : metDataList) {
            if (meta.getMeta_key().equals("assembly.default")) {
                genome.setGenomeAssembly(meta.getMeta_value());
            }

            if (meta.getMeta_key().equals("species.production_name")) {
                genome.setGenomeSpecies(meta.getMeta_value());
                species.setName(meta.getMeta_value());
            }

            if (meta.getMeta_key().equals("schema_version")) {
                release.setReleaseVersion(Integer.parseInt(meta.getMeta_value()));
            }

            if (meta.getMeta_key().equals("species.division")) {
                String divisionValue = meta.getMeta_value();
                if (divisionValue.equalsIgnoreCase(ReleasDivision.bacteria.toString())) {
                    release.setReleaseDivision(ReleasDivision.bacteria);
                }
                if (divisionValue.equalsIgnoreCase(ReleasDivision.ensembl.toString())) {
                    release.setReleaseDivision(ReleasDivision.ensembl);
                }
                if (divisionValue.equalsIgnoreCase(ReleasDivision.fungi.toString())) {
                    release.setReleaseDivision(ReleasDivision.fungi);
                }
                if (divisionValue.equalsIgnoreCase(ReleasDivision.metazoa.toString())) {
                    release.setReleaseDivision(ReleasDivision.metazoa);
                }
                if (divisionValue.equalsIgnoreCase(ReleasDivision.plants.toString())) {
                    release.setReleaseDivision(ReleasDivision.plants);
                }
                if (divisionValue.equalsIgnoreCase(ReleasDivision.protists.toString())) {
                    release.setReleaseDivision(ReleasDivision.protists);
                }
            }
        }

        //relese save
        releaseSaveData = releaseRepository.save(release);

        List<Genome> dbGenomeList = genomeRepository.findAll();
        if (dbGenomeList.isEmpty()) {
            //species save
            Species speciesSaveData = speciesRepository.save(species);

            genome.setSpeciesId(speciesSaveData);
            genome.setGenomeStrain(false);

            //genome save
            genomeSaveData = genomeRepository.save(genome);
        }

        List<String> trackTypeList = new ArrayList<>();
        trackTypeList.add("gene");
        trackTypeList.add("repeats");
        trackTypeList.add("dna_alignment");
        trackTypeList.add("gc_content");

        //save track type
        for (String name : trackTypeList) {
            List<TrackType> findByNameTrackData = trackTypeRepository.findByTrackTypeName(name);
            if (findByNameTrackData.isEmpty()) {
                TrackType trackType = new TrackType();
                trackType.setTrackTypeName(name);
                trackTypeRepository.save(trackType);
            }
        }

        String description = "";
        String shortName = "";
        String longName = "";
        for (Main main : dataList) {
            WebData webData = (WebData) main.getWebData();
            if (webData.getKey().equals("ensembl")) {
                shortName = webData.getCaption();
                longName = webData.getMulti_name();
                description += main.getDescription();
            }
        }

        List<TrackType> dbTrackTypeList = trackTypeRepository.findAll();
        trackTypeSaveData = dbTrackTypeList.get(0);

        List<Genome> dbGnomeListOne = genomeRepository.findAll();
        genomeSaveData = dbGnomeListOne.get(0);

        List<Location> dbLocationListTwo = locationRepository.findAll();
        locationSaveData = dbLocationListTwo.get(0);

        //save track
        Track track = new Track();
        track.setDescription(description);
        track.setVersion("1.0");
        track.setShortName(shortName);
        track.setLongName(longName);
        track.setGenomeId(genomeSaveData);
        track.setLocationId(locationSaveData);
        track.setTrackTypeId(trackTypeSaveData);
        Track trackSaveData = trackRepository.save(track);

        Integer releaseVersion = null;
        for (Meta meta : metDataList) {
            if (meta.getMeta_key().equals("schema_version")) {
                releaseVersion = Integer.parseInt(meta.getMeta_value());
            }
        }

        List<Track> dbTrackList = new ArrayList<>();
        dbTrackList.add(trackSaveData);
        
        List<Release> findByReleaseVersion = releaseRepository.findByReleaseVersion(releaseVersion);
        if (findByReleaseVersion.isEmpty()) {
            releaseSaveData.setTrackList(dbTrackList);
            releaseRepository.save(releaseSaveData);
        } else {
            Release dbReleseData = findByReleaseVersion.get(0);
            dbReleseData.setTrackList(dbTrackList);
            releaseRepository.save(dbReleseData);
        }
        
        return 0;
    }
}
