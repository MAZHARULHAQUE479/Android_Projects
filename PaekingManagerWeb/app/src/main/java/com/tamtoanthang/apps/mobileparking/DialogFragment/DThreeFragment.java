package com.tamtoanthang.apps.mobileparking.DialogFragment;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import com.tamtoanthang.apps.mobileparking.Adapter.CustomListViewAdapter;
import com.tamtoanthang.apps.mobileparking.DataBase.DatabaseHelper;
import com.tamtoanthang.apps.mobileparking.Model.Detail;
import com.tamtoanthang.apps.mobileparking.DataBase.SQLiteHelper;
import com.tamtoanthang.apps.mobileparking.R;
import com.tamtoanthang.apps.mobileparking.ZoomActivity;
import com.tamtoanthang.apps.mobileparking.java.HTTPResponse;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by lue on 22-11-2016.
 */
public class DThreeFragment extends DialogFragment  {
    Button Search;
    EditText inputsearch,inputsearch1,inputsearch2,inputsearch3,inputsearch4;
    private static final int SHOW_PROCESS_BAR = 1;
    private static final int HIDE_PROCESS_BAR = 0;
    String input="";
    String inputdate="";
    String inputTime="";
    String inputdateout="";
    String inputTimeout="";
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
    ProgressDialog pDialog;
    ListView listView;
    PhotoViewAttacher pAttacher;
    String CardId,CardNo,CardType,CardPrice,InImage,OutImage,InTime,OutTime,Status,UserName,AdminId,BaseUrl;
    public static final String MyPREFERENCES = "MyPrefrence1";
    SharedPreferences pref,Bu;
        //Processbar processbar;
    String fk="";

    public ArrayAdapter<Detail> adapter;
    public ArrayList<Detail> detailList=new ArrayList<Detail>();
    Detail detail;


    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            "dd-MM-yyyy", Locale.ENGLISH);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.dragment_three, container, false);
        getDialog().getActionBar();
        dbHelper = new DatabaseHelper(getActivity());
        Bu = getActivity().getSharedPreferences("Base", Context.MODE_PRIVATE);
        BaseUrl = Bu.getString("baseurl", "");
        parentLayout = (LinearLayout)rootView.findViewById(R.id.parentLayout);
        layoutDisplayPeople = (LinearLayout)rootView.findViewById(R.id.layoutDisplayPeople);


//        tvNoRecordsFound = (TextView)rootView.findViewById(R.id.tvNoRecordsFound);
        databaseHelper = new DatabaseHelper(getActivity());
        inputsearch=(EditText)rootView.findViewById(R.id.inputsearch);
        listView = (ListView)rootView.findViewById(R.id.customer_detail);
        adapter = new CustomListViewAdapter(getActivity(), R.layout.list_item3, detailList);
        adapter.notifyDataSetChanged();
        listView.invalidateViews();
        sQLiteHelper = new SQLiteHelper( getActivity());
        inputsearch1=(EditText)rootView.findViewById(R.id.inputTotal1);
        inputsearch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar newCalendar = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(getActivity(),
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


        inputsearch2=(EditText)rootView.findViewById(R.id.inputTotal2);
        inputsearch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
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
        inputsearch3=(EditText)rootView.findViewById(R.id.inputTotal3);
        inputsearch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar newCalendar = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(getActivity(),
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
        inputsearch4=(EditText)rootView.findViewById(R.id.inputTotal4);
        inputsearch4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
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
        Search=(Button)rootView.findViewById(R.id.Search);
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pref = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                AdminId=pref.getString("adminId","");
                input=inputsearch.getText().toString();
                inputdate=inputsearch1.getText().toString();
                inputTime=inputsearch2.getText().toString();
                inputdateout=inputsearch3.getText().toString();
                inputTimeout=inputsearch4.getText().toString();

                Register(AdminId,input,inputdate,inputTime,inputdateout,inputTimeout);
//                adapter.notifyDataSetChanged();
//                listView.invalidateViews();
                adapter.clear();




            }
        });




        return rootView;
    }
   /* Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what){
                case SHOW_PROCESS_BAR:
                    processbar=new Processbar(getActivity());
                    processbar.showProcess();
                    break;
                case HIDE_PROCESS_BAR:
                    processbar.hideProcess();
                    break;
            }
            return false;
        }
    });
*/
//    private void displayAllRecords() {
//
//        com.rey.material.widget.LinearLayout inflateParentView;
//        parentLayout.removeAllViews();
//
//        ArrayList<Contact> contacts = dbHelper.getContact();
//        if (contacts.size() > 0) {
//            tvNoRecordsFound.setVisibility(View.GONE);
//            Contact contact;
//            for (int v = 0; v < contacts.size(); v++) {
//                String x =contacts.get(v).getInTime();
//                String z=contacts.get(v).getCardNo();
//
//                if(inputdate.equals("")&&inputTime.equals("")) {
//                    if (input.equals(contacts.get(v).getCardNo())) {
//
//                        Log.d("nvn",""+x);
//                            contact = contacts.get(v);
//                        final Holder holder = new Holder();
//                        final View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_lists_one, null);
//                        inflateParentView = (com.rey.material.widget.LinearLayout) view.findViewById(R.id.inflateParentView);
//                        holder.ImageCamera = (ImageView) view.findViewById(R.id.imageView1);
//                        holder.ImageCamera.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                Intent intent=new Intent(getActivity(), ZoomActivity.class);
//                                ByteArrayInputStream imageStream = new ByteArrayInputStream(holder.InImage);
//                                Bitmap theImage = BitmapFactory.decodeStream(imageStream);
//                                Matrix matrix = new Matrix();
//                                matrix.postRotate(90);
//                                Bitmap rotated = Bitmap.createBitmap(theImage, 0, 0, theImage.getWidth(), theImage.getHeight(),
//                                        matrix, true);
//                                intent.putExtra("inimage",rotated);
//                                startActivity(intent);
//
//                            }
//                        });
//                        holder.ImageGallery = (ImageView) view.findViewById(R.id.imageView2);
//                        holder.ImageGallery.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                Intent intent1=new Intent(getActivity(), ZoomActivity.class);
//                                ByteArrayInputStream imageStream = new ByteArrayInputStream(holder.OutImage);
//                                Bitmap theImage1 = BitmapFactory.decodeStream(imageStream);
//                                Matrix matrix = new Matrix();
//                                matrix.postRotate(90);
//
//                                Bitmap rotated1 = Bitmap.createBitmap(theImage1, 0, 0, theImage1.getWidth(), theImage1.getHeight(),
//                                        matrix, true);
//                                intent1.putExtra("inimage",rotated1);
//                                startActivity(intent1);
//
//
//                            }
//                        });
//                        holder.tvFullName = (TextView) view.findViewById(R.id.tvFullName);
//                        holder.tvFullNo=(TextView)view.findViewById(R.id.tvFullNo);
//                        holder.Price = (TextView) view.findViewById(R.id.tvFullName1);
//                        holder.Intime = (TextView) view.findViewById(R.id.tvFullINTime);
//
//                        holder.Outtime = (TextView) view.findViewById(R.id.tvFullOutTime);
//                        holder.status=(TextView)view.findViewById(R.id.tvFullName3);
//
//                        view.setTag(contact.getID());
//                        holder.CardNo=contact.getCardNo();
//                        holder.CardIdNo = contact.getCardIdNo();
//                        holder.CardPrice = contact.getPrice();
//                        holder.InImage = contact.getInImage();
//                        holder.OutImage = contact.getOutImage();
//                        holder.OutTime = contact.getOutTime();
//                        holder.InTime = contact.getInTime();
//                        holder.status1=contact.getStatus();
//
//                        String cardIdNo = holder.CardIdNo;
//                        String cardNo=holder.CardNo;
//                        String Price = holder.CardPrice;
//                        String time = holder.InTime;
//                        String time1 = holder.OutTime;
//                        String timeu1=holder.status1;
//
//                        holder.tvFullName.setText(cardIdNo);
//                        holder.Price.setText(Price);
//                        holder.status.setText(timeu1);
//                        holder.tvFullNo.setText(cardNo);
//                        ByteArrayInputStream imageStream = new ByteArrayInputStream(holder.InImage);
//                        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
//                        Matrix matrix = new Matrix();
//                        matrix.postRotate(90);
//
//                        Bitmap rotated = Bitmap.createBitmap(theImage, 0, 0, theImage.getWidth(), theImage.getHeight(),
//                                matrix, true);
//                        holder.ImageCamera.setImageBitmap(rotated);
//                        ByteArrayInputStream imageStream1 = new ByteArrayInputStream(holder.OutImage);
//                        Bitmap theImage1 = BitmapFactory.decodeStream(imageStream1);
//                        Bitmap rotated1 = Bitmap.createBitmap(theImage1, 0, 0, theImage1.getWidth(), theImage1.getHeight(),
//                                matrix, true);
//                        holder.ImageGallery.setImageBitmap(rotated1);
//
//                        holder.Intime.setText(time);
//                        holder.Outtime.setText(time1);
//
//                        final CharSequence[] items = {Constants.UPDATE, Constants.DELETE};
//                        parentLayout.addView(view);
//
//                    }
//
//                }
//
//                else if( (input.equals(contacts.get(v).getCardNo())&&getDate(x).after(getDate(inputdate+" "+inputTime+":00"))&& getDate(x).before(getDate(inputdateout+" "+inputTimeout+":00"))) ){
//
//                    contact = contacts.get(v);
//                    final Holder holder = new Holder();
//                    final View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_lists_one, null);
//                    inflateParentView = (com.rey.material.widget.LinearLayout) view.findViewById(R.id.inflateParentView);
//                    holder.ImageCamera = (ImageView) view.findViewById(R.id.imageView1);
//                    holder.ImageCamera.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            Intent intent=new Intent(getActivity(), ZoomActivity.class);
//                            ByteArrayInputStream imageStream = new ByteArrayInputStream(holder.InImage);
//                            Bitmap theImage = BitmapFactory.decodeStream(imageStream);
//                            Matrix matrix = new Matrix();
//                            matrix.postRotate(90);
//                            Bitmap rotated = Bitmap.createBitmap(theImage, 0, 0, theImage.getWidth(), theImage.getHeight(),
//                                    matrix, true);
//                            intent.putExtra("inimage",rotated);
//                            startActivity(intent);
//
//                        }
//                    });
//                    holder.ImageGallery = (ImageView) view.findViewById(R.id.imageView2);
//                    holder.ImageGallery.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            Intent intent1=new Intent(getActivity(), ZoomActivity.class);
//                            ByteArrayInputStream imageStream = new ByteArrayInputStream(holder.OutImage);
//                            Bitmap theImage1 = BitmapFactory.decodeStream(imageStream);
//                            Matrix matrix = new Matrix();
//                            matrix.postRotate(90);
//
//                            Bitmap rotated1 = Bitmap.createBitmap(theImage1, 0, 0, theImage1.getWidth(), theImage1.getHeight(),
//                                    matrix, true);
//                            intent1.putExtra("inimage",rotated1);
//                            startActivity(intent1);
//
//
//                        }
//                    });
//                    holder.tvFullName = (TextView) view.findViewById(R.id.tvFullName);
//                    holder.tvFullNo=(TextView)view.findViewById(R.id.tvFullNo);
//                    holder.Price = (TextView) view.findViewById(R.id.tvFullName1);
//                    holder.Intime = (TextView) view.findViewById(R.id.tvFullINTime);
//
//                    holder.Outtime = (TextView) view.findViewById(R.id.tvFullOutTime);
//                    holder.status=(TextView)view.findViewById(R.id.tvFullName3);
//
//                    view.setTag(contact.getID());
//                    holder.CardNo=contact.getCardNo();
//                    holder.CardIdNo = contact.getCardIdNo();
//                    holder.CardPrice = contact.getPrice();
//                    holder.InImage = contact.getInImage();
//                    holder.OutImage = contact.getOutImage();
//                    holder.OutTime = contact.getOutTime();
//                    holder.InTime = contact.getInTime();
//                    holder.status1=contact.getStatus();
//
//                    String cardIdNo = holder.CardIdNo;
//                    String cardNo=holder.CardNo;
//                    String Price = holder.CardPrice;
//                    String time = holder.InTime;
//                    String time1 = holder.OutTime;
//                    String timeu1=holder.status1;
//
//                    holder.tvFullName.setText(cardIdNo);
//                    holder.Price.setText(Price);
//                    holder.status.setText(timeu1);
//                    holder.tvFullNo.setText(cardNo);
//                    ByteArrayInputStream imageStream = new ByteArrayInputStream(holder.InImage);
//                    Bitmap theImage = BitmapFactory.decodeStream(imageStream);
//                    Matrix matrix = new Matrix();
//                    matrix.postRotate(90);
//
//                    Bitmap rotated = Bitmap.createBitmap(theImage, 0, 0, theImage.getWidth(), theImage.getHeight(),
//                            matrix, true);
//                    holder.ImageCamera.setImageBitmap(rotated);
//                    ByteArrayInputStream imageStream1 = new ByteArrayInputStream(holder.OutImage);
//                    Bitmap theImage1 = BitmapFactory.decodeStream(imageStream1);
//                    Bitmap rotated1 = Bitmap.createBitmap(theImage1, 0, 0, theImage1.getWidth(), theImage1.getHeight(),
//                            matrix, true);
//                    holder.ImageGallery.setImageBitmap(rotated1);
//
//                    holder.Intime.setText(time);
//                    holder.Outtime.setText(time1);
//
//                    final CharSequence[] items = {Constants.UPDATE, Constants.DELETE};
//                    parentLayout.addView(view);
//
//                }
//                else if(input.equals("")){
//                    if(getDate(x).after(getDate(inputdate+" "+inputTime+":00"))&& getDate(x).before(getDate(inputdateout+" "+inputTimeout+":00"))){
//                        contact = contacts.get(v);
//                        final Holder holder = new Holder();
//                        final View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_lists_one, null);
//                        inflateParentView = (com.rey.material.widget.LinearLayout) view.findViewById(R.id.inflateParentView);
//                        holder.ImageCamera = (ImageView) view.findViewById(R.id.imageView1);
//                        holder.ImageCamera.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                Intent intent=new Intent(getActivity(), ZoomActivity.class);
//                                ByteArrayInputStream imageStream = new ByteArrayInputStream(holder.InImage);
//                                Bitmap theImage = BitmapFactory.decodeStream(imageStream);
//                                Matrix matrix = new Matrix();
//                                matrix.postRotate(90);
//                                Bitmap rotated = Bitmap.createBitmap(theImage, 0, 0, theImage.getWidth(), theImage.getHeight(),
//                                        matrix, true);
//                                intent.putExtra("inimage",rotated);
//                                startActivity(intent);
//
//                            }
//                        });
//                        holder.ImageGallery = (ImageView) view.findViewById(R.id.imageView2);
//                        holder.ImageGallery.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                Intent intent1=new Intent(getActivity(), ZoomActivity.class);
//                                ByteArrayInputStream imageStream = new ByteArrayInputStream(holder.OutImage);
//                                Bitmap theImage1 = BitmapFactory.decodeStream(imageStream);
//                                Matrix matrix = new Matrix();
//                                matrix.postRotate(90);
//
//                                Bitmap rotated1 = Bitmap.createBitmap(theImage1, 0, 0, theImage1.getWidth(), theImage1.getHeight(),
//                                        matrix, true);
//                                intent1.putExtra("inimage",rotated1);
//                                startActivity(intent1);
//
//
//                            }
//                        });
//                        holder.tvFullName = (TextView) view.findViewById(R.id.tvFullName);
//                        holder.tvFullNo=(TextView)view.findViewById(R.id.tvFullNo);
//                        holder.Price = (TextView) view.findViewById(R.id.tvFullName1);
//                        holder.Intime = (TextView) view.findViewById(R.id.tvFullINTime);
//
//                        holder.Outtime = (TextView) view.findViewById(R.id.tvFullOutTime);
//                        holder.status=(TextView)view.findViewById(R.id.tvFullName3);
//
//                        view.setTag(contact.getID());
//                        holder.CardNo=contact.getCardNo();
//                        holder.CardIdNo = contact.getCardIdNo();
//                        holder.CardPrice = contact.getPrice();
//                        holder.InImage = contact.getInImage();
//                        holder.OutImage = contact.getOutImage();
//                        holder.OutTime = contact.getOutTime();
//                        holder.InTime = contact.getInTime();
//                        holder.status1=contact.getStatus();
//
//                        String cardIdNo = holder.CardIdNo;
//                        String cardNo=holder.CardNo;
//                        String Price = holder.CardPrice;
//                        String time = holder.InTime;
//                        String time1 = holder.OutTime;
//                        String timeu1=holder.status1;
//
//                        holder.tvFullName.setText(cardIdNo);
//                        holder.Price.setText(Price);
//                        holder.status.setText(timeu1);
//                        holder.tvFullNo.setText(cardNo);
//                        ByteArrayInputStream imageStream = new ByteArrayInputStream(holder.InImage);
//                        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
//                        Matrix matrix = new Matrix();
//                        matrix.postRotate(90);
//
//                        Bitmap rotated = Bitmap.createBitmap(theImage, 0, 0, theImage.getWidth(), theImage.getHeight(),
//                                matrix, true);
//                        holder.ImageCamera.setImageBitmap(rotated);
//                        ByteArrayInputStream imageStream1 = new ByteArrayInputStream(holder.OutImage);
//                        Bitmap theImage1 = BitmapFactory.decodeStream(imageStream1);
//                        Bitmap rotated1 = Bitmap.createBitmap(theImage1, 0, 0, theImage1.getWidth(), theImage1.getHeight(),
//                                matrix, true);
//                        holder.ImageGallery.setImageBitmap(rotated1);
//
//                        holder.Intime.setText(time);
//                        holder.Outtime.setText(time1);
//
//                        final CharSequence[] items = {Constants.UPDATE, Constants.DELETE};
//                        parentLayout.addView(view);
//
//
//                    }
//                }
//
//
//
//            }
//        } else {
//            tvNoRecordsFound.setVisibility(View.VISIBLE);
//        }
//    }
   public void  Register(final String AdminId,
                          final String input,
                         final String inputdate,
                         final String inputTime,
                         final String inputdateout,
                         final String inputTimeout
   ){
       class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {

           protected void onPreExecute() {
               super.onPreExecute();
               pDialog = new ProgressDialog(getActivity());
               pDialog.setMessage("Please wait...");
               pDialog.setCancelable(true);
               pDialog.show();

           }
           @Override
           protected String doInBackground(String... params) {
               String return_text="";
               JSONObject jsonObject=new JSONObject();
               try {
                   jsonObject.accumulate("admin_id",AdminId);
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
                       String jresult=jsonObject.getString("result");
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

                           Detail detail=new Detail();
                           detail.setCardId(CardId);
                           detail.setCardNo(CardNo);
                           detail.setCardType(CardType);
                           detail.setCardPrice(CardPrice);
                           detail.setInImage(InImage);
                           detail.setOutImage(OutImage);
                           detail.setInTime(InTime);
                           detail.setOutTime(OutTime);
                           detail.setStatus(Status);
                           detail.setUserName(UserName);
                           detailList.add(detail);

                           listView.setAdapter(adapter);

                       }
                       pDialog.hide();

//                       Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
                       Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();


                   }

                   else if (jsonObject.getString("error").equalsIgnoreCase("true")) {
                       String  message = jsonObject.getString("message");
                       if (message != null) {
                           {
                               pDialog.hide();

                               Toast.makeText(getActivity(),message, Toast.LENGTH_LONG).show();
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
    private Date getDate(String datetimestamp){
        try {
            java.text.DateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = format.parse(datetimestamp);

            return date;
        }catch (Exception e){e.printStackTrace();
            return null;}
    }


}
