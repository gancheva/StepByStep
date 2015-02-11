package Itract_API;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.StepByStepModel.Arrival;

public class ArrivalsJSONParser {
	private ArrayList<Arrival> arrivals;
	
	public ArrivalsJSONParser(){
		arrivals = new ArrayList<Arrival>();
	}
	
	/*
	 * method to parse the given json array
	 * arrivals are added to the arrival Array
	 */
	public ArrayList<Arrival>parseJson(JSONArray object){
		try {
			if(object.length()!=0){
				//get the first json object
				JSONObject jobject1 = object.getJSONObject(0);
				//get the 2nd json object
				JSONObject jobject2 = jobject1.getJSONObject("data");
				//get the json data array
				JSONArray arrivalArray = jobject2.getJSONArray("arrivals");
				for(int i =0;i<arrivalArray.length();i++){
					JSONObject jsonobject = arrivalArray.getJSONObject(i);
					createBusStopFromJsonObject(jsonobject, i);
				}
				return arrivals;
			}else{
				return arrivals;
			}
			
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void createBusStopFromJsonObject(JSONObject obj, int i){
		 try {
			 Arrival arrival;
			 String headSign = obj.getString("tripHeadsign");
			 String time = obj.getString("time");
			 arrival = new Arrival(headSign,time);
			 arrivals.add(arrival);
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
	}
	
	
}
