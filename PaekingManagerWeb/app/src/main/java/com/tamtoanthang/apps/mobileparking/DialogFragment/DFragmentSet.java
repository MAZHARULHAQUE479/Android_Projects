package com.tamtoanthang.apps.mobileparking.DialogFragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tamtoanthang.apps.mobileparking.R;

/**
 * Created by lue on 18-10-2016.
 */
public class DFragmentSet extends DialogFragment{
Button button;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dfragment_set, container, false);
        getDialog().setTitle("Choose Attachment");
        getDialog().getActionBar();
        button=(Button)rootView.findViewById(R.id.okset);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return rootView;
    }
}
