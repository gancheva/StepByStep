package com.StepByStep.TrainFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.StepByStep.PageViewer.CustomFragment;
import com.actionbarsherlock.app.SherlockFragment;
import com.ovapp.stepbystep.R;

public class TreinInchecken extends SherlockFragment implements CustomFragment{

	private int title = R.string.train_menu_text3;
	
	public TreinInchecken() {
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
        View view = inflater.inflate(R.layout.trein_inchecken, container, false);
        return view;
    }

	@Override
	public int getTitle() {
		return title;
	}
}
