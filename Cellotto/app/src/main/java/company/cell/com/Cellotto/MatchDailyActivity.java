package company.cell.com.Cellotto;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import company.cell.com.Cellotto.Custom.Daily;
import company.cell.com.Cellotto.Custom.ListViewAdapter;
import company.cell.com.Cellotto.Custom.NetworkUtil;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MatchDailyActivity extends AppCompatActivity {
    ListView listView,selflistView;
    Button daily,self,Mdexit;
    TextView Plays_no,winning_cat,total_matches,total_win,winning_no;
    RelativeLayout dailytLayout, selfLayout;
    TextView Totoal_spend;
    String   Win_cat,Matches,Earn,Winning_number;
    String no,total_no;
    String TotalPlays,SumPlays, DailyPlays;
    private AdView mAdView;

    List<Daily> dailList;
    ProgressBar DailyAmount,DailyPlay,DailyWinResult,Win_no;
//    String Id;

//    List<String> heroList;
    private static final String JSON_URL = "http://www.cellotto.win/api/public/dailyplaysList";
    private static final String JSON_WIN_URL = "http://www.cellotto.win/api/public/dailyplaysResult";

    private ArrayAdapter<String> listAdapter ;
    public  static final String ID = "message";
    public static final String MyPrefrence = "MyPrefrence1";

    SharedPreferences sharedpreferences;
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_daily);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setLogo(R.drawable.cellotto_small);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        listView = (ListView) findViewById(R.id.scrolllist);
        dailList = new ArrayList<>();
        Plays_no=(TextView)findViewById(R.id.plays_no);
        daily=(Button)findViewById(R.id.btn_daily) ;
        self=(Button)findViewById(R.id.btn_self);
        Mdexit=(Button)findViewById(R.id.application);
        Totoal_spend=(TextView)findViewById(R.id.spend_no);
        dailytLayout=(RelativeLayout)findViewById(R.id.buttonlayout);
        selfLayout=(RelativeLayout)findViewById(R.id.selfbuttonlayout);
        selflistView = (ListView) findViewById(R.id.selfscrolllist);
        winning_cat=(TextView)findViewById(R.id.winning_categories_digit);
        total_matches=(TextView)findViewById(R.id.total_match_digit);
        total_win=(TextView)findViewById(R.id.winning_digit);
        winning_no=(TextView)findViewById(R.id.showresult);
        DailyAmount=(ProgressBar)findViewById(R.id.dailyamountProgressBar);
        DailyPlay=(ProgressBar)findViewById(R.id.DailyplayProgressBar) ;
        DailyWinResult=(ProgressBar)findViewById(R.id.resultProgressBar);
        Win_no=(ProgressBar)findViewById(R.id.win_noProgressBar);
        String[] sports = getResources().getStringArray(R.array.play);
        //Row layout defined by Android: android.R.layout.simple_list_item_1
//        listView.setAdapter(new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, sports));
//        selflistView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,sports));

//        SendId();

        loadHeroList();
        loadwinList();

        onReceive(this);
//        try{
//
//            sharedpreferences =getSharedPreferences(MyPrefrence,Context.MODE_PRIVATE);
//            Id=sharedpreferences.getString(ID,"");
//            System.out.println("=============Id daily_plays==================="+Id);
//
//
//
//        }catch (Exception e){
//
//        }

//        Bundle bundle = null;
//        try{
////            bundle = this.getIntent().getExtras();
////            s = bundle.getString("counter").toString();//this is for String
////            Plays_no.setText(s);
////            int t= Integer.parseInt((s));
////            int total=(t)*100;
////            int total=(t)*100;
////            Totoal_spend.setText(stotal);
//            //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
//            //String s = prefs.getString("counter",null);
//            sharedpreferences =getSharedPreferences(MyPref,Context.MODE_PRIVATE);
//            s=sharedpreferences.getString(COUNTER,"");
//
//            System.out.println("===========si=================="+s);
//
//            Plays_no.setText(s);
//            int t= Integer.parseInt(s);
//            int stotal=(t)*100;
//            System.out.println("==============stotal================"+stotal);
//            Totoal_spend.setText(String.valueOf(stotal));
//            // =============================custom listview=================================
//
//
//
//
//
//        }catch (Exception e){
//
//        }


        daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dailytLayout.setVisibility(View.VISIBLE);
                selfLayout.setVisibility(View.INVISIBLE);
                daily.setBackgroundDrawable(getResources().getDrawable(R.drawable.clickedbutton));
                self.setBackgroundDrawable(getResources().getDrawable(R.drawable.square_button));

            }
        });
        self.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dailytLayout.setVisibility(View.INVISIBLE);
                selfLayout.setVisibility(View.VISIBLE);
                self.setBackgroundDrawable(getResources().getDrawable(R.drawable.clickedbutton));
                daily.setBackgroundDrawable(getResources().getDrawable(R.drawable.square_button));

            }
        });
        Mdexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences("MyPrefrence1", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(MatchDailyActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });}

//    public void SendId() {
//
//
//
//        final String SESSION_ID="id";
//
//
//
//        String REGISTER_URL = "http://cellotto.win/api/public/dailyplaysList";
//        String url = null;
//        URL sourceUrl = null;
//        REGISTER_URL = REGISTER_URL.replaceAll(" ", "%20");
//        try {
//            sourceUrl = new URL(REGISTER_URL);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        url = sourceUrl.toString();
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        try {
//                            JSONObject jsonresponse = new JSONObject(response);
//
//
//                            String error = jsonresponse.getString("errors");
//                            if (error.equals("true")) {
//                                String message = jsonresponse.getString("message");
//                                Toast.makeText(MatchDailyActivity.this, message, Toast.LENGTH_LONG).show();
//
//
////                                Intent i = new Intent(SignInActivity.this, SignInActivity.class);
////                                startActivity(i);
////                              if (message == "Invalid Credentials") {
////
////
////                                }
//
//                            } else if (error.equals("false")){
//                                System.out.println("=============response=====================" + response);
//
////                                AlertDialog.Builder builder = new AlertDialog.Builder(SignInActivity.this);
////                                builder.setMessage(message)
////                                        .setNegativeButton("ok", null)
////                                        .create()
////                                        .show();
////                                Toast.makeText(SignInActivity.this, message, Toast.LENGTH_LONG).show();
//
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
////
//                    }
//                },
//                new Response.ErrorListener() {
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
////
//
//                    }
//                }) {
////            @Override
////            public String getBodyContentType() {
////                return "application/json";
////            }
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                //Adding parameters to request
//
//                params.put(SESSION_ID, Id);
//
//
//                return params;
//
//            }
//
//        };
//        com.android.volley.RequestQueue requestQueue = Volley.newRequestQueue(MatchDailyActivity.this);
//        requestQueue.add(stringRequest);
//    }


    private void loadHeroList() {
        sharedpreferences =getSharedPreferences(MyPrefrence,Context.MODE_PRIVATE);
         final   String  Id=sharedpreferences.getString(ID,"");
        //getting the progressbar
//        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        final String SESSION_ID="id";
        //making the progressbar visible
//        progressBar.setVisibility(View.VISIBLE);

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion

                        try {

                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);
                            String error = obj.getString("errors");
                            if (error.equals("true")) {

                                DailyPlay.setVisibility(View.VISIBLE);
                                DailyAmount.setVisibility(View.VISIBLE);
                            } else if (error.equals("false")){

                                DailyPlay.setVisibility(View.INVISIBLE);
                                DailyAmount.setVisibility(View.INVISIBLE);
                            }
//                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                            System.out.println("==============response========================"+response);
                            //we have the array named hero inside the object
                            //so here we are getting that json array
                            JSONArray dailyArray = obj.getJSONArray("message");

                            //now looping through all the elements of the json array
                            for (int i = 0; i < dailyArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject dailyObject = dailyArray.getJSONObject(i);

                                //creating a hero object and giving them the values from json object
                                 Daily daily = new Daily(dailyObject.getString("daily_draw_plays"));

                                dailList.add(daily);
                                System.out.println("=============DailyPlays_parse================"+dailList);

                            }
                            JSONArray playArray = obj.getJSONArray("total_play");
                            for (int i = 0; i < playArray.length(); i++) {
                                JSONObject playObject = playArray.getJSONObject(i);
                                TotalPlays = new String(playObject.getString("total_count")).toString();
                                System.out.println("=============TotalPlays_parse================"+TotalPlays);
                            }
                            JSONArray sumArray = obj.getJSONArray("daily_draw_sum");
                            for (int i = 0; i < sumArray.length(); i++) {
                                JSONObject sumObject = sumArray.getJSONObject(i);
                               SumPlays = new String(sumObject.getString("total_spend"));
                                System.out.println("=============SumPlays_parse================"+SumPlays);
                            }

                            //creating custom adapter object
                            ListViewAdapter adapter = new ListViewAdapter(dailList, getApplicationContext());

//                            adding the adapter to listview
                            listView.setAdapter(adapter);
                            Plays_no.setText(TotalPlays);
                            Totoal_spend.setText(SumPlays);
//

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
//            @Override
//            public String getBodyContentType() {
//                return "application/json";
//            }


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                //Adding parameters to request

                params.put(SESSION_ID, Id);


                return params;

            }

        };
//        CustomList customList = new CustomList(this, DailyPlays);
//        listView.setAdapter(customList);



        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }
    private void loadwinList() {
        sharedpreferences =getSharedPreferences(MyPrefrence,Context.MODE_PRIVATE);
     final String  Id=sharedpreferences.getString(ID,"");
        //getting the progressbar
//        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        final String SESSION_ID="id";
        //making the progressbar visible
//        progressBar.setVisibility(View.VISIBLE);

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_WIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion




                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);
                            String error = obj.getString("errors");
                            if (error.equals("true")) {

                                DailyWinResult.setVisibility(View.VISIBLE);
                                Win_no.setVisibility(View.VISIBLE);
                            } else if (error.equals("false")){

                                DailyWinResult.setVisibility(View.INVISIBLE);
                                Win_no.setVisibility(View.INVISIBLE);
                            }
//                            Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                            //we have the array named hero inside the object
                            //so here we are getting that json array
                            JSONArray dailyArray = obj.getJSONArray("message");

                            //now looping through all the elements of the json array
                            for (int i = 0; i < dailyArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject winObject = dailyArray.getJSONObject(i);

                                //creating a hero object and giving them the values from json object
                                    Win_cat = winObject.getString("daily_winning_category");
                                    Matches=winObject.getString("daily_total_matches");
                                    Earn=winObject.getString("daily_total_money_earn");




                            }
                            JSONArray Win_noArray = obj.getJSONArray("winning_number");
                            for (int i = 0; i < Win_noArray.length(); i++) {
                                JSONObject Win_noObject = Win_noArray.getJSONObject(i);
                               Winning_number = new String(Win_noObject.getString("winning_number")).toString();

                            }


                            //creating custom adapter object


//                            adding the adapter to listview

                                 winning_no.setText("WINNING NUMBERS:                  "+Winning_number);
                                 winning_no.setTextColor(Color.parseColor("#95362b"));
                                 winning_cat.setText(Win_cat);
                                total_matches.setText(Matches);
                                total_win.setText(Earn);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
//            @Override
//            public String getBodyContentType() {
//                return "application/json";
//            }


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                //Adding parameters to request

                params.put(SESSION_ID, Id);


                return params;

            }

        };
//        CustomList customList = new CustomList(this, DailyPlays);
//        listView.setAdapter(customList);



        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }








    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent iBack = new Intent(MatchDailyActivity.this, PlayActivity.class);
        startActivity(iBack);
        finish();
    }
    public void onReceive(Context context) {
        String status = NetworkUtil.getConnectivityStatusString(context);
        if ((status == "Not connected to Internet")) {
            Toast.makeText(context, status, Toast.LENGTH_SHORT).show();
        }
    }

    }
