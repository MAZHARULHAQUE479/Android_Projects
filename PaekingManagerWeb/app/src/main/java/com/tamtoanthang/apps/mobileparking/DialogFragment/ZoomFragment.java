package com.tamtoanthang.apps.mobileparking.DialogFragment;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.tamtoanthang.apps.mobileparking.Main2Activity;
import com.tamtoanthang.apps.mobileparking.R;
import com.tamtoanthang.apps.mobileparking.SecondActivity;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by lue on 21-02-2018.
 */

public class ZoomFragment extends DialogFragment {

//    Bundle bundle = new Bundle();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.zoom_fragment, container, false);

//        bundle = getArguments();
//        String str = bundle.getString("zoom");
//        System.out.println("==========zoom=========="+str);
//        Intent intent = getActivity().getIntent();
//        String str = intent.getStringExtra("zoom");

      SharedPreferences  im = getActivity().getBaseContext().getSharedPreferences("img", MODE_PRIVATE);
     String str= im.getString("img","");



        try {
            ImageView imageView = (ImageView) getActivity().findViewById(R.id.imageview);
            Picasso.with(getActivity())
                    .load("https://"+str)
                    .into(imageView);
        } catch (Exception e){}


        return rootView;
    }

}
