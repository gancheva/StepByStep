package com.StepByStep.Main;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.StepByStep.ListViewController.BusStopItemAdapter;
import com.StepByStepModel.BusStop;
import com.StepByStepModel.BusStopData;
import com.StepByStepModel.GPSTracker;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.ovapp.stepbystep.R;

public class BusStops extends SherlockActivity{

	private View customView;
	private ListView listView;
	private TextView pagetitle;
	private Location lastLocation;
	private ArrayList<BusStop> busstopsList;
	private GPSTracker gps;
	ImageButton imagebuttonMAP;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.busstop_listview);
		ActionBar ab = getSherlock().getActionBar();
		LayoutInflater li = LayoutInflater.from(this);
		customView = li.inflate(R.layout.busstop_actionbar_layout, null);
		// set the pagetitle
		pagetitle = (TextView) customView.findViewById(R.id.pagetitle);
		pagetitle.setText(R.string.menu_text4);
		buttonsSetup();
		ab.setCustomView(customView);
		listView = (ListView) findViewById(R.id.busstoplist);
		//Create GPSTracker and get the current location
		gps = new GPSTracker(this);
		lastLocation = new Location("busStopLocation");
	    if(isOnline()){
	    	if(gps.canGetLocation()){
				lastLocation=gps.getLocation();
				new BusStopAsynctask().execute();
			}else{
				showSettingsAlert();
			}
	    }else{
	    	AlertDialog.Builder alertDialog = new AlertDialog.Builder(BusStops.this);
	         // Setting Dialog Title
	         alertDialog.setTitle(R.string.no_internet_title);
	         // Setting Dialog Message
	         alertDialog.setMessage(R.string.no_internet_text);
	         // on pressing cancel button
	         alertDialog.setNegativeButton(R.string.button_ok, new DialogInterface.OnClickListener() {
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
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		lastLocation=gps.getLocation();
		new BusStopAsynctask().execute();
	}
	
	// Method to setup the menubutton
	private void buttonsSetup() {
		ImageButton imagebutton = (ImageButton) customView
				.findViewById(R.id.menubuttonapp);
		imagebutton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		imagebuttonMAP = (ImageButton) customView
				.findViewById(R.id.mapbutton);
		imagebuttonMAP.setVisibility(View.GONE);
		imagebuttonMAP.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent newIntent = new Intent(getBaseContext(),
						MapActivity.class);
				double[] busstoplocationslatitude = new double[busstopsList.size()];
				double[] busstoplocationslongitude = new double[busstopsList.size()];
				Bundle b = new Bundle();

				//get the latitude
				for(int i =0;i<busstopsList.size();i++){
					busstoplocationslatitude[i]= busstopsList.get(i).getLocation().getLatitude();
					busstoplocationslongitude[i]= busstopsList.get(i).getLocation().getLongitude();
					Log.i("Location","Location Latitude index"+i+" ="+busstopsList.get(i).getLocation().getLatitude());
					Log.i("Location","Location Longitude index="+i+" ="+busstopsList.get(i).getLocation().getLongitude());
				}
				b.putDoubleArray("latPoints", busstoplocationslatitude);
				b.putDoubleArray("lonPoints", busstoplocationslongitude);
				newIntent.putExtras(b);
				startActivity(newIntent);
			}
		});
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
	
	public void showSettingsAlert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
      
        // Setting Dialog Title
        alertDialog.setTitle(R.string.no_gps_title);
  
        // Setting Dialog Message
        alertDialog.setMessage(R.string.no_gps_text);
  
        // On pressing Settings button
        alertDialog.setPositiveButton(R.string.button_settings, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(intent, 1);
            }
        });
  
        // on pressing cancel button
        alertDialog.setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            if(gps.isNetworkEnabled()){
            	lastLocation = gps.getLocation();
            	new BusStopAsynctask().execute();
            	dialog.cancel();
            }else{
            dialog.cancel();
            finish();
            }
            }
        });
  
        // Showing Alert Message
        alertDialog.show();
    }
	
	private class BusStopAsynctask extends AsyncTask<String, Void, ArrayList<BusStop>>{
		// CAST THE LINEARLAYOUT HOLDING THE MAIN PROGRESS (SPINNER)
		LinearLayout linlaHeaderProgress = (LinearLayout) findViewById(R.id.linlaHeaderProgress);
		BusStopData bdata;

		@Override
		protected ArrayList<BusStop> doInBackground(String...arg0) {
			bdata = new BusStopData(lastLocation);
			busstopsList = bdata.getBusStopsList();
			return busstopsList;
		}

		@Override
		protected void onPostExecute(ArrayList<BusStop> result) {
			super.onPostExecute(result);
			// HIDE THE SPINNER AFTER LOADING FEEDS
		    linlaHeaderProgress.setVisibility(View.GONE);
		    if(result.size()==0){
		    	 AlertDialog.Builder alertDialog = new AlertDialog.Builder(BusStops.this);
		         // Setting Dialog Title
		         alertDialog.setTitle(R.string.no_busstops_title);
		         // Setting Dialog Message
		         alertDialog.setMessage(R.string.no_busstops_text);
		         // on pressing cancel button
		         alertDialog.setNegativeButton(R.string.button_ok, new DialogInterface.OnClickListener() {
		             public void onClick(DialogInterface dialog, int which) {
		            	 dialog.cancel();
			             finish();
		             }
		         });
		         // Showing Alert Message
		         alertDialog.show();
		    }else{
		    	imagebuttonMAP.setVisibility(View.VISIBLE);
				BusStopItemAdapter adapter = new BusStopItemAdapter(BusStops.this,R.layout.busstoplistitem, result);
				listView.setAdapter(adapter);
				listView.setOnItemClickListener(new OnItemClickListener() {

					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						BusStop busstop = (BusStop) listView
								.getItemAtPosition(position);
						Intent newIntent = new Intent(getBaseContext(),
								BusArrivals.class);
						//TODO send agencyID, StopID, lat and lon
						Bundle b = new Bundle();
						b.putString("busStopID", busstop.getBusStopID());
						b.putString("agencyID", busstop.getAgencyID());
						b.putDouble("lat", busstop.getLatitude());
						b.putDouble("lon", busstop.getLongitude());
						newIntent.putExtras(b);
						startActivity(newIntent);
					}
				});
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
