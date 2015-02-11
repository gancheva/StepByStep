package com.StepByStep.Main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.ovapp.stepbystep.R;

public class ApplicationExplanation extends SherlockActivity {

	final Context context = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_application_explanation);
		ActionBar ab = getSherlock().getActionBar();
		LayoutInflater li = LayoutInflater.from(this);
	    View customView = li.inflate(R.layout.default_actionbar, null);
	    TextView tview = (TextView)customView.findViewById(R.id.pagetitle);
	    tview.setText(R.string.menu_text5);
	    ab.setCustomView(customView);
	    
	    ImageButton ib = (ImageButton)customView.findViewById(R.id.menubuttonapp);
	    ib.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				finish();
			}
	    	});
	    
	}
	
	public void informationDialogExample(View view) {
		new OpenDialog(R.layout.application_explenation_information_dialog, context);
	}
}
