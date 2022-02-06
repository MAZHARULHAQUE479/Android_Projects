package com.tamtoanthang.apps.mobileparking;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.xminnov.ivrjack.rh06.IvrJackAdapter;
import com.xminnov.ivrjack.rh06.IvrJackService;
import com.xminnov.ivrjack.rh06.IvrJackStatus;
import com.stronglink.sl110.demo.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class ChooserActivity extends Activity implements IvrJackAdapter {

    private TextView viewStatus;
    private ImageView viewProgress;
    private View layoutReader;
    private View viewPlugout;
    private TextView viewSn;
    private TextView viewUid;
    private EditText viewKeyA;
    private EditText viewBlockNo;
    private EditText viewBlockData;
    private ProgressBar viewVolume;
   // private View buttonRead;
   // private View buttonWrite;
  //  private View buttonUid;

    private IvrJackService service;
    private BroadcastReceiver volumeBroadcast;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    public static Timer timer;
    TimerTask timerTask;
    final Handler handler = new Handler();
   public static String tagData= "";

    @Override
    public void onConnect(String deviceSn) {
        System.out.println("on connect");
        Log.d("con","Onconect");
     //  viewPlugout.setVisibility(View.GONE);
         //layoutReader.setVisibility(View.VISIBLE);

        viewSn.setText("SN:" + deviceSn);
        viewUid.setText("");
        viewKeyA.setText("FFFFFFFFFFFF");
        viewBlockNo.setText("4");
        viewBlockData.setText("");

        timer = new Timer();
        //initialize the TimerTask's job

        initializeTimerTask();

        //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms

        timer.schedule(timerTask, 500, 12000); //

    }

    @Override
    public void onDisconnect() {
        Log.d("con","Ondisconect");
        System.out.println("on disconnect");
        //layoutReader.setVisibility(View.GONE);
       // viewPlugout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStatusChange(IvrJackStatus status) {
        System.out.println("on status change: " + status);
        switch (status) {
            case ijsDetecting:
                Log.d("con","isDetecting");
                viewStatus.setText("Device detecting...");
                startAnimation();
                AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                viewVolume.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
                viewVolume.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
                if (viewVolume.getMax() != viewVolume.getProgress()) {
                    new AlertDialog.Builder(this)
                            .setTitle("Please set the volume to maximum!")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();

                }
                break;
            case ijsRecognized:
                Log.d("con","isRecognized");
                viewStatus.setText("Device connected.");
                stopAnimation();
                break;
            case ijsUnRecognized:
                Log.d("con","isUnRecognized");
                viewStatus.setText("Device unrecognized.");
                stopAnimation();
                break;
            case ijsPlugout:
                Log.d("con","Plugout");
                viewStatus.setText("Device disconnected.");
                Log.d("tresult","Plogout");
                Intent intent1 = new Intent(ChooserActivity.this,SecondActivity.class);
                startActivity(intent1);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewStatus = (TextView) findViewById(R.id.status);
        viewProgress = (ImageView) findViewById(R.id.progress);
        layoutReader = findViewById(R.id.readerLayout);
      //  viewPlugout = findViewById(R.id.plugout);
        viewSn = (TextView) findViewById(R.id.sn);
        viewUid = (TextView) findViewById(R.id.uid);
        viewKeyA = (EditText) findViewById(R.id.keyA);
        viewBlockNo = (EditText) findViewById(R.id.blockNo);
        viewBlockData = (EditText) findViewById(R.id.blockData);
        viewVolume = (ProgressBar) findViewById(R.id.volume);
       // buttonRead = findViewById(R.id.readButton);
       // buttonWrite = findViewById(R.id.writeButton);
      //  buttonUid = findViewById(R.id.uidButton);

        TextView viewTime = (TextView) findViewById(R.id.time);
        viewTime.setText(new SimpleDateFormat("yyyy-MM-dd E", Locale.US).format(new Date()));

        service = new IvrJackService(this, this);


        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        viewVolume.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        viewVolume.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
        volumeBroadcast = new VolumnBroadcast();
        registerReceiver(volumeBroadcast, new IntentFilter("android.media.VOLUME_CHANGED_ACTION"));
        volumeBroadcast.getResultData();
        AudioManager am1 =(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        if(!am1.isWiredHeadsetOn()){
            Log.d("con","Plugssssssout");
            startActivity(new Intent(ChooserActivity.this, SecondActivity.class));
            Toast.makeText(ChooserActivity.this,"Device is not connected Please use manual moade",Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        service.open();
        Log.d("con","onRes");
        System.out.println("open");
    }

    @Override
    protected void onPause() {
        super.onPause();
        service.close();
        System.out.println("close");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(volumeBroadcast);
    }

    private void startAnimation() {
        viewProgress.setVisibility(View.VISIBLE);
        AnimationDrawable anim = (AnimationDrawable) viewProgress.getDrawable();
        anim.start();
    }

    private void stopAnimation() {
        viewProgress.setVisibility(View.INVISIBLE);
        AnimationDrawable anim = (AnimationDrawable) viewProgress.getDrawable();
        anim.stop();
    }

    private void updateUi(boolean editing) {
     //   buttonRead.setEnabled(!editing);
      //  buttonWrite.setEnabled(!editing);
      //  buttonUid.setEnabled(!editing);
        if (editing) {
            startAnimation();
        } else {
            stopAnimation();
        }
    }

    private byte[] getKeyA() {
        byte[] keyA = hex2bytes(viewKeyA.getText().toString());
        if (keyA != null) {
            if (keyA.length != 6) {
                return null;
            }
        }
        return keyA;
    }

    private byte[] getBlockNo() {
        byte[] blockNo = new byte[1];
        try {
            blockNo[0] = (byte)(Integer.parseInt(viewBlockNo.getText().toString()));
        } catch (NumberFormatException e) {
            return null;
        }
        return blockNo;
    }

    private byte[] getBlockData() {
        byte[] blockData = hex2bytes(viewBlockData.getText().toString());
        if (blockData != null) {
            if (blockData.length < 4) {
                return null;
            }
        }
        return blockData;
    }

    private byte[] hex2bytes(String hex) {
        hex = hex.replace(" ", "");
        if (hex.length() % 2 != 0) {
            return null;
        }
        byte[] bytes = new byte[hex.length() / 2];
        for (int i=0; i<bytes.length; i++) {
            try {
                bytes[i] = (byte) Integer.parseInt(hex.substring(i*2, i*2+2), 16);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return bytes;
    }

    private String bytes2hex(byte[] bytes, String padding) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b)).append(padding);
        }
        return sb.toString();
    }

    private class VolumnBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            viewVolume.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            viewVolume.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
        }

    }



    public void initializeTimerTask() {

        timerTask = new TimerTask() {
            public void run() {

                //use a handler to run a toast that shows the current timestamp
                handler.post(new Runnable() {
                    public void run() {
                        //get the current timeStamp

                        final byte[] keyA = getKeyA();
                        if (keyA == null) {
                            //     Toast.makeText(this, "KeyA should be 6 bytes.", Toast.LENGTH_LONG).show();
                            return;
                        }
                        final byte[] blockNo = getBlockNo();
                        if (blockNo == null) {
                            // Toast.makeText(this, "Wrong BlockNo Format", Toast.LENGTH_LONG).show();
                            return;
                        }
                        updateUi(true);
                        viewStatus.setText("Device read block...");
                        viewUid.setText("");
                        viewBlockData.setText("");
                        new Thread() {
                            @Override
                            public void run() {
                                String info;

                                final IvrJackService.TagBlock tagBlock = new IvrJackService.TagBlock();
                                do {
                                    final IvrJackService.TagUid tagUid = new IvrJackService.TagUid();
                                    int ret = service.readTagUid(tagUid);
                                    if (ret != 0) {
                                        info = "Device read block failed: " + ret;
                                        break;
                                    }
                                    if (tagUid.uid == null) {
                                        info = "Device read block failed: no tag";
                                        break;
                                    }
                                    // 鐣岄潰鏇存柊UID
//                                    runOnUiThread(new Runnable() {
//                                        @Override
//                                        public void run() {
                                            tagData=bytes2hex(tagUid.uid, "");
                                    if (!tagData.equals("")) {

                                        Log.d("fki", "jk" + tagData);
                                        Intent intent = new Intent(ChooserActivity.this, SecondActivity.class);
                                        startActivity(intent);
                                        break;
                                    };
//                                        }
//                                    });
                                   /* if (tagUid.cardType == 0) {
                                        // 14443A
                                        ret = service.authenticateTag((byte)(blockNo[0]/4), (byte)0, keyA);
                                        if (ret == 0) {
                                            ret = service.readTagBlock(blockNo[0], tagBlock);
                                        }
                                        if (ret == 0) {
                                            info = "Device read block success!";
                                        } else {
                                            info = "Device read block failed: " + ret;
                                        }
                                    } else {
                                        // Ultralight
                                        ret = service.readTagBlockUL(blockNo[0], (byte) 1, tagBlock);
                                        if (ret == 0) {
                                            info = "Device read block success!";
                                        } else {
                                            info = "Device read block failed: " + ret;
                                        }
                                    }*/
                                } while (false);



                           }
                        }.start();

                        //show the toast
                        //int duration = Toast.LENGTH_SHORT;
                      //  Toast toast = Toast.makeText(getApplicationContext(), tagData, duration);
                        //toast.show();
                    }
                });
            }
        };
    }
    public void onBackPressed() {

        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(a);


    }

}
