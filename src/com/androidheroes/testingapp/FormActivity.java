package com.androidheroes.testingapp;

import com.androidheroes.testingapp.db.MyDB;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormActivity extends Activity {

	private MyDB database;
	private EditText title;
	private EditText description;
	private Button save;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form);
		
		title = (EditText) findViewById(R.id.edt_title);
		description = (EditText) findViewById(R.id.edt_description);
		save = (Button) findViewById(R.id.btn_save);
		
		database = new MyDB(getApplicationContext());
		
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		database.open();
		
		save.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String titleToSave = title.getText().toString();
				String descriptionToSave = description.getText().toString();
				
				long result = database.insertTask(titleToSave, descriptionToSave);
				if(result > -1){
					Toast.makeText(getApplicationContext(), R.string.success_message, Toast.LENGTH_SHORT).show();
					finish();
				}
				else{
					Toast.makeText(getApplicationContext(), R.string.fail_message, Toast.LENGTH_SHORT).show();
				}
			}
		});
	} 
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		database.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.form, menu);
		return true;
	}

}
