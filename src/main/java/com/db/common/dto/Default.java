/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.common.dto;

import java.io.Serializable;

/**
 *
 * @author chanu1993@gmail.com
 */
public class Default implements Serializable{

    private String MultiBottom;
    private String MultiTop;
    private String alignsliceviewbottom;
    private String contigviewbottom;
    private String contigviewtop;
    private String cytoview;

    public Default() {
    }

    public Default(String MultiBottom, String MultiTop, String alignsliceviewbottom, String contigviewbottom, String contigviewtop, String cytoview) {
        this.MultiBottom = MultiBottom;
        this.MultiTop = MultiTop;
        this.alignsliceviewbottom = alignsliceviewbottom;
        this.contigviewbottom = contigviewbottom;
        this.contigviewtop = contigviewtop;
        this.cytoview = cytoview;
    }

    public String getMultiBottom() {
        return MultiBottom;
    }

    public void setMultiBottom(String MultiBottom) {
        this.MultiBottom = MultiBottom;
    }

    public String getMultiTop() {
        return MultiTop;
    }

    public void setMultiTop(String MultiTop) {
        this.MultiTop = MultiTop;
    }

    public String getAlignsliceviewbottom() {
        return alignsliceviewbottom;
    }

    public void setAlignsliceviewbottom(String alignsliceviewbottom) {
        this.alignsliceviewbottom = alignsliceviewbottom;
    }

    public String getContigviewbottom() {
        return contigviewbottom;
    }

    public void setContigviewbottom(String contigviewbottom) {
        this.contigviewbottom = contigviewbottom;
    }

    public String getContigviewtop() {
        return contigviewtop;
    }

    public void setContigviewtop(String contigviewtop) {
        this.contigviewtop = contigviewtop;
    }

    public String getCytoview() {
        return cytoview;
    }

    public void setCytoview(String cytoview) {
        this.cytoview = cytoview;
    }
}
