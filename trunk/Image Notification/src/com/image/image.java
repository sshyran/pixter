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
	public void onCreate(Bundle savedInstanceState) {
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
         
         drawable= DownImages.getDrawable();
        imgView.setImageDrawable(drawable);
        txtView.setText(Integer.toString(DownImages.Cabron));
        
        Intent intent = new Intent(this, DownImages.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,
        		intent, PendingIntent.FLAG_ONE_SHOT);
      //  PendingIntent.getBroadcast(this, 0, intent, PendingIntent.)
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
       // alarmManager.set(AlarmManager.RTC,System.currentTimeMillis() + (10 * 1000) , pendingIntent);
        alarmManager.setRepeating(AlarmManager.RTC,0, 60000,pendingIntent);
        //Toast.makeText(this, "Alarm set", Toast.LENGTH_LONG).show();
        
   
        
	}
	
	
	@Override
	public void onContentChanged() {
		// TODO Auto-generated method stub
		super.onContentChanged();
        //txtView.setText(Integer.toString(DownImages.Cabron));

	}


/*	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		imgView.setImageDrawable(drawable);
        txtView.setText(Integer.toString(DownImages.Cabron));
		Toast.makeText(this, "onConfChanged", Toast.LENGTH_LONG).show();

	}*/




	//public static final String KEY_121 = "http://javacpp.com/steve/db.php"; //i use my real ip here
	/*	private String getServerData(String returnString) 
	{
	   InputStream is = null;

	   String result = "";
	   String result1 = "";
	   String single[] = {"","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
	   int i = 0;
	    //the year data to send
	    ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	    nameValuePairs.add(new BasicNameValuePair("filename","brooklyn.jpg"));
	    try
	    {
	            HttpClient httpclient = new DefaultHttpClient();
	            HttpPost httppost = new HttpPost(KEY_121);
	            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	            HttpResponse response = httpclient.execute(httppost);
	            HttpEntity entity = response.getEntity();
	            is = entity.getContent();
	    }
	    catch(Exception e)
	    {
	            Log.e("log_tag", "Error in http connection "+e.toString());
	    }

	    //convert response to string
	    try{
	            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	            StringBuilder sb = new StringBuilder();
	            String line = null;
	            while ((line = reader.readLine()) != null) 
	            {
	            	sb.append(line + "\n");
	            }
	            is.close();
	            result=sb.toString();
	            result1=result;
	            Cabron=0;
	            while (result.length()!=0)
	            {
	            	single[i] = result1.substring(0, result1.indexOf(","));
	            	result1 = result1.substring(result1.indexOf(",")+1, result1.length()); 
	            	i++;
	            	Cabron++;
	            }
	            //j=j%j.
	            return single[j];
	    }
	    catch(Exception e)
	    {
	            Log.e("log_tag", "Error converting result "+e.toString());
	    }
	     return single[j];
	    //return result;
	}   */
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
	
    	@Override 
    public void onBackPressed()
             {
					//return j++;
					 flip.showNext();
					// j = 0;
             }   
    	/*	 public void ClickHandler(View v)
	    {
		 //chgURL = true;
		 changeURLStr(url + getServerData(KEY_121));
	    }
	 public void ClickHandler1(View v)
	    {
		 j = j +1;
		// flip.showNext();
		 drawable = LoadImageFromWebOperations(url + getServerData(KEY_121));
		 imgView.setImageDrawable(drawable);
		   txtView =(TextView)findViewById(R.id.textView1);
	        txtView.setText(Integer.toString(j));
		// flip.showNext();
		}
	 
	 private void changeURLStr(String newUrl) {
			//URL reviewImageURL;
		 reviewImageLink =  getServerData(KEY_121);
		 String name = reviewImageLink;//getServerData(KEY_121);
			//String name = reviewImageLink + Integer.toString(j);
					//.lastIndexOf("/") + 1);
			try {
				reviewImageURL = new URL(newUrl);
				if (!hasExternalStoragePublicPicture(name)) {
					isImage = false;
					new DownloadImageTask().execute(reviewImageURL);
					Log.v("log_tag", "if");
					isImage = true;
					File sdImageMainDirectory = new File(Environment
							.getExternalStorageDirectory(), getResources()
							.getString(R.string.directory));
					sdImageMainDirectory.mkdirs();
					File file = new File(sdImageMainDirectory, name);
					Log.v("log_tag", "Directory created");
				}

			} catch (MalformedURLException e) {
				Log.v(TAG, e.toString());
			}
		} 
	private class DownloadImageTask extends AsyncTask<URL, Integer, Bitmap> {
		// This class definition states that DownloadImageTask will take String
		// parameters, publish Integer progress updates, and return a Bitmap
		protected Bitmap doInBackground(URL... paths) {
			URL url;
			try {
				url = paths[0];
				HttpURLConnection connection = (HttpURLConnection) url
						.openConnection();
				int length = connection.getContentLength();
				InputStream is = (InputStream) url.getContent();
				byte[] imageData = new byte[length];
				int buffersize = (int) Math.ceil(length / (double) 100);
				int downloaded = 0;
				int read;
				while (downloaded < length) {
					if (length < buffersize) {
						read = is.read(imageData, downloaded, length);
					} else if ((length - downloaded) <= buffersize) {
						read = is.read(imageData, downloaded, length
								- downloaded);
					} else {
						read = is.read(imageData, downloaded, buffersize);
					}
					downloaded += read;
					publishProgress((downloaded * 100) / length);
				}
				Bitmap bitmap = BitmapFactory.decodeByteArray(imageData, 0,
						length);
				if (bitmap != null) {
					Log.i(TAG, "Bitmap created");
				} else {
					Log.i(TAG, "Bitmap not created");
				}
				is.close();
				return bitmap;
			} catch (MalformedURLException e) {
				Log.e(TAG, "Malformed exception: " + e.toString());
			} catch (IOException e) {
				Log.e(TAG, "IOException: " + e.toString());
			} catch (Exception e) {
				Log.e(TAG, "Exception: " + e.toString());
			}
			return null;
		}
		protected void onPostExecute(Bitmap result) {
			 String name = getServerData(KEY_121);
			//String name = reviewImageLink.substring(reviewImageLink
			//		.lastIndexOf("/") + 1);
			if (result != null) {
				hasExternalStoragePublicPicture(name);
				saveToSDCard(result, name);
				isImage = true;

			} else {
				isImage = false;

			}
		}
	}
	public void saveToSDCard(Bitmap bitmap, String name) {
		boolean mExternalStorageAvailable = false;
		boolean mExternalStorageWriteable = false;
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			mExternalStorageAvailable = mExternalStorageWriteable = true;
			Log.v(TAG, "SD Card is available for read and write "
					+ mExternalStorageAvailable + mExternalStorageWriteable);
			saveFile(bitmap, name);
		} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			mExternalStorageAvailable = true;
			mExternalStorageWriteable = false;
			Log.v(TAG, "SD Card is available for read "
					+ mExternalStorageAvailable);
		} else {
			mExternalStorageAvailable = mExternalStorageWriteable = false;
			Log.v(TAG, "Please insert a SD Card to save your Video "
					+ mExternalStorageAvailable + mExternalStorageWriteable);
		}
	}

	private void saveFile(Bitmap bitmap, String name) {
		String filename = name;
		ContentValues values = new ContentValues();
		File sdImageMainDirectory = new File(Environment
				.getExternalStorageDirectory(), getResources().getString(
				R.string.directory));
		sdImageMainDirectory.mkdirs();
		File outputFile = new File(sdImageMainDirectory, filename);
		values.put(MediaStore.MediaColumns.DATA, outputFile.toString());
		values.put(MediaStore.MediaColumns.TITLE, filename);
		values.put(MediaStore.MediaColumns.DATE_ADDED, System
				.currentTimeMillis());
		values.put(MediaStore.MediaColumns.MIME_TYPE, "image/png");
		Uri uri = this.getContentResolver().insert(
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,

				values);
		try {
			OutputStream outStream = this.getContentResolver()
					.openOutputStream(uri);
			bitmap.compress(Bitmap.CompressFormat.PNG, 95, outStream);

			outStream.flush();
			outStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private boolean hasExternalStoragePublicPicture(String name) {
		File sdImageMainDirectory = new File(Environment
				.getExternalStorageDirectory(), getResources().getString(
				R.string.directory));
		File file = new File(sdImageMainDirectory, name);
		if (file != null) {
			file.delete();
		}
		return file.exists();
	}
	public void updater()
	{
		 j = j +1;
			// flip.showNext();
		 		String s=getServerData(KEY_121);
			 drawable = LoadImageFromWebOperations(url + "coachella");
			 imgView.setImageDrawable(drawable);
			   txtView =(TextView)findViewById(R.id.textView1);
		        txtView.setText(Integer.toString(10));
			// flip.showNext();
	}	*/
}
