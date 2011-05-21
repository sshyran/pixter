package org.example.sudoku;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class login extends Activity {
    
    public static EditText mUsername;
    public static String user;
    private EditText mPassword;
    public static String webURL = "http://javacpp.com/pixter/";
    public static String webURL2 = "/db.php";
    public static String pixterWeb;
    public static String authURL = "http://javacpp.com/pixter/usernameList.php";
    public int j = 0;
    public int k = 0;
    
    List<String> userList = new ArrayList<String>();
    List<String> pwList = new ArrayList<String>();

    TextView txtView;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pixlogin);
        
        mUsername = (EditText) findViewById(R.id.userName);
        mPassword = (EditText) findViewById(R.id.password);
        
        txtView =(TextView)findViewById(R.id.URL);
        getServerData(authURL);
        
    }
    
    public void ClickHandler(View v)
    {
    	k = 0;
    	boolean searchUserList = true;
    	user = mUsername.getText().toString();
        String pass = mPassword.getText().toString();
        while (searchUserList  && k < userList.size()-1){
        	if (user.equals(userList.get(k))){
        		searchUserList = false;
        	}else{
        		k++;
        	}
        				
        }
 
        if (pass.equals(pwList.get(k))){	
    	pixterWeb = webURL + user + webURL2;
    	txtView.setText("Logged in Sucessfully"
    	);startActivity(new Intent(login.this, MainScreen.class));
        }else {
        	txtView.setText("Username or Password incorrect");
        }
    }
    
	public void onBackPressed()
    { 
    	startActivity(new Intent(login.this, MainScreen.class));
    }

	private String getServerData(String returnString) 
	{
	   InputStream is = null;

	   String result = "";
	   String result1 = "";
	  
	   int i = 0;
	    //the year data to send
	    ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	    nameValuePairs.add(new BasicNameValuePair("filename","brooklyn.jpg"));
	    try
	    {
	            HttpClient httpclient = new DefaultHttpClient();
	            HttpPost httppost = new HttpPost(authURL);
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
	            
	            	userList.add(result1.substring(0, result1.indexOf(",")));
	            	result1 = result1.substring(result1.indexOf(",")+1, result1.length()); 
	            	pwList.add(result1.substring(0, result1.indexOf("|")));
	            	result1 = result1.substring(result1.indexOf("|")+1, result1.length()); 
	            	i++;
	            }
	          
	            return pwList.get(j);
	    }
	    catch(Exception e)
	    {
	            Log.e("log_tag", "Error converting result "+e.toString());
	    }
	     return pwList.get(j);

	}  
}
    