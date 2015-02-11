package com.StepByStepModel;

import java.util.ArrayList;

import android.location.Location;

public class BusStop{

	private String busStopID;
	private String name;
	private String agencyID;
	private android.location.Location location;
	private int count;
	private float distance;
	private String distanceString;
	private ArrayList<Arrival> arrivals;
	
	public BusStop(){	
	}
	
	public BusStop(String name, int newcount){
		this.name=name;
		count = newcount;
		arrivals = new ArrayList<Arrival>();
	}
	public BusStop(String newBusStopID, String newAgencyID, Double lat, Double lon){
		busStopID = newBusStopID;
		agencyID = newAgencyID;
		arrivals = new ArrayList<Arrival>();
		location = new Location("newagencyID");
		setLocation(lat,lon);
	}
	
	public BusStop(String newBusStopID, String newName, String newAgencyId, Double lat, Double lon,int newCount){
		busStopID = newBusStopID;
		name = newName;
		agencyID = newAgencyId;
		location = new Location(name);
		setLocation(lat,lon);
		count = newCount;
	}
	
	public BusStop(String newBusStopID, String newName, String newAgencyId, Double lat, Double lon){
		location = new Location(name);
		busStopID = newBusStopID;
		name = newName;
		agencyID = newAgencyId;
		setLocation(lat,lon);
	}
	
	public void setDistance(int distance){
		this.distance = distance;
	}

	
	private void setLocation(Double latitude, Double longitude){
		location.setLatitude(latitude);
		location.setLongitude(longitude);
	}
	
	public void addArrivals(ArrayList<Arrival> arrivals){
		this.arrivals=arrivals;
	}
	public ArrayList<Arrival> getArrivals(){
		return arrivals;
	}
	public String getBusStopID() {
		return busStopID;
	}
	public void setBusStopID(String busStopID) {
		this.busStopID = busStopID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAgencyID() {
		return agencyID;
	}
	public void setAgencyID(String agencyID) {
		this.agencyID = agencyID;
	}
	public android.location.Location getLocation() {
		return location;
	}
	public void setLocation(android.location.Location location) {
		this.location = location;
	}
	
	public void setCount(int count){
		this.count=count;
	}
	public int getCount(){
		return count;
	}
	
	public float getDistance(){
		return distance;
	}
	
	public String getDistanceString(){
		return distanceString;
	}
	
	public Double getLatitude(){
		return location.getLatitude();
	}
	
	public Double getLongitude(){
		return location.getLongitude();
	}
	
	public void setDistanceFromCurrentLocation(Location newlocation) {
		float distance = getDistance(newlocation);
		this.distance =distance;
		String distanceString;
		if (distance < 1000) {
	      distanceString = ((int) distance) + "m";
	    } else if (distance < 10000) {
	    	distanceString =  formatDec(distance / 1000f, 1) + "km";
	    } else {
	    	distanceString = ((int) (distance / 1000f)) + "km";
	    }
		this.distanceString = distanceString;
	}
	
	public float getDistance(Location newlocation){
		return this.getLocation().distanceTo(newlocation);
	}
	
	static String formatDec(float val, int dec) {
	    int factor = (int) Math.pow(10, dec);

	    int front = (int) (val);
	    int back = (int) Math.abs(val * (factor)) % factor;

	    return front + "." + back;
	  }
	
}
