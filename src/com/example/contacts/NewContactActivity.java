package com.example.contacts;

import com.example.contacts.ContactContract.ContactEntry;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.os.Build;

public class NewContactActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_contact);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_contact_actions, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void saveContact(View view) {
		EditText editText = (EditText) findViewById(R.id.name);
		String name = editText.getText().toString();
		editText = (EditText) findViewById(R.id.phone_number);
		long number = Long.parseLong(editText.getText().toString());
		
		ContactDbHelper cDbHelper = new ContactDbHelper(this);
		SQLiteDatabase db = cDbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(ContactEntry.COLUMN_NAME_NAME, name);
		values.put(ContactEntry.COLUMN_NAME_NUMBER, number);
		
		long row_id;
		row_id = db.insert(ContactEntry.TABLE_NAME, null, values);
		
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_new_contact,
					container, false);
			return rootView;
		}
	}

}
