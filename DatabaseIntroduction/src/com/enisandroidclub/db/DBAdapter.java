package com.enisandroidclub.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/*
 * This class is the one we'll be using for the queries
 * We can access this directly from the activities
 * and we can define our personalized queries in here
 * Examples:
 *   getAllContacts, setContactPhoneNumber, deleteAll, ...
 * 
 */
public class DBAdapter {
  // This is an instance of the DBHelper to execute the queries on the DB
  private DBHelper            myDBHelper;

  // And instance of the actual database
  private SQLiteDatabase      db;

  // The table name here, also
  private static final String TABLE_NAME = "contact";

  public DBAdapter(Context _context) {
    // Instanciate the DBHelper with the current context
    myDBHelper = new DBHelper(_context);
  }

  /*
   * A method to open the database, and return this DBHelper as
   * a result, this is just to facilitate the usage, it means
   * we can just open the database here without returning anything
   */
  public DBAdapter open() {
    db = myDBHelper.getWritableDatabase();
    return this;
  }

  /*
   * Close the database
   */
  public void close() {
    myDBHelper.close();
  }

  /*
   * A method to insert a row in the database
   * Currently we have only one table, so it won't be a problem
   */
  public long insertRow(String name, String phone) {
    // Create a ContentValues variable to contain our values
    // It is used as a HashMap (or an indexed array)
    ContentValues initialValues = new ContentValues();

    // For instance, the column "name" in the database will receive
    // the value of "name" (passed as a parameter to this method).
    // And so on...
    initialValues.put("name", name);
    initialValues.put("phone", phone);

    // Insert it into the database.
    return db.insert(TABLE_NAME, null, initialValues);
  }

  public boolean deleteRow(long rowId) {
    String where = "_id = " + rowId;
    return db.delete(TABLE_NAME, where, null) != 0;
  }

  // Return all data in the database.
  public Cursor getAllRows() {
    String where = null;
    Cursor c = db.query(true, TABLE_NAME, null, where, null, null, null, null, null);
    if (c != null) {
      c.moveToFirst();
    }
    return c;
  }

  public Cursor getRow(long rowId) {
    String where = " _id = " + rowId;
    String[] list = new String[] { "name", "phone" };
    Cursor c = db.query(true, TABLE_NAME, list, where, null, null, null, null, null);
    if (c != null) {
      c.moveToFirst();
    }
    return c;
  }

  public boolean updateRow(long rowId, String name, String phone) {
    String where = " _id = " + rowId;

    ContentValues newValues = new ContentValues();
    newValues.put("name", name);
    newValues.put("phone", phone);

    // Insert it into the database.
    return db.update(TABLE_NAME, newValues, where, null) != 0;
  }

}