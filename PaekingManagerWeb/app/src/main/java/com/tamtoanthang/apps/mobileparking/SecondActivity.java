package com.tamtoanthang.apps.mobileparking;

import android.app.FragmentManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.media.AudioManager;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tamtoanthang.apps.mobileparking.DialogFragment.ZoomFragment;
import com.tamtoanthang.apps.mobileparking.Model.Contact;
import com.tamtoanthang.apps.mobileparking.Model.ContactModel;
import com.tamtoanthang.apps.mobileparking.DataBase.DatabaseHelper;
import com.tamtoanthang.apps.mobileparking.DataBase.SQLiteHelper;
import com.tamtoanthang.apps.mobileparking.DataBase.UserSessionManager;
import com.tamtoanthang.apps.mobileparking.DialogFragment.DFragmentSet;
import com.tamtoanthang.apps.mobileparking.DialogFragment.DThreeFragment;
import com.tamtoanthang.apps.mobileparking.DialogFragment.DfragmentTotal;
import com.tamtoanthang.apps.mobileparking.DialogFragment.D2Fragment;
import com.tamtoanthang.apps.mobileparking.java.HTTPResponse;
import com.tamtoanthang.apps.mobileparking.java.MarshMallowPermission;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveId;
import com.xminnov.ivrjack.rh06.IvrJackAdapter;
import com.xminnov.ivrjack.rh06.IvrJackService;
import com.xminnov.ivrjack.rh06.IvrJackStatus;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.widget.Toast;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import static com.rey.material.util.ThemeUtil.dpToPx;
//import static com.stronglink.sl110.demo.R.id.imageView;

public class SecondActivity extends AppCompatActivity implements IvrJackAdapter {

    private static final int REQUEST_CODE_RESOLUTION = 1;
    String img = "http://";
    private static final int REQUEST_CODE_OPENER = 2;
    private GoogleApiClient mGoogleApiClient;
    private boolean fileOperation = false;
    private static Uri fileUri;
    UserSessionManager session;
    public static DriveId mFileId;
    public DriveFile file;
    Button set, run, serch, total, capture;
    DatabaseHelper dbHelper;
    String hc = "";
    int flag = 0;
    private ProgressBar viewVolume;
    String idfromreader = "";
    String cardprice = "";
    public static byte[] dataimage = null;
    public static ImageView camera1, galleryimage;
    public static TextView id, price;
    String idd;
    DatabaseHelper databaseHelper;
    SQLiteHelper sQLiteHelper;
    SQLiteDatabase database;
    private int position = 0;
    String fName = "";
    String jndj = "";
    private Context mContext;
    SurfaceHolder mHolder;
    private static final String TAG = "CameraDemo";
    SurfaceView sv;
    Camera mcamera;
    CameraPreview preview;
    String numb = "";
    SQLiteHelper sqLiteHelper;
    AudioManager am1;
    public static String tagData = "";
    byte[] image = null;
    public static String filename;
    public static String filenameImageInsert;
    public static File pictureFile;
    public static File pictureFile1;
    public static String userOuttime;
    String SessionUser, SessionLogin, CardId,Card_id, Intime, Baseimage, CardPrice, InImage,Status="", SessionStatus,UserId,AdminId,BaseUrl;
     ;
    public static List<String> vpic = new ArrayList<>();
    String v = "";
    MarshMallowPermission marshMallowPermission;
    TimerTask tt;
    Timer t;
    private EditText viewKeyA;
    public static Timer timer;
    TimerTask timerTask;
    private IvrJackService service;
    final Handler handler = new Handler();
    private int position1 = 0;
    static List<String> vaa = new ArrayList<>();
    static List<String> c = new ArrayList<>();
    public static String v2 = "";
    String mkvm = "";
    public static double sumpriceActivity = 0;
    public static String vcardno;
    public static final String MyPREFERENCES = "MyPrefrence1";
    private List<Tag> mTags = new ArrayList<Tag>();
    //    SharedPreferences SM;
    SharedPreferences pref,Bu,im;
    SharedPreferences prefstatus;
    private NfcAdapter mAdapter;
    private PendingIntent mPendingIntent;
    String n = "";
    public static byte[] imagevalue;
    byte[]compressedData;
    Bitmap bitmap;
    Bitmap Image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        service = new IvrJackService(this, this);
//         SM = getSharedPreferences("userrecord", 0);
//        pref=getSharedPreferences("userrecord",0);
        pref = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SessionUser = pref.getString("user_name", "");
//        System.out.println("=========SessionUser================" + SessionUser);
        SessionLogin = pref.getString("login_time", "");
//        System.out.println("=========SessionLogin================" + SessionLogin);
        UserId=pref.getString("user_id","");
        AdminId=pref.getString("adminId","");

        SharedPreferences pref = getSharedPreferences("MyPrefrence2", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("card_id", idd);
        editor.commit(); // or editor.apply();

        Bu = getSharedPreferences("Base", Context.MODE_PRIVATE);
        BaseUrl = Bu.getString("baseurl", "");
//======================================================Status=======================



        dbHelper = new DatabaseHelper(SecondActivity.this);
        sQLiteHelper = new SQLiteHelper(SecondActivity.this);
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        t = new Timer();
        viewVolume = (ProgressBar) findViewById(com.stronglink.sl110.demo.R.id.volume);
        sv = new SurfaceView(getApplicationContext());
        am1 = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        mHolder = sv.getHolder();
        final Intent intent = getIntent();
        jndj = intent.getStringExtra("fname");
        if (fName.equals("")) {

        } else {
            fName = jndj.trim();
        }


        id = (TextView) findViewById(R.id.id);

        price = (TextView) findViewById(R.id.price);

        galleryimage = (ImageView) findViewById(R.id.galleryimage);
//        PhotoViewAttacher pAttacher;
//        pAttacher = new PhotoViewAttacher(galleryimage);
//        pAttacher.update();
//        galleryimage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent = new Intent(SecondActivity.this, ZoomFragment.class);
////                intent.putExtra("zoom","https://"+InImage );
////                startActivity(intent);
//
//
////                Bundle bundle = new Bundle();
////                bundle.putString("zoom",InImage );
//                try {
//                    ZoomFragment dialogFragment = new ZoomFragment();
////                dialogFragment.setArguments(bundle);
//                    FragmentManager fm = getFragmentManager();
//                    dialogFragment.show(fm, "zoom Fragment");
//                }catch (Exception e){}
//
//
//
//
//
//
//
//            }
//        });

//        galleryimage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Uri uri;
//                if(pictureFile1!=null) {
//                    uri = Uri.fromFile(pictureFile1);
//                }else {
//                    uri = Uri.fromFile(D2Fragment.pictureFile1);
//                }
//                Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
//                String mime = "*/*";
//                MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
//                if (mimeTypeMap.hasExtension(
//                        mimeTypeMap.getFileExtensionFromUrl(uri.toString())))
//                    mime = mimeTypeMap.getMimeTypeFromExtension(
//                            mimeTypeMap.getFileExtensionFromUrl(uri.toString()));
//                intent.setDataAndType(uri, mime);
//                startActivity(intent);
//
//            }
//        });
        // id.setText(fName);

//        sqLiteHelper = new SQLiteHelper(SecondActivity.this);
        run = (Button) findViewById(R.id.run);
        marshMallowPermission = new MarshMallowPermission(this);
        databaseHelper = new DatabaseHelper(SecondActivity.this);
        serch = (Button) findViewById(R.id.search);
        serch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DThreeFragment dialogFragment = new DThreeFragment();
                FragmentManager fm = getFragmentManager();
                dialogFragment.show(fm, "Sample Fragment");

            }
        });

        set = (Button) findViewById(R.id.set);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                DFragmentSet dialogFragment = new DFragmentSet();

                dialogFragment.show(fm, "Sample Fragment");

            }
        });
        capture = (Button) findViewById(R.id.capture);
        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  if ( flag==1) {*/
              try {
                  preview.camera.takePicture(shutterCallback, rawCallback, jpegCallback);
                  D2Fragment dialogFragment = new D2Fragment();

//                Bundle bundle = new Bundle();
//                bundle.putString("link", fName);
//                bundle.putByteArray("imagevalue", imagevalue);
//                dialogFragment.setArguments(bundle);
                  FragmentManager fm = getFragmentManager();
                  dialogFragment.show(fm, "Sample Fragment");

                  //=============

              }catch (Exception e){}

            }
        });
        run.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (flag == 0) {

                    preview = new CameraPreview(SecondActivity.this);
                    ((FrameLayout) findViewById(R.id.preview)).addView(preview);
                    flag = 1;
                } else if (flag == 1) {
                    preview.surfaceDestroyed(mHolder);

                    ((FrameLayout) findViewById(R.id.preview)).removeView(preview);
                    flag = 0;
                }

            }
        });

        total = (Button) findViewById(R.id.total);
        total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DfragmentTotal dialogFragment = new DfragmentTotal();
                FragmentManager fm = getFragmentManager();
                dialogFragment.show(fm, "Sample Fragment");
            }
        });

        AudioManager am1 = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        if (!am1.isWiredHeadsetOn()) {
            Log.d("con", "Plugssssssout");

            Toast.makeText(SecondActivity.this, getString(R.string.Device_is_not_connected_Please_use_manual_mode), Toast.LENGTH_SHORT).show();
        }

        boolean userlogin = pref.getBoolean("userlogin", true);
        if (userlogin == true) {

            mAdapter = NfcAdapter.getDefaultAdapter(this);
            if (mAdapter == null) {
                //nfc not support your device.
                return;
            } else {
                mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                        getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
               /* preview = new CameraPreview(SecondActivity.this);
                ((FrameLayout) findViewById(R.id.preview)).addView(preview);*/
                if (flag == 0) {

                    preview = new CameraPreview(SecondActivity.this);
                    ((FrameLayout) findViewById(R.id.preview)).addView(preview);
                    flag = 1;
                } else if (flag == 1) {
                    preview.surfaceDestroyed(mHolder);

                    ((FrameLayout) findViewById(R.id.preview)).removeView(preview);
                    flag = 0;
                }

            }
        }
//        else {
//            Intent intent1=new Intent(SecondActivity.this,Main2Activity.class);
//            startActivity(intent1);
//        }

    }

    private void showWirelessSettingsDialog() {
        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setMessage(R.string.nfc_disabled);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                startActivity(intent);
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                //  onBackPressed();
                builder.setCancelable(true);
            }
        });
        builder.create().show();
        return;
    }


    @Override
    protected void onNewIntent(Intent intent) {
        getTagInfo(intent);
    }

    private void getTagInfo(Intent intent) {
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if (tag == null) {
        } else {

            String tagInfo = "";
            String str = "";
            String str1 = "";
            String str3p = "";
            String str5c = "";
            int dec3p = 0;
            int dec5c = 0;

            byte[] tagId = tag.getId();
            StringBuilder sb = new StringBuilder();
            sb.append(toHex(tagId)).append('\n');
            str = sb.toString().replaceAll("\\s+", "").replace(" ", "").toUpperCase();

            str3p = str.substring(2, 4);
            dec3p = hex2Decimal(str3p);
            str5c = str.substring(4, 8);
            dec5c = hex2Decimal(str5c);
            StringBuilder sb1 = new StringBuilder();
            if (dec3p < 10) {
                sb1.append('0');
                sb1.append('0');
                sb1.append(hex2Decimal(str3p));
            } else if (dec3p < 100) {
                sb1.append('0');
                sb1.append(hex2Decimal(str3p));
            } else {
                sb1.append(hex2Decimal(str3p));
            }
            if (dec5c < 10) {
                sb1.append('0');
                sb1.append('0');
                sb1.append('0');
                sb1.append('0');
                sb1.append(hex2Decimal(str5c));
            } else if (dec5c < 100) {
                sb1.append('0');
                sb1.append('0');
                sb1.append('0');
                sb1.append(hex2Decimal(str5c));
            } else if (dec5c < 1000) {
                sb1.append('0');
                sb1.append('0');
                sb1.append(hex2Decimal(str5c));
            } else if (dec5c < 10000) {
                sb1.append('0');
                sb1.append(hex2Decimal(str5c));
            } else {
                sb1.append(hex2Decimal(str5c));
            }
            str1 = sb1.toString();

            tagData = str1;

//            id.setText(tagData);
            idfromreader = tagData;
//            System.out.println("=========str1=========" + str1);
            preview.camera.takePicture(shutterCallback, rawCallback, jpegCallback);
        }
    }

    private int hex2Decimal(String s) {
        String digits = "0123456789ABCDEF";
        s = s.toUpperCase();
        int val = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int d = digits.indexOf(c);
            val = 16 * val + d;
        }
        return val;
    }

    private String toReversedHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; ++i) {
            if (i > 0) {
                sb.append(" ");
            }
            int b = bytes[i] & 0xff;
            if (b < 0x10)
                sb.append('0');
            sb.append(Integer.toHexString(b));
        }
        return sb.toString();
    }

    private String getIdsHex() {
        StringBuilder builder = new StringBuilder();
        for (Tag tag : mTags) {
            builder.append(toHex(tag.getId()));
            builder.append('\n');
        }
        builder.setLength(builder.length() - 1); // Remove last new line
        return builder.toString().replace(" ", "");
    }

    private String toHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = bytes.length - 1; i >= 0; --i) {
            int b = bytes[i] & 0xff;
            if (b < 0x10)
                sb.append('0');
            sb.append(Integer.toHexString(b));
            if (i > 0) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public static void showWirelessSettingsDialog(final Context context) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setMessage(R.string.nfc_disabled);
        builder.setCancelable(false);
        builder.setTitle(R.string.disable);
        builder.setPositiveButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(
                                Settings.ACTION_WIRELESS_SETTINGS);
                        context.startActivity(intent);
                    }
                });
        builder.create().show();
    }

    public static void setComponentState(Context context, String packageName, String componentClassName, boolean enabled) {
        PackageManager pm = context.getApplicationContext().getPackageManager();
        ComponentName componentName = new ComponentName(packageName, componentClassName);
        int state = enabled ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED : PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
        pm.setComponentEnabledSetting(componentName,
                state,
                PackageManager.DONT_KILL_APP);

    }


    final private ResultCallback<DriveFolder.DriveFileResult> fileCallback = new
            ResultCallback<DriveFolder.DriveFileResult>() {
                @Override
                public void onResult(DriveFolder.DriveFileResult result) {
                    if (result.getStatus().isSuccess()) {

                        mFileId = result.getDriveFile().getDriveId();
                        SharedPreferences settings = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);

                        // Writing data to SharedPreferences
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("key", String.valueOf(mFileId));
                        editor.commit();
                        Toast.makeText(getApplicationContext(), "file created: " + "" +
                                result.getDriveFile().getDriveId(), Toast.LENGTH_LONG).show();

                    }

                    return;

                }
            };

    @Override
    public void onBackPressed() {

        boolean userlogin = pref.getBoolean("userlogin", true);
        if (userlogin = true) {
            Toast.makeText(this, getString(R.string.Please_LogOut_First), Toast.LENGTH_LONG).show();
        } else {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(a);
        }


    }

    ShutterCallback shutterCallback = new ShutterCallback() {
        public void onShutter() {


        }
    };

    /**
     * Handles data for raw picture
     */
    PictureCallback rawCallback = new PictureCallback() {
        public void onPictureTaken(byte[] data, Camera camera) {


        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_favorite) {

            AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity.this);
            builder.setMessage(R.string.dialog_message);
            builder.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
//                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//                    userOuttime = dateFormat.format(new Date());
                    String currentDateAndTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());

                    Logout(SessionUser, SessionLogin, currentDateAndTime);

//                    String cardidno = "";
//                    String price = "";
//
//                    ArrayList<ContactModel> contact = sqLiteHelper.getAllRecordsUser();
//                    for (int i = 0; i < contact.size(); i++) {
//                        cardidno=contact.get(i).getCardIdNo();
//                        price =contact.get(i).getPrice();
//                        if (contact.get(i).getUserINTime().equals(DFragmentUser.userintime)) {
//                            position = i;
//                        }
//
//                    }
//                    mAdapter = NfcAdapter.getDefaultAdapter(SecondActivity.this);
//                    if(mAdapter!=null){
//                        Log.d("con","nfc inable");
//                        ArrayList<ContactModel> contactdjn = sqLiteHelper.getAllRecordsUser4();
//                        for (int o = 0; o < contactdjn.size(); o++) {
//                            if (contactdjn.get(o).getUsserOutTime().equals("")) {
//                                position = o;
//                                break;
//                            }
//
//                        }
//
//                        ContactModel cs = contact.get(position);
//                        cs.setUsserOutTime(userOuttime);
//                        int dh=0 ;
//                        if(D2Fragment.v2.equals(""))
//                        {
//                            D2Fragment.v2= String.valueOf(0);
//                        }if(v2.equals("")){
//                            v2= String.valueOf(0);
//                        }
//                        dh= (Integer.parseInt(v2) + Integer.parseInt(D2Fragment.v2));
//                        cs.setCardIdNo(String.valueOf(dh));
//
//                       // cs.setPrice(String.valueOf(D2Fragment.sumprice));
//                          cs.setPrice(String.valueOf(sumpriceActivity+D2Fragment.sumprice));
//                        sqLiteHelper.updateRecordUser4(cs);
//                        D2Fragment.sumprice=0;
//                        D2Fragment.v2= String.valueOf(0);
//                        sumpriceActivity=0;
//                        v2= String.valueOf(0);
//
//                    }else {
//                        AudioManager am1 = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//                        if (!am1.isWiredHeadsetOn()) {
//                            Log.d("con", "audio not on");
//
//                            ArrayList<ContactModel> contactdjn = sqLiteHelper.getAllRecordsUser4();
//                            for (int o = 0; o < contactdjn.size(); o++) {
//                                if (contactdjn.get(o).getUsserOutTime().equals("")) {
//                                    position = o;
//                                    break;
//                                }
//
//                            }
//
//                            ContactModel cs = contact.get(position);
//                            cs.setUsserOutTime(userOuttime);
//                            int dh = 0;
//                            v2 = String.valueOf(0);
//                            if (D2Fragment.v2.equals("")) {
//                                D2Fragment.v2 = String.valueOf(0);
//                            }
//                            if (v2.equals("")) {
//                                v2 = String.valueOf(0);
//                            }
//                            dh = (Integer.parseInt(v2) + Integer.parseInt(D2Fragment.v2));
//                            cs.setCardIdNo(String.valueOf(dh));
//                            cs.setPrice(String.valueOf(D2Fragment.sumprice));
//                            sqLiteHelper.updateRecordUser4(cs);
//                            D2Fragment.sumprice = 0;
//                            D2Fragment.v2 = String.valueOf(0);
//                            v2 = String.valueOf(0);
//                            sumpriceActivity = 0;
//                            vaa.clear();
//                            c.clear();
//                        } else {
//                            Log.d("con", "audio enable");
//                            ArrayList<ContactModel> contactdjn = sqLiteHelper.getAllRecordsUser4();
//                            for (int o = 0; o < contactdjn.size(); o++) {
//                                if (contactdjn.get(o).getUsserOutTime().equals("")) {
//                                    position = o;
//                                    break;
//                                }
//
//                            }
//
//                            ContactModel cs = contact.get(position);
//                            cs.setUsserOutTime(userOuttime);
//                            int dh = 0;
//                            if (D2Fragment.v2.equals("")) {
//                                D2Fragment.v2 = String.valueOf(0);
//                            }
//                            if (v2.equals("")) {
//                                v2 = String.valueOf(0);
//                            }
//                            dh = (Integer.parseInt(v2) + Integer.parseInt(D2Fragment.v2));
//                            cs.setCardIdNo(String.valueOf(dh));
//
//                            //cs.setPrice(String.valueOf(D2Fragment.sumprice));
//                              cs.setPrice(String.valueOf(sumpriceActivity+D2Fragment.sumprice));
//                            sqLiteHelper.updateRecordUser4(cs);
//                            D2Fragment.sumprice = 0;
//                            D2Fragment.v2 = String.valueOf(0);
//                            sumpriceActivity = 0;
//                            v2 = String.valueOf(0);
//
//                        }
//                    }
//
//                    ContactModel c = contact.get(position);
//                    c.setUsserOutTime(userOuttime);
//
//                    sqLiteHelper.updateRecordUser2(c);
//
//
//
//                    if (ChooserActivity.timer != null) {
//                        ChooserActivity.timer.cancel();
//                        ChooserActivity.timer = null;
//                    }
//                    Intent intent= new Intent(SecondActivity.this,Main2Activity.class);
//                    startActivity(intent);
//                    finish();
//
//
                }
            });
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void Logout(final String SessionUser,
                       final String SessionLogin,
                       final String userOuttime
    ) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {

            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected String doInBackground(String... params) {
                String return_text = "";
                JSONObject jsonObject = new JSONObject();
                try {

                    jsonObject.accumulate("user_name", SessionUser);
                    jsonObject.accumulate("login_time", SessionLogin);
                    jsonObject.accumulate("logout_time", userOuttime);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(BaseUrl+"/userlogout.php");
                    StringEntity httpiEntity = new StringEntity(jsonObject.toString());
                    httpPost.setEntity(httpiEntity);
                    org.apache.http.HttpResponse response = httpClient.execute(httpPost);
                    return_text = HTTPResponse.readResponse(response);
                    return return_text;
                } catch (ClientProtocolException e) {

                } catch (IOException e) {
                }
                return return_text;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.getString("error").equalsIgnoreCase("false")) {
                        String message = jsonObject.getString("message");


                        Toast.makeText(SecondActivity.this, message, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SecondActivity.this, Main2Activity.class);
                        startActivity(intent);

                    } else if (jsonObject.getString("error").equalsIgnoreCase("true")) {
                        String message = jsonObject.getString("message");
                        if (message != null) {
                            {
//                                System.out.println("===========================error");
                                Toast.makeText(SecondActivity.this, message, Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
            sendPostReqAsyncTask.execute();
        } catch (Exception e) {
        }
    }


    /**
     * Handles data for jpeg picture
     */
    PictureCallback jpegCallback = new PictureCallback() {
        public void onPictureTaken(byte[] data, Camera camera) {
            camera.startPreview();
            imagevalue = data;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap = BitmapFactory.decodeByteArray(imagevalue , 0, imagevalue.length);
            Bitmap rihxcb=  rotateImage(90,bitmap);
            rihxcb.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] imageBytes = baos.toByteArray();
            Baseimage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
//            System.out.println("========Baseimage===="+Baseimage);
            im= getSharedPreferences("img", MODE_PRIVATE);
            SharedPreferences.Editor editor = im.edit();
            editor.putString("im", Baseimage);
            editor.commit(); // or editor.apply();

//===========================================================

            //  File pictureFileDir =getDir();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Intime = dateFormat.format(new Date());

            CardId = tagData;
//            Baseimage = convertToBase64(compressedData);
//            System.out.println("========"+Status);

            prefstatus = getSharedPreferences("Mystatus", Context.MODE_PRIVATE);
            SessionStatus = prefstatus.getString("message", "");
//            System.out.println("======SessionStatus=========" + SessionStatus);

            if (SessionStatus.equals("")&&(tagData!="")) {
                Register(SessionUser, UserId,AdminId,SessionLogin, CardId, Intime, Baseimage);
            }
             else if (SessionStatus.equals("1")&&(tagData!="")) {
                RegisterUpdate(CardId, Intime, Baseimage);

            }



//            String idd="";
//            idd=id.getText().toString();
//
//
//            String photoFile="";
//            photoFile = "aaaa"+ id.getText()+"_"+ date + ".jpg";

            // filename = pictureFileDir.getPath() + File.separator + photoFile;
           /* vpic.add(filename);
            pictureFile = new File(filename);
            if (!pictureFileDir.exists() && !pictureFileDir.mkdirs()) {
                Toast.makeText(getApplicationContext(), getString(R.string.Cantcreatedirectorytosaveimage),
                        Toast.LENGTH_LONG).show();
                return;

            }*/

//            try {
//                /*FileOutputStream fos = new FileOutputStream(pictureFile);
//
//
//                fos.write(data);
//                fos.close();*/
//                dataimage=resizeImage(data);
//                Log.d("tag",""+"value in dataimage");
//                camera.startPreview();
//
//                Log.d("tag",""+"after taking pic");
//                String mkvm="";
//                mkvm=getContact1(idfromreader, SecondActivity.price);
//                if (mkvm.equals("")){
//                    price.setText("");
//                }
//
//                image = dataimage;
//                if(!idfromreader.equals("")) {
//
//                    id.setText(idfromreader);
//                    AudioManager am1 = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//                    if(am1.isWiredHeadsetOn()){
//
//                        dbHelper.getWritableDatabase();
//                        sQLiteHelper.getWritableDatabase();
//                        ArrayList<Contact> contact = dbHelper.getContact();
//                        ArrayList<ContactModel> contact1 = sQLiteHelper.getAllRecords1(idfromreader,idfromreader);
//
//                        if(containsCardno(contact1,idfromreader)||(containscardid(contact1,idfromreader))){
//                            if ((!mkvm.equals(""))) {
//
//                                if (contact.size() == 0) {
//
//                                    File pictureFileDir1 =getDir1();
//                                    SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss");
//                                    String date1 = dateFormat1.format(new Date());
//                                    photoFile = "in_"+ idfromreader+"_"+ date1 + ".jpg";
//                                    filenameImageInsert = pictureFileDir1.getPath() + File.separator + photoFile;
//                                    pictureFile1 = new File(filenameImageInsert);
//                                    if (!pictureFileDir1.exists() && !pictureFileDir1.mkdirs()) {
//                                        Toast.makeText(getApplicationContext(), getString(R.string.Cantcreatedirectorytosaveimage),
//                                                Toast.LENGTH_LONG).show();
//                                        return;
//
//                                    }
//
//                                    try {
//                                        FileOutputStream fos1 = new FileOutputStream(pictureFile1);
//                                        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
//                                        Bitmap rihxcb=  rotateImage(90,bmp);
//                                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                                        rihxcb.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                                        byte[] flippedImageByteArray = stream.toByteArray();
//
//
//                                        fos1.write(flippedImageByteArray);
//
//                                        fos1.close();
//                                    }catch (Exception e){
//                                        e.printStackTrace();
//                                    }
//                                    Insert();
//                                    getContactimage(idfromreader, SecondActivity.galleryimage);
//                                } else {
//
//                                    boolean isNotUpdated = true;
//                                    ArrayList<Contact> contactaudio = dbHelper.getContactAccordingToCardId(idfromreader,"1",idfromreader,"1");
//                                    for (int a = 0; a < contactaudio.size(); a++) {
//                                        if ((contactaudio.get(a).getCardIdNo().equals(idfromreader)) && (contactaudio.get(a).getStatus().equalsIgnoreCase("1"))) {
//                                            File pictureFileDir1 =getDir1();
//
//                                            SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss");
//                                            String date1 = dateFormat1.format(new Date());
//                                            photoFile = "out_"+ idfromreader+"_"+ date1 + ".jpg";
//                                            filename = pictureFileDir1.getPath() + File.separator + photoFile;
//                                            pictureFile1 = new File(filename);
//                                            if (!pictureFileDir1.exists() && !pictureFileDir1.mkdirs()) {
//                                                Toast.makeText(getApplicationContext(), getString(R.string.Cantcreatedirectorytosaveimage),
//                                                        Toast.LENGTH_LONG).show();
//                                                return;
//
//                                            }
//
//                                            try {
//                                                FileOutputStream fos1 = new FileOutputStream(pictureFile1);
//                                                Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
//                                                Bitmap rihxcb=  rotateImage(90,bmp);
//                                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                                                rihxcb.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                                                byte[] flippedImageByteArray = stream.toByteArray();
//
//
//                                                fos1.write(flippedImageByteArray);
//
//                                                fos1.close();
//                                            }catch (Exception e){
//                                                e.printStackTrace();
//                                            }
//                                            update();
//                                            //getContactimage1(idfromreader, SecondActivity.galleryimage);
//                                            Log.d("if", "if");
//                                            isNotUpdated = false;
//                                            break;
//                                        } else {
//                                            continue;
//                                        }
//
//                                    }
//
//                                    if (isNotUpdated) {
//                                        Log.d("else", "else");
//                                        File pictureFileDir1 =getDir1();
//                                        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss");
//                                        String date1 = dateFormat1.format(new Date());
//                                        photoFile = "in_"+ idfromreader+"_"+ date1 + ".jpg";
//                                        filenameImageInsert = pictureFileDir1.getPath() + File.separator + photoFile;
//                                        pictureFile1 = new File(filenameImageInsert);
//                                        if (!pictureFileDir1.exists() && !pictureFileDir1.mkdirs()) {
//                                            Toast.makeText(getApplicationContext(), getString(R.string.Cantcreatedirectorytosaveimage),
//                                                    Toast.LENGTH_LONG).show();
//                                            return;
//
//                                        }
//
//                                        try {
//                                            FileOutputStream fos1 = new FileOutputStream(pictureFile1);
//                                            Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
//                                            Bitmap rihxcb=  rotateImage(90,bmp);
//                                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                                            rihxcb.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                                            byte[] flippedImageByteArray = stream.toByteArray();
//
//
//                                            fos1.write(flippedImageByteArray);
//
//                                            fos1.close();
//                                        }catch (Exception e){
//                                            e.printStackTrace();
//                                        }
//                                        Insert();
//                                        getContactimage(idfromreader, SecondActivity.galleryimage);
//                                        // break;
//                                    }
//
//
//                                }
//
//                            } else {
//                                Toast.makeText(SecondActivity.this, getString(R.string.Please_enter_cardprice_in_admin_panel), Toast.LENGTH_SHORT).show();
//
//
//                            }
//                        } else {
//                            Toast.makeText(SecondActivity.this, getString(R.string.Please_enter_valid_card_no), Toast.LENGTH_SHORT).show();
//
//                        }
//                        // }
//
//                        Log.d("***","******");
//
//
//                    }
//                    if (mAdapter!=null){
//
//                        if (!idfromreader .equals("")) {
//                            Log.d("*****","*****");
//
//                            dbHelper.getWritableDatabase();
//                            sQLiteHelper.getWritableDatabase();
//                            ArrayList<Contact> contact = dbHelper.getContact();
//                            ArrayList<ContactModel> contact1 = sQLiteHelper.getAllRecords1(idfromreader,idfromreader);
//
//
//
//                            if(containsCardno(contact1,idfromreader)||(containscardid(contact1,idfromreader))){
//                                if (!mkvm.equals("")) {
//
//                                    if (contact.size() == 0) {
//                                        File pictureFileDir1 =getDir1();
//                                        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss");
//                                        String date1 = dateFormat1.format(new Date());
//                                        photoFile = "in_"+ idfromreader+"_"+ date1 + ".jpg";
//                                        filenameImageInsert = pictureFileDir1.getPath() + File.separator + photoFile;
//                                        pictureFile1 = new File(filenameImageInsert);
//                                        if (!pictureFileDir1.exists() && !pictureFileDir1.mkdirs()) {
//                                            Toast.makeText(getApplicationContext(), getString(R.string.Cantcreatedirectorytosaveimage),
//                                                    Toast.LENGTH_LONG).show();
//                                            return;
//
//                                        }
//
//                                        try {
//                                            FileOutputStream fos1 = new FileOutputStream(pictureFile1);
//                                            Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
//                                            Bitmap rihxcb=  rotateImage(90,bmp);
//                                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                                            rihxcb.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                                            byte[] flippedImageByteArray = stream.toByteArray();
//
//
//                                            fos1.write(flippedImageByteArray);
//
//                                            fos1.close();
//                                        }catch (Exception e){
//                                            e.printStackTrace();
//                                        }
//                                        Insert();
//                                        getContactimage(idfromreader, SecondActivity.galleryimage);
//                                    } else {
//
//                                        boolean isNotUpdated = true;
//                                        ArrayList<Contact> contactwithCalue = dbHelper.getContactAccordingToCardId(idfromreader,"1",idfromreader,"1");
//                                        for (int a = 0; a < contactwithCalue.size(); a++) {
//
//                                            if ((contactwithCalue.get(a).getCardIdNo().equals(idfromreader)) && (contactwithCalue.get(a).getStatus().equalsIgnoreCase("1"))) {
//                                                File pictureFileDir1 =getDir1();
//                                                SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss");
//                                                String date1 = dateFormat1.format(new Date());
//                                                photoFile = "out_"+ idfromreader+"_"+ date1 + ".jpg";
//                                                filename = pictureFileDir1.getPath() + File.separator + photoFile;
//                                                pictureFile1 = new File(filename);
//                                                if (!pictureFileDir1.exists() && !pictureFileDir1.mkdirs()) {
//                                                    Toast.makeText(getApplicationContext(), getString(R.string.Cantcreatedirectorytosaveimage),
//                                                            Toast.LENGTH_LONG).show();
//                                                    return;
//
//                                                }
//
//                                                try {
//                                                    FileOutputStream fos1 = new FileOutputStream(pictureFile1);
//                                                    Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
//                                                    Bitmap rihxcb=  rotateImage(90,bmp);
//                                                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                                                    rihxcb.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                                                    byte[] flippedImageByteArray = stream.toByteArray();
//
//
//                                                    fos1.write(flippedImageByteArray);
//
//                                                    fos1.close();
//                                                }catch (Exception e){
//                                                    e.printStackTrace();
//                                                }
//                                                update();
//                                                // getContactimage1(idfromreader, SecondActivity.galleryimage);
//
//                                                isNotUpdated = false;
//                                                break;
//                                            } else {
//                                                continue;
//                                            }
//
//                                        }
//
//                                        if (isNotUpdated) {
//
//                                            File pictureFileDir1 =getDir1();
//                                            SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss");
//                                            String date1 = dateFormat1.format(new Date());
//                                            photoFile = "in_"+ idfromreader+"_"+ date1 + ".jpg";
//                                            filenameImageInsert = pictureFileDir1.getPath() + File.separator + photoFile;
//                                            pictureFile1 = new File(filenameImageInsert);
//                                            if (!pictureFileDir1.exists() && !pictureFileDir1.mkdirs()) {
//                                                Toast.makeText(getApplicationContext(), getString(R.string.Cantcreatedirectorytosaveimage),
//                                                        Toast.LENGTH_LONG).show();
//                                                return;
//
//                                            }
//
//                                            try {
//                                                FileOutputStream fos1 = new FileOutputStream(pictureFile1);
//                                                Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
//                                                Bitmap rihxcb=  rotateImage(90,bmp);
//                                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                                                rihxcb.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                                                byte[] flippedImageByteArray = stream.toByteArray();
//
//
//                                                fos1.write(flippedImageByteArray);
//
//                                                fos1.close();
//                                            }catch (Exception e){
//                                                e.printStackTrace();
//                                            }
//                                            Insert();
//                                            getContactimage(idfromreader, SecondActivity.galleryimage);
//                                            // break;
//                                        }
//
//
//                                    }
//
//                                } else {
//                                    Toast.makeText(SecondActivity.this, getString(R.string.Please_Create_Card_Type_in_Admin_Panel), Toast.LENGTH_LONG).show();
//
//                                    //Toast.makeText(SecondActivity.this, "Please enter valid card no ", Toast.LENGTH_SHORT).show();
//                                }
//                            }else {
//
//                                Toast.makeText(SecondActivity.this, "Please enter valid card no", Toast.LENGTH_SHORT).show();
//                            }
//                            //}
//                        }
//
//                    }
//
//                    idfromreader="";
//                    cardprice="";
//                    mkvm="";
//                }else {
//
//                }
//
//            } catch ( Exception error) {
//                //Toast.makeText(getApplicationContext(),getString(R.string.Image_could_not_be_saved),
//                //   Toast.LENGTH_LONG).show();
//            }
        }

        protected String convertToBase64(byte[] bitmapByte) {


            String encodedImage = android.util.Base64.encodeToString(bitmapByte, Base64.DEFAULT);

            return encodedImage;
        }


        private File getDir() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String date1 = dateFormat.format(new Date());
            File sdDir = Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            return new File(sdDir, "Images/" + date1);

        }

        private File getDir1() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String date1 = dateFormat.format(new Date());
            File sdDir = Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            return new File(sdDir, "Images/" + date1);

        }

    };

    boolean containsCardno(ArrayList<ContactModel> contact1, String name) {
        for (ContactModel item : contact1) {
            if (item.getCardNo().equals(name)) {
                return true;
            }
        }
        return false;
    }

    boolean containscardid(ArrayList<ContactModel> contact1, String name) {
        for (ContactModel item : contact1) {
            if (item.getCardId().equals(name)) {
                return true;
            }
        }
        return false;
    }

    boolean containsuserIntime(ArrayList<ContactModel> contact1, String name) {
        for (ContactModel item : contact1) {
            if (item.getUserINTime().equals(name)) {
                return true;
            }
        }
        return false;
    }

    byte[] resizeImage(byte[] input) {
        Bitmap original = BitmapFactory.decodeByteArray(input, 0, input.length);
        Bitmap resized = Bitmap.createScaledBitmap(original, original.getWidth(), original.getHeight(), true);

        ByteArrayOutputStream blob = new ByteArrayOutputStream();
        resized.compress(Bitmap.CompressFormat.JPEG, 40, blob);

        return blob.toByteArray();
    }

    public Bitmap rotateImage(int angle, Bitmap bitmapSrc) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(bitmapSrc, 0, 0,
                bitmapSrc.getWidth(), bitmapSrc.getHeight(), matrix, true);
    }

    public void getContactimage(String jndj, android.widget.ImageView galleryimage) {
        dbHelper = new DatabaseHelper(SecondActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Contact> contacts = dbHelper.getContactwithCardId(jndj);
        int Id = 0;

        for (int k = 0; k < contacts.size(); k++) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
            String date = dateFormat.format(new Date());
            if (jndj.equals(contacts.get(k).getCardIdNo())) {

                try {
                    Bitmap b = BitmapFactory.decodeStream(new FileInputStream(filenameImageInsert));
                    int width = b.getWidth();
                    int height = b.getHeight();
                    int bounding = dpToPx(SecondActivity.this, 350);
                    float xScale = ((float) bounding) / width;
                    float yScale = ((float) bounding) / height;
                    float scale = (xScale <= yScale) ? xScale : yScale;
                  /*  Matrix matrix = new Matrix();
                    matrix.postScale(scale, scale);
                    matrix.postRotate(90);
                    Bitmap scaledBitmap = Bitmap.createBitmap(b, 0, 0, width, height, matrix, true);*/

//                    galleryimage.setImageBitmap(b);


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }


    }
//    public void getContactimage1(String jndj, android.widget.ImageView galleryimage) {
//        dbHelper = new DatabaseHelper(SecondActivity.this);
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        byte[]img=SecondActivity.dataimage;
//        ArrayList<Contact> contacts =dbHelper. getContact();
//        int Id = 0;
//
//        for (int k = 0; k < contacts.size(); k++) {
//
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
//            String date = dateFormat.format(new Date());
//            for (int i = 0; i < contacts.size(); i++) {
//
//                if (((contacts.get(i).getCardNo().equals(jndj))||(contacts.get(i).getCardNo().equals(jndj)))&&(contacts.get(i).getStatus().equals("1"))){
//                    //  String t =contact.get(i).getInTime();
//                    position = i;
//
//                }
//
//            }
//
//            byte[]cm=contacts.get(position).getInImage();
//            Bitmap b = BitmapFactory.decodeByteArray(cm,0,cm.length);
//            int width = b.getWidth();
//            int height = b.getHeight();
//            int bounding = dpToPx(SecondActivity.this,350);
//            float xScale = ((float) bounding) / width;
//            float yScale = ((float) bounding) / height;
//            float scale = (xScale <= yScale) ? xScale : yScale;
//            Matrix matrix = new Matrix();
//            matrix.postScale(scale, scale);
//            matrix.postRotate(90);
//            Bitmap scaledBitmap = Bitmap.createBitmap(b, 0, 0, width, height, matrix, true);

//            galleryimage.setImageBitmap(scaledBitmap);


//        }


//    }
//    public String getContact1(String editText, android.widget.TextView price) {
//
//        sQLiteHelper = new SQLiteHelper(SecondActivity.this);
//        //String[] str = textStr;
//
//
//
//        ArrayList<ContactModel> contacts = sQLiteHelper.getAllRecords();
//
//
//        for (int r = 0; r < contacts.size(); r++) {
//
//            if (editText.equals(contacts.get(r).getCardId())||(editText.equals(contacts.get(r).getCardNo()))) {
//                hc = contacts.get(r).getCardType();
//
//            }
//        }
//
//        ArrayList<ContactModel> contacts2 = sQLiteHelper.getAllRecordsAccordingToPrice(hc);
//        for(int d=0; d<contacts2.size();d++) {
//
//            if(hc.equals(contacts2.get(d).getCardType())){
//                cardprice=contacts2.get(d).getPrice();
//
//                price.setText(cardprice);
//
//            }
//
//
//        }
//
//        return cardprice;
//
//
//    }
//
//    public void Insert() {
//
//
//        try {
//            ArrayList<ContactModel> contact2 = sQLiteHelper.getAllRecordsUser();
//            for(int z = 0; z < contact2.size(); z++) {
//                v = contact2.get(z).getUserName();
//            }
//            ArrayList<ContactModel> contact1 = sQLiteHelper.getAllRecords();
//            for (int i = 0; i < contact1.size(); i++) {
//                if((idfromreader.equals(contact1.get(i).getCardId()))||(idfromreader.equals(contact1.get(i).getCardNo()))) {
//                    numb = contact1.get(i).getCardNo();
//
//                }
//            }
//
//
//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//            String string = dateFormat.format(new Date());
//
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.blankimage);
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.JPEG,40 , bos);
//            byte[] bitmapdata = bos.toByteArray();
//
//            dbHelper.inserRecord(new Contact(idfromreader, cardprice, image, bitmapdata, "1", string,"",numb,v,filenameImageInsert,filename));
//            ArrayList<ContactModel> contact11 = sQLiteHelper.getAllRecordsUser4();
//            if (contact11.size() == 0) {
//
//                ContactModel contact12 = new ContactModel();
//                contact12.setUserName(v);
//                contact12.setUserINTime(DFragmentUser.userintime);
//                List<String>arr=new ArrayList<>();
//                arr.add(idfromreader);
//                String no=String.valueOf(arr.size());
//                contact12.setCardIdNo(no);
//                contact12.setPrice(cardprice);
//                contact12.setUsserOutTime("");
//                vaa.add(cardprice);
//                c.add(idfromreader);
//
//
//                sQLiteHelper.insertRecordUser4(contact12);
//                vaa.clear();
//                c.clear();
//                D2Fragment.sumprice=0;
//                D2Fragment.v2= String.valueOf(0);
//                vaa.add(cardprice);
//                c.add(idfromreader);
//
//                v2 = String.valueOf(c.size());
//
//                double sum =0;
//                for (int p = 0; p < vaa.size(); p++) {
//                    sum +=  (Double.valueOf(vaa.get(p)));
//
//
//                }
//
//                sumpriceActivity=sum;
//
//            } else {
//
//                boolean isNotUpdated = true;
//                ArrayList<ContactModel> contact111 = sQLiteHelper.getAllRecordsUser4AccordingToUserInTime(DFragmentUser.userintime,"");
//                for (int a = 0; a < contact111.size(); a++) {
//                    String x = contact111.get(a).getUserINTime();
//                    String y =contact111.get(a).getUsserOutTime();
//
//                    if ((DFragmentUser.userintime.equals(x))&&y.equals("")) {
//                        position1 = a;
//
//
//                        c.add(idfromreader);
//                        vaa.add(cardprice);
//
//                        v2 = String.valueOf(c.size());
//
//                        double sum =0;
//                        for (int p = 0; p < vaa.size(); p++) {
//                            sum +=  (Double.valueOf(vaa.get(p)));
//                            Log.i("kkd", (Double.valueOf(vaa.get(p))) + ""+sum);
//
//                        }
//
//                        sumpriceActivity=sum;
//                        ContactModel c = contact11.get(position1);
//                        int dh=0 ;
//                        if(D2Fragment.v2.equals(""))
//                        {
//                            D2Fragment.v2= String.valueOf(0);
//                        }if(v2.equals("")){
//                            v2= String.valueOf(0);
//                        }
//                        dh= (Integer.parseInt(v2) + Integer.parseInt(D2Fragment.v2));
//                        c.setCardIdNo(String.valueOf(dh));
//
//                        c.setPrice(String.valueOf(sumpriceActivity+D2Fragment.sumprice));
//                        c.setUsserOutTime("");
//                        sQLiteHelper.updateRecordUser4(c);
//
//                        isNotUpdated = false;
//
//                        break;
//                    } else {
//                        Log.d("else part","working");
//                        continue;
//                    }
//
//                }
//
//                if (isNotUpdated) {
//                    Log.d("else", "else");
//                    ContactModel contact12 = new ContactModel();
//                    contact12.setUserName(v);
//                    contact12.setUserINTime(DFragmentUser.userintime);
//                    List<String>arr=new ArrayList<>();
//                    arr.add(idfromreader);
//                    String no=String.valueOf(arr.size());
//                    contact12.setCardIdNo(no);
//                    contact12.setPrice(cardprice);
//                    contact12.setUsserOutTime("");
//                    vaa.add(cardprice);
//                    c.add(idfromreader);
//
//                    sQLiteHelper.insertRecordUser4(contact12);
//                    vaa.clear();
//                    c.clear();
//                    D2Fragment.sumprice=0;
//                    D2Fragment.v2= String.valueOf(0);
//                    vaa.add(cardprice);
//                    c.add(idfromreader);
//
//                    v2 = String.valueOf(c.size());
//
//                    double sum =0;
//                    for (int p = 0; p < vaa.size(); p++) {
//                        sum +=  (Double.valueOf(vaa.get(p)));
//                        Log.i("kkd", (Double.valueOf(vaa.get(p))) + ""+sum);
//
//                    }
//
//                    sumpriceActivity=sum;
//                }
//
//
//            }
//
//            ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
//            toneG.startTone(ToneGenerator.TONE_CDMA_ABBR_ALERT, 200);
//
//            Toast.makeText(SecondActivity.this, getString(R.string.Please_Come_in), Toast.LENGTH_LONG).show();
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//        }
//    }
//
//    public void update() {
//        dbHelper = new DatabaseHelper(SecondActivity.this);
//        dbHelper.getWritableDatabase();
//        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//        String string1  = dateFormat1.format(new Date());
//
//
//        ArrayList<Contact> contact = dbHelper.getContactAccordingToCardId(idfromreader,"1",idfromreader,"1");
//        for (int i = 0; i < contact.size(); i++) {
//            position=i;
//
//           /* if ((contact.get(i).getCardIdNo().equals(idfromreader))&&(contact.get(i).getStatus().equals("1"))) {
//
//                position = i;
//
//            }*/
//
//        }
//
//        Contact c = contact.get(position);
//
//        String INImagePATH=c.getIn_ImagePath();
//        byte[]cm=contact.get(position).getInImage();
//        c.setCardIdNo(idfromreader);
//        c.setOutImage(image);
//        c.setOutTime(string1);
//        c.setStatus("0");
//        c.setIn_ImagePath(INImagePATH);
//        c.setOut_ImagePath(filename);
//        dbHelper.updateContact(c);
//
//        Log.d("Tag","ggggg "+filename);
//        Bitmap b = BitmapFactory.decodeByteArray(cm,0,cm.length);
//        int width = b.getWidth();
//        int height = b.getHeight();
//        int bounding = dpToPx(getApplicationContext(),350);
//        float xScale = ((float) bounding) / width;
//        float yScale = ((float) bounding) / height;
//        float scale =     (xScale <= yScale) ? xScale : yScale;
//        Matrix matrix = new Matrix();
//        matrix.postScale(scale, scale);
//        matrix.postRotate(90);
//        Bitmap scaledBitmap = Bitmap.createBitmap(b, 0, 0, width, height, matrix, true);
//
////        galleryimage.setImageBitmap(scaledBitmap);
//        ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
//        toneG.startTone(ToneGenerator.TONE_CDMA_ABBR_ALERT, 200);
//
//
//        Toast.makeText(SecondActivity.this, getString(R.string.Please_go_out), Toast.LENGTH_LONG).show();
//    }

    @Override
    public void onConnect(String deviceSn) {
        System.out.println("on connect");
        Toast.makeText(SecondActivity.this, getString(R.string.Device_Connected_Starting_Auto_mode), Toast.LENGTH_LONG).show();
        if (flag == 0) {

            preview = new CameraPreview(SecondActivity.this);
            ((FrameLayout) findViewById(R.id.preview)).addView(preview);
            flag = 1;
        } else if (flag == 1) {
            preview.surfaceDestroyed(mHolder);

            ((FrameLayout) findViewById(R.id.preview)).removeView(preview);
            flag = 0;
        }


        timer = new Timer();
        //initialize the TimerTask's job

        initializeTimerTask();

        //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms

        timer.schedule(timerTask, 300, 6000); //
    }

    @Override
    public void onDisconnect() {

        System.out.println("on disconnect");
    }

    @Override
    public void onStatusChange(IvrJackStatus status) {
        System.out.println("on status change: " + status);
        switch (status) {
            case ijsDetecting:
                Log.d("cccccaon", "isDetecting");

               /* AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                viewVolume.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
                viewVolume.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
                if (viewVolume.getMax() != viewVolume.getProgress()) {
                    new android.app.AlertDialog.Builder(this)
                            .setTitle("Please set the volume to maximum!")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();

                }*/
                break;
            case ijsRecognized:
                Log.d("cccccaon", "isRecognized");

                break;
            case ijsUnRecognized:
                Log.d("cccccaon", "isUnRecognized");
                // Toast.makeText(SecondActivity.this," please use manual mode",Toast.LENGTH_LONG);
                break;
            case ijsPlugout:
                Log.d("cccccaon", "Plugout");
                //  Toast.makeText(SecondActivity.this,"Device Plugout please use manual mode",Toast.LENGTH_LONG).show();

                Log.d("tresult", "Plogout");

                break;
        }
    }

    public void initializeTimerTask() {

        timerTask = new TimerTask() {
            public void run() {

                //use a handler to run a toast that shows the current timestamp
                handler.post(new Runnable() {
                    public void run() {

                        final byte[] blockNo = getBlockNo();
                        if (blockNo == null) {
                            // Toast.makeText(this, "Wrong BlockNo Format", Toast.LENGTH_LONG).show();
                            return;
                        }

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

                                    byte[] tag = tagUid.uid;
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(toHex(tag)).append('\n');
                                    String strdd = sb.toString().replaceAll("\\s+", "").replace(" ", "").toUpperCase();

                                    String str3p = "";
                                    String str5c = "";
                                    String str1 = "";
                                    int dec3p = 0;
                                    int dec5c = 0;
                                    str3p = strdd.substring(2, 4);
                                    dec3p = hex2Decimal(str3p);
                                    str5c = strdd.substring(4, 8);
                                    dec5c = hex2Decimal(str5c);
                                    StringBuilder sb1 = new StringBuilder();
                                    if (dec3p < 10) {
                                        sb1.append('0');
                                        sb1.append('0');
                                        sb1.append(hex2Decimal(str3p));
                                    } else if (dec3p < 100) {
                                        sb1.append('0');
                                        sb1.append(hex2Decimal(str3p));
                                    } else {
                                        sb1.append(hex2Decimal(str3p));
                                    }
                                    if (dec5c < 10) {
                                        sb1.append('0');
                                        sb1.append('0');
                                        sb1.append('0');
                                        sb1.append('0');
                                        sb1.append(hex2Decimal(str5c));
                                    } else if (dec5c < 100) {
                                        sb1.append('0');
                                        sb1.append('0');
                                        sb1.append('0');
                                        sb1.append(hex2Decimal(str5c));
                                    } else if (dec5c < 1000) {
                                        sb1.append('0');
                                        sb1.append('0');
                                        sb1.append(hex2Decimal(str5c));
                                    } else if (dec5c < 10000) {
                                        sb1.append('0');
                                        sb1.append(hex2Decimal(str5c));
                                    } else {
                                        sb1.append(hex2Decimal(str5c));
                                    }
                                    str1 = sb1.toString();

                                    tagData = str1;

                                    //tagData=strdd;

                                    if (!tagData.equals("")) {


                                        idfromreader = tagData;

                                        preview.camera.takePicture(shutterCallback, rawCallback, jpegCallback);                                      //  Intent intent = new Intent(ChooserActivity.this, SecondActivity.class);
                                        break;
                                    }
                                    ;
//
                                } while (false);


                            }
                        }.start();


                    }
                });
            }
        };
    }

    public void Register(final String SessionUser,
                         final String UserId,
                         final String AdminId,
                         final String SessionLogin,
                         final String CardId,
                         final String In_time,
                         final String Image

    ) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {

            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected String doInBackground(String... params) {
                String return_text = "";
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.accumulate("user_name", SessionUser);
                    jsonObject.accumulate("user_id", UserId);
                    jsonObject.accumulate("admin_id", AdminId);
                    jsonObject.accumulate("login_time", SessionLogin);
                    jsonObject.accumulate("card_id", CardId);
                    jsonObject.accumulate("in_time", In_time);
                    jsonObject.accumulate("in_image", Image);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(BaseUrl+"/transaction_create_auto.php");
                    StringEntity httpiEntity = new StringEntity(jsonObject.toString());
                    httpPost.setEntity(httpiEntity);
                    org.apache.http.HttpResponse response = httpClient.execute(httpPost);
                    return_text = HTTPResponse.readResponse(response);
                    return return_text;
                } catch (ClientProtocolException e) {

                } catch (IOException e) {
                }
                return return_text;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.getString("error").equalsIgnoreCase("false")) {

                        JSONArray message = jsonObject.getJSONArray("message");
//                        String sucess = jsonObject.getString("messsage1");
//                        Toast.makeText(SecondActivity.this, sucess, Toast.LENGTH_LONG).show();

                        if (message != null) {

                            for (int i = 0; i < message.length(); i++) {
                                JSONObject m = message.getJSONObject(i);
                                Card_id=m.getString("card_id");
                                id.setText(Card_id);
                                CardPrice = m.getString("card_price");

                                price.setText(CardPrice);
                                InImage = m.getString("in_image");
                                System.out.println("======InImage======="+InImage);
                                String  Status = m.getString("status");
                                prefstatus = getSharedPreferences("Mystatus", MODE_PRIVATE);
                                SharedPreferences.Editor editor = prefstatus.edit();
                                editor.putString("message", Status);
                                editor.commit(); // or editor.apply();
                                Picasso.with(getApplicationContext()).load(img+InImage).into(galleryimage);
//                                try
//                                {
//                                    Picasso.with(SecondActivity.this)
//                                            .load("https://"+InImage)
//                                            .into(galleryimage);
//
//                            }catch (Exception e){}
                            }

//                            System.out.println("===message====" + sucess);
                            Toast.makeText(SecondActivity.this, "Please Come In", Toast.LENGTH_SHORT).show();


                        }
                    } else if (jsonObject.getString("error").equalsIgnoreCase("true")) {
                        String message = jsonObject.getString("messsage");
                        if (message != null) {
                            {
//                                System.out.println("===========================error");
                                Toast.makeText(SecondActivity.this, message, Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
            sendPostReqAsyncTask.execute();
        } catch (Exception e) {
        }
    }

    //---------------------------------------------------------- transaction update---------------------------------------
    public void RegisterUpdate(

            final String CardId,
            final String In_time,
            final String Image

    ) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {

            protected void onPreExecute() {
                super.onPreExecute();
                prefstatus = getSharedPreferences("Mystatus", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefstatus.edit();
                editor.clear();
                editor.commit();


            }

            @Override
            protected String doInBackground(String... params) {
                String return_text = "";
                JSONObject jsonObject = new JSONObject();
                try {

                    jsonObject.accumulate("card_id", CardId);
                    jsonObject.accumulate("out_time", In_time);
                    jsonObject.accumulate("out_image", Image);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(BaseUrl+"/transaction_update_auto.php");
                    StringEntity httpiEntity = new StringEntity(jsonObject.toString());
                    httpPost.setEntity(httpiEntity);
                    org.apache.http.HttpResponse response = httpClient.execute(httpPost);
                    return_text = HTTPResponse.readResponse(response);
                    return return_text;
                } catch (ClientProtocolException e) {

                } catch (IOException e) {
                }
                return return_text;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.getString("error").equalsIgnoreCase("false")) {

                        JSONArray message = jsonObject.getJSONArray("message");
//                        String sucess = jsonObject.getString("messsage1");
//                        Toast.makeText(SecondActivity.this, sucess, Toast.LENGTH_LONG).show();
                        Toast.makeText(SecondActivity.this,"Please Go Out",Toast.LENGTH_SHORT).show();

                        if (message != null) {

                            for (int i = 0; i < message.length(); i++) {
                                JSONObject m = message.getJSONObject(i);
                                CardPrice = m.getString("card_price");
                                InImage = m.getString("in_image");
                               String Status = m.getString("status");

                                Picasso.with(getApplicationContext()).load(img+InImage).into(galleryimage);
//                                Glide.with(mContext).load(img+InImage)
//                                        .thumbnail(0.5f)
//                                        .crossFade()
//                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
//                                        .into(galleryimage);

//                                Picasso.with(SecondActivity.this)
//                                        .load("https://"+InImage)
//                                        .into(galleryimage);




                            }

//                            System.out.println("===message====" + sucess);



                        }
                    } else if (jsonObject.getString("error").equalsIgnoreCase("true")) {
                        String message = jsonObject.getString("messsage");
                        if (message != null) {
                            {
//                                System.out.println("===========================error");
                                Toast.makeText(SecondActivity.this, message, Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
            sendPostReqAsyncTask.execute();
        } catch (Exception e) {
        }
    }


    private byte[] getBlockNo() {
        byte[] blockNo = new byte[1];
        try {
            //    blockNo[0] = (byte)(Integer.parseInt(viewBlockNo.getText().toString()));
        } catch (NumberFormatException e) {
            return null;
        }
        return blockNo;
    }

    private byte[] hex2bytes(String hex) {
        hex = hex.replace(" ", "");
        if (hex.length() % 2 != 0) {
            return null;
        }
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            try {
                bytes[i] = (byte) Integer.parseInt(hex.substring(i * 2, i * 2 + 2), 16);
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


    @Override
    protected void onPause() {
        super.onPause();
        service.close();
        Log.d("cccccaon", "onPausse");
        System.out.println("close");
        if (mAdapter != null) {
            mAdapter.disableForegroundDispatch(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("cccccaon", "onDestroy");
    }

    @Override
    protected void onResume() {
        super.onResume();
        service.open();
        if (mAdapter != null) {
            if (!mAdapter.isEnabled()) {
                showWirelessSettingsDialog();
            }
            mAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient != null) {

            // disconnect Google API client connection
            mGoogleApiClient.disconnect();
        }
        super.onPause();
    }


}




