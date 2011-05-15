package org.example.sudoku;

//import org.example.sudoku.R;

//import com.image.DownImages;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;


public class Options extends Activity 
{   	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);        
    }

    public void transition_handler(View v)
	{
    	setContentView(R.layout.transitionspeed);
     	startActivity(new Intent(Options.this, transitionSpeed.class));
	}
    public void dib_handler(View v)
	{
		Log.e("dob", "download in b ground");
    	Intent intent = new Intent(this, DownImages.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,intent, PendingIntent.FLAG_CANCEL_CURRENT);
		//  PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,intent, PendingIntent.FLAG_ONE_SHOT);
		//  PendingIntent.getBroadcast(this, 0, intent, PendingIntent.)
		 AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    
    	alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(), 100*1000,pendingIntent);
		Toast.makeText(this, "Downloading in Background", Toast.LENGTH_LONG).show();


	}
    public void sdib_handler(View v)
	{
    	Intent intent = new Intent(this, DownImages.class);
    	PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,intent, PendingIntent.FLAG_CANCEL_CURRENT);
	  //PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,intent, PendingIntent.FLAG_ONE_SHOT);
    	//PendingIntent.getBroadcast(this, 0, intent, PendingIntent.);
    	AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

    	alarmManager.cancel(pendingIntent);
		Toast.makeText(this, "Cancel Downloads in Background", Toast.LENGTH_LONG).show();

	}
   
    
}