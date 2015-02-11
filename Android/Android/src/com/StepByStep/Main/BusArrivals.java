package com.StepByStep.Main;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.StepByStep.ListViewController.ArrivalItemAdapter;
import com.StepByStepModel.Arrival;
import com.StepByStepModel.ArrivalData;
import com.StepByStepModel.BusStop;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.ovapp.stepbystep.R;

public class BusArrivals extends SherlockActivity {

	private View customView;
	private ListView listView;
	private BusStop busstop;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.busstop_listview);
		ActionBar ab = getSherlock().getActionBar();
		LayoutInflater li = LayoutInflater.from(this);
	    customView = li.inflate(R.layout.default_actionbar, null);
	    getBustopFromIntent();
	    Log.i("BusStopID","AgencyID="+busstop.getAgencyID());
	    
	    //set the pagetitle
	    TextView textview = (TextView)customView.findViewById(R.id.pagetitle);
	    textview.setText(R.string.title_activity_bus_arrivals);  
	    ab.setCustomView(customView);
	    buttonsSetup();
	    listView = (ListView) findViewById(R.id.busstoplist);
	    if(isOnline()){
	    	new BusStopAsynctask(this, busstop).execute();
	    }else{
	    	AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
	         // Setting Dialog Title
	    	alertDialog.setTitle(R.string.no_internet_title);
	         // Setting Dialog Message
	         alertDialog.setMessage(R.string.no_internet_text);
	         // on pressing cancel button
	         alertDialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
	             public void onClick(DialogInterface dialog, int which) {
	            	 dialog.cancel();
		             finish();
	             }
	         });
	         // Showing Alert Message
	         alertDialog.show();
	    }
	}
	
	/**
	 * method to check if the phone has an active internet connection
	 * @return bolean true or false;
	 */
	private boolean isOnline()
    {
        try
        {
            ConnectivityManager cm = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo().isConnectedOrConnecting(); 
        }
        catch (Exception e)
        {
            return false;
        }
    }
	
	private void getBustopFromIntent() {
		Bundle b = getIntent().getExtras();
		String agencyId = b.getString("agencyID");
		String busSTopID = b.getString("busStopID");
		Double lat = b.getDouble("lat");
		Double lon = b.getDouble("lon");
		busstop = new BusStop(agencyId, busSTopID, lat, lon);
	}

		//Method to setup the menubutton
		private void buttonsSetup() {
			ImageButton imagebutton = (ImageButton)customView.findViewById(R.id.menubuttonapp);
			imagebutton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					finish();
				}
			});
		}
		
		private class BusStopAsynctask extends AsyncTask<String, Void, ArrayList<Arrival>>{
			// CAST THE LINEARLAYOUT HOLDING THE MAIN PROGRESS (SPINNER)
			LinearLayout linlaHeaderProgress = (LinearLayout) findViewById(R.id.linlaHeaderProgress);
			ArrivalData bdata;
			BusArrivals arrivals;
			BusStop busstop;
			
			public BusStopAsynctask(BusArrivals busarrival, BusStop busstop) {
				this.arrivals = busarrival;
				this.busstop=busstop;
			}

			@Override
			protected ArrayList<Arrival> doInBackground(String...args0) {
				bdata = new ArrivalData(busstop);
				bdata.getRealData();
				return bdata.getArrivalsList();
			}

			@Override
			protected void onPostExecute(ArrayList<Arrival> result) {
				super.onPostExecute(result);
				// HIDE THE SPINNER AFTER LOADING FEEDS
			    linlaHeaderProgress.setVisibility(View.GONE);
				if(result.size()!=0){
					ArrivalItemAdapter adapter = new ArrivalItemAdapter(arrivals,R.layout.arrival_list_item, result);
					listView.setAdapter(adapter);
				}else{
					 AlertDialog.Builder alertDialog = new AlertDialog.Builder(BusArrivals.this);
			         // Setting Dialog Title
					 alertDialog.setTitle(R.string.no_busstops_title);
			         // Setting Dialog Message
			         alertDialog.setMessage(R.string.no_busstops_text);
			         // on pressing cancel button
			         alertDialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
			             public void onClick(DialogInterface dialog, int which) {
			            	 dialog.cancel();
				             finish();
			             }
			         });
			         // Showing Alert Message
			         alertDialog.show();
				}
			    
			}

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				// SHOW THE SPINNER WHILE LOADING FEEDS
			    linlaHeaderProgress.setVisibility(View.VISIBLE);
			}		
		}
}
