package com.StepByStep.OVChipKaartTutorialFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.ovapp.stepbystep.R;

public class OVChipKaartVergetenUitchecken extends SherlockFragment {
	
	public OVChipKaartVergetenUitchecken() {
		
	}
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	    }
	 
	    @Override
	    public void onActivityCreated(Bundle savedInstanceState) {
	        super.onActivityCreated(savedInstanceState);
	        
	    }
	 
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        View view = inflater.inflate(R.layout.ov_chipkaart_vergeten_uitchecken, container, false);
	        return view;
	    }

}
