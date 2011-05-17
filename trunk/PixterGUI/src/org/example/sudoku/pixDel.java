package org.example.sudoku;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;
  
public class pixDel extends Activity implements OnClickListener {  
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
	    
	    setContentView(R.layout.deletepix); 
        Button btn = (Button) findViewById(R.id.Button01);
        registerForContextMenu(btn);
        btn.setOnClickListener(DeleteMenu);
        
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage("No Images in Media Storage. Do you wish to download Images?")
    	       .setCancelable(false)
    	       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	   if (login.user != null)
    	        		   startActivity(new Intent(pixDel.this, image.class));
    	        	   else
    	        		   startActivity(new Intent(pixDel.this, login.class));
    	           }
    	       })
    	       .setNegativeButton("No", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	   startActivity(new Intent(pixDel.this, MainScreen.class));
    	           }
    	       });
    	AlertDialog alert = builder.create();

        //View v = (View)findViewById(R.id.view); 
        //v.setOnClickListener(this);
        try{
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
	    }catch(Exception e){
	    	builder.show();
	    	
	    }
	   
	}  
	

	// cycle through images
    @Override
	public void onBackPressed()
    { 
    	startActivity(new Intent(pixDel.this, MainScreen.class));
    }

    
    public void next_handler(View v)
    {
    	imageNames = ReadSDCard();	
        j = j +1;
        if (j >= imageNames.size()) j = 0;
        //s1 = imageNames.get(j);
        imgView.setImageDrawable(Drawable.createFromPath(imageNames
                .get(j)));
    }
    
    public void previous_handler(View v)
    {
    	imageNames = ReadSDCard();	
    	if ((j)== 0) j = imageNames.size() - 1; 
    	else j =j-1;
    	//s1 = imageNames.get(j);
    	imgView.setImageDrawable(Drawable.createFromPath(imageNames
                .get(j)));

    }
    
    //After clicking on Delete Button context menu appears
	View.OnClickListener DeleteMenu = new View.OnClickListener() {
	      public void onClick(View v) {
	    	   openContextMenu(v);
	     }
	   };
	   
	    //Context Menu to Assure user wants to delete Image
	   @Override
	   public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
			menu.setHeaderTitle("Context Menu");
			menu.add(0, v.getId(), 0, "Delete Image");
			menu.add(0, v.getId(), 0, "Keep Image");
		}

	   @Override
		public boolean onContextItemSelected(MenuItem item) {
	      	if(item.getTitle()=="Delete Image"){Delete();}
	   	else {return false;}
		return true;
		}
	   
    //Delete image function, re read number of images in SD card, move to next image
    public void Delete(){
    	//Toast.makeText(this, "function 1 called", Toast.LENGTH_SHORT).show();
    	File del = new File(imageNames.get(j));
		boolean deleted = del.delete();
		imageNames = ReadSDCard();
		j = j+1;
		if (j >= imageNames.size()) j = 0;
	    imgView.setImageDrawable(Drawable.createFromPath(imageNames
                .get(j)));
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


	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

 

} 
