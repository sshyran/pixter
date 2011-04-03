package com.image;
//svn test
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DownImages extends BroadcastReceiver {


	/*----------------------------New----------------------------*/
	public static final String GETMAX = "http://javacpp.com/steve/max.php"; //i use my real ip here	
	static int Cabron=0;
	public int j;
	 static Drawable drawable;
	String url = "http://www.javacpp.com/steve/images/";
	public static final String KEY_121 = "http://javacpp.com/steve/db.php"; //i use my real ip here
	public String reviewImageLink;
	public URL reviewImageURL;
	private boolean isImage = false;
	private static final String TAG = "PRANJAL";

	/*----------------------------New----------------------------*/
	
	@Override
	public void onReceive(Context context, Intent intent) 
	{
		Toast.makeText(context, "Alarm worked.", Toast.LENGTH_LONG).show();
		getServerData(GETMAX,context);	
		changeURLStr(url + "image001",context);

	}



	
	//new new new new new new new new new new new new new new new new new new new new new new


	private String getServerData(String returnString,Context context) 
	{

	   InputStream is = null;
		Toast.makeText(context, "here", Toast.LENGTH_LONG).show();

	   String result = "";
	   String result1 = "";
	   String single[] = {"","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
	   int i = 0;
	    //the year data to send
	    ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	    nameValuePairs.add(new BasicNameValuePair("filename","brooklyn.jpg"));
	    try
	    {
			//Toast.makeText(context, "one", Toast.LENGTH_LONG).show();

	            HttpClient httpclient = new DefaultHttpClient();
	            HttpPost httppost = new HttpPost(GETMAX);
	            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	            HttpResponse response = httpclient.execute(httppost);
	            HttpEntity entity = response.getEntity();
	            is = entity.getContent();
	    }
	    catch(Exception e)
	    {
			//Toast.makeText(context, "2", Toast.LENGTH_LONG).show();

	            Log.e("log_tag", "Error in http connection "+e.toString());
	    }

	    //convert response to string
	    try{
			Toast.makeText(context, "2", Toast.LENGTH_LONG).show();

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
	            Cabron=Integer.parseInt(result.trim());
	           /* while (result.length()!=0)
	            {
	            	single[i] = result1.substring(0, result1.indexOf(","));
	            	result1 = result1.substring(result1.indexOf(",")+1, result1.length()); 
	            	i++;
	            	Cabron++;
	            }*/
	            //j=j%j.
	            return result;
	    }
	    catch(Exception e)
	    {
			Toast.makeText(context, "4", Toast.LENGTH_LONG).show();

	            Log.e("log_tag", "Error converting result "+e.toString());
	    }
	     return single[j];
	    //return result;
	}   
	private String getServerData(String returnString) 
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
	            while (result.length()!=0)
	            {
	            	single[i] = result1.substring(0, result1.indexOf(","));
	            	result1 = result1.substring(result1.indexOf(",")+1, result1.length()); 
	            	i++;
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
	
	 private void changeURLStr(String newUrl,Context context) {
			//URL reviewImageURL;
			Toast.makeText(context, "change", Toast.LENGTH_LONG).show();

			String name = reviewImageLink.substring(reviewImageLink
					.lastIndexOf("/") + 1);
			try {
				reviewImageURL = new URL(newUrl);
				if (!hasExternalStoragePublicPicture(name)) 
				{
					isImage = false;
					new DownloadImageTask().execute(reviewImageURL);
					Log.v("log_tag", "if");
					isImage = true;
					File sdImageMainDirectory = new File(Environment
							.getExternalStorageDirectory(), "Download");
					sdImageMainDirectory.mkdirs();
					File file = new File(sdImageMainDirectory, name);
					Log.v("log_tag", "Directory created");
				}

			} catch (MalformedURLException e) {
				Log.v(TAG, e.toString());
			}
		} 
	private class DownloadImageTask extends AsyncTask<URL, Integer, Bitmap>
	{
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
			/*protected void onPostExecute(Bitmap result)
			{
			String name = reviewImageLink.substring(reviewImageLink
					.lastIndexOf("/") + 1);
			if (result != null) {
				hasExternalStoragePublicPicture(name);
				saveToSDCard(result, name);
				isImage = true;

			} else {
				isImage = false;

			}
		}*/
	}
	/*public void saveToSDCard(Bitmap bitmap, String name) {
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
	}*/

/*	private void saveFile(Bitmap bitmap, String name) 
	{
		String filename = name;
		ContentValues values = new ContentValues();
		File sdImageMainDirectory = new File(Environment
				.getExternalStorageDirectory(), "Download");
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
	}*/
	private boolean hasExternalStoragePublicPicture(String name) {
		File sdImageMainDirectory = new File(Environment
				.getExternalStorageDirectory(), "Download");
		File file = new File(sdImageMainDirectory, name);
		if (file != null) {
			file.delete();
		}
		return file.exists();
	}
	 public static Drawable getDrawable()
	{
		return drawable;
	}
    
	
}//end of class
