package com.uphq.ulb_gis.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("respCode")
    @Expose
    private String respCode;
    @SerializedName("respMessage")
    @Expose
    private String respMessage;
    @SerializedName("OfficeName")
    @Expose
    private String officeName;
    @SerializedName("OfficeId")
    @Expose
    private Integer officeId;
    @SerializedName("DivisionName")
    @Expose
    private String divisionName;
    @SerializedName("UserId")
    @Expose
    private Integer userId;
    @SerializedName("DistrictName")
    @Expose
    private Object districtName;

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMessage() {
        return respMessage;
    }

    public void setRespMessage(String respMessage) {
        this.respMessage = respMessage;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Object getDistrictName() {
        return districtName;
    }

    public void setDistrictName(Object districtName) {
        this.districtName = districtName;
    }
}
