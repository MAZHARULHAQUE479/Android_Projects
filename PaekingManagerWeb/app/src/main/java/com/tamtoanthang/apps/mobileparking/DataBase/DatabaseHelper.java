package com.tamtoanthang.apps.mobileparking.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tamtoanthang.apps.mobileparking.Model.Contact;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by lue on 28-10-2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static String CREATE_TABLE1;

    // Database Name
    public static final String DATABASE_NAME = "databaseCustomer.db";
public static    File directory;
    // Contacts table name
    public static final String TABLE_CONTACTS = "customers";

    // Contacts Table Columns names
    public static final String KEY_ID = "id";
    public static final String COLUMN_CARDID_NO= "CardId_No";
    public static final String COLUMN_CARD_NO= "Card_No";
    public static final String COLUMN_CARD_PRICE= "CardPrice";
    public static final String COLUMN_INTIME_IMAGE  = "in_image";
    public static final String COLUMN_OUTTIME_IMAGE  = "out_image";
    public static final String COLUMN_IN_TIME= "InTime";
    public static final String COLUMN_OUT_TIME= "OutTime";
    public static final String COLUMN_IN_IMAGE_PATH="In_ImagePath";
    public static final String COLUMN_OUT_IMAGE_PATH="Out_ImagePath";

    public static final String COLUMN_STATUS= "CardStatus";
    public static final String COLUMN_USERNAME="userName";
    public static ContentValues cValues;
    public  SQLiteDatabase dataBase = null;
    public static Cursor cursor;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
       // context.getFilesDir();
        final File dbfile=new File(context.getFilesDir().getParent()+"/databases/"+DATABASE_NAME);
        Log.d("fejij"," "+dbfile.getAbsolutePath());
        copyDatabase(context,DATABASE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        CREATE_TABLE1= " CREATE TABLE " + TABLE_CONTACTS + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY," + COLUMN_INTIME_IMAGE + " BLOB," + COLUMN_CARDID_NO +" VARCHAR," + COLUMN_CARD_PRICE + " VARCHAR,"
                + COLUMN_OUTTIME_IMAGE + " BLOB," + COLUMN_IN_TIME + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP," + COLUMN_OUT_TIME + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP," + COLUMN_STATUS + " TEXT," + COLUMN_CARD_NO  + " TEXT,"   + COLUMN_USERNAME + " VARCHAR," + COLUMN_IN_IMAGE_PATH + " VARCHAR," + COLUMN_OUT_IMAGE_PATH + " VARCHAR  )";
        db.execSQL(CREATE_TABLE1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }
    public void deleteRecord(Contact contact) {
        dataBase = this.getWritableDatabase();
        dataBase.delete(TABLE_CONTACTS, KEY_ID + " = ?", new String[]{String.valueOf(contact.getID())});
        dataBase.close();
    }
    public void deleteAllRecords() {
        dataBase = this.getReadableDatabase();

       // dataBase.delete(TABLE_CONTACTS + " WHERE " + COLUMN_IN_TIME + " < " + " ? ", new String[] { String.valueOf(nDaysOldUnixTime) });
        dataBase.close();
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

    public void inserRecord(Contact contact) {
        dataBase = getWritableDatabase();
        cValues = new ContentValues();
        cValues.put(COLUMN_CARDID_NO, contact.getCardIdNo()); // Contact Name
        cValues.put(COLUMN_CARD_PRICE, contact.getPrice()); // Contact Name
        cValues.put(COLUMN_INTIME_IMAGE, contact.getInImage()); // Contact Name
        cValues.put(COLUMN_OUTTIME_IMAGE, contact.getOutImage());
        cValues.put(COLUMN_STATUS,"1");
        cValues.put(COLUMN_IN_TIME, contact.getInTime());
        cValues.put(COLUMN_OUT_TIME, contact.getOutTime());
        cValues.put(COLUMN_CARD_NO,contact.getCardNo());
        cValues.put(COLUMN_USERNAME,contact.getUserName());
        cValues.put(COLUMN_IN_IMAGE_PATH,contact.getIn_ImagePath());
        cValues.put(COLUMN_OUT_IMAGE_PATH,contact.getOut_ImagePath());
        // insert data into database
        dataBase.insert(TABLE_CONTACTS, null, cValues);

        dataBase.close();
    }

    public  Cursor selectRecords() {

        dataBase = getWritableDatabase();

//    Getting data from database table
        cursor = dataBase.rawQuery("select * from " + TABLE_CONTACTS, null);
        return cursor;
    }
    public ArrayList<Contact> getContact() {
        dataBase = getWritableDatabase();

//    Getting data from database table
        cursor = dataBase.rawQuery(" select * from " + TABLE_CONTACTS + " ORDER BY " + COLUMN_IN_TIME + " DESC" ,null);

        ArrayList<Contact> contact = new ArrayList<Contact>();
        Contact contact1;
        if (cursor.moveToFirst()) {

            do {
                contact1 = new Contact();
                Log.d("djif","jciji");
                contact1.setID(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
                Log.d("@","@");
                contact1.setCardIdNo(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CARDID_NO)));

                contact1.setPrice(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CARD_PRICE)));
                contact1.setInImage(cursor.getBlob(cursor.getColumnIndex(DatabaseHelper.COLUMN_INTIME_IMAGE)));
                contact1.setOutImage(cursor.getBlob(cursor.getColumnIndex(DatabaseHelper.COLUMN_OUTTIME_IMAGE)));
                contact1.setStatus(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_STATUS)));
                contact1.setInTime(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_IN_TIME)));
                contact1.setOutTime(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_OUT_TIME)));
                contact1.setCardNo(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CARD_NO)));
                contact1.setUserName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USERNAME)));
                contact1.setIn_ImagePath(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_IN_IMAGE_PATH)));
                contact1.setOut_ImagePath(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_OUT_IMAGE_PATH)));
                contact.add(contact1);


            } while (cursor.moveToNext());

        }
        cursor.close();
        return contact;
    }  public ArrayList<Contact> getContact2() {
        dataBase = getWritableDatabase();

//    Getting data from database table
        cursor = dataBase.rawQuery(" select * from " + TABLE_CONTACTS  ,null);

        ArrayList<Contact> contact = new ArrayList<Contact>();
        Contact contact1;
        if (cursor.moveToFirst()) {

            do {
                contact1 = new Contact();
                Log.d("djif","jciji");
                contact1.setID(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
                Log.d("@","@");
                contact1.setCardIdNo(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CARDID_NO)));

                contact1.setPrice(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CARD_PRICE)));
                contact1.setInImage(cursor.getBlob(cursor.getColumnIndex(DatabaseHelper.COLUMN_INTIME_IMAGE)));
                contact1.setOutImage(cursor.getBlob(cursor.getColumnIndex(DatabaseHelper.COLUMN_OUTTIME_IMAGE)));
                contact1.setStatus(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_STATUS)));
                contact1.setInTime(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_IN_TIME)));
                contact1.setOutTime(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_OUT_TIME)));
                contact1.setCardNo(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CARD_NO)));
                contact1.setUserName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USERNAME)));
                contact.add(contact1);


            } while (cursor.moveToNext());

        }
        cursor.close();
        return contact;
    }
    public ArrayList<Contact> getContact1(String cardno,String status) {
        dataBase = getWritableDatabase();

//    Getting data from database table
        cursor = dataBase.query(TABLE_CONTACTS,
                new String [] {KEY_ID,COLUMN_INTIME_IMAGE, COLUMN_CARDID_NO, COLUMN_CARD_PRICE, COLUMN_INTIME_IMAGE,COLUMN_OUTTIME_IMAGE,COLUMN_STATUS,COLUMN_IN_TIME,COLUMN_OUT_TIME,COLUMN_CARD_NO,COLUMN_USERNAME,COLUMN_IN_IMAGE_PATH,COLUMN_OUT_IMAGE_PATH},
                COLUMN_CARD_NO +"=?" +" AND " + COLUMN_STATUS + "=?",
        new String[] { cardno,status},
                null, null, null);
        ArrayList<Contact> contact = new ArrayList<Contact>();
        Contact contact1;
        if (cursor.moveToFirst()) {

            do {
                contact1 = new Contact();
                Log.d("djif","jciji");
                contact1.setID(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
                Log.d("@","@");
                contact1.setInImage(cursor.getBlob(cursor.getColumnIndex(DatabaseHelper.COLUMN_INTIME_IMAGE)));
                contact1.setCardIdNo(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CARDID_NO)));

                contact1.setPrice(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CARD_PRICE)));
                contact1.setInImage(cursor.getBlob(cursor.getColumnIndex(DatabaseHelper.COLUMN_INTIME_IMAGE)));
                contact1.setOutImage(cursor.getBlob(cursor.getColumnIndex(DatabaseHelper.COLUMN_OUTTIME_IMAGE)));
                contact1.setStatus(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_STATUS)));
                contact1.setInTime(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_IN_TIME)));
                contact1.setOutTime(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_OUT_TIME)));
                contact1.setCardNo(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CARD_NO)));
                contact1.setUserName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USERNAME)));
                contact1.setIn_ImagePath(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_IN_IMAGE_PATH)));
                contact1.setOut_ImagePath(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_OUT_IMAGE_PATH)));
                contact.add(contact1);


            } while (cursor.moveToNext());

        }
        cursor.close();
        return contact;
    }  public ArrayList<Contact> getContactUsername(String cardno,String status) {
        dataBase = getWritableDatabase();

//    Getting data from database table
        cursor = dataBase.query(TABLE_CONTACTS,
                new String [] {KEY_ID,COLUMN_INTIME_IMAGE, COLUMN_CARDID_NO, COLUMN_CARD_PRICE, COLUMN_INTIME_IMAGE,COLUMN_OUTTIME_IMAGE,COLUMN_STATUS,COLUMN_IN_TIME,COLUMN_OUT_TIME,COLUMN_CARD_NO,COLUMN_USERNAME,COLUMN_IN_IMAGE_PATH,COLUMN_OUT_IMAGE_PATH},
                COLUMN_USERNAME +"=?" +" AND " + COLUMN_STATUS + "=?",
        new String[] { cardno,status},
                null, null, null);
        ArrayList<Contact> contact = new ArrayList<Contact>();
        Contact contact1;
        if (cursor.moveToFirst()) {

            do {
                contact1 = new Contact();
                Log.d("djif","jciji");
                contact1.setID(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
                Log.d("@","@");
                contact1.setInImage(cursor.getBlob(cursor.getColumnIndex(DatabaseHelper.COLUMN_INTIME_IMAGE)));
                contact1.setCardIdNo(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CARDID_NO)));

                contact1.setPrice(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CARD_PRICE)));
                contact1.setInImage(cursor.getBlob(cursor.getColumnIndex(DatabaseHelper.COLUMN_INTIME_IMAGE)));
                contact1.setOutImage(cursor.getBlob(cursor.getColumnIndex(DatabaseHelper.COLUMN_OUTTIME_IMAGE)));
                contact1.setStatus(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_STATUS)));
                contact1.setInTime(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_IN_TIME)));
                contact1.setOutTime(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_OUT_TIME)));
                contact1.setCardNo(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CARD_NO)));
                contact1.setUserName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USERNAME)));
                contact1.setIn_ImagePath(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_IN_IMAGE_PATH)));
                contact1.setOut_ImagePath(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_OUT_IMAGE_PATH)));
                contact.add(contact1);


            } while (cursor.moveToNext());

        }
        cursor.close();
        return contact;
    }
    public ArrayList<Contact> getContactAccordingToCardId(String cardId,String status,String CardNO,String Status1) {
        dataBase = getWritableDatabase();

//    Getting data from database table
        cursor = dataBase.query(TABLE_CONTACTS,
                new String [] {KEY_ID,COLUMN_INTIME_IMAGE, COLUMN_CARDID_NO, COLUMN_CARD_PRICE, COLUMN_INTIME_IMAGE,COLUMN_OUTTIME_IMAGE,COLUMN_STATUS,COLUMN_IN_TIME,COLUMN_OUT_TIME,COLUMN_CARD_NO,COLUMN_USERNAME,COLUMN_IN_IMAGE_PATH,COLUMN_OUT_IMAGE_PATH},
                COLUMN_CARDID_NO +"=?" +" AND " + COLUMN_STATUS + "=?" + " OR " + COLUMN_CARD_NO +"=?" +" AND " + COLUMN_STATUS +"=?",
        new String[] { cardId,status,CardNO,Status1},
                null, null, null);
        ArrayList<Contact> contact = new ArrayList<Contact>();
        Contact contact1;
        if (cursor.moveToFirst()) {

            do {
                contact1 = new Contact();
                Log.d("djif","jciji");
                contact1.setID(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
                Log.d("@","@");
                contact1.setInImage(cursor.getBlob(cursor.getColumnIndex(DatabaseHelper.COLUMN_INTIME_IMAGE)));
                contact1.setCardIdNo(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CARDID_NO)));

                contact1.setPrice(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CARD_PRICE)));
                contact1.setInImage(cursor.getBlob(cursor.getColumnIndex(DatabaseHelper.COLUMN_INTIME_IMAGE)));
                contact1.setOutImage(cursor.getBlob(cursor.getColumnIndex(DatabaseHelper.COLUMN_OUTTIME_IMAGE)));
                contact1.setStatus(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_STATUS)));
                contact1.setInTime(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_IN_TIME)));
                contact1.setOutTime(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_OUT_TIME)));
                contact1.setCardNo(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CARD_NO)));
                contact1.setUserName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USERNAME)));
                contact1.setIn_ImagePath(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_IN_IMAGE_PATH)));
                contact1.setOut_ImagePath(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_OUT_IMAGE_PATH)));
                contact.add(contact1);


            } while (cursor.moveToNext());

        }
        cursor.close();
        return contact;
    }
    public ArrayList<Contact> getContactwithcrdno(String cardno) {
        dataBase = getWritableDatabase();

//    Getting data from database table
        cursor = dataBase.query(TABLE_CONTACTS,
                new String [] {KEY_ID,COLUMN_INTIME_IMAGE, COLUMN_CARDID_NO, COLUMN_CARD_PRICE, COLUMN_INTIME_IMAGE,COLUMN_OUTTIME_IMAGE,COLUMN_STATUS,COLUMN_IN_TIME,COLUMN_OUT_TIME,COLUMN_CARD_NO,COLUMN_USERNAME,COLUMN_IN_IMAGE_PATH,COLUMN_OUT_IMAGE_PATH},
                COLUMN_CARD_NO +"=?" ,
        new String[] { cardno},
                null, null, null);
        ArrayList<Contact> contact = new ArrayList<Contact>();
        Contact contact1;
        if (cursor.moveToFirst()) {

            do {
                contact1 = new Contact();
                Log.d("djif","jciji");
                contact1.setID(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
                Log.d("@","@");
                contact1.setInImage(cursor.getBlob(cursor.getColumnIndex(DatabaseHelper.COLUMN_INTIME_IMAGE)));
                contact1.setCardIdNo(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CARDID_NO)));

                contact1.setPrice(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CARD_PRICE)));
                contact1.setInImage(cursor.getBlob(cursor.getColumnIndex(DatabaseHelper.COLUMN_INTIME_IMAGE)));
                contact1.setOutImage(cursor.getBlob(cursor.getColumnIndex(DatabaseHelper.COLUMN_OUTTIME_IMAGE)));
                contact1.setStatus(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_STATUS)));
                contact1.setInTime(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_IN_TIME)));
                contact1.setOutTime(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_OUT_TIME)));
                contact1.setCardNo(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CARD_NO)));
                contact1.setUserName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USERNAME)));
                contact.add(contact1);


            } while (cursor.moveToNext());

        }
        cursor.close();
        return contact;
    }
    public ArrayList<Contact> getContactwithCardId(String cardId) {
        dataBase = getWritableDatabase();

//    Getting data from database table
        cursor = dataBase.query(TABLE_CONTACTS,
                new String [] {KEY_ID,COLUMN_INTIME_IMAGE, COLUMN_CARDID_NO, COLUMN_CARD_PRICE, COLUMN_INTIME_IMAGE,COLUMN_OUTTIME_IMAGE,COLUMN_STATUS,COLUMN_IN_TIME,COLUMN_OUT_TIME,COLUMN_CARD_NO,COLUMN_USERNAME,COLUMN_IN_IMAGE_PATH,COLUMN_OUT_IMAGE_PATH},
                COLUMN_CARDID_NO +"=?" ,
        new String[] { cardId},
                null, null, null);
        ArrayList<Contact> contact = new ArrayList<Contact>();
        Contact contact1;
        if (cursor.moveToFirst()) {

            do {
                contact1 = new Contact();
                Log.d("djif","jciji");
                contact1.setID(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
                Log.d("@","@");
                contact1.setInImage(cursor.getBlob(cursor.getColumnIndex(DatabaseHelper.COLUMN_INTIME_IMAGE)));
                contact1.setCardIdNo(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CARDID_NO)));

                contact1.setPrice(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CARD_PRICE)));
                contact1.setInImage(cursor.getBlob(cursor.getColumnIndex(DatabaseHelper.COLUMN_INTIME_IMAGE)));
                contact1.setOutImage(cursor.getBlob(cursor.getColumnIndex(DatabaseHelper.COLUMN_OUTTIME_IMAGE)));
                contact1.setStatus(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_STATUS)));
                contact1.setInTime(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_IN_TIME)));
                contact1.setOutTime(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_OUT_TIME)));
                contact1.setCardNo(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CARD_NO)));
                contact1.setUserName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USERNAME)));
                contact.add(contact1);


            } while (cursor.moveToNext());

        }
        cursor.close();
        return contact;
    }

    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CARDID_NO, contact.getCardIdNo());
        values.put(COLUMN_CARD_PRICE, contact.getPrice());
        values.put(COLUMN_INTIME_IMAGE, contact.getInImage());
        values.put(COLUMN_OUTTIME_IMAGE, contact.getOutImage());
        values.put(COLUMN_STATUS,"0");
         values.put(COLUMN_IN_TIME,  contact.getInTime());
        values.put(COLUMN_OUT_TIME,contact.getOutTime());
        values.put(COLUMN_USERNAME,contact.getUserName());
        values.put(COLUMN_IN_IMAGE_PATH,contact.getIn_ImagePath());
        values.put(COLUMN_OUT_IMAGE_PATH,contact.getOut_ImagePath());
       // values.put(COLUMN_STATUS,contact.getStatus());
        // updating row
        return db.update(TABLE_CONTACTS, values, COLUMN_IN_TIME + " = ?",
                new String[] { (contact.getInTime()) });

    }

    public String getTaskCount(String str){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor= db.rawQuery("SELECT COUNT (*) FROM " + TABLE_CONTACTS + " WHERE " + COLUMN_CARDID_NO + "=?", new String[] { str });
        cursor.moveToFirst();
        String count= cursor.getString(0);
        Log.d("tag4"," "+count);
        cursor.close();

        return count;

    }
    public boolean deleteRow(int value)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_CONTACTS, KEY_ID + "=" + value, null) > 0;
    } public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, null, null);


    }
    public void Join_table(){
        dataBase = getWritableDatabase();

    }


}
