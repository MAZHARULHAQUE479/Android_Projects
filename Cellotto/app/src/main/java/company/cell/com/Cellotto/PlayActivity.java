package company.cell.com.Cellotto;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import company.cell.com.Cellotto.Custom.NetworkUtil;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PlayActivity extends AppCompatActivity {
//    GridView grid;
//    int[] imageId = {
//            R.drawable.circle_btn_orange,
//            R.drawable.circle_btn_green,
//            R.drawable.circle_btn_skyblue,
//            R.drawable.circle_btn_orange,
//            R.drawable.circle_btn_green,
//            R.drawable.circle_btn_orange,
//            R.drawable.circle_btn_darkblue,
//            R.drawable.circle_btn_orange,
//            R.drawable.circle_btn_darkblue,
//            R.drawable.circle_btn_green,
//            R.drawable.circle_btn_orange,
//            R.drawable.circle_btn_skyblue,
//            R.drawable.circle_btn_green,
//            R.drawable.circle_btn_orange,
//            R.drawable.circle_btn_skyblue,
//            R.drawable.circle_btn_orange,
//            R.drawable.circle_btn_darkblue,
//            R.drawable.circle_btn_orange,
//            R.drawable.circle_btn_green,
//            R.drawable.circle_btn_orange,
//            R.drawable.circle_btn_skyblue,
//            R.drawable.circle_btn_orange,
//            R.drawable.circle_btn_skyblue,
//            R.drawable.circle_btn_orange,
//            R.drawable.circle_btn_skyblue,
//            R.drawable.circle_btn_orange,
//            R.drawable.circle_btn_darkblue,
//            R.drawable.circle_btn_orange,
//            R.drawable.circle_btn_green,
//            R.drawable.circle_btn_orange,
//            R.drawable.circle_btn_darkblue,
//            R.drawable.circle_btn_orange,
//            R.drawable.circle_btn_green,
//            R.drawable.circle_btn_orange,
//            R.drawable.circle_btn_orange,
//            R.drawable.circle_btn_orange,
//            R.drawable.circle_btn_darkblue,
//            R.drawable.circle_btn_orange,
//            R.drawable.circle_btn_green,
//            R.drawable.circle_btn_orange,
//            R.drawable.circle_btn_skyblue,
//            R.drawable.circle_btn_orange,
//            R.drawable.circle_btn_skyblue,
//            R.drawable.circle_btn_orange,
//            R.drawable.circle_btn_skyblue,
//            R.drawable.circle_btn_orange,
//            R.drawable.circle_btn_green,
//            R.drawable.circle_btn_orange,
//            R.drawable.circle_btn_darkblue
//
//    };
//    String [] web = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
//            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40",
//            "41", "42", "43", "44", "45", "46", "47", "48", "49"};
    String Daily_Draw;
    public static final String ID = "message";
    public static final String MyPrefrence = "MyPrefrence1";
    String Id;
    SharedPreferences sharedpreferences;
    StringBuilder StoreText;
    TextView Doption, Dclear, Dexit;
    RelativeLayout relativeLayout;
    TextView selfDraw, history, setting, help, Txt1, Txt2, Txt3, Txt4, Txt5, Txt6;
    Button Play, CheckPlay;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12, btn13, btn14, btn15, btn16, btn17, btn18, btn19,
            btn20, btn21, btn22, btn23, btn24, btn25, btn26, btn27, btn28, btn29, btn30, btn31, btn32, btn33, btn34, btn35, btn36,
            btn37, btn38, btn39, btn40, btn41, btn42, btn43, btn44, btn45, btn46, btn47, btn48, btn49;
    public   int Rnumber1,Rnumber2,Rnumber3,Rnumber4,Rnumber5;
    private AdView mAdView;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setLogo(R.drawable.cellotto_small);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
//        CustomGrid adapter = new CustomGrid(PlayActivity.this, imageId, web);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        relativeLayout = (RelativeLayout) findViewById(R.id.dialog);
        selfDraw = (TextView) findViewById(R.id.dialog_selfdraw);
        history = (TextView) findViewById(R.id.dialog_history);
        setting = (TextView) findViewById(R.id.dialog_setting);
//        text_field = (Button) findViewById(R.id.text_field);
        Txt1 = (TextView) findViewById(R.id.txt1);
        Txt2 = (TextView) findViewById(R.id.txt2);
        Txt3 = (TextView) findViewById(R.id.txt3);
        Txt4 = (TextView) findViewById(R.id.txt4);
        Txt5 = (TextView) findViewById(R.id.txt5);
        Txt6 = (TextView) findViewById(R.id.txt6);
        help = (TextView) findViewById(R.id.help);
        CheckPlay = (Button) findViewById(R.id.checkplay);
        Doption = (TextView) findViewById(R.id.option);
        Dclear = (TextView) findViewById(R.id.clear);
        Dexit = (TextView) findViewById(R.id.exit_play);
        Play = (Button) findViewById(R.id.play);
        btn1 = (Button) findViewById(R.id.one);
        btn2 = (Button) findViewById(R.id.two);
        btn3 = (Button) findViewById(R.id.three);
        btn4 = (Button) findViewById(R.id.four);
        btn5 = (Button) findViewById(R.id.five);
        btn6 = (Button) findViewById(R.id.six);
        btn7 = (Button) findViewById(R.id.seven);
        btn8 = (Button) findViewById(R.id.eight);
        btn9 = (Button) findViewById(R.id.nine);
        btn10 = (Button) findViewById(R.id.ten);
        btn11 = (Button) findViewById(R.id.eleven);
        btn12 = (Button) findViewById(R.id.twelve);
        btn13 = (Button) findViewById(R.id.thirteen);
        btn14 = (Button) findViewById(R.id.fourteen);
        btn15 = (Button) findViewById(R.id.fifteen);
        btn16 = (Button) findViewById(R.id.sixteen);
        btn17 = (Button) findViewById(R.id.seventeen);
        btn18 = (Button) findViewById(R.id.eighteen);
        btn19 = (Button) findViewById(R.id.nineteen);
        btn20 = (Button) findViewById(R.id.twinty);
        btn21 = (Button) findViewById(R.id.twintyone);
        btn22 = (Button) findViewById(R.id.twintytwo);
        btn23 = (Button) findViewById(R.id.twintythree);
        btn24 = (Button) findViewById(R.id.twintyfour);
        btn25 = (Button) findViewById(R.id.twintyfive);
        btn26 = (Button) findViewById(R.id.twintysix);
        btn27 = (Button) findViewById(R.id.twintyseven);
        btn28 = (Button) findViewById(R.id.twintyeight);
        btn29 = (Button) findViewById(R.id.twintynine);
        btn30 = (Button) findViewById(R.id.thirty);
        btn31 = (Button) findViewById(R.id.thirtyone);
        btn32 = (Button) findViewById(R.id.thirtytwo);
        btn33 = (Button) findViewById(R.id.thirtythree);
        btn34 = (Button) findViewById(R.id.thirtyfour);
        btn35 = (Button) findViewById(R.id.thirtyfive);
        btn36 = (Button) findViewById(R.id.thirtysix);
        btn37 = (Button) findViewById(R.id.thirtyseven);
        btn38 = (Button) findViewById(R.id.thirtyeight);
        btn39 = (Button) findViewById(R.id.thirtytnine);
        btn40 = (Button) findViewById(R.id.fourty);
        btn41 = (Button) findViewById(R.id.fourtyone);
        btn42 = (Button) findViewById(R.id.fourtytwo);
        btn43 = (Button) findViewById(R.id.fourtythree);
        btn44 = (Button) findViewById(R.id.fourtyfour);
        btn45 = (Button) findViewById(R.id.fourtyfive);
        btn46 = (Button) findViewById(R.id.fourtysix);
        btn47 = (Button) findViewById(R.id.fourtyseven);
        btn48 = (Button) findViewById(R.id.fourtyeight);
        btn49 = (Button) findViewById(R.id.fourtynine);


       onReceive(this);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

//        grid.setAdapter(adapter);
        Doption.setText("O" + "\n" + "P" + "\n" + "T" + "\n" + "I" + "\n" + "O" + "\n" + "N");
        Dclear.setText("C" + "\n" + "L" + "\n" + "E" + "\n" + "A" + "\n" + "R");
        Dexit.setText("E" + "\n" + "X" + "\n" + "I" + "\n" + "T");
        final String[] str = new String[5];

        StringBuilder sb = new StringBuilder();


//        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                Toast.makeText(PlayActivity.this, "You select " + web[position], Toast.LENGTH_SHORT).show();
//
//                text_field.append("\t"+web[position]);
////                         str[1]=web[position];
//                System.out.println("=============2============="+str[1]);
//
//
//            }
//        });
//

        Dexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                switch (view.getId()) {
//                    case R.id.exit:
                        SharedPreferences pref = getSharedPreferences("MyPrefrence1", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.clear();
                        editor.commit();
                        Intent intent = new Intent(PlayActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
//                        break;
                }
//            }
        });
        Doption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (relativeLayout.getVisibility() == View.VISIBLE)
                    relativeLayout.setVisibility(View.INVISIBLE);
                else
                    relativeLayout.setVisibility(View.VISIBLE);

            }
        });
        selfDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SelfDraw = new Intent(PlayActivity.this, SelfDrawActivity.class);
                startActivity(SelfDraw);
                System.out.println("==============SelfDrawActivity================" + SelfDraw);
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent Setting = new Intent(company.cell.com.Cellotto.PlayActivity.this, company.cell.com.Cellotto.MatchDailyActivity.class);
                    startActivity(Setting);
                }catch (Exception e){}

            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent Setting = new Intent(company.cell.com.cellotto.PlayActivity.this, company.cell.com.cellotto.MatchDailyActivity.class);
//                startActivity(Setting);

            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Help = new Intent(PlayActivity.this, Firebase.class);
                startActivity(Help);

            }
        });
        Dclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Txt1.setText("");
                Txt2.setText("");
                Txt3.setText("");
                Txt4.setText("");
                Txt5.setText("");
                Txt6.setText("");
                btn1.setEnabled(true);
                btn2.setEnabled(true);
                btn3.setEnabled(true);
                btn4.setEnabled(true);
                btn5.setEnabled(true);
                btn6.setEnabled(true);
                btn7.setEnabled(true);
                btn8.setEnabled(true);
                btn9.setEnabled(true);
                btn10.setEnabled(true);
                btn11.setEnabled(true);
                btn12.setEnabled(true);
                btn13.setEnabled(true);
                btn14.setEnabled(true);
                btn15.setEnabled(true);
                btn16.setEnabled(true);
                btn17.setEnabled(true);
                btn18.setEnabled(true);
                btn19.setEnabled(true);
                btn20.setEnabled(true);
                btn21.setEnabled(true);
                btn22.setEnabled(true);
                btn23.setEnabled(true);
                btn24.setEnabled(true);
                btn25.setEnabled(true);
                btn26.setEnabled(true);
                btn27.setEnabled(true);
                btn28.setEnabled(true);
                btn29.setEnabled(true);
                btn30.setEnabled(true);
                btn31.setEnabled(true);
                btn32.setEnabled(true);
                btn33.setEnabled(true);
                btn34.setEnabled(true);
                btn35.setEnabled(true);
                btn36.setEnabled(true);
                btn37.setEnabled(true);
                btn38.setEnabled(true);
                btn39.setEnabled(true);
                btn40.setEnabled(true);
                btn41.setEnabled(true);
                btn42.setEnabled(true);
                btn43.setEnabled(true);
                btn44.setEnabled(true);
                btn45.setEnabled(true);
                btn46.setEnabled(true);
                btn47.setEnabled(true);
                btn48.setEnabled(true);
                btn49.setEnabled(true);


            }
        });


        //======================buttons========================


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 1" , Toast.LENGTH_SHORT).show();
//                   String s1=  btn1.getText().toString();
//                    text_field.setText(s1);
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn1.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn1.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn1.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn1.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn1.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn1.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn1.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 2" , Toast.LENGTH_SHORT).show();

//                    String s2= btn2.getText().toString();
//                    text_field.setText(s2);
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn2.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn2.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn2.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn2.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn2.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn2.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn2.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 3" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn3.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn3.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn3.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn3.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn3.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn3.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn3.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn3.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });


        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 4" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn4.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn4.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn4.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn4.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn4.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn4.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn4.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn4.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 5" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn5.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn5.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn5.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn5.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn5.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn5.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn5.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn6.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 6" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn6.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn6.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn6.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn6.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn6.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn6.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn6.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn7.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 7" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn7.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn7.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn7.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn7.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn7.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn7.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn7.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn8.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 8" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn8.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn8.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn8.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn8.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn8.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn8.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn8.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn9.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 9" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn9.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn9.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn9.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn9.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn9.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn9.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn9.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn9.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 10" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn10.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn10.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn10.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn10.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn10.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn10.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn10.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn10.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 11" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn11.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn11.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn11.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn11.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn11.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn11.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn11.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn11.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 12", Toast.LENGTH_SHORT).show();

                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn12.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn12.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn12.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn12.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn12.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn12.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn12.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }
            }

        });
        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 13" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn13.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn13.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn13.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn13.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn13.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn13.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn13.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn13.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 14" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn14.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn14.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn14.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn14.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn14.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn14.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn14.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn14.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 15" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn15.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn15.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn15.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn15.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn15.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn15.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn15.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn15.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 16" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn16.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn16.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn16.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn16.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn16.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn16.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn16.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn16.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 17" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn17.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn17.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn17.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn17.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn17.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn17.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn17.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn1.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 18" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn18.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn18.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn18.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn18.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn18.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn18.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn18.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn18.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 19" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn19.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn19.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn19.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn19.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn19.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn19.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn19.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn19.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 20" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn20.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn20.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn20.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn20.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn20.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn20.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn20.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn20.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 21" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn21.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn21.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn21.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn21.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn21.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn21.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn21.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn21.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 22" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn22.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn22.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn22.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn22.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn22.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn22.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn22.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn21.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 23" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn23.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn23.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn23.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn23.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn23.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn23.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn23.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn23.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 24" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn24.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn24.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn24.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn24.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn24.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn24.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn24.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn24.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 25" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn25.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn25.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn25.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn25.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn25.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn25.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn25.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn25.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 26" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn26.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn26.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn26.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn26.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn26.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn26.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn26.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn26.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 27" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn27.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn27.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn27.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn27.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn27.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn27.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn27.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn27.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 28" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn28.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn28.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn28.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn28.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn28.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn28.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn28.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn28.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 29" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn29.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn29.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn29.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn29.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn29.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn29.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn29.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn29.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 30" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn30.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn30.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn30.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn30.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn30.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn30.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn30.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn30.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 31" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn31.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn31.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn31.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn31.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn31.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn31.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn31.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn31.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 32" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn32.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn32.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn32.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn32.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn32.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn32.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn32.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn32.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 33" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn33.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn33.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn33.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn33.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn33.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn33.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn33.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn33.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 34" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn34.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn34.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn34.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn34.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn34.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn34.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn34.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn34.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 35" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn35.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn35.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn35.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn12.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn35.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn35.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn35.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn35.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 36" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn36.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn36.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn36.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn36.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn36.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn36.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn36.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn36.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 37" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn37.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn37.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn37.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn37.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn37.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn37.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn37.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn37.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn38.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 38" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn38.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn38.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn38.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn38.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn38.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn38.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn38.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn38.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn39.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 39" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn39.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn39.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn39.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn39.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn39.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn39.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn39.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn39.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 40" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn40.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn40.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn40.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn40.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn40.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn40.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn40.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn40.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 41" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn41.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn41.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn41.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn41.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn41.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn41.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn41.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn41.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 42" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn42.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn42.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn42.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn42.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn42.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn42.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn42.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn42.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 43" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn43.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn43.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn43.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn43.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn43.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn43.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn43.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn43.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 44" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn44.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn44.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn44.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn44.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn44.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn44.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn44.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn44.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 45" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn45.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn45.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn45.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn45.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn45.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn45.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn45.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn45.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn46.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 46" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn46.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn46.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn46.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn46.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn46.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn46.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn46.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn46.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn47.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 47" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn47.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn47.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn47.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn47.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn47.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn47.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn47.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn47.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn48.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 48" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn48.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn48.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn48.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn48.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn48.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn48.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn48.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn48.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn49.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(PlayActivity.this, "You select 49" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(btn49.getText());
                if (Txt1.getText().length() == 0) {
                    Txt1.setText(btn49.getText());
                } else if (Txt2.getText().length() == 0) {
                    Txt2.setText(btn49.getText());
                } else if (Txt3.getText().length() == 0) {
                    Txt3.setText(btn49.getText());
                } else if (Txt4.getText().length() == 0) {
                    Txt4.setText(btn49.getText());
                } else if (Txt5.getText().length() == 0) {
                    Txt5.setText(btn49.getText());
                } else if (Txt6.getText().length() == 0) {
                    Txt6.setText(btn49.getText());
                } else {
                    if ((Txt1.getText().length() != 0) && (Txt2.getText().length() != 0) && (Txt3.getText().length() != 0) && (Txt4.getText().length() != 0) && (Txt5.getText().length() != 0) && (Txt6.getText().length() != 0)) {
                        btn49.setEnabled(false);
                        Toast.makeText(PlayActivity.this, "Sorry Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });




        Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder sb = new StringBuilder();
                String s1 = Txt1.getText().toString();
                System.out.println("================1=========" + s1);
                String s2 = Txt2.getText().toString();
                String s3 = Txt3.getText().toString();
                String s4 = Txt4.getText().toString();
                String s5 = Txt5.getText().toString();
                String s6 = Txt6.getText().toString();
                StoreText = sb.append(s1).append("  ").append(s2).append("  ").append(s3).append("  ").append(s4).append("  ").append(s5).append("  ").append(s6);
                System.out.println("===============================" + StoreText);
                Toast.makeText(PlayActivity.this, "your selected number is:" + StoreText, Toast.LENGTH_SHORT).show();
                if ((Txt1.getText().length() == 0) || (Txt2.getText().length() == 0) || (Txt3.getText().length() == 0) || (Txt4.getText().length() == 0) || (Txt5.getText().length() == 0) || (Txt6.getText().length() == 0))
                {
                    Toast.makeText(PlayActivity.this, "Kindly Check Your No.", Toast.LENGTH_SHORT).show();

                }else
                {
                    getDataFromEditText();
                    Register();

                }
                Txt1.setText("");
                Txt2.setText("");
                Txt3.setText("");
                Txt4.setText("");
                Txt5.setText("");
                Txt6.setText("");
            }


            public void getDataFromEditText() {
                Daily_Draw = StoreText.toString().trim();
                Id.toString().trim();
            }


        });

        try{

            sharedpreferences =getSharedPreferences(MyPrefrence,Context.MODE_PRIVATE);
           Id=sharedpreferences.getString(ID,"");
            System.out.println("=============Id daily_plays==================="+Id);



        }catch (Exception e){

        }

        CheckPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getDataFromEditText();
//                Register();
//            }
//
//            public void getDataFromEditText() {
//                Daily_Draw = StoreText.toString().trim();
//                Id.toString().trim();
                Intent i=new Intent(PlayActivity.this,MatchDailyActivity.class);
                startActivity(i);
            }

        });
    }
    public void Register() {


       final String DEALY_DRAW_PLAYS = "daily_draw_plays";
        final String SESSION_ID="id";



        String REGISTER_URL = "http://www.cellotto.win/api/public/dailyplays";
        String url = null;
        URL sourceUrl = null;
        REGISTER_URL = REGISTER_URL.replaceAll(" ", "%20");
        try {
            sourceUrl = new URL(REGISTER_URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        url = sourceUrl.toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                           Log.d("jaba", Daily_Draw);
                        try {
                            JSONObject jsonresponse = new JSONObject(response);
                            String error = jsonresponse.getString("errors");
                            if (error.equals("true")) {
                                String message = jsonresponse.getString("message");
                                Toast.makeText(PlayActivity.this, message, Toast.LENGTH_LONG).show();
                                System.out.println("==================================" + message);

//                                Intent i = new Intent(SignInActivity.this, SignInActivity.class);
//                                startActivity(i);
//                              if (message == "Invalid Credentials") {
//
//
//                                }

                            } else if (error.equals("false")){
                                Toast.makeText(PlayActivity.this, "you have Drawn Sucessfully", Toast.LENGTH_LONG).show();
//                                Intent i = new Intent(PlayActivity.this, PlayActivity.class);
//                                startActivity(i);
//                                AlertDialog.Builder builder = new AlertDialog.Builder(SignInActivity.this);
//                                builder.setMessage(message)
//                                        .setNegativeButton("ok", null)
//                                        .create()
//                                        .show();
//                                Toast.makeText(SignInActivity.this, message, Toast.LENGTH_LONG).show();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

//                         Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("jaba123",  error.toString());
//                              Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

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
                params.put(DEALY_DRAW_PLAYS, Daily_Draw);

                return params;

            }

        };
        com.android.volley.RequestQueue requestQueue = Volley.newRequestQueue(PlayActivity.this);
        requestQueue.add(stringRequest);
    }

//    public void  Register(final String Daily_Draw
//                          ){
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
//                    jsonObject.accumulate("daily_draw_plays","sfffgvdf");
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    HttpClient httpClient = new DefaultHttpClient();
//                    HttpPost httpPost = new HttpPost("http://cellotto.win/api/public/dailyplays");
//                    httpPost.setHeader("Content-type", "application/json");
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
//                    if (jsonObject.getString("errors").equalsIgnoreCase("false")) {
//
//                       String message= jsonObject.getString("message");
//                        if (message != null) {
//                            Toast.makeText(PlayActivity.this, message, Toast.LENGTH_LONG).show();
//                            //Intent iSave = new Intent(ApplicationActivity.this, MainActivity.class);
//                           // startActivity(iSave);
//                            //finish();
//                        }
//                    }
//                    else if (jsonObject.getString("errors").equalsIgnoreCase("true")) {
//                      String  message = jsonObject.getString("message");
//                        if (message != null) {
//                            {
//                                System.out.println("===========================error");
//                                Toast.makeText(PlayActivity.this,message , Toast.LENGTH_LONG).show();
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





    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent iBack = new Intent(PlayActivity.this, MainActivity.class);
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








