package com.androidheroes.testingapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper{

	private static final String CREATE_TABLE = "CREATE TABLE " 
	+ Constants.TABLE_NAME + " ("
	+ Constants.KEY_ID + " integer primary key autoincrement, "
	+ Constants.COLUMN_TITLE + " text not null, "
	+ Constants.COLUMN_DESCRIPTION + " text not null);";
	
	public DatabaseHandler(Context context, String name, CursorFactory factory, int version) {
		super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
		onCreate(db);
	}
}