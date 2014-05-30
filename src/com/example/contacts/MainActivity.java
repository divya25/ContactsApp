package com.example.contacts;


import java.util.ArrayList;
import java.util.List;

import com.example.contacts.ContactContract.ContactEntry;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.os.Build;
import android.app.ListActivity;
import android.app.LoaderManager;

public class MainActivity extends ListActivity {

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);

		
		MyAdapter adapter = new MyAdapter(this, generateContacts());
		setListAdapter(adapter);
		
	}
	
	private ArrayList<Contact> generateContacts() {
		ContactDbHelper cDbHelper = new ContactDbHelper(this);
		SQLiteDatabase db = cDbHelper.getReadableDatabase();
		
		String [] projection = { ContactEntry.COLUMN_NAME_NAME, ContactEntry.COLUMN_NAME_NUMBER};
		String sortOrder = ContactEntry.COLUMN_NAME_NAME + " Asc";
		ArrayList <Contact>contacts = new ArrayList<Contact>();
		
		Cursor cursor = db.query(ContactEntry.TABLE_NAME, projection, null, null, null, null, sortOrder);
		cursor.moveToFirst();
		
		while(cursor.isAfterLast() == false) {
			String name = cursor.getString(cursor.getColumnIndex(ContactEntry.COLUMN_NAME_NAME));
			long number = cursor.getLong(cursor.getColumnIndex(ContactEntry.COLUMN_NAME_NUMBER));
			Contact contact = new Contact(name, number);
			contacts.add(contact);
			cursor.moveToNext();
		}
		
		return contacts;
	}
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case R.id.action_settings:
			
			return true;
		case R.id.new_contact:
			Intent intent = new Intent(this, NewContactActivity.class);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}		
	}

	public void openNewContact(View view) {
		
	}

	
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
