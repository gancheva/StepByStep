package com.StepByStepModel;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class Arrival{
	
	private String headsign; 
	private String time;
	
	public Arrival(String headsign, String time){
		this.headsign=headsign;
		this.time = getDate(time);
	}
	
	public String getDate(String time){
		Date date = new Date(Long.parseLong(time));
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm");
        String newTime = DATE_FORMAT.format(date);
        return newTime;
	}
	
	public String getName(){
		return headsign;
	}
	
	public String getTime(){
		return time;
	}
}
