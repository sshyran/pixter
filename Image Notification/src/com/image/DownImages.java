package com.image;
//svn test
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
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
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
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
	public Context newContext;
	int numOfPictures=0;
	static int picsB4=0;
	ArrayList<String> picNames=new ArrayList<String>(20);
	int picNum=5;
	boolean go=true;//false means downloading. true means done
	int savePic;
	
	Bitmap daPicture;

	/*----------------------------New----------------------------*/
	///NOTE: THERE ARE TWO getServerData fuctions overloaded
	//ONE TO GET # OF PICTURES ONLINE OTHER TO DOWNLOAD THEM
	@Override
	public void onReceive(Context context, Intent intent) 
	{

		newContext=context;
		numOfPictures=getServerData(GETMAX,context);	//gets humber of pictures
		
		if(numOfPictures != picsB4)//new pictures can be less or > than before
		{//NOTE:IT IS CHECKING FOR CHANGES SO IF ONE ADDED THEN SUB WONT WORK
			getServerData(KEY_121);//gets names of pictures and saves on picNames
			
	
			//picNum=picNames.size();
				picNum=20;
				savePic=20;
			while(picNum>0)
			{				
				changeURLStr(picNames.get(picNum-1),context);//download images
				//resetPurgeTimer();
			//	Bitmap bitmap = getBitmapFromCache(url + picNames.get(picNum-1));
				//while(bitmap==null)
				//{
			//		   Toast.makeText(context, "Waiting", Toast.LENGTH_LONG).show();
				//	   bitmap = getBitmapFromCache(url + picNames.get(picNum-1));
					//clearCache();
				//	changeURLStr(url + picNames.get(picNum-1),context);//download images
			//	}
				
				picNum--;

			}
			picsB4=numOfPictures;
		}
		//getServerData(KEY_121);//gets names of pictures and saves on picNames
	//	changeURLStr(url + picNames.get(15),context);//download images
	}



	
	//new new new new new new new new new new new new new new new new new new new new new new

	//this fuction CALCULATES # OF PICTURES ONLINE NOT NAMES!!!!
	private int getServerData(String returnString,Context context) 
	{

	   InputStream is = null;
	   Toast.makeText(context, "Checking for New Pictures", Toast.LENGTH_LONG).show();

	   String result = "";
	   String result1 = "";
	   int numofPics=0;
	  // String single[] = {"","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
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
			Toast.makeText(context, "Getting names", Toast.LENGTH_LONG).show();

	            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	            StringBuilder sb = new StringBuilder();
	            String line = null;
	            while ((line = reader.readLine()) != null) 
	            {
	            	sb.append(line + "\n");
	            }
	            is.close();
	            
	            result=sb.toString();
	            numofPics = Integer.parseInt(result.trim());
	          //  result1=result;
	            Cabron=Integer.parseInt(result.trim());
	           /* while (result.length()!=0)
	            {
	            	single[i] = result1.substring(0, result1.indexOf(","));
	            	result1 = result1.substring(result1.indexOf(",")+1, result1.length()); 
	            	i++;
	            	Cabron++;
	            }*/
	            //j=j%j.
	            return numofPics;
	    }
	    catch(Exception e)
	    {
			Toast.makeText(context, "4", Toast.LENGTH_LONG).show();

	            Log.e("log_tag", "Error converting result "+e.toString());
	    }
	     return numofPics;
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
	            	picNames.add(result1.substring(0, result1.indexOf(",")));
	            	//single[i] = result1.substring(0, result1.indexOf(","));
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
	 private void changeURLStr(String image,Context context) {
			//URL reviewImageURL;
			Toast.makeText(context, "changeURLStr", Toast.LENGTH_LONG).show();

			String name =url +  image;
			try
			{
				Toast.makeText(context, "PicName "+ image, Toast.LENGTH_LONG).show();

				reviewImageURL = new URL((name));
					if (!hasExternalStoragePublicPicture(image)) 
				{
					Toast.makeText(context, "InsideIFStatement", Toast.LENGTH_LONG).show();
					isImage = false;
					new DownloadImageTask().execute(reviewImageURL);
					Log.v("log_tag", "if");
					isImage = true;
					/*File sdImageMainDirectory = new File(Environment
							.getExternalStorageDirectory(), "Download");
					sdImageMainDirectory.mkdirs();
					File file = new File(sdImageMainDirectory, name);*/
					Log.v("log_tag", "Directory created");
				}

			} 
			catch (MalformedURLException e) {
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
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
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
				Bitmap bitmap = BitmapFactory.decodeByteArray(imageData, 0,length);
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
		

			@Override
		protected void onPreExecute()
		{
			// TODO Auto-generated method stub
			super.onPreExecute();
			go=false;
			Toast.makeText(newContext, "onPreExecute", Toast.LENGTH_LONG).show();
		}


	protected void onPostExecute(Bitmap result)
	{
		
		//String name = getServerData(KEY_121);
		String name = picNames.get(savePic);
		savePic--;
		Toast.makeText(newContext, "NameOfBitmap"+ result.toString(), Toast.LENGTH_LONG).show();

		if(isCancelled())
			result=null;
		if (result != null)
		{
			hasExternalStoragePublicPicture(name);
			saveToSDCard(result, name);
			isImage = true;		
			Toast.makeText(newContext, "if", Toast.LENGTH_LONG).show();
		}
		else 
		{
			isImage = false;
			Toast.makeText(newContext, "else", Toast.LENGTH_LONG).show();
		}
			go=true;
	}
	}
	public void saveToSDCard(Bitmap bitmap, String name) {
		Toast.makeText(newContext, "in SaveToSDCard", Toast.LENGTH_LONG).show();

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

	private void saveFile(Bitmap bitmap, String name) 
	{

		String filename = name;
		Toast.makeText(newContext, "in SaveFile "+filename, Toast.LENGTH_LONG).show();

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
		//Uri uri = this.getContentResolver().insert(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
		Uri uri = newContext.getContentResolver().insert(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
		try {
		//	OutputStream outStream = this.getContentResolver().openOutputStream(uri);
			OutputStream outStream = newContext.getContentResolver().openOutputStream(uri);
			bitmap.compress(Bitmap.CompressFormat.PNG, 95, outStream);

			outStream.flush();
			outStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private boolean hasExternalStoragePublicPicture(String name)
	{
		File sdImageMainDirectory = new File(Environment.getExternalStorageDirectory(), "Download");
		File file = new File(sdImageMainDirectory, name);
		if (file != null) 
		{
			file.delete();
		}
		return file.exists();
	}
	
	 public static Drawable getDrawable()
	{
		return drawable;
	}
//***************************************************************************
	    /*
	     * Cache-related fields and methods.
	     * 
	     * We use a hard and a soft cache. A soft reference cache is too aggressively cleared by the
	     * Garbage Collector.
	     */
	    
	    private static final int HARD_CACHE_CAPACITY = 10;
	    private static final int DELAY_BEFORE_PURGE = 10 * 1000; // in milliseconds


	    /**
	     * Clears the image cache used internally to improve performance. Note that for memory
	     * efficiency reasons, the cache will automatically be cleared after a certain inactivity delay.
	     */

	    // Hard cache, with a fixed maximum capacity and a life duration
	    private final HashMap<String, Bitmap> sHardBitmapCache =
	        new LinkedHashMap<String, Bitmap>(HARD_CACHE_CAPACITY / 2, 0.75f, true) {
	        @Override
	        protected boolean removeEldestEntry(LinkedHashMap.Entry<String, Bitmap> eldest) {
	            if (size() > HARD_CACHE_CAPACITY) {
	                // Entries push-out of hard reference cache are transferred to soft reference cache
	                sSoftBitmapCache.put(eldest.getKey(), new SoftReference<Bitmap>(eldest.getValue()));
	                return true;
	            } else
	                return false;
	        }
	    };

	    // Soft cache for bitmaps kicked out of hard cache
	    private final static ConcurrentHashMap<String, SoftReference<Bitmap>> sSoftBitmapCache =
	        new ConcurrentHashMap<String, SoftReference<Bitmap>>(HARD_CACHE_CAPACITY / 2);

	    private final Handler purgeHandler = new Handler();

	    private final Runnable purger = new Runnable() {
	        public void run() {
	            clearCache();
	        }
	    };

	    /**
	     * Adds this bitmap to the cache.
	     * @param bitmap The newly downloaded bitmap.
	     */
	    private void addBitmapToCache(String url, Bitmap bitmap) {
	        if (bitmap != null) {
	            synchronized (sHardBitmapCache) {
	                sHardBitmapCache.put(url, bitmap);
	            }
	        }
	    }

	    /**
	     * @param url The URL of the image that will be retrieved from the cache.
	     * @return The cached bitmap or null if it was not found.
	     */
	    private Bitmap getBitmapFromCache(String url) {
	        // First try the hard reference cache
	        synchronized (sHardBitmapCache) {
	            final Bitmap bitmap = sHardBitmapCache.get(url);
	            if (bitmap != null) {
	                // Bitmap found in hard cache
	                // Move element to first position, so that it is removed last
	                sHardBitmapCache.remove(url);
	                sHardBitmapCache.put(url, bitmap);
	                return bitmap;
	            }
	        }

	        // Then try the soft reference cache
	        SoftReference<Bitmap> bitmapReference = sSoftBitmapCache.get(url);
	        if (bitmapReference != null) {
	            final Bitmap bitmap = bitmapReference.get();
	            if (bitmap != null) {
	                // Bitmap found in soft cache
	                return bitmap;
	            } else {
	                // Soft reference has been Garbage Collected
	                sSoftBitmapCache.remove(url);
	            }
	        }

	        return null;
	    }
	 
	    /**
	     * Clears the image cache used internally to improve performance. Note that for memory
	     * efficiency reasons, the cache will automatically be cleared after a certain inactivity delay.
	     */
	    public void clearCache() {
	        sHardBitmapCache.clear();
	        sSoftBitmapCache.clear();
	    }

	    /**
	     * Allow a new delay before the automatic cache clear is done.
	     */
	    private void resetPurgeTimer() {
	        purgeHandler.removeCallbacks(purger);
	        purgeHandler.postDelayed(purger, DELAY_BEFORE_PURGE);
	    }


}//end of class
