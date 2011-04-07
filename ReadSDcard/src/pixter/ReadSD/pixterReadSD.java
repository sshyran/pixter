package pixter.ReadSD;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import android.app.Activity;
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
	ImageView imgView ;
	public int j = 1;
	Timer timer;
	private RefreshHandler mRedrawHandler = new RefreshHandler();
	List<String> imageNames = new ArrayList<String>();  
	
	@Override  
	public void onCreate(Bundle savedInstanceState) {  
	    super.onCreate(savedInstanceState);  
	    setContentView(R.layout.main);  
	    

	    imageNames = ReadSDCard();
	    imgView =(ImageView)findViewById(R.id.ImageView01);
	    imgView.setImageDrawable(Drawable.createFromPath(imageNames
				.get(0)));
	    
	    s1 = imageNames.get(0);
	    
	   // TimerThread timerThread = new TimerThread();
       // timerThread.start();
     
	    updateUI();

	 /*   flip = (ViewFlipper)findViewById(R.id.flip);
	    
	    flip.setInAnimation(this,android.R.anim.fade_in);
	   //when a view disappears
	    flip.setOutAnimation(this, android.R.anim.fade_out); 
	    */
	    
	}  
	
	/*private class TimerThread extends Thread
    {
            public void run()
            {
                    timer = new Timer();
                    timer.schedule(new DownloadTimerTask(), 5000);
            }
    }
	
    private class DownloadTimerTask extends TimerTask
    {
            public void run()
            {
                    
                    handler.sendEmptyMessage(0);
                    j = j +1;
           		    if (j == imageNames.size()) j = 0;
            }
    }*/
    
    class RefreshHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
        	pixterReadSD.this.updateUI();
        }
        
        public void sleep(long delayMillis) {
            this.removeMessages(0);
            sendMessageDelayed(obtainMessage(0), delayMillis);
          }
        
      };
      
      private void updateUI(){
		    	  imgView.setImageDrawable(Drawable.createFromPath(imageNames
							.get(j)));
		    	 mRedrawHandler.sleep(3000);
				 j = j +1;
				 if (j == imageNames.size()) j = 0;
    	    }
    	  
   
  /*  Handler handler = new Handler()
    {
            
            @Override
            public void handleMessage(Message msg)
            {
            	imgView.setImageDrawable(Drawable.createFromPath(imageNames
    					.get(j)));
            }
    };*/
	
	public void onBackPressed()
    {
			
		 s1 = imageNames.get(j);
	     
		// pixterLoadImage(s1);
		 imgView.setImageDrawable(Drawable.createFromPath(imageNames
					.get(j)));
		 j = j +1;
		 if (j == imageNames.size()) j = 0;
     
    	
	}
  
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