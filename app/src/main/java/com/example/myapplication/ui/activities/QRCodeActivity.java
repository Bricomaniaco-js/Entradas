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

public class QRCodeActivity extends AppCompatActivity {
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