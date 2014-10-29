package com.enisandroidclub.databaseintroduction;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.enisandroidclub.db.DBAdapter;

public class DeleteActivity extends ActionBarActivity {

  DBAdapter myDatabase;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_delete);

    myDatabase = new DBAdapter(getApplicationContext());
    myDatabase.open();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    myDatabase.close();
  }

  public void btnDeleteRecord(View v) {
    EditText id_field = (EditText) findViewById(R.id.delete_id);
    long idToDelete = 0;

    try {
      idToDelete = Long.parseLong(id_field.getText().toString());
    } catch (NumberFormatException e) {
      Toast.makeText(getApplicationContext(), "ID is not valid",
          Toast.LENGTH_SHORT).show();
      return;
    }

    if (idToDelete > 0) {
      myDatabase.deleteRow(idToDelete);
      Toast.makeText(getApplicationContext(), "Record deleted",
          Toast.LENGTH_SHORT).show();
    } else {
      Toast.makeText(getApplicationContext(), "ID " + idToDelete + " is not valid",
          Toast.LENGTH_SHORT).show();
    }
  }

  public void btnCancel(View v) {
    finish();
  }
}
