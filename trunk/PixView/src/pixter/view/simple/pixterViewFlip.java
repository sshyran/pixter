
/*
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.net.MalformedURLException;




import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;


import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;*/

package pixter.view.simple;

import android.graphics.drawable.Drawable;
import java.io.InputStream;
import android.app.Activity;
import java.net.URL;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class pixterViewFlip extends Activity {
    /** Called when the activity is first created. */
	private ViewFlipper flip;
	private Button btn;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        btn = (Button)findViewById(R.id.btn);
        flip = (ViewFlipper)findViewById(R.id.flip);
        
        flip.setInAnimation(this,android.R.anim.fade_in);
       //when a view disappears
        flip.setOutAnimation(this, android.R.anim.fade_out); 
     
        
        ImageView imgView =(ImageView)findViewById(R.id.ImageView01);
        Drawable drawable = LoadImageFromWebOperations("http://www.javacpp.com/steve/images/brooklyn.jpg");
        imgView.setImageDrawable(drawable);
        
        ImageView imgView2 =(ImageView)findViewById(R.id.ImageView02);
        Drawable drawable2 = LoadImageFromWebOperations("http://www.javacpp.com/steve/images/coachella.jpg");
        imgView2.setImageDrawable(drawable2);
        
       flip.setFlipInterval(1000);
       flip.setAutoStart(true);

    }
    
    public void ClickHandler()
    {
     
    		flip.showNext();
    }
    
    private Drawable LoadImageFromWebOperations(String url)
	   {
	        try
	        {
	            InputStream is = (InputStream) new URL(url).getContent();
	            Drawable d = Drawable.createFromStream(is, "src name");
	            return d;
	        }catch (Exception e) {
	            System.out.println("Exc="+e);
	            return null;
	        }
	    }
    
    

 }
