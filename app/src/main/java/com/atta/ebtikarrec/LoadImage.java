package com.atta.ebtikarrec;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.atta.ebtikarrec.model.CustomVolleyRequest;

public class LoadImage {

    public void loadImage(NetworkImageView imageView, String url, Context mContext) {

        ImageLoader imageLoader;

        if(url.equals("")){
            Toast.makeText(mContext,"Please enter a URL",Toast.LENGTH_LONG).show();
            return;
        }

        imageLoader = CustomVolleyRequest.getInstance(mContext)
                .getImageLoader();
        imageLoader.get(url, ImageLoader.getImageListener(imageView,
                R.drawable.personal, android.R.drawable
                        .ic_dialog_alert));
        imageView.setImageUrl(url, imageLoader);
    }
}
