package org.example.sudoku;

//import org.example.sudoku.R;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//import R.attr;
public class MainScreen extends Activity 
{  
	TextView txtView;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        setContentView(R.layout.main); 
        txtView =(TextView)findViewById(R.id.loggedIn);
        if (login.user != null)
        	txtView.setText("Logged In As: " +login.user);
    }

    public void myhandler1(View v)
    {
	  startActivity(new Intent(MainScreen.this, image.class));
    }
      
    public void options_handler(View v)
	{
      startActivity(new Intent(MainScreen.this, Options.class));
	}
    public void delete_handler(View v)
	{
    	boolean picsFound=ReadSDCard();
    	if(picsFound)
    		startActivity(new Intent(MainScreen.this, delete.class));
    	else
	    	Toast.makeText(this, "No Pictures Found. Login To Download Pictures", Toast.LENGTH_SHORT).show();

	}
    public void readsd_handler(View v)
	{
      startActivity(new Intent(MainScreen.this, startReadSD.class));
	}
    public void login_handler(View v)
	{
      startActivity(new Intent(MainScreen.this, login.class));
	}
    public void onBackPressed()
    {
		
	}
    
    private boolean ReadSDCard()  
	{  	 
	 //It have to be matched with the directory in SDCard  
	 File f = new File("/mnt/sdcard/Download");  
	  
	 File[] files=f.listFiles();  
	 if(files.length==0)
		 return false;
	 else	  
		 return true;  
	}
   }