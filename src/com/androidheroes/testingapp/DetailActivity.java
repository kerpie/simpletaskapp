package com.androidheroes.testingapp;

import com.androidheroes.testingapp.db.MyDB;
import com.androidheroes.testingapp.model.Task;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends Activity {

	MyDB database;
	int idToSearch;
	TextView title;
	TextView description;
	Button destroyTask;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		
		database = new MyDB(getApplicationContext());
		Intent intent = getIntent();
		idToSearch = intent.getIntExtra("id", -1);
	
		title = (TextView) findViewById(R.id.etq_title_data);
		description = (TextView) findViewById(R.id.etq_description_data);
		destroyTask = (Button) findViewById(R.id.btn_delete);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		database.open();
		Cursor c = database.getTask(idToSearch);
		
		final Task task = getTask(c);
		title.setText(task.getTitle());
		description.setText(task.getDescription());
		destroyTask.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(database.deleteTask(task.getId()) == 1){
					Toast.makeText(getApplicationContext(), "Una tarea menos, bravo!", Toast.LENGTH_SHORT).show();
				}
				finish();
			}
		});
		
	}

	private Task getTask(Cursor c) {
		Task tmp = null;
		if(c.moveToFirst()){
			int i = 0;
			do{
				tmp = new Task(c.getInt(0),c.getString(1),c.getString(2));
			} while(c.moveToNext());
		}
		return tmp;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail, menu);
		return true;
	}

}
