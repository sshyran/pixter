package pixter.delete;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;
  
public class pixDel extends Activity {  
/** Called when the activity is first created. */
	public String s1;
	private ViewFlipper flip;
	Drawable drawable;
	ImageView imgView, imgView2 ;
	public int j = 1;
	//private RefreshHandler mRedrawHandler = new RefreshHandler();
	List<String> imageNames = new ArrayList<String>();  
	
	@Override  
	public void onCreate(Bundle savedInstanceState) {  
	    super.onCreate(savedInstanceState);  
	    setContentView(R.layout.main);  
	    

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
	    
	  //  refreshUI();	//Handler to refresh Imageview every 3 seconds
	    
	   
	}  
	
	// cycle through images
    public void onBackPressed()
    {                
         s1 = imageNames.get(j);
         imgView.setImageDrawable(Drawable.createFromPath(imageNames
                                .get(j)));
         j = j +1;
         if (j == imageNames.size()) j = 0;
     
        }

	
 
	//Delete Images and re-read images in sdcard
    public void ClickHandler(View v)
    {
    	int k = j-1;
    	if ((j-1)<0) k = imageNames.size() - 1;
		File del = new File(imageNames.get(k));
		boolean deleted = del.delete();
		imageNames = ReadSDCard();	
	    imgView.setImageDrawable(Drawable.createFromPath(imageNames
                .get(j)));
	    j = j+1;


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