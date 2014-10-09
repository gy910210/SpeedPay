package com.speedpay.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper
{
	private static final int VERSION=1;
	
	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	public DBHelper(Context context,String name)
	{
		this(context,name,VERSION);
	}
	
	public DBHelper(Context context, String name, int version2) 
	{
		// TODO Auto-generated constructor stub
		this(context,name,null,version2);
	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		// TODO Auto-generated method stub
		System.out.println("create a Database");
		String sqlStr="create table goods(goods_id int,goods_name varchar(20),goods_barCode varchar(20))";
		db.execSQL(sqlStr);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		// TODO Auto-generated method stub
		System.out.println("update a Database");
	}

}
