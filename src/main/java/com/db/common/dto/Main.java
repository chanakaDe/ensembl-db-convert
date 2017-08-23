/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author chanu1993@gmail.com
 */
public class Main {

    private Integer analysisId;
    private String logicName;
    private String description;
    private String displayLabel;
    private Object webData;

    public Main() {
    }

    public Main(Integer analysisId, String logicName, String description, String displayLabel, Object webData) {
        this.analysisId = analysisId;
        this.logicName = logicName;
        this.description = description;
        this.displayLabel = displayLabel;
        this.webData = webData;
    }

    public Integer getAnalysisId() {
        return analysisId;
    }

    public void setAnalysisId(Integer analysisId) {
        this.analysisId = analysisId;
    }

    public String getLogicName() {
        return logicName;
    }

    public void setLogicName(String logicName) {
        this.logicName = logicName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayLabel() {
        return displayLabel;
    }

    public void setDisplayLabel(String displayLabel) {
        this.displayLabel = displayLabel;
    }

    @JsonProperty("webData")
    public Object getWebData() {
        return webData;
    }

    public void setWebData(Object webData) {
        this.webData = webData;
    }

}
