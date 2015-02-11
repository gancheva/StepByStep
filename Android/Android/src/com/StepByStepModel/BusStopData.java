package com.StepByStepModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.json.JSONArray;

import Itract_API.ApiCallArray;
import Itract_API.BusStopJSONParser;
import android.location.Location;
import android.util.Log;

public class BusStopData {

	//parameter to set the data to testdata
	private static final boolean isTest = false; //false for realdata true for testdata
	//distance radius for the area to search
	public static double DISTANCE_RADIUS = 1.0; // 1.0 is about 2km
	//arraylist which holds the busstops from the apicall
	private ArrayList<BusStop> busstops;
	//max busstops to show
	private static final int MAX_BUSSTOPS_TO_SHOW = 10;
	//this is the current location
	private Location location;
	//the URL for the API call
	private String url;
	
	
	public BusStopData(Location location){
		//if test = true create testdate
		//else get Realdata
		this.location = location;
		if(isTest){
			getTestData();
		}
		else{
			getRealData(DISTANCE_RADIUS);
		}
	}
	
	public void getTestData(){
		TestData tdata = new TestData(location);
		busstops = tdata.getBusStopArray();
	}
	
	/**
	 * method to get realdata from the ITRACT server
	 * @param distanceRadius the radius to for the search area for busstops
	 * @return ArrayList of busstops in the area
	 */
	public ArrayList<BusStop> getRealData(double distanceRadius){
		url = setURL("http://itract.cs.kau.se:8081/proxy/api/transit/stopsInArea?", distanceRadius);
		ApiCallArray asyncTask = new ApiCallArray();
		JSONArray jsonarray = asyncTask.GetJson(url);
		BusStopJSONParser busstopParser = new BusStopJSONParser(location);
		return busstops = busstopParser.parseJson(jsonarray);
	}
	
	/**
	 * method to return the ArrayList with busstops
	 * when the returned busstop list is empty another API call will be done by the method 
	 * @return ArryList with max 10 busstops
	 */
	public ArrayList<BusStop> getBusStopsList(){
		if(busstops.isEmpty()){
			ArrayList<BusStop> busstopList = getRealData(DISTANCE_RADIUS);
			return getBustopsInArray(busstopList);
		}
		else{
			return getBustopsInArray(busstops);
		}
	}
	
	/**
	 * method to return busstops sorted by distance from location
	 * @param busstopList
	 * @return ArrayList with busstops sorted by distance
	 */
	public ArrayList<BusStop> getBustopsInArray(ArrayList<BusStop> busstopList){
		SortArray();
		ArrayList<BusStop> newList = new ArrayList<BusStop>();	
		int maxValue=0;
		if(busstopList.size()<busstopList.size()){
			maxValue = busstopList.size(); 
		}
		else {
			maxValue = MAX_BUSSTOPS_TO_SHOW;
		}
		Log.i("","MaxValue= "+maxValue);
		for(int i=0;i<maxValue;i++){
			Log.i("name","busstop name "+busstops.get(i).getName());
			busstops.get(i).setCount(i+1);
			newList.add(i, busstops.get(i));
		}
		return newList;
	}

	/**
	 * method to sort the array by distance from location
	 */
	public void SortArray(){
		 Collections.sort(busstops, new Comparator<BusStop>() {
		        @Override public int compare(BusStop p1, BusStop p2) {
		        	if (p1.getDistance() < p2.getDistance()) {  
		        		  return -1;  
		        		} else if (p1.getDistance() > p2.getDistance()) {  
		        		  return 1;  
		        		} else {  
		        		  return 0;  
		        		} 
		        }

		    });
	}
	
	
	/**
	 * method to set the URL
	 * @param url
	 * @param area_radius the radius for the search area
	 * @return URL in string format with parameters
	 */
	private String setURL(String url, double area_radius ) {
		double lat = location.getLatitude();
		double lon = location.getLongitude();
		double latitudeRange = DISTANCE_RADIUS/69.172;
		double longitudeRange = Math.abs(DISTANCE_RADIUS/(Math.cos(lat) *69.172));
		double southWestLat = lat - latitudeRange;
		double southWestLon = lon - longitudeRange;
		double northEastLat = lat + latitudeRange;
		double northEastLon = lon + longitudeRange;
		if(!url.endsWith("?")){
			url+="?";		
		}
		url+=("lat="+Double.toString(lat));//add latitude
		url+=("&lon="+Double.toString(lon));//add longitude
		url+=("&southWestLat="+Double.toString(southWestLat));//add southwest latitude
		url+=("&southWestLon="+Double.toString(southWestLon));//add southwest longitude
		url+=("&northEastLat="+Double.toString(northEastLat));//add northeast latitude
		url+=("&northEastLon="+Double.toString(northEastLon));//add northeast longitude
		return url;
	}
	
}
