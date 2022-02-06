package com.tamtoanthang.apps.mobileparking.DialogFragment;

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
import android.widget.Toast;

import com.tamtoanthang.apps.mobileparking.Main2Activity;
import com.tamtoanthang.apps.mobileparking.R;

/**
 * Created by lue on 28-11-2016.
 */
public class SettingFragment extends DialogFragment {
    EditText ed1, ed2;
    Button bt1;
    SharedPreferences Ag, Bu;
    String AgentId, BaseUrl,SavedBaseUrl,SavedAgentId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.setting_fragment, container, false);

        Bu = getActivity().getSharedPreferences("Base", Context.MODE_PRIVATE);
        SavedBaseUrl = Bu.getString("baseurl", "");
        Ag = getActivity().getSharedPreferences("agent", Context.MODE_PRIVATE);
        SavedAgentId = Ag.getString("agentId", "");
        ed1 = (EditText) rootView.findViewById(R.id.url);
        ed1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_LONG_MESSAGE);
        ed2 = (EditText) rootView.findViewById(R.id.agent);
        ed2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_NULL);
        bt1 = (Button) rootView.findViewById(R.id.ok);
        ed1.setText(SavedBaseUrl);
        ed2.setText(SavedAgentId);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BaseUrl = ed1.getText().toString();
                AgentId = ed2.getText().toString();

                try {

                    if ((BaseUrl.isEmpty()) && (AgentId.isEmpty())) {
                        Toast.makeText(getActivity(), "Base Url and Agent field Can't Be Empty", Toast.LENGTH_SHORT).show();
                    } else {

                        Bu = getActivity().getSharedPreferences("Base", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = Bu.edit();
                        editor.putString("baseurl", BaseUrl);
                        editor.commit(); // or editor.apply();
//                        Toast.makeText(getActivity(), "Base Url Is Save Sucessfully", Toast.LENGTH_SHORT).show();
//                        ed1.setText("");

                        Ag = getActivity().getSharedPreferences("agent", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = Ag.edit();
                        editor1.putString("agentId", AgentId);
                        editor1.commit(); // or editor.apply();
//                        Toast.makeText(getActivity(), "Agent Name Save Sucessfully", Toast.LENGTH_SHORT).show();
//                        ed2.setText("");

                        Toast.makeText(getActivity(), " Saved Sucessfully", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(getActivity(), Main2Activity.class);
                        startActivity(i);
                    }
                    } catch(Exception e){
                    }


                }



        });
        return rootView;
    }

}