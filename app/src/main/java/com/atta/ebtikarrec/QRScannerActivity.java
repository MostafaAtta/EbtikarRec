package com.atta.ebtikarrec;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.atta.ebtikarrec.main.MainActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QRScannerActivity extends AppCompatActivity {


    public String students;
    public final Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);
        final Activity activity = this;
        IntentIntegrator integrator = new IntentIntegrator(activity);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scan");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents()== null){
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(QRScannerActivity.this,MainActivity.class);
                bundle.putString("students","You cancelled the scanning");
                intent.putExtras(bundle);
                startActivity(intent);
            }
            else {
                students = result.getContents();
                //Toast.makeText(this, payment, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(QRScannerActivity.this,MainActivity.class);
                bundle.putString("students", students);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
