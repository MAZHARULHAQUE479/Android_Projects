package com.tamtoanthang.apps.mobileparking.DialogFragment;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.tamtoanthang.apps.mobileparking.Customer.CustomerDetailsActivity;
import com.tamtoanthang.apps.mobileparking.DataBase.DatabaseHelper;
import com.tamtoanthang.apps.mobileparking.R;
import com.tamtoanthang.apps.mobileparking.java.HTTPResponse;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by lue on 24-04-2017.
 */
public class DeleteFragment extends DialogFragment  {
EditText editdate,edittime;
    SharedPreferences SM1,Bu;
    String Id,BaseUrl;
    Button ok;
    DatePickerDialog datePickerDialog;
    Calendar dateCalendar;
    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            "dd-MM-yyyy", Locale.ENGLISH);

    DatabaseHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_delete, container, false);
        Bu = getActivity().getSharedPreferences("Base", Context.MODE_PRIVATE);
        BaseUrl = Bu.getString("baseurl", "");

        SM1 =getActivity().getSharedPreferences("Myid", Context.MODE_PRIVATE);
        Id=SM1.getString("id","");
        editdate=(EditText)rootView.findViewById(R.id.date);
        dbHelper = new DatabaseHelper(getActivity());

        editdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar newCalendar = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                dateCalendar = Calendar.getInstance();
                                dateCalendar.set(year, monthOfYear, dayOfMonth);
                                editdate.setText(formatter.format(dateCalendar
                                        .getTime()));
                            }

                        }, newCalendar.get(Calendar.YEAR),
                        newCalendar.get(Calendar.MONTH),
                        newCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        edittime=(EditText)rootView.findViewById(R.id.time);
        edittime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                edittime.setText(hourOfDay + ":" + minute);
                            }
                        }, hour, minute, false);
                timePickerDialog.show();
            }
        });


        ok=(Button)rootView.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editdate.getText().toString();
                String t = edittime.getText().toString();
//                String ts=s+" "+t+":00";
//                Log.d("ts","  "+ts);
//                ArrayList<Contact> contacts = dbHelper.getContact();
//                for (int i = 0; i < contacts.size(); i++) {
//                    String x =contacts.get(i).getInTime();
//                    String date2=x.substring(0,2);
//
//                    String status=contacts.get(i).getStatus();
//
//                    if((getDate(x).before(getDate(s+" "+t+":00")))&&(status.equals("0"))){
//                        contacts.get(i).getID();
//
//                        deleteFromExternalStorage(contacts.get(i).getIn_ImagePath());
//                        deleteFromExternalStorage(contacts.get(i).getOut_ImagePath());
//                        dbHelper.deleteRow(contacts.get(i).getID());
                DeleteAll(Id,s,t);
                Intent intent = new Intent(getActivity(), CustomerDetailsActivity.class);
                startActivity(intent);

//                    }else {
//                        DeleteFragment.this.dismiss();
//                    }
//                }
//
//
            }

        });
        return  rootView;
    }

    public void  DeleteAll(final String Id,
                            final String data,
                          final String time

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
                    jsonObject.accumulate("date1",data);
                    jsonObject.accumulate("time1",time);



                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(BaseUrl+"/transaction_delete_all.php");
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


                        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();


                    }

                    else if (jsonObject.getString("error").equalsIgnoreCase("true")) {
                        String  message = jsonObject.getString("message");
                        if (message != null) {
                            {

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
//    private Date getDate(String datetimestamp){
//        try {
//            java.text.DateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//            Date date = format.parse(datetimestamp);
//
//            return date;
//        }catch (Exception e){e.printStackTrace();
//            return null;}
//    }
//   private boolean deleteFromExternalStorage(String path) {
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
}
