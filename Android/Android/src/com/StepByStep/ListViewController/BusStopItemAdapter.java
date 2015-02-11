package com.StepByStep.ListViewController;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.StepByStepModel.BusStop;
import com.ovapp.stepbystep.R;

public class BusStopItemAdapter extends ArrayAdapter<BusStop>{

	private Context context; 
    private int layoutResourceId;    
    private ArrayList<BusStop> objects = null;
	
	public BusStopItemAdapter(Context context, int layoutResourceId, ArrayList<BusStop> objects) {
		super(context, layoutResourceId, objects);
		this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.objects = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
        BusStopViewHolder holder = null;
        
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            
            holder = new BusStopViewHolder();
            holder.text = (TextView)row.findViewById(R.id.busstop_text);
            holder.count = (TextView)row.findViewById(R.id.busstop_text_number);
            holder.distance = (TextView)row.findViewById(R.id.busstop_text_distance);
            row.setTag(holder);
        }
        else
        {
            holder = (BusStopViewHolder)row.getTag();
        }
        BusStop busstop = objects.get(position);
        holder.count.setText(Integer.toString(busstop.getCount()));
        holder.text.setText(busstop.getName());
        holder.distance.setText(busstop.getDistanceString());
        return row;
	}
	

}
