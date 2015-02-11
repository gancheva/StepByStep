package Itract_API;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.location.Location;
import android.util.Log;

import com.StepByStepModel.BusStop;

public class BusStopJSONParser {

	private ArrayList<BusStop> busstops;
	private Location myLocation;
	
	public BusStopJSONParser(Location location){
		busstops = new ArrayList<BusStop>();
		myLocation = location;
	}
	
	/*
	 * method to parse the given json array
	 * busstops are added to the BusStops Array
	 */
	public ArrayList<BusStop>parseJson(JSONArray object){
		try {
			if(object.length()!=0){
				JSONObject jobject = object.optJSONObject(0);
				JSONArray jarray = jobject.getJSONArray("data");
				for(int i =0;i<jarray.length();i++){
					JSONObject jsonobject = jarray.getJSONObject(i);
					createBusStopFromJsonObject(jsonobject, i);
				}
				return busstops;
			}else{
				return busstops;
			}
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void createBusStopFromJsonObject(JSONObject obj, int i){
		
		 try {
			 BusStop busstop;
			 String id = obj.getString("id");
			 String name = obj.getString("name");
			 String agencyID = obj.getString("agencyId");
			 Double lat = obj.getDouble("lat");
			 Double lon = obj.getDouble("lon");
			 busstop = new BusStop(id,name,agencyID,lat,lon);
			 busstop.setDistanceFromCurrentLocation(myLocation);
			 Log.i("distance","distance:"+busstop.getDistance());
			 busstop.setCount(i+1);
			 busstops.add(busstop);
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
	}
}
