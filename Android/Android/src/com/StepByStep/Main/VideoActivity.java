package com.StepByStep.Main;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.MediaController;
import android.widget.VideoView;

import com.StepByStepModel.Video;
import com.ovapp.stepbystep.R;

public class VideoActivity extends Activity {

	private int stopPosition;
	private VideoView videoView;
	private ProgressDialog progressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);

    	progressDialog = ProgressDialog.show(VideoActivity.this, "", "Loading...");
    	
	    videoView = (VideoView)findViewById(R.id.your_video_view);
	    MediaController mc = new MediaController(this);
	    mc.setAnchorView(videoView);
	    videoView.setMediaController(mc);
	    Intent intent = getIntent();
        String videoType = intent.getStringExtra("VideoType");

    	Video video = new Video();
    	String videoUrl = video.getVideoUrl(videoType);
    	System.out.println(videoUrl);
	    Uri uri = Uri.parse(videoUrl);
	    
	    videoView.setVideoURI(uri);
	    videoView.setZOrderOnTop(true);
	    videoView.requestFocus();
	    videoView.start();
	    
	    videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

	        @Override
	        public void onPrepared(MediaPlayer mp) {
	        	progressDialog.dismiss();
	        }
	    });
	    
	    
	    
	    videoView.setOnCompletionListener( new MediaPlayer.OnCompletionListener()
	    {
	       public void onCompletion(MediaPlayer mp) 
	            {
	                finish();
	            }  
	    });
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);
	    stopPosition = videoView.getCurrentPosition();
	    setContentView(R.layout.activity_video);
	    videoView = (VideoView)findViewById(R.id.your_video_view);
	    MediaController mc = new MediaController(this);
	    mc.setAnchorView(videoView);
	    videoView.setMediaController(mc);
	    Intent intent = getIntent();
        String str = intent.getStringExtra("VideoURL");
	    Uri uri = Uri.parse(str);
	    
	    videoView.setVideoURI(uri);
	    videoView.setZOrderOnTop(true);
	    videoView.requestFocus();
    	
    	videoView.seekTo(stopPosition);
    	progressDialog = ProgressDialog.show(VideoActivity.this, "", "Loading...");
	    videoView.start();
	    
	    videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

	        @Override
	        public void onPrepared(MediaPlayer mp) {
	        	progressDialog.dismiss();
	        }
	    });
	    
	    
	    
	    videoView.setOnCompletionListener( new MediaPlayer.OnCompletionListener()
	    {
	       public void onCompletion(MediaPlayer mp) 
	            {
	                finish();
	            }  
	    });

	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.video, menu);
		return true;
	}
	
	@Override
	public void onPause() {
	    super.onPause();
	    Log.i("ONPAUSE", "yes i am called");
	    stopPosition = videoView.getCurrentPosition();
	    videoView.pause();
	}
	
	@Override
	public void onDestroy() {
	    super.onPause();
	    Log.i("ONPAUSE", "yes i am called");
	    stopPosition = videoView.getCurrentPosition();
	    videoView.pause();
	}
	
	@Override
	public void onResume() {
	    super.onResume();
	    Log.i("onresume", "yes i am called");
	    videoView.seekTo(stopPosition);
	    videoView.start();
	}
	
}
