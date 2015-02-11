package com.StepByStep.Main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.StepByStep.ListViewController.ItemAdapter;
import com.StepByStepModel.Item;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.ovapp.stepbystep.R;

public class MainMenu extends SherlockActivity {
	
	ListView listView ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		checkFirstTime();
		setContentView(R.layout.activity_main_menu);
		ActionBar ab = getSherlock().getActionBar();
		LayoutInflater li = LayoutInflater.from(this);
	    View customView = li.inflate(R.layout.mainmenu_actionbar, null);
	    ab.setCustomView(customView);
	    
	    Item data[] = new Item[]
	            {
	                new Item(R.drawable.bus,R.string.menu_text1),
	                new Item(R.drawable.trein, R.string.menu_text2),
	                new Item(R.drawable.chipkaart,R.string.menu_text3),
	                new Item(R.drawable.bushalteic,R.string.menu_text4),
	                new Item(R.drawable.uitleg,R.string.menu_text5)
	            };
	            
	            ItemAdapter adapter = new ItemAdapter(this, 
	                    R.layout.mainmenulistitem, data);
	            
	            
	            listView = (ListView)findViewById(R.id.list);
	            listView.setAdapter(adapter);
	            listView.setOnItemClickListener(new OnItemClickListener(){
	            	public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
	                {
	            				Item item = (Item)listView.getItemAtPosition(position);
	            				String text = getstring(getBaseContext(), item.text);
	            				Intent newIntent;
	            				if(text.equals(getString(R.string.menu_text1))){
	            					newIntent = new Intent(getBaseContext(), Travelbybus.class);
	            					startActivity(newIntent);
	            				}
	            				else if(text.equals(getString(R.string.menu_text2))){
	            					newIntent = new Intent(getBaseContext(), TravelByTrain.class);
	            					startActivity(newIntent);
	            				}
	            				else if(text.equals(getString(R.string.menu_text3))){
	            					newIntent = new Intent(getBaseContext(), OVchipkaart.class);
	            					startActivity(newIntent);
	            				}
	            				else if(text.equals(getString(R.string.menu_text4))){
	            					newIntent = new Intent(getBaseContext(), BusStops.class );
	            					startActivity(newIntent);
	            				}
	            				else if(text.equals(getString(R.string.menu_text5))){
	            					newIntent = new Intent(getBaseContext(), ApplicationExplanation.class);
	            					startActivity(newIntent);
	            				}
	                }});
	            	
	            	
	        }
	
		private String getstring(Context context, int id){
		return context.getResources().getString(id).toString();
	}
	
	private void checkFirstTime(){
		SharedPreferences prefs = this.getSharedPreferences("com.ovapp.stepbystep", Context.MODE_PRIVATE);
		if(!prefs.contains("first_time")){
			Intent intent = new Intent(getBaseContext(), FirstTimeScreen.class);
			startActivity(intent);
		}
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		checkFirstTime();
	}
	
}
