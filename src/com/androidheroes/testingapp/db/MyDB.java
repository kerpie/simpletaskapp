package com.androidheroes.testingapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.Toast;

public class MyDB {

	private SQLiteDatabase db;
	private final Context context;
	private final DatabaseHandler dbHelper;
	
	public MyDB(Context c) {
		context = c;
		dbHelper = new DatabaseHandler(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
	}
	
	public void open() throws SQLiteException{
		try{
			db = dbHelper.getWritableDatabase();
		}catch(SQLiteException error){
			Log.e("MyDB", "writable database not available... loading readable version");
			db = dbHelper.getReadableDatabase();
		}
	}
	
	public void close(){
		db.close();
	}
	
	public long insertTask(String title, String description){
		try{
			ContentValues newTaskValue = new ContentValues();
			newTaskValue.put(Constants.COLUMN_TITLE, title);
			newTaskValue.put(Constants.COLUMN_DESCRIPTION, description);
			return db.insert(Constants.TABLE_NAME, null, newTaskValue);
		}catch(SQLiteException error){
			Log.e("MyDB", error.toString());
			return -1;
		}
	}
	
	public long deleteTask(int id){
		return db.delete(Constants.TABLE_NAME, Constants.KEY_ID + " = " + id, null);
	}
	
	public Cursor getTask(int id){
		Cursor c = db.query(Constants.TABLE_NAME, null, Constants.KEY_ID + " = " + id, null, null, null, null);
		return c;
	}
	
	public Cursor getAllTasks(){
		Cursor c = db.query(Constants.TABLE_NAME, null, null, null, null, null, null);
		return c;
	}
	
}