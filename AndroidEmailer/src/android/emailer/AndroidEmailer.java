package android.emailer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AndroidEmailer extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button send = (Button) this.findViewById(R.id.send);
        
        send.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub

                try {   
                    GMailSender sender = new GMailSender("carbertspam@gmail.com", "pixter4ever");
                    sender.sendMail("This is Subject",   
                            "This is Body",   
                            "saderfox@gmail.com",   
                            "saderfox@gmail.com");
                    
           		 
                } catch (Exception e) {   
                    Log.e("SendMail", e.getMessage(), e);   
                } 

            }
        });

    }
}
