package com.atta.ebtikarrec.details;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;
import com.atta.ebtikarrec.R;

public class DetailsActivity extends AppCompatActivity implements DetailsContract.View {

    int id;

    String phoneNumber = "01018890088";

    DetailsPresenter detailsPresenter;

    private static final int CALL_REQUEST_CODE = 1 ;

    Intent callIntent;
    
    TextView nameText, emailText, phoneText, classText, gpaText, genderText;
    
    NetworkImageView profilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        
        initiateViews();

        detailsPresenter = new DetailsPresenter(this, this);
        Bundle dataFromIntent = getIntent().getExtras();




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                if (ActivityCompat.checkSelfPermission(DetailsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                    requestPermission(Manifest.permission.CALL_PHONE, CALL_REQUEST_CODE);
                    return;
                }
                startActivity(callIntent);
            }
        });


        if (dataFromIntent != null) {
            id = dataFromIntent.getInt("id");

            showStudent(id);
        }else showMessage("no student ID");
    }

    @Override
    public void initiateViews() {

        nameText = findViewById(R.id.student_name);
        emailText = findViewById(R.id.email);
        phoneText = findViewById(R.id.mobile);
        classText = findViewById(R.id.student_class);
        gpaText = findViewById(R.id.gpa);
        genderText = findViewById(R.id.gender);

        profilePic = findViewById(R.id.profile_image);
    }

    @Override
    public void showMessage(String message) {

        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showStudent(int id) {

        detailsPresenter.getStudent(id, nameText, emailText, phoneText, classText, gpaText, genderText, profilePic, detailsPresenter);
        
    }


    protected void requestPermission(String permissionType, int requestCode) {
        int permission = ContextCompat.checkSelfPermission(this,
                permissionType);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{permissionType}, requestCode
            );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CALL_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    startActivity(callIntent);

                }
                break;
        }
    }
}
