package com.StepByStep.BusTutorialFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.StepByStep.PageViewer.CustomFragment;
import com.actionbarsherlock.app.SherlockFragment;
import com.ovapp.stepbystep.R;

public class Terugreis extends SherlockFragment implements CustomFragment{

	private int title = R.string.bus_menu_text5;
	
	public Terugreis() {
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
        View view = inflater.inflate(R.layout.bus_tutorial_terugreis, container, false);
        return view;
    }
    
    @Override
	public int getTitle() {
		return title;
	}
}
