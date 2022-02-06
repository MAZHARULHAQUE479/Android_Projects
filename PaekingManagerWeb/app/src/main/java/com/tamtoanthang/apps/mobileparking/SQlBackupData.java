package com.tamtoanthang.apps.mobileparking;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tamtoanthang.apps.mobileparking.DataBase.Main3Activity;
import com.tamtoanthang.apps.mobileparking.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lue on 13-02-2017.
 */
public class SQlBackupData extends DialogFragment {
    TextView textView;
    public static Timer timer;
    TimerTask timerTask;
    int value = 0;
    final android.os.Handler handler = new android.os.Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.dialog_backup_data, container, false);
        textView = (TextView) rootView.findViewById(R.id.textView13);

        value = Main3Activity.timeinterval;
        if (value == 0) {
            Toast.makeText(getActivity(), "Please enter time interval and then press ok Button", Toast.LENGTH_LONG).show();
        } else {
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    timer = new Timer();
                    //initialize the TimerTask's job

                    initializeTimerTask();

                    //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms

                    timer.schedule(timerTask, 300, 60000 * value);//in current thread

                }
            });
        }
        return rootView;
    }

    public void initializeTimerTask() {

        timerTask = new TimerTask() {
            public void run() {

                //use a handler to run a toast that shows the current timestamp
                handler.post(new Runnable() {
                    public void run() {

                        new Thread() {
                            @Override
                            public void run() {
                                exportDB();
                                exportDB1();
                                // Toast.makeText(getActivity(),"export",Toast.LENGTH_LONG).show();
                            }
                        }.start();


                    }
                });
            }
        };
    }
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
    public File getAlbumStorageDir(Context context, String albumName) {
        // Get the directory for the app's private pictures directory.
        File file = new File(context.getExternalFilesDir(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e("    ", "Directory not created");
        }
        return file;
    }
    private void exportDB() {
        File pathdetail = null;
        // TODO Auto-generated method stub
        /*boolean isExtAvail=isExternalStorageWritable();
        if(isExtAvail){
          pathdetail= getAlbumStorageDir(getActivity(),"ParkingManagerBackup");

        }*/

        try {

            File sd = android.os.Environment.getExternalStorageDirectory();

            File data = android.os.Environment.getDataDirectory();

            if (sd.canWrite()) {
                String currentDBPath = "//data//" + "com.example.lue.parkingmanager"
                        + "//databases//" + "databaseCustomer.db";
                String backupDBPath = "/Android/data/com.example.lue.parkingmanager/files/Pictures/ParkingManagerBackup/databaseCustomer.db";
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd,backupDBPath);
                Log.d("dsjjn", "dkkj");
                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
                //  Toast.makeText(getActivity(), backupDB.toString(),
                //        Toast.LENGTH_LONG).show();

            }
        } catch (Exception e) {
            e.printStackTrace();

            //  Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG)
            //      .show();

        }
    }

    private void exportDB1() {
        // TODO Auto-generated method stub

        try {
            File sd = android.os.Environment.getExternalStorageDirectory();
            File data = android.os.Environment.getDataDirectory();

            if (sd.canWrite()) {
                String currentDBPath = "//data//" + "com.example.lue.parkingmanager"
                        + "//databases//" + "cardDatabase.db";
                String backupDBPath = "/Android/data/com.example.lue.parkingmanager/files/Pictures/ParkingManagerBackup/cardDatabase.db";
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File( sd,backupDBPath);
                Log.d("dsjjhgyughuyn", "dkkj");
                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();

                //  Toast.makeText(getActivity(), backupDB.toString(),
                //        Toast.LENGTH_LONG).show();

            }
        } catch (Exception e) {
            e.printStackTrace();

            //  Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG)
            //      .show();

        }
        dismiss();
    }
/*
    protected static boolean exportDb() {
        if (!SdIsPresent()) return false;

        File dbFile = DATA_DIRECTORY_DATABASE;
        String filename = "MyDb.db";

        File exportDir = DATABASE_DIRECTORY;
        File file = new File(exportDir, filename);

        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }

        try {
            file.createNewFile();
            copyFile(dbFile, file);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    protected static boolean restoreDb(){
        if( ! SdIsPresent() ) return false;

        File exportFile = DATA_DIRECTORY_DATABASE;
        File importFile = IMPORT_FILE;

        if( ! checkDbIsValid(importFile) ) return false;

        if (!importFile.exists()) {
            Log.d(TAG, "File does not exist");
            return false;
        }

        try {
            exportFile.createNewFile();
            copyFile(importFile, exportFile);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    *//** Imports the file at IMPORT_FILE **//*
    protected static boolean importIntoDb(Context ctx){
        if( ! SdIsPresent() ) return false;

        File importFile = IMPORT_FILE;

        if( ! checkDbIsValid(importFile) ) return false;

        try{
            SQLiteDatabase sqlDb = SQLiteDatabase.openDatabase
                    (importFile.getPath(), null, SQLiteDatabase.OPEN_READONLY);

            Cursor cursor = sqlDb.query(true, DATABASE_TABLE,
                    null, null, null, null, null, null, null
            );

            DbAdapter dbAdapter = new DbAdapter(ctx);
            dbAdapter.open();

            final int titleColumn = cursor.getColumnIndexOrThrow("title");
            final int timestampColumn = cursor.getColumnIndexOrThrow("timestamp");

            // Adds all items in cursor to current database
            cursor.moveToPosition(-1);
            while(cursor.moveToNext()){
                dbAdapter.createQuote(
                        cursor.getString(titleColumn),
                        cursor.getString(timestampColumn)
                );
            }

            sqlDb.close();
            cursor.close();
            dbAdapter.close();
        } catch( Exception e ){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    *//** Given an SQLite database file, this checks if the file
     * is a valid SQLite database and that it contains all the
     * columns represented by DbAdapter.ALL_COLUMN_KEYS **//*
    protected static boolean checkDbIsValid( File db ){
        try{
            SQLiteDatabase sqlDb = SQLiteDatabase.openDatabase
                    (db.getPath(), null, SQLiteDatabase.OPEN_READONLY);

            Cursor cursor = sqlDb.query(true, DATABASE_TABLE,
                    null, null, null, null, null, null, null
            );

            // ALL_COLUMN_KEYS should be an array of keys of essential columns.
            // Throws exception if any column is missing
            for( String s : DbAdapter.ALL_COLUMN_KEYS ){
                cursor.getColumnIndexOrThrow(s);
            }

            sqlDb.close();
            cursor.close();
        } catch( IllegalArgumentException e ) {
            Log.d(TAG, "Database valid but not the right type");
            e.printStackTrace();
            return false;
        } catch( SQLiteException e ) {
            Log.d(TAG, "Database file is invalid.");
            e.printStackTrace();
            return false;
        } catch( Exception e){
            Log.d(TAG, "checkDbIsValid encountered an exception");
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private static void copyFile(File src, File dst) throws IOException {
        FileChannel inChannel = new FileInputStream(src).getChannel();
        FileChannel outChannel = new FileOutputStream(dst).getChannel();
        try {
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } finally {
            if (inChannel != null)
                inChannel.close();
            if (outChannel != null)
                outChannel.close();
        }
    }

    *//** Returns whether an SD card is present and writable **//*
    public static boolean SdIsPresent() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }*/
}