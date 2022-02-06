package com.tamtoanthang.apps.mobileparking.DataBase;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tamtoanthang.apps.mobileparking.Admin.AdminTableManipulation;
import com.tamtoanthang.apps.mobileparking.R;

import java.util.ArrayList;
import java.util.HashMap;

public class AdminDatabaseActivity extends ActionBarActivity {
    Button btnAddNewRecord;
    com.tamtoanthang.apps.mobileparking.DataBase.SQLiteHelper sQLiteHelper;

    android.widget.LinearLayout parentLayout;
    LinearLayout layoutDisplayPeople;
    TextView tvNoRecordsFound;
    private String rowID = null;

    private ArrayList<HashMap<String, String>> tableData = new ArrayList<HashMap<String, String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        btnAddNewRecord = (Button) findViewById(R.id.btnAddNewRecord);

        parentLayout = (LinearLayout) findViewById(R.id.parentLayout);
        layoutDisplayPeople = (LinearLayout) findViewById(R.id.layoutDisplayPeople);
//        getAllWidgets();
//        sQLiteHelper = new com.tamtoanthang.apps.mobileparking.DataBase.SQLiteHelper( AdminDatabaseActivity.this);
//        bindWidgetsWithEvent();
        btnAddNewRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onAddRecord();

            }
        });
//        displayAllRecords();
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//
//            String AdminUserName = data.getStringExtra(com.tamtoanthang.apps.mobileparking.Model.Constants.AdminName);
//            String AdminPassword = data.getStringExtra(Constants.AdminPassword);
//
//
//            ContactModel contact = new ContactModel();
//            contact.setAdminName(AdminUserName);
//            contact.setAdminPassword(AdminPassword);
//            if (requestCode == Constants.ADD_RECORD) {
//                // sQLiteHelper.insertRecordAdmin(AdminUserName, AdminPassword);
//                sQLiteHelper.insertRecordAdmin(contact);
//            } else if (requestCode == Constants.UPDATE_RECORD) {
//                contact.setID(rowID);
//                // sQLiteHelper.updateRecord(CardId, CardNo,CardType, rowID);
//                sQLiteHelper.updateRecordAdmin(contact);
//            }
////            displayAllRecords();
//        }
//    }
    private void getAllWidgets() {
//        btnAddNewRecord = (Button) findViewById(R.id.btnAddNewRecord);
//
//        parentLayout = (LinearLayout) findViewById(R.id.parentLayout);
//        layoutDisplayPeople = (LinearLayout) findViewById(R.id.layoutDisplayPeople);

//        tvNoRecordsFound = (TextView) findViewById(R.id.tvNoRecordsFound);
    }
//    private void bindWidgetsWithEvent() {
//        btnAddNewRecord.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onAddRecord();
//            }
//        });
//    }
//    private void onAddRecord() {
//        Intent intent = new Intent(AdminDatabaseActivity.this, AdminTableManipulation.class);
//        intent.putExtra(Constants.DML_TYPE, Constants.INSERT);
//        startActivityForResult(intent, Constants.ADD_RECORD);
//    }

//    private void onUpdateRecord( String AdminName ,String AdminPassword) {
//        Intent intent = new Intent(AdminDatabaseActivity.this, AdminTableManipulation.class);
//        intent.putExtra(Constants.AdminName, AdminName);
//        intent.putExtra(Constants.AdminPassword,AdminPassword);
//        intent.putExtra(Constants.DML_TYPE, Constants.UPDATE);
//        startActivityForResult(intent, Constants.UPDATE_RECORD);
//    }

//    private void displayAllRecords() {
//
//        com.rey.material.widget.LinearLayout inflateParentView;
//        parentLayout.removeAllViews();
//
//        ArrayList<ContactModel> contacts = sQLiteHelper.getAllRecordsAdmin();
//
//        if (contacts.size() > 0) {
////            tvNoRecordsFound.setVisibility(View.GONE);
//            ContactModel contactModel;
//            for (int i = 0; i < contacts.size(); i++) {
//
//                contactModel = contacts.get(i);
//
//                final Holder holder = new Holder();
//                final View view = LayoutInflater.from(this).inflate(R.layout.item_lists, null);
//                inflateParentView = (com.rey.material.widget.LinearLayout) view.findViewById(R.id.inflateParentView);
//                holder.tvFullName = (TextView) view.findViewById(R.id.tvFullName);
//
//
//                view.setTag(contactModel.getID());
//                holder.AdminNmae = contactModel.getAdminName();
//                holder.AdminPassword= contactModel.getAdminPassword();
//                final String personName = holder.AdminNmae + " " + holder.AdminPassword ;
//                holder.tvFullName.setText(personName);
//
//                final CharSequence[] items = {Constants.UPDATE, Constants.DELETE};
//                inflateParentView.setOnLongClickListener(new View.OnLongClickListener() {
//                    @Override
//                    public boolean onLongClick(View v) {
//
//                        AlertDialog.Builder builder = new AlertDialog.Builder(AdminDatabaseActivity.this);
//                        builder.setItems(items, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                if (which == 0) {
//
//                                    rowID = view.getTag().toString();
//                                    onUpdateRecord(holder.AdminNmae,holder.AdminPassword.toString());
//                                } else {
//                                    AlertDialog.Builder deleteDialogOk = new AlertDialog.Builder(AdminDatabaseActivity.this);
//                                    deleteDialogOk.setTitle("Delete Contact?");
//                                    deleteDialogOk.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                                                @Override
//                                                public void onClick(DialogInterface dialog, int which) {
//                                                    rowID = view.getTag().toString();                                                    ContactModel contact = new ContactModel();
//                                                    contact.setID(view.getTag().toString());
//                                                     sQLiteHelper.deleteAdminRecord(rowID);
//                                                    displayAllRecords();
//                                                }
//                                            }
//                                    );
//                                    deleteDialogOk.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int which) {
//
//                                        }
//                                    });
//                                    deleteDialogOk.show();
//                                }
//                            }
//                        });
//                        AlertDialog alertDialog = builder.create();
//                        alertDialog.show();
//                        return true;
//                    }
//                });
//                parentLayout.addView(view);
//            }
//        } else {
////            tvNoRecordsFound.setVisibility(View.VISIBLE);
//        }
//    }

    private class Holder {
        TextView tvFullName;
        String AdminNmae;
        String AdminPassword;

    }
}
