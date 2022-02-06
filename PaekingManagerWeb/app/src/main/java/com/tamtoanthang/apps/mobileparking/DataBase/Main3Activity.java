package com.tamtoanthang.apps.mobileparking.DataBase;

import android.app.FragmentManager;
import android.content.Context;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tamtoanthang.apps.mobileparking.SQlBackupData;
import com.tamtoanthang.apps.mobileparking.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class Main3Activity extends AppCompatActivity implements com.tamtoanthang.apps.mobileparking.DataBase.BackupData.OnBackupListener{
    private Context context;

    private DatabaseHelper db;

    private BackupData backupData;
    public static int timeinterval;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        boolean b=canWriteOnExternalStorage();
        boolean isExtAvail=isExternalStorageWritable();
        if(isExtAvail){
            getAlbumStorageDir(this,"ParkingManagerBackup");

        }
        Log.e("Directory"," "+getAlbumStorageDir(this,"ParkingManagerBackup"));
        /*if (b==true) {
            File direct = new File(android.os.Environment.getExternalStorageDirectory() + "/parking manager");

            if (!direct.exists()) {
                direct.mkdir();
            }
            Log.d("Directry","  "+direct.getAbsolutePath());
        }*/
           /* File[] fs = getApplicationContext().getExternalFilesDirs(null);
            String extPath = "";
            // at index 0 you have the internal storage and at index 1 the real external...
            if (fs != null && fs.length >= 2)
            {
                extPath = fs[1].getAbsolutePath();
                Log.e("SD Path","show "+fs[1].getAbsolutePath()+"  "+extPath);
            }*/
        /*}else {
           // Toast.makeText(this,"Insert Sdcard")
        };
*/

        Button button=(Button)findViewById(R.id.button4);
        context = this;

        db = new DatabaseHelper(context);
        backupData = new BackupData(context);
        backupData.setOnBackupListener(this);
        final EditText editText=(EditText)findViewById(R.id.editText) ;

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.getText().toString();


            }
        });
        Button button3=(Button)findViewById(R.id.button5);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timeinterval= Integer.parseInt(editText.getText().toString());
                editText.setEnabled(false);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // backupData.exportToSD();
                importDB();
                importDB1();
            }
        });
        Button button1=(Button)findViewById(R.id.export);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // backupData.importData("/MyNote");
                SQlBackupData dialogFragment = new SQlBackupData();

                FragmentManager fm = getFragmentManager();
                dialogFragment.show(fm, "Sample Fragment");
                //   exportDB();
            }
        });
    }
    public static boolean canWriteOnExternalStorage() {
        // get the state of your external storage
        String state = android.os.Environment.getExternalStorageState();
        if (android.os.Environment.MEDIA_MOUNTED.equals(state)) {
            // if storage is mounted return true
            Log.v("sTag", "Yes, can write to external storage.");
            return true;
        }
        return false;
    }

    @Override
    public void onFinishExport(String error) {
        String notify = error;
        if (error == null) {
            notify = "Export success";
        }
        Toast.makeText(Main3Activity.this, notify, Toast.LENGTH_SHORT).show();
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

    private void importDB() {
        // TODO Auto-generated method stub

        try {
            File sd = android.os.Environment.getExternalStorageDirectory();
            File data  = android.os.Environment.getDataDirectory();

            if (sd.canWrite()) {
                String  currentDBPath= "//data//" + "com.example.lue.parkingmanager"
                        + "//databases//" + "databaseCustomer.db";
                String backupDBPath  = "/Android/data/com.example.lue.parkingmanager/files/Pictures/ParkingManagerBackup/databaseCustomer.db";
                File  backupDB= new File(data, currentDBPath);
                File currentDB  = new File(sd, backupDBPath);

                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
                Toast.makeText(getBaseContext(), backupDB.toString(),
                        Toast.LENGTH_LONG).show();

            }
        } catch (Exception e) {

            Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG)
                    .show();

        }
    } private void importDB1() {
        // TODO Auto-generated method stub

        try {
            File sd = android.os.Environment.getExternalStorageDirectory();
            File data  = android.os.Environment.getDataDirectory();

            if (sd.canWrite()) {
                String  currentDBPath= "//data//" + "com.example.lue.parkingmanager"
                        + "//databases//" + "cardDatabase.db";
                String backupDBPath  = "/Android/data/com.example.lue.parkingmanager/files/Pictures/ParkingManagerBackup/cardDatabase.db";
                File  backupDB= new File(data, currentDBPath);
                File currentDB  = new File(sd, backupDBPath);

                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
                Toast.makeText(getBaseContext(), backupDB.toString(),
                        Toast.LENGTH_LONG).show();

            }
        } catch (Exception e) {

            Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG)
                    .show();

        }
    }
    @Override
    public void onFinishImport(String error) {
        String notify = error;
        if (error == null) {
            notify = "Import success";
            // updateListNote();
        }
        Toast.makeText(Main3Activity.this, notify, Toast.LENGTH_SHORT).show();

    }


}
