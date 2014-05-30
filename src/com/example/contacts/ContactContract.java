package com.example.contacts;

import android.provider.BaseColumns;

public final class ContactContract {

	public ContactContract() {	}
	
	public static abstract class ContactEntry implements BaseColumns {
        public static final String TABLE_NAME = "contacts";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_NUMBER = "number";
        
        
        
    }

}
