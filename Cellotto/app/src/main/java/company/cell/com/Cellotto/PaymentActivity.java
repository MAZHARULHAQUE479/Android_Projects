package company.cell.com.Cellotto;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import company.cell.com.Cellotto.Custom.MyEditTextLight;
import company.cell.com.Cellotto.Custom.NetworkUtil;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PaymentActivity extends AppCompatActivity  {


    Button proceed;
    RadioGroup pay;
    RadioButton verve,mastercard, visa;
    MyEditTextLight card_number, card_holder_name, expiryDate, cvvCode, security_code;
    String Card_Type,Card_no,CardHolder_name,Expiry_date,Cvv_code,Security_code;
    public static final String ID = "message";
    public static final String MyPrefrence = "MyPrefrence";
    String Id;
    SharedPreferences sharedpreferences;
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setLogo(R.drawable.cellotto_small);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        proceed=(Button)findViewById(R.id.proceed);
        card_number = (MyEditTextLight)findViewById(R.id.card_number);
        card_holder_name = (MyEditTextLight) findViewById(R.id.card_holder_name);
        expiryDate = (MyEditTextLight) findViewById(R.id.expiryDate);
        cvvCode = (MyEditTextLight) findViewById(R.id.cvvCode);
        security_code = (MyEditTextLight) findViewById(R.id.security_code);
        verve = (RadioButton) findViewById(R.id.verve);
        mastercard = (RadioButton) findViewById(R.id.mastercard);
        visa = (RadioButton) findViewById(R.id.visa);
        pay= (RadioGroup) findViewById(R.id.pay);



        try{

            sharedpreferences =getSharedPreferences(MyPrefrence,Context.MODE_PRIVATE);
            Id=sharedpreferences.getString(ID,"");
            System.out.println("================Id================"+Id);



        }catch (Exception e){

        }

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataFromEditText();
                Register();
                if (validateProduct()) {

                }

            }
        });

        onReceive(this);

    }


//    @Override
//    public void onClick(View view) {
//        getDataFromEditText();
//        Register();
//        if (validateProduct()) {
//
//        }
//        Intent iPlay = new Intent(PaymentActivity.this, PlayActivity.class);
//        startActivity(iPlay);
//        finish();


    public boolean validateProduct() {
        String var_card_num = card_number.getText().toString();
        String var_card_holder_name = card_holder_name.getText().toString();
        String var_expiry_date = expiryDate.getText().toString();
        String var_cvvCode = cvvCode.getText().toString();
        String var_prod_qty2 = security_code.getText().toString();



        boolean validate=true;
        if (var_card_num.isEmpty()) {
            card_number.setError("Please enter card number");
            validate= false;
        }
        if (var_card_holder_name.isEmpty()) {
            card_holder_name.setError("Please enter card holder name");
            validate= false;
        }
        if (var_expiry_date.isEmpty()) {
            expiryDate.setError("Please enter expiry date");
            validate= false;
        }
        if (var_cvvCode.isEmpty()) {
            cvvCode.setError("Please enter CVV");
            validate= false;
        }
        if (var_prod_qty2.isEmpty()) {
            security_code.setError("Please enter security code");
            validate= false;
        }
        if (verve.isChecked() || mastercard.isChecked() || visa.isChecked()){

        }else{
            Toast.makeText(getApplicationContext(), "Please select any payment method", Toast.LENGTH_LONG).show();
        }

        return validate;
    }
    public void getDataFromEditText() {
        if (verve.isChecked()) {
            Card_Type = verve.getText().toString().trim();
        } else if (mastercard.isChecked()) {
            Card_Type = mastercard.getText().toString().trim();
        }else if (visa.isChecked())
        {
            Card_Type=visa.getText().toString().trim();
        }
        Card_no = card_number.getText().toString();
        CardHolder_name = card_holder_name.getText().toString().trim();
        Expiry_date = expiryDate.getText().toString().trim();
        Cvv_code = cvvCode.getText().toString().trim();
        Security_code=security_code.getText().toString().trim();
        Id.toString().trim();



    }

    public void Register() {

        final String CARD_TYPE = "card_type";
        final String CARD_NUMBER = "card_number";
        final String CARD_HOLDER_NAME = "card_holder_name";
        final String EXPIRY_DATE = "expiry_date";
        final String CVV = "cvv";
        final String SECURITY_CODE = "security_code";
        final String SESSION_ID="id";



        String REGISTER_URL = "http://www.cellotto.win/api/public/paymentdetail";
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
                            String error = jsonresponse.getString("errors");
                            if (error.equals("true")) {
                                String message = jsonresponse.getString("message");

                                Toast.makeText(PaymentActivity.this, message, Toast.LENGTH_LONG).show();

                            } else {
                                if (error.equals("false")) {
                                    String message = jsonresponse.getString("message");
                                    System.out.println("==================================" + message);
                                    Toast.makeText(PaymentActivity.this, message, Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(PaymentActivity.this, PlayActivity.class);
                                    startActivity(intent);

                                }
//
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
                params.put(SESSION_ID, Id);
                params.put(CARD_TYPE, Card_Type);
                params.put(CARD_NUMBER, Card_no);
                params.put(CARD_HOLDER_NAME, CardHolder_name);
                params.put(EXPIRY_DATE, Expiry_date);
                params.put(CVV, Cvv_code);
                params.put(SECURITY_CODE, Security_code);


                return params;

            }

        };
        com.android.volley.RequestQueue requestQueue = Volley.newRequestQueue(PaymentActivity.this);
        requestQueue.add(stringRequest);
    }

//    public void Register()
//    {
//
//        final String CARD_TYPE = "card_type";
//        final String CARD_NUMBER = "card_number";
//        final String CARD_HOLDER_NAME = "card_holder_name";
//        final String EXPIRY_DATE = "expiry_date";
//        final String CVV = "cvv";
//        final String SECURITY_CODE = "security_code";
////
//    class AsyncT extends AsyncTask<Void, Void, Void> {
//        @Override
//        protected Void doInBackground(Void... voids) {
//            HttpClient httpclient = new DefaultHttpClient();
//            HttpPost httppost = new HttpPost("http://cellotto.win/api/public/paymentdetail");
//
//            try {
//
//                JSONObject jsonobj = new JSONObject();
//
//                jsonobj.put(CARD_TYPE, Card_Type);
//                jsonobj.put(CARD_NUMBER, Card_no);
//                jsonobj.put(CARD_HOLDER_NAME, CardHolder_name);
//                jsonobj.put(EXPIRY_DATE, Expiry_date);
//                jsonobj.put(CVV, Cvv_code);
//                jsonobj.put(SECURITY_CODE, Security_code);
//
//
//                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//                nameValuePairs.add(new BasicNameValuePair("req", jsonobj.toString()));
//
//
//
//                // Use UrlEncodedFormEntity to send in proper format which we need
//                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//
//                // Execute HTTP Post Request
//                HttpResponse response = httpclient.execute(httppost);
//                InputStream inputStream = response.getEntity().getContent();
//
//
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//
//    }}

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent iBack = new Intent(PaymentActivity.this, MainActivity.class);
        startActivity(iBack);
        finish();
    }
    public void onReceive(Context context) {
        String status = NetworkUtil.getConnectivityStatusString(context);
        if ((status == "Not connected to Internet")) {
            Toast.makeText(context, status, Toast.LENGTH_SHORT).show();
        }
    }
    }
