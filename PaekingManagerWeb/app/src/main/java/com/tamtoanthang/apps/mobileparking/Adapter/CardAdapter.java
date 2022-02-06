package com.tamtoanthang.apps.mobileparking.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tamtoanthang.apps.mobileparking.Model.Card;
import com.tamtoanthang.apps.mobileparking.R;

import java.util.List;

/**
 * Created by lue on 30-10-2017.
 */

public class CardAdapter extends ArrayAdapter<Card> {

    private Activity activity;



    public CardAdapter(Activity activity, int resource, List<Card> books) {
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
            convertView = inflater.inflate(R.layout.list, parent, false);
            // get all UI view
            holder = new ViewHolder(convertView);
            // set tag for holder
            convertView.setTag(holder);
        } else {
            // if holder created, get tag from view
            holder = (ViewHolder) convertView.getTag();
        }

        Card detail = getItem(position);
        holder.id.setText(detail.getId());
        holder.cardid.setText(detail.getCardId());
        holder.cardno.setText(detail.getCardNo());
        holder.cardtype.setText(detail.getCardType());

//        String i = img+detail.getInImage();



        return convertView;
    }

    private static class ViewHolder {
        private TextView id;
        private TextView cardid;
        private TextView cardno;
        private TextView cardtype;
        private ImageView edit;
        private ImageView delete;



        public ViewHolder(View v) {
            id=(TextView)v.findViewById(R.id.id);
            cardid = (TextView) v.findViewById(R.id.card_id);
            cardno=(TextView)v.findViewById(R.id.card_no);
            cardtype=(TextView)v.findViewById(R.id.card_type);
            edit=(ImageView)v.findViewById(R.id.edit);
            delete=(ImageView)v.findViewById(R.id.delete);

        }
    }

}
