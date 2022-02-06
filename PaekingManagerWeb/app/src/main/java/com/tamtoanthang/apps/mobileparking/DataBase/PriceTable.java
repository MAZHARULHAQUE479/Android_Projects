package com.tamtoanthang.apps.mobileparking.DataBase;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by lue on 10-11-2016.
 */
public class PriceTable extends Activity {
    EditText CardType,CardPrice;
    Button btnDM;
    String Card_Type,Card_price,BaseUrl;
    SharedPreferences SM1,Bu;
    String Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.price_table);

        Bu = getSharedPreferences("Base", Context.MODE_PRIVATE);
        BaseUrl = Bu.getString("baseurl", "");
        CardType = (EditText) findViewById(R.id.CardType1);
        CardPrice = (EditText) findViewById(R.id.CardPrice1);
        btnDM = (Button) findViewById(R.id.btnDM);
//        getAllWidgets();
        bindWidgetsWithEvent();
//        checkForRequest();
    }
    private void bindWidgetsWithEvent() {
        btnDM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SM1 =getSharedPreferences("Myid", Context.MODE_PRIVATE);
                Id=SM1.getString("id","");
               Card_Type= CardType.getText().toString();
                Card_price=CardPrice.getText().toString();
                CardPrice(Id,Card_Type,Card_price);
//                onButtonClick();
            }
        });
    }

//    private void getAllWidgets() {


//    }
//    private void checkForRequest() {
//        String request = getIntent().getExtras().get(Constants.DML_TYPE).toString();
//        if (request.equals(Constants.UPDATE)) {
//            btnDM.setText(Constants.UPDATE);
//            CardType.setText(getIntent().getExtras().get(Constants.CARD_TYPE).toString());
//            CardPrice.setText(getIntent().getExtras().get(Constants.CARD_PRICE).toString());
//        } else {
//          //  btnDM .setText(Constants.INSERT);
//        }
//    }


    public void  CardPrice( final String Id,
                           final String Card_Type,
                            final String Card_price



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
                    jsonObject.accumulate("card_type",Card_Type);
                    jsonObject.accumulate("card_price",Card_price);






                } catch (JSONException e) {
                    e.printStackTrace();
                }


                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(BaseUrl+"/price_create.php");
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
                        String  message = jsonObject.getString("messsage1");
                        if (message != null) {
                            {
                                System.out.println("===========================message1");
                                Toast.makeText(PriceTable.this,message, Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(PriceTable.this,PriceDatabaseActivity.class);
                                startActivity(intent);
                            }}
                    }

                    else if (jsonObject.getString("error").equalsIgnoreCase("true")) {
                        String  message = jsonObject.getString("message");
                        if (message != null) {
                            {
                                System.out.println("===========================error");
                                Toast.makeText(PriceTable.this,message, Toast.LENGTH_LONG).show();
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

//    private void onButtonClick() {
//        if (CardType.getText().toString().equals("") || CardPrice.getText().toString().equals("")) {
//            Toast.makeText(getApplicationContext(), getString(R.string.Add_All_Fields), Toast.LENGTH_LONG).show();
//        } else {
//            Intent intent = new Intent();
//            intent.putExtra(Constants.CARD_TYPE, CardType.getText().toString());
//            intent.putExtra(Constants.CARD_PRICE, CardPrice.getText().toString());
//            setResult(RESULT_OK, intent);
//            finish();
//        }
//    }
}
