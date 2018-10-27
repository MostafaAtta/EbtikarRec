package com.atta.ebtikarrec.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.atta.ebtikarrec.details.DetailsActivity;
import com.atta.ebtikarrec.QRScannerActivity;
import com.atta.ebtikarrec.R;
import com.atta.ebtikarrec.model.MyRecyclerViewAdapter;
import com.atta.ebtikarrec.model.Student;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener, View.OnClickListener, MainContract.View {


    MyRecyclerViewAdapter adapter;

    ImageView qrButton;

    LinearLayout linearLayout;

    RecyclerView recyclerView;

    ArrayList<Student> studentsList;

    MainPresenter mainPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mainPresenter = new MainPresenter(this, this);

        qrButton = findViewById(R.id.qr_scan);
        qrButton.setOnClickListener(this);

        studentsList = new ArrayList<>();

        Bundle dataFromIntent = getIntent().getExtras();


        linearLayout = findViewById(R.id.lin);

        // set up the RecyclerView
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, studentsList, mainPresenter);
        adapter.setClickListener(this);


        if (dataFromIntent != null) {

            if(dataFromIntent.getString("students").equals("You cancelled the scanning")){
                showMessage(dataFromIntent.getString("students"));
            }else{

                jsonPhrasing(dataFromIntent.getString("students"));
            }

        }

    }

    @Override
    public void showMessage(String message) {


        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void jsonPhrasing(String students) {
        try {
            JSONArray jsonArray = new JSONArray(students);
            for (int i=0; i<jsonArray.length();i++){
                JSONObject currentStudent = jsonArray.getJSONObject(i);

                int id = currentStudent.getInt("id");
                String name = currentStudent.getString("name");
                String imageUrl = currentStudent.getString("img_url");

                Student student = new Student(id, name, imageUrl);

                studentsList.add(student);
            }
            linearLayout.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            recyclerView.setAdapter(adapter);



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showStudent(int id) {

        Bundle bundle = new Bundle();
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        bundle.putInt("id", id);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onItemClick(View view, int position) {

        showMessage(String.valueOf(position));

        showStudent(position);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, QRScannerActivity.class);
        startActivity(intent);
    }
}
