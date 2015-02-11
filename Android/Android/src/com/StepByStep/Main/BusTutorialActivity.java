package com.StepByStep.Main;

import java.util.List;
import java.util.Vector;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.StepByStep.BusTutorialFragments.Bushalte;
import com.StepByStep.BusTutorialFragments.Busreis;
import com.StepByStep.BusTutorialFragments.BusreisBetalen;
import com.StepByStep.BusTutorialFragments.BusreisPlannen;
import com.StepByStep.BusTutorialFragments.Terugreis;
import com.StepByStep.PageViewer.PagerAdapter;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.ovapp.stepbystep.R;


public class BusTutorialActivity extends SherlockFragmentActivity implements OnPageChangeListener {
	
	final Context context = this;
	private PagerAdapter mPagerAdapter;
	private ViewPager pager;
	AlertDialog.Builder adb;
	TextView headerValue;
	TextView headerNum;
	ActionBar ab;
	View customView;
	ImageButton rightButton;
	ImageButton leftButton;
	ImageButton ib;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//set the layout of the activity
		setContentView(R.layout.viewpager_layout);
		ab = getSherlock().getActionBar();
		LayoutInflater li = LayoutInflater.from(this);
		customView = li.inflate(R.layout.tutorial_actionbar, null);
		pager = (ViewPager)super.findViewById(R.id.pager);
		headerValue = (TextView) customView.findViewById(R.id.pagetitle);
		ab.setCustomView(customView);
		
		//GET EXTRA DATA FROM INTENT
		Bundle extra = getIntent().getExtras(); 
		int position = extra.getInt("ItemID");
	    
		
		setPageTitle(position); //set the current pagetitle
	    
	    
	    //set the PageAdapter with the Fragments
	    this.initialisePaging();
	    
	    pager.setCurrentItem(position); // set the current item
	    setPageButtons();				// init the buttons and onclicklisteners
	    checkButtons(position);			// check for displaying the buttons
	}
	/**
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        super.onCreateOptionsMenu(menu);
	        getSupportMenuInflater().inflate(R.menu.activity_screen_slide, menu);
	        RelativeLayout relativelayout = (RelativeLayout) menu.findItem(R.id.layout_item).getActionView();
	        
	        View inflatedView = getLayoutInflater().inflate(R.layout.android_bottom_actionbar, null);
	        
	        relativelayout.addView(inflatedView);
	        return true;
	    }
	    
	
	 @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        switch (item.getItemId()) {
	            case android.R.id.home:
	                // Navigate "up" the demo structure to the launchpad activity.
	                // See http://developer.android.com/design/patterns/navigation.html for more.
	                NavUtils.navigateUpTo(this, new Intent(this, MainActivity.class));
	                return true;

	            case R.id.leftbutton:
	                // Go to the previous step in the wizard. If there is no previous step,
	                // setCurrentItem will do nothing.
	                pager.setCurrentItem(pager.getCurrentItem() - 1);
	                return true;

	            case R.id.rightbutton:
	                // Advance to the next step in the wizard. If there is no next step, setCurrentItem
	                // will do nothing.
	                pager.setCurrentItem(pager.getCurrentItem() + 1);
	                return true;
	        }
			return false;
	    }
	 */


	private void setPageButtons() {
		ib = (ImageButton)customView.findViewById(R.id.menubutton);
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
		}else if(position==4){
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
        fragments.add(Fragment.instantiate(this, BusreisPlannen.class.getName()));
        fragments.add(Fragment.instantiate(this, BusreisBetalen.class.getName()));
        fragments.add(Fragment.instantiate(this, Bushalte.class.getName()));
        fragments.add(Fragment.instantiate(this, Busreis.class.getName()));
        fragments.add(Fragment.instantiate(this, Terugreis.class.getName()));

        mPagerAdapter  = new PagerAdapter(getSupportFragmentManager(), fragments);
        
        pager.setAdapter(mPagerAdapter);
    }
    
    public void setPageTitle(int position){
    	switch(position){
    	case 0 : headerValue.setText( this.getString(R.string.bus_menu_text1));break;
    	case 1 : headerValue.setText( this.getString(R.string.bus_menu_text2));break;
    	case 2 : headerValue.setText( this.getString(R.string.bus_menu_text3));break;
    	case 3 : headerValue.setText( this.getString(R.string.bus_menu_text4));break;
    	case 4 : headerValue.setText( this.getString(R.string.bus_menu_text5));break;
    	default: headerValue.setText(this.getString(R.string.bus_menu_text1));break;
	}
    }
    
    
	@Override
	public void onPageScrollStateChanged(int arg0) {}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {}

	
	

	@Override
	protected void onPause() {
		super.onPause();
	}
	
	@Override
	protected void onResume(){
		super.onResume();
	}
	@Override
	public void onPageSelected(int arg0) {
		
		
	}
	
	public void internetDialog(View view) {
		 new OpenDialog(R.layout.bus_dialog_internet, context);	 
	}
	    
    public void telephoneDialog(View view) {
    	new OpenDialog(R.layout.bus_dialog_telephone, context);
    }
	    
    public void bushalteDialog(View view) {
    	new OpenDialog(R.layout.bus_dialog_bushalte, context);
    }
	
    public void eurokaartjeDialog(View view) {
    	new OpenDialog(R.layout.bus_dialog_eurokaartje, context);
    }
    
    public void ovchipkaartDialog(View view) {
    	new OpenDialog(R.layout.bus_dialog_ovchipkaart, context);
    }
    
    public void playVideo(View view) {
    	
    	Intent intent = new Intent(getBaseContext(), VideoActivity.class);
    	intent.putExtra("VideoType", "bus");
    	startActivity(intent);
    }
	
	
}
