package company.cell.com.Cellotto.Custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import company.cell.com.Cellotto.R;

/**
 * Created by lue on 21-08-2017.
 */

public class CustomSpinnerAdapter extends BaseAdapter {
    Context context;
//    String choose;
    String[] content;
    LayoutInflater inflter;

    public CustomSpinnerAdapter(Context applicationContext,   String[] content) {
        this.context = applicationContext;
//        this.choose = choose;
        this.content = content;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return content.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.custom_spinner_items, null);
//        TextView number = (TextView) view.findViewById(R.id.tvNumber);
        TextView names = (TextView) view.findViewById(R.id.tvContent);
//        number.setText(choose);
        names.setText(content[i]);
        return view;
    }


}


