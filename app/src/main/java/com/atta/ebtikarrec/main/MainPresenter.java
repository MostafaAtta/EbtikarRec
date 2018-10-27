package com.atta.ebtikarrec.main;

import android.content.Context;

import com.android.volley.toolbox.NetworkImageView;
import com.atta.ebtikarrec.LoadImage;

public class MainPresenter implements MainContract.Presenter {


    private MainContract.View mView;

    private Context mContext;


    public MainPresenter(MainContract.View view, Context context) {

        mView = view;

        mContext = context;
    }

    @Override
    public void loadImage(NetworkImageView imageView, String url) {

        LoadImage loadImage = new LoadImage();

        loadImage.loadImage(imageView, url,mContext);
    }


}
