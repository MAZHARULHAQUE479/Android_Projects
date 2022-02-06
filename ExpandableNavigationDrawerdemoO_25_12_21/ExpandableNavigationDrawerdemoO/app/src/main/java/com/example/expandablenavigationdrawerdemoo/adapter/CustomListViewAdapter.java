package com.example.expandablenavigationdrawerdemoo.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.expandablenavigationdrawerdemoo.Model.Detail;
import com.example.expandablenavigationdrawerdemoo.R;

import java.util.List;




/**
 * Created by lue on 06-10-2017.
 */

public class CustomListViewAdapter extends ArrayAdapter<Detail> {
//    PhotoViewAttacher pAttacher;
    private Activity activity;
    String img="http://";


    public CustomListViewAdapter(Activity activity, int resource, List<Detail> books) {
        super(activity, resource, books);
        this.activity = activity;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        // If holder not exist then locate all view from UI file.
        if (convertView == null) {
            // inflate UI from XML file
            convertView = inflater.inflate(R.layout.list_item3, parent, false);
            // get all UI view
            holder = new ViewHolder(convertView);
            // set tag for holder
            convertView.setTag(holder);
        } else {
            // if holder created, get tag from view
            holder = (ViewHolder) convertView.getTag();
        }

        final Detail detail = getItem(position);

        holder.cardid.setText(detail.getCardId());
        holder.cardno.setText(detail.getCardNo());
        holder.cardtype.setText(detail.getCardType());
        holder.cardprice.setText(detail.getCardPrice());
//        String i = img+detail.getInImage();
        Glide.with(getContext()).load(img+detail.getInImage())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.inimage);
        Glide.with(getContext()).load(img+detail.getOutImage())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.outimage);
        holder.inimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PopUpZoom.class);
                intent.putExtra("imageurl",img+detail.getInImage() );
                activity.startActivity(intent);
            }
        });
        holder.outimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PopUpZoom.class);
                intent.putExtra("imageurl",img+detail.getOutImage() );
                activity.startActivity(intent);

            }
        });


//        pAttacher = new PhotoViewAttacher(holder.inimage);
//        pAttacher.update();
//        pAttacher = new PhotoViewAttacher(holder.outimage);
//        pAttacher.update();

        holder.intime.setText(detail.getInTime());
        holder.outtime.setText(detail.getOutTime());
        holder.status.setText(detail.getStatus());
        holder.username.setText(detail.getUserName());

        return convertView;
    }

    private static class ViewHolder {
        private TextView cardid;
        private TextView cardno;
        private TextView cardtype;
        private TextView cardprice;
        private ImageView inimage;
        private ImageView outimage;
        private TextView intime;
        private TextView outtime;
        private TextView status;
        private TextView username;

        public ViewHolder(View v) {
            cardid = (TextView) v.findViewById(R.id.cardid);
            cardno=(TextView)v.findViewById(R.id.cardno);
            cardtype=(TextView)v.findViewById(R.id.cardtype);
            cardprice=(TextView)v.findViewById(R.id.cardprice);
            inimage=(ImageView) v.findViewById(R.id.inimage);
            outimage = (ImageView) v.findViewById(R.id.outimage);
            intime = (TextView) v.findViewById(R.id.intime);
            outtime=(TextView)v.findViewById(R.id.outtime);
            status=(TextView)v.findViewById(R.id.status);
            username=(TextView)v.findViewById(R.id.username);
        }
    }

}

