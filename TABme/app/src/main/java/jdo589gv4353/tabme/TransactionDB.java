package jdo589gv4353.tabme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Garner on 4/16/16.
 */
public class TransactionDB extends SQLiteOpenHelper {
    // declare the DB name here
    public static final String DATABASE_NAME = "TabMe.db";

    // now declare the TABLE name that will be part of the DB
    public static final String TABLE_NAME = "Transactions";

    // declare the COLUMNS of the TABLE
    public static final String COL_1 = "ID";
    public static final String COL_2 = "cost";
    public static final String COL_3 = "item";
    public static final String COL_4 = "payer";

    // this is referencing the java class that will manage the SQL DB
    public TransactionDB(Context context) {

        // whenever the constructor below is called, our DB will now be created
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // this is the execute sql query method that takes a string sql query and executes this query
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, COST REAL, ITEM TEXT, PAYER TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // upgrade the table if version number is increased and call onCreate to create a new DB
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(Float COST, String ITEM, String PAYER) {

        // Open the database for reading and writing
        SQLiteDatabase db = this.getWritableDatabase();

        // This class is used to store a set of values that a ContentResolver can process.
        ContentValues contentValues = new ContentValues();

        // you need to specify the column and the data for that column
        contentValues.put(COL_2, COST);
        contentValues.put(COL_3, ITEM);
        contentValues.put(COL_4, PAYER);

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

    public Cursor getSingleData(String Id) {
        // Open the database for reading and writing
        SQLiteDatabase db = this.getWritableDatabase();
        String[] projections = {COL_1, COL_2, COL_3, COL_4};
        String selection = COL_1 + " = ?";
        String[] selection_args = {Id};
        Cursor cursor = db.query(TABLE_NAME, projections, selection, selection_args, null, null, null);
        return cursor;
    }

    public boolean updateData(String Id, Float COST, String ITEM, String PAYER){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, Id);
        contentValues.put(COL_2, COST);
        contentValues.put(COL_3, ITEM);
        contentValues.put(COL_4, PAYER);
        db.update(TABLE_NAME, contentValues, "id = ?", new String[] {Id});
        return true;
    }

    public Integer deleteData(String Id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {Id});
    }
}

