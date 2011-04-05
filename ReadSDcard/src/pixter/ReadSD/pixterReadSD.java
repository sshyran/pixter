package pixter.ReadSD;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;
  
public class pixterReadSD extends Activity {  
/** Called when the activity is first created. */
	public String s1;
	private ViewFlipper flip;
	Drawable drawable;
	ImageView imgView ;
	public int j = -1;
	List<String> imageNames = new ArrayList<String>();  
	
	@Override  
	public void onCreate(Bundle savedInstanceState) {  
	    super.onCreate(savedInstanceState);  
	    setContentView(R.layout.main);  
	    
	
	    
	    //List<String> imageNames = new ArrayList<String>();  
	    imageNames = ReadSDCard();
	   
		//while(true){
		 //   s1 = imageNames.get(j);
	     //   pixterLoadImage(s1);
	        
		//}
	    //imgView =(ImageView)findViewById(R.id.ImageView01);
	    
	
	    
	  /*  for(int j=0; j<imageNames.size(); j++)  
	    {  
	     
	    	s1 = imageNames.get(j);  
	    // s1 = file.getPath().toString();
	    	pixterLoadImage(s1);
	    	try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//if (j == (imageNames.size()-1)) j = 0;
	    	
	    }*/
	  /*  flip = (ViewFlipper)findViewById(R.id.flip);
	    
	    flip.setInAnimation(this,android.R.anim.fade_in);
	   //when a view disappears
	    flip.setOutAnimation(this, android.R.anim.fade_out); */
	    
	    
	}  
	
	public void onBackPressed()
    {
	 j = j +1;
	 if (j == imageNames.size()) j = 0;
	 s1 = imageNames.get(j);
     
	 pixterLoadImage(s1);
     
    	
    	
	}
  
	private List<String> ReadSDCard()  
	{  
	 //List<String> tFileList = new ArrayList<String>();  
	  
	 //It have to be matched with the directory in SDCard  
	/*
	 File file[] = Environment.getExternalStorageDirectory().listFiles();
	 StringBuilder sb = new StringBuilder();
	
	 for(int i=0; i<file.length; i++)  
	 {  
		 tFileList.add(file[i].getAbsolutePath());  
		 
		   
		 
	     //sb.append((file[i]).toString());
	 // this.output.setText(sb.toString());
	 }  */
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
	
    public void pixterLoadImage (String imageSD) {
    	
        ImageView image = (ImageView) findViewById(R.id.ImageView01);
        FileInputStream in;
        BufferedInputStream buf;
        try {
       	    //in = new FileInputStream("/sdcard/Download/brooklyn.png");
       	    in = new FileInputStream(imageSD);
            buf = new BufferedInputStream(in);
            Bitmap bMap = BitmapFactory.decodeStream(buf);
            image.setImageBitmap(bMap);
            if (in != null) {
         	in.close();
            }
            if (buf != null) {
         	buf.close();
            }
        } catch (Exception e) {
            Log.e("Error reading file", e.toString());
        }
    }

}  