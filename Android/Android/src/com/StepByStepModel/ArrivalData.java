package com.StepByStepModel;

import java.util.ArrayList;

import org.json.JSONArray;

import Itract_API.ApiCallArray;
import Itract_API.ArrivalsJSONParser;

public class ArrivalData {
	private boolean isTest = true;
	private String url;
	private ArrayList<Arrival> arrivals;
	private BusStop busstop;
	
	public ArrivalData(BusStop busstop){
		this.busstop=busstop;
		if(isTest){
			url ="http://itract.cs.kau.se:8081/proxy/api/transit/arrivalsAndDeparturesForStop?lat=59.37919176435972&lon=13.498671776845185&agencyId=601&stopId=7421275&startTime=1382602879730&endTime=1382689279730";
		}else{
			url = setURI("http://itract.cs.kau.se:8081/proxy/api/transit/arrivalsAndDeparturesForStop?");
		}
	}
	
	/**
	 *
	 */
	public void getRealData(){
		ApiCallArray apicall = new ApiCallArray();
		JSONArray jsonarray = apicall.GetJson(url);
		ArrivalsJSONParser arrivaljsonparser = new ArrivalsJSONParser();
		arrivals = arrivaljsonparser.parseJson(jsonarray);
	}
	
	public ArrayList<Arrival> getArrivalsList(){
		return arrivals;
	}
	/**
	 * method to set the url for the ITRACT api call
	 * @param URL to be set
	 * @return URL in String format with parameters
	 */
	private String setURI(String url) {
		Double lat = busstop.getLocation().getLatitude();
		Double lon = busstop.getLocation().getLongitude();
		String agencyID = busstop.getAgencyID(); 
		String stopID =	busstop.getBusStopID();
		long millis = System.currentTimeMillis()/1000;
		String startTime = Long.toString(millis);
		String endTime = Long.toString(millis+3600);
		if(!url.endsWith("?")){
			url+="?";		
		}
		url+=("lat="+lat);//add latitude
		url+=("&lon="+lon);//add longitude
		url+=("&agencyId="+agencyID);
		url+=("&stopId="+stopID);
		url+=("&startTime="+startTime);
		url+=("&endTime="+endTime);
		return url;
	}
}
