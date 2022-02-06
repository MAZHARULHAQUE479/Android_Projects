package com.tamtoanthang.apps.mobileparking.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tamtoanthang.apps.mobileparking.Model.ContactModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by Nikunj on 27-08-2015.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "cardDatabase.db";
    public static final String TABLE_NAME = "CARD";
    public static final String TABLE_NAME_PRICE = "CARD_PRICE";
    public static final String TABLE_NAME_USER = "USER";
    public static final String TABLE_NAME_ADMIN = "ADMIN";
    public static final String TABLE_NAME_USER1 = "USER1";
    public static final String TABLE_NAME_USER4 = "USER4";
    public static final String COLUMN_ID = "ID";


    public static final String COLUMN_CARD_ID = "CARD_ID";
    public static final String COLUMN_CARD_NO = "CARD_NO";
    public static final String COLUMN_CARD_TYPE = "CARD_TYPE";
    public static final String COLUMN_CARD_Price = "CARD_PRICE";
    public static final String COLUMN_ADMIN_NAME = "ADMIN_NAME";
    public static final String COLUMN_USER_NAME = "USER_NAME";
    public static final String COLUMN_USER_PASSWORD = "USER_PASSWORD";
    public static final String COLUMN_ADMIN_PASSWORD = "ADMIN_PASSWORD";
    public static final String CLOUMN_USER_IN_TIME = "USER_IN_TIME";
    public static final String CLOUMN_USER_OUT_TIME = "USER_OUT_TIME";
    public static final String COLUMN_CARDNO_ID = "Card_ID_No";
    public static final String COLUMN_INTIME = "IN_TIME";
    public static final String COLUMN_OUTTIME = "OUT_TIME";
    public static final String COLUMN_INTIME_IMAGE = "IN_TIME_IMAGE";
    public static final String COLUMN_OUTTIME_IMAGE = "OUT_TIME_IMAGE";

    private SQLiteDatabase database;
    public static    File directory;
    public SQLiteHelper(Context context1) {
        super(context1, DATABASE_NAME, null, DATABASE_VERSION);
        final File dbfile=new File(context1.getFilesDir().getParent()+"/databases/"+DATABASE_NAME);
        Log.d("fejij"," "+dbfile.getAbsolutePath());
        copyDatabase(context1,DATABASE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CARD_TABLE = " CREATE TABLE " + TABLE_NAME + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_CARD_ID + " VARCHAR, " + COLUMN_CARD_NO + " VARCHAR," + COLUMN_CARD_TYPE + " VARCHAR );";

        String CREATE_PRICE_TABLE = " CREATE TABLE " + TABLE_NAME_PRICE + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_CARD_TYPE + " VARCHAR," + COLUMN_CARD_Price + " VARCHAR );";
        String CREATE_PRICE_TABLE1 = " CREATE TABLE " + TABLE_NAME_USER + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " VARCHAR," + COLUMN_USER_PASSWORD + " VARCHAR," + COLUMN_CARDNO_ID + " VARCHAR," + COLUMN_CARD_Price + " VARCHAR," + CLOUMN_USER_IN_TIME + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP," + CLOUMN_USER_OUT_TIME + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP );";
        String CREATE_PRICE_TABLE4 = " CREATE TABLE " + TABLE_NAME_USER4 + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " VARCHAR," + COLUMN_USER_PASSWORD + " VARCHAR," + COLUMN_CARDNO_ID + " VARCHAR," + COLUMN_CARD_Price + " VARCHAR," + CLOUMN_USER_IN_TIME + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP," + CLOUMN_USER_OUT_TIME + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP );";
        String CREATE_PRICE_TABLE2 = " CREATE TABLE " + TABLE_NAME_ADMIN + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_ADMIN_NAME + " VARCHAR," + COLUMN_ADMIN_PASSWORD + " VARCHAR );";
        String CREATE_PRICE_TABLE3 = " CREATE TABLE " + TABLE_NAME_USER1 + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " VARCHAR," + COLUMN_USER_PASSWORD + " VARCHAR );";
        db.execSQL(CREATE_CARD_TABLE);
        db.execSQL(CREATE_PRICE_TABLE1);
        db.execSQL(CREATE_PRICE_TABLE2);
        db.execSQL(CREATE_PRICE_TABLE);
        db.execSQL(CREATE_PRICE_TABLE3);
        db.execSQL(CREATE_PRICE_TABLE4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PRICE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ADMIN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USER1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USER4);

        onCreate(db);
    }

    public void insertRecord(ContactModel contact) {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CARD_ID, contact.getCardId());
        contentValues.put(COLUMN_CARD_NO, contact.getCardNo());
        contentValues.put(COLUMN_CARD_TYPE, contact.getCardType());
        database.insert(TABLE_NAME, null, contentValues);
        database.close();
    }

    public void insertRecordPrice(ContactModel contact) {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CARD_TYPE, contact.getCardType());
        contentValues.put(COLUMN_CARD_Price, contact.getPrice());
        database.insert(TABLE_NAME_PRICE, null, contentValues);
        database.close();
    }

    public void insertRecordUser(ContactModel contact) {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USER_NAME, contact.getUserName());
        contentValues.put(COLUMN_USER_PASSWORD, contact.getUserPassword());
        contentValues.put(COLUMN_CARDNO_ID, contact.getCardIdNo());
        contentValues.put(COLUMN_CARD_Price, contact.getPrice());
        contentValues.put(CLOUMN_USER_IN_TIME, contact.getUserINTime());
        contentValues.put(CLOUMN_USER_OUT_TIME, contact.getUsserOutTime());
        database.insert(TABLE_NAME_USER, null, contentValues);
        database.close();
    }

    public void insertRecordUser4(ContactModel contact) {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USER_NAME, contact.getUserName());
        contentValues.put(COLUMN_USER_PASSWORD, contact.getUserPassword());
        contentValues.put(COLUMN_CARDNO_ID, contact.getCardIdNo());
        contentValues.put(COLUMN_CARD_Price, contact.getPrice());
        contentValues.put(CLOUMN_USER_IN_TIME, contact.getUserINTime());
        contentValues.put(CLOUMN_USER_OUT_TIME, contact.getUsserOutTime());
        database.insert(TABLE_NAME_USER4, null, contentValues);
        database.close();
    }

    public void insertRecordUser1(ContactModel contact) {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USER_NAME, contact.getUserName());
        contentValues.put(COLUMN_USER_PASSWORD, contact.getUserPassword());
        database.insert(TABLE_NAME_USER1, null, contentValues);
        database.close();
    }

    public void insertRecordAdmin(ContactModel contact) {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ADMIN_NAME, contact.getAdminName());
        contentValues.put(COLUMN_ADMIN_PASSWORD, contact.getAdminPassword());
        database.insert(TABLE_NAME_ADMIN, null, contentValues);
        database.close();
    }

    public void insertRecordAlternate(ContactModel contact) {
        database = this.getReadableDatabase();
        database.execSQL("INSERT INTO " + TABLE_NAME + "(" + COLUMN_CARD_ID + "," + COLUMN_CARD_NO + "," + COLUMN_CARD_TYPE + ") VALUES('" + contact.getCardId() + "','" + contact.getCardNo() + "','" + contact.getCardType() + "')");
        database.close();
    }


    public ArrayList<ContactModel> getAllRecords() {
        database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null);

        ArrayList<ContactModel> contacts = new ArrayList<ContactModel>();
        ContactModel contactModel;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();

                contactModel = new ContactModel();
                contactModel.setID(cursor.getString(0));
                contactModel.setCardId(cursor.getString(1));
                contactModel.setCardNo(cursor.getString(2));
                contactModel.setCardType(cursor.getString(3));


                contacts.add(contactModel);

            }
        }
        Log.d("tag5", "d" + contacts);
        cursor.close();
        database.close();

        return contacts;
    }public ArrayList<ContactModel> getAllRecords1(String cardid,String cardNo) {
        database = this.getReadableDatabase();

        Cursor cursor = database.query(TABLE_NAME,
                new String [] {COLUMN_ID,COLUMN_CARD_ID, COLUMN_CARD_NO, COLUMN_CARD_TYPE},
                COLUMN_CARD_ID +"=?" + " OR " + COLUMN_CARD_NO +"=?" ,
                new String[] { cardid,cardNo},
                null, null, null);
        ArrayList<ContactModel> contacts = new ArrayList<ContactModel>();
        ContactModel contactModel;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();

                contactModel = new ContactModel();
                contactModel.setID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_ID)));
                contactModel.setCardId(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_CARD_ID)));
                contactModel.setCardNo(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_CARD_NO)));
                contactModel.setCardType(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_CARD_TYPE)));



                contacts.add(contactModel);

            }
        }
        Log.d("tag5", "d" + contacts);
        cursor.close();
        database.close();

        return contacts;
    }
public ArrayList<ContactModel> getAllRecordsAccordingCardNo(String cardNo) {
        database = this.getReadableDatabase();

        Cursor cursor = database.query(TABLE_NAME,
                new String [] {COLUMN_ID,COLUMN_CARD_ID, COLUMN_CARD_NO, COLUMN_CARD_TYPE},
                COLUMN_CARD_NO +"=?" + " OR " + COLUMN_CARD_ID +"=?" ,
                new String[] { cardNo},
                null, null, null);
        ArrayList<ContactModel> contacts = new ArrayList<ContactModel>();
        ContactModel contactModel;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();

                contactModel = new ContactModel();
                contactModel.setID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_ID)));
                contactModel.setCardId(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_CARD_ID)));
                contactModel.setCardNo(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_CARD_NO)));
                contactModel.setCardType(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_CARD_TYPE)));



                contacts.add(contactModel);

            }
        }
        Log.d("tag5", "d" + contacts);
        cursor.close();
        database.close();

        return contacts;
    }

    public ArrayList<ContactModel> getAllRecordsUser() {
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(" select * from " + TABLE_NAME_USER + " ORDER BY " + CLOUMN_USER_IN_TIME + " DESC" ,null);

       // Cursor cursor = database.query(TABLE_NAME_USER, null, null, null, null, null, null);

        ArrayList<ContactModel> contacts = new ArrayList<ContactModel>();
        ContactModel contactModel;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();

                contactModel = new ContactModel();
                contactModel.setID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_ID)));
                contactModel.setUserName(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_USER_NAME)));
                contactModel.setUserPassword(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_USER_PASSWORD)));
                contactModel.setCardIdNo(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_CARDNO_ID)));
                contactModel.setPrice(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_CARD_Price)));
                contactModel.setUserINTime(cursor.getString(cursor.getColumnIndex(SQLiteHelper.CLOUMN_USER_IN_TIME)));
                contactModel.setUsserOutTime(cursor.getString(cursor.getColumnIndex(SQLiteHelper.CLOUMN_USER_OUT_TIME)));
                contacts.add(contactModel);

            }
        }
        Log.d("tag5", "d" + contacts);
        cursor.close();
        database.close();

        return contacts;
    }

    public ArrayList<ContactModel> getAllRecordsUser4() {
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(" select * from " + TABLE_NAME_USER4 + " ORDER BY " + CLOUMN_USER_IN_TIME + " DESC" ,null);



        ArrayList<ContactModel> contacts = new ArrayList<ContactModel>();
        ContactModel contactModel;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();

                contactModel = new ContactModel();
                contactModel.setID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_ID)));
                contactModel.setUserName(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_USER_NAME)));
                contactModel.setUserPassword(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_USER_PASSWORD)));
                contactModel.setCardIdNo(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_CARDNO_ID)));
                contactModel.setPrice(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_CARD_Price)));
                contactModel.setUserINTime(cursor.getString(cursor.getColumnIndex(SQLiteHelper.CLOUMN_USER_IN_TIME)));
                contactModel.setUsserOutTime(cursor.getString(cursor.getColumnIndex(SQLiteHelper.CLOUMN_USER_OUT_TIME)));
                contacts.add(contactModel);

            }
        }
        Log.d("tag5", "d" + contacts);
        cursor.close();
        database.close();

        return contacts;
    }

 public ArrayList<ContactModel> getAllRecordsUser4AccordingToUserInTime(String UserIntime,String UserOutTime) {
        database = this.getReadableDatabase();

       Cursor  cursor = database.query(TABLE_NAME_USER4,
             new String [] {COLUMN_ID,COLUMN_USER_NAME, COLUMN_USER_PASSWORD, COLUMN_CARDNO_ID, COLUMN_CARD_Price,CLOUMN_USER_IN_TIME,CLOUMN_USER_OUT_TIME},
                     CLOUMN_USER_IN_TIME +"=?" +" AND " + CLOUMN_USER_OUT_TIME + "=?",
             new String[] { UserIntime,UserOutTime},
             null, null, null);

        ArrayList<ContactModel> contacts = new ArrayList<ContactModel>();
        ContactModel contactModel;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();

                contactModel = new ContactModel();
                contactModel.setID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_ID)));
                contactModel.setUserName(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_USER_NAME)));
                contactModel.setUserPassword(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_USER_PASSWORD)));
                contactModel.setCardIdNo(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_CARDNO_ID)));
                contactModel.setPrice(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_CARD_Price)));
                contactModel.setUserINTime(cursor.getString(cursor.getColumnIndex(SQLiteHelper.CLOUMN_USER_IN_TIME)));
                contactModel.setUsserOutTime(cursor.getString(cursor.getColumnIndex(SQLiteHelper.CLOUMN_USER_OUT_TIME)));
                contacts.add(contactModel);

            }
        }
        Log.d("tag5", "d" + contacts);
        cursor.close();
        database.close();

        return contacts;
    }

    public ArrayList<ContactModel> getAllRecordsUser1() {
        database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME_USER1, null, null, null, null, null, null);

        ArrayList<ContactModel> contacts = new ArrayList<ContactModel>();
        ContactModel contactModel;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();

                contactModel = new ContactModel();
                contactModel.setID(cursor.getString(0));
                contactModel.setUserName(cursor.getString(1));
                contactModel.setUserPassword(cursor.getString(2));

                contacts.add(contactModel);

            }
        }
        Log.d("tag5", "d" + contacts);
        cursor.close();
        database.close();

        return contacts;
    }

    public ArrayList<ContactModel> getAllRecordsPrice() {
        database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME_PRICE, null, null, null, null, null, null);

        ArrayList<ContactModel> contacts = new ArrayList<ContactModel>();
        ContactModel contactModel;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();

                contactModel = new ContactModel();
                contactModel.setID(cursor.getString(0));
                contactModel.setCardType(cursor.getString(1));
                contactModel.setPrice(cursor.getString(2));

                contacts.add(contactModel);

            }
        }
        Log.d("tag5", "d" + contacts);
        cursor.close();
        database.close();

        return contacts;

    }

    public ArrayList<ContactModel> getAllRecordsAccordingToPrice(String CardType) {
        database = this.getReadableDatabase();


        Cursor  cursor = database.query(TABLE_NAME_PRICE,
                new String [] {COLUMN_ID,COLUMN_CARD_TYPE, COLUMN_CARD_Price},
                COLUMN_CARD_TYPE +"=?",
                new String[] { CardType},
                null, null, null);
        ArrayList<ContactModel> contacts = new ArrayList<ContactModel>();
        ContactModel contactModel;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();

                contactModel = new ContactModel();
                contactModel.setID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_ID)));
                contactModel.setCardType(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_CARD_TYPE)));
                contactModel.setPrice(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_CARD_Price)));


                contacts.add(contactModel);

            }
        }
        Log.d("tag5", "d" + contacts);
        cursor.close();
        database.close();

        return contacts;

    }

    public ArrayList<ContactModel> getAllRecordsAdmin() {
        database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME_ADMIN, null, null, null, null, null, null);

        ArrayList<ContactModel> contacts = new ArrayList<ContactModel>();
        ContactModel contactModel;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();

                contactModel = new ContactModel();
                contactModel.setID(cursor.getString(0));
                contactModel.setAdminName(cursor.getString(1));
                contactModel.setAdminPassword(cursor.getString(2));

                contacts.add(contactModel);

            }
        }
        Log.d("tag5", "d" + contacts);
        cursor.close();
        database.close();

        return contacts;

    }

    public ArrayList<ContactModel> getAllRecordsAlternate() {
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<ContactModel> contacts = new ArrayList<ContactModel>();

        ContactModel contactModel;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();

                contactModel = new ContactModel();
                contactModel.setID(cursor.getString(0));
                contactModel.setCardId(cursor.getString(1));
                contactModel.setCardNo(cursor.getString(2));
                contactModel.setCardType(cursor.getString(3));

                contacts.add(contactModel);
            }
        }
        cursor.close();
        database.close();

        return contacts;
    }

    public void updateRecord(ContactModel contact) {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CARD_ID, contact.getCardId());
        contentValues.put(COLUMN_CARD_NO, contact.getCardNo());
        contentValues.put(COLUMN_CARD_TYPE, contact.getCardType());
        database.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?", new String[]{contact.getID()});
        database.close();
    }

    public void updateRecordAdmin(ContactModel contact) {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ADMIN_NAME, contact.getAdminName());
        contentValues.put(COLUMN_ADMIN_PASSWORD, contact.getAdminPassword());

        database.update(TABLE_NAME_ADMIN, contentValues, COLUMN_ID + " = ?", new String[]{contact.getID()});
        database.close();
    }

    public void updateRecordUser(ContactModel contact) {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USER_NAME, contact.getUserName());
        contentValues.put(COLUMN_USER_PASSWORD, contact.getUserPassword());

        database.update(TABLE_NAME_USER1, contentValues, COLUMN_ID + " = ?", new String[]{contact.getID()});
        database.close();
    } public void updateRecordCard(ContactModel contact) {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CARD_ID, contact.getCardId());
        contentValues.put(COLUMN_CARD_TYPE, contact.getCardType());
        contentValues.put(COLUMN_CARD_NO,contact.getCardNo());

        database.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?", new String[]{contact.getID()});
        database.close();
    }

    public void updateRecordUser2(ContactModel contact) {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USER_NAME, contact.getUserName());
        contentValues.put(COLUMN_USER_PASSWORD, contact.getUserPassword());
        contentValues.put(COLUMN_CARDNO_ID, contact.getCardIdNo());
        contentValues.put(COLUMN_CARD_Price, contact.getPrice());
        contentValues.put(CLOUMN_USER_IN_TIME, contact.getUserINTime());
        contentValues.put(CLOUMN_USER_OUT_TIME, contact.getUsserOutTime());

        database.update(TABLE_NAME_USER, contentValues, COLUMN_ID + " = ?", new String[]{contact.getID()});
        database.close();
    }

    public int updateRecordUser4(ContactModel contact) {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USER_NAME, contact.getUserName());
        contentValues.put(COLUMN_USER_PASSWORD, contact.getUserPassword());
        contentValues.put(COLUMN_CARDNO_ID, contact.getCardIdNo());
        contentValues.put(COLUMN_CARD_Price, contact.getPrice());
        contentValues.put(CLOUMN_USER_IN_TIME, contact.getUserINTime());
        contentValues.put(CLOUMN_USER_OUT_TIME, contact.getUsserOutTime());

        return database.update(TABLE_NAME_USER4, contentValues, CLOUMN_USER_IN_TIME + " = ?",
                new String[]{(contact.getUserINTime())});


    }



    public void updateRecordprice(ContactModel contact) {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CARD_TYPE, contact.getCardType());
        contentValues.put(COLUMN_CARD_Price, contact.getPrice());

        database.update(TABLE_NAME_PRICE, contentValues, COLUMN_ID + " = ?", new String[]{contact.getID()});
        database.close();
    }

    public void updateRecordAlternate(ContactModel contact) {
        database = this.getReadableDatabase();
        database.execSQL("update " + TABLE_NAME + " set " + COLUMN_CARD_ID + " = '" + contact.getCardId() + "', " + COLUMN_CARD_NO + " = '" + contact.getCardNo() + "'," +COLUMN_CARD_ID+"='"+contact.getCardType()+"'where " + COLUMN_ID + " = '" + contact.getID() + "'");

        database.close();
    }

    public void deleteAllRecords() {
        database = this.getReadableDatabase();
        database.delete(TABLE_NAME, null, null);
        database.close();
    }

    public void deleteAllRecordsAlternate() {
        database = this.getReadableDatabase();
        database.execSQL("delete from " + TABLE_NAME);
        database.close();
    }

    public void deleteRecord(ContactModel contact) {
        database = this.getReadableDatabase();
        database.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{contact.toString() + "'"});
        database.close();
    }
    public void deleteRecordprice(ContactModel contact) {
        database = this.getReadableDatabase();
        database.delete(TABLE_NAME_PRICE, COLUMN_ID + " = ?", new String[]{contact.toString() + "'"});
        database.close();}
    public void deleteRecordAdmin(ContactModel contact) {
        database = this.getReadableDatabase();
        database.delete(TABLE_NAME_ADMIN, COLUMN_ID + " = ?", new String[]{contact.toString() + "'"});
        database.close();
    }public void deleteRecordUser(ContactModel contact) {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USER_NAME, contact.getUserName());
        contentValues.put(COLUMN_USER_PASSWORD, contact.getUserPassword());

        database.delete(TABLE_NAME_USER1, COLUMN_ID + " = ?", new String[]{contact.getID()});
        database.close();
    }
    public boolean deleteRecordUser1(int value)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_USER1, COLUMN_ID + "=" + value, null) > 0;
    }
    public boolean deleteUserRecord(String rowId) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_USER1, COLUMN_ID + "=" + rowId, null) > 0;
    }
    public boolean deleteAdminRecord(String rowId) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_ADMIN, COLUMN_ID + "=" + rowId, null) > 0;
    }
    public boolean deleteCardPriceRecord(String rowId) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_PRICE, COLUMN_ID + "=" + rowId, null) > 0;
    }
    public boolean deleteCardRecord(String rowId) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COLUMN_ID + "=" + rowId, null) > 0;
    }


    public ArrayList<String> getAllTableName()
    {
        database = this.getReadableDatabase();
        ArrayList<String> allTableNames=new ArrayList<String>();
        Cursor cursor=database.rawQuery("SELECT name FROM sqlite_master WHERE type='table'",null);
        if(cursor.getCount()>0)
        {
            for(int i=0;i<cursor.getCount();i++)
            {
                cursor.moveToNext();
                allTableNames.add(cursor.getString(cursor.getColumnIndex("name")));
            }
        }
        cursor.close();
        database.close();
        return allTableNames;
    }

    public static void copyDatabase(Context c, String DATABASE_NAME) {
        String databasePath = c.getDatabasePath(DATABASE_NAME).getPath();
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

                myOutput = new FileOutputStream(directory.getAbsolutePath()
                        + "/" + DATABASE_NAME);
                myInput = new FileInputStream(databasePath);

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


}
