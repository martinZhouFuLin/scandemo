package com.zltd.demo.scan;

import com.zltd.demo.scan.R;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

@SuppressLint("SetJavaScriptEnabled")
public class WebActivity  extends BaseScanActivity{
     private Handler mHandler =new Handler();
     private WebView webView;
     
     
	 @Override
	protected void onScanned(String barcode) {
		// TODO Auto-generated method stub
			mSoundUtils.getInstance().success();
		 webView.loadUrl("javascript:jsText('"+barcode+"')");  
	}
	
	@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.webview);
	        // 查找WebView
	        webView= (WebView) findViewById(R.id.webview);
	        // 获取WebView配置
	        WebSettings ws = webView.getSettings();
	        // 启用JavaScript
	        ws.setJavaScriptEnabled(true);
	        // 在载入assets目录下的一个页面
	        webView.loadUrl("file:///android_asset/scan.html");
//	        webView.loadUrl("http://192.168.67.67:81");
//	        webView.addJavascriptInterface(new Object(){
//	        	 @JavascriptInterface
//	            public void clickOnAndroid(){
//	                mHandler.post(new Runnable(){
//	                    public void run(){
//	                    	LogUtils.i("debug", "js");
////	                    	webView.loadUrl("javascript:wave'"+text+"')");  
//	                        webView.loadUrl("javascript:wave()");
//	                    }
//	                });
//	            }
//	        }, "demo");
	        webView.addJavascriptInterface(new JSInterface(), "jsi");
	    }
	    private final class JSInterface{  
	        /** 
	         * 注意这里的@JavascriptInterface注解， target是4.2以上都需要添加这个注解，否则无法调用 
	         * @param text 
	         */  
	        @JavascriptInterface  
	        public void showToast(String text){  
	            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();  
	        }  
	        @JavascriptInterface  
	        public void showJsText(String text){  
	        	 webView.loadUrl("javascript:jsText('"+text+"')");   
	        }  
	        
	        @JavascriptInterface  
	        public void clickOnAndroid(){  
	        	mHandler.post(new Runnable(){
                    public void run(){
                    	LogUtils.i("debug", "js");
                        webView.loadUrl("javascript:wave()");
                    }
                }); 
	        }  
	    }  
}
