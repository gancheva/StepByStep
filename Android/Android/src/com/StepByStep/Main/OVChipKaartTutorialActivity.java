package com.StepByStep.Main;

import java.util.List;
import java.util.Vector;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.StepByStep.OVChipKaartTutorialFragments.*;
import com.StepByStep.PageViewer.PagerAdapter;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.ovapp.stepbystep.R;



public class OVChipKaartTutorialActivity extends SherlockFragmentActivity implements OnPageChangeListener {
	private PagerAdapter mPagerAdapter;
	private ViewPager pager;
	 AlertDialog.Builder adb;
	 TextView headerValue;
	 View customView;
	 ImageButton rightButton;
	 ImageButton leftButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//set the layout of the activity
		setContentView(R.layout.viewpager_layout);
		ActionBar ab = getSherlock().getActionBar();
		LayoutInflater li = LayoutInflater.from(this);
		customView = li.inflate(R.layout.tutorial_actionbar, null);
		headerValue = (TextView) customView.findViewById(R.id.pagetitle);
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
		}else if(position==6){
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
        fragments.add(Fragment.instantiate(this, OVChipKaart.class.getName()));
        fragments.add(Fragment.instantiate(this, OVChipKaartKopen.class.getName()));
        fragments.add(Fragment.instantiate(this, OVChipVoldoendeSaldo.class.getName()));
        fragments.add(Fragment.instantiate(this, OVChipKaartBus.class.getName()));			 
        fragments.add(Fragment.instantiate(this, OVChipKaartTrein.class.getName()));	
        fragments.add(Fragment.instantiate(this, OVChipKaartVergetenUitchecken.class.getName()));
        fragments.add(Fragment.instantiate(this, OVChipKaartKapot.class.getName()));
        
        mPagerAdapter  = new PagerAdapter(getSupportFragmentManager(), fragments);
        //
        pager = (ViewPager)super.findViewById(R.id.pager);
        pager.setAdapter(mPagerAdapter);
    }
    
    public void setPageTitle(int position){
    	switch(position){
    	case 0 : headerValue.setText( this.getString(R.string.chipkaart_menu_text_1));break;
    	case 1 : headerValue.setText( this.getString(R.string.chipkaart_menu_text_2));break;
    	case 2 : headerValue.setText( this.getString(R.string.chipkaart_menu_text_3));break;
    	case 3 : headerValue.setText( this.getString(R.string.chipkaart_menu_text_4));break;
    	case 4 : headerValue.setText( this.getString(R.string.chipkaart_menu_text_5));break;
    	case 5 : headerValue.setText( this.getString(R.string.chipkaart_menu_text_6));break;
    	case 6 : headerValue.setText( this.getString(R.string.chipkaart_menu_text_7));break;
    	default: headerValue.setText(this.getString(R.string.bus_menu_text1));break;
    	}
    }

	@Override
	public void onPageScrollStateChanged(int arg0) {}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {}

	@Override
	public void onPageSelected(int position) {
	}
	
	
	
}
