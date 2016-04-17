package jdo589gv4353.tabme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by Justin on 4/16/2016.
 */
public class DBHelper extends SQLiteOpenHelper{
    // declare the DB name here
    public static final String DATABASE_NAME = "TabMe.db";

    // now declare the TABLE name that will be part of the DB
    public static final String TABLE_NAME = "Users";

    // declare the COLUMNS of the TABLE
    public static final String COL_1 = "USERNAME";
    public static final String COL_2 = "FIRST";
    public static final String COL_3 = "LAST";
    public static final String COL_4 = "EMAIL";
    public static final String COL_5 = "PHONE";
    public static final String COL_6 = "BIRTHDAY";
    public static final String COL_7 = "PASSWORD";

    // this is referencing the java class that will manage the SQL DB
    public DBHelper(Context context) {

        // whenever the constructor below is called, our DB will now be created
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // this is the execute sql query method that takes a string sql query and executes this query
        db.execSQL("create table " + TABLE_NAME + " (USERNAME TEXT PRIMARY KEY, FIRST TEXT, LAST TEXT, EMAIL TEXT, PHONE TEXT, BIRTHDAY TEXT, PASSWORD)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // upgrade the table if version number is increased and call onCreate to create a new DB
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String USERNAME, String FIRST, String LAST, String EMAIL, String PHONE, String BIRTHDAY, String PASSWORD) {

        // Open the database for reading and writing
        SQLiteDatabase db = this.getWritableDatabase();

        // This class is used to store a set of values that a ContentResolver can process.
        ContentValues contentValues = new ContentValues();

        // you need to specify the column and the data for that column
        contentValues.put(COL_1, USERNAME);
        contentValues.put(COL_2, FIRST);
        contentValues.put(COL_3, LAST);
        contentValues.put(COL_4, EMAIL);
        contentValues.put(COL_5, PHONE);
        contentValues.put(COL_6, BIRTHDAY);
        contentValues.put(COL_7, PASSWORD);

        // need to give this the table name and the content values
        long result = db.insert(TABLE_NAME, null, contentValues);

        // method will return -1 if the insert did not work
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        // Open the database for reading and writing
        SQLiteDatabase db = this.getWritableDatabase();

        // A Cursor represents the result of a query and basically points to one row of the query result.
        // This way Android can buffer the query results efficiently; as it does not have to load all data into memory.
        // the "*" means select "all"
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public Cursor getSingleData(String USERNAME) {
        // Open the database for reading and writing
        SQLiteDatabase db = this.getWritableDatabase();
        String[] projections = {COL_1, COL_2, COL_3, COL_4, COL_5, COL_6, COL_7};
        String selection = COL_1 + " = ?";
        String[] selection_args = {USERNAME};
        Cursor cursor = db.query(TABLE_NAME, projections, selection, selection_args, null, null, null);
        return cursor;
    }

    public boolean updateData(String USERNAME, String FIRST, String LAST, String EMAIL, String PHONE, String BIRTHDAY, String PASSWORD){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, USERNAME);
        contentValues.put(COL_2, FIRST);
        contentValues.put(COL_3, LAST);
        contentValues.put(COL_4, EMAIL);
        contentValues.put(COL_5, PHONE);
        contentValues.put(COL_6, BIRTHDAY);
        contentValues.put(COL_7, PASSWORD);
        db.update(TABLE_NAME, contentValues, "USERNAME = ?", new String[] {USERNAME});
        return true;
    }

    public Integer deleteData(String USERNAME) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "USERNAME = ?", new String[] {USERNAME});
    }

    public boolean checkLogin(String USERNAME, String PASSWORD){
        // Open the database for reading and writing
        SQLiteDatabase db = this.getWritableDatabase();

        String[] projections = {COL_1, COL_2, COL_3, COL_4, COL_5, COL_6, COL_7};
        String selection = COL_1 + " = ?";
        String[] selection_args = {USERNAME};
        Cursor res = db.query(TABLE_NAME, projections, selection, selection_args, null, null, null);
        if (res.moveToFirst()) {
            if (res.getString(0).equals(USERNAME) && res.getString(6).equals(PASSWORD)) {
                return true;
            } else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    public Cursor getUserData(String USERNAME) {
        // Open the database for reading and writing
        SQLiteDatabase db = this.getWritableDatabase();

        String[] projections = {COL_1, COL_2, COL_3, COL_4, COL_5, COL_6, COL_7};
        String selection = COL_1 + " = ?";
        String[] selection_args = {USERNAME};
        Cursor res = db.query(TABLE_NAME, projections, selection, selection_args, null, null, null);
        return res;
    }
}

