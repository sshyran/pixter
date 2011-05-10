package org.example.sudoku;
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
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class image extends Activity {
	private static final String TAG = "PRANJAL";
	private boolean isImage = false;
	public String reviewImageLink;
	public URL reviewImageURL;
	public boolean chgURL = false;
//	private final String imageInSD = "/sdcard/Download/";//brooklyn.PNG";
	private ViewFlipper flip;
	public String jj = "";
	public int j = 0;
	//String url = "http://www.javacpp.com/steve/images/";
	
	String url1="http://www.javacpp.com/pixter/";
	String url2=login.user;
	String url3="/images/";
	
	List<String> single = new ArrayList<String>(); 

	String url = url1 + url2+ url3;
	
	Drawable drawable;
	TextView txt;
	ImageView imgView ;
	TextView txtView;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
		setContentView(R.layout.viewpictures);

        flip = (ViewFlipper)findViewById(R.id.flip);
        
        flip.setInAnimation(this,android.R.anim.fade_in);
       //when a view disappears
        flip.setOutAnimation(this, android.R.anim.fade_out); 
   
         txtView =(TextView)findViewById(R.id.textView1);
         txtView.setText(Integer.toString(j));
         imgView =(ImageView)findViewById(R.id.ImageView01);
       
         drawable = LoadImageFromWebOperations(url + getServerData(KEY_121));
         imgView.setImageDrawable(drawable);
      
        //flip.setFlipInterval(2000);
        //flip.setAutoStart(true);
        
	}
	//public static final String KEY_121 = "http://javacpp.com/steve/db.php"; //i use my real ip here
	public static final String KEY_121 = login.pixterWeb;
	private String getServerData(String returnString) 
	{
	   InputStream is = null;

	   String result = "";
	   String result1 = "";
	   //List<String> single = new ArrayList<String>(); 
	   //String single[] = {"","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
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
	            	//single[i] = result1.substring(0, result1.indexOf(","));
	            	single.add(result1.substring(0, result1.indexOf(",")));
	            	result1 = result1.substring(result1.indexOf(",")+1, result1.length()); 
	            	i++;
	            }
	            //j=j%j.
	            //return single[j];
	            return single.get(j);
	    }
	    catch(Exception e)
	    {
	            Log.e("log_tag", "Error converting result "+e.toString());
	    }
	     return single.get(j);
	     //return single[j];
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
    
	 public void nextpic_handler(View v)
	    {
		 if (j + 1 >= single.size()) j = 0;
		 j = j +1;
		 
		 drawable = LoadImageFromWebOperations(url + getServerData(KEY_121));
		 imgView.setImageDrawable(drawable);
		   txtView =(TextView)findViewById(R.id.textView1);
	        txtView.setText(Integer.toString(j));
	    }
	 
	 public void previous_handler(View v)
	    {
		 j = j - 1;
		 if (j < 0) j = single.size() - 1;
		 drawable = LoadImageFromWebOperations(url + getServerData(KEY_121));
		 imgView.setImageDrawable(drawable);
		   txtView =(TextView)findViewById(R.id.textView1);
	        txtView.setText(Integer.toString(j));
	    }
	//Download Image Button
	 public void ClickHandler(View v)
	    {
		 Toast.makeText(this, "Image Being Downloaded", Toast.LENGTH_SHORT).show();
		 changeURLStr(url + getServerData(KEY_121));
	    }
	 
	 //Download Image Function, calls other functions to download from internet and save to SDcard
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
					
					Toast.makeText(this, "Download Has Finished", Toast.LENGTH_SHORT).show();
				}

			} catch (MalformedURLException e) {
				Toast.makeText(this, "Download Failed", Toast.LENGTH_SHORT).show();
				Log.v(TAG, e.toString());
			}
		} 
	 
	 
	private class DownloadImageTask extends AsyncTask<URL, Integer, Bitmap> {
		// This class definition states that DownloadImageTask will take String
		// parameters, publish Integer progress updates, and return a Bitmap
		@Override
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
		@Override
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
}

