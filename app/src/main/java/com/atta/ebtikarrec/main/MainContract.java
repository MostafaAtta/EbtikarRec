package com.atta.ebtikarrec.main;

import com.android.volley.toolbox.NetworkImageView;

public interface MainContract {

    interface View{

        void showMessage(String message);

        void jsonPhrasing(String students);

        void showStudent(int id);
    }

    interface Presenter{

        void loadImage(final NetworkImageView imageView, String url);
    }
}
