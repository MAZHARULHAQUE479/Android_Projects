package company.cell.com.Cellotto;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import company.cell.com.Cellotto.Custom.CustomSpinnerAdapter;
import company.cell.com.Cellotto.Custom.MyEditTextLight;
import company.cell.com.Cellotto.Custom.NetworkUtil;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ApplicationActivity extends AppCompatActivity  {
    MyEditTextLight fname, lname, dob, city, scretCode, rScretcode, question, answer, phone, email;
    Button submit;
    TextView Spinner_hint;
    Spinner spin;
    String message;
    AppCompatRadioButton male, female;
    ProgressDialog pDialog;
    String firstname, lastname, Dob, gender, City, Pincode, SecretCode, reSecretCode, Answer, Phone, Email,spin_item;


    String[] content ={"What is your pet name","What is your mother maiden name","Which color do you like most"};
//    String  choose= "choose your question";

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setLogo(R.drawable.cellotto_small);
//
//        getSupportActionBar().setDisplayUseLogoEnabled(true);
//
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        fname = (MyEditTextLight) findViewById(R.id.fname);
        lname = (MyEditTextLight) findViewById(R.id.lname);
        dob = (MyEditTextLight) findViewById(R.id.dob);
//        spinner_hint=(MyEditTextLight)findViewById(R.id.spinner_hint);
        spin = (Spinner) findViewById(R.id.spinnerquestion);
        //  sex=(MyEditTextLight)findViewById(R.id.sex);
        phone = (MyEditTextLight) findViewById(R.id.phone);
        email = (MyEditTextLight) findViewById(R.id.email);
        city = (MyEditTextLight) findViewById(R.id.city);
//        pincode=(MyEditTextLight)findViewById(R.id.pincode);
        scretCode = (MyEditTextLight) findViewById(R.id.secret_code);
        rScretcode = (MyEditTextLight) findViewById(R.id.reSecret_code);
//        question=(MyEditTextLight)findViewById(R.id.spinnerquestion);
        answer = (MyEditTextLight) findViewById(R.id.answer);
        Spinner_hint=(TextView)findViewById(R.id.spinner_hint);
        submit = (Button) findViewById(R.id.submit);
        male = (AppCompatRadioButton) findViewById(R.id.male);
        female = (AppCompatRadioButton) findViewById(R.id.female);
//        submit.setOnClickListener(this);
        pDialog = new ProgressDialog(ApplicationActivity.this);
        pDialog.setCancelable(false);


        CustomSpinnerAdapter customAdapter=new CustomSpinnerAdapter(getApplicationContext(), content);
        spin.setAdapter(customAdapter);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                String  spin_item    = ((TextView) view.findViewById(R.id.tvContent)).getText().toString();
//                spin_item=String.valueOf(spin.getSelectedItem());

                System.out.println("=================spin_item========================"+spin_item);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        onReceive(this);


//        Spinner_hint.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Spinner_hint.setVisibility(View.INVISIBLE);
//                spin.setVisibility(View.VISIBLE);
//            }
//        });

//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getDataFromEditText();
//                Register(firstname, lastname,Dob ,Email, gender,Phone, City, SecretCode, spin_item, Answer);
//                if (validateProduct()) {
//                }
//
//            }
//        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataFromEditText();
               Register();
              if (validateProduct()) {

 }

            }
        });
    }








//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.submit:
//                getDataFromEditText();
//                Register();
////                if (validateProduct()) {
////                }
//                break;
//
//
//        }
//    }


    public boolean validateProduct() {
        String var_fname = fname.getText().toString();
        String var_lname = lname.getText().toString();
        String var_dob = dob.getText().toString();
        //  String var_sex = sex.getText().toString();
        String var_city = city.getText().toString();
//        String var_pincode = pincode.getText().toString();
        String var_scretCode = scretCode.getText().toString();
        String var_rScretcode = rScretcode.getText().toString();
//        String var_question = question.getText().toString();
        String var_answer = answer.getText().toString();
        String var_phone = phone.getText().toString();
        String var_email = email.getText().toString();
        String  var_spin_item = ((TextView) findViewById(R.id.tvContent)).getText().toString();


        boolean validate = true;
        if (var_fname.isEmpty()) {
            fname.setError("Please enter First name");
            validate = false;
        }
        if (var_lname.isEmpty()) {
            lname.setError("Please enter Last name");
            validate = false;
        }
        if (var_dob.isEmpty()) {
            dob.setError("Please enter Date of Birth");
            validate = false;
        }
        if (var_city.isEmpty()) {
            city.setError("Please enter city");
            validate = false;
        }
//        if (var_pincode.isEmpty()) {
//            pincode.setError("Please enter pincode");
//            validate= false;
//        }
        if (var_scretCode.isEmpty()) {
            scretCode.setError("Please enter scret code");
            validate = false;
        }
        if (var_rScretcode.isEmpty()) {
            rScretcode.setError("Please enter scret code");
            validate = false;
        }
        if (!scretCode.getText().toString().trim().equals(rScretcode.getText().toString().trim())) {
            rScretcode.setError("Not matched");
            validate = false;

        }
        if (var_spin_item.isEmpty()) {
            question.setError("Please enter Question");
            validate = false;
        }
        if (var_answer.isEmpty()) {
            answer.setError("Please enter answer");
            validate = false;
        }
        if (male.isChecked() || female.isChecked()) {
            male.setError(null);
        } else {
            male.setError("Please select your gender");
            Toast.makeText(getApplicationContext(), "Please select your gender", Toast.LENGTH_LONG).show();
        }

        return validate;
    }

    public void getDataFromEditText() {
        firstname = fname.getText().toString();
        lastname = lname.getText().toString().trim();
        Dob = dob.getText().toString().trim();
        City = city.getText().toString().trim();
        Phone = phone.getText().toString().trim();
        Email = email.getText().toString().trim();
//        Pincode = pincode.getText().toString().trim();
        SecretCode = scretCode.getText().toString().trim();
        reSecretCode = rScretcode.getText().toString().trim();
        spin_item = ((TextView)findViewById(R.id.tvContent)).getText().toString().trim();
        Answer = answer.getText().toString().trim();
        if (male.isChecked()) {
            gender = male.getText().toString().trim();
        } else if (female.isChecked()) {
            gender = female.getText().toString().trim();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent iBack = new Intent(ApplicationActivity.this, MainActivity.class);
        startActivity(iBack);
        finish();
    }

    public void Register() {

        final String FIRST_NAME = "first_name";
        final String LAST_NAME = "last_name";
        final String DOB = "dob";
        final String SEX = "sex";
        final String EMAIL = "email";
        final String PHONE = "phone";
        final String CITY = "city";
        final String SECRET_CODE = "secret_code";
        final String RETYPE_SECRET_CODE = "";
        final String SELECTQUESTION = "test_question";
        final String ANSWER = "answer";


        String REGISTER_URL = "http://www.cellotto.win/api/public/signup";
        String url = null;
        URL sourceUrl = null;
        REGISTER_URL = REGISTER_URL.replaceAll(" ", "%20");
        try {
            sourceUrl = new URL(REGISTER_URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        url = sourceUrl.toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //   Log.d("jaba", usernsme);
                        try {
                            JSONObject jsonresponse = new JSONObject(response);
                            String res= jsonresponse.getString("message");
                            SharedPreferences pref = getSharedPreferences("MyPrefrence", MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("message", res);

                            editor.commit(); // or editor.apply();
                            Toast.makeText(ApplicationActivity.this, res, Toast.LENGTH_LONG).show();
                            System.out.println("=============response id================"+res);
                            String error = jsonresponse.getString("errors");
                            if (error.equals("true")) {
                                String message = jsonresponse.getString("message");
                                Toast.makeText(ApplicationActivity.this, message, Toast.LENGTH_LONG).show();
                            }



                             else   if ( error.equals("false")) {
                           {
                                System.out.println("==================================" + message);
                                Toast.makeText(ApplicationActivity.this, message, Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(ApplicationActivity.this, PaymentActivity.class);
                                startActivity(intent);


                            }
//                                AlertDialog.Builder builder = new AlertDialog.Builder(ApplicationActivity.this);
//                                builder.setMessage(message)
//                                        .setNegativeButton("ok", null)
//                                        .create()
//                                        .show();
                                Toast.makeText(ApplicationActivity.this, message, Toast.LENGTH_LONG).show();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //   Toast.makeText(getContext(), response.toString(), Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //       Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();

                    }
                }) {
//            @Override
//            public String getBodyContentType() {
//                return "application/json";
//            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                //Adding parameters to request

                params.put(FIRST_NAME, firstname);
                params.put(LAST_NAME, lastname);
                params.put(DOB, Dob);
                params.put(SEX, gender);
                params.put(CITY, City);
                params.put(PHONE, Phone);
                params.put(EMAIL, Email);
                params.put(SECRET_CODE, SecretCode);
                params.put(RETYPE_SECRET_CODE, reSecretCode);
                params.put(SELECTQUESTION, spin_item);
                params.put(ANSWER, Answer);

                return params;

            }

        };
        com.android.volley.RequestQueue requestQueue = Volley.newRequestQueue(ApplicationActivity.this);
        requestQueue.add(stringRequest);
    }

    public void onReceive(Context context) {
        String status = NetworkUtil.getConnectivityStatusString(context);
        if ((status=="Not connected to Internet")){
            Toast.makeText(context, status, Toast.LENGTH_SHORT).show();
        }




    }
//    public void  Register(final String f_name,
//                          final String l_name,
//                          final String dob,
//                          final String email,
//                          final String gender,
//                          final String phone,
//                          final String city,
//                          final String secretCode,
//                          final String spin_item,
//                          final String answer){
//        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
//
//            protected void onPreExecute() {
//                super.onPreExecute();
//                pDialog.setMessage("data uploading...");
//                pDialog.show();
//            }
//            @Override
//            protected String doInBackground(String... params) {
//                String return_text="";
//                JSONObject jsonObject=new JSONObject();
//                try {
//
//                    jsonObject.accumulate("first_name",f_name);
//                    jsonObject.accumulate("last_name",l_name);
//                    jsonObject.accumulate("dob",dob);
//                    jsonObject.accumulate("email",email);
//                    jsonObject.accumulate("sex",gender);
//                    jsonObject.accumulate("phone",phone);
//                    jsonObject.accumulate("city",city);
//                    jsonObject.accumulate("secret_code",secretCode);
//                    jsonObject.accumulate("test_question",spin_item);
//                    jsonObject.accumulate("answer",answer);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    HttpClient httpClient = new DefaultHttpClient();
//                    HttpPost httpPost = new HttpPost("http://cellotto.win/api/public/signup");
//                    StringEntity httpiEntity=new StringEntity(jsonObject.toString());
//                    httpPost.setEntity(httpiEntity);
//                    org.apache.http.HttpResponse response = httpClient.execute(httpPost);
//                    return_text= HTTPResponse.readResponse(response);
//                    return return_text;
//                } catch (ClientProtocolException e) {
//
//                } catch (IOException e) {
//                }
//                return return_text;
//            }
//            @Override
//            protected void onPostExecute(String result) {
//                super.onPostExecute(result);
//                if(pDialog.isShowing()) pDialog.dismiss();
//                try {
//                    JSONObject jsonObject=new JSONObject(result);
//                    if (jsonObject.getString("errors").equalsIgnoreCase("false")) {
//                        message=jsonObject.getString("message");
//                        if (message != null) {
//                            Toast.makeText(ApplicationActivity.this, message, Toast.LENGTH_LONG).show();
//                            //Intent iSave = new Intent(ApplicationActivity.this, MainActivity.class);
//                           // startActivity(iSave);
//                            //finish();
//                        }
//                    }
//                    else if (jsonObject.getString("errors").equalsIgnoreCase("true")) {
//                        message = jsonObject.getString("message");
//                        if (message != null) {
//                            {
//                                System.out.println("===========================error");
//                                Toast.makeText(ApplicationActivity.this, "error", Toast.LENGTH_LONG).show();
//                            }
//                        }
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        try {
//            SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
//            sendPostReqAsyncTask.execute();
//        }catch (Exception e){}
//    }



}
