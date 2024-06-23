package com.uphq.ulb_gis.models;

import android.widget.Spinner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AllSpinnerDataModel {
    @SerializedName("respCode")
    @Expose
    private String respCode;
    @SerializedName("respMessage")
    @Expose
    private String respMessage;
    @SerializedName("Data")
    @Expose
    private ArrayList<SpinnerData> data;

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

    public ArrayList<SpinnerData> getData() {
        return data;
    }

    public void setData(ArrayList<SpinnerData> data) {
        this.data = data;
    }
}
