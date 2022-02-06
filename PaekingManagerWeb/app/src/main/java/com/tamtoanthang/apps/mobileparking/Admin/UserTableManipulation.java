package com.tamtoanthang.apps.mobileparking.Admin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tamtoanthang.apps.mobileparking.Model.Constants;
import com.tamtoanthang.apps.mobileparking.DataBase.UserDatabaseActivity;
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

public class UserTableManipulation extends AppCompatActivity {

    EditText User,Upassword;
    Button btnDM;
    String UserName,Password,BaseUrl;
    String Id;
    SharedPreferences SM1,Bu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_table_manipulation);
        User = (EditText) findViewById(R.id.UserName);
        Upassword = (EditText) findViewById(R.id.UPassword);
        btnDM = (Button) findViewById(R.id.btnDMLUser);
        Bu = getSharedPreferences("Base", Context.MODE_PRIVATE);
        BaseUrl = Bu.getString("baseurl", "");
//        getAllWidgets();
        bindWidgetsWithEvent();
        checkForRequest();
    }
    private void bindWidgetsWithEvent() {
        btnDM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserName=User.getText().toString();
                Password=Upassword.getText().toString();
                SM1 =getSharedPreferences("Myid", Context.MODE_PRIVATE);
                Id=SM1.getString("id","");
                Register(Id,UserName,Password);

//                onButtonClick();
            }
        });

    }
//    private void getAllWidgets() {



//    }


    private void checkForRequest() {
        String request = getIntent().getExtras().get(Constants.DML_TYPE).toString();
        if (request.equals(Constants.UPDATE)) {
            btnDM.setText(Constants.UPDATE);
            User.setText(getIntent().getExtras().get(Constants.UserName).toString());
            Upassword.setText(getIntent().getExtras().get(Constants.UserPassword).toString());
        } else {
          //  btnDM .setText(Constants.INSERT);
        }
    }




//    private void onButtonClick() {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//        String string = dateFormat.format(new Date());
//        if (User.getText().toString().equals("") || Upassword.getText().toString().equals("")) {
//            Toast.makeText(getApplicationContext(), getString(R.string.Add_All_Fields), Toast.LENGTH_LONG).show();
//        } else {
//            Intent intent = new Intent();
//            intent.putExtra(Constants.UserName, User.getText().toString());
//            intent.putExtra(Constants.UserPassword, Upassword.getText().toString());
//            intent.putExtra(Constants.IN_TIME,string);
//           // intent.putExtra(Constants.Out_TIME,String);
//            setResult(RESULT_OK, intent);
//            finish();
//        }

//    }

    public void  Register(final String  Id ,
                          final String UserName,
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
                    jsonObject.accumulate("admin_id",Id);
                    jsonObject.accumulate("user_name",UserName);
                    jsonObject.accumulate("user_password",Password);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(BaseUrl+"/user_create.php");
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
                        String  message = jsonObject.getString("success");
//                        String id=jsonObject.getString("id");
                        if(message !=null) {
                            Toast.makeText(UserTableManipulation.this, message, Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(UserTableManipulation.this, UserDatabaseActivity.class);
                            startActivity(intent);
                        }


                    }
                    else if (jsonObject.getString("error").equalsIgnoreCase("true")) {
                        String  message = jsonObject.getString("message");
                        if (message != null) {
                            {
                                System.out.println("===========================error");
                                Toast.makeText(UserTableManipulation.this,message, Toast.LENGTH_LONG).show();
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
