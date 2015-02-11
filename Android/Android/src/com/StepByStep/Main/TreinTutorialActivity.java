package com.StepByStep.Main;

import java.util.List;
import java.util.Vector;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.StepByStep.PageViewer.PagerAdapter;
import com.StepByStep.TrainFragments.TreinInchecken;
import com.StepByStep.TrainFragments.TreinStation;
import com.StepByStep.TrainFragments.TreinTerugreis;
import com.StepByStep.TrainFragments.TreinUitchecken;
import com.StepByStep.TrainFragments.Treinreis;
import com.StepByStep.TrainFragments.TreinreisPlannen;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.ovapp.stepbystep.R;

public class TreinTutorialActivity extends SherlockFragmentActivity implements OnPageChangeListener {
	
	final Context context = this;
	private PagerAdapter mPagerAdapter;
	private ViewPager pager;
	 AlertDialog.Builder adb;
	 TextView headerText;
	 ImageButton rightButton;
	 ImageButton leftButton;
	 View customView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//set the layout of the activity
		setContentView(R.layout.viewpager_layout);
		ActionBar ab = getSherlock().getActionBar();
		LayoutInflater li = LayoutInflater.from(this);
		customView = li.inflate(R.layout.tutorial_actionbar, null);
		headerText = (TextView) customView.findViewById(R.id.pagetitle);
	    ab.setCustomView(customView);
	    
	    Bundle extra = getIntent().getExtras();
	    int position = extra.getInt("ItemID");
	    setPageTitle(position);
	    
	    //set the Page with the Fragments
	    this.initialisePaging();
	    pager.setCurrentItem(position);
	    setPageButtons();
	    checkButtons(position);    
	}

	private void setPageButtons() {
		ImageButton ib = (ImageButton)customView.findViewById(R.id.menubutton);
	    ib.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				
				finish();
			}
	    	});
	    
	    //left backbutton
	    leftButton = (ImageButton)findViewById(R.id.leftbutton);
	    leftButton.setOnClickListener(new OnClickListener() {

	     @Override
	           public void onClick(View view) {
	               pager.setCurrentItem(pager.getCurrentItem()-1, true); //getItem(-1) for previous
	               setPageTitle(pager.getCurrentItem());
	               checkButtons(pager.getCurrentItem());
	     }
	        });
	    
	  //right button
	    rightButton = (ImageButton)findViewById(R.id.rightbutton);
	    rightButton.setOnClickListener(new OnClickListener() {

	     @Override
	           public void onClick(View view) {
	               pager.setCurrentItem(pager.getCurrentItem()+1, true); //getItem(+1) for next
	               setPageTitle(pager.getCurrentItem());
	               checkButtons(pager.getCurrentItem());
	     		}
	        });
	}
	
	private void checkButtons(int position){
		if(position==0){
			leftButton.setVisibility(View.GONE);
		}else if(position==5){
			rightButton.setVisibility(View.GONE);
		}else{
			rightButton.setVisibility(View.VISIBLE);
			leftButton.setVisibility(View.VISIBLE);
		}
	}
	
	/**
     * Initialise the fragments to be paged
     */
    private void initialisePaging() {
 
        List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(this, TreinreisPlannen.class.getName()));
        fragments.add(Fragment.instantiate(this, TreinStation.class.getName()));
        fragments.add(Fragment.instantiate(this, TreinInchecken.class.getName()));
        fragments.add(Fragment.instantiate(this, Treinreis.class.getName()));			 
        fragments.add(Fragment.instantiate(this, TreinUitchecken.class.getName()));	
        fragments.add(Fragment.instantiate(this, TreinTerugreis.class.getName()));
        
        mPagerAdapter  = new PagerAdapter(getSupportFragmentManager(), fragments);
        //
        pager = (ViewPager)super.findViewById(R.id.pager);
        pager.setAdapter(mPagerAdapter);
    }
    
    public void setPageTitle(int position){
    	switch(position){
    	case 0 : headerText.setText( this.getString(R.string.train_menu_text1));break;
    	case 1 : headerText.setText( this.getString(R.string.train_menu_text2));break;
    	case 2 : headerText.setText( this.getString(R.string.train_menu_text3));break;
    	case 3 : headerText.setText( this.getString(R.string.train_menu_text4));break;
    	case 4 : headerText.setText( this.getString(R.string.train_menu_text5));break;
    	case 5 : headerText.setText( this.getString(R.string.train_menu_text6));break;
    	default: headerText.setText(this.getString(R.string.train_menu_text1));break;
    	}
    }

	@Override
	public void onPageScrollStateChanged(int arg0) {}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {}

	@Override
	public void onPageSelected(int position) {
	}
	
	public void negenTweeNegenTweeDialog(View view) {
		new OpenDialog(R.layout.train_dialog_negentwee, context);
    }
	
	 public void nsDialog(View view) {
		 new OpenDialog(R.layout.train_dialog_ns, context);
    }
	 
	 public void arrivaDialog(View view) {
		 new OpenDialog(R.layout.train_dialog_arriva, context);
    }
	
	
	
	
}
