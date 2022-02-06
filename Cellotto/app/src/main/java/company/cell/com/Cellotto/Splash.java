package company.cell.com.Cellotto;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.skyfishjy.library.RippleBackground;

public class Splash extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    private final String MY_lOGIN_PREFERENCE="my_login_preference";
    public static final String Login = "login";
    AlertDialog alertDialog;
    //String loginstring="no";
    ImageView spaceshipImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();
        setContentView(R.layout.activity_splash);

        spaceshipImage = (ImageView) findViewById(R.id.image);
        final RippleBackground rippleBackground=(RippleBackground)findViewById(R.id.content);
        rippleBackground.startRippleAnimation();
        sharedPreferences = getSharedPreferences(MY_lOGIN_PREFERENCE, Context.MODE_PRIVATE);
        String loginstring=sharedPreferences.getString( Login,"" );
        {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i= new Intent(Splash.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                },5000); // SPLASH_TIME_OUT);
            }
        }
    }


