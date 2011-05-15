package org.example.sudoku;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
  
public class transitionSpeed extends Activity {  
	 	private RadioButton rb0;
	    private RadioButton rb1;
	    private RadioButton rb2;
	    private RadioButton rb3;
	    private RadioButton rb4; 
	public static long interval_speed = 2000;
	public static long slide;
	public static boolean fade;
	
    public static String transition;
    public static String animation;
	@Override 
	public void onBackPressed()
    { 
    	startActivity(new Intent(transitionSpeed.this, MainScreen.class));
    }
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.transitionspeed);
	    rb0=(RadioButton)findViewById(R.id.radio0);
        rb1=(RadioButton)findViewById(R.id.radio1);
        rb2=(RadioButton)findViewById(R.id.radio2);
        rb3=(RadioButton)findViewById(R.id.radio3);
        rb4=(RadioButton)findViewById(R.id.radio4);
        
        Button Save = (Button) this.findViewById(R.id.Savebutton);
        
        Save.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		startActivity(new Intent(transitionSpeed.this, MainScreen.class));
        		 
                // TODO Auto-generated method stub
            	if(rb0.isChecked() == true)
            		interval_speed = 3000;
            		transition = "Last Interval speed : 3 Sec";
                if(rb1.isChecked() == true)
                	interval_speed = 4000;
                	transition = "Last Interval speed : 4 Sec";
                if(rb2.isChecked() == true)
                	interval_speed = 5000;
                	transition= "Last Interval speed : 5 Sec";
                if(rb3.isChecked() == true)
                	slide = 1000;
                	animation ="Last Animation : Slide in/out";
                if(rb4.isChecked() == true)
                	slide = 2000;
                	animation ="Last Animation : Fade in/out";
                	
        	}
        	});
	    }
	
	
	}

