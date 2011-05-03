package android.emailer;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AndroidEmailer extends Activity {
	
	private EditText mEmailTo;
    private EditText mSubject;
    private EditText mBody;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mEmailTo = (EditText) findViewById(R.id.emailaddress);
        mSubject = (EditText) findViewById(R.id.emailsubject);
        mBody = (EditText) findViewById(R.id.emailtext);
        
        Button send = (Button) this.findViewById(R.id.send);
        
        send.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
            	String EmailTo = mEmailTo.getText().toString();
                String Subject = mSubject.getText().toString();
                String Body = mBody.getText().toString();
                try {   
                    GMailSender sender = new GMailSender("carbertspam@gmail.com", "pixter4ever");
                    sender.sendMail(Body,   
                    		Subject,   
                            "saderfox@gmail.com",   
                            EmailTo);
                    
           		 
                } catch (Exception e) {   
                    Log.e("SendMail", e.getMessage(), e);   
                } 

            }
        });

    }
}
