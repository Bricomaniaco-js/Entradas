package com.example.myapplication.ui.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

/**
 * Activity for displaying a QR code.
 */
public class QRCodeActivity extends AppCompatActivity {

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Otherwise it is null.
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);

        ImageView qrCodeImageView = findViewById(R.id.qrCodeImageView);
        String qrContent = getIntent().getStringExtra("QR_CONTENT");

        int screenWidth = getResources().getDisplayMetrics().widthPixels;

        Bitmap qrCodeBitmap = generateQRCode(qrContent, screenWidth);
        qrCodeImageView.setImageBitmap(qrCodeBitmap);
    }

    /**
     * Generates a QR code bitmap from the given text.
     *
     * @param text The text to encode in the QR code.
     * @param size The size of the QR code.
     * @return The generated QR code bitmap.
     */
    private Bitmap generateQRCode(String text, int size) {
        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        Bitmap bitmap = null;
        try {
            BitMatrix bitMatrix = barcodeEncoder.encode(text, BarcodeFormat.QR_CODE, size, size);
            bitmap = barcodeEncoder.createBitmap(bitMatrix);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}