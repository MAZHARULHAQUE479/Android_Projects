package com.tamtoanthang.apps.mobileparking;

import android.Manifest;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.support.v4.app.ActivityCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tamtoanthang.apps.mobileparking.Admin.AdminArea;
import com.tamtoanthang.apps.mobileparking.DataBase.LanguageDatabase;
import com.tamtoanthang.apps.mobileparking.DataBase.ReadLanguage;
import com.tamtoanthang.apps.mobileparking.DialogFragment.DFragmentAdmin;
import com.tamtoanthang.apps.mobileparking.DialogFragment.DFragmentUser;

import com.tamtoanthang.apps.mobileparking.DialogFragment.SettingFragment;
import com.tamtoanthang.apps.mobileparking.R;
import com.google.android.gms.common.api.GoogleApiClient;

import com.tamtoanthang.apps.mobileparking.java.MarshMallowPermission;
import com.xminnov.ivrjack.rh06.IvrJackAdapter;

import java.util.Locale;

public class Main2Activity extends BaseActivity {
    private static final String PREFS_NAME = "name";
    Button button1,bt2,bt3,bt4,bt5,btn6,btn7;
    EditText EditText_url,Edit_agent;
    TextView tv1,tv2;
    ImageView setting;
    RelativeLayout r1,r2,r3;
    SharedPreferences   SharedBaseUrl;
    SharedPreferences   Ag,Bu;
    private Boolean exit = false;
    String log="",AgentId,BaseUrl;
    IvrJackAdapter ivrJackAdapter;
    MarshMallowPermission marshMallowPermission;
     SharedPreferences SM;
    SharedPreferences prefs ;
    String myVariable ="";
    GoogleApiClient googleApiClient;

    public void onCreate(Bundle save)
    {
        MultiDex.install(getApplicationContext());
        super.onCreate(save);
        prefs= getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        myVariable=prefs.getString("key_name", "default_value");
        if(!myVariable.equals("")) {

            Log.d("cjbjsn", " " + myVariable + " " + log);
            Resources res = getResources();
            // Change locale settings in the app.
            DisplayMetrics dm = res.getDisplayMetrics();
            android.content.res.Configuration conf = res.getConfiguration();
            conf.locale = new Locale(myVariable.toLowerCase());
            res.updateConfiguration(conf, dm);

            LanguageDatabase languageDatabase = new LanguageDatabase(Main2Activity.this);
            languageDatabase.open();
            languageDatabase.update(myVariable);
            languageDatabase.close();
        }
        setContentView(R.layout.activity_main2);
        SM = getSharedPreferences("userrecord", 0);

        bt2=(Button)findViewById(R.id.admin);
        bt5=(Button)findViewById(R.id.intro_btn_language) ;
        btn6=(Button)findViewById(R.id.btnurl);
        btn7=(Button)findViewById(R.id.btnagent);
        EditText_url=(EditText)findViewById(R.id.texturl) ;
        Edit_agent=(EditText)findViewById(R.id.textagent);
        setting=(ImageView)findViewById(R.id.setting);
        r1=(RelativeLayout)findViewById(R.id.url);
        r2=(RelativeLayout)findViewById(R.id.agent);
        r3=(RelativeLayout)findViewById(R.id.menu);
        tv1=(TextView)findViewById(R.id.tv1);
        tv2=(TextView)findViewById(R.id.tv2);

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                r3.setVisibility(View.VISIBLE);
//                if (r3.getVisibility()==View.INVISIBLE){
//                    r3.setVisibility(View.VISIBLE);
//                }else
//                    if (r3.getVisibility()==View.VISIBLE)
//                    {
//                        r3.setVisibility(View.INVISIBLE);
//                    }
                SettingFragment dialogFragment = new SettingFragment();
                FragmentManager fm = getFragmentManager();
                dialogFragment.show(fm, "Setting Fragment");

            }
        });
//        tv1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                r1.setVisibility(View.VISIBLE);
//                r3.setVisibility(View.INVISIBLE);
//                r2.setVisibility(View.INVISIBLE);
//            }
//        });
//        tv2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                r2.setVisibility(View.VISIBLE);
//                r3.setVisibility(View.INVISIBLE);
//                r1.setVisibility(View.INVISIBLE);
//            }
//        });
//        btn7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AgentId= Edit_agent.getText().toString();
//                Ag = getSharedPreferences("agent", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = Ag.edit();
//                editor.putString("agentId", AgentId);
//                editor.commit(); // or editor.apply();
//                Toast.makeText(Main2Activity.this,"Agent Name Save Sucessfully",Toast.LENGTH_SHORT).show();
//                Edit_agent.setText("");
//
//            }
//        });
//        btn6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                BaseUrl=EditText_url.getText().toString();
//                Bu = getSharedPreferences("Base", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = Bu.edit();
//                editor.putString("baseurl", BaseUrl);
//                editor.commit(); // or editor.apply();
//                Toast.makeText(Main2Activity.this,"Base Url Is Save Sucessfully",Toast.LENGTH_SHORT).show();
//                EditText_url.setText("");
//
//            }
//        });


        marshMallowPermission = new MarshMallowPermission(Main2Activity.this);
        String[] PERMISSIONS_ARRAY = {android.Manifest.permission.CAMERA, android.Manifest.permission.RECORD_AUDIO, android.Manifest.permission.GET_ACCOUNTS,
                android.Manifest.permission.READ_EXTERNAL_STORAGE,android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_INPUT_STATE, android.Manifest.permission.GET_ACCOUNTS_PRIVILEGED,
                android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK, android.Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.READ_SYNC_SETTINGS,
                Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.MODIFY_AUDIO_SETTINGS};
        int PERMISSION_ALL = 1;


        if(!marshMallowPermission.hasPermissions(Main2Activity.this, PERMISSIONS_ARRAY)) {
            ActivityCompat.requestPermissions(Main2Activity.this, PERMISSIONS_ARRAY, PERMISSION_ALL);
        }
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DFragmentAdmin dialogFragment = new DFragmentAdmin();
                FragmentManager fm = getFragmentManager();
                dialogFragment.show(fm, "Sample Fragment");

            }
        });

        bt4=(Button)findViewById(R.id.user);
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* boolean userlogin = SM.getBoolean("userlogin", false);
                if(userlogin==true) {
                    Intent intent= new Intent(Main2Activity.this, SecondActivity.class);
                    startActivity(intent);
                }else {*/


                DFragmentUser dialogFragment = new DFragmentUser();
                FragmentManager fm = getFragmentManager();
                dialogFragment.show(fm, "Sample Fragment");

            }
        });


      /*  if(!myVariable.equals("")){
            if(myVariable.equals())
        }*/

    }

    @Override
    protected void onResume() {

        super.onResume();

    }

    public void admin(){
        Intent intent = new Intent(Main2Activity.this,AdminArea.class);
        startActivity(intent);
    }
    public void language(View view)
    {

        onchooselang();

    }
    private String getResString(int resId){
        return getResources().getString(resId);
    }

    public void onchooselang()
    {

        try {

            final String s[] = {getResString(R.string.en), getResString(R.string.vi)};
            AlertDialog.Builder adb = new AlertDialog.Builder(Main2Activity.this);
            adb.setTitle(R.string.txt_setting_changeyourlocale);
            final String lan = ReadLanguage.read(Main2Activity.this);
            adb.setAdapter(new ArrayAdapter(Main2Activity.this, android.R.layout.simple_list_item_1, s), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case -1:

                            break;
                        case 0:
                            log = "EN";

                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString("key_name", log);
                            editor.commit();
                            bt5.setText("ENGLISH");
                            break;
                        case 1:
                            log = "VI";
                            bt5.setText("Vietnamese");
                            prefs= getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                            editor = prefs.edit();
                            editor.putString("key_name", log);
                            editor.commit();
                            break;

                        default:

                            break;
                    }

                    if (!log.equals(lan)) {
                        showRestartConfirmDlg(log);
                    }
                }
            });
            adb.show();
        }
        catch (Exception e)
        {
            Toast.makeText(this,"Exception "+e,Toast.LENGTH_LONG).show();
        }
    }

    public void showRestartConfirmDlg(final String log)
    {
        AlertDialog.Builder adb=new AlertDialog.Builder(Main2Activity.this);
        adb.setTitle("Parking manager");
        adb.setMessage(getResources().getString(R.string.msg_change_locale));
        adb.setPositiveButton(getString(R.string.OK),new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(log==getResString(R.string.vi)){
                    bt5.setText("Vietnamese");
                }
                changeLocale(log);
            }
        });
        adb.setNegativeButton(getString(R.string.cancel),null);
        adb.show();
    }

    private void changeLocale(String language_code){
        Resources res = getResources();
        // Change locale settings in the app.
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.locale = new Locale(language_code.toLowerCase());
        res.updateConfiguration(conf, dm);

        LanguageDatabase languageDatabase=new LanguageDatabase(Main2Activity.this);
        languageDatabase.open();
        languageDatabase.update(language_code);
        languageDatabase.close();
        restartApp();
    }

    private void restartApp(){
        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    public void onBackPressed(){
        if (exit) {
            //finish(); // finish activity
            //finish(); // finish activity
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(a);
        }
        else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    Intent a = new Intent(Intent.ACTION_MAIN);
                    a.addCategory(Intent.CATEGORY_HOME);

                    a.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(a);
                }
            }, 1000);

        }
    }

}
