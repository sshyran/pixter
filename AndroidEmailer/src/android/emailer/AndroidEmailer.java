package android.emailer;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class AndroidEmailer extends Activity {
	
	private EditText mEmailTo;
    private EditText mSubject;
    private EditText mBody;
    
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mEmailTo = (EditText) findViewById(R.id.emailaddress);
        mSubject = (EditText) findViewById(R.id.emailsubject);
        mBody = (EditText) findViewById(R.id.emailtext);
        
        rb1=(RadioButton)findViewById(R.id.radio0);
        rb2=(RadioButton)findViewById(R.id.radio1);
        rb3=(RadioButton)findViewById(R.id.radio2);
        
        Button send = (Button) this.findViewById(R.id.send);
        Button sms = (Button) this.findViewById(R.id.sms);
        
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
        
        sms.setOnClickListener(new View.OnClickListener() {
        	
        	
            public void onClick(View v) {
                // TODO Auto-generated method stub
            	String EmailTo = mEmailTo.getText().toString();
                String Subject = mSubject.getText().toString();
                String Body = mBody.getText().toString();
                if(rb1.isChecked() == true)
                	EmailTo = EmailTo + "@txt.att.net";
                if(rb2.isChecked() == true)
                	EmailTo = EmailTo + "@messaging.sprintpcs.com";
                if(rb3.isChecked() == true)
                	EmailTo = EmailTo + "@vtext.com";
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
