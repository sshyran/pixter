package org.example.sudoku;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.ViewFlipper;
  
public class pixterReadSD extends Activity {  
/** Called when the activity is first created. */
	public String s1;
	private ViewFlipper flip;
	Drawable drawable;
	ImageView imgView, imgView2 ;
	public int j = 1;
	private RefreshHandler mRedrawHandler = new RefreshHandler();
	List<String> imageNames = new ArrayList<String>();  
	
	@Override  
	public void onCreate(Bundle savedInstanceState) {  
	    super.onCreate(savedInstanceState);  
	    setContentView(R.layout.readsd);  
	    

	    imageNames = ReadSDCard();										//Read Images from SD card and store in List
	    imgView =(ImageView)findViewById(R.id.ImageView01);				// Instantiate Image view
	    imgView.setImageDrawable(Drawable.createFromPath(imageNames     // View images from List
				.get(0)));
	    
	   
	    imgView2 =(ImageView)findViewById(R.id.ImageView02);				// Instantiate Image view 2
	    imgView2.setImageDrawable(Drawable.createFromPath(imageNames     // View images from List
				.get(0)));
	    
	    flip = (ViewFlipper)findViewById(R.id.flip);
	    flip.setInAnimation(this,android.R.anim.fade_in);
	    flip.setOutAnimation(this, android.R.anim.fade_out);
	    
	    refreshUI();	//Handler to refresh Imageview every 3 seconds
	    
	   
	}  
	
    // Handles Imageview Refresh 
    class RefreshHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
        	pixterReadSD.this.refreshUI();
        	
        }
        
        public void sleep(long delayMillis) {
            this.removeMessages(0);
            sendMessageDelayed(obtainMessage(0), delayMillis);
           
          }
        
      };
      
      //Reinstantiates imgView. Variable j is used to move through all the elements in the list
      //handler is paused for 3 seconds before next image is loaded
      //Order of code is very important and shall not be moved
      private void refreshUI()
      {
    	  
    	 mRedrawHandler.sleep(3000); // deliberately placed in this location to get synchronized animation
    	 imgView.setImageDrawable(Drawable.createFromPath(imageNames.get(j)));
    	 imgView2.setImageDrawable(Drawable.createFromPath(imageNames.get(j)));
    	 flip.showNext();  // deliberately placed in this location to get synchronized animation
    	
    	 j = j +1;
		 if (j == imageNames.size()) j = 0;
		 flip.showNext(); // deliberately placed in this location to get synchronized animation
		    
       }
    	  
 
	//Different functionality needed
	public void onBackPressed()
    {
		 /*imgView.setImageDrawable(Drawable.createFromPath(imageNames.get(j)));
		 j = j +1;
		 if (j == imageNames.size()) j = 0;*/
		startActivity(new Intent(pixterReadSD.this, MainScreen.class));

	}
  
	//All images in Download directory are added to the list string
	private List<String> ReadSDCard()  
	{  

	 List<String> tFileList = new ArrayList<String>();  
	 
	 //It have to be matched with the directory in SDCard  
	 File f = new File("/mnt/sdcard/Download");  
	  
	 File[] files=f.listFiles();  
	  
	 for(int i=0; i<files.length; i++)  
	 {  
	  File file = files[i];  
	  /*It's assumed that all file in the path 
	    are in supported type*/  
	  tFileList.add(file.getPath());  
	 }  
	  
	 return tFileList;  
	}  
	
	   

}    