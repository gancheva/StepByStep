package com.StepByStep.ListViewController;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.StepByStepModel.Arrival;
import com.ovapp.stepbystep.R;

public class ArrivalItemAdapter extends ArrayAdapter<Arrival>{

	private Context context;
	private int resource;
	private ArrayList<Arrival> data;
	
	public ArrivalItemAdapter(Context context, int resource, ArrayList<Arrival> data) {
		super(context, resource, data);
		this.context=context;
		this.resource=resource;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ArrivalViewHolder holder = null;
		
		if(row==null){
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(resource, parent, false);
			holder = new ArrivalViewHolder();
			holder.name = (TextView)row.findViewById(R.id.arrival_naam);
			holder.time = (TextView)row.findViewById(R.id.arrival_time);
			row.setTag(holder);
		}
		else{
			holder = (ArrivalViewHolder)row.getTag();
		}
		Arrival arrival = data.get(position);
		holder.name.setText(arrival.getName());
		holder.time.setText(arrival.getTime());
		return row;
	}
	
}
