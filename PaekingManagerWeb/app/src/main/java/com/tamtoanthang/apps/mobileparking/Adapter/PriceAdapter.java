package com.tamtoanthang.apps.mobileparking.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tamtoanthang.apps.mobileparking.DataBase.PriceDatabaseActivity;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lue on 31-10-2017.
 */

public class PriceAdapter extends ArrayAdapter<Price> {

    private Activity activity;
    String cardid,cardtype,cardprice,AgentId,UId,Update_cardPrice,Update_cardType,Id;
    String id;
    SharedPreferences SM1;
//    public ArrayAdapter<Price> adapter;
//    public List<Price> priceList=new ArrayList<Price>();





    public PriceAdapter(Activity activity, int resource, List<Price> books) {
        super(activity, resource, books);
        this.activity = activity;
//        this.priceList=books;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        // If holder not exist then locate all view from UI file.
        if (convertView == null) {
            // inflate UI from XML file
            convertView = inflater.inflate(R.layout.item_lists, parent, false);
            // get all UI view
            holder = new ViewHolder(convertView);
            // set tag for holder
            convertView.setTag(holder);
        } else {
            // if holder created, get tag from view
            holder = (ViewHolder) convertView.getTag();
        }

        final Price price = getItem(position);
        holder.id.setText(price.getId());
        holder.cardid.setText(price.getCardId());
        holder.cardtype.setText(price.getCardType());
//        Price rdv = priceList.get(position);
//        holder.edit.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                Log.i("Edit Button Clicked", "**********");
//                Toast.makeText(activity, "Edit button Clicked",
//                        Toast.LENGTH_LONG).show();
//
//                 id=price.getId().toString();
//                System.out.println("======id========="+id);
//                SenId(id);
////                Dupdate_show();
//
//
//
////                UpdateFragment dialogFragment = new UpdateFragment();
////                Bundle bundle = new Bundle();
////                bundle.putString("id", id);
////
////                dialogFragment.setArguments(bundle);
////                FragmentManager fm = getFragmentManager();
////                dialogFragment.show(fm, "Sample Fragment");
//            }
//
//        });
//        holder.delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String id=price.getId().toString();
//                System.out.println("======id========="+id);
//                SenId(id);
//
//
//            }
//        });


//        Read more: http://www.androidhub4you.com/2013/02/muftitouch-listview-multi-click.html#ixzz4x4bMhqGh

//        String i = img+detail.getInImage();


        return convertView;
    }

    private static class ViewHolder {
        private TextView id;
        private TextView cardid;
        private TextView cardtype;
        private ImageView edit;
        private ImageView delete;


        public ViewHolder(View v) {
            id=(TextView)v.findViewById(R.id.id);
            cardid = (TextView) v.findViewById(R.id.cardprice);
            cardtype = (TextView) v.findViewById(R.id.cardtype);
            edit=(ImageView)v.findViewById(R.id.edit);
            delete=(ImageView)v.findViewById(R.id.delete);


        }
    }

//    public void  SenId(final String UId
//
//
//
//    ){
//        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
//
//            protected void onPreExecute() {
//                super.onPreExecute();
//
//            }
//            @Override
//            protected String doInBackground(String... params) {
//                String return_text="";
//                JSONObject jsonObject=new JSONObject();
//                try {
//
//                    jsonObject.accumulate("update_id",UId);
//
//
//
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//                try {
//                    HttpClient httpClient = new DefaultHttpClient();
//                    SharedPreferences Bu = activity.getSharedPreferences("Base", Context.MODE_PRIVATE);
//                    String BaseUrl = Bu.getString("baseurl", "");
//                    HttpPost httpPost = new HttpPost(BaseUrl+"/price_update_id");
//                    StringEntity httpiEntity=new StringEntity(jsonObject.toString());
//                    httpPost.setEntity(httpiEntity);
//                    org.apache.http.HttpResponse response = httpClient.execute(httpPost);
//                    return_text= HTTPResponse.readResponse(response);
//                    return return_text;
//                } catch (ClientProtocolException e) {
//
//                } catch (IOException e) {
//                }
//                return return_text;
//            }
//            @Override
//            protected void onPostExecute(String result) {
//                super.onPostExecute(result);
//
//                try {
//                    JSONObject jsonObject=new JSONObject(result);
//                    if (jsonObject.getString("error").equalsIgnoreCase("false")) {
//
////                        String  message = jsonObject.getString("message");
//                        JSONArray carddetail=jsonObject.getJSONArray("message");
//                        for(int i=0;i<carddetail.length();i++)
//                        {
//                            JSONObject c=carddetail.getJSONObject(i);
//                            cardid=c.getString("id");
//                            cardtype=c.getString("card_type");
//                            cardprice=c.getString("card_price");
//
////                            Price price=new Price();
////                            price.setId(cardid);
////                            price.setCardId(cardtype);
////                            price.setCardType(cardprice);
////                            priceList.add(price);
//
//                        }
//
////                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
//
//                    }
//
//                    else if (jsonObject.getString("error").equalsIgnoreCase("true")) {
//                        String  message = jsonObject.getString("message");
//                        if (message != null) {
//                            {
//                                System.out.println("===========================error");
//                                Toast.makeText(activity,message, Toast.LENGTH_LONG).show();
//                            }
//                        }
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        try {
//            SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
//            sendPostReqAsyncTask.execute();
//        }catch (Exception e){}
//    }
//    //============================================Update data================================
//    public void  Update(final String AgentId,
//                        final String UId,
//                        final String cardtype,
//                        final String cardprice
//
//
//
//    ){
//        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
//
//            protected void onPreExecute() {
//                super.onPreExecute();
//
//            }
//            @Override
//            protected String doInBackground(String... params) {
//                String return_text="";
//                JSONObject jsonObject=new JSONObject();
//                try {
//
//                    jsonObject.accumulate("admin_id",AgentId);
//                    jsonObject.accumulate("update_id",UId);
//                    jsonObject.accumulate("card_type",cardtype);
//                    jsonObject.accumulate("card_price",cardprice);
//
//
//
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//                try {
//                    HttpClient httpClient = new DefaultHttpClient();
//                    SharedPreferences Bu = activity.getSharedPreferences("Base", Context.MODE_PRIVATE);
//                    String BaseUrl = Bu.getString("baseurl", "");
//                    HttpPost httpPost = new HttpPost(BaseUrl+"/price_update_id");
//                    StringEntity httpiEntity=new StringEntity(jsonObject.toString());
//                    httpPost.setEntity(httpiEntity);
//                    org.apache.http.HttpResponse response = httpClient.execute(httpPost);
//                    return_text= HTTPResponse.readResponse(response);
//                    return return_text;
//                } catch (ClientProtocolException e) {
//
//                } catch (IOException e) {
//                }
//                return return_text;
//            }
//            @Override
//            protected void onPostExecute(String result) {
//                super.onPostExecute(result);
//
//                try {
//                    JSONObject jsonObject=new JSONObject(result);
//                    if (jsonObject.getString("error").equalsIgnoreCase("false")) {
//                        String message = jsonObject.getString("message");
//                        Toast.makeText(activity,message,Toast.LENGTH_SHORT).show();
//
////                        String  message = jsonObject.getString("message");
////                        JSONArray carddetail=jsonObject.getJSONArray("message");
////                        for(int i=0;i<carddetail.length();i++)
////                        {
////                            JSONObject c=carddetail.getJSONObject(i);
////                            cardid=c.getString("id");
////                            cardtype=c.getString("card_type");
////                            cardprice=c.getString("card_price");
////
//////                            Price price=new Price();
//////                            price.setId(cardid);
//////                            price.setCardId(cardtype);
//////                            price.setCardType(cardprice);
//////                            priceList.add(price);
////
////                        }
//
////                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
//
//                    }
//
//                    else if (jsonObject.getString("error").equalsIgnoreCase("true")) {
//                        String  message = jsonObject.getString("message");
//                        if (message != null) {
//                            {
//                                System.out.println("===========================error");
//                                Toast.makeText(activity,message, Toast.LENGTH_LONG).show();
//                            }
//                        }
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        try {
//            SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
//            sendPostReqAsyncTask.execute();
//        }catch (Exception e){}
//    }
//    public void Dupdate_show()
//    {
//
//
//        LayoutInflater li = LayoutInflater.from(activity);
//        View promptsView = li.inflate(R.layout.fragment_update, null);
//        final EditText update_cardType = (EditText) promptsView.findViewById(R.id.update_cardtype);
//        update_cardType.setText(cardtype);
//        final EditText update_cardPrice=(EditText)promptsView.findViewById(R.id.update_card_price);
//        update_cardPrice.setText(cardprice);
//
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
//                activity);
//
//        // set prompts.xml to alertdialog builder
//        alertDialogBuilder.setView(promptsView);
//
//
//        // set dialog message
//        alertDialogBuilder
//                .setCancelable(false)
//                .setPositiveButton("OK",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog,
//                                                int id) {
//                                // get user input and set it to result
//                                // edit text
////                                result.setText(userInput.getText());
//                                SM1 =activity.getSharedPreferences("Myid", Context.MODE_PRIVATE);
//                                Id=SM1.getString("id","");
//                                Update_cardType= update_cardType.getText().toString();
//                                System.out.println("=======Update_cardType==========="+Update_cardType);
//                                Update_cardPrice=update_cardPrice.getText().toString();
//                                System.out.println("=======Update_cardPrice==========="+Update_cardPrice);
//                                Update(Id,UId,Update_cardType,Update_cardPrice);
//
//                                Toast.makeText(activity,"Card Type And Card Price Is Updated",Toast.LENGTH_SHORT).show();
//                                Intent intent=new Intent(activity, PriceDatabaseActivity.class);
//                                activity.startActivity(intent);
//
//
//                            }
//                        })
//                .setNegativeButton("Cancel",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog,
//                                                int id) {
//                                dialog.cancel();
//                            }
//                        });
//
//        // create alert dialog
//        AlertDialog alertDialog = alertDialogBuilder.create();
//
//        // show it
//        alertDialog.show();
//    }

}
