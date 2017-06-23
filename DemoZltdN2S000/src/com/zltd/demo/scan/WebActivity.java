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
	        // ����WebView
	        webView= (WebView) findViewById(R.id.webview);
	        // ��ȡWebView����
	        WebSettings ws = webView.getSettings();
	        // ����JavaScript
	        ws.setJavaScriptEnabled(true);
	        // ������assetsĿ¼�µ�һ��ҳ��
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
	         * ע�������@JavascriptInterfaceע�⣬ target��4.2���϶���Ҫ������ע�⣬�����޷����� 
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
