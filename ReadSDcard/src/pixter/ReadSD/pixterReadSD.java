package pixter.ReadSD;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewFlipper;
  
public class pixterReadSD extends Activity {  
/** Called when the activity is first created. */
	public String s1;
	private ViewFlipper flip;
	Drawable drawable;
	ImageView imgView ;
	public int j = 1;
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



	 /*   flip = (ViewFlipper)findViewById(R.id.flip);
	    
	    flip.setInAnimation(this,android.R.anim.fade_in);
	   //when a view disappears
	    flip.setOutAnimation(this, android.R.anim.fade_out); 
	    */
	    
	}  
	
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
	
	   
	  /* while (j<imageNames.size()) {
		   imgView.setImageDrawable(Drawable.createFromPath(imageNames
					.get(j)));
		 s1 = imageNames.get(j);
	     
		// pixterLoadImage(s1);
		 SystemClock.sleep(1);
		 j = j +1;
		 if (j == imageNames.size()) j = 0;
		 
	   }
	   */
		
	    /*for(int i=0; i<some_value; i++) {
		   for(int j=0; j<some_other_value; j++) {
		    String buttonID = "btn" + i + "-" + j;
		    int resID = getResources().getIdentifier(buttonID, "id", "com.sample.project");
		    buttons[i][j] = ((Button) findViewById(resID));
		    buttons[i][j].setOnClickListener(this);
		   }
		}*/


}  