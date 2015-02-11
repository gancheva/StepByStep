package com.StepByStep.ListViewController;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.StepByStepModel.Item;
import com.ovapp.stepbystep.R;


public class ItemAdapter extends ArrayAdapter<Item>{

    Context context; 
    int layoutResourceId;    
    Item data[] = null;
    
    public ItemAdapter(Context context, int layoutResourceId, Item[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ItemViewHolder holder = null;
        
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            
            holder = new ItemViewHolder();
            holder.icon = (ImageView)row.findViewById(R.id.imgIcon);
            holder.text = (TextView)row.findViewById(R.id.txtTitle);
            holder.count = (TextView)row.findViewById(R.id.textCount);
            row.setTag(holder);
        }
        else
        {
            holder = (ItemViewHolder)row.getTag();
        }
        
        Item Item = data[position];
        if(Item.count!=0){
        	holder.count.setText(Integer.toString(Item.count));
        }
        holder.text.setText(Item.text);
        holder.icon.setImageResource(Item.icon);
        return row;
    }
}