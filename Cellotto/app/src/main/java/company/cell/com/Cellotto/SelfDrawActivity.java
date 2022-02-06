package company.cell.com.Cellotto;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import company.cell.com.Cellotto.Custom.NetworkUtil;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SelfDrawActivity extends AppCompatActivity {
//    GridView grid;

    Button Sbtn1, Sbtn2, Sbtn3, Sbtn4, Sbtn5, Sbtn6, Sbtn7, Sbtn8, Sbtn9, Sbtn10, Sbtn11, Sbtn12, Sbtn13, Sbtn14, Sbtn15, Sbtn16, Sbtn17, Sbtn18, Sbtn19,
            Sbtn20, Sbtn21, Sbtn22, Sbtn23, Sbtn24, Sbtn25, Sbtn26, Sbtn27, Sbtn28, Sbtn29, Sbtn30, Sbtn31, Sbtn32, Sbtn33, Sbtn34, Sbtn35, Sbtn36,
            Sbtn37, Sbtn38, Sbtn39, Sbtn40, Sbtn41, Sbtn42, Sbtn43, Sbtn44, Sbtn45, Sbtn46, Sbtn47, Sbtn48, Sbtn49;

    TextView option,counter,Stxt1, Stxt2, Stxt3, Stxt4, Stxt5, Stxt6
            , Sclear, Sback;
    Button Splay,Sdraw;
    int count;
    boolean clicked=false;
    String StoreText,StoreText1,StoreText2,StoreText3,StoreText4;
    private AdView mAdView;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_draw);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
//        getSupportActionBar().setDisSplayShowHomeEnabled(true);
//        getSupportActionBar().setLogo(R.Sdrawable.cellotto_small);
//        getSupportActionBar().setDisSplayUseLogoEnabled(true);
//        getSupportActionBar().setDisSplayShowTitleEnabled(false);
//        getSupportActionBar().setDisSplayOptions(ActionBar.DISSplay_SHOW_CUSTOM);
//        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
//        CustomGrid adapter = new CustomGrid(SelfDrawActivity.this, imageId, web);
//        grid=(GridView)findViewById(R.id.grid);
        StringBuilder sb = new StringBuilder();

        Sbtn1 = (Button) findViewById(R.id.one);
        Sbtn2 = (Button) findViewById(R.id.two);
        Sbtn3 = (Button) findViewById(R.id.three);
        Sbtn4 = (Button) findViewById(R.id.four);
        Sbtn5 = (Button) findViewById(R.id.five);
        Sbtn6 = (Button) findViewById(R.id.six);
        Sbtn7 = (Button) findViewById(R.id.seven);
        Sbtn8 = (Button) findViewById(R.id.eight);
        Sbtn9 = (Button) findViewById(R.id.nine);
        Sbtn10 = (Button) findViewById(R.id.ten);
        Sbtn11 = (Button) findViewById(R.id.eleven);
        Sbtn12 = (Button) findViewById(R.id.twelve);
        Sbtn13 = (Button) findViewById(R.id.thirteen);
        Sbtn14 = (Button) findViewById(R.id.fourteen);
        Sbtn15 = (Button) findViewById(R.id.fifteen);
        Sbtn16 = (Button) findViewById(R.id.sixteen);
        Sbtn17 = (Button) findViewById(R.id.seventeen);
        Sbtn18 = (Button) findViewById(R.id.eighteen);
        Sbtn19 = (Button) findViewById(R.id.nineteen);
        Sbtn20 = (Button) findViewById(R.id.twinty);
        Sbtn21 = (Button) findViewById(R.id.twintyone);
        Sbtn22 = (Button) findViewById(R.id.twintytwo);
        Sbtn23 = (Button) findViewById(R.id.twintythree);
        Sbtn24 = (Button) findViewById(R.id.twintyfour);
        Sbtn25 = (Button) findViewById(R.id.twintyfive);
        Sbtn26 = (Button) findViewById(R.id.twintysix);
        Sbtn27 = (Button) findViewById(R.id.twintyseven);
        Sbtn28 = (Button) findViewById(R.id.twintyeight);
        Sbtn29 = (Button) findViewById(R.id.twintynine);
        Sbtn30 = (Button) findViewById(R.id.thirty);
        Sbtn31 = (Button) findViewById(R.id.thirtyone);
        Sbtn32 = (Button) findViewById(R.id.thirtytwo);
        Sbtn33 = (Button) findViewById(R.id.thirtythree);
        Sbtn34 = (Button) findViewById(R.id.thirtyfour);
        Sbtn35 = (Button) findViewById(R.id.thirtyfive);
        Sbtn36 = (Button) findViewById(R.id.thirtysix);
        Sbtn37 = (Button) findViewById(R.id.thirtyseven);
        Sbtn38 = (Button) findViewById(R.id.thirtyeight);
        Sbtn39 = (Button) findViewById(R.id.thirtytnine);
        Sbtn40 = (Button) findViewById(R.id.fourty);
        Sbtn41 = (Button) findViewById(R.id.fourtyone);
        Sbtn42 = (Button) findViewById(R.id.fourtytwo);
        Sbtn43 = (Button) findViewById(R.id.fourtythree);
        Sbtn44 = (Button) findViewById(R.id.fourtyfour);
        Sbtn45 = (Button) findViewById(R.id.fourtyfive);
        Sbtn46 = (Button) findViewById(R.id.fourtysix);
        Sbtn47 = (Button) findViewById(R.id.fourtyseven);
        Sbtn48 = (Button) findViewById(R.id.fourtyeight);
        Sbtn49 = (Button) findViewById(R.id.fourtynine);

        option=(TextView)findViewById(R.id.option);
        Splay=(Button)findViewById(R.id.play_button);
        counter=(TextView)findViewById(R.id.counter);
        Sdraw=(Button)findViewById(R.id.draw_button);
        Sclear=(TextView)findViewById(R.id.sclear);
        Sback=(TextView)findViewById(R.id.sback);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Stxt1 = (TextView) findViewById(R.id.Stxt1);
        Stxt2 = (TextView) findViewById(R.id.Stxt2);
        Stxt3 = (TextView) findViewById(R.id.Stxt3);
        Stxt4 = (TextView) findViewById(R.id.Stxt4);
        Stxt5 = (TextView) findViewById(R.id.Stxt5);
        Stxt6 = (TextView) findViewById(R.id.Stxt6);

//        clear=(TextView)findViewById(R.id.clear);
//        exit=(TextView)findViewById(R.id.exit);
//        option.setText("O"+"\n"+"P"+"\n"+"T"+"\n"+"I"+"\n"+"O"+"\n"+"N");
//        option.setText("M"+"\n"+"U"+"\n"+"L"+"\n"+"T"+"\n"+"I"+"\n\n"+"P"+"\n"+"L"+"\n"+"A"+"\n"+"Y"+"\n"+"S");
//        clear.setBackgroundColor(Color.TRANSPARENT);
//        exit.setText("E"+"\n"+"X"+"\n"+"I"+"\n"+"T");
//        grid.setAdapter(adapter);
////        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////            @Override
////            public void onItemClick(AdapterView<?> parent, View view,
////                                    int position, long id) {
//                Toast.makeText(SelfDrawActivity.this, "You select " +web[+ position], Toast.LENGTH_SHORT).show();
//
//            }
//        });


        Sclear.setText("C" + "\n" + "L" + "\n" + "E" + "\n" + "A" + "\n" + "R");
        Sclear.setTextSize(12);
        Sback.setText("B" + "\n" + "A" + "\n" + "C" + "\n" + "K" );
        Sback.setTextSize(12);

        Splay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {

                    count++;

                }
              counter.setText(String.valueOf(count));
                StringBuilder sb = new StringBuilder();
                String s1 = Stxt1.getText().toString();
                String s2 = Stxt2.getText().toString();
                String s3 = Stxt3.getText().toString();
                String s4 = Stxt4.getText().toString();
                String s5 = Stxt5.getText().toString();
                String s6 = Stxt6.getText().toString();
                if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0) && count == 1) {
                    StoreText = sb.append(s1).append("\t").append(s2).append("\t").append(s3).append("\t").append(s4).append("\t").append(s5).append("\t").append(s6).toString();
                    System.out.println("==============StoreText1=================" + StoreText.toString());
                    Toast.makeText(SelfDrawActivity.this, "your selected number is:" + StoreText, Toast.LENGTH_SHORT).show();
                    Stxt1.setText("");
                    Stxt2.setText("");
                    Stxt3.setText("");
                    Stxt4.setText("");
                    Stxt5.setText("");
                    Stxt6.setText("");
                } else if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0) && count == 2) {
                    StoreText1 = sb.append(s1).append("\t").append(s2).append("\t").append(s3).append("\t").append(s4).append("\t").append(s5).append("\t").append(s6).toString();

                    System.out.println("==============StoreText2=================" + StoreText1.toString());
                    Toast.makeText(SelfDrawActivity.this, "your selected number is:" + StoreText1, Toast.LENGTH_SHORT).show();
                    Stxt1.setText("");
                    Stxt2.setText("");
                    Stxt3.setText("");
                    Stxt4.setText("");
                    Stxt5.setText("");
                    Stxt6.setText("");
                } else if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0) && count == 3) {
                    StoreText2 = sb.append(s1).append("\t").append(s2).append("\t").append(s3).append("\t").append(s4).append("\t").append(s5).append("\t").append(s6).toString();

                    System.out.println("==============StoreText3=================" + StoreText2.toString());
                    Toast.makeText(SelfDrawActivity.this, "your selected number is:" + StoreText2, Toast.LENGTH_SHORT).show();
                    Stxt1.setText("");
                    Stxt2.setText("");
                    Stxt3.setText("");
                    Stxt4.setText("");
                    Stxt5.setText("");
                    Stxt6.setText("");

                }else if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0) && count == 4) {
                    StoreText3 = sb.append(s1).append("\t").append(s2).append("\t").append(s3).append("\t").append(s4).append("\t").append(s5).append("\t").append(s6).toString();

                    System.out.println("==============StoreText4=================" + StoreText3.toString());
                    Toast.makeText(SelfDrawActivity.this, "your selected number is:" + StoreText2, Toast.LENGTH_SHORT).show();
                    Stxt1.setText("");
                    Stxt2.setText("");
                    Stxt3.setText("");
                    Stxt4.setText("");
                    Stxt5.setText("");
                    Stxt6.setText("");

                }else if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0) && count == 5) {
                    StoreText4 = sb.append(s1).append("\t").append(s2).append("\t").append(s3).append("\t").append(s4).append("\t").append(s5).append("\t").append(s6).toString();

                    System.out.println("==============StoreText5=================" + StoreText4.toString());
                    Toast.makeText(SelfDrawActivity.this, "your selected number is:" + StoreText2, Toast.LENGTH_SHORT).show();
                    Stxt1.setText("");
                    Stxt2.setText("");
                    Stxt3.setText("");
                    Stxt4.setText("");
                    Stxt5.setText("");
                    Stxt6.setText("");


                }
            }
        });

        Sclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Stxt1.setText("");
                Stxt2.setText("");
                Stxt3.setText("");
                Stxt4.setText("");
                Stxt5.setText("");
                Stxt6.setText("");


            }
        });

//        Intent intent=new Intent(SelfDrawActivity.this,MatchDailyActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putString( "counter", String.valueOf(count));
//        intent.putExtras(bundle);
//        startActivity(intent);
//        exit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                switch (view.getId()){
//                    case R.id.exit:
//                        Intent intent = new Intent(SelfDrawActivity.this, MainActivity.class);
//                        startActivity(intent);
//                        finish();
//                        break;
//                }
//            }
//        });

        Sbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 1" , Toast.LENGTH_SHORT).show();
//                   String s1=  Sbtn1.getText().toString();
//                    text_field.setText(s1);
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn1.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn1.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn1.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn1.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn1.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn1.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn1.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 2" , Toast.LENGTH_SHORT).show();

//                    String s2= Sbtn2.getText().toString();
//                    text_field.setText(s2);
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn2.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn2.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn2.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn2.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn2.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn2.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn2.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        Sbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 3" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn3.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn3.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn3.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn3.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn3.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn3.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn3.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn3.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });


        Sbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 4" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn4.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn4.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn4.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn4.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn4.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn4.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn4.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn4.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 5" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn5.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn5.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn5.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn5.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn5.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn5.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn5.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn6.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        Sbtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 6" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn6.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn6.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn6.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn6.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn6.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn6.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn6.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn7.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 7" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn7.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn7.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn7.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn7.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn7.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn7.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn7.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn8.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 8" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn8.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn8.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn8.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn8.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn8.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn8.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn8.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn9.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 9" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn9.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn9.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn9.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn9.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn9.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn9.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn9.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn9.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 10" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn10.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn10.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn10.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn10.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn10.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn10.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn10.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn10.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 11" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn11.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn11.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn11.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn11.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn11.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn11.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn11.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn11.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 12", Toast.LENGTH_SHORT).show();

                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn12.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn12.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn12.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn12.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn12.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn12.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn12.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }
            }

        });
        Sbtn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 13" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn13.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn13.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn13.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn13.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn13.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn13.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn13.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn13.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 14" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn14.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn14.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn14.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn14.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn14.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn14.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn14.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn14.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 15" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn15.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn15.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn15.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn15.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn15.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn15.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn15.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn15.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 16" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn16.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn16.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn16.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn16.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn16.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn16.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn16.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn16.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 17" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn17.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn17.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn17.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn17.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn17.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn17.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn17.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn1.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 18" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn18.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn18.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn18.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn18.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn18.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn18.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn18.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn18.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 19" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn19.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn19.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn19.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn19.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn19.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn19.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn19.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn19.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 20" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn20.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn20.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn20.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn20.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn20.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn20.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn20.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn20.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 21" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn21.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn21.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn21.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn21.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn21.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn21.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn21.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn21.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 22" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn22.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn22.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn22.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn22.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn22.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn22.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn22.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn21.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 23" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn23.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn23.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn23.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn23.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn23.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn23.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn23.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn23.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 24" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn24.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn24.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn24.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn24.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn24.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn24.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn24.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn24.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 25" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn25.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn25.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn25.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn25.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn25.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn25.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn25.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn25.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 26" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn26.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn26.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn26.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn26.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn26.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn26.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn26.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn26.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 27" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn27.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn27.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn27.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn27.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn27.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn27.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn27.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn27.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 28" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn28.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn28.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn28.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn28.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn28.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn28.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn28.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn28.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 29" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn29.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn29.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn29.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn29.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn29.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn29.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn29.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn29.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 30" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn30.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn30.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn30.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn30.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn30.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn30.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn30.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn30.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 31" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn31.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn31.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn31.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn31.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn31.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn31.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn31.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn31.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 32" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn32.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn32.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn32.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn32.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn32.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn32.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn32.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn32.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 33" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn33.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn33.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn33.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn33.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn33.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn33.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn33.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn33.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 34" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn34.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn34.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn34.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn34.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn34.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn34.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn34.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn34.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 35" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn35.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn35.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn35.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn12.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn35.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn35.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn35.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn35.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 36" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn36.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn36.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn36.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn36.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn36.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn36.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn36.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn36.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 37" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn37.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn37.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn37.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn37.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn37.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn37.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn37.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn37.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn38.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 38" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn38.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn38.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn38.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn38.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn38.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn38.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn38.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn38.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn39.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 39" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn39.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn39.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn39.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn39.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn39.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn39.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn39.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn39.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 40" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn40.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn40.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn40.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn40.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn40.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn40.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn40.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn40.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 41" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn41.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn41.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn41.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn41.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn41.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn41.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn41.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn41.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 42" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn42.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn42.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn42.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn42.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn42.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn42.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn42.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn42.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 43" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn43.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn43.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn43.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn43.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn43.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn43.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn43.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn43.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 44" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn44.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn44.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn44.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn44.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn44.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn44.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn44.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn44.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 45" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn45.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn45.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn45.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn45.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn45.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn45.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn45.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn45.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn46.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 46" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn46.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn46.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn46.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn46.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn46.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn46.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn46.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn46.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn47.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 47" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn47.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn47.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn47.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn47.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn47.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn47.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn47.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn47.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn48.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 48" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn48.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn48.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn48.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn48.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn48.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn48.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn48.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn48.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sbtn49.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(SelfDrawActivity.this, "You select 49" , Toast.LENGTH_SHORT).show();

//                    text_field.setText(Sbtn49.getText());
                if (Stxt1.getText().length() == 0) {
                    Stxt1.setText(Sbtn49.getText());
                } else if (Stxt2.getText().length() == 0) {
                    Stxt2.setText(Sbtn49.getText());
                } else if (Stxt3.getText().length() == 0) {
                    Stxt3.setText(Sbtn49.getText());
                } else if (Stxt4.getText().length() == 0) {
                    Stxt4.setText(Sbtn49.getText());
                } else if (Stxt5.getText().length() == 0) {
                    Stxt5.setText(Sbtn49.getText());
                } else if (Stxt6.getText().length() == 0) {
                    Stxt6.setText(Sbtn49.getText());
                } else {
                    if ((Stxt1.getText().length() != 0) && (Stxt2.getText().length() != 0) && (Stxt3.getText().length() != 0) && (Stxt4.getText().length() != 0) && (Stxt5.getText().length() != 0) && (Stxt6.getText().length() != 0)) {
                        Sbtn49.setEnabled(false);
                        Toast.makeText(SelfDrawActivity.this, "Sorry  You can't choose more than 6", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Sdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//
//                Intent intent=new Intent(SelfDrawActivity.this,MatchDailyActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString( "counter", String.valueOf(count));
//                intent.putExtras(bundle);
//                startActivity(intent);
                SharedPreferences pref = getSharedPreferences("MyPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("counter", String.valueOf(count)).commit();

                System.out.println("===========pref===========" + editor.putString("counter", String.valueOf(count)).commit());
              try {
                  if (  (Stxt1.getText().length() == 0) && (Stxt2.getText().length() == 0) && (Stxt3.getText().length() == 0) && (Stxt4.getText().length() == 0) && (Stxt5.getText().length() == 0) && (Stxt6.getText().length() == 0))

                  {

                      Intent intent = new Intent(SelfDrawActivity.this, MatchDailyActivity.class);
                      startActivity(intent);

                  }

                  else

                  {
                      Toast.makeText(getApplicationContext(),"please click on play button",Toast.LENGTH_SHORT).show();
                  }
              }catch (Exception e){}


            }

        });

            Sback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(SelfDrawActivity.this, PlayActivity.class);
                    startActivity(i);
                }
            });


       onReceive(this);




    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent iBack = new Intent(SelfDrawActivity.this, PlayActivity.class);
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

