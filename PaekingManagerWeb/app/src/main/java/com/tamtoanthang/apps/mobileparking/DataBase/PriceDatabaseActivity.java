package com.tamtoanthang.apps.mobileparking.DataBase;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.tamtoanthang.apps.mobileparking.Adapter.PriceAdapter;
import com.tamtoanthang.apps.mobileparking.Admin.AdminArea;
import com.tamtoanthang.apps.mobileparking.Model.Price;
import com.tamtoanthang.apps.mobileparking.R;
import com.tamtoanthang.apps.mobileparking.Transaction;
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
import java.util.ArrayList;
import java.util.HashMap;



/**
 * Created by lue on 10-11-2016.
 */
public class PriceDatabaseActivity extends ActionBarActivity {

    Button btnAddNewRecord;
    SQLiteHelper sQLiteHelper;
    private static String url ="http://parking.lueinfoservices.com/manage_price.php";
    android.widget.LinearLayout parentLayout;
    LinearLayout layoutDisplayPeople;
    TextView tvNoRecordsFound;
    ListView listView;
    SharedPreferences SM1,Bu;
    String Id,BaseUrl,Uid,cardid,cardtype,cardprice,name,type;
    String Update_cardType,Update_cardPrice;
    private String rowID = null;
    ArrayList<HashMap<String, String>> CardDetail;
    ProgressDialog pDialog;
    public ArrayAdapter<Price> adapter;
    public ArrayList<Price> priceList=new ArrayList<Price>();

    //    private ArrayList<HashMap<String, String>> tableData = new ArrayList<HashMap<String, String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);


        Bu = getSharedPreferences("Base", Context.MODE_PRIVATE);
        BaseUrl = Bu.getString("baseurl", "");
        btnAddNewRecord = (Button) findViewById(R.id.btnAddNewRecord);

        parentLayout = (LinearLayout) findViewById(R.id.parentLayout);
        layoutDisplayPeople = (LinearLayout) findViewById(R.id.layoutDisplayPeople);
        SM1 = getSharedPreferences("Myid", Context.MODE_PRIVATE);
        Id = SM1.getString("id", "");
        UserCardDetail(Id);
//        tvNoRecordsFound = (TextView) findViewById(R.id.tvNoRecordsFound);
//        new GetCardPrice().execute();
//        tvNoRecordsFound.setVisibility(View.INVISIBLE);

        listView = (ListView) findViewById(R.id.user_card_detail);
        adapter = new PriceAdapter(PriceDatabaseActivity.this, R.layout.item_lists, priceList);
        adapter.notifyDataSetChanged();
        listView.invalidateViews();

//        CardDetail = new ArrayList<>();

//        getAllWidgets();
//        sQLiteHelper = new SQLiteHelper( PriceDatabaseActivity.this);
//        bindWidgetsWithEvent();
//        displayAllRecords();
        btnAddNewRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PriceDatabaseActivity.this, PriceTable.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                TextView tprice=(TextView)view.findViewById(R.id.cardprice);
                TextView ttype=(TextView)view.findViewById(R.id.cardtype);
                    name= tprice.getText().toString();
                     type= ttype.getText().toString();
                System.out.println("==========name==========="+name);
                System.out.println("==========name==========="+type);
                TextView uid=(TextView)view.findViewById(R.id.id);

                  Uid=uid.getText().toString();
//                Toast.makeText(PriceDatabaseActivity.this,Uid,Toast.LENGTH_SHORT).show();


                ImageView edit=(ImageView)view.findViewById(R.id.edit);
                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listView.invalidate();

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



                // Getting the inner Linear Layout


                // Getting the Country TextView
//                TextView tvCountry = (TextView) linearLayoutChild.getChildAt(0);
//                String item = ((LinearLayout) view).getText().toString();

//                Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show();

            }
        });



    }

    public void Dupdate_show()
    {


        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.fragment_update, null);
        final EditText update_cardType = (EditText) promptsView.findViewById(R.id.update_cardtype);
        update_cardType.setText(name);
        final EditText update_cardPrice=(EditText)promptsView.findViewById(R.id.update_card_price);
        update_cardPrice.setText(type);

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
                                Update_cardType= update_cardType.getText().toString();
                                System.out.println("=======Update_cardType==========="+Update_cardType);
                                  Update_cardPrice=update_cardPrice.getText().toString();
                                System.out.println("=======Update_cardPrice==========="+Update_cardPrice);
                                Update(Id,Uid,Update_cardType,Update_cardPrice);

                                Toast.makeText(PriceDatabaseActivity.this,"Card Type And Card Price Is Updated",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(PriceDatabaseActivity.this, PriceDatabaseActivity.class);
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
    //=========================================Delete_Dialog========================
    public void Delete_show()
    {
//        updateDataAsync();

        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.delete_price, null);
        final TextView textView=(TextView)promptsView.findViewById(R.id.pricetxt) ;
        textView.setText("Do You Want To Delete");

//        final EditText update_username = (EditText) promptsView.findViewById(R.id.update_username);
//        update_username.setText(name);
//        final EditText update_password=(EditText)promptsView.findViewById(R.id.update_password);
//        update_password.setText(pass);
////        final EditText update_cardType=(EditText)promptsView.findViewById(R.id.update_cardtype);
////        update_cardType.setText(Uid);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set prompts.xml to alertdialog builder
//        alertDialogBuilder.setView(promptsView);


        // set dialog message
        alertDialogBuilder
                .setMessage("   Do You Want To Delete?")
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

                                Delete(Uid);

                                Toast.makeText(PriceDatabaseActivity.this,"Card Type And Card Price Is Deleted",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(PriceDatabaseActivity.this, PriceDatabaseActivity.class);
                                startActivity(intent);



                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {

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
                    HttpPost httpPost = new HttpPost(BaseUrl+"/price_update_id.php");
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
                            cardid=c.getString("id");
                            cardtype=c.getString("card_type");
                            cardprice=c.getString("card_price");

//                            Price price=new Price();
//                            price.setId(cardid);
//                            price.setCardId(cardtype);
//                            price.setCardType(cardprice);
//                            priceList.add(price);

                        }

//                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();

                    }

                    else if (jsonObject.getString("error").equalsIgnoreCase("true")) {
                        String  message = jsonObject.getString("message");
                        if (message != null) {
                            {
                                System.out.println("===========================error");
                                Toast.makeText(PriceDatabaseActivity.this,message, Toast.LENGTH_LONG).show();
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
                        final String Update_cardType,
                        final String Update_cardPrice





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
                    jsonObject.accumulate("card_type",Update_cardType);
                    jsonObject.accumulate("card_price",Update_cardPrice);






                } catch (JSONException e) {
                    e.printStackTrace();
                }


                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    SharedPreferences Bu = getSharedPreferences("Base", Context.MODE_PRIVATE);
                    String BaseUrl = Bu.getString("baseurl", "");
                    HttpPost httpPost = new HttpPost(BaseUrl+"/price_update.php");
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
                                Toast.makeText(PriceDatabaseActivity.this,message, Toast.LENGTH_LONG).show();
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
    //============================================Delete===============================
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
                    HttpPost httpPost = new HttpPost(BaseUrl+"/price_delete.php");
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

                        String  message = jsonObject.getString("message");
                        Toast.makeText(PriceDatabaseActivity.this,message,Toast.LENGTH_SHORT).show();
//                        JSONArray carddetail=jsonObject.getJSONArray("message");
//                        for(int i=0;i<carddetail.length();i++)
//                        {
//                            JSONObject c=carddetail.getJSONObject(i);
//                            String ucid=c.getString("id");
//                            String username =c.getString("user_name");
//                            String  password=c.getString("user_password");
//
//
//
//                        }

//                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();

                    }

                    else if (jsonObject.getString("error").equalsIgnoreCase("true")) {
                        String  message = jsonObject.getString("message");
                        if (message != null) {
                            {
                                System.out.println("===========================error");
                                Toast.makeText(PriceDatabaseActivity.this,message, Toast.LENGTH_LONG).show();
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





//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//
//            String CardType = data.getStringExtra(Constants.CARD_TYPE);
//            String CardPrice = data.getStringExtra(Constants.CARD_PRICE);
//
//
//            ContactModel contact = new ContactModel();
//            contact.setCardType(CardType);
//            contact.setPrice(CardPrice);
//            Log.d("pricerecord", "  " + CardPrice);
//            if (requestCode == Constants.ADD_RECORD) {
//                //  sQLiteHelper.insertRecord(CardId, CardNo,CardType);
//                sQLiteHelper.insertRecordPrice(contact);
//            } else if (requestCode == Constants.UPDATE_RECORD) {
//                contact.setID(rowID);
//                // sQLiteHelper.updateRecord(CardId, CardNo,CardType, rowID);
//                sQLiteHelper.updateRecordprice(contact);
//            }
//            displayAllRecords();
//        }
//    }
//
//    private void getAllWidgets() {

//    }

//    private void bindWidgetsWithEvent() {

//    }
//
//    private void onAddRecord() {
//        Intent intent = new Intent(PriceDatabaseActivity.this, PriceTable.class);
//        intent.putExtra(Constants.DML_TYPE, Constants.INSERT);
//        startActivityForResult(intent, Constants.ADD_RECORD);
//    }
//
//    private void onUpdateRecord(String CardType, String CardPrice) {
//        Intent intent = new Intent(PriceDatabaseActivity.this, PriceTable.class);
//        intent.putExtra(Constants.CARD_TYPE, CardType);
//        intent.putExtra(Constants.CARD_PRICE, CardPrice);
//        intent.putExtra(Constants.DML_TYPE, Constants.UPDATE);
//        startActivityForResult(intent, Constants.UPDATE_RECORD);
//    }
//
//    private void displayAllRecords() {
//
//        com.rey.material.widget.LinearLayout inflateParentView;
//        parentLayout.removeAllViews();
//
//        ArrayList<ContactModel> contacts = sQLiteHelper.getAllRecordsPrice();
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
//                holder.CardType = contactModel.getCardType();
//                holder.CardPrice = contactModel.getPrice();
//                String personName = holder.CardType + " " + holder.CardPrice;
//                holder.tvFullName.setText(personName);
//
//                final CharSequence[] items = {Constants.UPDATE, Constants.DELETE};
//                inflateParentView.setOnLongClickListener(new View.OnLongClickListener() {
//                    @Override
//                    public boolean onLongClick(View v) {
//
//                        AlertDialog.Builder builder = new AlertDialog.Builder(PriceDatabaseActivity.this);
//                        builder.setItems(items, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                if (which == 0) {
//
//                                    rowID = view.getTag().toString();
//                                    onUpdateRecord(holder.CardType, holder.CardPrice.toString());
//                                } else {
//                                    AlertDialog.Builder deleteDialogOk = new AlertDialog.Builder(PriceDatabaseActivity.this);
//                                    deleteDialogOk.setTitle("Delete Contact?");
//                                    deleteDialogOk.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                                                @Override
//                                                public void onClick(DialogInterface dialog, int which) {
//                                                    rowID = view.getTag().toString();
//                                                    ContactModel contact = new ContactModel();
//                                                    contact.setID(view.getTag().toString());
//                                                    sQLiteHelper.deleteCardPriceRecord(rowID);
//                                                    displayAllRecords();
//                                                }
//                                            }
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
//                parentLayout.addView(view);
//            }
//        } else {
//            tvNoRecordsFound.setVisibility(View.VISIBLE);
//        }
//    }
//
//    private class Holder {
//        TextView tvFullName;
//        String CardType;
//        String CardPrice;


//    class GetCardPrice extends AsyncTask<Void, Void, Void> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
////             Showing progress dialog
//            pDialog = new ProgressDialog(PriceDatabaseActivity.this);
//            pDialog.setMessage("Please wait...");
//            pDialog.setCancelable(false);
//            pDialog.show();
//
//        }
//
//        @Override
//        protected Void doInBackground(Void... arg0) {
//
//            HttpHandler sh = new HttpHandler();
//            String jsonStr = sh.makeServiceCall(url);
//            JSONObject jsonObject=new JSONObject();
//            try {
//                jsonObject.accumulate("admin_id",Id);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            // Making a request to url and getting response
//
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
//                        String id = c.getString("card_type");
//                        String no = c.getString("card_price");
//
//                        // tmp hash map for single contact
//                        HashMap<String, String> Card = new HashMap<>();
//
//                        // adding each child node to HashMap key => value
//                        Card.put("card_id", id);
//                        Card.put("card_no", no);
//
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
//
//
//            /**
//             * Updating parsed JSON data into ListView
//             * */
//            ListAdapter adapter = new SimpleAdapter(
//                    PriceDatabaseActivity.this, CardDetail,
//                    R.layout.item_lists, new String[]{"card_id", "card_no"
//            }, new int[]{R.id.cardtype,
//                    R.id.cardprice});
//
//            listView.setAdapter(adapter);
//
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
                HttpPost httpPost = new HttpPost(BaseUrl+"/manage_price.php");
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

                    JSONArray userdetail=jsonObject.getJSONArray("message");
                    for (int i=0;i<userdetail.length();i++)
                    {
                        JSONObject c=userdetail.getJSONObject(i);
                        String id = c.getString("id");
                        String cid = c.getString("card_type");
                        String no = c.getString("card_price");


                        Price price=new Price();
                        price.setId(id);
                        price.setCardId(cid);
                        price.setCardType(no);
                        priceList.add(price);


//                        ListAdapter adapter = new SimpleAdapter(
//                    PriceDatabaseActivity.this, CardDetail,
//                    R.layout.item_lists, new String[]{"card_id", "card_no"
//            }, new int[]{R.id.cardtype,
//                    R.id.cardprice});

            listView.setAdapter(adapter);




                    }


//                        Toast.makeText(getActivity(), Total_Amount+Total_CarIn+Total_CarOut, Toast.LENGTH_SHORT).show();






                }

                else if (jsonObject.getString("error").equalsIgnoreCase("true")) {
                    String  message = jsonObject.getString("message");
                    if (message!= null) {
                        {
                            System.out.println("===========================error");
                            Toast.makeText(PriceDatabaseActivity.this,message, Toast.LENGTH_LONG).show();
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
    public void onBackPressed() {
        Intent intent=new Intent(PriceDatabaseActivity.this,AdminArea.class);
        startActivity(intent);
        super.onBackPressed();
    }
}

