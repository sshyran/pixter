package pixter.website;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class pixterWebsite extends Activity {
    /** Called when the activity is first created. */
	//WebView mWebView;
	
	 private WebView wv;  
	   
	 private ValueCallback<Uri> mUploadMessage;  
	 private final static int FILECHOOSER_RESULTCODE=1;  
	   
	 @Override  
	 protected void onActivityResult(int requestCode, int resultCode,  
	                                    Intent intent) {  
	  if(requestCode==FILECHOOSER_RESULTCODE)  
	  {  
	   if (null == mUploadMessage) return;  
	            Uri result = intent == null || resultCode != RESULT_OK ? null  
	                    : intent.getData();  
	            mUploadMessage.onReceiveValue(result);  
	            mUploadMessage = null;  
	              
	  }  
	 }  
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);  

 	wv = new WebView(this);  
	wv.setWebViewClient(new WebViewClient());
	wv.loadUrl("http://www.javacpp.com/steve/php.php");
	wv.setWebChromeClient(new WebChromeClient()  
{  
       //The undocumented magic method override  
       //Eclipse will swear at you if you try to put @Override here  
       public void openFileChooser(ValueCallback<Uri> uploadMsg) {  
         
        mUploadMessage = uploadMsg;  
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);  
        i.addCategory(Intent.CATEGORY_OPENABLE);  
        i.setType("image/*");  
        pixterWebsite.this.startActivityForResult(Intent.createChooser(i,"File Chooser"), FILECHOOSER_RESULTCODE);  
   
       }  
});  

		setContentView(wv);  
        
    }
    
    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wv.canGoBack()) {
            wv.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}