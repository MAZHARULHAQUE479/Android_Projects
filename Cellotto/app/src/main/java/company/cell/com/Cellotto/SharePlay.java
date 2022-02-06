package company.cell.com.Cellotto;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import company.cell.com.Cellotto.Custom.MyEditTextLight;
import company.cell.com.Cellotto.Custom.NetworkUtil;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SharePlay extends AppCompatActivity implements View.OnClickListener {
    MyEditTextLight secret_code;
    Button grant_access;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_play);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setLogo(R.drawable.cellotto_small);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        secret_code=(MyEditTextLight)findViewById(R.id.secret_code);
        grant_access=(Button)findViewById(R.id.grant_access);
        grant_access.setOnClickListener(this);

       onReceive(this);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent iBack = new Intent(SharePlay.this, MainActivity.class);
        startActivity(iBack);
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.grant_access:
                Toast.makeText(getApplicationContext(), "Secret Code not valid", Toast.LENGTH_LONG).show();
                break;
        }
    }
    public void onReceive(Context context) {
        String status = NetworkUtil.getConnectivityStatusString(context);
        if ((status == "Not connected to Internet")) {
            Toast.makeText(context, status, Toast.LENGTH_SHORT).show();
        }
    }
    }
