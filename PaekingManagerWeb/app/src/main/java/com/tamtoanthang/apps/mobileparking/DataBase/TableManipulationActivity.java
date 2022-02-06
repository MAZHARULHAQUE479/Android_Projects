package com.tamtoanthang.apps.mobileparking.DataBase;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.tamtoanthang.apps.mobileparking.Admin.AdminArea;
import com.tamtoanthang.apps.mobileparking.R;
import com.tamtoanthang.apps.mobileparking.java.HTTPResponse;
import com.tamtoanthang.apps.mobileparking.java.HttpHandler;
import com.xminnov.ivrjack.rh06.IvrJackAdapter;
import com.xminnov.ivrjack.rh06.IvrJackService;
import com.xminnov.ivrjack.rh06.IvrJackStatus;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Nikunj on 01-09-2015.
 */
public class TableManipulationActivity extends AppCompatActivity implements IvrJackAdapter,Spinner.OnItemSelectedListener {

    EditText cardid,cardno,cardtype;
    String CardId,CardNo,CardType;
    private NfcAdapter mAdapter;
    Button btnDML;
    public static Timer timer;
    TimerTask timerTask;
    private IvrJackService service;
    final Handler handler = new Handler();
    public static String tagData= "";
    String idfromreader="";
    String  Id,item,BaseUrl;
    private Spinner spinner;
    private static String url ="http://dichvucongcong.vn/eparking/show_card_type.php";
    SharedPreferences SM1,Bu;
    private PendingIntent mPendingIntent;
    private ArrayList<String> students;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_card);

        //=============admin id=========
        SM1 =getSharedPreferences("Myid",Context.MODE_PRIVATE);
        Id=SM1.getString("id","");
        //=============base Url
        Bu = getSharedPreferences("Base", Context.MODE_PRIVATE);
        BaseUrl = Bu.getString("baseurl", "");
        RegCardType(Id);

        cardid = (EditText) findViewById(R.id.cardId);
        cardno = (EditText) findViewById(R.id.cardno);
//        cardtype = (EditText) findViewById(R.id.CardType);
        spinner = (Spinner) findViewById(R.id.CardType);
        spinner.setOnItemSelectedListener(this);

        students = new ArrayList<String>();
        btnDML = (Button) findViewById(R.id.btnDML);
        SM1 =this. getSharedPreferences("adminrecord", 0);

        service=new IvrJackService(this,this);
        Log.d("cccccaon","in on create");
        boolean adminlogin = SM1.getBoolean("adminlogin", false);
        if (adminlogin=true) {
            mAdapter = NfcAdapter.getDefaultAdapter(this);
            if (mAdapter == null) {
//                getAllWidgets();
                bindWidgetsWithEvent();
//                checkForRequest();
                //nfc not support your device.
                return;
            } else {
//                getAllWidgets();
                bindWidgetsWithEvent();
               // checkForRequest();
                mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                        getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

            }
        }else {
            Toast.makeText(this,"please login first",Toast.LENGTH_LONG).show();
        }
        if(mAdapter==null) {
            Log.d("gbhb","hhu");
//            getAllWidgets();
            bindWidgetsWithEvent();
//            checkForRequest();
        }
    }
    @Override
    protected void onNewIntent(Intent intent){
        getTagInfo(intent);
    }

    private void getTagInfo(Intent intent) {
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if (tag == null) {
            //  textViewInfo.setText("tag == null");
        } else {

            String tagInfo = "";
            String str = "";
            String str1 = "";
            String str3p = "";
            String str5c = "";
            int dec3p = 0;
            int dec5c = 0;

            byte[] tagId = tag.getId();
            StringBuilder sb = new StringBuilder();
            sb.append(toHex(tagId)).append('\n');
            str = sb.toString().replaceAll("\\s+", "").replace(" ", "").toUpperCase();

            str3p = str.substring(2,4);
            dec3p = hex2Decimal(str3p);
            str5c = str.substring(4,8);
            dec5c = hex2Decimal(str5c);
            StringBuilder sb1 = new StringBuilder();
            if (dec3p < 10) {
                sb1.append('0');
                sb1.append('0');
                sb1.append(hex2Decimal(str3p));
            }else if (dec3p < 100) {
                sb1.append('0');
                sb1.append(hex2Decimal(str3p));
            }else{
                sb1.append(hex2Decimal(str3p));
            }
            if (dec5c < 10) {
                sb1.append('0');
                sb1.append('0');
                sb1.append('0');
                sb1.append('0');
                sb1.append(hex2Decimal(str5c));
            }else if (dec5c < 100) {
                sb1.append('0');
                sb1.append('0');
                sb1.append('0');
                sb1.append(hex2Decimal(str5c));
            }else if (dec5c < 1000) {
                sb1.append('0');
                sb1.append('0');
                sb1.append(hex2Decimal(str5c));
            }else if (dec5c < 10000) {
                sb1.append('0');
                sb1.append(hex2Decimal(str5c));
            }else{
                sb1.append(hex2Decimal(str5c));
            }
            str1 =sb1.toString();


            tagData = str1;
            cardid.setText(tagData);

        }
    }

    private int hex2Decimal(String s) {
        String digits = "0123456789ABCDEF";
        s = s.toUpperCase();
        int val = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int d = digits.indexOf(c);
            val = 16*val + d;
        }
        return val;
    }



    private void bindWidgetsWithEvent() {
        btnDML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onButtonClick();
//                getAllWidgets();

                CardId=cardid.getText().toString();
                CardNo=cardno.getText().toString();
//                CardType=cardtype.getText().toString();
                RegisterNewCard(Id,CardId,CardNo,item);
            }
        });
    }
//    private void getAllWidgets() {

//    }

//    private void checkForRequest() {
//        String request = getIntent().getExtras().get(Constants.DML_TYPE).toString();
//        if (request.equals(Constants.UPDATE)) {
//            btnDML.setText(Constants.UPDATE);
//            cardid.setText(getIntent().getExtras().get(Constants.CARD_ID).toString());
//            cardno.setText(getIntent().getExtras().get(Constants.CARD_NO).toString());
//            cardtype.setText(getIntent().getExtras().get(Constants.CARD_TYPE).toString());
//        } else {
//           //btnDML .setText(Constants.INSERT);
//        }
//    }
    public void  RegisterNewCard(final String CardId ,
                                 final String CardNo,
                                 final String CardType,
                                 final String Id

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

//                    jsonObject.accumulate("admin_id",Id);
//                    jsonObject.accumulate("card_id",CardId);
//                    jsonObject.accumulate("card_no",CardNo);
//                    jsonObject.accumulate("card_type",CardType);

                    jsonObject.accumulate("admin_id",CardId);
                    jsonObject.accumulate("card_id",CardNo);
                    jsonObject.accumulate("card_no",CardType);
                    jsonObject.accumulate("card_type",Id);




                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(BaseUrl+"/card_create.php");
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
                       // String id=jsonObject.getString("id");
                            Toast.makeText(TableManipulationActivity.this, message, Toast.LENGTH_LONG).show();
                            Intent intent= new Intent(TableManipulationActivity.this, DataBaseActivity.class);
                            startActivity(intent);
                          /*  SharedPreferences.Editor edit = SM1.edit();
                            edit.putBoolean("adminlogin", true);
                            edit.putString("id",id);
                            edit.commit();*/


                    }
                    else if (jsonObject.getString("error").equalsIgnoreCase("true")) {
                        String  message = jsonObject.getString("message");
                        if (message != null) {
                            {
                                System.out.println("===========================error");
                                Toast.makeText(TableManipulationActivity.this,message, Toast.LENGTH_LONG).show();
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


//    class GetCardDetail extends AsyncTask<Void, Void, Void> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
////             Showing progress dialog
//
//
//        }
//
//        @Override
//        protected Void doInBackground(Void... arg0) {
//            HttpHandler sh = new HttpHandler();
//
//            // Making a request to url and getting response
//            String jsonStr = sh.makeServiceCall(url);
//
////            Log.e(TAG, "Response from url: " + jsonStr);
//
//            if (jsonStr != null) {
//                try {
//                    JSONObject jsonObj = new JSONObject(jsonStr);
//
//                    // Getting JSON Array node
//                    JSONArray contacts = jsonObj.getJSONArray("message");
//
//                    // looping through All Contacts
//                    for (int i = 0; i < contacts.length(); i++) {
//                        JSONObject c = contacts.getJSONObject(i);
//                        students.add(c.getString("card_type"));
//                        // adding contact to contact list
//
//                    }
//
//                } catch (final JSONException e) {
////                    Log.e(TAG, "Json parsing error: " + e.getMessage());
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(getApplicationContext(),
//                                    "Json parsing error: " + e.getMessage(),
//                                    Toast.LENGTH_LONG)
//                                    .show();
//                        }
//                    });
//
//                }
//            } else {
////                Log.e(TAG, "Couldn't get json from server.");
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(getApplicationContext(),
//                                "Couldn't get json from server. Check LogCat for possible errors!",
//                                Toast.LENGTH_LONG)
//                                .show();
//                    }
//                });
//
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            super.onPostExecute(result);
//            // Dismiss the progress dialog
//            spinner.setAdapter(new ArrayAdapter<String>(TableManipulationActivity.this, android.R.layout.simple_spinner_dropdown_item, students));
//
////            tvNoRecordsFound.setVisibility(View.INVISIBLE);
//
//            /**
//             * Updating parsed JSON data into ListView
//             * */
//
//        }
//    }
public void  RegCardType(final String Id

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





            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(BaseUrl+"/show_card_type.php");
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
                    JSONArray contacts = jsonObject.getJSONArray("message");

                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        students.add(c.getString("card_type"));

                        spinner.setAdapter(new ArrayAdapter<String>(TableManipulationActivity.this, android.R.layout.simple_spinner_dropdown_item, students));

                    }
                }
                else if (jsonObject.getString("error").equalsIgnoreCase("true")) {
                    String  message = jsonObject.getString("message");
                    if (message != null) {
                        {
                            System.out.println("===========================error");
                            Toast.makeText(TableManipulationActivity.this,message, Toast.LENGTH_LONG).show();
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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Setting the values to textviews for a selected item

         item = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


//    private void onButtonClick() {
//      // cardId.setText(idfromreader);
//        if (cardId.getText().toString().equals("") || CardNo.getText().toString().equals("")||CardType.getText().toString().equals("")) {
//            Toast.makeText(getApplicationContext(), getString(R.string.Add_All_Fields), Toast.LENGTH_LONG).show();
//        } else {
//            Intent intent = new Intent();
//            intent.putExtra(Constants.CARD_ID, cardId.getText().toString());
//
//            intent.putExtra(Constants.CARD_NO, CardNo.getText().toString());
//            intent.putExtra(Constants.CARD_TYPE, CardType.getText().toString());
//            setResult(RESULT_OK, intent);
//            finish();
//        }


    @Override
    public void onConnect(String s) {
        System.out.println("on connect");
        Log.d("cccccaon","Onconect");
        Toast.makeText(TableManipulationActivity.this,"DeviceConnectedReadingCardId",Toast.LENGTH_LONG).show();
        timer = new Timer();
        //initialize the TimerTask's job

        initializeTimerTask();

        //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms

        timer.schedule(timerTask, 500, 1000);
    }

    @Override
    public void onDisconnect() {
        Log.d("cccccaon","Ondisconect");
    }

    @Override
    public void onStatusChange(IvrJackStatus ivrJackStatus) {

    }
    public void initializeTimerTask() {

        timerTask = new TimerTask() {
            public void run() {

                //use a handler to run a toast that shows the current timestamp
                handler.post(new Runnable() {
                    public void run() {

                        final byte[] blockNo = getBlockNo();
                        if (blockNo == null) {
                            // Toast.makeText(this, "Wrong BlockNo Format", Toast.LENGTH_LONG).show();
                            return;
                        }

                        new Thread() {
                            @Override
                            public void run() {
                                String info;

                                final IvrJackService.TagBlock tagBlock = new IvrJackService.TagBlock();
                                do {
                                    final IvrJackService.TagUid tagUid = new IvrJackService.TagUid();
                                    int ret = service.readTagUid(tagUid);
                                    if (ret != 0) {
                                        info = "Device read block failed: " + ret;
                                        break;
                                    }
                                    if (tagUid.uid == null) {
                                        info = "Device read block failed: no tag";
                                        break;
                                    }
                                    // 鐣岄潰鏇存柊UID
//                                    runOnUiThread(new Runnable() {
//                                        @Override
//                                        public void run() {
                                    byte[] tag=tagUid.uid;
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(toHex(tag)).append('\n');
                                    String strdd =sb.toString().replaceAll("\\s+","").replace(" ","").toUpperCase();

                                    String str3p = "";
                                    String str5c = "";
                                    String str1 = "";
                                    int dec3p = 0;
                                    int dec5c = 0;
                                    str3p = strdd.substring(2,4);
                                    dec3p = hex2Decimal(str3p);
                                    str5c = strdd.substring(4,8);
                                    dec5c = hex2Decimal(str5c);
                                    StringBuilder sb1 = new StringBuilder();
                                    if (dec3p < 10) {
                                        sb1.append('0');
                                        sb1.append('0');
                                        sb1.append(hex2Decimal(str3p));
                                    }else if (dec3p < 100) {
                                        sb1.append('0');
                                        sb1.append(hex2Decimal(str3p));
                                    }else{
                                        sb1.append(hex2Decimal(str3p));
                                    }
                                    if (dec5c < 10) {
                                        sb1.append('0');
                                        sb1.append('0');
                                        sb1.append('0');
                                        sb1.append('0');
                                        sb1.append(hex2Decimal(str5c));
                                    }else if (dec5c < 100) {
                                        sb1.append('0');
                                        sb1.append('0');
                                        sb1.append('0');
                                        sb1.append(hex2Decimal(str5c));
                                    }else if (dec5c < 1000) {
                                        sb1.append('0');
                                        sb1.append('0');
                                        sb1.append(hex2Decimal(str5c));
                                    }else if (dec5c < 10000) {
                                        sb1.append('0');
                                        sb1.append(hex2Decimal(str5c));
                                    }else{
                                        sb1.append(hex2Decimal(str5c));
                                    }
                                    str1 =sb1.toString();

                                    tagData = str1;
                                    //tagData=strdd;
                                    Log.d("huhuj","  "+strdd);
                                   // tagData=bytes2hex(tagUid.uid, "");

                                    if (!tagData.equals("")) {
                                   //  Toast.makeText(TableManipulationActivity.this,"Card Id read ",Toast.LENGTH_LONG).show();

                                        idfromreader=tagData;
                                        Log.d("fki", "jk" + tagData+"   "+idfromreader);
                                        Log.d("fki", "jkxxx" );
                                        handler.post(new Runnable() {
                                            public void run() {

                                                cardid.setText(idfromreader);
                                            }
                                        });
                                        //  Intent intent = new Intent(ChooserActivity.this, SecondActivity.class);
                                        break;
                                    };

//
                                } while (false);



                            }
                        }.start();


                    }
                });
            }
        };

    }
    private String toHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = bytes.length - 1; i >= 0; --i) {
            int b = bytes[i] & 0xff;
            if (b < 0x10)
                sb.append('0');
            sb.append(Integer.toHexString(b));
            if (i > 0) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    @Override
    protected void onResume() {
        super.onResume();
        service.open();
        Log.d("cccccaon","onResume");
        System.out.println("open");
        if (mAdapter != null) {
            if (!mAdapter.isEnabled()) {
                showWirelessSettingsDialog();
            }
            mAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        service.close();
        Log.d("cccccaon","onPausse");
        System.out.println("close");
        if (mAdapter != null) {
            mAdapter.disableForegroundDispatch(this);
        }
    }
    public static void showWirelessSettingsDialog(final Context context) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setMessage(R.string.nfc_disabled);
        builder.setCancelable(false);
        builder.setTitle(R.string.disable);
        builder.setPositiveButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(
                                Settings.ACTION_WIRELESS_SETTINGS);
                        context.startActivity(intent);
                    }
                });
        builder.create().show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("cccccaon","onDestroy");
    }
    private byte[] getBlockNo() {
        byte[] blockNo = new byte[1];
        try {
            //    blockNo[0] = (byte)(Integer.parseInt(viewBlockNo.getText().toString()));
        } catch (NumberFormatException e) {
            return null;
        }
        return blockNo;
    }
    private void showWirelessSettingsDialog() {
        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setMessage(R.string.nfc_disabled);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                startActivity(intent);
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                //  onBackPressed();
                builder.setCancelable(true);
            }
        });
        builder.create().show();
        return;
    }

}
