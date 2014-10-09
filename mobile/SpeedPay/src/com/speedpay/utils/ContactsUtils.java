package com.speedpay.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.speedpay.services.IsUserService;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.Contacts.People;
import android.provider.Contacts.People.Phones;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.text.TextUtils;
import android.util.Pair;

public class ContactsUtils 
{
	@SuppressWarnings("deprecation")
	public ArrayList<Pair<String,String>> getContacts(Context context)
	{
		
		System.out.println("--->in getContacts");
		ArrayList<Pair<String,String>> list=new ArrayList<Pair<String,String>>();
		ContentResolver resolver = context.getContentResolver();
		
		//String[] columns = new String[] {Phones.NAME,Phones.NUMBER};
		String[] PEOPLE_PROJECTION = new String[] {
			ContactsContract.Contacts._ID,
			ContactsContract.CommonDataKinds.Phone.NUMBER,
			ContactsContract.Contacts.DISPLAY_NAME };
		Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, PEOPLE_PROJECTION, null, null, null);
		IsUserService isUserService=new IsUserService();
		if(phoneCursor==null)
		{
			System.out.println("phoneCursor==null");
		}
		if(phoneCursor!=null)
		{
			while(phoneCursor.moveToNext())
			{
				String phoneNumber = phoneCursor.getString(1);
				String name=phoneCursor.getString(2);
				if(TextUtils.isEmpty(name)||TextUtils.isEmpty(phoneNumber))
					continue;
				if(!isUserService.isUser(phoneNumber))
					continue;
				Pair<String,String> pair=new Pair<String, String>(name, phoneNumber);
				list.add(pair);
			}
		}
		 
		return list;
	}
}
