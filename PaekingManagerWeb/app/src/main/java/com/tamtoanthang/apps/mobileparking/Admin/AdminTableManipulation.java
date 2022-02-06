package com.tamtoanthang.apps.mobileparking.Admin;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class AdminTableManipulation extends AppCompatActivity {

    EditText Admin, password;
    Button btnDM;
    String  AdminUser,AdminPass,BaseUrl;
    SharedPreferences Bu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_table_manipulation);
        Admin = (EditText) findViewById(R.id.AdminName);
        password = (EditText) findViewById(R.id.Password);
        btnDM = (Button) findViewById(R.id.btnDMADMIN);
        Bu = getSharedPreferences("Base", Context.MODE_PRIVATE);
        BaseUrl = Bu.getString("baseurl", "");
//        getAllWidgets();
//        bindWidgetsWithEvent();
//        checkForRequest();


//    private void bindWidgetsWithEvent() {
        btnDM.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){
//                onButtonClick();
            getDataFromEditText();
            Register(AdminUser,AdminPass);
    }
    });
    }
    public void getDataFromEditText() {
        AdminUser = Admin.getText().toString().trim();
        AdminPass = password.getText().toString().trim();

        }

    public void  Register(final String AdminUser,
                          final String AdminPass
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

                    jsonObject.accumulate("admin_name",AdminUser);
                    jsonObject.accumulate("admin_password",AdminPass);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(BaseUrl+"/admin_create.php");
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
                        if (message != null) {
                            Toast.makeText(AdminTableManipulation.this, message, Toast.LENGTH_LONG).show();
//                            Intent iSave = new Intent(AdminTableManipulation.this, .class);
//                             startActivity(iSave);
//                            finish();
                        }
                    }
                    else if (jsonObject.getString("error").equalsIgnoreCase("true")) {
                      String  message = jsonObject.getString("message");
                        if (message != null) {
                            {
                                System.out.println("===========================error");
                                Toast.makeText(AdminTableManipulation.this, message, Toast.LENGTH_LONG).show();
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
//    }

//    private void getAllWidgets() {



//    }
//    private void checkForRequest() {
//        String request = getIntent().getExtras().get(Constants.DML_TYPE).toString();
//        if (request.equals(Constants.UPDATE)) {
//            btnDM.setText(Constants.UPDATE);
//            Admin.setText(getIntent().getExtras().get(Constants.AdminName).toString());
//            password.setText(getIntent().getExtras().get(Constants.AdminPassword).toString());
//        } else {
//           // btnDM .setText(Constants.INSERT);
//        }
//    }
//    private void onButtonClick() {
//        if (Admin.getText().toString().equals("") || password.getText().toString().equals("")) {
//            Toast.makeText(getApplicationContext(),getString(R.string.Add_All_Fields), Toast.LENGTH_LONG).show();
//        } else {
//            Intent intent = new Intent();
//            intent.putExtra(Constants.AdminName, Admin.getText().toString());
//            intent.putExtra(Constants.AdminPassword, password.getText().toString());
//            setResult(RESULT_OK, intent);
//            finish();
//        }
//    }


}
