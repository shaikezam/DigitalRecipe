package com.example.shayzambrovski.digitalrecipe;

/**
 * Created by Shay Zambrovski on 07/09/2016.
 */
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "myAppDB";

    // Contacts table name
    private static final String TABLE_NAME = "myTable";

    // Contacts Table Columns names
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_PASSWORD = "password";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_USER_NAME + " VARCHAR PRIMARY KEY," + KEY_PASSWORD + " VARCHAR" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_NAME, user.getUserName()); // Contact Name
        values.put(KEY_PASSWORD, user.getPassword()); // Contact Phone

        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    User getUser(String sUserName, String sPassword) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor resultSet = null;
        try{
            resultSet = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + KEY_PASSWORD + " = '" + sPassword + "' and " + KEY_USER_NAME + " = '" + sUserName + "';",null);
        }catch(Exception e) {
            Log.e("Error: ", e.toString());
        }
        if(resultSet.getCount() == 0 || resultSet == null) {
            return null;
        }

        if (resultSet != null)
            resultSet.moveToFirst();
        User user = new User(resultSet.getString(resultSet.getColumnIndex(KEY_USER_NAME)), resultSet.getString(resultSet.getColumnIndex(KEY_PASSWORD)));
            // return contact
        return user;
    }

    // Getting All Contacts
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setUserName(cursor.getString(0));
                user.setPassword(cursor.getString(1));
                // Adding contact to list
                userList.add(user);
            } while (cursor.moveToNext());
        }

        // return contact list
        return userList;
    }

    // Getting contacts Count
    public int getUsersCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        //cursor.close();

        // return count
        return cursor.getCount();
    }

    public void deleteDB() {
        String countQuery = "DELETE  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

}
