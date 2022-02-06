package com.tamtoanthang.apps.mobileparking.DataBase;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.rey.material.widget.ImageView;
import com.tamtoanthang.apps.mobileparking.Model.Detail;
import com.tamtoanthang.apps.mobileparking.R;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PopUpZoom extends AppCompatActivity {
 android.widget.ImageView imageView;
    String img="http://";
    String imageurl;
    PhotoViewAttacher pAttacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_zoom);
        imageView=(android.widget.ImageView)findViewById(R.id.zoom);
        Bundle bundle = getIntent().getExtras();
         imageurl = bundle.getString("imageurl");

        Glide.with(getBaseContext()).load(imageurl)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);

        pAttacher = new PhotoViewAttacher(imageView);
        pAttacher.update();
    }
}
