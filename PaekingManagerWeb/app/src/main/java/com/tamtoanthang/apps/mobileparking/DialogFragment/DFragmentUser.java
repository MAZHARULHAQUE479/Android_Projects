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

import com.tamtoanthang.apps.mobileparking.SecondActivity;
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
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by lue on 28-11-2016.
 */
public class DFragmentUser extends DialogFragment{
    SQLiteHelper sQLiteHelper;
    EditText ed1,ed2;
    Button bt1;
   public static String UserName,Password;
    String userintime,AgentId,BaseUrl;
    SharedPreferences pref;
    SharedPreferences Ag,Bu;

//    SharedPreferences SM;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dfragment_user, container, false);

        getDialog().getActionBar();
        Bu = getActivity().getSharedPreferences("Base", Context.MODE_PRIVATE);
        BaseUrl = Bu.getString("baseurl", "");

        ed1=(EditText)rootView.findViewById(R.id.userName);
        ed1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        ed2=(EditText)rootView.findViewById(R.id.userPassword);
        ed2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        bt1=(Button)rootView.findViewById(R.id.buttonUok);
//        SM =getActivity(). getSharedPreferences("userrecord", 0);


        sQLiteHelper=new SQLiteHelper(getActivity());
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ag = getActivity().getSharedPreferences("agent", Context.MODE_PRIVATE);
                AgentId = Ag.getString("agentId", "");
                UserName=ed1.getText().toString();
                Password=ed2.getText().toString();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                userintime = dateFormat.format(new Date());
                Register(AgentId,UserName,Password,userintime);
//                if(ed2.getText()==null &&ed1.getText()==null){
//
//                    Toast.makeText(getActivity(),"Enter Name And Password",Toast.LENGTH_LONG).show();
//                }else {
//                    sQLiteHelper.getWritableDatabase();
//                    ArrayList<ContactModel> contact = sQLiteHelper.getAllRecordsUser1();
//                    if (contact.size() > 0) {
//                        for (int i = 0; i < contact.size(); i++) {
//                            String v = contact.get(i).getUserName();
//                            String p = contact.get(i).getUserPassword();
//                            if ((ed1.getText().toString().equals(v))&&(ed2.getText().toString().equals(p))){
//                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//                                userintime = dateFormat.format(new Date());
//                                ContactModel contact1 = new ContactModel();
//                                contact1.setUserName(v);
//                                contact1.setUserPassword(p);
//                                contact1.setUserINTime(userintime);
//                                Log.d("ffss",""+userintime);
//                                contact1.setUsserOutTime("");
//                                sQLiteHelper.insertRecordUser(contact1);
//                                Intent intent= new Intent(getActivity(), SecondActivity.class);
//                                startActivity(intent);
//
//                                SharedPreferences.Editor edit = SM.edit();
//                                edit.putBoolean("userlogin", true);
//                                edit.commit();
//                                break;
//                            }else {
//
//
//                            }
//
//                        }
//
//
//
//                    }
//
//                }





            }
        });
        return rootView;
    }
    public void  Register(final String AgentId,
                          final String UserName,
                          final String Password,
                          final String userintime
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
                    jsonObject.accumulate("agent_name",AgentId);
                    jsonObject.accumulate("user_name",UserName);
                    jsonObject.accumulate("password",Password);
                    jsonObject.accumulate("login_time",userintime);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(BaseUrl+"/userlogin.php");
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
                        String message=jsonObject.getString("message");
                        String userId=jsonObject.getString("user_id");
                        String username=jsonObject.getString("user_name");
                        String  adminId=jsonObject.getString("admin_id");

                        pref=getActivity().getSharedPreferences("MyPrefrence1", MODE_PRIVATE);

                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("user_name",username);
                        editor.putString("login_time",userintime);
                        editor.putString("adminId",adminId);
                        editor.putString("user_id",userId);
                        editor.commit();

                        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                        Intent intent= new Intent(getActivity(), SecondActivity.class);

                        getActivity().startActivity(intent);

                        }

                    else if (jsonObject.getString("error").equalsIgnoreCase("true")) {
                        String  message = jsonObject.getString("message");
                        if (message != null) {
                            {

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

