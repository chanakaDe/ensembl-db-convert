/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.receive.repository;

import com.db.receive.domain.DataReceive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chanu1993@gmail.com
 */
@Repository
public interface DataReceiveRepostitory extends JpaRepository<DataReceive, Integer> {

    @Query(value = "select a.analysis_id, logic_name, ad.description, ad.display_label, ad.web_data\n"
            + "FROM (\n"
            + "    select distinct(analysis_id) as analysis_id from gene \n"
            + ") as a \n"
            + "join analysis on (a.analysis_id = analysis.analysis_id) \n"
            + "join analysis_description as ad on (analysis.analysis_id = ad.analysis_id)\n"
            + "where ad.displayable = 1", nativeQuery = true)
    public List<Object[]> getDetails();

    @Query(value = "select * from meta where meta_key IN ('assembly.default', 'species.production_name', 'species.strain_collection', 'species.division', 'schema_type', 'schema_version');", nativeQuery = true)
    public List<Object[]> getDetailsOne();
}
