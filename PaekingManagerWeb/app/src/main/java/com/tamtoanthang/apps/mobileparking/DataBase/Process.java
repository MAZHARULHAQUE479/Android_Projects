package com.tamtoanthang.apps.mobileparking.DataBase;

import android.app.Activity;
import android.app.ProgressDialog;

/**
 * Created by lue on 21-12-2016.
 */
public class Process {
    Activity activity;
    ProgressDialog progressDoalog;
    public Process(Activity activity){
        this.activity=activity;
    }
    public void showProcess(){
        progressDoalog = new ProgressDialog(activity);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Its loading....");
        progressDoalog.setTitle("ProgressDialog bar example");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDoalog.show();
        progressDoalog.setCancelable(true);
    }
    public void hideProcess(){
        if (progressDoalog.isShowing()){
            progressDoalog.cancel();
        }
    }


}
