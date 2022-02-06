package com.stronglink.sl110.demo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
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

public class MainActivity extends Activity implements IvrJackAdapter {

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
    private View buttonRead;
    private View buttonWrite;
    private View buttonUid;

    private IvrJackService service;
    private BroadcastReceiver volumeBroadcast;

    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    @Override
    public void onConnect(String deviceSn) {
        System.out.println("on connect");

        viewPlugout.setVisibility(View.GONE);
        layoutReader.setVisibility(View.VISIBLE);

        viewSn.setText("SN:" + deviceSn);
        viewUid.setText("");
        viewKeyA.setText("FFFFFFFFFFFF");
        viewBlockNo.setText("4");
        viewBlockData.setText("");
    }

    @Override
    public void onDisconnect() {
        System.out.println("on disconnect");
        layoutReader.setVisibility(View.GONE);
        viewPlugout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStatusChange(IvrJackStatus ivrJackStatus) {

    }

    /*
        @Override
        public void onStatusChange(IvrJackStatus status) {
            System.out.println("on status change: " + status);
            switch (status) {
                case ijsDetecting:
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
                    viewStatus.setText("Device connected.");
                    stopAnimation();
                    break;
                case ijsUnRecognized:
                    viewStatus.setText("Device unrecognized.");
                    stopAnimation();
                    break;
                case ijsPlugout:
                    viewStatus.setText("Device disconnected.");
                    stopAnimation();
                    break;
            }
        }

        public void onRead(View v) {
            final byte[] keyA = getKeyA();
            if (keyA == null) {
                Toast.makeText(this, "KeyA should be 6 bytes.", Toast.LENGTH_LONG).show();
                return;
            }
            final byte[] blockNo = getBlockNo();
            if (blockNo == null) {
                Toast.makeText(this, "Wrong BlockNo Format", Toast.LENGTH_LONG).show();
                return;
            }
            updateUi(true);
            viewStatus.setText("Device read block...");
            viewUid.setText("");
            viewBlockData.setText("");
            new Thread() {
                @TargetApi(Build.VERSION_CODES.CUPCAKE)
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
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                viewUid.setText(bytes2hex(tagUid.uid, ""));
                            }
                        });
                        if (tagUid.cardType == 0) {
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
                        }
                    } while (false);

                    final String finalInfo = info;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            viewStatus.setText(finalInfo);
                            if (tagBlock.data != null) {
                                viewBlockData.setText(bytes2hex(tagBlock.data, " "));
                            }
                            updateUi(false);
                        }
                    });
                }
            }.start();
        }

        public void onWrite(View v) {
            final byte[] keyA = getKeyA();
            if (keyA == null) {
                Toast.makeText(this, "KeyA should be 6 bytes.", Toast.LENGTH_LONG).show();
                return;
            }
            final byte[] blockNo = getBlockNo();
            if (blockNo == null) {
                Toast.makeText(this, "Wrong BlockNo Format", Toast.LENGTH_LONG).show();
                return;
            }
            final byte[] blockData = getBlockData();
            if (blockData == null) {
                Toast.makeText(this, "Wrong BlockData Format", Toast.LENGTH_LONG).show();
                return;
            }
            updateUi(true);
            viewStatus.setText("Device write block...");
            viewUid.setText("");
            new Thread() {
                @Override
                public void run() {
                    String info = null;
                    final IvrJackService.TagUid tagUid = new IvrJackService.TagUid();
                    do {
                        int ret = service.readTagUid(tagUid);
                        if (ret != 0) {
                            info = "Device write block failed: " + ret;
                            break;
                        }
                        if (tagUid.uid == null) {
                            info = "Device write block failed: no tag";
                            break;
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                viewUid.setText(bytes2hex(tagUid.uid, ""));
                            }
                        });
                        if (tagUid.cardType == 0) {
                            // 14443A
                            if (blockData.length < 16) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "BlockData should be " +
                                                "at least 16 bytes", Toast.LENGTH_LONG).show();
                                    }
                                });
                                break;
                            }
                            ret = service.authenticateTag((byte)(blockNo[0]/4), (byte)0, keyA);
                            if (ret == 0) {
                                ret = service.writeTagBlock(blockNo[0], blockData);
                            }
                            if (ret == 0) {
                                info = "Device write block success!";
                            } else {
                                info = "Device write block failed: " + ret;
                            }
                        } else {
                            // Ultralight
                            ret = service.writeTagBlockUL(blockNo[0], (byte)1, blockData);
                            if (ret == 0) {
                                info = "Device write block success!";
                            } else {
                                info = "Device write block failed: " + ret;
                            }
                        }
                    } while (false);
                    final String finalInfo = info;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            viewStatus.setText(finalInfo);
                            updateUi(false);
                        }
                    });
                }
            }.start();
        }

        public void onUid(View v) {
            updateUi(true);
            viewStatus.setText("Device read uid...");
            viewUid.setText("");
            new Thread() {
                @Override
                public void run() {
                    String info;
                    final IvrJackService.TagUid tagUid = new IvrJackService.TagUid();
                    int ret = service.readTagUid(tagUid);
                    if (ret == 0) {
                        if (tagUid.uid != null) {
                            info = "Device read uid success!";
                        } else {
                            info = "Device read uid: no tag!";
                        }
                    } else {
                        info = "Device read uid failed: " + ret;
                    }
                    final String finalInfo = info;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            viewStatus.setText(finalInfo);
                            if (tagUid.uid != null) {
                                viewUid.setText(bytes2hex(tagUid.uid, ""));
                            }
                            updateUi(false);
                        }
                    });
                }
            }.start();
        }
    */
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      /*
        viewStatus = (TextView) findViewById(R.id.status);
        viewProgress = (ImageView) findViewById(R.id.progress);
        layoutReader = findViewById(R.id.readerLayout);
        viewPlugout = findViewById(R.id.plugout);
        viewSn = (TextView) findViewById(R.id.sn);
        viewUid = (TextView) findViewById(R.id.uid);
        viewKeyA = (EditText) findViewById(R.id.keyA);
        viewBlockNo = (EditText) findViewById(R.id.blockNo);
        viewBlockData = (EditText) findViewById(R.id.blockData);
        viewVolume = (ProgressBar) findViewById(R.id.volume);
        buttonRead = findViewById(R.id.readButton);
        buttonWrite = findViewById(R.id.writeButton);
        buttonUid = findViewById(R.id.uidButton);

        TextView viewTime = (TextView) findViewById(R.id.time);
       viewTime.setText(new SimpleDateFormat("yyyy-MM-dd E", Locale.US).format(new Date()));

        service = new IvrJackService(this, this);

        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        viewVolume.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        viewVolume.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
        volumeBroadcast = new VolumnBroadcast();
        registerReceiver(volumeBroadcast, new IntentFilter("android.media.VOLUME_CHANGED_ACTION"));

      */
    }
/*
    @Override
    protected void onResume() {
        super.onResume();
        service.open();
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
        buttonRead.setEnabled(!editing);
        buttonWrite.setEnabled(!editing);
        buttonUid.setEnabled(!editing);
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
*/

}
