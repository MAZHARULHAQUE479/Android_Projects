package com.example.expandablenavigationdrawerdemoo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.skyfishjy.library.RippleBackground;

public class Splash extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    private final String MY_lOGIN_PREFERENCE = "my_login_preference";
    public static final String Login = "login";
    AlertDialog alertDialog;
    //String loginstring="no";
    ImageView spaceshipImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();
        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        setContentView(R.layout.activity_splash);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        spaceshipImage = (ImageView) findViewById(R.id.image);
        final RippleBackground rippleBackground = (RippleBackground) findViewById(R.id.content);
        rippleBackground.startRippleAnimation();
        sharedPreferences = getSharedPreferences(MY_lOGIN_PREFERENCE, Context.MODE_PRIVATE);
        String loginstring = sharedPreferences.getString(Login, "");
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(Splash.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }, 5000); // SPLASH_TIME_OUT);
        }
    }
}


