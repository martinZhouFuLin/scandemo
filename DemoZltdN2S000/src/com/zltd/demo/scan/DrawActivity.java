package com.zltd.demo.scan;

import android.app.Activity;
import android.os.Bundle;

public class DrawActivity extends Activity{
	
	 private DrawView mDrawView;
	
	 @Override  
	    public void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);  
	        setContentView(R.layout.draw);  
	        init();  
	    }  
	  
	    private void init() {  
//	        LinearLayout layout=(LinearLayout) findViewById(R.id.root);  
//	        LogUtils.i("debug","draw");
//	        final DrawView view=new DrawView(this,R.attr.DrawView, null);
////	        view.setMinimumHeight(500);  
////	        view.setMinimumWidth(300);  
//	        view.setMinimumWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//	        view.setMinimumHeight(ViewGroup.LayoutParamsO.MATCH_PARENT);  
//	        //通知view组件重绘    
//	        view.invalidate();  
//	        layout.addView(view);  
	    	
	    	mDrawView=(DrawView) findViewById(R.id.circleView);
	    	mDrawView.setPercent((int)(Math.random()*100));
	    }  
	
}
