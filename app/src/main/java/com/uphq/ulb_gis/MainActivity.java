package com.uphq.ulb_gis;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String[] PERMISSIONS = {
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.CAMERA
    };
    private ImageView image_data;
    private ImageView image_data1;

    private final ActivityResultLauncher<String[]> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), this::onPermissionsResult);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


//        Glide.with(this).load(R.drawable.gps).into(image_data);
//        Glide.with(this).load(R.drawable.ofaf).into(image_data1);

        if (!checkPermissions()) {
            requestPermissions();
        } else {
            new Handler().postDelayed(() -> {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }, 4000);
        }
    }

    private boolean checkPermissions() {
        for (String permission : PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    private void requestPermissions() {
        requestPermissionLauncher.launch(PERMISSIONS);
    }

    private void showPermissionAlertDialog() {
        new AlertDialog.Builder(this)
                .setMessage("This app requires all permissions to function properly. Please grant all permissions to continue.")
                .setCancelable(false)
                .setPositiveButton("OK", (dialog, which) -> finish())
                .create()
                .show();
    }

    private void onPermissionsResult(@NonNull Map<String, Boolean> permissions) {
        boolean allPermissionsGranted = true;
        for (boolean granted : permissions.values()) {
            if (!granted) {
                allPermissionsGranted = false;
                break;
            }
        }
        if (allPermissionsGranted) {
            new Handler().postDelayed(() -> {
                startActivity(new Intent(this, Login.class));
                finish();
            }, 4000);
        } else {
            showPermissionAlertDialog();
        }
    }
}