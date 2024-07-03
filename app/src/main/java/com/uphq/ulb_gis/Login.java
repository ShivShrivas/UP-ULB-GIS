package com.uphq.ulb_gis;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.JsonObject;
import com.uphq.ulb_gis.models.LoginResponse;
import com.uphq.ulb_gis.retrofirClient.ApiClient;
import com.uphq.ulb_gis.retrofirClient.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    private String schemeId;
    private String schemeName;
    private Button btnLogin;
    private EditText etPassword;
    private EditText etUserName;
    private CustomProgress customProgress;

    private boolean isPasswordVisible = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        customProgress = CustomProgress.getInstance();
       // spinnerImage = findViewById(R.id.spinner_imagetype);
        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        etPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    int drawableEnd = etPassword.getRight() - etPassword.getCompoundDrawables()[2].getBounds().width();
                    if (motionEvent.getRawX() >= drawableEnd) {
                        togglePasswordVisibility();
                        return true;
                    }
                }
                return false;
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dataValidate()) {
                    getLoginResult();
                }
            }
        });
    }

    private void getLoginResult() {

        customProgress.showProgress(Login.this,"Logging in...",false);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
     //   Log.d("TAG", "getLoginResult: "+getLoginJsonObj());
        Call<LoginResponse> call = apiService.getLoginResponse(getLoginJsonObj());
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.d("TAG", "onResponse: "+response.body().getRespMessage());
                if (response.isSuccessful() && response.body().getRespCode().equals("101")){

                    //MySharedPreferences.saveLoginObject(getApplicationContext(), response.body());
                    customProgress.hideProgress();
                    Intent intent=new Intent(Login.this,MasterFormActivity.class);
                    intent.putExtra("OfficeId",response.body().getOfficeId().toString());
                    intent.putExtra("UserId",response.body().getUserId().toString());
                    intent.putExtra("propertyId","");


                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(Login.this, "Please enter correct username and password", Toast.LENGTH_SHORT).show();
                    customProgress.hideProgress();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(Login.this, "Please enter correct username and password", Toast.LENGTH_SHORT).show();
                customProgress.hideProgress();
            }
        });

    }
    private JsonObject getLoginJsonObj() {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("ApiUserName", "GISUSER");
        jsonObject.addProperty("Token", "12345");
        jsonObject.addProperty("username", etUserName.getText().toString().trim());
        jsonObject.addProperty("Password", etPassword.getText().toString().trim());
        return jsonObject;
    }
    private void togglePasswordVisibility() {
        if (isPasswordVisible) {
            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            etPassword.setCompoundDrawablesWithIntrinsicBounds(null, null,
                    ContextCompat.getDrawable(this, R.drawable.eye_close_ic), null);
        } else {
            etPassword.setTransformationMethod(null);
            etPassword.setCompoundDrawablesWithIntrinsicBounds(null, null,
                    ContextCompat.getDrawable(this, R.drawable.eye_open_ic), null);
        }
        isPasswordVisible = !isPasswordVisible;
    }
    private boolean dataValidate() {
        if (etUserName.getText().toString().trim().length() < 3) {
            etUserName.setError("Please enter username");
            Toast.makeText(this, "Please enter username", Toast.LENGTH_SHORT).show();
            return false;
        } else if (etPassword.getText().toString().trim().length() < 3) {
            etPassword.setError("Please enter password");
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}