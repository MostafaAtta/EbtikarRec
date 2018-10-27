package com.atta.ebtikarrec.details;

import android.content.Context;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.atta.ebtikarrec.LoadImage;
import com.atta.ebtikarrec.model.APIUrl;
import com.atta.ebtikarrec.model.Student;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetailsPresenter implements DetailsContract.Presenter {


    private DetailsContract.View mView;

    private Context mContext;


    Student student = null ;

    public DetailsPresenter(DetailsContract.View view, Context context) {

        mView = view;

        mContext = context;
    }

    @Override
    public void loadImage(NetworkImageView imageView, String url) {
        LoadImage loadImage = new LoadImage();

        loadImage.loadImage(imageView, url,mContext);
    }

    @Override
    public void getStudent(final int id, final TextView nameText, final TextView emailText, final TextView phoneText, final TextView classText,
                           final TextView gpaText, final TextView genderText, final NetworkImageView profilePic,
                           final DetailsPresenter detailsPresenter) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, APIUrl.BASE_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    // Create a JSONObject from the JSON response string
                    JSONObject baseJsonResponse = new JSONObject(response);

                    if(!baseJsonResponse.getBoolean("error")){

                        JSONObject jsonArray = baseJsonResponse.getJSONObject("data");

                        int id = jsonArray.getInt("id");
                        int studentClass = jsonArray.getInt("class");
                        String nameString = jsonArray.getString("name");
                        String emailString = jsonArray.getString("email");
                        String imageUrl = jsonArray.getString("img_url");
                        String mobile = jsonArray.getString("mobile");
                        Double gpa = jsonArray.getDouble("gpa");
                        String gender = jsonArray.getString("gender");


                        imageUrl.replaceAll("\'/", "/");


                        nameText.setText(nameString);
                        emailText.setText("Email: " + emailString);
                        phoneText.setText("Mobile: " + mobile);
                        classText.setText("Class: " + String.valueOf(studentClass));
                        gpaText.setText("GPA: " + String.valueOf(gpa));
                        genderText.setText("Gender: " + gender);

                        detailsPresenter.loadImage(profilePic, imageUrl);


                        student = new Student(id, studentClass, nameString, imageUrl, emailString, mobile, gender, gpa);


                    }else{

                        mView.showMessage(baseJsonResponse.getString("error_msg"));

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message = null;
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof ServerError) {
                    message = "The server could not be found. Please try again after some time!!";
                } else if (error instanceof AuthFailureError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                } else if (error instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                }


                mView.showMessage(message);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String ,String> params = new HashMap<String,String>();

                params.put("student_id", String.valueOf(id));

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        requestQueue.add(stringRequest);


    }
}
