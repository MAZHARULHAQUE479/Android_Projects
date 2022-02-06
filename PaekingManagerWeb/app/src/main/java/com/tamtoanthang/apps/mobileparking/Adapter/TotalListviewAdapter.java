package com.tamtoanthang.apps.mobileparking.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tamtoanthang.apps.mobileparking.Model.Total;
import com.tamtoanthang.apps.mobileparking.R;

import java.util.List;

/**
 * Created by lue on 07-10-2017.
 */

public class TotalListviewAdapter extends ArrayAdapter<Total> {

    private Activity activity;



    public TotalListviewAdapter(Activity activity, int resource, List<Total> total) {
        super(activity, resource, total);
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
            convertView = inflater.inflate(R.layout.list_item5, parent, false);
            // get all UI view
            holder = new ViewHolder(convertView);
            // set tag for holder
            convertView.setTag(holder);
        } else {
            // if holder created, get tag from view
            holder = (ViewHolder) convertView.getTag();
        }

        Total total = getItem(position);

        holder.price.setText(total.getTPrice());
        holder.intime.setText(total.getTLogInTime());
        holder.outtime.setText(total.getTLogOutTime());
        holder.username.setText(total.getTUserName());
        holder.car.setText(total.getTCar());

        return convertView;
    }

    private static class ViewHolder {
        private TextView username;
        private TextView price;
        private TextView intime;
        private TextView outtime;
        private TextView car;





        public ViewHolder(View v) {

            price=(TextView)v.findViewById(R.id.atprice);
            intime = (TextView) v.findViewById(R.id.alogintime);
            outtime=(TextView)v.findViewById(R.id.alogouttime);
            username=(TextView)v.findViewById(R.id.ausername);
            car=(TextView)v.findViewById(R.id.atotalcar);
        }
    }

}

