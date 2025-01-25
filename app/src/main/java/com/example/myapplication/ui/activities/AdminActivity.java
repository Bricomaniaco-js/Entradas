package com.example.myapplication.ui.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.App;
import com.example.myapplication.bdd.ApiController;
import com.example.myapplication.bdd.ApiServiceManager;
import com.example.myapplication.dtos.UserDTO;
import com.example.myapplication.model.Ticket;
import com.example.myapplication.model.User;
import com.google.zxing.integration.android.*;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.myapplication.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminActivity extends AppCompatActivity {
    private ApiController apiController;
    private FrameLayout rootLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        rootLayout = findViewById(R.id.root_layout);
        Button startQRScannerButton = findViewById(R.id.button_start_qr_scanner);
        startQRScannerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQRScanner();
            }
        });

        apiController = App.getInstance().getController();
    }

    private void startQRScanner() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        integrator.setPrompt("Scan a QR code");
        integrator.setCameraId(1);
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(true);

        integrator.setOrientationLocked(true);
        integrator.setCaptureActivity(CustomCaptureActivity.class);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                validateTicket(result.getContents());
            } else {
                // Handle cancel
            }
        }
    }

    private void validateTicket(String qrContent) {
        User user = App.getInstance().getCurrentUser();
        apiController.validateTicket(user, qrContent, new ApiController.TicketCallback() {
            @Override
            public void onSuccess(Ticket ticket) {
                rootLayout.setBackgroundColor(Color.GREEN);
            }

            @Override
            public void onFailure(Throwable t) {
                rootLayout.setBackgroundColor(Color.RED);
            }
        });
    }
}