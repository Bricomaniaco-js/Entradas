package com.example.myapplication.ui.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.App;
import com.example.myapplication.db.ApiController;
import com.example.myapplication.model.Ticket;
import com.example.myapplication.model.User;
import com.google.zxing.integration.android.*;

import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.myapplication.R;

/**
 * Activity for admin functionalities, such as ticket Validation.
 */
public class AdminActivity extends AppCompatActivity {
    private ApiController apiController;
    private FrameLayout rootLayout;

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Otherwise it is null.
     */
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

    /**
     * Starts the QR code scanner.
     */
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

    /**
     * Called when an activity you launched exits, giving you the requestCode you started it with, the resultCode it returned, and any additional data from it.
     *
     * @param requestCode The integer request code originally supplied to startActivityForResult(), allowing you to identify who this result came from.
     * @param resultCode The integer result code returned by the child activity through its setResult().
     * @param data An Intent, which can return result data to the caller (various data can be attached to Intent "extras").
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                validateTicket(result.getContents());
            } else {
                rootLayout.setBackgroundColor(Color.WHITE);
            }
        }
    }

    /**
     * Validates the ticket using the QR code content.
     *
     * @param qrContent The content of the scanned QR code.
     */
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