package org.example.sudoku;

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
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

public class DownImages extends BroadcastReceiver {

	/*----------------------------New----------------------------*/
	public static final String GETMAX = "http://javacpp.com/steve/max.php";
	public static final String GETPRIME = "http://javacpp.com/steve/primary.php";
	static int Cabron = 0;
	public int j;
	static Drawable drawable;
	String url = "http://www.javacpp.com/steve/images/";
	public static final String KEY_121 = "http://javacpp.com/steve/db.php"; // i
	public String reviewImageLink;
	public URL reviewImageURL;
	private static final String TAG = "PRANJAL";
	public Context newContext;
	int numOfPictures = 0;
	static int picsB4 = 0;
	ArrayList<String> picNames = new ArrayList<String>(20);
	int picNum;
	int savePic;
	int numTasks = 0;
	int numPrime;

	Bitmap daPicture;

	/*----------------------------New----------------------------*/
	// /NOTE: THERE ARE TWO getServerData fuctions overloaded
	// ONE TO GET # OF PICTURES ONLINE OTHER TO DOWNLOAD THEM
	@Override
	public void onReceive(Context context, Intent intent) 
	{
		SharedPreferences myPrefs = context.getSharedPreferences("settings",Context.MODE_WORLD_READABLE);
        SharedPreferences.Editor prefsEditor = myPrefs.edit();
		Toast.makeText(context, "Checking for New Pictures", Toast.LENGTH_LONG);
		newContext = context;
		numPrime= getServerData(GETPRIME, context);
		//prefsEditor.putInt("oldPrime", 0);///			DELETE THIS AFTER DEBUGGIN
	   // prefsEditor.commit();//							DELETE THIS AFTER DEBUGGIN
		Log.e("Prime", "Online Prime Is # " + numPrime);
		Log.e("internalPrime","oldPrime: " + myPrefs.getInt("oldPrime", 0));
		Log.e("internalMax","oldMax: " + myPrefs.getInt("oldMax", 0));
        int difference;
		numOfPictures = getServerData(GETMAX, context); // gets humber of pictures
		Log.e("Max", "Online Max Is # " + numOfPictures);


		if (numPrime > myPrefs.getInt("oldPrime", 0))
		{		
			Toast.makeText(context, "Checking for New Pictures", Toast.LENGTH_LONG);
			//numOfPictures = getServerData(GETMAX, context); // gets humber of pictures
			difference=numOfPictures- myPrefs.getInt("oldMax", 0);
			//difference=(difference>1?difference:0);
			Log.e("difference ","difference is:"+ difference);
			getServerData(KEY_121);// gets names of pictures and saves on
			picNum = numOfPictures;//DONT CHANGE THIS
			savePic = numOfPictures;//DONT CHANGE THIS
			while(difference>0)///////////work on task
			{
				Log.e("Inside while ","inside whileloop");
				changeURLStr(picNames.get(picNum - 1), context);// download
				difference--;
				picNum--;
			}
			prefsEditor.putInt("oldPrime", numPrime);
		}
		prefsEditor.putInt("oldMax", numOfPictures);
		prefsEditor.commit();
		//prefsEditor.putInt("oldPrime", 0);	//FOR DEBUGGIN
		//prefsEditor.putInt("oldMax", 0);		//FOR DEBUGGIN
		//prefsEditor.commit();					//FOR DEBUGGIN
	}

	// this fuction CALCULATES # OF PICTURES ONLINE NOT NAMES!!!!
	private int getServerData(String site, Context context)
	{

		InputStream is = null;
		// Toast.makeText(context, "Checking for New Pictures",
		// Toast.LENGTH_LONG).show();

		String result = "";
		String result1 = "";
		int numofPics = 0;
		int i = 0;
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("filename", "brooklyn.jpg"));
		try {
			// Toast.makeText(context, "one", Toast.LENGTH_LONG).show();

			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(site);
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		} catch (Exception e) {
			// Toast.makeText(context, "2", Toast.LENGTH_LONG).show();

			Log.e("log_tag", "Error in http connection " + e.toString());
		}

		// convert response to string
		try {
			// Toast.makeText(context, "Getting names",
			// Toast.LENGTH_LONG).show();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();

			result = sb.toString();
			numofPics = Integer.parseInt(result.trim());
			// result1=result;
			Cabron = Integer.parseInt(result.trim());
			return numofPics;
		} catch (Exception e) {
			// Toast.makeText(context, "4", Toast.LENGTH_LONG).show();

			Log.e("log_tag", "Error converting result " + e.toString());
		}
		return numofPics;
		// return result;
	}

	private void getServerData(String returnString) {
		InputStream is = null;

		String result = "";
		String result1 = "";
		int i = 0;
		// the year data to send
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("filename", "brooklyn.jpg"));
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(KEY_121);
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
		}

		// convert response to string
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result = sb.toString();
			result1 = result;
			while (result.length() != 0) {
				picNames.add(result1.substring(0, result1.indexOf(",")));
				// single[i] = result1.substring(0, result1.indexOf(","));
				result1 = result1.substring(result1.indexOf(",") + 1,result1.length());
				i++;
			}	
		} catch (Exception e) {
			Log.e("log_tag", "Error converting result " + e.toString());
		}
	}

	private Drawable LoadImageFromWebOperations(String url) {
		try {
			InputStream is = (InputStream) new URL(url).getContent();
			Drawable d = Drawable.createFromStream(is, "src name");
			return d;
		} catch (Exception e) {
			System.out.println("Exc=" + e);
			return null;
		}
	}

	private void changeURLStr(String image, Context context) {
		// URL reviewImageURL;
		// Toast.makeText(context, "changeURLStr", Toast.LENGTH_LONG).show();
		numTasks++;

		String name = url + image;
		try {
			// Toast.makeText(context, "PicName " + image,
			// Toast.LENGTH_LONG).show();

			reviewImageURL = new URL((name));
			if (!hasExternalStoragePublicPicture(image)) {
				// Toast.makeText(context, "InsideIFStatement",
				// Toast.LENGTH_LONG).show();

				new DownloadImageTask().execute(reviewImageURL);

				Log.v("log_tag", "if");
				/*
				 * File sdImageMainDirectory = new File(Environment
				 * .getExternalStorageDirectory(), "Download");
				 * sdImageMainDirectory.mkdirs(); File file = new
				 * File(sdImageMainDirectory, name);
				 */
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

			// String name = getServerData(KEY_121);
			String name = picNames.get(savePic - 1);
			savePic--;
			// Toast.makeText(newContext, "NameOfBitmap" +
			// result.toString(),Toast.LENGTH_LONG).show();

			if (isCancelled())
				result = null;
			if (result != null) {
				hasExternalStoragePublicPicture(name);
				saveToSDCard(result, name);
				// Toast.makeText(newContext, "if", Toast.LENGTH_LONG).show();
			} else {
				// Toast.makeText(newContext, "else", Toast.LENGTH_LONG).show();
			}
		}
	}

	public void saveToSDCard(Bitmap bitmap, String name) {
		// Toast.makeText(newContext, "in SaveToSDCard",
		// Toast.LENGTH_LONG).show();

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
		// Toast.makeText(newContext, "in SaveFile " + filename,
		// Toast.LENGTH_LONG).show();

		ContentValues values = new ContentValues();
		File sdImageMainDirectory = new File(
				Environment.getExternalStorageDirectory(), "Download");
		sdImageMainDirectory.mkdirs();
		File outputFile = new File(sdImageMainDirectory, filename);
		values.put(MediaStore.MediaColumns.DATA, outputFile.toString());
		values.put(MediaStore.MediaColumns.TITLE, filename);
		values.put(MediaStore.MediaColumns.DATE_ADDED,
				System.currentTimeMillis());
		values.put(MediaStore.MediaColumns.MIME_TYPE, "image/png");
		// Uri uri =
		// this.getContentResolver().insert(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
		Uri uri = newContext.getContentResolver().insert(
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
				values);
		try {
			// OutputStream outStream =
			// this.getContentResolver().openOutputStream(uri);
			OutputStream outStream = newContext.getContentResolver()
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
		File sdImageMainDirectory = new File(
				Environment.getExternalStorageDirectory(), "Download");
		File file = new File(sdImageMainDirectory, name);
		if (file != null) {
			file.delete();
		}
		return file.exists();
	}
}// end of class
