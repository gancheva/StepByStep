package com.StepByStep.Main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.ovapp.stepbystep.R;

public class FirstTimeScreen extends SherlockActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_time_screen_activity);
		ActionBar ab = getSherlock().getActionBar();
		LayoutInflater li = LayoutInflater.from(this);
	    View customView = li.inflate(R.layout.first_time_screen_actionbar_layout, null);
	    ab.setCustomView(customView);
	    setSharedPref();//set shared pref for first time
	    ImageButton rightButton = (ImageButton)findViewById(R.id.first_time_screen_button);
	    rightButton.setOnClickListener(new OnClickListener() {

	     @Override
	           public void onClick(View view) {
	    	 	Intent newIntent = new Intent(getBaseContext(), ApplicationExplanationFirstTime.class);
				startActivity(newIntent);
	     }
	        });
	}
	
	private void setSharedPref(){
		SharedPreferences sharedpreferences = getSharedPreferences("com.ovapp.stepbystep",   MODE_PRIVATE);        
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean("first_time",true);
        editor.commit(); 
        
	}
	
	public void removeSharedPref(){
		SharedPreferences sharedpreferences = getSharedPreferences("com.ovapp.stepbystep",   MODE_PRIVATE);        
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
	}
	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	        moveTaskToBack(true);
	        removeSharedPref();
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
	}
	
	@Override
	protected void onResume(){
		super.onResume();
	}
	

}
