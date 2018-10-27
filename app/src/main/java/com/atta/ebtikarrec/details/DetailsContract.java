package com.atta.ebtikarrec.details;

import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

public interface DetailsContract {



    interface View{

        void showMessage(String message);

        void showStudent(int id);

        void initiateViews();
    }

    interface Presenter{

        void loadImage(final NetworkImageView imageView, String url);

        void getStudent(int id, final TextView nameText, final TextView emailText, final TextView phoneText, final TextView classText,
                        final TextView gpaText, final TextView genderText, final NetworkImageView profilePic,
                        final DetailsPresenter detailsPresenter);
    }
}
