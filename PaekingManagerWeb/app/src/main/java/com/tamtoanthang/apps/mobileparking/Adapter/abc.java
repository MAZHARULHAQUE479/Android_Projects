//package com.tamtoanthang.apps.mobileparking.Adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//
//import com.rey.material.widget.TextView;
//import com.tamtoanthang.apps.mobileparking.Model.Price;
//import com.tamtoanthang.apps.mobileparking.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by lue on 03-11-2017.
// */
////https://stackoverflow.com/questions/24034754/how-to-get-data-from-custom-adapter-in-listview
//public class abc extends ArrayAdapter<Price> {
//    private List<Price> listNextRdv = new ArrayList<Price>();
//    private Context context;
//
//    public abc(List<Price> nextRdvList, Context ctx) {
//        super(ctx, R.layout.list_next_rdv, nextRdvList);
//        this.listNextRdv = nextRdvList;
//        this.context = ctx;
//    }
//
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        if (convertView == null) { // This a new view we inflate the new layout
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = inflater.inflate(R.layout.list_next_rdv, parent, false);
//        } // Now we can fill the layout with the right values
//
//        TextView tvTimeStart = (TextView) convertView.findViewById(R.id.next_time_start);
//        TextView tvTimeEnd = (TextView) convertView.findViewById(R.id.next_time_end);
//        TextView tvEnterprise = (TextView) convertView.findViewById(R.id.next_enterprise_name);
//        TextView tvAddress = (TextView) convertView.findViewById(R.id.next_address);
//        Price rdv = listNextRdv.get(position);
//
//        tvTimeStart.setText(rdv.getStartTime());
//        tvTimeEnd.setText(rdv.getEndTime());
//        tvEnterprise.setText(rdv.getEnterprise());
//        tvAddress.setText(rdv.getAddress());
//
//
//        return convertView;
//    }
//}