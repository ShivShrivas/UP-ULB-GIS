package com.uphq.ulb_gis.retrofirClient;


import com.google.gson.JsonObject;
import com.uphq.ulb_gis.models.AllSpinnerDataModel;
import com.uphq.ulb_gis.models.LoginResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {

    @POST("gis/login")
    Call<LoginResponse> getLoginResponse(@Body JsonObject jsonObject);


    @POST("gis/MasterData")
    Call<AllSpinnerDataModel> getSpinnerMasterData(@Body JsonObject jsonObject);
    @POST("gis/MasterDataById")
    Call<AllSpinnerDataModel> getSpinnerMasterDataById(@Body JsonObject jsonObject);

    @Multipart
    @POST("gis/insertdata")
    Call<JsonObject> insertData(
            @Part MultipartBody.Part file,
            @Part("PropertyId") RequestBody propertyId,
            @Part("OfficeId") RequestBody officeId,
            @Part("PropertyNo") RequestBody propertyNo,
            @Part("OldPropertyNo") RequestBody oldPropertyNo,
            @Part("AssesmentDate") RequestBody assessmentDate,
            @Part("WardId") RequestBody wardId,
            @Part("MuhallaId") RequestBody muhallaId,
            @Part("OwnershipId") RequestBody ownershipId,
            @Part("FatherName") RequestBody fatherName,
            @Part("HindiFatherName") RequestBody hindiFatherName,
            @Part("OwnerName") RequestBody ownerName,
            @Part("HindiOwnerName") RequestBody hindiOwnerName,
            @Part("NewOwnerName") RequestBody newOwnerName,
            @Part("NewOwnerFatherName") RequestBody newOwnerFatherName,
            @Part("HouseNo") RequestBody houseNo,
            @Part("Address") RequestBody address,
            @Part("MobileNo") RequestBody mobileNo,
            @Part("RentAreaSqr") RequestBody rentAreaSqr,
            @Part("TotalArea") RequestBody totalArea,
            @Part("AreaRateId") RequestBody areaRateId,
            @Part("RoadWidthId") RequestBody roadWidthId,
            @Part("ConstructionYear") RequestBody constructionYear,
            @Part("TotalOwnArea") RequestBody totalOwnArea,
            @Part("TypeId") RequestBody typeId,
            @Part("Floor") RequestBody floor,
            @Part("NoofRoom") RequestBody noofRoom,
            @Part("NoofShop") RequestBody noofShop,
            @Part("RoadFit") RequestBody roadFit,
            @Part("Remark") RequestBody remark,
            @Part("IsWConnection") RequestBody isWConnection,
            @Part("GridNo") RequestBody gridNo,
            @Part("GaliNo") RequestBody galiNo,
            @Part("IsTaxPay") RequestBody isTaxPay,
            @Part("RashanCardTypeId") RequestBody rashanCardTypeId,
            @Part("NoofMember") RequestBody noofMember,
            @Part("RelegionId") RequestBody religionId,
            @Part("CasteId") RequestBody casteId,
            @Part("RegistrationTypeId") RequestBody registrationTypeId,
            @Part("RaodTypeId") RequestBody roadTypeId,
            @Part("UID") RequestBody uid,
            @Part("AssessedTax") RequestBody assessedTax,
            @Part("ModifiedTax") RequestBody modifiedTax,
            @Part("Toilet") RequestBody toilet,
            @Part("RUID") RequestBody ruid,
            @Part("Latitute") RequestBody latitude,
            @Part("Longitute") RequestBody longitude,
            @Part("DeviceId") RequestBody deviceId,
            @Part("ProcId") RequestBody procId,
            @Part("GovtSchemeId") RequestBody govtSchemeId,
            @Part("ExemptionCategoryId") RequestBody ExemptionCategoryId,
            @Part("PropertyUseId") RequestBody PropertyUseId,
            @Part("SubCastId") RequestBody SubCastId,
            @Part("PropertySubType") RequestBody PropertySubType,
            @Part("DOB") RequestBody DOB
    );


}
