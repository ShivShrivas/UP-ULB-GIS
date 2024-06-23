package com.uphq.ulb_gis;

import android.animation.ObjectAnimator;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.QuickContactBadge;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.JsonObject;
import com.uphq.ulb_gis.Utils.GpsTracker;
import com.uphq.ulb_gis.Utils.ImageDialogFragment;
import com.uphq.ulb_gis.Utils.ImageUtils;
import com.uphq.ulb_gis.adapters.MasterSpinnerAdapter;
import com.uphq.ulb_gis.models.AllSpinnerDataModel;
import com.uphq.ulb_gis.models.LoginResponse;
import com.uphq.ulb_gis.models.SpinnerData;
import com.uphq.ulb_gis.retrofirClient.ApiClient;
import com.uphq.ulb_gis.retrofirClient.ApiInterface;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    private TextView tv_dob;
    private EditText et_mob;
    private EditText et_propertyNumber;
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
    private Spinner spin_builtyear;
    private EditText etAreaOfProperty;
    private EditText etAreaOfPlot;
    private EditText etAreaOfCovered;
    private EditText etCarpetArea;
    private EditText etLivingArea;
    private EditText etBusinessArea;
    private EditText et_galiNumber;
    private EditText etRentArea;

    LinearLayout llBeforeWorkImage;
    ImageView iv_capturedImage;

    ArrayList<SpinnerData> genderList;
    CustomProgress customProgress;
    private Calendar calendar;
    GpsTracker gpsTracker;
    String lattitude,longitude;
    String photoPath="";

    String oficeId,userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_master_form);
        gpsTracker=new GpsTracker(MasterFormActivity.this);
        scrollView = findViewById(R.id.scrollView2);
        lattitude=String.valueOf(gpsTracker.getLatitude());
        longitude=String.valueOf(gpsTracker.getLongitude());
        oficeId=getIntent().getStringExtra("OfficeId");
        userid=getIntent().getStringExtra("UserId");
        calendar = Calendar.getInstance();
        llBeforeWorkImage = findViewById(R.id.llBeforeWorkImage);
        iv_capturedImage = findViewById(R.id.iv_capturedImage);
        et_ownerFather = findViewById(R.id.et_ownerFather);
        et_OwnerName = findViewById(R.id.et_OwnerName);
        spin_Gender = findViewById(R.id.spin_Gender);
        tv_dob = findViewById(R.id.tv_dob);
        et_mob = findViewById(R.id.et_mob);
        et_galiNumber = findViewById(R.id.et_galiNumber);
        et_propertyNumber = findViewById(R.id.et_propertyNumber);
        spin_business = findViewById(R.id.spin_business);
        et_numberOfamMem = findViewById(R.id.et_numberOfamMem);
        spin_typeOfRoad = findViewById(R.id.spin_typeOfRoad);
        spin_widthOfRoad = findViewById(R.id.spin_widthOfRoad);
        spin_typeOfRoad = findViewById(R.id.spin_typeOfRoad);
        spin_categoryOfPrperty = findViewById(R.id.spin_categoryOfPrperty);
        spin_builtyear = findViewById(R.id.spin_builtyear);
        etAreaOfProperty = findViewById(R.id.et_areOfProperty);
        etAreaOfPlot = findViewById(R.id.et_areaOfPlot);
        etAreaOfCovered = findViewById(R.id.et_areaOfCovered);
        etCarpetArea = findViewById(R.id.et_carpetArea);
        etLivingArea = findViewById(R.id.et_livingArea);
        etBusinessArea = findViewById(R.id.et_bussinessArea);
        etRentArea = findViewById(R.id.et_rentArea);

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
        getSpinnerData(27,spin_subCaste,"Sub-Caste category");
        getSpinnerData(16,spin_wardNo,"WardNumbers");
        getSpinnerData(13,spin_religion,"Religions");
        getSpinnerData(28,spin_gridNumber,"Grid Numbers");
        getSpinnerData(11,spin_registratioType,"Registration Type");
        etLatitude.setText(lattitude);
        etLatitude.setEnabled(false);
        etLongitude.setText(longitude);
        etLongitude.setEnabled(false);
        customProgress.hideProgress();

        tv_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
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
        findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateEditText(et_OwnerName) &&
                        validateSpinner(spin_Gender) &&
                        validateTextView(tv_dob) &&
                        validateEditText(et_ownerFather) &&
                        validateEditText(et_mob) &&
                        validateEditText(et_propertyNumber) &&
                        validateSpinner(spin_business) &&
                        validateEditText(et_numberOfamMem) &&
                        validateSpinner(spin_typeOfRoad) &&
                        validateSpinner(spin_widthOfRoad) &&
                        validateSpinner(spin_categoryOfPrperty) &&
                        validateSpinner(spin_builtyear) &&
                        validateEditText(etAreaOfProperty) &&
                        validateEditText(etAreaOfPlot) &&
                        validateEditText(etAreaOfCovered) &&
                        validateEditText(etCarpetArea)&&
                        validateEditText(etLivingArea)&&
                        validateEditText(etBusinessArea)&&
                        validateEditText(etRentArea)&&
                        validateSpinner(spin_ownership) &&
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
                        validateSpinner(spin_gridNumber) &&
                        validateEditText(et_galiNumber) &&
                        validateEditText(etMuhallaName)&&


                        validateSpinner(spin_religion)








                )



                {
                    if (photoPath.isEmpty() && photoPath.length()<=1){
                        Toast.makeText(MasterFormActivity.this, "Please capture image...", Toast.LENGTH_SHORT).show();
                    }else{
                        submitData();
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
    private void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        };

        new DatePickerDialog(MasterFormActivity.this, dateSetListener,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateLabel() {
        String dateFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.getDefault());
        tv_dob.setText(sdf.format(calendar.getTime()));
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
                    spinner.setAdapter(new MasterSpinnerAdapter(MasterFormActivity.this,allSpinnerDataModel.getData()));


                }else{


                }
            }

            @Override
            public void onFailure(Call<AllSpinnerDataModel> call, Throwable t) {


            }
        });
    }








  public void submitData(){
        CustomProgress customProgress1=new CustomProgress();
      customProgress1.showProgress(MasterFormActivity.this,"Data Submitting Please Wait...",false);
//      MediaType MEDIA_TYPE = MediaType.parse("multipart/form-data");
//      File file = new File(photoPath);
//      RequestBody requestBody = new MultipartBody.Builder()
//              .setType(MultipartBody.FORM)
//              .addFormDataPart("PropertyId", "0")
//              .addFormDataPart("OfficeId",oficeId)
//              .addFormDataPart("PropertyNo", "")
//              .addFormDataPart("OldPropertyNo", "")
//              .addFormDataPart("AssesmentDate", "")
//              .addFormDataPart("WardId", ((SpinnerData)spin_wardNo.getSelectedItem()).getMasterId().toString())
//              .addFormDataPart("MuhallaId", etMuhallaName.getText().toString())
//              .addFormDataPart("OwnershipId", ((SpinnerData)spin_ownership.getSelectedItem()).getMasterId().toString())
//              .addFormDataPart("FatherName", et_ownerFather.getText().toString())
//              .addFormDataPart("HindiFatherName", et_ownerFather.getText().toString())
//              .addFormDataPart("OwnerName", et_OwnerName.getText().toString())
//              .addFormDataPart("HindiOwnerName", et_OwnerName.getText().toString())
//              .addFormDataPart("NewOwnerName", et_OwnerName.getText().toString())
//              .addFormDataPart("NewOwnerFatherName", et_ownerFather.getText().toString())
//              .addFormDataPart("HouseNo", et_propertyNumber.getText().toString())
//              .addFormDataPart("Address", "")
//              .addFormDataPart("MobileNo", et_mob.getText().toString())
//              .addFormDataPart("RentAreaSqr", etRentArea.getText().toString())
//              .addFormDataPart("TotalArea", etAreaOfPlot.getText().toString())
//              .addFormDataPart("AreaRateId", etCarpetArea.getText().toString())
//              .addFormDataPart("RoadWidthId", ((SpinnerData)spin_widthOfRoad.getSelectedItem()).getMasterId().toString())
//              .addFormDataPart("ConstructionYear", ((SpinnerData)spin_builtyear.getSelectedItem()).getMasterId().toString())
//              .addFormDataPart("TotalOwnArea", etAreaOfCovered.getText().toString())
//              .addFormDataPart("TypeId", ((SpinnerData)spin_categoryOfPrperty.getSelectedItem()).getMasterId().toString())
//              .addFormDataPart("Floor", ((SpinnerData)spin_numberOfBase.getSelectedItem()).getMasterId().toString())
//              .addFormDataPart("NoofRoom", etNumberOfRoom.getText().toString())
//              .addFormDataPart("NoofShop", etNumberOfShop.getText().toString())
//              .addFormDataPart("RoadFit", ((SpinnerData)spin_typeOfRoad.getSelectedItem()).getMasterName())
//              .addFormDataPart("Remark", etComment.getText().toString())
//              .addFormDataPart("IsWConnection", ((SpinnerData)spin_itWaterConnection.getSelectedItem()).getMasterId().toString())
//              .addFormDataPart("GridNo", ((SpinnerData)spin_gridNumber.getSelectedItem()).getMasterId().toString())
//              .addFormDataPart("GaliNo", et_galiNumber.getText().toString())
//              .addFormDataPart("IsTaxPay", ((SpinnerData)spin_isTaxPaying.getSelectedItem()).getMasterId().toString())
//              .addFormDataPart("RashanCardTypeId",((SpinnerData)spin_rationcard.getSelectedItem()).getMasterId().toString())
//              .addFormDataPart("NoofMember", et_numberOfamMem.getText().toString())
//              .addFormDataPart("RelegionId",((SpinnerData)spin_religion.getSelectedItem()).getMasterId().toString())
//              .addFormDataPart("CasteId", ((SpinnerData)spin_casteCategory.getSelectedItem()).getMasterId().toString())
//              .addFormDataPart("RegistrationTypeId", ((SpinnerData)spin_registratioType.getSelectedItem()).getMasterId().toString())
//              .addFormDataPart("RaodTypeId",((SpinnerData)spin_typeOfRoad.getSelectedItem()).getMasterId().toString())
//              .addFormDataPart("UID", userid)
//              .addFormDataPart("AssessedTax", ((SpinnerData)spin_ctegoryOftaxRelaxation.getSelectedItem()).getMasterId().toString())
//              .addFormDataPart("ModifiedTax", "")
//              .addFormDataPart("Toilet", ((SpinnerData)spin_isToilet.getSelectedItem()).getMasterId().toString())
//              .addFormDataPart("RUID", "")
//              .addFormDataPart("PhotoPath", file.getName(), RequestBody.create(MEDIA_TYPE, file))
//              .addFormDataPart("Latitute", lattitude)
//              .addFormDataPart("Longitute", longitude)
//              .addFormDataPart("DeviceId", "")
//              .addFormDataPart("ProcId", "1")
//              .addFormDataPart("GovtSchemeId", ((SpinnerData)spin_PMHouseScheme.getSelectedItem()).getMasterId().toString())
//              .build();

//      ApiInterface apiService =
//              ApiClient.getClient().create(ApiInterface.class);
//      //  Log.d("TAG", "getLoginResult: "+getLoginJsonObj());
//      Call<JsonObject> call = apiService.insertData(requestBody);

      File file = new File(photoPath);
      RequestBody requestFile = RequestBody.create(MediaType.parse("image/png"), file);

      // MultipartBody.Part is used to send also the actual file name
      MultipartBody.Part body = MultipartBody.Part.createFormData("PhotoPath", file.getName(), requestFile);
// Log all data
      Log.d("RequestBody", "PropertyId: " + "0");
      Log.d("RequestBody", "OfficeId: " + oficeId);
      Log.d("RequestBody", "PropertyNo: ");
      Log.d("RequestBody", "OldPropertyNo: ");
      Log.d("RequestBody", "AssesmentDate: ");
      Log.d("RequestBody", "WardId: " + ((SpinnerData) spin_wardNo.getSelectedItem()).getMasterId().toString());
      Log.d("RequestBody", "MuhallaId: " + etMuhallaName.getText().toString());
      Log.d("RequestBody", "OwnershipId: " + ((SpinnerData) spin_ownership.getSelectedItem()).getMasterId().toString());
      Log.d("RequestBody", "FatherName: " + et_ownerFather.getText().toString());
      Log.d("RequestBody", "HindiFatherName: " + et_ownerFather.getText().toString());
      Log.d("RequestBody", "OwnerName: " + et_OwnerName.getText().toString());
      Log.d("RequestBody", "HindiOwnerName: " + et_OwnerName.getText().toString());
      Log.d("RequestBody", "NewOwnerName: " + et_OwnerName.getText().toString());
      Log.d("RequestBody", "NewOwnerFatherName: " + et_ownerFather.getText().toString());
      Log.d("RequestBody", "HouseNo: " + et_propertyNumber.getText().toString());
      Log.d("RequestBody", "Address: ");
      Log.d("RequestBody", "MobileNo: " + et_mob.getText().toString());
      Log.d("RequestBody", "RentAreaSqr: " + etRentArea.getText().toString());
      Log.d("RequestBody", "TotalArea: " + etAreaOfPlot.getText().toString());
      Log.d("RequestBody", "AreaRateId: " + etCarpetArea.getText().toString());
      Log.d("RequestBody", "RoadWidthId: " + ((SpinnerData) spin_widthOfRoad.getSelectedItem()).getMasterId().toString());
      Log.d("RequestBody", "ConstructionYear: " + ((SpinnerData) spin_builtyear.getSelectedItem()).getMasterId().toString());
      Log.d("RequestBody", "TotalOwnArea: " + etAreaOfCovered.getText().toString());
      Log.d("RequestBody", "TypeId: " + ((SpinnerData) spin_categoryOfPrperty.getSelectedItem()).getMasterId().toString());
      Log.d("RequestBody", "Floor: " + ((SpinnerData) spin_numberOfBase.getSelectedItem()).getMasterId().toString());
      Log.d("RequestBody", "NoofRoom: " + etNumberOfRoom.getText().toString());
      Log.d("RequestBody", "NoofShop: " + etNumberOfShop.getText().toString());
      Log.d("RequestBody", "RoadFit: " + ((SpinnerData) spin_typeOfRoad.getSelectedItem()).getMasterName());
      Log.d("RequestBody", "Remark: " + etComment.getText().toString());
      Log.d("RequestBody", "IsWConnection: " + ((SpinnerData) spin_itWaterConnection.getSelectedItem()).getMasterId().toString());
      Log.d("RequestBody", "GridNo: " + ((SpinnerData) spin_gridNumber.getSelectedItem()).getMasterId().toString());
      Log.d("RequestBody", "GaliNo: " + et_galiNumber.getText().toString());
      Log.d("RequestBody", "IsTaxPay: " + ((SpinnerData) spin_isTaxPaying.getSelectedItem()).getMasterId().toString());
      Log.d("RequestBody", "RashanCardTypeId: " + ((SpinnerData) spin_rationcard.getSelectedItem()).getMasterId().toString());
      Log.d("RequestBody", "NoofMember: " + et_numberOfamMem.getText().toString());
      Log.d("RequestBody", "ReligionId: " + ((SpinnerData) spin_religion.getSelectedItem()).getMasterId().toString());
      Log.d("RequestBody", "CasteId: " + ((SpinnerData) spin_casteCategory.getSelectedItem()).getMasterId().toString());
      Log.d("RequestBody", "RegistrationTypeId: " + ((SpinnerData) spin_registratioType.getSelectedItem()).getMasterId().toString());
      Log.d("RequestBody", "RoadTypeId: " + ((SpinnerData) spin_typeOfRoad.getSelectedItem()).getMasterId().toString());
      Log.d("RequestBody", "UID: " + "");
      Log.d("RequestBody", "AssessedTax: " + ((SpinnerData) spin_ctegoryOftaxRelaxation.getSelectedItem()).getMasterId().toString());
      Log.d("RequestBody", "ModifiedTax: ");
      Log.d("RequestBody", "Toilet: " + ((SpinnerData) spin_isToilet.getSelectedItem()).getMasterId().toString());
      Log.d("RequestBody", "RUID: ");
      Log.d("RequestBody", "Latitude: " + lattitude);
      Log.d("RequestBody", "Longitude: " + longitude);
      Log.d("RequestBody", "DeviceId: ");
      Log.d("RequestBody", "ProcId: " + "1");
      Log.d("RequestBody", "GovtSchemeId: " + ((SpinnerData) spin_PMHouseScheme.getSelectedItem()).getMasterId().toString());

      // Add other parameters
      RequestBody propertyId = RequestBody.create(MultipartBody.FORM, "0");
      RequestBody officeIdPart = RequestBody.create(MultipartBody.FORM, oficeId);
      RequestBody propertyNo = RequestBody.create(MultipartBody.FORM, "");
      RequestBody oldPropertyNo = RequestBody.create(MultipartBody.FORM, "");
      RequestBody assessmentDate = RequestBody.create(MultipartBody.FORM, "");
      RequestBody wardId = RequestBody.create(MultipartBody.FORM, ((SpinnerData) spin_wardNo.getSelectedItem()).getMasterId().toString());
      RequestBody muhallaId = RequestBody.create(MultipartBody.FORM, etMuhallaName.getText().toString());
      RequestBody ownershipId = RequestBody.create(MultipartBody.FORM, ((SpinnerData) spin_ownership.getSelectedItem()).getMasterId().toString());
      RequestBody fatherName = RequestBody.create(MultipartBody.FORM, et_ownerFather.getText().toString());
      RequestBody hindiFatherName = RequestBody.create(MultipartBody.FORM, et_ownerFather.getText().toString());
      RequestBody ownerName = RequestBody.create(MultipartBody.FORM, et_OwnerName.getText().toString());
      RequestBody hindiOwnerName = RequestBody.create(MultipartBody.FORM, et_OwnerName.getText().toString());
      RequestBody newOwnerName = RequestBody.create(MultipartBody.FORM, et_OwnerName.getText().toString());
      RequestBody newOwnerFatherName = RequestBody.create(MultipartBody.FORM, et_ownerFather.getText().toString());
      RequestBody houseNo = RequestBody.create(MultipartBody.FORM, et_propertyNumber.getText().toString());
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
      RequestBody galiNo = RequestBody.create(MultipartBody.FORM, et_galiNumber.getText().toString());
      RequestBody isTaxPay = RequestBody.create(MultipartBody.FORM, ((SpinnerData) spin_isTaxPaying.getSelectedItem()).getMasterId().toString());
      RequestBody rashanCardTypeId = RequestBody.create(MultipartBody.FORM, ((SpinnerData) spin_rationcard.getSelectedItem()).getMasterId().toString());
      RequestBody noofMember = RequestBody.create(MultipartBody.FORM, et_numberOfamMem.getText().toString());
      RequestBody religionId = RequestBody.create(MultipartBody.FORM, ((SpinnerData) spin_religion.getSelectedItem()).getMasterId().toString());
      RequestBody casteId = RequestBody.create(MultipartBody.FORM, ((SpinnerData) spin_casteCategory.getSelectedItem()).getMasterId().toString());
      RequestBody registrationTypeId = RequestBody.create(MultipartBody.FORM, ((SpinnerData) spin_registratioType.getSelectedItem()).getMasterId().toString());
      RequestBody roadTypeId = RequestBody.create(MultipartBody.FORM, ((SpinnerData) spin_typeOfRoad.getSelectedItem()).getMasterId().toString());
      RequestBody uid = RequestBody.create(MultipartBody.FORM, userid);
      RequestBody assessedTax = RequestBody.create(MultipartBody.FORM, ((SpinnerData) spin_ctegoryOftaxRelaxation.getSelectedItem()).getMasterId().toString());
      RequestBody modifiedTax = RequestBody.create(MultipartBody.FORM, "");
      RequestBody toilet = RequestBody.create(MultipartBody.FORM, ((SpinnerData) spin_isToilet.getSelectedItem()).getMasterId().toString());
      RequestBody ruid = RequestBody.create(MultipartBody.FORM, "");
      RequestBody latitudePart = RequestBody.create(MultipartBody.FORM, lattitude);
      RequestBody longitudePart = RequestBody.create(MultipartBody.FORM, longitude);
      RequestBody deviceId = RequestBody.create(MultipartBody.FORM, "");
      RequestBody procId = RequestBody.create(MultipartBody.FORM, "1");
      RequestBody govtSchemeId = RequestBody.create(MultipartBody.FORM, ((SpinnerData) spin_PMHouseScheme.getSelectedItem()).getMasterId().toString());

      ApiInterface apiService =
              ApiClient.getClient().create(ApiInterface.class);
      //  Log.d("TAG", "getLoginResult: "+getLoginJsonObj());

      Call<JsonObject> call = apiService.insertData(
              body,
              propertyId, officeIdPart, propertyNo, oldPropertyNo, assessmentDate, wardId, muhallaId,
              ownershipId, fatherName, hindiFatherName, ownerName, hindiOwnerName, newOwnerName,
              newOwnerFatherName, houseNo, address, mobileNo, rentAreaSqr, totalArea, areaRateId,
              roadWidthId, constructionYear, totalOwnArea, typeId, floor, noofRoom, noofShop,
              roadFit, remark, isWConnection, gridNo, galiNo, isTaxPay, rashanCardTypeId, noofMember,
              religionId, casteId, registrationTypeId, roadTypeId, uid, assessedTax, modifiedTax,
              toilet, ruid, latitudePart, longitudePart, deviceId, procId, govtSchemeId
      );

      call.enqueue(new Callback<JsonObject>() {
          @Override
          public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
              if (response.isSuccessful() && response.body().get("respCode").getAsInt()==101) {
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
                          .setMessage("("+response.body().get("respMessage").getAsString()+") "+response.body().get("respCode").getAsInt())
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
