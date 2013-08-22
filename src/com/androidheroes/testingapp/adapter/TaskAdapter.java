package com.androidheroes.testingapp.adapter;

import com.androidheroes.testingapp.model.Task;

import com.androidheroes.testingapp.DetailActivity;
import com.androidheroes.testingapp.R;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TaskAdapter extends BaseAdapter{

	Task[] data;
    Context context;
	
    public TaskAdapter(Context context, Task[] data){
    	this.context = context;
    	this.data = data;
    }
    
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Log.i("AQUI ESTOY YO", "Hola mundo!");
		
		View v = convertView;
		
		if (v==null){
			LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        v = vi.inflate(R.layout.row_in_list, null);
		}
		
		TextView title = (TextView) v.findViewById(R.id.title_in_list);
		
		title.setText(data[position].getTitle());
		Log.i("Adapter", data[position].getTitle());
		
		final int new_position = position;
		
		title.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, DetailActivity.class);
				intent.putExtra("id", data[new_position].getId());
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent);
			}
		});
		
		return v;
	}

}
