
package com.example.admin.login_expenseapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.admin.login_expenseapp.Datamodel.Data_expense_list;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class GetBookDatabase extends SQLiteOpenHelper {

    String DB_PATH = "data/data/com.example.admin.login_expenseapp/Database/";
    static String DB_NAME = "Glimray Expenses";
    SQLiteDatabase sqLiteDatabase;
    Context context;
    private DatabaseContract databaseContract = new DatabaseContract();
    private String TAG = "GetBookDatabase";

    public GetBookDatabase(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
        if (android.os.Build.VERSION.SDK_INT >= 17) {
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createDataBase() throws IOException {

        boolean dbExist = checkDatabase();
        Log.d(TAG, "db is " + dbExist);
        if (!dbExist) {

            this.getReadableDatabase();
            try {

                copyDatabase();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private boolean checkDatabase() {
        File databasePath = context.getDatabasePath(DB_NAME);
        return databasePath.exists();
    }

    public void copyDatabase() throws IOException {

        InputStream inputStream = context.getAssets().open("AddExpense.sqlite");

        String path = DB_PATH + DB_NAME;

        OutputStream outputStream = new FileOutputStream(path);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }

        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    public void openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        sqLiteDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public synchronized void close() {
        if (sqLiteDatabase != null)
            sqLiteDatabase.close();
        super.close();

    }

    String addexpense = " CREATE TABLE IF NOT EXISTS " + databaseContract.TABLE_USER + " ("
            + databaseContract.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + databaseContract.KEY_AMOUNT + " TEXT ," + databaseContract.KEY_EMAILID + " TEXT ,"
            + databaseContract.KEY_CATEGORIES + " TEXT ," +databaseContract.KEY_DESCRPTION + " TEXT ,"
            + databaseContract.KEY_EXPENSE_DATE + " TEXT ," + databaseContract.KEY_EXPENSE_TIME + " TEXT ,"
            + databaseContract.KEY_PAYMENT_TO + " TEXT ,"+databaseContract.KEY_ICON + " BLOB )";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(addexpense);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /*@Override
    public void onUpgrade(int id,String amount,String email_id,String Categories,String description,String payment_to,String expense_date,
                          String expense_time)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(databaseContract.KEY_AMOUNT,amount);
        values.put(databaseContract.KEY_EMAILID, email_id);
        values.put(databaseContract.KEY_CATEGORIES,Categories);
        values.put(databaseContract.KEY_DESCRPTION,description);
        values.put(databaseContract.KEY_EXPENSE_DATE,payment_to);
        values.put(databaseContract.KEY_EXPENSE_TIME,expense_date);
        values.put(databaseContract.KEY_PAYMENT_TO,expense_time);

        db.insert(databaseContract.TABLE_USER, null, values); //Insert query to store the record in the database
        db.close();

    }*/

    /*addUser() will add a new Book to database*/
    public void addUser(String Amount,String Email_id, String Categories, String Description, String Payment_To, String expense_date
            , String expense_time,byte[] icon) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(databaseContract.KEY_AMOUNT, Amount);
        values.put(databaseContract.KEY_ICON,icon);
        values.put(databaseContract.KEY_EMAILID, Email_id);
        values.put(databaseContract.KEY_CATEGORIES, Categories);
        values.put(databaseContract.KEY_DESCRPTION,Description);
        values.put(databaseContract.KEY_EXPENSE_DATE, expense_date);
        values.put(databaseContract.KEY_EXPENSE_TIME, expense_time);
        values.put(databaseContract.KEY_PAYMENT_TO, Payment_To);
        db.insert(databaseContract.TABLE_USER, null, values); //Insert query to store the record in the database
        db.close();
    }

    /*getUser() will return he user's object if id matches*/
    public Data_expense_list getUser(int user_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Data_expense_list expense_list = null;
        Cursor cursor = db.query(databaseContract.TABLE_USER,
                new String[]{databaseContract.KEY_ID,
                        databaseContract.KEY_AMOUNT,
                        databaseContract.KEY_EMAILID,
                        databaseContract.KEY_CATEGORIES,databaseContract.KEY_DESCRPTION,
                        databaseContract.KEY_EXPENSE_DATE,
                        databaseContract.KEY_EXPENSE_TIME,
                        databaseContract.KEY_PAYMENT_TO,databaseContract.KEY_ICON},

                databaseContract.KEY_ID + "=?",
                new String[]{String.valueOf(user_id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            expense_list = new Data_expense_list(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),
                    cursor.getString(6),cursor.getString(7),cursor.getBlob(8));
            cursor.close();
        }
        return expense_list;
    }

   public Cursor getUser1(int user_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor1 = db.query(databaseContract.TABLE_USER,
                new String[]{databaseContract.KEY_ID,
                        databaseContract.KEY_AMOUNT,
                        databaseContract.KEY_EMAILID,databaseContract.KEY_CATEGORIES,
                        databaseContract.KEY_DESCRPTION,
                        databaseContract.KEY_EXPENSE_DATE,
                        databaseContract.KEY_EXPENSE_TIME,
                        databaseContract.KEY_PAYMENT_TO,databaseContract.KEY_ICON},

                databaseContract.KEY_ID + "=?",
                new String[]{String.valueOf(user_id)}, null, null, null, null);
 return cursor1;
    }

    /*getAllUsers() will return the list of all users*/
    public ArrayList<Data_expense_list> getAllUsers() {
        ArrayList<Data_expense_list> usersList = new ArrayList<Data_expense_list>();
        String selectQuery = "SELECT  * FROM " + databaseContract.TABLE_USER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Data_expense_list expense_list = new Data_expense_list(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),
                        cursor.getString(6),cursor.getString(7),cursor.getBlob(8));
                usersList.add(expense_list);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return usersList;
    }

    /*updateUser() will be used to update the existing book record*/
   /* public int updateUser(int Amount,String Email_id,String Categories, String Description, String Payment_to) {


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(databaseContract.KEY_AMOUNT,Amount);
        values.put(databaseContract.KEY_EMAILID,Email_id);
        values.put(databaseContract.KEY_CATEGORIES,Categories);
        values.put(databaseContract.KEY_DESCRPTION,Description);
        values.put(databaseContract.KEY_PAYMENT_TO,Payment_to);

        // updating record
        return db.update(databaseContract.TABLE_USER, values, databaseContract.KEY_ID + " = ?",
                // update query to make changes to the existing record
                new String[]{String.valueOf(book.getId())});

    }*/


    /*getUsersCount() will give the total number of records in the table*/
    public int getUsersCount() {
        String countQuery = "SELECT * FROM " + databaseContract.TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    /*deleteContact() to delete the record from the table*/
    public void deleteContact(int data_expense_list) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(databaseContract.TABLE_USER, databaseContract.KEY_ID + " = ?",
                new String[]{String.valueOf(data_expense_list)});
        db.close();

    }
}
