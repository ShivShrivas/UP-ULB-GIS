package com.uphq.ulb_gis.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MasterListResponse
{
    @SerializedName("RespCode")
    @Expose
    private String respCode;
    @SerializedName("respMessage")
    @Expose
    private String respMessage;
    @SerializedName("Data")
    @Expose
    private List<MasterListData> data;

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

    public List<MasterListData> getData() {
        return data;
    }

    public void setData(List<MasterListData> data) {
        this.data = data;
    }
}
