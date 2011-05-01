package org.example.sudoku;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
  
public class transitionSpeed extends Activity {  
	
	public static long interval_speed;
	@Override  
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.transitionspeed);

	    }
	
	public void Threesec_handler(View v)
    {
	 interval_speed = 3000;
	 startActivity(new Intent(transitionSpeed.this, MainScreen.class));
    }
	public void Fivesec_handler(View v)
    {
	interval_speed = 5000;
	startActivity(new Intent(transitionSpeed.this, MainScreen.class));
    }
}

