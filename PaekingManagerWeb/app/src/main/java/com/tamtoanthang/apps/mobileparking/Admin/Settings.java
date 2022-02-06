package com.tamtoanthang.apps.mobileparking.Admin;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.tamtoanthang.apps.mobileparking.DataBase.DatabaseHelper;
import com.tamtoanthang.apps.mobileparking.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.OpenFileActivityBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;

public class Settings extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener {
    private GoogleApiClient mGoogleApiClient;
    private final String dataPath = "//data//nguyenvanquan7826.com.tut.demodatabase//databases//";
    private final String dataName = DatabaseHelper.DATABASE_NAME;

    // data main
    private final String data = dataPath + dataName;

    // name of temp data
    private final String dataTempName = DatabaseHelper.DATABASE_NAME + "_temp";

    // temp data for copy data from sd then copy data temp into main data
    private final String dataTemp = dataPath + dataTempName;

    // folder on sd to backup data
    private final String folderSD = Environment.getExternalStorageDirectory() + "/MyNote";

    private boolean fileOperation = false;
    private static final int REQUEST_CODE_RESOLUTION = 1;
    private static final  int REQUEST_CODE_OPENER = 2;
    private static final String TAG = "CameraDemo";
    public static Timer timer;
    TimerTask timerTask;
    public static DriveId mFileId;
    final Handler handler = new Handler();
    TextView textView;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static    File directory;
    private Context context;

     DatabaseHelper db;
    public Settings(Context context) {
        this.context = context;
    }
    public Settings()
    {

    }

    // create folder if it not exist
    private void createFolder() {
        File sd = new File(folderSD);
        if (!sd.exists()) {
            sd.mkdir();
            System.out.println("create folder");
        } else {
            System.out.println("exits");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        context = this;

        db = new DatabaseHelper(context);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Drive.API)
                .addScope(Drive.SCOPE_FILE)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        Boolean isChecked=sharedPreferences.getBoolean("state",false);
          textView=(TextView)findViewById(R.id.backuptext);
          textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             // DatabaseHelper.copyDatabase();
              String databasePath=context.getFilesDir().getParent()+"/databases/"+dataPath;
                Log.d("pathString",databasePath);
                File f = new File(databasePath);

                Log.d("vk",""+databasePath);
                OutputStream myOutput = null;
                InputStream myInput = null;
                Log.d("testing", " testing db path " + databasePath);
                Log.d("testing", " testing db exist " + f.exists());

                if (f.exists()) {
                    try {

                        directory = new File("/mnt/sdcard/DB_DEBUG");
                        if (!directory.exists())
                            directory.mkdir();

                        myInput = new FileInputStream(directory.getAbsolutePath()
                                + "/" + "databaseCustomer.db");
                        myOutput = new FileOutputStream(databasePath);

                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = myInput.read(buffer)) > 0) {
                            myOutput.write(buffer, 0, length);
                        }

                        myOutput.flush();
                    } catch (Exception e) {
                    } finally {
                        try {
                            if (myOutput != null) {
                                myOutput.close();
                                myOutput = null;
                            }
                            if (myInput != null) {
                                myInput.close();
                                myInput = null;
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            }
          });
        final ToggleButton tB = (ToggleButton) findViewById(R.id.toggleButton);
        if(isChecked){
            tB.setChecked(true);
        }else {
            tB.setChecked(false);
        }
        tB.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View arg0) {
                SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if(tB.isChecked()){

                    editor.putBoolean("state", true);

                    Log.d("*","******");

                    timer = new Timer();
                    //initialize the TimerTask's job

                    newinitializeTimerTask();

                    //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
                    timer.schedule(timerTask, 100, 10000);
                }
                else if(!tB.isChecked()){
                    editor.putBoolean("state", false);
                    if (Settings.timer != null) {
                        Settings.timer.cancel();
                        Settings.timer = null;

                    }
                }
                editor.commit();
                //Button is OFF
                // Do Something
            }
        });    }
    public void onClickCreateFile(){

        fileOperation = true;

        // create new contents resource

        Drive.DriveApi.newDriveContents(mGoogleApiClient)
                .setResultCallback(driveContentsCallback);

    }
   /* public static void copyDatabase( String DATABASE_NAME) {
       static String databasePath = c.getDatabasePath(DATABASE_NAME).getPath();
        File f = new File(databasePath);
        Log.d("vk",""+databasePath);
        OutputStream myOutput = null;
        InputStream myInput = null;
        Log.d("testing", " testing db path " + databasePath);
        Log.d("testing", " testing db exist " + f.exists());

        if (f.exists()) {
            try {

                directory = new File("/mnt/sdcard/DB_DEBUG");
                if (!directory.exists())
                    directory.mkdir();

                myInput   = new FileInputStream(directory.getAbsolutePath()
                        + "/" + DATABASE_NAME);
                myOutput = new FileOutputStream(databasePath);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }

                myOutput.flush();
            } catch (Exception e) {
            } finally {
                try {
                    if (myOutput != null) {
                        myOutput.close();
                        myOutput = null;
                    }
                    if (myInput != null) {
                        myInput.close();
                        myInput = null;
                    }
                } catch (Exception e) {
                }
            }
        }
    }*/
    public void onBackPressed() {
        super.onBackPressed();
    }
    public void newinitializeTimerTask() {

        timerTask = new TimerTask() {
            public void run() {
                Log.d("*","******b");
                //use a handler to run a toast that shows the current timestamp
                handler.post(new Runnable() {
                    public void run() {

                        new Thread() {
                            @Override
                            public void run() {
                                String info;

                                onClickCreateFile();


                            }
                        }.start();


                    }
                });
            }
        };
    }
    final ResultCallback<DriveApi.DriveContentsResult> driveContentsCallback =
            new ResultCallback<DriveApi.DriveContentsResult>() {
                @Override
                public void onResult(DriveApi.DriveContentsResult result) {

                    if (result.getStatus().isSuccess()) {

                        if (fileOperation == true) {

                            CreateFileOnGoogleDrive(result);

                        }
                    }

                }
            };
    protected void onActivityResult(final int requestCode,
                                    final int resultCode, final Intent data) {
        switch (requestCode) {

            case REQUEST_CODE_OPENER:

                if (resultCode == RESULT_OK) {

                    mFileId = (DriveId) data.getParcelableExtra(
                            OpenFileActivityBuilder.EXTRA_RESPONSE_DRIVE_ID);


                    Log.e("file id", mFileId.getResourceId() + "");

                    String url = "https://drive.google.com/open?id="+ mFileId.getResourceId();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }

                break;

            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }
    public void CreateFileOnGoogleDrive(DriveApi.DriveContentsResult result){

        final DriveContents driveContents = result.getDriveContents();

        // Perform I/O off the UI thread.
        new Thread() {
            @Override
            public void run() {
                Log.d("*","******c");
                // write content to DriveContents
                File file = new File("/mnt/sdcard/DB_DEBUG");
                String FILENAME = file.getAbsolutePath()
                        + "/" + "databaseCustomer.db";
                try {
                    byte[] s=readFile(FILENAME);
//                    OutputStream outputStream = driveContents.getOutputStream();
//                    Writer writer = new OutputStreamWriter(outputStream);
//                    writer.write(s);
//                    writer.close();
                    // String fileId = result.getDriveFile().getDriveId().getResourceId();
                    ParcelFileDescriptor parcelFileDescriptor = driveContents.getParcelFileDescriptor();
                    FileInputStream fileInputStream = new FileInputStream(parcelFileDescriptor
                            .getFileDescriptor());
                    // Read to the end of the file.
                    fileInputStream.read(new byte[fileInputStream.available()]);

                    // Append to the file.
                    FileOutputStream fileOutputStream = new FileOutputStream(parcelFileDescriptor
                            .getFileDescriptor());
//                    Writer writer = new OutputStreamWriter(fileOutputStream);
//                    writer.
                    fileOutputStream.write(s);


                } catch (IOException e) {
                    e.printStackTrace();
                }
                MetadataChangeSet changeSet = new MetadataChangeSet.Builder()
                        .setTitle("Backup123")
                        .setMimeType("text/plain")

                        .setStarred(true).build();

                // create a file in root folder

                if(mFileId!=null) {

                    Log.d("hjbhbju", "" + "if");
                    DriveFile driveFile = Drive.DriveApi.getFile(mGoogleApiClient,
                            DriveId.decodeFromString(String.valueOf(mFileId)));
// Call to delete file.
                    driveFile.delete(mGoogleApiClient);
                }else {
                    SharedPreferences settings = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                    String value = settings.getString("key", "");
                    Log.d("hjbhbju", "" + "else");
                    if (!value.equals("")) {

                        Log.d("hjbhbju", "" + value);
                        DriveFile driveFile = Drive.DriveApi.getFile(mGoogleApiClient,
                                DriveId.decodeFromString(String.valueOf(value)));
// Call to delete file.
                        driveFile.delete(mGoogleApiClient);
                    }
                }
                Drive.DriveApi.getRootFolder(mGoogleApiClient)
                        .createFile(mGoogleApiClient, changeSet, driveContents)
                        .setResultCallback(fileCallback);

                // driveContents.commit(mGoogleApiClient, null).await();
            }
        }.start();
    }
    final private ResultCallback<DriveFolder.DriveFileResult> fileCallback = new
            ResultCallback<DriveFolder.DriveFileResult>() {
                @Override
                public void onResult(DriveFolder.DriveFileResult result) {
                    if (result.getStatus().isSuccess()) {

                        mFileId=result.getDriveFile().getDriveId();
                        SharedPreferences settings = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);

                        // Writing data to SharedPreferences
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("key", String.valueOf(mFileId));
                        editor.commit();
                      /*  Toast.makeText(getApplicationContext(), "file created: "+""+
                                result.getDriveFile().getDriveId(), Toast.LENGTH_LONG).show();*/

                    }

                    return;

                }
            };
    byte[] readFile(String fileName) throws IOException {
        FileInputStream br = new FileInputStream(new File(fileName));
        byte[] data;
        try {
            StringBuilder sb = new StringBuilder();
            data=new byte[br.available()];
            br.read(data);

        } finally {
            br.close();
        }

        return data;
    }
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Toast.makeText(getApplicationContext(), getString(R.string.Connected), Toast.LENGTH_LONG).show();

    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "GoogleApiClient connection suspended");

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult result) {
        Log.i(TAG, "GoogleApiClient connection failed:" + result.toString());

        if (!result.hasResolution()) {

            // show the localized error dialog.
            GoogleApiAvailability.getInstance().getErrorDialog(this, result.getErrorCode(), 0).show();
            return;
        }

        /**
         *  The failure has a resolution. Resolve it.
         *  Called typically when the app is not yet authorized, and an  authorization
         *  dialog is displayed to the user.
         */

        try {

            result.startResolutionForResult(this, REQUEST_CODE_RESOLUTION);

        } catch (IntentSender.SendIntentException e) {

            Log.e(TAG, "Exception while starting resolution activity", e);
        }

    }
    @Override
    protected void onResume() {
        super.onResume();
        if (mGoogleApiClient == null) {

            /**
             * Create the API client and bind it to an instance variable.
             * We use this instance as the callback for connection and connection failures.
             * Since no account name is passed, the user is prompted to choose.
             */
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(Drive.API)
                    .addScope(Drive.SCOPE_FILE)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
        }

        mGoogleApiClient.connect();

    }
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient != null) {

            // disconnect Google API client connection
            mGoogleApiClient.disconnect();
        }
        super.onPause();
    }
}
