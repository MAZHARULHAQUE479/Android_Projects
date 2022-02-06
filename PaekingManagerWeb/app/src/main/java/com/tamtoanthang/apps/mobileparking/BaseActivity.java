package com.tamtoanthang.apps.mobileparking;

import android.app.Activity;
import android.app.AlertDialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

public class BaseActivity extends Activity
{

    public void onCreate(Bundle save)
    {
        super.onCreate(save);
        /*if(!isConnected())
        {
            AlertDialog.Builder adb=new AlertDialog.Builder(BaseActivity.this);
            adb.setTitle("Error");
            adb.setMessage("No Internet Connection, Please Connect to Internet and then try again");
            adb.setCancelable(false);
            adb.setPositiveButton("Okay",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            adb.show();
        }*/
    }


    public void showtoast(String s)
    {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

    public  boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }


    public void showalertdialog(String msg,String title)
    {
        AlertDialog.Builder adb=new AlertDialog.Builder(BaseActivity.this);
        adb.setTitle(title);
        adb.setMessage(msg);
        adb.setPositiveButton("Okay",null);
        adb.show();
    }
}
