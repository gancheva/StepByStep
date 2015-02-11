package com.StepByStep.Main;

import java.util.ArrayList;
import java.util.List;

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.ScaleBarOverlay;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.StepByStepModel.GPSTracker;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.ovapp.stepbystep.R;

public class MapActivity extends SherlockActivity {

	private MapView myOpenMapView;
	private MapController myMapController;
	ArrayList<OverlayItem> overlayItemArray;
	private GPSTracker gps;
	View customView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapview);
		ActionBar ab = getSherlock().getActionBar();
		LayoutInflater li = LayoutInflater.from(this);
		customView = li.inflate(R.layout.default_actionbar, null);
		
		//set the pagetitle
	    TextView textview = (TextView)customView.findViewById(R.id.pagetitle);
	    textview.setText(R.string.menu_text4);
	    buttonsSetup();
	    ab.setCustomView(customView);

		myOpenMapView = (MapView) findViewById(R.id.openmapview);
		myOpenMapView.setBuiltInZoomControls(true);
		// myOpenMapView.setMultiTouchControls(true); // for pinch to zoom
		myMapController = (MapController) myOpenMapView.getController();
		myMapController.setZoom(16);
		Bundle b = getIntent().getExtras();
		double[] busstoplocationslatitude = b.getDoubleArray("latPoints");
		double[] busstoplocationslongitude = b.getDoubleArray("lonPoints");
		// --- Create Overlay
		overlayItemArray = new ArrayList<OverlayItem>();
		setMapMarkers(busstoplocationslatitude, busstoplocationslongitude);
		
		DefaultResourceProxyImpl defaultResourceProxyImpl = new DefaultResourceProxyImpl(this);
		MyItemizedIconOverlay myItemizedIconOverlay = new MyItemizedIconOverlay(overlayItemArray, null, defaultResourceProxyImpl);
		
		myOpenMapView.getOverlays().add(myItemizedIconOverlay);
		
		//get the current location
		gps = new GPSTracker(this);
		if(gps.canGetLocation()){
			updateLoc(gps.getLocation());
		}


		// Add Scale Bar
		ScaleBarOverlay myScaleBarOverlay = new ScaleBarOverlay(this);
		myOpenMapView.getOverlays().add(myScaleBarOverlay);
	}

	private ArrayList<OverlayItem> setMapMarkers(double[] busstoplocationslatitude, double[] busstoplocationslongitude) {
			for(int i =0;i<busstoplocationslatitude.length;i++){
					double lat = busstoplocationslatitude[i];
					double lon = busstoplocationslongitude[i];
					Log.i("","location:"+i+"latitude = "+lat+"longitude ="+lon);
					overlayItemArray.add(new OverlayItem("0, 0", "0, 0", new GeoPoint(
							(int) (lat * 1E6), (int)(lon * 1E6))));
			}
			
			Drawable icon = this.getResources().getDrawable(R.drawable.busstop_marker);
	        icon.setBounds(- icon.getIntrinsicWidth() / 2, - icon.getIntrinsicHeight(), icon.getIntrinsicWidth() / 2, 0);
	        
	        for (int i = 0; overlayItemArray.size() > i; i++) {
	        	overlayItemArray.get(i).setMarker(icon);
	        }
	        
			return overlayItemArray;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		updateLoc(gps.getLocation());
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		gps.stopUsingGPS();
	}

	private void updateLoc(Location loc) {
		GeoPoint locGeoPoint = new GeoPoint(loc.getLatitude(),
				loc.getLongitude());
		myMapController.setCenter(locGeoPoint);

		setOverlayLoc(loc);

		myOpenMapView.invalidate();
	}

	private void setOverlayLoc(Location overlayloc) {
		GeoPoint overlocGeoPoint = new GeoPoint(overlayloc);
		// ---
		overlayItemArray.clear();

		OverlayItem newMyLocationItem = new OverlayItem("My Location",
				"My Location", overlocGeoPoint);
		overlayItemArray.add(newMyLocationItem);
		// ---
	}

	private class MyItemizedIconOverlay extends
			ItemizedIconOverlay<OverlayItem> {

		public MyItemizedIconOverlay(
				List<OverlayItem> pList,
				org.osmdroid.views.overlay.ItemizedIconOverlay.OnItemGestureListener<OverlayItem> pOnItemGestureListener,
				ResourceProxy pResourceProxy) {
			super(pList, pOnItemGestureListener, pResourceProxy);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void draw(Canvas canvas, MapView mapview, boolean arg2) {
			// TODO Auto-generated method stub
			super.draw(canvas, mapview, arg2);
			if (!overlayItemArray.isEmpty()) {

				// overlayItemArray have only ONE element only, so I hard code
				// to get(0)
				GeoPoint in = overlayItemArray.get(0).getPoint();

				Point out = new Point();
				mapview.getProjection().toPixels(in, out);

				Bitmap bm = BitmapFactory.decodeResource(getResources(),
						R.drawable.icon_location);
				canvas.drawBitmap(bm, out.x - bm.getWidth() / 2, // shift the
																	// bitmap
																	// center
						out.y - bm.getHeight() / 2, // shift the bitmap center
						null);
			}
		}

		@Override
		public boolean onSingleTapUp(MotionEvent event, MapView mapView) {
			// TODO Auto-generated method stub
			// return super.onSingleTapUp(event, mapView);
			return true;
		}
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
}
