package com.uphq.ulb_gis.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpinnerData {
    @SerializedName("MasterId")
    @Expose
    private Integer masterId;
    @SerializedName("MasterName")
    @Expose
    private String masterName;

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }
}
