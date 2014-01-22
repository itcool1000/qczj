package com.itcool.qczj;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	WebView wv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        wv.loadUrl("http://www.163.com");
    }
    @SuppressLint("SetJavaScriptEnabled")
	public void init(){
    	wv=(WebView)findViewById(R.id.page_mainpage);
        wv.getSettings().setJavaScriptEnabled(true);//可用JS
        wv.setScrollBarStyle(0);
        wv.setWebViewClient(new WebViewClient(){   
            public boolean shouldOverrideUrlLoading(final WebView view, final String url) {
            	wv.loadUrl(url);
                return true;   
            }
			
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
			} 

			
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
			}
			
            
        });
        wv.setWebChromeClient(new WebChromeClient(){
        	public void onProgressChanged(WebView view,int progress){
                super.onProgressChanged(view, progress);   
            }   
        });
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wv.canGoBack()) {   
            wv.goBack();   
            return true;   
        }else if(keyCode == KeyEvent.KEYCODE_BACK){
        	exitWeb();
        	return true; 
        }   
        return super.onKeyDown(keyCode, event);   
    }
    
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
			case R.id.action_login:
				Log.w("optionsItem", "action_login  selected");
				wv.loadUrl("http://192.168.199.201:80/snowmobile/Weixin/login/login.jsp");
				break;
			case R.id.action_loginout:
				Log.w("optionsItem", "action_loginout  selected");
				exitWeb();
				break;
			case R.id.action_signin:
				Log.w("optionsItem", "action_signin   selected");
				wv.loadUrl("http://192.168.199.201:80/snowmobile/Weixin/login/login.jsp");
				break;
			case R.id.action_settings:
				Log.w("optionsItem", "action_settings   selected");
				wv.loadUrl("http://192.168.199.201:80/snowmobile/Weixin/login/login.jsp");
				break;
			default :
				break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	private void exitWeb(){
		AlertDialog.Builder ad=new AlertDialog.Builder(MainActivity.this);
    	ad.setTitle("退出");
    	ad.setMessage("确定退出系统?");
    	ad.setPositiveButton("是", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int i) {
				MainActivity.this.finish();
 
			}
		});
    	ad.setNegativeButton("否",new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int i) {
				
			}
		});
    	ad.show();
    	
	}
}