package company.cell.com.Cellotto.Custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import company.cell.com.Cellotto.R;

/**
 * Created by lue on 05-09-2017.
 */


public class ListViewAdapter extends ArrayAdapter<Daily> {
    TextView textViewName;
    TextView no;

    //the hero list that will be displayed
    private List<Daily> dailyList;

    //the context object
    private Context mCtx;

    //here we are getting the herolist and context
    //so while creating the object of this adapter class we need to give herolist and context
    public ListViewAdapter(List<Daily> dailyList, Context mCtx) {
        super(mCtx, R.layout.row, dailyList);
        this.dailyList = dailyList;
        this.mCtx = mCtx;

    }

    //this method will return the list item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //getting the layoutinflater
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        //creating a view with our xml layout
        View listViewItem = inflater.inflate(R.layout.row, null, true);

        //getting text views
         textViewName = (TextView) listViewItem.findViewById(R.id.play_digit);
         no = (TextView) listViewItem.findViewById(R.id.no);

        //Getting the hero for the specified position
        Daily daily = dailyList.get(position);


        //setting hero values to textviews
        textViewName.setText(daily.getName());
        no.setText(""+(position+1));





        System.out.println("=================position===================="+position);

        //returning the listitem
        return listViewItem;
    }
//    private AssetManager getAssets() {
//        // TODO Auto-generated method stub
//        return null;
//    }
}
