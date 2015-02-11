package com.StepByStep.Main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.StepByStep.ListViewController.ItemAdapter;
import com.StepByStepModel.Item;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.ovapp.stepbystep.R;

public class OVchipkaart extends SherlockActivity{

	ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		ActionBar ab = getSherlock().getActionBar();
		LayoutInflater li = LayoutInflater.from(this);
		
		//set the listview
	    View customView = li.inflate(R.layout.tutorialmenu_actionbar, null);
	    TextView textview = (TextView)customView.findViewById(R.id.pagetitle);
	    textview.setText(R.string.menu_text3);
	    ab.setCustomView(customView);
	    ImageButton ib = (ImageButton)customView.findViewById(R.id.Menubutton_tutorial);
	    ib.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				
				finish();
			}
	    	});
	    
	    Item data[] = new Item[]
	    		{
                new Item(R.drawable.right_button_dark,R.string.chipkaart_menu_text_1,1),
                new Item(R.drawable.right_button_dark,R.string.chipkaart_menu_text_2,2),
                new Item(R.drawable.right_button_dark,R.string.chipkaart_menu_text_3,3),
                new Item(R.drawable.right_button_dark,R.string.chipkaart_menu_text_4,4),
                new Item(R.drawable.right_button_dark,R.string.chipkaart_menu_text_5,5),
                new Item(R.drawable.right_button_dark,R.string.chipkaart_menu_text_6,6),
                new Item(R.drawable.right_button_dark,R.string.chipkaart_menu_text_7,7)
	    		};
	            
	            ItemAdapter adapter = new ItemAdapter(this, 
	                    R.layout.tutorialmenulistitem, data);
	            
	            
	            listView = (ListView)findViewById(R.id.list);
	            listView.setAdapter(adapter);
	            listView.setOnItemClickListener(new OnItemClickListener(){
	            	public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
	                {
	            				Intent newIntent = new Intent(getBaseContext(), OVChipKaartTutorialActivity.class);
	            				newIntent.putExtra("ItemID", position);
	            				startActivity(newIntent);
	                }});
	}
}

