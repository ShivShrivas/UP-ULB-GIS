package com.uphq.ulb_gis.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MasterListData {
    @SerializedName("WardName")
    @Expose
    private String wardName;
    @SerializedName("WardNo")
    @Expose
    private Integer wardNo;
    @SerializedName("OwnerName")
    @Expose
    private String ownerName;
    @SerializedName("FatherName")
    @Expose
    private String fatherName;
    @SerializedName("DOB")
    @Expose
    private String dob;
    @SerializedName("PropertyNo")
    @Expose
    private String propertyNo;
    @SerializedName("PropertyName")
    @Expose
    private String propertyName;
    @SerializedName("PropertyId")
    @Expose

    private Integer propertyId;

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public Integer getWardNo() {
        return wardNo;
    }

    public void setWardNo(Integer wardNo) {
        this.wardNo = wardNo;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPropertyNo() {
        return propertyNo;
    }

    public void setPropertyNo(String propertyNo) {
        this.propertyNo = propertyNo;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

}
