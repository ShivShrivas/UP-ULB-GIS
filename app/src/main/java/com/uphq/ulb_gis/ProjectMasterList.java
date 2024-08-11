package com.uphq.ulb_gis;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.uphq.ulb_gis.adapters.MasterListAdapter;
import com.uphq.ulb_gis.adapters.MasterSpinnerAdapter;
import com.uphq.ulb_gis.models.AllSpinnerDataModel;
import com.uphq.ulb_gis.models.MasterListResponse;
import com.uphq.ulb_gis.models.SpinnerData;
import com.uphq.ulb_gis.retrofirClient.ApiClient;
import com.uphq.ulb_gis.retrofirClient.ApiInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectMasterList extends AppCompatActivity {

    RecyclerView recView_MasterProjectList;
    MasterListAdapter masterListAdapter;
    String oficeId,userid;
    TextView tv_norecord;
    Spinner spin_wardNo;
    private Toolbar toolbar;
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_master_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Project List");
        actionBar.setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#FF7F50")));
        if (actionBar != null ) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        spin_wardNo=findViewById(R.id.spin_wardNo);
        oficeId=getIntent().getStringExtra("OfficeId");
        userid=getIntent().getStringExtra("UserId");
        getSpinnerData(14,spin_wardNo,"WardNumbers");
        spin_wardNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SpinnerData spinnerData=(SpinnerData)adapterView.getSelectedItem();
                fetchMasterListdata(spinnerData.getMasterId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        recView_MasterProjectList=findViewById(R.id.recView_MasterProjectList);
        tv_norecord=findViewById(R.id.tv_norecord);
        tv_norecord.setVisibility(View.GONE);
        recView_MasterProjectList.setLayoutManager(new LinearLayoutManager(this));


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Handle the back button click here
            // Navigate back or perform a specific action
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void fetchMasterListdata(int wardId) {
        CustomProgress customProgress1=new CustomProgress();
        customProgress1.showProgress(ProjectMasterList.this,"Data Fetching Please Wait...",false);
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("ApiUserName", "GISUSER");
        jsonObject.addProperty("Token", "12345");
        jsonObject.addProperty("WardId", wardId);
        jsonObject.addProperty("OfficeId", oficeId);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        //  Log.d("TAG", "getLoginResult: "+getLoginJsonObj());
        Call<MasterListResponse> call = apiService.getMasterListData(jsonObject);
        call.enqueue(new Callback<MasterListResponse>() {
            @Override
            public void onResponse(Call<MasterListResponse> call, Response<MasterListResponse> response) {
                Log.d("TAG", "onResponse: "+new Gson().toJson(response.body()));
                if (response.isSuccessful() && response.code()==200){
                    MasterListResponse masterListResponse=response.body();
                    if (masterListResponse!=null && masterListResponse.getRespCode().equals("101")){
                        masterListAdapter=new MasterListAdapter(ProjectMasterList.this,masterListResponse.getData(),userid,oficeId);
                        recView_MasterProjectList.setAdapter(masterListAdapter);
                        tv_norecord.setVisibility(View.GONE);
                        recView_MasterProjectList.setVisibility(View.VISIBLE);
                        customProgress1.hideProgress();

                    }else {
                        tv_norecord.setVisibility(View.VISIBLE);
                        recView_MasterProjectList.setVisibility(View.GONE);

                        customProgress1.hideProgress();
                    }
                }else {
                    tv_norecord.setVisibility(View.VISIBLE);
                    recView_MasterProjectList.setVisibility(View.GONE);

                    customProgress1.hideProgress();
                }
            }

            @Override
            public void onFailure(Call<MasterListResponse> call, Throwable t) {
                customProgress1.hideProgress();
                tv_norecord.setVisibility(View.VISIBLE);
                recView_MasterProjectList.setVisibility(View.GONE);

            }
        });
    }
    private void getSpinnerData(int procId, Spinner spinner, String name) {

        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("ApiUserName", "GISUSER");
        jsonObject.addProperty("Token", "12345");
        jsonObject.addProperty("Procid", procId);
        jsonObject.addProperty("Id", oficeId);

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
                    spinner.setAdapter(new MasterSpinnerAdapter(ProjectMasterList.this,arrayList));


                }else{


                }
            }

            @Override
            public void onFailure(Call<AllSpinnerDataModel> call, Throwable t) {


            }
        });
    }

}