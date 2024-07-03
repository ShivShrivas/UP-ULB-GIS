package com.uphq.ulb_gis.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PropertywiseDataModel {
    @SerializedName("RespCode")
    @Expose
    private Object respCode;
    @SerializedName("respMessage")
    @Expose
    private Object respMessage;
    @SerializedName("PropertyId")
    @Expose
    private Integer propertyId;
    @SerializedName("OfficeId")
    @Expose
    private Integer officeId;
    @SerializedName("PropertyNo")
    @Expose
    private String propertyNo;
    @SerializedName("OldPropertyNo")
    @Expose
    private String oldPropertyNo;
    @SerializedName("AssesmentDate")
    @Expose
    private String assesmentDate;
    @SerializedName("WardId")
    @Expose
    private Integer wardId;
    @SerializedName("MuhallaId")
    @Expose
    private Integer muhallaId;
    @SerializedName("OwnershipId")
    @Expose
    private Integer ownershipId;
    @SerializedName("FatherName")
    @Expose
    private String fatherName;
    @SerializedName("HindiHatherName")
    @Expose
    private String hindiHatherName;
    @SerializedName("OwnerName")
    @Expose
    private String ownerName;
    @SerializedName("HindiOwnerName")
    @Expose
    private String hindiOwnerName;
    @SerializedName("HouseNo")
    @Expose
    private String houseNo;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("MobileNo")
    @Expose
    private String mobileNo;
    @SerializedName("RentAreaSqr")
    @Expose
    private String rentAreaSqr;
    @SerializedName("TotalArea")
    @Expose
    private Double totalArea;
    @SerializedName("AreaRateId")
    @Expose
    private Integer areaRateId;
    @SerializedName("RoadWidthId")
    @Expose
    private Integer roadWidthId;
    @SerializedName("ConstructionYear")
    @Expose
    private Integer constructionYear;
    @SerializedName("TotalOwnArea")
    @Expose
    private Double totalOwnArea;
    @SerializedName("TypeId")
    @Expose
    private Integer typeId;
    @SerializedName("Floor")
    @Expose
    private Integer floor;
    @SerializedName("NoofRoom")
    @Expose
    private Integer noofRoom;
    @SerializedName("NoofShop")
    @Expose
    private String noofShop;
    @SerializedName("RoadFit")
    @Expose
    private Double roadFit;
    @SerializedName("Remark")
    @Expose
    private String remark;
    @SerializedName("IsWConnection")
    @Expose
    private Integer isWConnection;
    @SerializedName("GridNo")
    @Expose
    private String gridNo;
    @SerializedName("GaliNo")
    @Expose
    private Integer galiNo;
    @SerializedName("IsTaxPay")
    @Expose
    private Integer isTaxPay;
    @SerializedName("RashanCardTypeId")
    @Expose
    private Integer rashanCardTypeId;
    @SerializedName("NoofMember")
    @Expose
    private Integer noofMember;
    @SerializedName("RelegionId")
    @Expose
    private Integer relegionId;
    @SerializedName("CasteId")
    @Expose
    private Integer casteId;
    @SerializedName("RegistrationTypeId")
    @Expose
    private Integer registrationTypeId;
    @SerializedName("RaodTypeId")
    @Expose
    private Integer raodTypeId;
    @SerializedName("Toilet")
    @Expose
    private Integer toilet;
    @SerializedName("RUID")
    @Expose
    private String ruid;
    @SerializedName("GovtSchemeId")
    @Expose
    private Integer govtSchemeId;
    @SerializedName("SubCastId")
    @Expose
    private Integer subCastId;
    @SerializedName("PropertyUseId")
    @Expose
    private Integer propertyUseId;
    @SerializedName("ExemptionCategoryId")
    @Expose
    private Integer exemptionCategoryId;

    public Object getRespCode() {
        return respCode;
    }

    public void setRespCode(Object respCode) {
        this.respCode = respCode;
    }

    public Object getRespMessage() {
        return respMessage;
    }

    public void setRespMessage(Object respMessage) {
        this.respMessage = respMessage;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public String getPropertyNo() {
        return propertyNo;
    }

    public void setPropertyNo(String propertyNo) {
        this.propertyNo = propertyNo;
    }

    public String getOldPropertyNo() {
        return oldPropertyNo;
    }

    public void setOldPropertyNo(String oldPropertyNo) {
        this.oldPropertyNo = oldPropertyNo;
    }

    public String getAssesmentDate() {
        return assesmentDate;
    }

    public void setAssesmentDate(String assesmentDate) {
        this.assesmentDate = assesmentDate;
    }

    public Integer getWardId() {
        return wardId;
    }

    public void setWardId(Integer wardId) {
        this.wardId = wardId;
    }

    public Integer getMuhallaId() {
        return muhallaId;
    }

    public void setMuhallaId(Integer muhallaId) {
        this.muhallaId = muhallaId;
    }

    public Integer getOwnershipId() {
        return ownershipId;
    }

    public void setOwnershipId(Integer ownershipId) {
        this.ownershipId = ownershipId;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getHindiHatherName() {
        return hindiHatherName;
    }

    public void setHindiHatherName(String hindiHatherName) {
        this.hindiHatherName = hindiHatherName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getHindiOwnerName() {
        return hindiOwnerName;
    }

    public void setHindiOwnerName(String hindiOwnerName) {
        this.hindiOwnerName = hindiOwnerName;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getRentAreaSqr() {
        return rentAreaSqr;
    }

    public void setRentAreaSqr(String rentAreaSqr) {
        this.rentAreaSqr = rentAreaSqr;
    }

    public Double getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(Double totalArea) {
        this.totalArea = totalArea;
    }

    public Integer getAreaRateId() {
        return areaRateId;
    }

    public void setAreaRateId(Integer areaRateId) {
        this.areaRateId = areaRateId;
    }

    public Integer getRoadWidthId() {
        return roadWidthId;
    }

    public void setRoadWidthId(Integer roadWidthId) {
        this.roadWidthId = roadWidthId;
    }

    public Integer getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(Integer constructionYear) {
        this.constructionYear = constructionYear;
    }

    public Double getTotalOwnArea() {
        return totalOwnArea;
    }

    public void setTotalOwnArea(Double totalOwnArea) {
        this.totalOwnArea = totalOwnArea;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getNoofRoom() {
        return noofRoom;
    }

    public void setNoofRoom(Integer noofRoom) {
        this.noofRoom = noofRoom;
    }

    public String getNoofShop() {
        return noofShop;
    }

    public void setNoofShop(String noofShop) {
        this.noofShop = noofShop;
    }

    public Double getRoadFit() {
        return roadFit;
    }

    public void setRoadFit(Double roadFit) {
        this.roadFit = roadFit;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsWConnection() {
        return isWConnection;
    }

    public void setIsWConnection(Integer isWConnection) {
        this.isWConnection = isWConnection;
    }

    public String getGridNo() {
        return gridNo;
    }

    public void setGridNo(String gridNo) {
        this.gridNo = gridNo;
    }

    public Integer getGaliNo() {
        return galiNo;
    }

    public void setGaliNo(Integer galiNo) {
        this.galiNo = galiNo;
    }

    public Integer getIsTaxPay() {
        return isTaxPay;
    }

    public void setIsTaxPay(Integer isTaxPay) {
        this.isTaxPay = isTaxPay;
    }

    public Integer getRashanCardTypeId() {
        return rashanCardTypeId;
    }

    public void setRashanCardTypeId(Integer rashanCardTypeId) {
        this.rashanCardTypeId = rashanCardTypeId;
    }

    public Integer getNoofMember() {
        return noofMember;
    }

    public void setNoofMember(Integer noofMember) {
        this.noofMember = noofMember;
    }

    public Integer getRelegionId() {
        return relegionId;
    }

    public void setRelegionId(Integer relegionId) {
        this.relegionId = relegionId;
    }

    public Integer getCasteId() {
        return casteId;
    }

    public void setCasteId(Integer casteId) {
        this.casteId = casteId;
    }

    public Integer getRegistrationTypeId() {
        return registrationTypeId;
    }

    public void setRegistrationTypeId(Integer registrationTypeId) {
        this.registrationTypeId = registrationTypeId;
    }

    public Integer getRaodTypeId() {
        return raodTypeId;
    }

    public void setRaodTypeId(Integer raodTypeId) {
        this.raodTypeId = raodTypeId;
    }

    public Integer getToilet() {
        return toilet;
    }

    public void setToilet(Integer toilet) {
        this.toilet = toilet;
    }

    public String getRuid() {
        return ruid;
    }

    public void setRuid(String ruid) {
        this.ruid = ruid;
    }

    public Integer getGovtSchemeId() {
        return govtSchemeId;
    }

    public void setGovtSchemeId(Integer govtSchemeId) {
        this.govtSchemeId = govtSchemeId;
    }

    public Integer getSubCastId() {
        return subCastId;
    }

    public void setSubCastId(Integer subCastId) {
        this.subCastId = subCastId;
    }

    public Integer getPropertyUseId() {
        return propertyUseId;
    }

    public void setPropertyUseId(Integer propertyUseId) {
        this.propertyUseId = propertyUseId;
    }

    public Integer getExemptionCategoryId() {
        return exemptionCategoryId;
    }

    public void setExemptionCategoryId(Integer exemptionCategoryId) {
        this.exemptionCategoryId = exemptionCategoryId;
    }
}
