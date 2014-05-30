package com.example.contacts;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyAdapter extends ArrayAdapter  {

	private final Context context;
    private final ArrayList<Contact> contactsList;
    
	public MyAdapter(Context context, ArrayList<Contact> contactsList) {
		super(context, R.layout.contact_list_layout, contactsList);
		this.context = context;
		this.contactsList = contactsList;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
		View rowView = inflater.inflate(R.layout.contact_list_layout, parent, false);
		 TextView contactNameView = (TextView) rowView.findViewById(R.id.contact_name);
         TextView contactNumberView = (TextView) rowView.findViewById(R.id.contact_number);
         contactNameView.setText(contactsList.get(position).getName());
         contactNumberView.setText(String.valueOf(contactsList.get(position).getNumber()));

		return rowView;
	}

}
