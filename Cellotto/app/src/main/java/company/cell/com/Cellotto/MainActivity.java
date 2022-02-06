package company.cell.com.Cellotto;

import android.content.Context;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import company.cell.com.Cellotto.Custom.NetworkUtil;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

//import company.cell.com.cellotto.Custom.FontsActivity;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
     Button application,sharePlay,play, Mexit;




    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onReceive(this);

//      FirebaseInstanceId.getInstance().getToken();

//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setLogo(R.drawable.cellotto_small);
//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);

//        getSupportActionBar().setDisplayUseLogoEnabled(true);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


//        ImageView msearch = (ImageView) toolbar.findViewById(R.id.toolbaricon);
        application=(Button)findViewById(R.id.application);
        sharePlay=(Button)findViewById(R.id.share_play);
        play=(Button)findViewById(R.id.play);
        Mexit=(Button)findViewById(R.id.mexit);
        application.setOnClickListener(this);
        sharePlay.setOnClickListener(this);
        play.setOnClickListener(this);
        Mexit.setOnClickListener(this);

//        FontsActivity.setDefaultFont(this, "DEFAULT", "SHOWG.ttf");

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.application:
                Intent i1 =new Intent(MainActivity.this, ApplicationActivity.class);
                startActivity(i1);
                finish();
                break;
            case R.id.share_play:
                Intent i2 = new Intent(MainActivity.this, SharePlay.class);
                startActivity(i2);
                finish();
                break;
            case R.id.play:
              /* Intent i3 = new Intent(MainActivity.this, PlayActivity.class);
                startActivity(i3);*/
                Intent i3 = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(i3);
                finish();
                break;
            case R.id.mexit:
             finish();
                break;
        }

    }
    public void onReceive(Context context) {
        String status = NetworkUtil.getConnectivityStatusString(context);
        if ((status=="Not connected to Internet")){
            Toast.makeText(context, status, Toast.LENGTH_SHORT).show();
        }




    }}
