package com.enisandroidclub.databaseintroduction;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import com.enisandroidclub.db.DBAdapter;

public class MainActivity extends ActionBarActivity {

  private DBAdapter myDatabase;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    myDatabase = new DBAdapter(getApplicationContext());
    myDatabase.open();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    myDatabase.close();
  }

  public void btnActionReadAll(View v) {
    String all = "";
    Cursor cursor = myDatabase.getAllRows();
    if (cursor.moveToFirst()) {
      do {
        long id = cursor.getLong(cursor.getColumnIndex("_id"));
        String name = cursor.getString(cursor.getColumnIndex("name"));
        String phone = cursor.getString(cursor.getColumnIndex("phone"));

        all += id + " | " + name + " | " + phone + "\n";

      } while (cursor.moveToNext());
    }

    TextView dataContainer = (TextView) findViewById(R.id.data);
    dataContainer.setText(all);
  }

  public void btnActionAdd(View v) {
    Intent i = new Intent(MainActivity.this, AddActivity.class);
    startActivity(i);
  }

  public void btnActionDelete(View v) {
    Intent i = new Intent(MainActivity.this, DeleteActivity.class);
    startActivity(i);
  }
}
