package company.cell.com.Cellotto;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import company.cell.com.Cellotto.Custom.CustomSpinnerAdapter_SignIn;
import company.cell.com.Cellotto.Custom.MyEditTextLight;
import company.cell.com.Cellotto.Custom.NetworkUtil;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SignInActivity extends AppCompatActivity {
    MyEditTextLight security_code;
    Spinner security_question;
    MyEditTextLight security_answer;
    Button Sign_In;
    String Security_code,Security_Question,Security_Answer;


    String[] content ={"What is your pet name","What is your mother maiden name","Which color do you like most"};

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        security_code=(MyEditTextLight)findViewById(R.id.secret_code);
        security_question=(Spinner) findViewById(R.id.secret_question);
        security_answer=(MyEditTextLight)findViewById(R.id.secret_answer);
        Sign_In=(Button)findViewById(R.id.sign_in);


        CustomSpinnerAdapter_SignIn customSpinnerAdapter_signIn=new CustomSpinnerAdapter_SignIn(getApplicationContext(), content);
        security_question.setAdapter(customSpinnerAdapter_signIn);

        security_question.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                String  spin_item    = ((TextView) view.findViewById(R.id.tvContent)).getText().toString();
//                spin_item=String.valueOf(spin.getSelectedItem());

                System.out.println("=================spin_item==signin======================"+spin_item);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Sign_In.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataFromEditText();
                Register();
                if (validateProduct()) {

                }

//                Intent i = new Intent(SignInActivity.this, PlayActivity.class);
//                startActivity(i);
            }
        });

        onReceive(this);
}
    public boolean validateProduct() {
        String var_security_code = security_code.getText().toString();
        String  var_security_question = ((TextView) findViewById(R.id.tvContent)).getText().toString();
        String var_security_answer = security_answer.getText().toString();

        boolean validate = true;
        if (var_security_code.isEmpty()) {
            security_code.setError("Please enter Security Code");
            validate = false;
        }
        if (var_security_answer.isEmpty()) {
            security_answer.setError("Please Answer Your Security Question");
            validate = false;
        }



        return validate;
    }
    public void getDataFromEditText() {
        Security_code = security_code.getText().toString().trim();
        Security_Question = ((TextView) findViewById(R.id.tvContent)).getText().toString().trim();
        Security_Answer = security_answer.getText().toString().trim();
    }
    public void Register() {


        final String SECRET_CODE = "secret_code";
        final String SELECTQUESTION = "test_question";
        final String ANSWER = "answer";


        String REGISTER_URL = "http://www.cellotto.win/api/public/loginAuth";
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
                            SharedPreferences pref = getSharedPreferences("MyPrefrence1", MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("message", res);
                            editor.commit(); // or editor.apply();
                            String error = jsonresponse.getString("errors");
                            if (error.equals("true")) {
                                String message = jsonresponse.getString("message");
                                Toast.makeText(SignInActivity.this, message, Toast.LENGTH_LONG).show();
                                System.out.println("==================================" + message);

//                                Intent i = new Intent(SignInActivity.this, SignInActivity.class);
//                                startActivity(i);
//                              if (message == "Invalid Credentials") {
//
//
//                                }

                            } else if (error.equals("false")){

                                Intent i = new Intent(SignInActivity.this, PlayActivity.class);
                                startActivity(i);
//                                AlertDialog.Builder builder = new AlertDialog.Builder(SignInActivity.this);
//                                builder.setMessage(message)
//                                        .setNegativeButton("ok", null)
//                                        .create()
//                                        .show();
//                                Toast.makeText(SignInActivity.this, message, Toast.LENGTH_LONG).show();

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

                params.put(SECRET_CODE, Security_code);
                params.put(SELECTQUESTION, Security_Question);
                params.put(ANSWER, Security_Answer);

                return params;

            }

        };
        com.android.volley.RequestQueue requestQueue = Volley.newRequestQueue(SignInActivity.this);
        requestQueue.add(stringRequest);
    }

    public void onReceive(Context context) {
        String status = NetworkUtil.getConnectivityStatusString(context);
        if ((status == "Not connected to Internet")) {
            Toast.makeText(context, status, Toast.LENGTH_SHORT).show();
        }
    }

        @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent iBack = new Intent(SignInActivity.this, MainActivity.class);
        startActivity(iBack);
        finish();
    }
}