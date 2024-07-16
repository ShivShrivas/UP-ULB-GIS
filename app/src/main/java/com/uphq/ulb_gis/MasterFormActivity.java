package com.uphq.ulb_gis;

import android.animation.ObjectAnimator;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.uphq.ulb_gis.Utils.CompressLib.Compressor;
import com.uphq.ulb_gis.Utils.DateTextWatcher;
import com.uphq.ulb_gis.Utils.GpsTracker;
import com.uphq.ulb_gis.Utils.ImageDialogFragment;
import com.uphq.ulb_gis.adapters.MasterSpinnerAdapter;
import com.uphq.ulb_gis.models.AllSpinnerDataModel;
import com.uphq.ulb_gis.models.PropertywiseDataModel;
import com.uphq.ulb_gis.models.SpinnerData;
import com.uphq.ulb_gis.retrofirClient.ApiClient;
import com.uphq.ulb_gis.retrofirClient.ApiInterface;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MasterFormActivity extends AppCompatActivity {
    ScrollView scrollView;
    private EditText et_OwnerName;
    private EditText et_ownerFather;
    private Spinner spin_Gender;
    private EditText et_dob;
    private EditText et_AssesmentDate;
    private EditText et_mob;
    private EditText et_propertyNumber;
    private EditText et_oldPropertyNumber;
    private Spinner spin_business;
    private Spinner spin_widthOfRoad;
    private EditText et_numberOfamMem;
    private Spinner spin_typeOfRoad;

    private Spinner spin_ownership;
    private Spinner spin_numberOfBase;
    private Spinner spin_ctegoryOftaxRelaxation;
    private Spinner spin_useOfProperty;
    private EditText etNumberOfRoom;
    private EditText etNumberOfShop;
    private Spinner spin_isTaxPaying;
    private Spinner spin_itWaterConnection;
    private Spinner spin_PMHouseScheme;
    private Spinner spin_isToilet;
    private Spinner spin_rationcard;
    private Spinner spin_casteCategory;
    private Spinner spin_subCaste;
    private EditText etComment;
    private EditText et_others;
    private Spinner spin_wardNo;
    private Spinner spin_registratioType;
    private EditText et_nameOfWard;
    private Spinner spin_gridNumber;
    private EditText etMuhallaName;
    private EditText etLongitude;
    private EditText etLatitude;
    private Spinner spin_religion;


    private EditText etTypeOfRoad;
    private Spinner spin_categoryOfPrperty;
    private Spinner spin_SubCategoryOfPrperty;
    private Spinner spin_builtyear;
    private EditText etAreaOfProperty;
    private EditText etAreaOfPlot;
    private EditText etAreaOfCovered;
    private EditText etCarpetArea;
    private EditText etLivingArea;
    private EditText etBusinessArea;
    private EditText et_galiNumber;
    private EditText et_HouseNumber;
    private EditText etRentArea;
    private EditText et_NewOwnerName;
    private EditText et_NewOwnerFatherName;

    LinearLayout llBeforeWorkImage;
    ImageView iv_capturedImage;

    ArrayList<SpinnerData> genderList;
    CustomProgress customProgress;
    private Calendar calendar;
    GpsTracker gpsTracker;
    String lattitude,longitude;
    String photoPath="";

    FloatingActionButton fb_masterlist;
    String oficeId,userid,propertyId_fromList;

    LinearLayout ll_location,ll_imageCpture;
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_master_form);
        propertyId_fromList=getIntent().getStringExtra("propertyId");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Master Form");
        actionBar.setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#FF7F50")));
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.progress_dialog);
        dialog.setCancelable(false);
        dialog.show();

        // Hide the dialog after 3 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 3000); // 3 seconds
         if (actionBar != null && !propertyId_fromList.equals("")) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }else{
             actionBar.setDisplayHomeAsUpEnabled(false);
         }
        gpsTracker=new GpsTracker(MasterFormActivity.this);
        scrollView = findViewById(R.id.scrollView2);
        lattitude=String.valueOf(gpsTracker.getLatitude());
        longitude=String.valueOf(gpsTracker.getLongitude());
        oficeId=getIntent().getStringExtra("OfficeId");
        userid=getIntent().getStringExtra("UserId");

        calendar = Calendar.getInstance();
        et_NewOwnerName = findViewById(R.id.et_NewOwnerName);
        et_NewOwnerFatherName = findViewById(R.id.et_NewOwnerFatherName);
        llBeforeWorkImage = findViewById(R.id.llBeforeWorkImage);
        iv_capturedImage = findViewById(R.id.iv_capturedImage);
        et_ownerFather = findViewById(R.id.et_ownerFather);
        et_OwnerName = findViewById(R.id.et_OwnerName);
        spin_Gender = findViewById(R.id.spin_Gender);
        et_dob = findViewById(R.id.et_dob);
        et_AssesmentDate = findViewById(R.id.ev_AssesmentDate);
        et_mob = findViewById(R.id.et_mob);
        et_galiNumber = findViewById(R.id.et_galiNumber);
        et_HouseNumber = findViewById(R.id.et_HouseNumber);
        et_propertyNumber = findViewById(R.id.et_propertyNumber);
        et_oldPropertyNumber = findViewById(R.id.et_oldPropertyNumber);
        spin_business = findViewById(R.id.spin_business);
        et_numberOfamMem = findViewById(R.id.et_numberOfamMem);
        spin_typeOfRoad = findViewById(R.id.spin_typeOfRoad);
        spin_widthOfRoad = findViewById(R.id.spin_widthOfRoad);
         spin_categoryOfPrperty = findViewById(R.id.spin_categoryOfPrperty);
        spin_SubCategoryOfPrperty = findViewById(R.id.spin_SubCategoryOfPrperty);
        spin_builtyear = findViewById(R.id.spin_builtyear);
        etAreaOfProperty = findViewById(R.id.et_areOfProperty);
        etAreaOfPlot = findViewById(R.id.et_areaOfPlot);
        etAreaOfCovered = findViewById(R.id.et_areaOfCovered);
        etCarpetArea = findViewById(R.id.et_carpetArea);
        etLivingArea = findViewById(R.id.et_livingArea);
        etBusinessArea = findViewById(R.id.et_bussinessArea);
        etRentArea = findViewById(R.id.et_rentArea);
        fb_masterlist = findViewById(R.id.fb_masterlist);
        ll_location = findViewById(R.id.ll_location);
        ll_imageCpture = findViewById(R.id.ll_imageCpture);

        spin_ownership = findViewById(R.id.spin_ownership);
        spin_numberOfBase = findViewById(R.id.spin_numberOfBase);
        spin_ctegoryOftaxRelaxation = findViewById(R.id.spin_ctegoryOftaxRelaxation);
        spin_useOfProperty = findViewById(R.id.spin_useOfProperty);
        etNumberOfRoom = findViewById(R.id.et_nnumberOfRoom);
        etNumberOfShop = findViewById(R.id.et_NumberofShop);
        spin_isTaxPaying = findViewById(R.id.spin_isTaxPaying);
        spin_itWaterConnection = findViewById(R.id.spin_itWaterConnection);
        spin_PMHouseScheme = findViewById(R.id.spin_PMHouseScheme);
        spin_isToilet = findViewById(R.id.spin_isToilet);
        spin_rationcard = findViewById(R.id.spin_rationcard);
        spin_casteCategory = findViewById(R.id.spin_casteCategory);
        spin_subCaste = findViewById(R.id.spin_subCaste);
        etComment = findViewById(R.id.et_Comment);
        et_others = findViewById(R.id.et_others);
        spin_wardNo = findViewById(R.id.spin_wardNo);
        spin_registratioType = findViewById(R.id.spin_registratioType);
        et_nameOfWard = findViewById(R.id.et_nameOfWard);
        spin_gridNumber = findViewById(R.id.spin_gridNumber);
        etMuhallaName = findViewById(R.id.et_MuhallaName);
        etLongitude = findViewById(R.id.et_logitude);
        etLatitude = findViewById(R.id.et_lattitude);
        spin_religion = findViewById(R.id.spin_religion);
        if (!propertyId_fromList.equals("")){
            ll_location.setVisibility(View.GONE);
            ll_imageCpture.setVisibility(View.GONE);
            fb_masterlist.setVisibility(View.GONE);
        }

        customProgress=new CustomProgress();
        customProgress.showProgress(MasterFormActivity.this,"Data Fetcing...\nPlease wait...",false);

        getSpinnerData(5,spin_Gender,"Genders");
        getSpinnerData(7,spin_business,"Business");
        getSpinnerData(15,spin_widthOfRoad,"Widths of road");
        getSpinnerData(14,spin_typeOfRoad,"Types of road");
        getSpinnerData(18,spin_categoryOfPrperty,"Categories of Property");
        getSpinnerData(19,spin_builtyear,"Years of building");
        getSpinnerData(8,spin_ownership,"Ownerships");
        getSpinnerData(20,spin_numberOfBase,"Number Of Bases");
        getSpinnerData(21,spin_ctegoryOftaxRelaxation,"Tax Relaxations");
        getSpinnerData(22,spin_useOfProperty,"Uses of property");
        getSpinnerData(23,spin_isTaxPaying,"Is tax paying");
        getSpinnerData(24,spin_itWaterConnection,"Water Connection");
        getSpinnerData(17,spin_PMHouseScheme,"Prime minister House Scheme");
        getSpinnerData(25,spin_isToilet,"Toilet numbers");
        getSpinnerData(25,spin_rationcard,"Ration card");
        getSpinnerData(3,spin_casteCategory,"Caste category");
        //getSpinnerData(27,spin_subCaste,"Sub-Caste category");
        getSpinnerData(16,spin_wardNo,"WardNumbers");
        getSpinnerData(13,spin_religion,"Religions");
        getSpinnerData(28,spin_gridNumber,"Grid Numbers");
        getSpinnerData(11,spin_registratioType,"Registration Type");
        etLatitude.setText(lattitude);
        etLatitude.setEnabled(false);
        etLongitude.setText(longitude);
        etLongitude.setEnabled(false);
        customProgress.hideProgress();
        if (!propertyId_fromList.equals("")){
            fetchDataFromServer();
        }
        fb_masterlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MasterFormActivity.this,ProjectMasterList.class);
                intent.putExtra("OfficeId",oficeId);
                intent.putExtra("UserId",userid);
                startActivity(intent);
            }
        });

        et_dob.addTextChangedListener(new DateTextWatcher(et_dob));
       et_AssesmentDate.addTextChangedListener(new DateTextWatcher(et_AssesmentDate));

//        et_dob.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showDatePickerDialog(1);
//            }
//        });

//        tv_AssesmentDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showDatePickerDialog(2);
//            }
//        });
        llBeforeWorkImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent(1111);
            }
        });
        iv_capturedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageDialogFragment dialogFragment = ImageDialogFragment.newInstance(photoPath);
                dialogFragment.show(getSupportFragmentManager(), "ImageDialogFragment");
            }
        });
        spin_casteCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerData spinnerData=(SpinnerData) parent.getSelectedItem();
                Log.d("TAG",spinnerData.getMasterId().toString());
                getSpinnerDataById(12,spin_subCaste,"Sub Caste",spinnerData.getMasterId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spin_categoryOfPrperty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerData spinnerData=(SpinnerData) parent.getSelectedItem();
                Log.d("TAG",spinnerData.getMasterId().toString());
                getSpinnerDataById(11,spin_SubCategoryOfPrperty,"Sub Property",spinnerData.getMasterId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateEditText(et_OwnerName) &&
                        validateSpinner(spin_Gender) &&
                        validateEditTextDate(et_dob) &&
                        validateEditText(et_ownerFather) &&
                        //validateEditText(et_NewOwnerName) &&
                        //validateEditText(et_NewOwnerFatherName) &&
                        validateEditText(et_mob) &&
                        validateEditText(et_propertyNumber) &&
                        //validateEditText(et_oldPropertyNumber) &&
                        validateSpinner(spin_business) &&
                        validateEditText(et_numberOfamMem) &&
                        validateSpinner(spin_typeOfRoad) &&
                        validateSpinner(spin_widthOfRoad) &&
                        validateSpinner(spin_categoryOfPrperty) &&
                        validateSpinner(spin_SubCategoryOfPrperty) &&
                        validateSpinner(spin_builtyear) &&
                        validateEditText(etAreaOfProperty) &&
                        validateEditText(etAreaOfPlot) &&
                        validateEditText(etAreaOfCovered) &&
                        validateEditText(etCarpetArea)&&
                        validateEditText(etLivingArea)&&
                        validateEditText(etBusinessArea)&&
                        validateEditText(etRentArea)&&
                        //validateSpinner(spin_ownership) &&
                        validateSpinner(spin_numberOfBase) &&
                        validateSpinner(spin_ctegoryOftaxRelaxation) &&
                        validateSpinner(spin_useOfProperty) &&
                        validateEditText(etNumberOfRoom)&&
                        validateEditText(etNumberOfShop)&&
                        validateSpinner(spin_isTaxPaying) &&
                        validateSpinner(spin_itWaterConnection) &&
                        validateSpinner(spin_PMHouseScheme) &&
                        validateSpinner(spin_isToilet) &&
                        validateSpinner(spin_rationcard) &&
                        validateSpinner(spin_registratioType) &&
                        validateSpinner(spin_casteCategory) &&
                        validateSpinner(spin_subCaste) &&
                        validateEditText(etComment)&&
                        validateEditText(et_others)&&

                        validateEditText(et_nameOfWard)&&
                        validateSpinner(spin_wardNo) &&
                        validateSpinner(spin_gridNumber)
                        //validateEditText(et_HouseNumber) &&
                        //validateEditText(et_galiNumber) &&
                       // validateEditText(etMuhallaName)&&


                       // validateSpinner(spin_religion) &&
                       // validateEditTextDate(et_AssesmentDate)








                )



                {

                        if (propertyId_fromList.equals("")){
                            if (photoPath.isEmpty() && photoPath.length()<=1){
                                Toast.makeText(MasterFormActivity.this, "Please capture image...", Toast.LENGTH_SHORT).show();
                            }else {
                                submitData();
                            }
                        }else {
                            updateData();

                        }

                    // All fields are valid, proceed with submission
                    //submitForm();


                } else {
                    // Validation failed, show error or alert
                    Toast.makeText(MasterFormActivity.this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void updateData() {
        CustomProgress customProgress1=new CustomProgress();
        customProgress1.showProgress(MasterFormActivity.this,"Data Updating Please Wait...",false);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        //  Log.d("TAG", "getLoginResult: "+getLoginJsonObj());
        Call<JsonObject> call = apiService.updateData(getJsonObject());
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful() && response.body().get("respCode").equals("101")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MasterFormActivity.this);
                    builder.setTitle("Submit Response")
                            .setMessage("("+response.body().get("respMessage").getAsString()+") "+response.body().get("respCode"))
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    dialog.dismiss();
                                    finish();
                                }
                            })
                            .setCancelable(false) // Prevent dismissing dialog by clicking outside or pressing back button
                            .show();
                } else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(MasterFormActivity.this);
                    builder.setTitle("Submit Response")
                            .setMessage("("+response.body().get("respMessage").getAsString()+") "+response.body().get("respCode"))
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    dialog.dismiss();
                                    finish();
                                }
                            })
                            .setCancelable(false) // Prevent dismissing dialog by clicking outside or pressing back button
                            .show();
                }
                customProgress1.hideProgress();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MasterFormActivity.this);
                builder.setTitle("Submit Response")
                        .setMessage("("+t.getMessage()+") ")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        })
                        .setCancelable(false) // Prevent dismissing dialog by clicking outside or pressing back button
                        .show();
                customProgress1.hideProgress();

            }
        });
    }

    private void fetchDataFromServer() {
        CustomProgress customProgress1=new CustomProgress();
        customProgress1.showProgress(MasterFormActivity.this,"Data Fetching Please Wait...",false);
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("ApiUserName", "GISUSER");
        jsonObject.addProperty("Token", "12345");
        jsonObject.addProperty("PropertyId", propertyId_fromList);
        Log.d("TAG", "fetchDataFromServer: "+jsonObject);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        //  Log.d("TAG", "getLoginResult: "+getLoginJsonObj());
        Call<PropertywiseDataModel> call = apiService.getPropertyListData(jsonObject);
        call.enqueue(new Callback<PropertywiseDataModel>() {
            @Override
            public void onResponse(Call<PropertywiseDataModel> call, Response<PropertywiseDataModel> response) {
                Log.d("TAG", "onResponse: "+new Gson().toJson(response.body()));
                if (response.isSuccessful() && response.code()==200){
                    PropertywiseDataModel propertywiseDataModel=response.body();
                    customProgress1.hideProgress();
                    assert propertywiseDataModel != null;
                    setDataoView(propertywiseDataModel);

                }else{
                    customProgress1.hideProgress();
                }
            }

            @Override
            public void onFailure(Call<PropertywiseDataModel> call, Throwable t) {
                customProgress1.hideProgress();
            }
        });
    }

    private void setDataoView(PropertywiseDataModel propertywiseDataModel) {
       CustomProgress  customProgress1=new  CustomProgress();
       customProgress1.showProgress(MasterFormActivity.this,"fetching data\nPlease  wait...",false);
        et_OwnerName.setText(propertywiseDataModel.getOwnerName());
        et_ownerFather.setText(propertywiseDataModel.getFatherName());
        //et_dob.setText(propertywiseDataModel.getDob());
        et_AssesmentDate.setText(propertywiseDataModel.getAssesmentDate());
        et_mob.setText(propertywiseDataModel.getMobileNo());

        et_propertyNumber.setText(propertywiseDataModel.getPropertyNo());
        et_oldPropertyNumber.setText(propertywiseDataModel.getOldPropertyNo());
        et_numberOfamMem.setText(String.valueOf(propertywiseDataModel.getNoofMember()));

        //et_nameOfWard.setText(propertywiseDataModel.getWardId());
        etMuhallaName.setText(String.valueOf(propertywiseDataModel.getMuhallaId()));

        etAreaOfPlot.setText(String.valueOf(propertywiseDataModel.getTotalArea()));
        etCarpetArea.setText(String.valueOf(propertywiseDataModel.getAreaRateId()));
        etAreaOfCovered.setText(String.valueOf(propertywiseDataModel.getTotalOwnArea()));
        etNumberOfRoom.setText(String.valueOf(propertywiseDataModel.getNoofRoom()));
        etNumberOfShop.setText(String.valueOf(propertywiseDataModel.getNoofShop()));
         et_galiNumber.setText(String.valueOf(propertywiseDataModel.getGaliNo()));
        et_HouseNumber.setText(String.valueOf(propertywiseDataModel.getHouseNo()));
        etComment.setText(propertywiseDataModel.getRemark());
        et_galiNumber.setText(String.valueOf(propertywiseDataModel.getGaliNo()));
        et_dob.setText(String.valueOf(propertywiseDataModel.getDob()));
        et_propertyNumber.setText(propertywiseDataModel.getPropertyNo());
        etAreaOfProperty.setText(String.valueOf(propertywiseDataModel.getTotalPropertyArea()));
        etRentArea.setText(String.valueOf(propertywiseDataModel.getRentAreaSqr()));
        etBusinessArea.setText(String.valueOf(propertywiseDataModel.getCommercialArrear()));
        etLivingArea.setText(String.valueOf(propertywiseDataModel.getResidentialArrear()));


       // et_others.setText(propertywiseDataModel.get());

        et_nameOfWard.setText(String.valueOf(propertywiseDataModel.getWardId()));

        setSpinnerSelection(spin_typeOfRoad,propertywiseDataModel.getRaodTypeId());
        setSpinnerSelection(spin_wardNo,propertywiseDataModel.getWardId());
        setSpinnerSelection(spin_widthOfRoad,propertywiseDataModel.getRoadWidthId());
        setSpinnerSelection(spin_builtyear,propertywiseDataModel.getConstructionYear());
        setSpinnerSelection(spin_rationcard,propertywiseDataModel.getRashanCardTypeId());
        setSpinnerSelection(spin_registratioType,propertywiseDataModel.getRegistrationTypeId());
        setSpinnerSelection(spin_casteCategory,propertywiseDataModel.getCasteId());
        setSpinnerSelection(spin_itWaterConnection,propertywiseDataModel.getIsWConnection());
        setSpinnerSelection(spin_isTaxPaying,propertywiseDataModel.getIsTaxPay());
        setSpinnerSelection(spin_isToilet,propertywiseDataModel.getToilet());
        setSpinnerSelection(spin_PMHouseScheme,propertywiseDataModel.getGovtSchemeId());
        setSpinnerSelection(spin_gridNumber,propertywiseDataModel.getGaliNo());
        setSpinnerSelection(spin_categoryOfPrperty,propertywiseDataModel.getTypeId());
        setSpinnerSelection(spin_SubCategoryOfPrperty,propertywiseDataModel.getPropertySubType());
        setSpinnerSelection(spin_numberOfBase,propertywiseDataModel.getFloor());
        setSpinnerSelection(spin_useOfProperty,propertywiseDataModel.getMuhallaId());
        setSpinnerSelection(spin_useOfProperty,propertywiseDataModel.getMuhallaId());
        setSpinnerSelection(spin_subCaste,propertywiseDataModel.getSubCastId());
        setSpinnerSelection(spin_gridNumber,propertywiseDataModel.getGaliNo());
        setSpinnerSelection(spin_Gender,propertywiseDataModel.getGenderId());

        customProgress1.hideProgress();
        //spin_Gender.setSelection(propertywiseDataModel.getGenderId());
        //spin_business.setSelection(propertywiseDataModel.getBusinessId());



    }
    private void setSpinnerSelection(Spinner spinner, int itemToFind) {
        int position = findPositionInSpinner(spinner, itemToFind);
        if (position >= 0) {
            spinner.setSelection(position);
        } else {
            Toast.makeText(this, "Item '" + itemToFind + "' not found in Spinner", Toast.LENGTH_SHORT).show();
        }
    }


    private int findPositionInSpinner(Spinner spinner, int item) {
        int count = spinner.getCount();  // Get the number of items in the Spinner
        for (int i = 0; i < count; i++) {
            SpinnerData currentItem = (SpinnerData) spinner.getItemAtPosition(i);  // Get item at the current position
            if (currentItem.getMasterId()==item) {
                return i;  // Return the position if the item matches
            }
        }
        return -1;  // Item not found
    }
    private boolean validateSpinner(Spinner spinner) {
       if (((SpinnerData) spinner.getSelectedItem()).getMasterId()==0){
           spinner.setBackground(ContextCompat.getDrawable(this, R.drawable.border_bg_red));
           focusOnView(spinner);
           return false;
       }
        spinner.setBackground(ContextCompat.getDrawable(this, R.drawable.border_bg));

           return true;

    }

    private void focusOnView(View view) {
        view.requestFocus();
        scrollView.post(() -> {
            scrollView.smoothScrollTo(0, view.getTop());

            // Add highlighting animation
            ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", 0.3f, 1f);
            animator.setDuration(900);
            animator.start();
        });
    }
    private boolean validateEditText(EditText editText) {
        String text = editText.getText().toString().trim();
        if (text.isEmpty()) {
            editText.setError("इसे भरना आवश्यक है");
            focusOnView(editText);
            return false;
        }else{
            return true;
        }

    }

    private boolean validateEditTextDate(EditText editText) {
        String text = editText.getText().toString().trim();
        if (text.length() != 10) {
            editText.setError("इसे भरना आवश्यक है");
            focusOnView(editText);
            return false;
        }else{
            return true;
        }

    }

    private boolean validateTextView(TextView editText) {
        String text = editText.getText().toString().trim();
        if (text.isEmpty()) {
            editText.setError("इसे भरना आवश्यक है");
            focusOnView(editText);
            return false;
        }else{
            return true;
        }

    }
    private void dispatchTakePictureIntent(int reqCode) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                ex.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.uphq.ulb_gis.provider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, reqCode
                );
            }
        }
    }
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
            photoPath=image.getAbsolutePath();
            //   Log.d("TAG", "createImageFileBefore work new: "+ImageUtils.getFileSizeInKB(ImageUtils.compressImage(image.getAbsolutePath()).getAbsolutePath()));

        return image;
    }
    private void showDatePickerDialog(int type) {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel(type);
        };

        new DatePickerDialog(MasterFormActivity.this, dateSetListener,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateLabel(int type) {
        String dateFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.getDefault());
        if (type==1){

            et_dob.setText(sdf.format(calendar.getTime()));
            et_dob.setError(null);
        }else{
            et_AssesmentDate.setText(sdf.format(calendar.getTime()));
            et_AssesmentDate.setError(null);
        }
    }
    private void getSpinnerData(int procId,Spinner spinner,String name) {

        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("ApiUserName", "GISUSER");
        jsonObject.addProperty("Token", "12345");
        jsonObject.addProperty("Procid", procId);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        //  Log.d("TAG", "getLoginResult: "+getLoginJsonObj());
        Call<AllSpinnerDataModel> call = apiService.getSpinnerMasterData(jsonObject);
        call.enqueue(new Callback<AllSpinnerDataModel>() {
            @Override
            public void onResponse(Call<AllSpinnerDataModel> call, Response<AllSpinnerDataModel> response) {

                if (response.isSuccessful()){
                    AllSpinnerDataModel allSpinnerDataModel=response.body();
                    ArrayList<SpinnerData> arrayList=allSpinnerDataModel.getData();
                    Collections.sort(arrayList, new Comparator<SpinnerData>() {
                        @Override
                        public int compare(SpinnerData m1, SpinnerData m2) {
                            return Integer.compare(m1.getMasterId(), m2.getMasterId());
                        }
                    });
                    spinner.setAdapter(new MasterSpinnerAdapter(MasterFormActivity.this,arrayList));


                }else{


                }
            }

            @Override
            public void onFailure(Call<AllSpinnerDataModel> call, Throwable t) {


            }
        });
    }

  private void getSpinnerDataById(int procId,Spinner spinner,String name,int id) {

        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("ApiUserName", "GISUSER");
        jsonObject.addProperty("Token", "12345");
        jsonObject.addProperty("Procid", procId);
        jsonObject.addProperty("Id", id);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        //  Log.d("TAG", "getLoginResult: "+getLoginJsonObj());
        Call<AllSpinnerDataModel> call = apiService.getSpinnerMasterDataById(jsonObject);
        call.enqueue(new Callback<AllSpinnerDataModel>() {
            @Override
            public void onResponse(Call<AllSpinnerDataModel> call, Response<AllSpinnerDataModel> response) {

                if (response.isSuccessful()){
                    AllSpinnerDataModel allSpinnerDataModel=response.body();
                    ArrayList<SpinnerData> arrayList=allSpinnerDataModel.getData();
                    Collections.sort(arrayList, new Comparator<SpinnerData>() {
                        @Override
                        public int compare(SpinnerData m1, SpinnerData m2) {
                            return Integer.compare(m1.getMasterId(), m2.getMasterId());
                        }
                    });
                    spinner.setAdapter(new MasterSpinnerAdapter(MasterFormActivity.this,arrayList));


                }else{


                }
            }

            @Override
            public void onFailure(Call<AllSpinnerDataModel> call, Throwable t) {


            }
        });
    }
JsonObject getJsonObject(){
        JsonObject jsonObject=new JsonObject();
    jsonObject.addProperty("ApiUserName", "GISUSER");
    jsonObject.addProperty("Token", "1234");
    jsonObject.addProperty("PropertyId", propertyId_fromList);  // Replace with actual propertyId_fromList
    jsonObject.addProperty("OfficeId", oficeId);
    jsonObject.addProperty("PropertyNo", "");
    jsonObject.addProperty("OldPropertyNo", "");
    jsonObject.addProperty("AssesmentDate", "");
    jsonObject.addProperty("WardId", ((SpinnerData) spin_wardNo.getSelectedItem()).getMasterId().toString());
    jsonObject.addProperty("MuhallaId", etMuhallaName.getText().toString());
    jsonObject.addProperty("OwnershipId", ((SpinnerData) spin_ownership.getSelectedItem()).getMasterId().toString());
    jsonObject.addProperty("FatherName", et_ownerFather.getText().toString());
    jsonObject.addProperty("HindiHatherName", et_ownerFather.getText().toString());
    jsonObject.addProperty("OwnerName", et_OwnerName.getText().toString());
    jsonObject.addProperty("HindiOwnerName", et_OwnerName.getText().toString());
    jsonObject.addProperty("NewOwnerName", et_NewOwnerName.getText().toString());
    jsonObject.addProperty("NewOwnerFatherName", et_NewOwnerFatherName.getText().toString());
    jsonObject.addProperty("HouseNo", et_propertyNumber.getText().toString());
    jsonObject.addProperty("Address", "");
    jsonObject.addProperty("MobileNo", et_mob.getText().toString());
    jsonObject.addProperty("RentAreaSqr", etRentArea.getText().toString());
    jsonObject.addProperty("TotalArea", etAreaOfPlot.getText().toString());
    jsonObject.addProperty("AreaRateId", etCarpetArea.getText().toString());
    jsonObject.addProperty("RoadWidthId", ((SpinnerData) spin_widthOfRoad.getSelectedItem()).getMasterId().toString());
    jsonObject.addProperty("ConstructionYear", ((SpinnerData) spin_builtyear.getSelectedItem()).getMasterId().toString());
    jsonObject.addProperty("TotalOwnArea", etAreaOfCovered.getText().toString());
    jsonObject.addProperty("TypeId", ((SpinnerData) spin_categoryOfPrperty.getSelectedItem()).getMasterId().toString());
    jsonObject.addProperty("Floor", ((SpinnerData) spin_numberOfBase.getSelectedItem()).getMasterId().toString());
    jsonObject.addProperty("NoofRoom", etNumberOfRoom.getText().toString());
    jsonObject.addProperty("NoofShop", etNumberOfShop.getText().toString());
    jsonObject.addProperty("RoadFit", ((SpinnerData) spin_typeOfRoad.getSelectedItem()).getMasterName());
    jsonObject.addProperty("Remark", etComment.getText().toString());
    jsonObject.addProperty("IsWConnection", ((SpinnerData) spin_itWaterConnection.getSelectedItem()).getMasterId().toString());
    jsonObject.addProperty("GridNo", ((SpinnerData) spin_gridNumber.getSelectedItem()).getMasterId().toString());
    jsonObject.addProperty("GaliNo", et_galiNumber.getText().toString());
    jsonObject.addProperty("IsTaxPay", ((SpinnerData) spin_isTaxPaying.getSelectedItem()).getMasterId().toString());
    jsonObject.addProperty("RashanCardTypeId", ((SpinnerData) spin_rationcard.getSelectedItem()).getMasterId().toString());
    jsonObject.addProperty("NoofMember", et_numberOfamMem.getText().toString());
    jsonObject.addProperty("RelegionId", "");
    jsonObject.addProperty("CasteId", ((SpinnerData) spin_casteCategory.getSelectedItem()).getMasterId().toString());
    jsonObject.addProperty("RegistrationTypeId", ((SpinnerData) spin_registratioType.getSelectedItem()).getMasterId().toString());
    jsonObject.addProperty("RaodTypeId", ((SpinnerData) spin_typeOfRoad.getSelectedItem()).getMasterId().toString());
    jsonObject.addProperty("UID", "");
    jsonObject.addProperty("AssessedTax", "");
    jsonObject.addProperty("ModifiedTax", "");
    jsonObject.addProperty("Toilet", ((SpinnerData) spin_isToilet.getSelectedItem()).getMasterId().toString());
    jsonObject.addProperty("RUID", "");
    jsonObject.addProperty("Latitude", lattitude);
    jsonObject.addProperty("Longitude", longitude);
    jsonObject.addProperty("DeviceId", "");
    jsonObject.addProperty("ProcId", "1");
    jsonObject.addProperty("GovtSchemeId", ((SpinnerData) spin_PMHouseScheme.getSelectedItem()).getMasterId().toString());
    jsonObject.addProperty("SubCastId", ((SpinnerData) spin_subCaste.getSelectedItem()).getMasterId().toString());
    jsonObject.addProperty("GenderId", ((SpinnerData) spin_Gender.getSelectedItem()).getMasterId().toString());
    jsonObject.addProperty("OccupationId", ((SpinnerData) spin_business.getSelectedItem()).getMasterId().toString());
    jsonObject.addProperty("ExemptionCategoryId", ((SpinnerData) spin_ctegoryOftaxRelaxation.getSelectedItem()).getMasterId().toString());
    jsonObject.addProperty("PropertyUseId", ((SpinnerData) spin_useOfProperty.getSelectedItem()).getMasterId().toString());
    jsonObject.addProperty("CommercialArrear", etBusinessArea.getText().toString());
    jsonObject.addProperty("ResidentialArrear", etLivingArea.getText().toString());
    jsonObject.addProperty("TotalPropertyArea", etAreaOfProperty.getText().toString());
    jsonObject.addProperty("OtherRemark", et_others.getText().toString());
    jsonObject.addProperty("WardName", et_nameOfWard.getText().toString());
    return jsonObject;
}


  public void submitData(){
        CustomProgress customProgress1=new CustomProgress();
      customProgress1.showProgress(MasterFormActivity.this,"Data Submitting Please Wait...",false);

      File file = new File(photoPath);
      File tempCroppedImage = new Compressor.Builder(MasterFormActivity.this)
              .setMaxWidth(720)
              .setMaxHeight(720)
              .setQuality(75)
              .setCompressFormat(Bitmap.CompressFormat.JPEG)
              .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                      Environment.DIRECTORY_PICTURES).getAbsolutePath())
              .build()
              .compressToFile(file);
      RequestBody requestFile = RequestBody.create(MediaType.parse("image/png"), tempCroppedImage);

      // MultipartBody.Part is used to send also the actual file name
      MultipartBody.Part body = MultipartBody.Part.createFormData("PhotoPath", tempCroppedImage.getName(), requestFile);

      // Add other parameters
      RequestBody officeIdPart = RequestBody.create(MultipartBody.FORM, oficeId);
      RequestBody propertyNo = RequestBody.create(MultipartBody.FORM, et_propertyNumber.getText().toString());
      RequestBody oldPropertyNo = RequestBody.create(MultipartBody.FORM, "");//et_oldPropertyNumber.getText().toString());
      RequestBody assessmentDate = RequestBody.create(MultipartBody.FORM,"");// et_AssesmentDate.getText().toString());
      RequestBody wardId = RequestBody.create(MultipartBody.FORM, ((SpinnerData) spin_wardNo.getSelectedItem()).getMasterId().toString());
      RequestBody muhallaId = RequestBody.create(MultipartBody.FORM, "");//etMuhallaName.getText().toString());
      RequestBody ownershipId = RequestBody.create(MultipartBody.FORM, "");//((SpinnerData) spin_ownership.getSelectedItem()).getMasterId().toString());
      RequestBody fatherName = RequestBody.create(MultipartBody.FORM, et_ownerFather.getText().toString());
      RequestBody hindiFatherName = RequestBody.create(MultipartBody.FORM, et_ownerFather.getText().toString());
      RequestBody ownerName = RequestBody.create(MultipartBody.FORM, et_OwnerName.getText().toString());
      RequestBody hindiOwnerName = RequestBody.create(MultipartBody.FORM, et_OwnerName.getText().toString());
      RequestBody newOwnerName = RequestBody.create(MultipartBody.FORM, et_OwnerName.getText().toString());
      RequestBody newOwnerFatherName = RequestBody.create(MultipartBody.FORM, et_ownerFather.getText().toString());
      RequestBody houseNo = RequestBody.create(MultipartBody.FORM, "");//et_HouseNumber.getText().toString());
      RequestBody address = RequestBody.create(MultipartBody.FORM, "");
      RequestBody mobileNo = RequestBody.create(MultipartBody.FORM, et_mob.getText().toString());
      RequestBody rentAreaSqr = RequestBody.create(MultipartBody.FORM, etRentArea.getText().toString());
      RequestBody totalArea = RequestBody.create(MultipartBody.FORM, etAreaOfPlot.getText().toString());
      RequestBody areaRateId = RequestBody.create(MultipartBody.FORM, etCarpetArea.getText().toString());
      RequestBody roadWidthId = RequestBody.create(MultipartBody.FORM, ((SpinnerData) spin_widthOfRoad.getSelectedItem()).getMasterId().toString());
      RequestBody constructionYear = RequestBody.create(MultipartBody.FORM, ((SpinnerData) spin_builtyear.getSelectedItem()).getMasterId().toString());
      RequestBody totalOwnArea = RequestBody.create(MultipartBody.FORM, etAreaOfCovered.getText().toString());
      RequestBody typeId = RequestBody.create(MultipartBody.FORM, ((SpinnerData) spin_categoryOfPrperty.getSelectedItem()).getMasterId().toString());
      RequestBody floor = RequestBody.create(MultipartBody.FORM, ((SpinnerData) spin_numberOfBase.getSelectedItem()).getMasterId().toString());
      RequestBody noofRoom = RequestBody.create(MultipartBody.FORM, etNumberOfRoom.getText().toString());
      RequestBody noofShop = RequestBody.create(MultipartBody.FORM, etNumberOfShop.getText().toString());
      RequestBody roadFit = RequestBody.create(MultipartBody.FORM, ((SpinnerData) spin_typeOfRoad.getSelectedItem()).getMasterName());
      RequestBody remark = RequestBody.create(MultipartBody.FORM, etComment.getText().toString());
      RequestBody isWConnection = RequestBody.create(MultipartBody.FORM, ((SpinnerData) spin_itWaterConnection.getSelectedItem()).getMasterId().toString());
      RequestBody gridNo = RequestBody.create(MultipartBody.FORM, ((SpinnerData) spin_gridNumber.getSelectedItem()).getMasterId().toString());
      RequestBody galiNo = RequestBody.create(MultipartBody.FORM, "");//et_galiNumber.getText().toString());
      RequestBody DOB = RequestBody.create(MultipartBody.FORM, et_dob.getText().toString());
      RequestBody isTaxPay = RequestBody.create(MultipartBody.FORM, ((SpinnerData) spin_isTaxPaying.getSelectedItem()).getMasterId().toString());
      RequestBody rashanCardTypeId = RequestBody.create(MultipartBody.FORM, ((SpinnerData) spin_rationcard.getSelectedItem()).getMasterId().toString());
      RequestBody noofMember = RequestBody.create(MultipartBody.FORM, et_numberOfamMem.getText().toString());
      RequestBody religionId = RequestBody.create(MultipartBody.FORM, "");//((SpinnerData) spin_religion.getSelectedItem()).getMasterId().toString());
      RequestBody casteId = RequestBody.create(MultipartBody.FORM, ((SpinnerData) spin_casteCategory.getSelectedItem()).getMasterId().toString());
      RequestBody registrationTypeId = RequestBody.create(MultipartBody.FORM, ((SpinnerData) spin_registratioType.getSelectedItem()).getMasterId().toString());
      RequestBody roadTypeId = RequestBody.create(MultipartBody.FORM, ((SpinnerData) spin_typeOfRoad.getSelectedItem()).getMasterId().toString());
      RequestBody uid = RequestBody.create(MultipartBody.FORM, userid);
      RequestBody assessedTax = RequestBody.create(MultipartBody.FORM, "");
      RequestBody exemptionCategoryId = RequestBody.create(MultipartBody.FORM, ((SpinnerData) spin_ctegoryOftaxRelaxation.getSelectedItem()).getMasterId().toString());
      RequestBody modifiedTax = RequestBody.create(MultipartBody.FORM, "");
      RequestBody toilet = RequestBody.create(MultipartBody.FORM, ((SpinnerData) spin_isToilet.getSelectedItem()).getMasterId().toString());
      RequestBody ruid = RequestBody.create(MultipartBody.FORM, "");
      RequestBody latitudePart = RequestBody.create(MultipartBody.FORM, lattitude);
      RequestBody longitudePart = RequestBody.create(MultipartBody.FORM, longitude);
      RequestBody deviceId = RequestBody.create(MultipartBody.FORM, "");
      RequestBody procId = RequestBody.create(MultipartBody.FORM, "1");
      RequestBody govtSchemeId = RequestBody.create(MultipartBody.FORM, ((SpinnerData) spin_PMHouseScheme.getSelectedItem()).getMasterId().toString());
      RequestBody propertyUseId = RequestBody.create(MultipartBody.FORM, ((SpinnerData) spin_useOfProperty.getSelectedItem()).getMasterId().toString());
      RequestBody OccupationId = RequestBody.create(MultipartBody.FORM, ((SpinnerData) spin_business.getSelectedItem()).getMasterId().toString());
      RequestBody SubCastId = RequestBody.create(MultipartBody.FORM, ((SpinnerData) spin_subCaste.getSelectedItem()).getMasterId().toString());
      RequestBody GenderId  = RequestBody.create(MultipartBody.FORM, ((SpinnerData) spin_Gender.getSelectedItem()).getMasterId().toString());
      RequestBody PropertySubType = RequestBody.create(MultipartBody.FORM, ((SpinnerData) spin_SubCategoryOfPrperty.getSelectedItem()).getMasterId().toString());
      RequestBody ResidentialArrear = RequestBody.create(MultipartBody.FORM,  etLivingArea.getText().toString());
      RequestBody CommercialArrear = RequestBody.create(MultipartBody.FORM,  etBusinessArea.getText().toString());
      RequestBody TotalPropertyArea = RequestBody.create(MultipartBody.FORM,  etAreaOfProperty.getText().toString());
      RequestBody OtherRemark = RequestBody.create(MultipartBody.FORM,  et_others.getText().toString());
      RequestBody WardName = RequestBody.create(MultipartBody.FORM,  et_nameOfWard.getText().toString());

      ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
      RequestBody propertyId = RequestBody.create(MultipartBody.FORM, "0");

      Call<JsonObject> call = apiService.insertData(
            body,
            propertyId, officeIdPart, propertyNo, oldPropertyNo, assessmentDate, wardId, muhallaId,
            ownershipId, fatherName, hindiFatherName, ownerName, hindiOwnerName, newOwnerName,
            newOwnerFatherName, houseNo, address, mobileNo, rentAreaSqr, totalArea, areaRateId,
            roadWidthId, constructionYear, totalOwnArea, typeId, floor, noofRoom, noofShop,
            roadFit, remark, isWConnection, gridNo, galiNo, isTaxPay, rashanCardTypeId, noofMember,
            religionId, casteId, registrationTypeId, roadTypeId, uid, assessedTax, modifiedTax,
            toilet, ruid, latitudePart, longitudePart, deviceId, procId, govtSchemeId,exemptionCategoryId,propertyUseId,SubCastId
              ,PropertySubType,DOB,ResidentialArrear,CommercialArrear,GenderId,OccupationId,TotalPropertyArea,OtherRemark,WardName
      );
      call.enqueue(new Callback<JsonObject>() {
          @Override
          public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
              if (response.isSuccessful() && response.body().get("respCode").equals("101")) {
                  AlertDialog.Builder builder = new AlertDialog.Builder(MasterFormActivity.this);
                  builder.setTitle("Submit Response")
                          .setMessage("("+response.body().get("respMessage").getAsString()+") "+response.body().get("respCode").getAsInt())
                          .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                              @Override
                              public void onClick(DialogInterface dialog, int which) {

                                  dialog.dismiss();
                              }
                          })
                          .setCancelable(false) // Prevent dismissing dialog by clicking outside or pressing back button
                          .show();
              } else {

                  AlertDialog.Builder builder = new AlertDialog.Builder(MasterFormActivity.this);
                  builder.setTitle("Submit Response")
                          .setMessage("("+response.body().get("respMessage").getAsString()+") "+response.body().get("respCode").getAsString())
                          .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                              @Override
                              public void onClick(DialogInterface dialog, int which) {

                                  dialog.dismiss();
                              }
                          })
                          .setCancelable(false) // Prevent dismissing dialog by clicking outside or pressing back button
                          .show();
              }
              customProgress1.hideProgress();
          }

          @Override
          public void onFailure(Call<JsonObject> call, Throwable t) {

              AlertDialog.Builder builder = new AlertDialog.Builder(MasterFormActivity.this);
              builder.setTitle("Submit Response")
                      .setMessage("("+t.getMessage()+") ")
                      .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialog, int which) {

                              dialog.dismiss();
                          }
                      })
                      .setCancelable(false) // Prevent dismissing dialog by clicking outside or pressing back button
                      .show();
              customProgress1.hideProgress();

          }
      });
  }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
       super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1111 && resultCode == RESULT_OK) {

            File imgFile = new File(photoPath);

            if (imgFile.exists()) {
                // Load the image from the specified path
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                // Set the loaded image to the ImageView
                iv_capturedImage.setImageBitmap(myBitmap);
                iv_capturedImage.setVisibility(View.VISIBLE);
            }
            Toast.makeText(this, "Image saved to: " + photoPath, Toast.LENGTH_SHORT).show();
        }
    }
}

