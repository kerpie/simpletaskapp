package com.androidheroes.testingapp;

import java.text.Normalizer.Form;
import java.util.ArrayList;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.androidheroes.testingapp.adapter.TaskAdapter;
import com.androidheroes.testingapp.db.MyDB;
import com.androidheroes.testingapp.model.Task;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends SherlockActivity {

	ListView taskList;
	MyDB database;
	Task allTasks[];
	TaskAdapter adapter;
	Button newTask;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		taskList = (ListView) findViewById(R.id.lst_task_index);
		newTask = (Button) findViewById(R.id.new_task);
		database = new MyDB(getApplicationContext());
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		database.open();
		
		Cursor tasks = database.getAllTasks();
		allTasks = turnToArray(tasks);
		
		adapter = new TaskAdapter(getApplicationContext(), allTasks);
		
		taskList.setAdapter(adapter);
		
		newTask.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), FormActivity.class);
				startActivity(intent);
			}
		});
		
	}

	private Task[] turnToArray(Cursor tasks) {
		Task tmp_tasks[] = new Task[tasks.getCount()];
		
		if(tasks.moveToFirst()){
			int i = 0;
			do{
				Task tmp = new Task(tasks.getInt(0),tasks.getString(1),tasks.getString(2));
				tmp_tasks[i] = tmp;
				i++;
			} while(tasks.moveToNext());
		}
		return tmp_tasks;
	}
	
	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
		
		menu.add("Nueva Tarea")
			.setIcon(android.R.drawable.ic_menu_add)
			.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent(getApplicationContext(), FormActivity.class);
		startActivity(intent);
		return true;
	}

}
