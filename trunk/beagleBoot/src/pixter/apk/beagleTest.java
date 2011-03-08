package pixter.apk;

import android.app.Activity;
import android.os.Bundle;
//import android.content.Context;
//import android.content.Intent;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.Context;

 

public class beagleTest extends Activity {
    /** Called when the activity is first created. */
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        setContentView(R.layout.main);
    }   
    
    @Override 
    public void onBackPressed()
             {
             }   
    

}
