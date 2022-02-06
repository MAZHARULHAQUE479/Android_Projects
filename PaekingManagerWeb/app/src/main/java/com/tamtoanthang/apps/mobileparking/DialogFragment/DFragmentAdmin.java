package com.tamtoanthang.apps.mobileparking.DialogFragment;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tamtoanthang.apps.mobileparking.Admin.AdminArea;
import com.tamtoanthang.apps.mobileparking.DataBase.SQLiteHelper;
import com.tamtoanthang.apps.mobileparking.R;
import com.tamtoanthang.apps.mobileparking.java.HTTPResponse;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by lue on 28-11-2016.
 */
public class DFragmentAdmin extends DialogFragment {
    SQLiteHelper sQLiteHelper;
    EditText ed1,ed2;
    Button bt1;
    SharedPreferences SM1,Bu;
    String name,Password,BaseUrl;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dfragment_admin, container, false);
        Bu = getActivity().getSharedPreferences("Base", Context.MODE_PRIVATE);
        BaseUrl = Bu.getString("baseurl", "");

        SM1 =getActivity(). getSharedPreferences("adminrecord", 0);
        getDialog().getActionBar();
        sQLiteHelper=new SQLiteHelper(getActivity());
        ed1=(EditText)rootView.findViewById(R.id.AdminName);
        ed1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        ed2=(EditText)rootView.findViewById(R.id.Apassword);
        ed2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        bt1=(Button)rootView.findViewById(R.id.buttonAok);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(ed2.getText()==null &&ed1.getText()==null){

//                    Toast.makeText(getActivity(),"Enter Name And Password",Toast.LENGTH_LONG).show();
//                }
//                else
//                    {
//                    sQLiteHelper.getWritableDatabase();
//                    ArrayList<ContactModel> contact = sQLiteHelper.getAllRecordsAdmin();
//                    Log.d("size", "" + contact.size());
//                    if (contact.size() > 0) {
//                        for (int i = 0; i < contact.size(); i++) {
//                            String v = contact.get(i).getAdminName();
//                            String p = contact.get(i).getAdminPassword();
//
//
//                            if ((ed1.getText().toString().equals(v))&&(ed2.getText().toString().equals(p))){
//                                Intent intent= new Intent(getActivity(), AdminArea.class);
//                                SharedPreferences.Editor edit = SM1.edit();
//                                edit.putBoolean("adminlogin", true);
//                                edit.commit();
//                                startActivity(intent);
//                                break;
//
//                            }else {
//                                Toast.makeText(getActivity(),"Wrong Entry",Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    }else if(contact.size() == 0) {
//                        Toast.makeText(getActivity(),"No record Plz make first record",Toast.LENGTH_SHORT).show();
//                        Intent intent= new Intent(getActivity(), AdminArea.class);
//                        SharedPreferences.Editor edit = SM1.edit();
//                        edit.putBoolean("adminlogin", true);
//                        edit.commit();
//                        startActivity(intent);
//
//
//                    }
                name=ed1.getText().toString();
                Password=ed2.getText().toString();
                        Register(name,Password);

                }





        });
        return rootView;
    }
    public void  Register(final String name,
                          final String Password
    ){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {

            protected void onPreExecute() {
                super.onPreExecute();

            }
            @Override
            protected String doInBackground(String... params) {
                String return_text="";
                JSONObject jsonObject=new JSONObject();
                try {

                    jsonObject.accumulate("admin_name",name);
                    jsonObject.accumulate("password",Password);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(BaseUrl+"/adminlogin.php");
                    StringEntity httpiEntity=new StringEntity(jsonObject.toString());
                    httpPost.setEntity(httpiEntity);
                    org.apache.http.HttpResponse response = httpClient.execute(httpPost);
                    return_text= HTTPResponse.readResponse(response);
                    return return_text;
                } catch (ClientProtocolException e) {

                } catch (IOException e) {
                }
                return return_text;
            }
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                try {
                    JSONObject jsonObject=new JSONObject(result);
                    if (jsonObject.getString("error").equalsIgnoreCase("false")) {
                        String message=jsonObject.getString("messsage");
                        String id=jsonObject.getString("id");
                        if (message != null) {
                            Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                            Intent intent= new Intent(getActivity(), AdminArea.class);
                            SharedPreferences pref = getActivity().getSharedPreferences("Myid", MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("id", id);
                            editor.commit(); // or editor.apply();
                            startActivity(intent);

                        }
                    }
                    else if (jsonObject.getString("error").equalsIgnoreCase("true")) {
                        String  message = jsonObject.getString("messsage");
                        if (message != null) {
                            {
                                System.out.println("===========================error");
                                Toast.makeText(getActivity(),message, Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
            sendPostReqAsyncTask.execute();
        }catch (Exception e){}
    }
}
