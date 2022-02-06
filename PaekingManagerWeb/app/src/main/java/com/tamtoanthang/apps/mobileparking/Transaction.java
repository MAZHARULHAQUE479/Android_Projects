package com.tamtoanthang.apps.mobileparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tamtoanthang.apps.mobileparking.DataBase.AdminDatabaseActivity;
import com.tamtoanthang.apps.mobileparking.DataBase.UserDatabaseActivity;
import com.tamtoanthang.apps.mobileparking.R;

public class Transaction extends AppCompatActivity {
Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
//        b1=(Button)findViewById(R.id.Admin);
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent =new Intent(Transaction.this,AdminDatabaseActivity.class);
//                startActivity(intent);
//            }
//        });
        b2=(Button)findViewById(R.id.user);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Transaction.this,UserDatabaseActivity.class);
                startActivity(intent);
            }
        });
    }
}
