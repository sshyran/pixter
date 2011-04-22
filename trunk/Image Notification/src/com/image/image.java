package com.image;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.ByteArrayBuffer;
import android.R.string;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.widget.ImageView.ScaleType;

public class image extends Activity {
	private static final String TAG = "PRANJAL";
	private boolean isImage = false;
	public String reviewImageLink;
	private Button btn;
	public URL reviewImageURL;
	public boolean chgURL = false;
	private final String imageInSD = "/sdcard/Download/brooklyn.PNG";
	private ViewFlipper flip;
	public String jj = "";
	public int j = 0;
	String url = "http://www.javacpp.com/steve/images/";
	Drawable drawable;
	TextView txt;
	ImageView imgView ;
	public TextView txtView;
	int Cabron;
	public static final String KEY_121 = "http://javacpp.com/steve/db.php"; //i use my real ip here	

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
		setContentView(R.layout.main);
		btn = (Button)findViewById(R.id.btn);
		
		 Bitmap bitmap = BitmapFactory.decodeFile(imageInSD);
	      ImageView myImageView = (ImageView)findViewById(R.id.ImageView01);
	      myImageView.setImageBitmap(bitmap);
	      j = 0;
		
		//reviewImageLink = "brooklyn";
        btn = (Button)findViewById(R.id.btn);
        flip = (ViewFlipper)findViewById(R.id.flip);
        
        flip.setInAnimation(this,android.R.anim.fade_in);
       //when a view disappears
        flip.setOutAnimation(this, android.R.anim.fade_out); 
     //txt.setText(j);
         txtView =(TextView)findViewById(R.id.textView1);
        txtView.setText(Integer.toString(j));
         imgView =(ImageView)findViewById(R.id.ImageView03);
       
   //      drawable = LoadImageFromWebOperations(url + getServerData(KEY_121));		//HOSER
         
   //     imgView.setImageDrawable(drawable);
    //    txtView.setText(Integer.toString(DownImages.Cabron));
        
        Intent intent = new Intent(this, DownImages.class);

       // PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,
        //		intent, PendingIntent.FLAG_CANCEL_CURRENT);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,
        		intent, PendingIntent.FLAG_ONE_SHOT);
      //  PendingIntent.getBroadcast(this, 0, intent, PendingIntent.)
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
       // alarmManager.set(AlarmManager.RTC,System.currentTimeMillis() + (600 * 1000) , pendingIntent);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(), 60*1000,pendingIntent);
        //Toast.makeText(this, "Alarm set", Toast.LENGTH_LONG).show();
        
   
        
	}
}
