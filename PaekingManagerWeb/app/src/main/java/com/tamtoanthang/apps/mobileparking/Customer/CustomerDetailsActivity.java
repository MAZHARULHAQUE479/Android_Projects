package com.tamtoanthang.apps.mobileparking.Customer;

import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.tamtoanthang.apps.mobileparking.Admin.AdminArea;
import com.tamtoanthang.apps.mobileparking.Model.Contact;
import com.tamtoanthang.apps.mobileparking.Model.CustomerDetail;
import com.tamtoanthang.apps.mobileparking.DataBase.SQLiteHelper;
import com.tamtoanthang.apps.mobileparking.Adapter.TransactionListviewAdapter;
import com.tamtoanthang.apps.mobileparking.DialogFragment.D2Fragment;
import com.tamtoanthang.apps.mobileparking.DialogFragment.DeleteFragment;
import com.tamtoanthang.apps.mobileparking.DataBase.DatabaseHelper;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;



public class CustomerDetailsActivity extends ActionBarActivity {
    Button Search;
    EditText inputsearch, inputsearch1, inputsearch2, inputsearch3, inputsearch4;
    private static final int SHOW_PROCESS_BAR = 1;
    private static final int HIDE_PROCESS_BAR = 0;
    String input = "";
    String inputdate = "";
    String inputTime = "";
    String inputdateout = "";
    String inputTimeout = "";
    DatabaseHelper databaseHelper;
    SQLiteHelper sQLiteHelper;
    android.widget.LinearLayout parentLayout;
    LinearLayout layoutDisplayPeople;
    TextView tvNoRecordsFound;
    private String rowID = null;
    SQLiteDatabase database;
    DatabaseHelper dbHelper;
    D2Fragment d2Fragment;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    Calendar dateCalendar;
    Calendar mcurrentTime;
    String CardId,CardNo,CardType,CardPrice,InImage,OutImage,InTime,OutTime,Status,UserName,Id,BaseUrl;
    String  jresult;
    ListView listView;
//    PhotoView ptv,pvt1;
    Button button,button1;
   // Processbar processbar;
    ProgressDialog progressDialog;
    SharedPreferences SM1,Bu;

    String fk = "";
    Contact contact;
    int fkdd=0;
    public ArrayAdapter<CustomerDetail> adapter;
    public ArrayList<CustomerDetail> CustomerdetailList=new ArrayList<CustomerDetail>();
    DeleteFragment deleteFragment;
    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            "dd-MM-yyyy", Locale.ENGLISH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deleteFragment=new DeleteFragment();
        setContentView(R.layout.activity_customer_details);

//            initView();
        Bu = getSharedPreferences("Base", Context.MODE_PRIVATE);
        BaseUrl = Bu.getString("baseurl", "");
//        dbHelper = new DatabaseHelper(this);
        listView = (ListView)findViewById(R.id.customerlist);
        adapter = new TransactionListviewAdapter(CustomerDetailsActivity.this, R.layout.list_item2, CustomerdetailList);
        adapter.notifyDataSetChanged();
        listView.invalidateViews();
        parentLayout = (LinearLayout) findViewById(R.id.parentLayout);
        layoutDisplayPeople = (LinearLayout) findViewById(R.id.layoutDisplayPeople);

//        tvNoRecordsFound = (TextView) findViewById(R.id.tvNoRecordsFound);
//        databaseHelper = new DatabaseHelper(CustomerDetailsActivity.this);
        inputsearch = (EditText) findViewById(R.id.inputsearch);



//        sQLiteHelper = new SQLiteHelper(CustomerDetailsActivity.this);
//        displayAllRecords();
        inputsearch1 = (EditText) findViewById(R.id.inputTotal1);
        inputsearch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar newCalendar = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(CustomerDetailsActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                dateCalendar = Calendar.getInstance();
                                dateCalendar.set(year, monthOfYear, dayOfMonth);
                                inputsearch1.setText(formatter.format(dateCalendar
                                        .getTime()));
                            }

                        }, newCalendar.get(Calendar.YEAR),
                        newCalendar.get(Calendar.MONTH),
                        newCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();

            }

        });


        inputsearch2 = (EditText) findViewById(R.id.inputTotal2);
        inputsearch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(CustomerDetailsActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                inputsearch2.setText(hourOfDay + ":" + minute);
                            }
                        }, hour, minute, false);
                timePickerDialog.show();


            }
        });
        inputsearch3 = (EditText) findViewById(R.id.inputTotal3);
        inputsearch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar newCalendar = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(CustomerDetailsActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                dateCalendar = Calendar.getInstance();
                                dateCalendar.set(year, monthOfYear, dayOfMonth);
                                inputsearch3.setText(formatter.format(dateCalendar
                                        .getTime()));
                            }

                        }, newCalendar.get(Calendar.YEAR),
                        newCalendar.get(Calendar.MONTH),
                        newCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        inputsearch4 = (EditText) findViewById(R.id.inputTotal4);
        inputsearch4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(CustomerDetailsActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                inputsearch4.setText(hourOfDay + ":" + minute);
                            }
                        }, hour, minute, false);
                timePickerDialog.show();

            }
        });
        Search = (Button) findViewById(R.id.Search);
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SM1 =getSharedPreferences("Myid", Context.MODE_PRIVATE);
                Id=SM1.getString("id","");
                input = inputsearch.getText().toString();
                inputdate = inputsearch1.getText().toString();
                inputTime = inputsearch2.getText().toString();
                inputdateout = inputsearch3.getText().toString();
                inputTimeout = inputsearch4.getText().toString();

                Register(Id,input,inputdate,inputTime,inputdateout,inputTimeout);
//                displayAllRecords();
                adapter.clear();


            }
        });
        button=(Button)findViewById(R.id.button6);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                fm.addOnBackStackChangedListener(null);
                deleteFragment.show(fm, "Sample Fragment");

            }
        });

        button1=(Button)findViewById(R.id.button7);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SM1 =getSharedPreferences("Myid", Context.MODE_PRIVATE);
                Id=SM1.getString("id","");
                input = inputsearch.getText().toString();
                inputdate = inputsearch1.getText().toString();
                inputTime = inputsearch2.getText().toString();
                inputdateout = inputsearch3.getText().toString();
                inputTimeout = inputsearch4.getText().toString();
                Register(Id,input,inputdate,inputTime,inputdateout,inputTimeout);

            }
        });



    }
//    private void initView()
//    {
//        ptv=(PhotoView)findViewById(R.id.inimage);
//        pvt1=(PhotoView)findViewById(R.id.outimage);
//
//    }
    public void  Register(final String Id,
                          final String input,
                          final String inputdate,
                          final String inputTime,
                          final String inputdateout,
                          final String inputTimeout
    ){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {

            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = new ProgressDialog(CustomerDetailsActivity.this);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();
            }
            @Override
            protected String doInBackground(String... params) {
                String return_text="";
                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.accumulate("admin_id",Id);
                    jsonObject.accumulate("card_no",input);
                    jsonObject.accumulate("date1",inputdate);
                    jsonObject.accumulate("time1",inputTime);
                    jsonObject.accumulate("date2",inputdateout);
                    jsonObject.accumulate("time2",inputTimeout);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(BaseUrl+"/transaction.php");
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
                         jresult=jsonObject.getString("result");

                        System.out.println("========jresult========"+jresult);
                        JSONArray details=jsonObject.getJSONArray("result");

                        for (int i=0;i<details.length();i++)
                        {


                            JSONObject c = details.getJSONObject(i);
                            CardId=c.getString("card_id");
                            System.out.println("CardId"+CardId);
                            CardNo=c.getString("card_no");
                            CardType=c.getString("card_type");
                            CardPrice=c.getString("card_price");
                            InImage=c.getString("in_image");
                            System.out.println("======InImage====="+InImage);
                            OutImage=c.getString("out_image");
                            InTime=c.getString("in_time");
                            OutTime=c.getString("out_time");
                            Status=c.getString("status");
                            UserName=c.getString("user_name");

                            CustomerDetail customerDetail=new CustomerDetail();
                            customerDetail.setCardId(CardId);
                            customerDetail.setCardNo(CardNo);
                            customerDetail.setCardType(CardType);
                            customerDetail.setCardPrice(CardPrice);
                            customerDetail.setInImage(InImage);
                            customerDetail.setOutImage(OutImage);
                            customerDetail.setInTime(InTime);
                            customerDetail.setOutTime(OutTime);
                            customerDetail.setStatus(Status);
                            customerDetail.setUserName(UserName);
                            CustomerdetailList.add(customerDetail);

                            listView.setAdapter(adapter);
                        }
                        progressDialog.hide();
//                       Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
                        Toast.makeText(CustomerDetailsActivity.this, message, Toast.LENGTH_LONG).show();

                        button1.setVisibility(View.VISIBLE);
                        button.setVisibility(View.INVISIBLE);

                    }


                    else if (jsonObject.getString("error").equalsIgnoreCase("true")) {
                        String  message = jsonObject.getString("message");
                        if (message != null) {
                            {

                                Toast.makeText(CustomerDetailsActivity.this,message, Toast.LENGTH_LONG).show();
                              /*  button1.setVisibility(View.VISIBLE);
                                button.setVisibility(View.INVISIBLE);*/
                            }
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
              /*  if (jresult!=null)
                {
                    button1.setVisibility(View.VISIBLE);
                    button.setVisibility(View.INVISIBLE);
                }*/
            }
        }

        try {
            SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
            sendPostReqAsyncTask.execute();
        }catch (Exception e){}
    }
    private Date getDate(String datetimestamp){
        try {
            java.text.DateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = format.parse(datetimestamp);

            return date;
        }catch (Exception e){e.printStackTrace();
            return null;}
    }

//
//    /*public void onCheckboxClicked(View view) {
//        // Is the view now checked?
//        boolean checked = ((CheckBox) view).isChecked();
//
//        // Check which checkbox was clicked
//        switch(view.getId()) {
//            case R.id.checkBoxDelete:
//                if (checked){
//
//                }
//                // Put some meat on the sandwich
//                else{}
//                // Remove the meat
//                break;
//
//            // TODO: Veggie sandwich
//        }
//    }
//*/
////    private void getAllWidgets() {
////
////
////        parentLayout = (LinearLayout) findViewById(R.id.parentLayout);
////        layoutDisplayPeople = (LinearLayout) findViewById(R.id.layoutDisplayPeople);
////
//////        tvNoRecordsFound = (TextView) findViewById(R.id.tvNoRecordsFound);
////    }
//
////    private void displayAllRecords() {
////        com.rey.material.widget.LinearLayout inflateParentView;
////        parentLayout.removeAllViews();
////        ArrayList<Contact> contacts = dbHelper.getContact();
////
////        if (contacts.size() > 0) {
////            tvNoRecordsFound.setVisibility(View.GONE);
////
////            for (int v = 0; v < contacts.size(); v++) {
////                fkdd=contacts.get(v).getID();
////                Log.d("id","d"+fkdd);
////                String z=contacts.get(v).getCardNo();
////                String x = contacts.get(v).getInTime();
////                if (inputdate.equals("") && inputTime.equals("")) {
////                    if (input.equals(contacts.get(v).getCardNo())) {
////                        Log.d("yfgyuhfyuh"," "+"1");
////                        contact = contacts.get(v);
////                        Button button=(Button)findViewById(R.id.button6);
////
////                        button.setOnClickListener(new View.OnClickListener() {
////                            @Override
////                            public void onClick(View view) {
////                                DeleteFragment deleteFragment=new DeleteFragment();
////                                FragmentManager fm = getFragmentManager();
////
////                                if(!input.equals("")){
////                                    ArrayList<Contact> contacts = dbHelper.getContact();
////                                    for (int i = 0; i < contacts.size(); i++) {
////                                        String x = contacts.get(i).getCardIdNo();
////                                        String y=contacts.get(i).getCardNo();
////                                        Log.d("xhxhu", "  " + input + "  " + x);
////                                        if ((x.equals(input)||y.equals(input))) {
////                                            Log.d("xhxhu", "  " + input + "  " + x);
////                                            contacts.get(i).getID();
////                                            deleteFromExternalStorage(contacts.get(i).getIn_ImagePath());
////                                            deleteFromExternalStorage(contacts.get(i).getOut_ImagePath());
////                                            dbHelper.deleteRow(contacts.get(i).getID());
////                                        }
////                                    }
////
////                                }else {
////                                    deleteFragment.show(fm, "Sample Fragment");
////                                }
////
////
////
////                                        displayAllRecords();
////
////                            }
////                        });
////
////                        final Holder holder = new Holder();
////                        final View view = LayoutInflater.from(CustomerDetailsActivity.this).inflate(R.layout.list_item2, null);
////                        view.setTag(contacts.get(v).getID());
////                        final CharSequence[] items = {Constants.UPDATE, Constants.DELETE};
////                        rowID = view.getTag().toString();
////                        holder.delete=(Button)view.findViewById(R.id.delete2);
////                        holder.delete.setOnClickListener(new View.OnClickListener() {
////                            @Override
////                            public void onClick(View v) {
////                                AlertDialog.Builder deleteDialogOk = new AlertDialog.Builder(CustomerDetailsActivity.this);
////                                deleteDialogOk.setTitle("Delete Contact?");
////                                deleteDialogOk.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
////                                            @Override
////                                            public void onClick(DialogInterface dialog, int which) {
////
////                                                boolean b=  dbHelper.deleteRow(Integer.parseInt(view.getTag().toString()));
////                                                displayAllRecords();
////                                            }
////                                        }
////                                );
////                                deleteDialogOk.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
////                                    @Override
////                                    public void onClick(DialogInterface dialog, int which) {
////
////                                    }
////                                });
////                                deleteDialogOk.show();
////                            }
////
////
////                        });
////                        parentLayout.addView(view);
////                        holder.ImageCamera = (ImageView) view.findViewById(R.id.imageView1);
////                        holder.ImageCamera.setOnClickListener(new View.OnClickListener() {
////                            @Override
////                            public void onClick(View view) {
////                                Intent intent = new Intent(CustomerDetailsActivity.this, ZoomActivity.class);
////                                ByteArrayInputStream imageStream = new ByteArrayInputStream(holder.InImage);
////                                Bitmap theImage = BitmapFactory.decodeStream(imageStream);
////                                Matrix matrix = new Matrix();
////                                matrix.postRotate(90);
////                                Bitmap rotated = Bitmap.createBitmap(theImage, 0, 0, theImage.getWidth(), theImage.getHeight(),
////                                        matrix, true);
////                                intent.putExtra("inimage", rotated);
////                                startActivity(intent);
////
////                            }
////                        });
////                        holder.ImageGallery = (ImageView) view.findViewById(R.id.imageView2);
////                        holder.ImageGallery.setOnClickListener(new View.OnClickListener() {
////                            @Override
////                            public void onClick(View view) {
////                                Intent intent1 = new Intent(CustomerDetailsActivity.this, ZoomActivity.class);
////                                ByteArrayInputStream imageStream = new ByteArrayInputStream(holder.OutImage);
////                                Bitmap theImage1 = BitmapFactory.decodeStream(imageStream);
////                                Matrix matrix = new Matrix();
////                                matrix.postRotate(90);
////
////                                Bitmap rotated1 = Bitmap.createBitmap(theImage1, 0, 0, theImage1.getWidth(), theImage1.getHeight(),
////                                        matrix, true);
////                                intent1.putExtra("inimage", rotated1);
////                                startActivity(intent1);
////
////
////                            }
////                        });
////                        holder.tvFullName = (TextView) view.findViewById(R.id.tvFullName);
////                        holder.Price = (TextView) view.findViewById(R.id.tvFullName1);
////                        holder.Intime = (TextView) view.findViewById(R.id.tvFullINTime);
////
////                        holder.Outtime = (TextView) view.findViewById(R.id.tvFullOutTime);
////                        holder.status = (TextView) view.findViewById(R.id.tvFullName3);
////
////                        view.setTag(contact.getID());
////                        holder.CardIdNo = contact.getCardIdNo();
////                        holder.CardPrice = contact.getPrice();
////                        holder.InImage = contact.getInImage();
////                        holder.OutImage = contact.getOutImage();
////                        holder.OutTime = contact.getOutTime();
////                        holder.InTime = contact.getInTime();
////                        holder.status1 = contact.getStatus();
////
////                        String cardIdNo = holder.CardIdNo;
////                        String Price = holder.CardPrice;
////                        String time = holder.InTime;
////                        String time1 = holder.OutTime;
////                        String timeu1 = holder.status1;
////
////                        holder.tvFullName.setText(cardIdNo);
////                        holder.Price.setText(Price);
////                        holder.status.setText(timeu1);
////                        ByteArrayInputStream imageStream = new ByteArrayInputStream(holder.InImage);
////                        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
////                        Matrix matrix = new Matrix();
////                        matrix.postRotate(90);
////
////                        Bitmap rotated = Bitmap.createBitmap(theImage, 0, 0, theImage.getWidth(), theImage.getHeight(),
////                                matrix, true);
////                        holder.ImageCamera.setImageBitmap(rotated);
////                        ByteArrayInputStream imageStream1 = new ByteArrayInputStream(holder.OutImage);
////                        Bitmap theImage1 = BitmapFactory.decodeStream(imageStream1);
////                        Bitmap rotated1 = Bitmap.createBitmap(theImage1, 0, 0, theImage1.getWidth(), theImage1.getHeight(),
////                                matrix, true);
////                        holder.ImageGallery.setImageBitmap(rotated1);
////
////                        holder.Intime.setText(time);
////                        holder.Outtime.setText(time1);
////
////                    }
////                } else if ((input.equals(contacts.get(v).getCardNo()) && getDate(x).after(getDate(inputdate + " " + inputTime + ":00")) && getDate(x).before(getDate(inputdateout + " " + inputTimeout + ":00")))) {
////                        contact = contacts.get(v);
////
////                    Button button=(Button)findViewById(R.id.button6);
////
////                    button.setOnClickListener(new View.OnClickListener() {
////                        @Override
////                        public void onClick(View view) {
////                            DeleteFragment deleteFragment=new DeleteFragment();
////                            FragmentManager fm = getFragmentManager();
////                            if(!input.equals("")) {
////                                Log.d("xhxhu", "*******"+inputsearch3.getText().toString());
////                                ArrayList<Contact> contacts = dbHelper.getContact();
////                                for (int i = 0; i < contacts.size(); i++) {
////                                    String x = contacts.get(i).getInTime();
////                                    String status = contacts.get(i).getStatus();
////                                    Log.d("xhxhu", "  " + status + "  " + String.valueOf(0));
////                                    if ((getDate(x).before(getDate(inputsearch3.getText().toString() + " " + inputsearch4.getText().toString() + ":00"))) && (status.equals("0"))) {
////                                        Log.d("xhxhu", "  " + inputsearch3.getText().toString() + "  " + x);
////                                        contacts.get(i).getID();
////                                        deleteFromExternalStorage(contacts.get(i).getOut_ImagePath());
////                                        deleteFromExternalStorage(contacts.get(i).getIn_ImagePath());
////                                        dbHelper.deleteRow(contacts.get(i).getID());
////
////                                    }
////                                }
////                            }else {
////                                deleteFragment.show(fm, "Sample Fragment");
////                            }
////
////
////
////                            Log.d("yfgyuhfyuh"," "+"2");
////                            displayAllRecords();
////
////                        }
////                    });
////                        final Holder holder = new Holder();
////                        final View view = LayoutInflater.from(CustomerDetailsActivity.this).inflate(R.layout.list_item2, null);
////                        view.setTag(contacts.get(v).getID());
////                        final CharSequence[] items = {Constants.UPDATE, Constants.DELETE};
////                        rowID = view.getTag().toString();
////                        holder.delete=(Button)view.findViewById(R.id.delete2);
////                        holder.delete.setOnClickListener(new View.OnClickListener() {
////                            @Override
////                            public void onClick(View v) {
////                                AlertDialog.Builder deleteDialogOk = new AlertDialog.Builder(CustomerDetailsActivity.this);
////                                deleteDialogOk.setTitle("Delete Contact?");
////                                deleteDialogOk.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
////                                            @Override
////                                            public void onClick(DialogInterface dialog, int which) {
////
////                                                boolean b=  dbHelper.deleteRow(Integer.parseInt(view.getTag().toString()));
////                                                displayAllRecords();
////                                            }
////                                        }
////                                );
////                                deleteDialogOk.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
////                                    @Override
////                                    public void onClick(DialogInterface dialog, int which) {
////
////                                    }
////                                });
////                                deleteDialogOk.show();
////                            }
////
////
////                        });
////                        parentLayout.addView(view);
////                        holder.ImageCamera = (ImageView) view.findViewById(R.id.imageView1);
////                        holder.ImageCamera.setOnClickListener(new View.OnClickListener() {
////                            @Override
////                            public void onClick(View view) {
////                                Intent intent = new Intent(CustomerDetailsActivity.this, ZoomActivity.class);
////                                ByteArrayInputStream imageStream = new ByteArrayInputStream(holder.InImage);
////                                Bitmap theImage = BitmapFactory.decodeStream(imageStream);
////                                Matrix matrix = new Matrix();
////                                matrix.postRotate(90);
////                                Bitmap rotated = Bitmap.createBitmap(theImage, 0, 0, theImage.getWidth(), theImage.getHeight(),
////                                        matrix, true);
////                                intent.putExtra("inimage", rotated);
////                                startActivity(intent);
////
////                            }
////                        });
////                        holder.ImageGallery = (ImageView) view.findViewById(R.id.imageView2);
////                        holder.ImageGallery.setOnClickListener(new View.OnClickListener() {
////                            @Override
////                            public void onClick(View view) {
////                                Intent intent1 = new Intent(CustomerDetailsActivity.this, ZoomActivity.class);
////                                ByteArrayInputStream imageStream = new ByteArrayInputStream(holder.OutImage);
////                                Bitmap theImage1 = BitmapFactory.decodeStream(imageStream);
////                                Matrix matrix = new Matrix();
////                                matrix.postRotate(90);
////
////                                Bitmap rotated1 = Bitmap.createBitmap(theImage1, 0, 0, theImage1.getWidth(), theImage1.getHeight(),
////                                        matrix, true);
////                                intent1.putExtra("inimage", rotated1);
////                                startActivity(intent1);
////
////
////                            }
////                        });
////                        holder.tvFullName = (TextView) view.findViewById(R.id.tvFullName);
////                        holder.Price = (TextView) view.findViewById(R.id.tvFullName1);
////                        holder.Intime = (TextView) view.findViewById(R.id.tvFullINTime);
////
////                        holder.Outtime = (TextView) view.findViewById(R.id.tvFullOutTime);
////                        holder.status = (TextView) view.findViewById(R.id.tvFullName3);
////
////                        view.setTag(contact.getID());
////                        holder.CardIdNo = contact.getCardIdNo();
////                        holder.CardPrice = contact.getPrice();
////                        holder.InImage = contact.getInImage();
////                        holder.OutImage = contact.getOutImage();
////                        holder.OutTime = contact.getOutTime();
////                        holder.InTime = contact.getInTime();
////                        holder.status1 = contact.getStatus();
////
////                        String cardIdNo = holder.CardIdNo;
////                        String Price = holder.CardPrice;
////                        String time = holder.InTime;
////                        String time1 = holder.OutTime;
////                        String timeu1 = holder.status1;
////
////                        holder.tvFullName.setText(cardIdNo);
////                        holder.Price.setText(Price);
////                        holder.status.setText(timeu1);
////                        ByteArrayInputStream imageStream = new ByteArrayInputStream(holder.InImage);
////                        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
////                        Matrix matrix = new Matrix();
////                        matrix.postRotate(90);
////
////                        Bitmap rotated = Bitmap.createBitmap(theImage, 0, 0, theImage.getWidth(), theImage.getHeight(),
////                                matrix, true);
////                        holder.ImageCamera.setImageBitmap(rotated);
////                        ByteArrayInputStream imageStream1 = new ByteArrayInputStream(holder.OutImage);
////                        Bitmap theImage1 = BitmapFactory.decodeStream(imageStream1);
////                        Bitmap rotated1 = Bitmap.createBitmap(theImage1, 0, 0, theImage1.getWidth(), theImage1.getHeight(),
////                                matrix, true);
////                        holder.ImageGallery.setImageBitmap(rotated1);
////
////                        holder.Intime.setText(time);
////                        holder.Outtime.setText(time1);
////
////
////                }
////                    else if (input.equals("")) {
////                    Log.d("yfgyuhfyuh"," "+"3");
////                    if (getDate(x).after(getDate(inputdate + " " + inputTime + ":00")) && getDate(x).before(getDate(inputdateout + " " + inputTimeout + ":00"))) {
////                        contact = contacts.get(v);
////                        Button button=(Button)findViewById(R.id.button6);
////
////                        button.setOnClickListener(new View.OnClickListener() {
////                            @Override
////                            public void onClick(View view) {
////                                DeleteFragment deleteFragment=new DeleteFragment();
////                                FragmentManager fm = getFragmentManager();
////                                if(!inputdate.equals("")) {
////                                    ArrayList<Contact> contacts = dbHelper.getContact();
////                                    for (int i = 0; i < contacts.size(); i++) {
////                                        String x = contacts.get(i).getInTime();
////                                        String status = contacts.get(i).getStatus();
////                                        Log.d("xhxhu", "  " + status + "  " +x+"%"+inputsearch3.getText().toString()+"%"+inputsearch4.getText().toString());
////                                        if ((getDate(x).before(getDate(inputsearch3.getText().toString() + " " + inputsearch4.getText().toString() + ":00"))) && (status.equals("0"))) {
////                                            Log.d("xhxhu", "  " + inputsearch3.getText().toString() + "  " + x);
////                                            contacts.get(i).getID();
////                                            deleteFromExternalStorage(contacts.get(i).getIn_ImagePath());
////                                            deleteFromExternalStorage(contacts.get(i).getOut_ImagePath());
////                                            dbHelper.deleteRow(contacts.get(i).getID());
////
////                                        }
////                                    }
////                                }else {
////                                    deleteFragment.show(fm, "Sample Fragment");
////                                }
////
////
////                                displayAllRecords();
////
////                            }
////                        });
////                        final Holder holder = new Holder();
////                        final View view = LayoutInflater.from(CustomerDetailsActivity.this).inflate(R.layout.list_item2, null);
////                        view.setTag(contacts.get(v).getID());
////                        final CharSequence[] items = {Constants.UPDATE, Constants.DELETE};
////                        rowID = view.getTag().toString();
////                        holder.delete=(Button)view.findViewById(R.id.delete2);
////
////                        holder.delete.setOnClickListener(new View.OnClickListener() {
////                            @Override
////                            public void onClick(View v) {
////                                AlertDialog.Builder deleteDialogOk = new AlertDialog.Builder(CustomerDetailsActivity.this);
////                                deleteDialogOk.setTitle("Delete Contact?");
////                                deleteDialogOk.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
////                                            @Override
////                                            public void onClick(DialogInterface dialog, int which) {
////
////                                                boolean b=  dbHelper.deleteRow(Integer.parseInt(view.getTag().toString()));
////                                                displayAllRecords();
////                                            }
////                                        }
////                                );
////                                deleteDialogOk.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
////                                    @Override
////                                    public void onClick(DialogInterface dialog, int which) {
////
////                                    }
////                                });
////                                deleteDialogOk.show();
////                            }
////
////
////                        });
////                        parentLayout.addView(view);
////                        holder.ImageCamera = (ImageView) view.findViewById(R.id.imageView1);
////                        holder.ImageCamera.setOnClickListener(new View.OnClickListener() {
////                            @Override
////                            public void onClick(View view) {
////                                Intent intent = new Intent(CustomerDetailsActivity.this, ZoomActivity.class);
////                                ByteArrayInputStream imageStream = new ByteArrayInputStream(holder.InImage);
////                                Bitmap theImage = BitmapFactory.decodeStream(imageStream);
////                                Matrix matrix = new Matrix();
////                                matrix.postRotate(90);
////                                Bitmap rotated = Bitmap.createBitmap(theImage, 0, 0, theImage.getWidth(), theImage.getHeight(),
////                                        matrix, true);
////                                intent.putExtra("inimage", rotated);
////                                startActivity(intent);
////
////                            }
////                        });
////                        holder.ImageGallery = (ImageView) view.findViewById(R.id.imageView2);
////                        holder.ImageGallery.setOnClickListener(new View.OnClickListener() {
////                            @Override
////                            public void onClick(View view) {
////                                Intent intent1 = new Intent(CustomerDetailsActivity.this, ZoomActivity.class);
////                                ByteArrayInputStream imageStream = new ByteArrayInputStream(holder.OutImage);
////                                Bitmap theImage1 = BitmapFactory.decodeStream(imageStream);
////                                Matrix matrix = new Matrix();
////                                matrix.postRotate(90);
////
////                                Bitmap rotated1 = Bitmap.createBitmap(theImage1, 0, 0, theImage1.getWidth(), theImage1.getHeight(),
////                                        matrix, true);
////                                intent1.putExtra("inimage", rotated1);
////                                startActivity(intent1);
////
////
////                            }
////                        });
////                        holder.tvFullName = (TextView) view.findViewById(R.id.tvFullName);
////                        holder.Price = (TextView) view.findViewById(R.id.tvFullName1);
////                        holder.Intime = (TextView) view.findViewById(R.id.tvFullINTime);
////
////                        holder.Outtime = (TextView) view.findViewById(R.id.tvFullOutTime);
////                        holder.status = (TextView) view.findViewById(R.id.tvFullName3);
////
////                        view.setTag(contact.getID());
////                        holder.CardIdNo = contact.getCardIdNo();
////                        holder.CardPrice = contact.getPrice();
////                        holder.InImage = contact.getInImage();
////                        holder.OutImage = contact.getOutImage();
////                        holder.OutTime = contact.getOutTime();
////                        holder.InTime = contact.getInTime();
////                        holder.status1 = contact.getStatus();
////
////                        String cardIdNo = holder.CardIdNo;
////                        String Price = holder.CardPrice;
////                        String time = holder.InTime;
////                        String time1 = holder.OutTime;
////                        String timeu1 = holder.status1;
////
////                        holder.tvFullName.setText(cardIdNo);
////                        holder.Price.setText(Price);
////                        holder.status.setText(timeu1);
////                        ByteArrayInputStream imageStream = new ByteArrayInputStream(holder.InImage);
////                        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
////                        Matrix matrix = new Matrix();
////                        matrix.postRotate(90);
////
////                        Bitmap rotated = Bitmap.createBitmap(theImage, 0, 0, theImage.getWidth(), theImage.getHeight(),
////                                matrix, true);
////                        holder.ImageCamera.setImageBitmap(rotated);
////                        ByteArrayInputStream imageStream1 = new ByteArrayInputStream(holder.OutImage);
////                        Bitmap theImage1 = BitmapFactory.decodeStream(imageStream1);
////                        Bitmap rotated1 = Bitmap.createBitmap(theImage1, 0, 0, theImage1.getWidth(), theImage1.getHeight(),
////                                matrix, true);
////                        holder.ImageGallery.setImageBitmap(rotated1);
////
////                        holder.Intime.setText(time);
////                        holder.Outtime.setText(time1);
////
////                    }
////                }
////
////
////            }
////        } else {
////            tvNoRecordsFound.setVisibility(View.VISIBLE);
////        }
////    }
//
//    private boolean deleteFromExternalStorage(String path) {
//
//        File file = new File(path);
//        System.out.println("fullPath - " + path);
//        if (file.exists() && file.canRead()) {
//            System.out.println(" Test - ");
//            file.delete();
//            return false; // File exists
//        }
//        System.out.println(" Test2 - ");
//        return true; // File not exists
//    }
//
//    private Date getDate(String datetimestamp) {
//        try {
//            java.text.DateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//            Date date = format.parse(datetimestamp);
//
//            return date;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    private class Holder {
//        TextView Price;
//        TextView tvFullName;
//        Button delete;
//        ImageView ImageCamera;
//        ImageView ImageGallery;
//        TextView Intime;
//        TextView Outtime;
//        byte[] InImage;
//        byte[] OutImage;
//        String CardIdNo;
//        String CardPrice;
//        String OutTime;
//        String InTime;
//        TextView status;
//        String status1;
//    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(CustomerDetailsActivity.this, AdminArea.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}