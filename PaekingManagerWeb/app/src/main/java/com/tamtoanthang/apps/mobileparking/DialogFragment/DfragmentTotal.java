package com.tamtoanthang.apps.mobileparking.DialogFragment;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.tamtoanthang.apps.mobileparking.Model.Contact;
import com.tamtoanthang.apps.mobileparking.DataBase.SQLiteHelper;
import com.tamtoanthang.apps.mobileparking.DataBase.DatabaseHelper;
import com.tamtoanthang.apps.mobileparking.Model.Total;
import com.tamtoanthang.apps.mobileparking.Adapter.TotalListviewAdapter;
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
import java.util.List;
import java.util.Locale;

/**
 * Created by lue on 22-11-2016.
 */
public class DfragmentTotal extends DialogFragment {
    Button Search;
    EditText inputsearch, inputsearch1,inputsearch2,inputsearch3,inputsearch4;
    SQLiteHelper sQLiteHelper;
    android.widget.LinearLayout parentLayout;
    LinearLayout layoutDisplayPeople;
    SharedPreferences pref,Bu;
    public static final String MyPREFERENCES = "MyPrefrence1";
    TextView tvNoRecordsFound;
    private String rowID = null;
    String Total_Amount,Total_CarOut,Total_CarIn,UserId,AdminId,BaseUrl;
    SQLiteDatabase database;
    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;
    D2Fragment d2Fragment;
    DatabaseHelper dbHelper;
    List<String> v=new ArrayList<>();
    List<String> c=new ArrayList<>();
    String v2;
    String j,t2;
    int price = 0;
    String input="";
    String inputdate="";
    String inputTime="";
    String inputdateout="";
    String inputTimeout="";
    String fj="";
    TextView tAmount,tcarIn,tcarOut;
    Double Value = 0.0;
    int ValueCarIn=0;
    int ValueCarOut=0;
    String TotalCarOt="";
    Contact contact12;
//    ArrayList<String>TotalCarOut=new ArrayList<>();
    ArrayList<String>TotalAmount=new ArrayList<>();
    ArrayList<String>TotalCarIn=new ArrayList<>();
    ArrayList<String>TotalCarOut=new ArrayList<>();
    public ArrayAdapter<Total> adapter;
    public ArrayList<Total> TotalList=new ArrayList<Total>();

    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            "dd-MM-yyyy", Locale.ENGLISH);
    ProgressDialog pDialog;
    DatePickerDialog datePickerDialog;
    ListView listView;
    Calendar dateCalendar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.deagment_total, container, false);
        getDialog().getActionBar();
        Bu = getActivity().getSharedPreferences("Base", Context.MODE_PRIVATE);
        BaseUrl = Bu.getString("baseurl", "");
        dbHelper = new DatabaseHelper(getActivity());
        parentLayout = (LinearLayout)rootView.findViewById(R.id.parentLayout);
        layoutDisplayPeople = (LinearLayout)rootView.findViewById(R.id.layoutDisplayPeople);

//        tvNoRecordsFound = (TextView)rootView.findViewById(R.id.tvNoRecordsFound);
        sQLiteHelper = new SQLiteHelper( getActivity());
        inputsearch=(EditText)rootView.findViewById(R.id.inputsearch);
        tAmount=(TextView)rootView.findViewById(R.id.tAmount);
        tcarIn=(TextView)rootView.findViewById(R.id.tCarin);
        listView = (ListView)rootView.findViewById(R.id.customerdetail);
        adapter = new TotalListviewAdapter(getActivity(), R.layout.list_item5, TotalList);
        tcarOut=(TextView) rootView.findViewById(R.id.tCarout);



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
                input = inputsearch.getText().toString();
                inputdate = inputsearch1.getText().toString();
                inputTime = inputsearch2.getText().toString();
                inputdateout = inputsearch3.getText().toString();
                inputTimeout = inputsearch4.getText().toString();
                UserCardDetail(AdminId,input,inputdate,inputTime,inputdateout,inputTimeout);
                adapter.clear();

//                TotalAmount.clear();
//                TotalCarIn.clear();
//                TotalCarOut.clear();
              /*  ArrayList<Contact> contact2 = dbHelper.getContact();
                ArrayList<ContactModel>contactModels=sQLiteHelper.getAllRecordsUser();

                for (int r = 0; r < contact2.size(); r++) {
                    String fj = contact2.get(r).getInTime();
                    String eede=contactModels.get(r).getUserINTime();

                    if (inputdate.equals("") && inputTime.equals("")) {
                        if (input.equals(contact2.get(r).getUserName()) ) {
                            Log.d("ifccccccci","dsdrr");
                            c.add(contact2.get(r).getCardIdNo());
                            v.add(contact2.get(r).getPrice());
                        }

                    }else if(input.equals(contact2.get(r).getUserName()) && getDate(fj).after(getDate(inputdate + " " + inputTime + ":00")) && getDate(fj).before(getDate(inputdateout + " " + inputTimeout + ":00"))){
                        c.add(contact2.get(r).getCardIdNo());
                        Log.d("ifccccccci","dsdff");
                        v.add(contact2.get(r).getPrice());
                    } else if(input.equals("")){
                        if(getDate(eede).after(getDate(inputdate + " " + inputTime + ":00")) && getDate(eede).before(getDate(inputdateout + " " + inputTimeout + ":00"))){
                            c.add(contact2.get(r).getCardIdNo());
                            Log.d("ifccccccci","dsxxd");
                            v.add(contact2.get(r).getPrice());

                        }
                    }
                }
                v2 = String.valueOf(c.size());
                for (int p = 0; p < v.size(); p++) {
                    price = price + (Integer.parseInt(v.get(p)));
                }*/
//                displayAllRecords();


             /*   c.clear();
                v.clear();
                price=0;*/

            }
        });



        return rootView;
    }
    public void  UserCardDetail(final String AdminId,
                                final String input,
                                final String inputdate,
                                final String inputTime,
                                final String inputdateout,
                                final String inputTimeout

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
                    jsonObject.accumulate("admin_id",AdminId);
                    jsonObject.accumulate("user_name",input);
                    jsonObject.accumulate("date1",inputdate);
                    jsonObject.accumulate("time1",inputTime);
                    jsonObject.accumulate("date2",inputdateout);
                    jsonObject.accumulate("time2",inputTimeout);



                } catch (JSONException e) {
                    e.printStackTrace();
                }


                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(BaseUrl+"/assistance.php");
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
                        String finalArry=jsonObject.getString("final1rr");
                        System.out.println("=====finalArry===="+finalArry);
                        Total_Amount=jsonObject.getString("Total_Amount");
                        Total_CarIn=jsonObject.getString("Car_in");
                        Total_CarOut=jsonObject.getString("Car_out");
                        tAmount.setText(Total_Amount);
                        tcarIn.setText(Total_CarIn);
                        tcarOut.setText(Total_CarOut);

                        JSONArray userdetail=jsonObject.getJSONArray("final1rr");
                        for (int i=0;i<userdetail.length();i++)
                        {
                            JSONObject c=userdetail.getJSONObject(i);
                            String user_name=c.getString("User_name");
                            String total_price=c.getString("Total Price");
                            String login_time=c.getString("Login time");
                            String logout_time=c.getString("Logout time");
                            String total_car=c.getString("Total cars");

                            Total total=new Total();
                            total.setTUserName(user_name);
                            total.setTPrice(total_price);
                            total.setTLogInTime(login_time);
                            total.setTLogOutTime(logout_time);
                            total.setTCar(total_car);
                            TotalList.add(total);


                            listView.setAdapter(adapter);




                        }


//                        Toast.makeText(getActivity(), Total_Amount+Total_CarIn+Total_CarOut, Toast.LENGTH_SHORT).show();






                    }

                    else if (jsonObject.getString("error").equalsIgnoreCase("true")) {
                        String  message = jsonObject.getString("message");
                        if (message != null) {
                            {
                                System.out.println("===========================error");
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


//    private void displayAllRecords() {
//        tAmount.setText(String.valueOf(0));
//        tcarIn.setText(String .valueOf(0));
//        tcarOut.setText(String.valueOf(0));
//         Value = 0.0;
//         ValueCarIn=0;
//        ValueCarOut=0;
//        com.rey.material.widget.LinearLayout inflateParentView;
//        parentLayout.removeAllViews();
//        ArrayList<ContactModel> contacts = sQLiteHelper.getAllRecordsUser4();
//
//
//
//
//        if (contacts.size() > 0) {
//            tvNoRecordsFound.setVisibility(View.GONE);
//            ContactModel contact;
//
//                TotalAmount.clear();
//            TotalCarIn.clear();
//            TotalCarOut.clear();
//
//            for (int i = 0; i < contacts.size(); i++) {
//                String x =contacts.get(i).getUserINTime();
//                String Y=contacts.get(i).getUsserOutTime();
//
//                if(inputdate.equals("")&&inputTime.equals("")) {
//
//                    TotalCarIn.clear();
//                    TotalAmount.clear();
//                    TotalCarOut.clear();
//                    tAmount.setText("");
//                    tcarIn.setText("");
//                    tcarOut.setText("");
//                    if (input.equals(contacts.get(i).getUserName())) {
//
//                        contact = contacts.get(i);
//                        final Holder holder = new Holder();
//                        final View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_lists_total, null);
//                        inflateParentView = (com.rey.material.widget.LinearLayout) view.findViewById(R.id.inflateParentView);
//                        holder.username = (TextView) view.findViewById(R.id.username);
//                        holder.Price = (TextView) view.findViewById(R.id.tvFullName1);
//                        holder.Intime = (TextView) view.findViewById(R.id.tvFullName3);
//                        holder.Outtime = (TextView) view.findViewById(R.id.tvFullName4);
//                        holder.totalCar = (TextView) view.findViewById(R.id.tvFullName5);
//
//
//
//                        view.setTag(contact.getID());
//
//                        holder.UserName = contact.getUserName();
//                        holder.TotalPrice = contact.getPrice();
//                        holder.UserInTime = contact.getUserINTime();
//                        holder.UserOutTime = contact.getUsserOutTime();
//                        holder.Totalcar = contact.getCardIdNo();
//
//                        String userName = holder.UserName;
//                        String Price = holder.TotalPrice;
//
//                        String time = holder.UserInTime;
//                        String time1 = holder.UserOutTime;
//                        String TotalCar = holder.Totalcar;
//
//
//
//
//                        holder.username.setText(userName);
//                        holder.Price.setText(Price);
//                        holder.Intime.setText(time);
//                        holder.Outtime.setText(time1);
//
//
//                        holder.totalCar.setText(TotalCar);
//                        TotalAmount.add(Price);
//                        TotalCarIn.add(TotalCar);
//                        TotalCarOt=holder.UserName;
//
//                        Value =sum();
//
//                        ValueCarIn =sum1();
//                        ValueCarOut=sumTotalCarOut();
//
//                        final CharSequence[] items = {Constants.UPDATE, Constants.DELETE};
//                        inflateParentView.setOnLongClickListener(new View.OnLongClickListener() {
//                            @Override
//                            public boolean onLongClick(View v) {
//
//                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                                builder.setItems(items, new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        if (which == 0) {
//
//                                            rowID = view.getTag().toString();
//                                        } else {
//                                            AlertDialog.Builder deleteDialogOk = new AlertDialog.Builder(getActivity());
//                                            deleteDialogOk.setTitle("Delete Contact?");
//                                            deleteDialogOk.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                                                        @Override
//                                                        public void onClick(DialogInterface dialog, int which) {
//                                                            displayAllRecords();
//                                                        }
//                                                    }
//                                            );
//                                            deleteDialogOk.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                                                @Override
//                                                public void onClick(DialogInterface dialog, int which) {
//
//                                                }
//                                            });
//                                            deleteDialogOk.show();
//                                        }
//                                    }
//                                });
//                                AlertDialog alertDialog = builder.create();
//                                alertDialog.show();
//                                return true;
//                            }
//
//                        });
//                        parentLayout.addView(view);
//                    }
//                    tAmount.setText(String.valueOf(Value));
//                    tcarIn.setText(String.valueOf(ValueCarIn));
//                    tcarOut.setText(String.valueOf(ValueCarOut));
//
//
//                }
//                else if((input.equals(contacts.get(i).getUserName())&&getDate(x).after(getDate(inputdate + " " + inputTime + ":00")) && getDate(x).before(getDate(inputdateout + " " + inputTimeout + ":00")))){
//                    contact = contacts.get(i);
//                    TotalAmount.clear();
//                    TotalCarIn.clear();
//                    TotalCarOut.clear();
//                    tAmount.setText("");
//                    tcarIn.setText("");
//                    tcarOut.setText("");
//                    final Holder holder = new Holder();
//                    final View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_lists_total, null);
//                    inflateParentView = (com.rey.material.widget.LinearLayout) view.findViewById(R.id.inflateParentView);
//                    holder.username = (TextView) view.findViewById(R.id.username);
//                    holder.Price = (TextView) view.findViewById(R.id.tvFullName1);
//                    holder.Intime = (TextView) view.findViewById(R.id.tvFullName3);
//                    holder.Outtime = (TextView) view.findViewById(R.id.tvFullName4);
//                    holder.totalCar = (TextView) view.findViewById(R.id.tvFullName5);
//
//
//                    view.setTag(contact.getID());
//
//                    holder.UserName = contact.getUserName();
//                    holder.TotalPrice = contact.getPrice();
//                    holder.UserInTime = contact.getUserINTime();
//                    holder.UserOutTime = contact.getUsserOutTime();
//                    holder.Totalcar = contact.getCardIdNo();
//
//                    String userName = holder.UserName;
//                    String Price = holder.TotalPrice;
//
//                    String time = holder.UserInTime;
//                    String time1 = holder.UserOutTime;
//                    String TotalCar = holder.Totalcar;
//                    TotalCarOt=holder.UserName;
//                    TotalAmount.add(Price);
//                    TotalCarIn.add(TotalCar);
//                    TotalCarOut.add(TotalCar);
//                    Value =sum();
//                    ValueCarIn=sum1();
//                    ValueCarOut=sumTotalCarOut();
//
//                    holder.username.setText(userName);
//                    holder.Price.setText(Price);
//                    holder.Intime.setText(time);
//                    holder.Outtime.setText(time1);
//                    holder.totalCar.setText(TotalCar);
//
//                    final CharSequence[] items = {Constants.UPDATE, Constants.DELETE};
//                    inflateParentView.setOnLongClickListener(new View.OnLongClickListener() {
//                        @Override
//                        public boolean onLongClick(View v) {
//
//                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                            builder.setItems(items, new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    if (which == 0) {
//
//                                        rowID = view.getTag().toString();
//                                    } else {
//                                        AlertDialog.Builder deleteDialogOk = new AlertDialog.Builder(getActivity());
//                                        deleteDialogOk.setTitle("Delete Contact?");
//                                        deleteDialogOk.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                                                    @Override
//                                                    public void onClick(DialogInterface dialog, int which) {
//                                                        displayAllRecords();
//                                                    }
//                                                }
//                                        );
//                                        deleteDialogOk.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialog, int which) {
//
//                                            }
//                                        });
//                                        deleteDialogOk.show();
//                                    }
//                                }
//                            });
//                            AlertDialog alertDialog = builder.create();
//                            alertDialog.show();
//                            return true;
//                        }
//
//                    });
//                    parentLayout.addView(view);
//                    tAmount.setText(String.valueOf(Value));
//                    tcarIn.setText(String.valueOf(ValueCarIn));
//                    tcarOut.setText(String.valueOf(ValueCarOut));
//
//                }
//                else if(input.equals("")) {
//                    if (getDate(x).after(getDate(inputdate + " " + inputTime + ":00")) && getDate(x).before(getDate(inputdateout + " " + inputTimeout + ":00"))) {
//                        contact = contacts.get(i);
//                        final Holder holder = new Holder();
//                        TotalAmount.clear();
//                        TotalCarIn.clear();
//                        TotalCarOut.clear();
//                        tAmount.setText("");
//                        tcarIn.setText("");
//                        tcarOut.setText("");
//                        final View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_lists_total, null);
//                        inflateParentView = (com.rey.material.widget.LinearLayout) view.findViewById(R.id.inflateParentView);
//                        holder.username = (TextView) view.findViewById(R.id.username);
//                        holder.Price = (TextView) view.findViewById(R.id.tvFullName1);
//                        holder.Intime = (TextView) view.findViewById(R.id.tvFullName3);
//                        holder.Outtime = (TextView) view.findViewById(R.id.tvFullName4);
//                        holder.totalCar = (TextView) view.findViewById(R.id.tvFullName5);
//
//
//                        view.setTag(contact.getID());
//
//                        holder.UserName = contact.getUserName();
//                        holder.TotalPrice = contact.getPrice();
//                        holder.UserInTime = contact.getUserINTime();
//                        holder.UserOutTime = contact.getUsserOutTime();
//                        holder.Totalcar = contact.getCardIdNo();
//
//                        String userName = holder.UserName;
//                        String Price = holder.TotalPrice;
//
//                        String time = holder.UserInTime;
//                        String time1 = holder.UserOutTime;
//                        String TotalCar = holder.Totalcar;
//                        TotalCarOt=holder.UserName;
//
//
//                        holder.username.setText(userName);
//                        holder.Price.setText(Price);
//                        holder.Intime.setText(time);
//                        holder.Outtime.setText(time1);
//                        holder.totalCar.setText(TotalCar);
//                        TotalAmount.add(Price);
//                        TotalCarIn.add(TotalCar);
//                        TotalCarOut.add(TotalCar);
//
//
//                        Value =sum();
//                        ValueCarIn=sum1();
//                        ValueCarOut=sumTotalCarOut();
//
//
//                        final CharSequence[] items = {Constants.UPDATE, Constants.DELETE};
//                        inflateParentView.setOnLongClickListener(new View.OnLongClickListener() {
//                            @Override
//                            public boolean onLongClick(View v) {
//
//                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                                builder.setItems(items, new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        if (which == 0) {
//
//                                            rowID = view.getTag().toString();
//                                        } else {
//                                            AlertDialog.Builder deleteDialogOk = new AlertDialog.Builder(getActivity());
//                                            deleteDialogOk.setTitle("Delete Contact?");
//                                            deleteDialogOk.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                                                        @Override
//                                                        public void onClick(DialogInterface dialog, int which) {
//                                                            displayAllRecords();
//                                                        }
//                                                    }
//                                            );
//                                            deleteDialogOk.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                                                @Override
//                                                public void onClick(DialogInterface dialog, int which) {
//
//                                                }
//                                            });
//                                            deleteDialogOk.show();
//                                        }
//                                    }
//                                });
//                                AlertDialog alertDialog = builder.create();
//                                alertDialog.show();
//                                return true;
//                            }
//
//                        });
//                        parentLayout.addView(view);
//                    }
//                    tAmount.setText(String.valueOf(Value));
//                    tcarIn.setText(String.valueOf(ValueCarIn));
//                   tcarOut.setText(String.valueOf(ValueCarOut));
//
//                }
//            }
//        } else {
//            tvNoRecordsFound.setVisibility(View.VISIBLE);
//        }
//
//    }
//       private  double sum(){
//            for (int z=0; z<TotalAmount.size(); z++){
//                    Value += Double.valueOf(TotalAmount.get(z));
//
//                }
//                return Value;
//
//       }
//       private  int sum1(){
//            for (int u=0; u<TotalCarIn.size(); u++){
//                Log.d("cssndjnj",""+TotalCarIn.get(u));
//                    ValueCarIn += Double.valueOf(TotalCarIn.get(u));
//
//                }
//                return ValueCarIn;
//
//
//       }private  int sumTotalCarOut(){
//        ArrayList<String>cj=new ArrayList<>();
//            for (int u=0; u<TotalCarIn.size(); u++){
//                String username=TotalCarOt;
//
//                ArrayList<Contact>tcv=dbHelper.getContactUsername(username,"0");
//                Log.d("cndjnj",""+username+"**"+tcv.size());
//
//                Log.d("cssndjnj",""+username+"**"+cj.size());
//                    ValueCarOut = Integer.parseInt(String.valueOf((tcv.size())));
//                Log.d("cssndjnj",""+username+"**"+ValueCarOut);
//                }
//                return ValueCarOut;
//
//
//       }
//
//    private Date getDate(String datetimestamp){
//        try {
//            java.text.DateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//            Date date = format.parse(datetimestamp);
//
//            return date;
//        }catch (Exception e){e.printStackTrace();
//            return null;}
//    }
//    private class Holder {
//        TextView Price;
//        TextView username;
//        TextView totalCar;
//        TextView totalCarOut;
//        TextView Intime;
//        TextView Outtime;
//        String UserName;
//        String TotalPrice;
//        String UserOutTime;
//        String Totalcar;
//        String TotalCarOut;
//        String UserInTime;
//    }
}
