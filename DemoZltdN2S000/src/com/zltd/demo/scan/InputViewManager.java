package com.zltd.demo.scan;

import java.util.ArrayList;
import java.util.List;

import android.view.View;

public class InputViewManager {
	public  List<View>  mViews=new ArrayList<View>();
	private static InputViewManager mInputViewManager=new InputViewManager();
	
	public static InputViewManager getInstance(){
		return mInputViewManager;
	}

	public List<View> getmViews() {
		return mViews;
	}

	public void setmViews(List<View> mViews) {
		this.mViews = mViews;
	}
	
	
}
