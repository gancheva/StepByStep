package com.StepByStep.Main;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.ovapp.stepbystep.R;

public class ApplicationExplanationFirstTime extends SherlockActivity {
	View customView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_application_explanation);
		ActionBar ab = getSherlock().getActionBar();
		LayoutInflater li = LayoutInflater.from(this);
		customView = li.inflate(R.layout.application_explenation_first_time, null);
		TextView tview = (TextView)customView.findViewById(R.id.first_time_screen_textview);
	    tview.setText(R.string.menu_text5);
		ab.setCustomView(customView);
	    
	    ImageButton lb = (ImageButton)customView.findViewById(R.id.application_expl_left_button);
	    lb.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent newIntent = new Intent(getBaseContext(), FirstTimeScreen.class);
				startActivity(newIntent);
			}
	    	});
	    
	    ImageButton rb = (ImageButton)customView.findViewById(R.id.application_expl_right_button);
	    rb.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent newIntent = new Intent(getBaseContext(), MainMenu.class);
				newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
				newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(newIntent);
			}
	    	});
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	    	Intent newIntent = new Intent(getBaseContext(), FirstTimeScreen.class);
			startActivity(newIntent);
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
}
