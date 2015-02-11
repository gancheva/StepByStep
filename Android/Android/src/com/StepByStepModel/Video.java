package com.StepByStepModel;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Itract_API.ApiCall;
import android.util.Log;

public class Video {
	
	public  Video() {}
	
	public String getVideoUrl(String videoName) {
		URI uri;
		try {
			uri = new URI("http://dibbit.nl/itract/api/videosForType/"+videoName);
			Log.i("URI", uri.toString());
			ApiCall asyncTask = new ApiCall();
			try {
				JSONObject json = asyncTask.execute(uri).get();
				JSONArray videos = json.getJSONArray("videos");
				
				for(int i = 0; i < videos.length();){
					JSONObject video = videos.getJSONObject(i);
					return video.getString("url");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
}
