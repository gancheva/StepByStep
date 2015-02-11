package com.StepByStepModel;

import java.util.ArrayList;

import android.location.Location;

public class TestData {

	public ArrayList<BusStop> data;
	
	public TestData(Location location){
		BusStop busstop1 = new BusStop("QBUZZ|10002000","Groningen, Zernikeplein", "QBUZZ", 53.24104, 6.53402);
		busstop1.setDistanceFromCurrentLocation(location);
		BusStop busstop2 = new BusStop("QBUZZ|10002000","Groningen, Zernikeplein", "QBUZZ", 53.24131, 6.53395);
		busstop2.setDistanceFromCurrentLocation(location);
		BusStop busstop3 = new BusStop("QBUZZ|10004270","Groningen, P+R Zernike", "QBUZZ", 53.24427, 6.53076);
		busstop3.setDistanceFromCurrentLocation(location);
		BusStop busstop4 = new BusStop("QBUZZ|10004280","Groningen, P+R Zernike", "QBUZZ", 53.24427, 6.53076);
		busstop4.setDistanceFromCurrentLocation(location);
		BusStop busstop5 = new BusStop("QBUZZ|gngzee|parent","Groningen, P+R Zernike", "QBUZZ", 53.24427, 6.53076);
		busstop5.setDistanceFromCurrentLocation(location);
		BusStop busstop6 = new BusStop("QBUZZ|10004460","Groningen, P+R Zernike", "QBUZZ", 53.24435, 6.53068);
		busstop6.setDistanceFromCurrentLocation(location);
		BusStop busstop7 = new BusStop("QBUZZ|gngzer|parent","Groningen, P+R Zernike", "QBUZZ", 53.24439, 6.53047);
		busstop7.setDistanceFromCurrentLocation(location);
		BusStop busstop8 = new BusStop("QBUZZ|10004150","Groningen, P+R Zernike", "QBUZZ", 53.24439, 6.53047);
		busstop8.setDistanceFromCurrentLocation(location);
		BusStop busstop9 = new BusStop("QBUZZ|10004160","Groningen, P+R Zernike", "QBUZZ", 53.24439, 6.53047);
		busstop9.setDistanceFromCurrentLocation(location);
		BusStop busstop10 = new BusStop("QBUZZ|gngzpa|parent","Groningen, P+R Zernike", "QBUZZ", 53.24439,6.53055);
		busstop10.setDistanceFromCurrentLocation(location);
		data = new ArrayList<BusStop>();
		data.add(busstop1);
		data.add(busstop2);
		data.add(busstop3);
		data.add(busstop4);
		data.add(busstop5);
		data.add(busstop6);
		data.add(busstop7);
		data.add(busstop8);
		data.add(busstop9);
		data.add(busstop10);
	}
	public ArrayList<BusStop> getBusStopArray(){
		return data;
	}
}
