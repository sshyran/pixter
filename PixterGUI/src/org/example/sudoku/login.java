package org.example.sudoku;

import android.app.Activity;
import android.os.Bundle;
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

    TextView txtView;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pixlogin);
        
        mUsername = (EditText) findViewById(R.id.userName);
        mPassword = (EditText) findViewById(R.id.password);
        
        txtView =(TextView)findViewById(R.id.URL);
        
        
    }
    
    public void ClickHandler(View v)
    {
    	user = mUsername.getText().toString();
        String pass = mPassword.getText().toString();
    	pixterWeb = webURL + user + webURL2;
    	txtView.setText(pixterWeb);
	 
    }
}