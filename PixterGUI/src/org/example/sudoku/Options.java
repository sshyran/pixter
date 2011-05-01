package org.example.sudoku;

//import org.example.sudoku.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;


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
   
    
}