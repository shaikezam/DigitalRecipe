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
    private static final String DATABASE_NAME = "myAppDataBase";

    // Tables name
    private static final String USER_TABLE = "tableOfUsers";
    private static final String RECIPE_TABLE = "tableOfRecipes";

    // Users Table Columns names
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_PASSWORD = "password";

    // Recipe Table Columns names
    private static final String KEY_RECIPE_NAME = "recipe_name";
    private static final String KEY_INGREDIENTS = "ingredients";
    private static final String KEY_INSTRUCTIONS = "instructions";
    private static final String KEY_RECIPE_RATE = "recipe_rate";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        SQLiteDatabase db = this.getWritableDatabase();

        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + USER_TABLE + "("
                + KEY_USER_NAME + " VARCHAR PRIMARY KEY," + KEY_PASSWORD + " VARCHAR" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

        String CREATE_RECIPE_TABLE = "CREATE TABLE IF NOT EXISTS " + RECIPE_TABLE + "("
                + KEY_RECIPE_NAME + " VARCHAR," + KEY_INGREDIENTS + " VARCHAR," + KEY_INSTRUCTIONS + " VARCHAR," + KEY_USER_NAME + " VARCHAR," +
                KEY_RECIPE_RATE + " INTEGER," + "FOREIGN KEY(" + KEY_USER_NAME + ") REFERENCES " + USER_TABLE + "(" + USER_TABLE + "))";
        db.execSQL(CREATE_RECIPE_TABLE);

    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + USER_TABLE + "("
                + KEY_USER_NAME + " VARCHAR PRIMARY KEY," + KEY_PASSWORD + " VARCHAR" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

        String CREATE_RECIPE_TABLE = "CREATE TABLE " + RECIPE_TABLE + "("
                + KEY_RECIPE_NAME + " VARCHAR," + KEY_INGREDIENTS + " VARCHAR," + KEY_INSTRUCTIONS + " VARCHAR," + KEY_USER_NAME + " VARCHAR," +
                KEY_RECIPE_RATE + " INTEGER," + "FOREIGN KEY(" + KEY_USER_NAME + ") REFERENCES " + USER_TABLE + "(" + USER_TABLE + "))";
        db.execSQL(CREATE_RECIPE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + RECIPE_TABLE);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    long addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_NAME, user.getUserName()); // Contact Name
        values.put(KEY_PASSWORD, user.getPassword()); // Contact Phone

        // Inserting Row
        long number = db.insert(USER_TABLE, null, values);
        db.close(); // Closing database connection
        return number;
    }

    // Getting single contact
    User getUser(String sUserName, String sPassword) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor resultSet = null;
        try{
            resultSet = db.rawQuery("SELECT * FROM " + USER_TABLE + " where " + KEY_PASSWORD + " = '" + sPassword + "' and " + KEY_USER_NAME + " = '" + sUserName + "';",null);
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
        String selectQuery = "SELECT  * FROM " + USER_TABLE;

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

    // Getting users Count
    public int getUsersCount() {
        String countQuery = "SELECT  * FROM " + USER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        //cursor.close();

        // return count
        return cursor.getCount();
    }
    // Getting contacts Count
    public int getRecipeCount() {
        String countQuery = "SELECT  * FROM " + RECIPE_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        //cursor.close();

        // return count
        return cursor.getCount();
    }

    public void deleteDB() {
        //String countQuery = "DELETE  * FROM " + USER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        //db.execSQL("delete from "+ USER_TABLE);
        db.execSQL("DROP TABLE " + USER_TABLE);
        db.execSQL("DROP TABLE " + RECIPE_TABLE);
    }

}
