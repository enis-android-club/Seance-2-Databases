package com.enisandroidclub.databaseintroduction;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.enisandroidclub.db.DBAdapter;

public class AddActivity extends ActionBarActivity {

  private DBAdapter myDatabase;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add);
    
    // We have to open the database each time we intend to use it
    // So we'll just put that on the onCreate method
    myDatabase = new DBAdapter(getApplicationContext());
    myDatabase.open();
  }

  @Override
  protected void onDestroy() {
	// On Destroy is launched every time we exit the
	// activity, so we will put the database closing method here
    super.onDestroy();
    myDatabase.close();
  }

  public void btnSubmit(View v) {
    EditText c_name = (EditText) findViewById(R.id.contact_name);
    EditText c_phone = (EditText) findViewById(R.id.contact_phone);

    String name = c_name.getText().toString();
    String phone = c_phone.getText().toString();

    if (name.trim().equals("") || phone.trim().equals("")) {
      Toast.makeText(getApplicationContext(), "Name and phone number should not be empty!",
          Toast.LENGTH_SHORT).show();
      return;
    }

    myDatabase.insertRow(name, phone);

    c_name.setText("");
    c_phone.setText("");

    Toast.makeText(getApplicationContext(), name + " added to database",
        Toast.LENGTH_SHORT).show();
  }

  public void btnCancel(View v) {
    finish();
  }

}
