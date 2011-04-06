package org.example.sudoku;

//import org.example.sudoku.R;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//import R.attr;
public class MainScreen extends Activity 
{  
Button b1;
Button b2;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main); 
        b1 = (Button) findViewById(R.id.viewPicturesButton);
        b2 = (Button) findViewById(R.id.options_button);
        b1.setOnClickListener(myhandler1);
        b2.setOnClickListener(myhandler2);
      }
      View.OnClickListener myhandler1 = new View.OnClickListener() {
        public void onClick(View v) {
        	startActivity(new Intent(MainScreen.this, ViewPictures.class));
        }
      };
      View.OnClickListener myhandler2 = new View.OnClickListener() {
        public void onClick(View v) {
        	startActivity(new Intent(MainScreen.this, ViewPictures.class));
        };
        
    };

   }