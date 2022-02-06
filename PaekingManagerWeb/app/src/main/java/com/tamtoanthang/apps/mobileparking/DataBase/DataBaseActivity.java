package com.tamtoanthang.apps.mobileparking.DataBase;
//http://www.coderefer.com/android-upload-file-to-server/

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tamtoanthang.apps.mobileparking.Adapter.CardAdapter;
import com.tamtoanthang.apps.mobileparking.Admin.AdminArea;
import com.tamtoanthang.apps.mobileparking.Model.Card;
import com.tamtoanthang.apps.mobileparking.Model.Constants;
import com.tamtoanthang.apps.mobileparking.Model.Price;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.R.attr.data;


public class DataBaseActivity extends ActionBarActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 111;
    private static final int REQUEST_READ_PERMISSION = 786;
    ProgressDialog pDialog;
    Dialog dialog;
    private static String url = "http://parking.lueinfoservices.com/manage_card.php";
    private String selectedFilePath;
    public static final String[] PERMISSIONS_EXTERNAL_STORAGE = {
            READ_EXTERNAL_STORAGE,
            WRITE_EXTERNAL_STORAGE
    };

    public static final String TABLE_NAME = "CARD";
    private static final int SHOW_PROCESS_BAR = 1;
    private static final int HIDE_PROCESS_BAR = 0;
    private static final int REQUEST_EXTERNAL_PERMISSION_CODE = 11;
    File yourFile;
    String TextFile,Id,BaseUrl;
    String CType,CNo,CId,Uid;
    Button btnAddNewRecord, browse;
    ListView listView;
    SQLiteHelper sQLiteHelper;
    Activity activity;
    android.widget.LinearLayout parentLayout;
    LinearLayout layoutDisplayPeople;
    TextView tvNoRecordsFound;
    private String rowID = null;
    public static final int PICKFILE_RESULT_CODE = 3;
    private ProgressDialog progress;
    SharedPreferences SM1,Bu;
    private static final int PICK_FILE_REQUEST = 1;
    public static final int HIDE_PROCESS_DIALOG = 2;
    public static final int SHOW_PROCESS_DIALOG = 1;
    private ArrayList<HashMap<String, String>> tableData = new ArrayList<HashMap<String, String>>();
//    ArrayList<HashMap<String, String>> CardDetail;

    public ArrayAdapter<Card> adapter;
    public ArrayList<Card> CardDetail=new ArrayList<Card>();

    ProgressBar processbar;

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(DataBaseActivity.this, AdminArea.class);
        startActivity(intent);
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acativity_card_detail);

        SM1 =getSharedPreferences("Myid", Context.MODE_PRIVATE);
        Id=SM1.getString("id","");

        Bu = getSharedPreferences("Base", Context.MODE_PRIVATE);
        BaseUrl = Bu.getString("baseurl", "");
        UserCardDetail(Id);

        processbar = (ProgressBar) findViewById(R.id.pb_cot);
        listView = (ListView) findViewById(R.id.card_detail);
//        CardDetail = new ArrayList<>();
        adapter = new CardAdapter(DataBaseActivity.this, R.layout.list, CardDetail);
        adapter.notifyDataSetChanged();
        listView.invalidateViews();
        adapter.notifyDataSetInvalidated();
        adapter.clear();


        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Log.d("detailsactivity", "  " + "first");
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Log.d("detailsactivity", "  " + "if");
            } else {

                // No explanation needed, we can request the permission.
                Log.d("detailsactivity", "  " + "elase");
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

            }
        }
//        SM = DataBaseActivity.this.getSharedPreferences("CardRecord", 0);

        getAllWidgets();
        sQLiteHelper = new SQLiteHelper(DataBaseActivity.this);
        bindWidgetsWithEvent();
        Log.d("sjjsnj", "djhj");


        browse.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
//                 checkExternalStoragePermission(DataBaseActivity.this);
//                requestPermission();
//                uploadFile(selectedFilePath);

                showFileChooser();
//                UploadToserver();


            }


        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {


                TextView uid=(TextView)view.findViewById(R.id.id);
                TextView cid=(TextView)view.findViewById(R.id.card_id);
                TextView cno=(TextView)view.findViewById(R.id.card_no);
                TextView ctype=(TextView)view.findViewById(R.id.card_type);
                CId= cid.getText().toString();
                CNo= cno.getText().toString();
                CType= ctype.getText().toString();

                Uid=uid.getText().toString();
                System.out.println("==========CId==========="+CId);
                System.out.println("==========CNo==========="+CNo);
                System.out.println("==========CType==========="+CType);
                Uid=uid.getText().toString();
//                Toast.makeText(DataBaseActivity.this,Uid,Toast.LENGTH_SHORT).show();


                ImageView edit=(ImageView)view.findViewById(R.id.edit);
                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

//                        ((BaseAdapter) mMyListView.getAdapter()).notifyDataSetChanged();

                        SenId(Uid);
                        Dupdate_show();



                    }
                });
                ImageView delete=(ImageView)view.findViewById(R.id.delete);
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        SenId(Uid);
                        Delete_show();
                    }
                });


            }


        });
    }


    public void Dupdate_show()
    {
//        updateDataAsync();

        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.fragment_cardupdate, null);
        final EditText update_cardId = (EditText) promptsView.findViewById(R.id.update_cardid);
        update_cardId.setText(CId);
        final EditText update_cardNo=(EditText)promptsView.findViewById(R.id.update_cardno);
        update_cardNo.setText(CNo);
        final EditText update_cardType=(EditText)promptsView.findViewById(R.id.update_cardtype);
        update_cardType.setText(CType);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);


        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                // get user input and set it to result
                                // edit text
//                                result.setText(userInput.getText());
                                SM1 =getSharedPreferences("Myid", Context.MODE_PRIVATE);
                                Id=SM1.getString("id","");
                                String Update_carId= update_cardId.getText().toString();
                                System.out.println("=======Update_carId==========="+Update_carId);
                                String  Update_cardNo=update_cardNo.getText().toString();
                                System.out.println("=======Update_cardPrice==========="+Update_cardNo);
                                String Update_cardType=update_cardType.getText().toString();
                                System.out.println("=======Update_cardType==========="+Update_cardType);


                                Update(Id,Uid,Update_carId,Update_cardNo,Update_cardType);
                                adapter.clear();


                                Toast.makeText(DataBaseActivity.this,"Card  Is Updated",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(DataBaseActivity.this, DataBaseActivity.class);
                                startActivity(intent);


                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
//                                adapter.clear();
//                                adapter.addAll();
//                                adapter.notifyDataSetChanged();


                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }
    public void Delete_show()
    {
//        updateDataAsync();

        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.delete_card, null);
       final TextView textView=(TextView)promptsView.findViewById(R.id.cardtxt);
        textView.setText("    Do You Want To Delete");
//        final EditText update_cardId = (EditText) promptsView.findViewById(R.id.update_cardid);
//        update_cardId.setText(CId);
//        final EditText update_cardNo=(EditText)promptsView.findViewById(R.id.update_cardno);
//        update_cardNo.setText(CNo);
//        final EditText update_cardType=(EditText)promptsView.findViewById(R.id.update_cardtype);
//        update_cardType.setText(CType);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
//
//        // set prompts.xml to alertdialog builder
//        alertDialogBuilder.setView(promptsView);


        // set dialog message
        alertDialogBuilder
                .setMessage("      Do You Want To Delete?")
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                // get user input and set it to result
                                // edit text
//                                result.setText(userInput.getText());



                                Delete(Uid);
                                adapter.clear();

                                Toast.makeText(DataBaseActivity.this,"Card Is Deleted",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(DataBaseActivity.this, DataBaseActivity.class);
                                startActivity(intent);


                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
//                                adapter.clear();
//                                adapter.addAll();
//                                adapter.notifyDataSetChanged();


                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }
    public void  SenId(final String UId



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

                    jsonObject.accumulate("update_id",UId);





                } catch (JSONException e) {
                    e.printStackTrace();
                }


                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    SharedPreferences Bu = getSharedPreferences("Base", Context.MODE_PRIVATE);
                    String BaseUrl = Bu.getString("baseurl", "");
                    HttpPost httpPost = new HttpPost(BaseUrl+"/card_update_id.php");
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

//                        String  message = jsonObject.getString("message");
                        JSONArray carddetail=jsonObject.getJSONArray("message");
                        for(int i=0;i<carddetail.length();i++)
                        {
                            JSONObject c=carddetail.getJSONObject(i);
                           String cid=c.getString("id");
                            System.out.println("======cid======"+cid);
                            String cardid=c.getString("card_id");
                           String cardno=c.getString("card_no");
                           String cardtype=c.getString("card_type");


//                            Card card=new Card();
//                            card.setId(cid);
//                            card.setCardId(cardid);
//                            card.setCardNo(cardno);
//                            card.setCardType(cardtype);
//                            CardDetail.add(card);

                        }

//                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();

                    }

                    else if (jsonObject.getString("error").equalsIgnoreCase("true")) {
                        String  message = jsonObject.getString("message");
                        if (message != null) {
                            {
                                System.out.println("===========================error");
                                Toast.makeText(DataBaseActivity.this,message, Toast.LENGTH_LONG).show();
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
    //===============================================Update===================================
    public void  Update(final String Id,
                        final String UId,
                        final String Update_carId,
                        final String Update_cardNo,
                        final String Update_cardType





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
                    jsonObject.accumulate("update_id",UId);
                    jsonObject.accumulate("card_id",Update_carId);
                    jsonObject.accumulate("card_no",Update_cardNo);
                    jsonObject.accumulate("card_type",Update_cardType);






                } catch (JSONException e) {
                    e.printStackTrace();
                }


                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    SharedPreferences Bu = getSharedPreferences("Base", Context.MODE_PRIVATE);
                    String BaseUrl = Bu.getString("baseurl", "");
                    HttpPost httpPost = new HttpPost(BaseUrl+"/card_update.php");
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

//                        String  message = jsonObject.getString("message");
                        JSONArray carddetail=jsonObject.getJSONArray("message");
//                        for(int i=0;i<carddetail.length();i++)
//                        {
//                            JSONObject c=carddetail.getJSONObject(i);
//                            cardid=c.getString("id");
//                            cardtype=c.getString("card_type");
//                            cardprice=c.getString("card_price");
//
//                            Price price=new Price();
//                            price.setId(cardid);
//                            price.setCardId(cardtype);
//                            price.setCardType(cardprice);
//                            priceList.add(price);
//
//                        }

//                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();

                    }

                    else if (jsonObject.getString("error").equalsIgnoreCase("true")) {
                        String  message = jsonObject.getString("message");
                        if (message != null) {
                            {
                                System.out.println("===========================error");
                                Toast.makeText(DataBaseActivity.this,message, Toast.LENGTH_LONG).show();
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

    public void  Delete(final String UId



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

                    jsonObject.accumulate("delete_id",UId);





                } catch (JSONException e) {
                    e.printStackTrace();
                }


                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    SharedPreferences Bu = getSharedPreferences("Base", Context.MODE_PRIVATE);
                    String BaseUrl = Bu.getString("baseurl", "");
                    HttpPost httpPost = new HttpPost(BaseUrl+"/card_delete.php");
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
                        Toast.makeText(DataBaseActivity.this,message,Toast.LENGTH_SHORT).show();
//                        String  message = jsonObject.getString("message");
//                        JSONArray carddetail=jsonObject.getJSONArray("message");

//                        for(int i=0;i<carddetail.length();i++)
//                        {
//                            JSONObject c=carddetail.getJSONObject(i);
//                            String cid=c.getString("id");
//                            System.out.println("======cid======"+cid);
//                            String cardid=c.getString("card_id");
//                            String cardno=c.getString("card_no");
//                            String cardtype=c.getString("card_type");
//

//                            Card card=new Card();
//                            card.setId(cid);
//                            card.setCardId(cardid);
//                            card.setCardNo(cardno);
//                            card.setCardType(cardtype);
//                            CardDetail.add(card);

                        }

//                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();

//                    }

                    else if (jsonObject.getString("error").equalsIgnoreCase("true")) {
                        String  message = jsonObject.getString("message");
                        if (message != null) {
                            {
                                System.out.println("===========================error");
                                Toast.makeText(DataBaseActivity.this,message, Toast.LENGTH_LONG).show();
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
    private void showFileChooser() {
        Intent intent = new Intent();
        //sets the select file to all types of files
        intent.setType("text/plain");

        //allows to select data and return it
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //starts new activity to select file and return data
        startActivityForResult(Intent.createChooser(intent, "Choose File to Upload.."), PICK_FILE_REQUEST);
//        UploadToserver();


    }




    //android upload file to server

//    class GetCardDetail extends AsyncTask<Void, Void, Void> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
////             Showing progress dialog
//            pDialog = new ProgressDialog(DataBaseActivity.this);
//            pDialog.setMessage("Please wait...");
//            pDialog.setCancelable(false);
//            pDialog.show();
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
//
//                        String id = c.getString("card_id");
//                        String no = c.getString("card_no");
//                        String type = c.getString("card_type");
//                        // tmp hash map for single contact
//                        HashMap<String, String> Card = new HashMap<>();
//
//                        // adding each child node to HashMap key => value
//                        Card.put("card_id", id);
//                        Card.put("card_no", no);
//                        Card.put("card_type", type);
//
//
//                        // adding contact to contact list
//                        CardDetail.add(Card);
//                    }
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
//            if (pDialog.isShowing())
//                pDialog.dismiss();
////            tvNoRecordsFound.setVisibility(View.INVISIBLE);
//
//            /**
//             * Updating parsed JSON data into ListView
//             * */
//            ListAdapter adapter = new SimpleAdapter(
//                    DataBaseActivity.this, CardDetail,
//                    R.layout.list, new String[]{"card_id", "card_no",
//                    "card_type"}, new int[]{R.id.card_id,
//                    R.id.card_no, R.id.card_type});
//
//            listView.setAdapter(adapter);
//        }
//    }
public void  UserCardDetail(final String Id



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
                HttpPost httpPost = new HttpPost(BaseUrl+"/manage_card.php");
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
            CardDetail.clear();
            try {
                JSONObject jsonObject=new JSONObject(result);
                if (jsonObject.getString("error").equalsIgnoreCase("false")) {

                    JSONArray userdetail=jsonObject.getJSONArray("message");
                    for (int i=0;i<userdetail.length();i++)
                    {
                        JSONObject c=userdetail.getJSONObject(i);
                        String id=c.getString("id");
                        String cid = c.getString("card_id");
                        String no = c.getString("card_no");
                        String type=c.getString("card_type");


                        Card card=new Card();
                        card.setId(id);
                        card.setCardId(cid);
                        card.setCardNo(no);
                        card.setCardType(type);
                        CardDetail.add(card);
                        listView.setAdapter(adapter);





                    }


//                        Toast.makeText(getActivity(), Total_Amount+Total_CarIn+Total_CarOut, Toast.LENGTH_SHORT).show();






                }

                else if (jsonObject.getString("error").equalsIgnoreCase("true")) {
                    String  message = jsonObject.getString("message");
                    if (message != null) {
                        {
                            System.out.println("===========================error");
                            Toast.makeText(DataBaseActivity.this,message, Toast.LENGTH_LONG).show();
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
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_READ_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//             openFilePicker();
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("text/plain");

            startActivityForResult(intent, PICKFILE_RESULT_CODE);
        }
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.d("StringTA", " " + "if");
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_READ_PERMISSION);
        } else {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("text/plain");
            Log.d("StringTA", " " + "if");
            startActivityForResult(intent, PICKFILE_RESULT_CODE);

        }
    }


    private void getAllWidgets() {
        btnAddNewRecord = (Button) findViewById(R.id.btnAddNewRecord);
        parentLayout = (LinearLayout) findViewById(R.id.parentLayout);
        layoutDisplayPeople = (LinearLayout) findViewById(R.id.layoutDisplayPeople);

//        tvNoRecordsFound = (TextView) findViewById(R.id.tvNoRecordsFound);
        browse = (Button) findViewById(R.id.browse);

    }

    private void bindWidgetsWithEvent() {
        btnAddNewRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddRecord();
            }
        });

    }

 public  void UploadToserver() {
        if (selectedFilePath != null) {
            dialog = ProgressDialog.show(DataBaseActivity.this, "", "Uploading File...", true);

//            new Thread(new Runnable() {
//                @Override
//                public void run() {
                    //creating new thread to handle Http Operations
                    Register(TextFile);
                    dialog.hide();
                    Toast.makeText(DataBaseActivity.this, " File uploaded successfully", Toast.LENGTH_SHORT).show();


//                }
//            }).start();
        } else {
            Toast.makeText(DataBaseActivity.this, "Please choose a File First", Toast.LENGTH_SHORT).show();
        }

    }

    private void onAddRecord() {

        SharedPreferences.Editor edit = SM1.edit();
        edit.putBoolean("userlogin", true);
        Intent intent = new Intent(DataBaseActivity.this, TableManipulationActivity.class);
        intent.putExtra(Constants.DML_TYPE, Constants.INSERT);

        startActivityForResult(intent, Constants.ADD_RECORD);

    }


//    private void onUpdateRecord(String cardId, String cardNo, String CardType) {
//        Intent intent = new Intent(DataBaseActivity.this, TableManipulationActivity.class);
//        intent.putExtra(Constants.CARD_ID, cardId);
//        intent.putExtra(Constants.CARD_NO, cardNo);
//        intent.putExtra(Constants.CARD_TYPE, CardType);
//        intent.putExtra(Constants.DML_TYPE, Constants.UPDATE);
//        startActivityForResult(intent,
//                Constants.UPDATE_RECORD);
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == RESULT_OK) {
//            String CardType = data.getStringExtra(Constants.CARD_TYPE);
//            String CardId = data.getStringExtra(Constants.CARD_ID);
//            String CardNo = data.getStringExtra(Constants.CARD_NO);
//
//
//            ContactModel contact = new ContactModel();
//            contact.setCardNo(CardNo);
//            contact.setCardId(CardId);
//            contact.setCardType(CardType);
//            Log.d("user", "" + contact.getIntime());
//
//            if (requestCode == Constants.ADD_RECORD) {
//                ArrayList<ContactModel> list = sQLiteHelper.getAllRecords();
//
//
//                if (list.size() == 0) {
//                    sQLiteHelper.insertRecord(contact);
//                } else {
//
//                    if (contains(list, CardNo)) {
//                        Toast.makeText(getApplicationContext(), "Data Exist", Toast.LENGTH_LONG).show();
//                        Log.d("datadeatils", " " + "Data Exist(s)");
//                        System.out.println("Data Exist(s)");
//                    } else {
//                        sQLiteHelper.insertRecord(contact);
//                        Log.d("datadeatils", " " + "Not Exist(s)");
//                        System.out.println("Not Exist(s)");
//                    }
//
//
//                }
//            } else if (requestCode == Constants.UPDATE_RECORD) {
//                contact.setID(rowID);
//                // sQLiteHelper.updateRecord(CardId, CardNo,CardType, rowID);
//                sQLiteHelper.updateRecord(contact);
//            } else if (requestCode == PICKFILE_RESULT_CODE) {
//                handler.sendEmptyMessage(SHOW_PROCESS_BAR);
//                StringBuilder returnString = new StringBuilder();
//                InputStream fIn = null;
//                InputStreamReader isr = null;
//                BufferedReader input = null;
//
//                String FilePath = data.getData().getPath();
//
//                Log.d("NJJJ", "" + FilePath);
//                BufferedReader br = null;
//                FileInputStream fis = null;
//
//                File file = null;
//                try {
//                    file = new File( FilePath); // Pass getFilesDir() and "MyFile" to read file
//
//                    br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
//                    /*try {
//                        fIn = getApplicationContext().getResources().getAssets().open("the test.txt", Context.MODE_WORLD_READABLE);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    isr = new InputStreamReader(fIn);
//                    br = new BufferedReader(isr);*/
//                    String line = null;
//                    System.out.println("Card ID  Card No  Card Type");
//
//                    try {
//                        if (br != null) {
//                            Log.d("working", "  " + "working");
//
//                            while ((line = br.readLine()) != null) {
//                                String datafile[] = line.split("\t");
//                                sQLiteHelper.insertRecord(new ContactModel(datafile[0], datafile[1], datafile[2]));
//
//                                displayAllRecords();
//
//                            }
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } finally {
//                        if ((br != null)) {
//                            try {
//                                br.close();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//
//
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//                displayAllRecords();
//            }

       /* if (resultCode == RESULT_OK) {
            String CardType = data.getStringExtra(Constants.CARD_TYPE);
            String CardId = data.getStringExtra(Constants.CARD_ID);
            String CardNo = data.getStringExtra(Constants.CARD_NO);

            ContactModel contact = new ContactModel();
            contact.setCardId(CardId);
            contact.setCardNo(CardNo);
            contact.setCardType(CardType);

            if (requestCode == Constants.ADD_RECORD) {

                ArrayList<ContactModel> list = sQLiteHelper.getAllRecords();


                if (list.size() == 0) {
                    sQLiteHelper.insertRecord(contact);
                } else {

                    if (contains(list, CardNo)) {
                        Toast.makeText(getApplicationContext(), "Data Exist", Toast.LENGTH_LONG).show();
                        Log.d("datadeatils", " " + "Data Exist(s)");
                        System.out.println("Data Exist(s)");
                    } else {
                        sQLiteHelper.insertRecord(contact);
                        Log.d("datadeatils", " " + "Not Exist(s)");
                        System.out.println("Not Exist(s)");
                    }


                    displayAllRecords();
                }
            } else if (requestCode == Constants.UPDATE_RECORD) {
                contact.setID(rowID);
                 Toast.makeText(getApplicationContext(),""+contact.getCardId(),Toast.LENGTH_LONG).show();
                sQLiteHelper.updateRecord(contact);
            }
            else if (requestCode == PICKFILE_RESULT_CODE) {
                handler.sendEmptyMessage(SHOW_PROCESS_BAR);

                StringBuilder returnString = new StringBuilder();
                InputStream fIn = null;
                InputStreamReader isr = null;
                BufferedReader input = null;

                String FilePath = data.getData().getPath();

                Log.d("NJJJ", "" + FilePath);
                BufferedReader br = null;
                FileInputStream fis = null;

                try {

                    fIn = getApplicationContext().getResources().getAssets().open("the test.txt", Context.MODE_WORLD_READABLE);
                    isr = new InputStreamReader(fIn);
                    br = new BufferedReader(isr);
                    Log.d("fdjknjv", "" + isr + "  " + br);
                    String line = null;
                    System.out.println("Card ID  Card No  Card Type");

                    try {
                        if (br != null) {
                            Log.d("working", "  " + "working");

                            while ((line = br.readLine()) != null) {
                                String datafile[] = line.split("\t");
                                sQLiteHelper.insertRecord(new ContactModel(datafile[0], datafile[1], datafile[2]));

                                displayAllRecords();

                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if ((br != null)) {
                            try {
                                br.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }*/
//             displayAllRecords();


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
//            if (requestCode == PICKFILE_RESULT_CODE) {
                if (data == null) {
                    //no data present
                    return;
                }


//                Uri uri = data.getData();
//                selectedFilePath=uri.getPath();
                Uri selectedFileUri = data.getData();
                selectedFilePath = FilePath.getPath(this, selectedFileUri);
              TextFile =encodeFile(selectedFilePath);
                System.out.println("=====selectedFilePath=========="+selectedFilePath);


//                Log.i(TAG, "Selected File Path:" + selectedFilePath);
                Toast.makeText(DataBaseActivity.this, "Selected File Path:" + selectedFilePath, Toast.LENGTH_SHORT).show();

                if (selectedFilePath != null && !selectedFilePath.equals("")) {
//                    uploadFile(selectedFilePath);
                    UploadToserver();

                } else {
                    Toast.makeText(this, "Cannot upload file to server", Toast.LENGTH_SHORT).show();
                }
            }
        }
//    }

//    private String encodeFileToBase64Binary(File fileName)
//            throws IOException {
//
//        File file = new File(String.valueOf(fileName));
//        byte[] bytes = loadFile(file);
//        String encodedImage = Base64.encodeToString(bytes, Base64.DEFAULT);
//        return encodedImage;
//    }
//
//    private static byte[] loadFile(File file) throws IOException {
//        InputStream is = new FileInputStream(file);
//
//        long length = file.length();
//        if (length > Integer.MAX_VALUE) {
//            // File is too large
//        }
//        byte[] bytes = new byte[(int) length];
//
//        int offset = 0;
//        int numRead = 0;
//        while (offset < bytes.length
//                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
//            offset += numRead;
//        }
//
//        if (offset < bytes.length) {
//            throw new IOException("Could not completely read file " + file.getName());
//        }
//
//        is.close();
//        return bytes;
//    }


public String encodeFile(String selectedFilePath) {

    byte[] audioBytes;
    String AudioString = null;
    try {

        // Just to check file size.. Its is correct i-e; Not Zero
        File audioFile = new File(selectedFilePath);
        long fileSize = audioFile.length();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FileInputStream fis = new FileInputStream(new File(selectedFilePath));
        byte[] buf = new byte[1024];
        int n;
        while (-1 != (n = fis.read(buf)))
            baos.write(buf, 0, n);
        audioBytes = baos.toByteArray();

        // Here goes the Base64 string
        AudioString = Base64.encodeToString(audioBytes, Base64.DEFAULT);

    } catch (Exception e) {
        e.printStackTrace();
    }

    return AudioString;

}

    public void Register(final String TextFile

    ) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {

            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected String doInBackground(String... params) {
                String return_text = "";
                JSONObject jsonObject = new JSONObject();
                try {

                    jsonObject.accumulate("file1", TextFile);
                    jsonObject.accumulate("id",Id);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(BaseUrl+"/card_import.php");
                    StringEntity httpiEntity = new StringEntity(jsonObject.toString());
                    httpPost.setEntity(httpiEntity);
                    org.apache.http.HttpResponse response = httpClient.execute(httpPost);
                    return_text = HTTPResponse.readResponse(response);
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
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.getString("error").equalsIgnoreCase("false")) {
                        String message = jsonObject.getString("message");


                        Toast.makeText(DataBaseActivity.this, message, Toast.LENGTH_LONG).show();

                        System.out.println("==========message============"+message);
                    } else if (jsonObject.getString("error").equalsIgnoreCase("true")) {
                        String message = jsonObject.getString("message");
                        if (message != null) {
                            {

                                Toast.makeText(DataBaseActivity.this, message, Toast.LENGTH_LONG).show();
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
        } catch (Exception e) {
        }
    }}

//    private void copyInputStreamToFile( InputStream in, File file ) {
//        try {
//            OutputStream out = new FileOutputStream(file);
//            byte[] buf = new byte[1024];
//            int len;
//            while((len=in.read(buf))>0){
//                out.write(buf,0,len);
//            }
//            out.close();
//            in.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    boolean contains(ArrayList<ContactModel> list, String name) {
//        for (ContactModel item : list) {
//            if (item.getCardNo().equals(name)) {
//                return true;
//            }
//        }
//        return false;
//    }

//    private void displayAllRecords() {
//        handler.sendEmptyMessage(HIDE_PROCESS_BAR);
//
//        com.rey.material.widget.LinearLayout inflateParentView;
//        parentLayout.removeAllViews();
//
//        ArrayList<ContactModel> contacts = sQLiteHelper.getAllRecords();
//
//        if (contacts.size() > 0) {
//            tvNoRecordsFound.setVisibility(View.GONE);
//            ContactModel contactModel;
//            for (int i = 0; i < contacts.size(); i++) {
//
//                contactModel = contacts.get(i);
//
//                final Holder holder = new Holder();
//                final View view = LayoutInflater.from(this).inflate(R.layout.item_lists, null);
//                inflateParentView = (com.rey.material.widget.LinearLayout) view.findViewById(R.id.inflateParentView);
//                holder.tvFullName = (TextView) view.findViewById(R.id.tvFullName);
//
//
//                view.setTag(contactModel.getID());
//                holder.CardId = contactModel.getCardId();
//                holder.CardNo = contactModel.getCardNo();
//                holder.CardType = contactModel.getCardType();
//                String personName = holder.CardId + " " + holder.CardNo+" "+holder.CardType;
//                holder.tvFullName.setText(personName);
//
//                final CharSequence[] items = {Constants.UPDATE,Constants.DELETE};
//                inflateParentView.setOnLongClickListener(new View.OnLongClickListener() {
//                    @Override
//                    public boolean onLongClick(View v) {
//
//                        AlertDialog.Builder builder = new AlertDialog.Builder(DataBaseActivity.this);
//                        builder.setItems(items, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                if (which == 0) {
//
//                                    rowID = view.getTag().toString();
//                                    onUpdateRecord(holder.CardId.toString(), holder.CardNo.toString(),holder.CardType.toString());
//                                } else {
//                                    AlertDialog.Builder deleteDialogOk = new AlertDialog.Builder(DataBaseActivity.this);
//                                    deleteDialogOk.setTitle("Delete Contact?");
//                                    deleteDialogOk.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                                                @Override
//                                                public void onClick(DialogInterface dialog, int which) {
//
//                                                    rowID = view.getTag().toString();
//                                                    ContactModel contact = new ContactModel();
//                                                    contact.setID(view.getTag().toString());
//                                                   sQLiteHelper.deleteCardRecord(rowID);
//                                                    displayAllRecords();
//                                                }
//                                            }
//
//                                    );
//                                    deleteDialogOk.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int which) {
//
//                                        }
//                                    });
//                                    deleteDialogOk.show();
//                                }
//                            }
//                        });
//                        AlertDialog alertDialog = builder.create();
//                        alertDialog.show();
//                        return true;
//                    }
//                });
//
//                parentLayout.addView(view);
//            }
//        } else {
//            tvNoRecordsFound.setVisibility(View.VISIBLE);
//        }


//    private class Holder {
//        TextView tvFullName;
//        String CardType;
//        String CardNo;
//        String CardId;
//    }
//    Handler handler=new Handler(new Handler.Callback() {
//
//
//        @Override
//        public boolean handleMessage(Message message) {
//            switch(message.what){
//                case SHOW_PROCESS_DIALOG :
//                    processbar.setVisibility(View.VISIBLE);
//                    break;
//                case HIDE_PROCESS_DIALOG :
//                    processbar.setVisibility(View.GONE);
//                    break;
//            }
//            return false;
//        }
//    });

