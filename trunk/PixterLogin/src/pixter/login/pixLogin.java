package pixter.login;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class pixLogin extends Activity {
    
    private EditText mUsername;
    private EditText mPassword;
    public static String webURL = "http://javacpp.com/";
    public static String webURL2 = "/db.php";
    public static String pixterWeb;
    TextView txtView;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mUsername = (EditText) findViewById(R.id.userName);
        mPassword = (EditText) findViewById(R.id.password);
        
        txtView =(TextView)findViewById(R.id.URL);
        
        
    }
    
    public void ClickHandler(View v)
    {
    	String user = mUsername.getText().toString();
        String pass = mPassword.getText().toString();
    	pixterWeb = webURL + user + webURL2;
    	txtView.setText(pixterWeb);
	 
    }
}