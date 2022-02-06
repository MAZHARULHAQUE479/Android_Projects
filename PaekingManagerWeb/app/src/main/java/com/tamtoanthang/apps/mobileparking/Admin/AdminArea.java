package com.tamtoanthang.apps.mobileparking.Admin;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tamtoanthang.apps.mobileparking.Customer.CustomerDetailsActivity;
import com.tamtoanthang.apps.mobileparking.DataBase.DataBaseActivity;
import com.tamtoanthang.apps.mobileparking.DataBase.Main3Activity;
import com.tamtoanthang.apps.mobileparking.DataBase.PriceDatabaseActivity;
import com.tamtoanthang.apps.mobileparking.DataBase.UserDatabaseActivity;
import com.tamtoanthang.apps.mobileparking.Transaction;
import com.tamtoanthang.apps.mobileparking.DataBase.DatabaseHelper;
import com.tamtoanthang.apps.mobileparking.Main2Activity;
import com.tamtoanthang.apps.mobileparking.R;

public class AdminArea extends AppCompatActivity {
    Button b1, b2, b3, b4, b5;
    DatabaseHelper dbHelper;
    SharedPreferences SM1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_area);
        SM1 =this. getSharedPreferences("adminrecord", 0);
        b1 = (Button) findViewById(R.id.createnewcard);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminArea.this, DataBaseActivity.class);
                startActivity(intent);
            }
        });
        b2 = (Button) findViewById(R.id.manageuser);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  dbHelper = new DatabaseHelper(AdminArea.this);
                Intent intent = new Intent(AdminArea.this, UserDatabaseActivity.class);
                startActivity(intent);
            }
        });

        b3 = (Button) findViewById(R.id.ManagecardPrice);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminArea.this, PriceDatabaseActivity.class);
                startActivity(intent);
            }
        });
        b4 = (Button) findViewById(R.id.transactionManager);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminArea.this, CustomerDetailsActivity.class);
                startActivity(intent);
            }
        });
        b5 = (Button) findViewById(R.id.button3);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminArea.this, Main3Activity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_favorite) {
            AlertDialog.Builder builder = new AlertDialog.Builder(AdminArea.this);
            builder.setMessage(R.string.dialog_message);
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    SharedPreferences.Editor edit = SM1.edit();
                    edit.putBoolean("adminlogin", false);
                    Intent intent= new Intent(AdminArea.this,Main2Activity.class);
                    startActivity(intent);
                    finish();
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
    @Override
    public void onBackPressed()
    {
        boolean adminlogin = SM1.getBoolean("adminlogin", false);
        if (adminlogin=true) {
            Toast.makeText(this,getString(R.string.Please_LogOut_First),Toast.LENGTH_LONG).show();
        }else {
            super.onBackPressed();
        }
        // code here to show dialog
       // optional depending on your needs
    }
}
