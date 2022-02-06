package com.tamtoanthang.apps.mobileparking.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.tamtoanthang.apps.mobileparking.Model.User;
import com.tamtoanthang.apps.mobileparking.R;

import java.util.List;

/**
 * Created by lue on 31-10-2017.
 */

public class UserAdapter extends ArrayAdapter<User> {

    private Activity activity;


    public UserAdapter(Activity activity, int resource, List<User> books) {
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
            convertView = inflater.inflate(R.layout.user_list, parent, false);
            // get all UI view
            holder = new ViewHolder(convertView);
            // set tag for holder
            convertView.setTag(holder);
        } else {
            // if holder created, get tag from view
            holder = (ViewHolder) convertView.getTag();
        }

        User detail = getItem(position);
        holder.id.setText(detail.getId());
        holder.username.setText(detail.getUserName());
        holder.password.setText(detail.getPassword());
//        String i = img+detail.getInImage();


        return convertView;
    }

    private static class ViewHolder {
        private TextView id;
        private TextView username;
        private TextView password;
        private ImageView edit;
        private ImageView delete;


        public ViewHolder(View v) {
            id = (TextView) v.findViewById(R.id.id);
            username = (TextView) v.findViewById(R.id.user_name);
            password = (TextView) v.findViewById(R.id.user_password);
            edit=(ImageView)v.findViewById(R.id.edit);
            delete=(ImageView)v.findViewById(R.id.delete);

        }
    }
}
