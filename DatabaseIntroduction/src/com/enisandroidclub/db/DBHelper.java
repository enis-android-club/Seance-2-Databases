package com.enisandroidclub.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
 * This class will be our interface to low level queries on the database
 * using the methods
 *  - 'SQLiteDatabase.rawQuery("SQL_QUERY")'
 *  - 'SQLiteDatabase.execSQL("SQL_QUERY")'
 * 
 * We can do any sort of queries in here but it is not very recommended
 */
public class DBHelper extends SQLiteOpenHelper {

  // Define the Database file name here
  public static final String DB_NAME      = "testDB";

  // Define our table name here
  public static final String TABLE_NAME   = "contact";

  // Define the database version, this is mostly used to upgrade the database
  public static final int    DB_VERSION   = 1;

  // Define the SQL query that will create our table(s)
  public static final String CREATE_QUERY = "CREATE TABLE " + TABLE_NAME + " " +
                                            "(_id integer primary key autoincrement, " +
                                            "name varchar(32) not null, " +
                                            "phone varchar(64) not null" +
                                            ");";

  public DBHelper(Context context) {
    // Call the parent class (SQLiteOpenHelper) constructor with our parameter
    // Param 1 : Context: Will define the activity we are working with
    // Param 2 : DB_NAME: The name of the database
    // Param 3 : CursorFactory: %TODO%
    // Param 4 : DB_VERSION: Version of the current database
    super(context, DB_NAME, null, DB_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    // on the database creation, we will execute our CREATE_QUERY
    // to initiate the database
    // We can also insert initial values
    db.execSQL(CREATE_QUERY);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // Just an upgrade simulation
    // We will drop the table if it already exist
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    // And then create it again
    onCreate(db);
  }

}
