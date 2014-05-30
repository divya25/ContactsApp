package com.example.contacts;

import com.example.contacts.ContactContract.ContactEntry;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactDbHelper extends SQLiteOpenHelper {

	public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MyContact.db";
    
    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    
    private static final String SQL_CREATE_ENTRIES =
    	    "CREATE TABLE " + ContactEntry.TABLE_NAME + " (" +
    	    		ContactEntry.COLUMN_NAME_NAME + TEXT_TYPE + " PRIMARY KEY," +
    	    		ContactEntry.COLUMN_NAME_NUMBER + INT_TYPE + " UNIQUE" + 
    	    		" )";

    	private static final String SQL_DELETE_ENTRIES =
    	    "DROP TABLE IF EXISTS " + ContactEntry.TABLE_NAME;
    	
	public ContactDbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_ENTRIES);
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(SQL_DELETE_ENTRIES);
	    onCreate(db);
	}
	
	 public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		 onUpgrade(db, oldVersion, newVersion);
	 }

}
