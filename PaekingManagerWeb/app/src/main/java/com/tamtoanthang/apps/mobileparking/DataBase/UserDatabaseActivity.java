package com.tamtoanthang.apps.mobileparking.DataBase;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.tamtoanthang.apps.mobileparking.Adapter.CardAdapter;
import com.tamtoanthang.apps.mobileparking.Adapter.UserAdapter;
import com.tamtoanthang.apps.mobileparking.Admin.AdminArea;
import com.tamtoanthang.apps.mobileparking.Admin.UserTableManipulation;
import com.tamtoanthang.apps.mobileparking.Model.Card;
import com.tamtoanthang.apps.mobileparking.Model.Constants;
import com.tamtoanthang.apps.mobileparking.Model.Price;
import com.tamtoanthang.apps.mobileparking.Model.User;
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

public class UserDatabaseActivity extends ActionBarActivity {
    Button btnAddNewRecord;
    SQLiteHelper sQLiteHelper;

    android.widget.LinearLayout parentLayout;
    LinearLayout layoutDisplayPeople;
    SharedPreferences  SM1,Bu;
    TextView tvNoRecordsFound;
    String Id,BaseUrl;
    String name,pass,Uid;
    ProgressDialog pDialog;
    private static String url = "http://parking.lueinfoservices.com/manage_user.php";
    private String rowID = "";
    ListView UserlistView;
//    ArrayList<HashMap<String, String>> UserCardDetail;
public ArrayList<User> UserCardDetail=new ArrayList<User>();
    public ArrayAdapter<User> adapter;

    private ArrayList<HashMap<String, String>> tableData = new ArrayList<HashMap<String, String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        Bu = getSharedPreferences("Base", Context.MODE_PRIVATE);
        BaseUrl = Bu.getString("baseurl", "");

        SM1 =getSharedPreferences("Myid", Context.MODE_PRIVATE);
        Id=SM1.getString("id","");
        UserCardDetail(Id);
        UserlistView=(ListView)findViewById(R.id.user_card_detail);
//        UserCardDetail = new ArrayList<>();
        adapter = new UserAdapter(UserDatabaseActivity.this, R.layout.user_list, UserCardDetail);
        adapter.notifyDataSetChanged();
        UserlistView.invalidateViews();
        getAllWidgets();
        sQLiteHelper = new SQLiteHelper( UserDatabaseActivity.this);
        bindWidgetsWithEvent();
//        displayAllRecords();
    }
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            String UserName = data.getStringExtra(Constants.UserName);
//            String UserPassword = data.getStringExtra(Constants.UserPassword);
//
//
//            ContactModel contact = new ContactModel();
//            contact.setUserName(UserName);
//            contact.setUserPassword(UserPassword);
//            Log.d("user",""+contact.getIntime());
//
//            if (requestCode == Constants.ADD_RECORD) {
//                contact.setID(rowID);
//                sQLiteHelper.insertRecordUser1(contact);
//            } else if (requestCode == Constants.UPDATE_RECORD) {
//                contact.setID(rowID);
//                // sQLiteHelper.updateRecord(CardId, CardNo,CardType, rowID);
//                sQLiteHelper.updateRecordUser(contact);
//            }
//            displayAllRecords();
//        }
//    }
    private void getAllWidgets() {
        btnAddNewRecord = (Button) findViewById(R.id.btnAddNewRecord);

        parentLayout = (LinearLayout) findViewById(R.id.parentLayout);
        layoutDisplayPeople = (LinearLayout) findViewById(R.id.layoutDisplayPeople);

//        tvNoRecordsFound = (TextView) findViewById(R.id.tvNoRecordsFound);
    }
    private void bindWidgetsWithEvent() {
        btnAddNewRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddRecord();
            }
        });

        UserlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                TextView uid=(TextView)view.findViewById(R.id.id);
                TextView uname=(TextView)view.findViewById(R.id.user_name);
                TextView upass=(TextView)view.findViewById(R.id.user_password);
                name= uname.getText().toString();
                pass= upass.getText().toString();
                Uid=uid.getText().toString();
                System.out.println("==========name==========="+name);
                System.out.println("==========name==========="+pass);



//                Toast.makeText(UserDatabaseActivity.this,Uid,Toast.LENGTH_SHORT).show();


                ImageView edit=(ImageView)view.findViewById(R.id.edit);
                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UserlistView.invalidate();

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
//        updateDataAsync();

        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.fragment_userupdate, null);
        final EditText update_username = (EditText) promptsView.findViewById(R.id.update_username);
        update_username.setText(name);
        final EditText update_password=(EditText)promptsView.findViewById(R.id.update_password);
        update_password.setText(pass);
//        final EditText update_cardType=(EditText)promptsView.findViewById(R.id.update_cardtype);
//        update_cardType.setText(Uid);
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
                                String Update_username= update_username.getText().toString();
                                System.out.println("=======Update_carId==========="+Update_username);
                                String  Update_password=update_password.getText().toString();
                                System.out.println("=======Update_cardPrice==========="+Update_password);
//                                String Update_cardType=update_cardType.getText().toString();
//                                System.out.println("=======Update_cardType==========="+Update_cardType);


                                Update(Id,Uid,Update_username,Update_password);

                                Toast.makeText(UserDatabaseActivity.this,"User Updated",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(UserDatabaseActivity.this, UserDatabaseActivity.class);
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
    //-================================delete dialog====================================
    public void Delete_show()
    {
//        updateDataAsync();

        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.delete_user, null);
      final  TextView textView =(TextView)promptsView.findViewById(R.id.usertxt);

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
                .setMessage("  Do You Want To Delete?")
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

                                Toast.makeText(UserDatabaseActivity.this,"User Deleted",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(UserDatabaseActivity.this, UserDatabaseActivity.class);
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
                    HttpPost httpPost = new HttpPost(BaseUrl+"/user_update_id.php");
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
                           String ucid=c.getString("id");
                            String username =c.getString("user_name");
                            String  password=c.getString("user_password");



                        }

//                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();

                    }

                    else if (jsonObject.getString("error").equalsIgnoreCase("true")) {
                        String  message = jsonObject.getString("message");
                        if (message != null) {
                            {
                                System.out.println("===========================error");
                                Toast.makeText(UserDatabaseActivity.this,message, Toast.LENGTH_LONG).show();
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
    //=========================update user details============================================
    public void  Update(final String Id,
                        final String UId,
                        final String Update_username,
                        final String Update_password





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
                    jsonObject.accumulate("user_name",Update_username);
                    jsonObject.accumulate("user_password",Update_password);






                } catch (JSONException e) {
                    e.printStackTrace();
                }


                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    SharedPreferences Bu = getSharedPreferences("Base", Context.MODE_PRIVATE);
                    String BaseUrl = Bu.getString("baseurl", "");
                    HttpPost httpPost = new HttpPost(BaseUrl+"/user_update.php");
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
                        JSONArray carddetail=jsonObject.getJSONArray("message");
                        Toast.makeText(UserDatabaseActivity.this,message,Toast.LENGTH_SHORT).show();
                        for(int i=0;i<carddetail.length();i++)
                        {
                            JSONObject c=carddetail.getJSONObject(i);
                           String username=c.getString("user_name");
                           String password=c.getString("user_password");


//
                        }

//                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();

                    }

                    else if (jsonObject.getString("error").equalsIgnoreCase("true")) {
                        String  message = jsonObject.getString("message");
                        if (message != null) {
                            {
                                System.out.println("===========================error");
                                Toast.makeText(UserDatabaseActivity.this,message, Toast.LENGTH_LONG).show();
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

    //============================delete=================================
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
                    HttpPost httpPost = new HttpPost(BaseUrl+"/user_delete.php");
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
                        Toast.makeText(UserDatabaseActivity.this,message,Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(UserDatabaseActivity.this,message, Toast.LENGTH_LONG).show();
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
    private void onAddRecord() {
        Intent intent = new Intent(UserDatabaseActivity.this, UserTableManipulation.class);
        intent.putExtra(Constants.DML_TYPE, Constants.INSERT);
        startActivityForResult(intent, Constants.ADD_RECORD);
    }
//    class GetUserCardDetail extends AsyncTask<Void, Void, Void> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
////             Showing progress dialog
//            pDialog = new ProgressDialog(UserDatabaseActivity.this);
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
//                        String id = c.getString("user_name");
//                        String no = c.getString("user_password");
//
//                        // tmp hash map for single contact
//                        HashMap<String, String> Card = new HashMap<>();
//
//                        // adding each child node to HashMap key => value
//                        Card.put("user_name", id);
//                        Card.put("user_password", no);
//
//
//
//
//                        // adding contact to contact list
//                        UserCardDetail.add(Card);
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
//                    UserDatabaseActivity.this, UserCardDetail,
//                    R.layout.user_list, new String[]{"user_name", "user_password",
//                    }, new int[]{R.id.user_name,
//                    R.id.user_password,});
//
//            UserlistView.setAdapter(adapter);
//        }}


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
                    HttpPost httpPost = new HttpPost(BaseUrl+"/manage_user.php");
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
                            System.out.println("====id===="+id);
                            String username=c.getString("user_name");
                            System.out.println("====id===="+username);
                            String no = c.getString("user_password");
                            System.out.println("====id===="+no);


                            User user=new User();

                            user.setId(id);
                            user.setUserName(username);
                            user.setPassword(no);
                            UserCardDetail.add(user);
                            UserlistView.setAdapter(adapter);

//                            ListAdapter adapter = new SimpleAdapter(
//                    UserDatabaseActivity.this, UserCardDetail,
//                    R.layout.user_list, new String[]{"user_name", "user_password",
//                    }, new int[]{R.id.user_name,
//                    R.id.user_password,});

//            UserlistView.setAdapter(adapter);




                        }


//                        Toast.makeText(getActivity(), Total_Amount+Total_CarIn+Total_CarOut, Toast.LENGTH_SHORT).show();






                    }

                    else if (jsonObject.getString("error").equalsIgnoreCase("true")) {
                        String  message = jsonObject.getString("message");
                        if (message != null) {
                            {
                                System.out.println("===========================error");
                                Toast.makeText(UserDatabaseActivity.this,message, Toast.LENGTH_LONG).show();
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
        Intent intent=new Intent(UserDatabaseActivity.this,AdminArea.class);
        startActivity(intent);
        super.onBackPressed();
    }
//
//    private void onUpdateRecord( String UserName ,String UserPassword) {
//        Intent intent = new Intent(UserDatabaseActivity.this, UserTableManipulation.class);
//        intent.putExtra(Constants.UserName, UserName);
//        intent.putExtra(Constants.UserPassword,UserPassword);
//        intent.putExtra(Constants.DML_TYPE, Constants.UPDATE);
//        startActivityForResult(intent, Constants.UPDATE_RECORD);
//    }
//
//    private void displayAllRecords() {
//
//        com.rey.material.widget.LinearLayout inflateParentView;
//        parentLayout.removeAllViews();
//
//        ArrayList<ContactModel> contacts = sQLiteHelper.getAllRecordsUser1();
//
//        if (contacts.size() > 0) {
//            tvNoRecordsFound.setVisibility(View.GONE);
//             ContactModel contactModel;
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
//                holder.UserName = contactModel.getUserName();
//                holder.UserPassword= contactModel.getUserPassword();
//                 String personName = holder.UserName + " " + holder.UserPassword ;
//                holder.tvFullName.setText(personName);
//
//                final CharSequence[] items = {Constants.UPDATE, Constants.DELETE};
//                inflateParentView.setOnLongClickListener(new View.OnLongClickListener() {
//                    @Override
//                    public boolean onLongClick(View v) {
//
//                        AlertDialog.Builder builder = new AlertDialog.Builder(UserDatabaseActivity.this);
//                        builder.setItems(items, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                if (which == 0) {
//
//                                    rowID = view.getTag().toString();
//                                    onUpdateRecord(holder.UserName,holder.UserPassword.toString());
//                                } else {
//                                    AlertDialog.Builder deleteDialogOk = new AlertDialog.Builder(UserDatabaseActivity.this);
//                                    deleteDialogOk.setTitle("Delete Contact?");
//                                    deleteDialogOk.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                                                @Override
//                                                public void onClick(DialogInterface dialog, int which) {
//                                                    rowID = view.getTag().toString();
//                                                    ContactModel contact = new ContactModel();
//                                                    contact.setID(rowID);
//
//
//                                                    sQLiteHelper.deleteUserRecord(rowID);
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


//    private class Holder {
//        TextView tvFullName;
//        String UserName;
//        String UserPassword;
//
//    }
}
