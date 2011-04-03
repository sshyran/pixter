package com.image;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
public class sqlitePix extends Activity {
/** Called when the activity is first created. */

TextView txt;
@Override
public void onCreate(Bundle savedInstanceState) 
{
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    // Create a crude view - this should really be set via the layout resources 
    // but since its an example saves declaring them in the XML. 
    LinearLayout rootLayout = new LinearLayout(getApplicationContext()); 
    txt = new TextView(getApplicationContext()); 
    rootLayout.addView(txt); 
    setContentView(rootLayout); 
    // Set the text and call the connect function. 
    txt.setText("Connecting...");
  //call the method to run the data retrieval
    txt.setText(getServerData(KEY_121));
}
public static final String KEY_121 = "http://javacpp.com/steve/db.php"; //i use my real ip here
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
   
    }
    catch(Exception e)
    {
            Log.e("log_tag", "Error converting result "+e.toString());
    }
     return single[0]+single[1];
    //return result;
}   
}
