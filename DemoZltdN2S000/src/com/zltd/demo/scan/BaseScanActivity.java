package com.zltd.demo.scan;

import java.io.UnsupportedEncodingException;

import com.zltd.industry.ScannerManager;
import com.zltd.industry.ScannerManager.IScannerStatusListener;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class BaseScanActivity extends Activity{

	Handler handler = new Handler();
	protected ScannerManager mScannerManager;
	protected SoundUtils mSoundUtils;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initSound();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		// 移除监听
		if (mScannerManager != null) {
			mScannerManager.removeScannerStatusListener(mIScannerStatusListener);
		}
//		mScannerManager.scannerEnable(false); 
//		mScannerManager = null;
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		initScanner();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	private void initSound() {
		mSoundUtils = SoundUtils.getInstance();
		mSoundUtils.init(this);
	}

	protected void onScanned(String barcode){
		
	}
	
	private void initScanner() {

		try {
			// 初始化扫描
			mScannerManager = ScannerManager.getInstance();
			mScannerManager.scannerEnable(true);// 扫描可用   true:switch on barcode scanner; 
//			                                               false: switch off barcode scanner;

			
			mScannerManager.setScanMode(ScannerManager.SCAN_SINGLE_MODE);// 单扫
//			 mScannerManager.setScanMode(ScannerManager.SCAN_CONTINUOUS_MODE);//连扫
			

			mScannerManager.setDataTransferType(ScannerManager.TRANSFER_BY_API);// API获取扫描数据
			//mScannerManager.setDataTransferType(ScannerManager.TRANSFER_BY_EDITTEXT);// 填充文本编辑框
			//mScannerManager.setDataTransferType(ScannerManager.TRANSFER_BY_KEY);// 转换为按键发送
			
			
			// 注册监听
			mScannerManager.addScannerStatusListener(mIScannerStatusListener);
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "程序只能在N2S机器上运行", Toast.LENGTH_SHORT).show();
		}
	}

	private IScannerStatusListener mIScannerStatusListener = new IScannerStatusListener() {

		@Override
		public void onScannerStatusChanage(int paramInt) {

			System.out.println("onScannerStatusChanage-------" + paramInt);
		}

		@Override
		public void onScannerResultChanage(final byte[] paramArrayOfByte) {
//			mSoundUtils.success();
			handler.post(new Runnable() {

				@Override
				public void run() {
					String s = null;
					try {
						s = new String(paramArrayOfByte, "UTF-8");
					} catch (UnsupportedEncodingException ex) {
						ex.printStackTrace();
					}
					if (s != null) {
//						textView.setText(textView.getText().toString() + "\r\n" + s);
//						BarcodeFactoty.getInstance(TestActivity.this).checkBarcode(s);
						onScanned(s);
//						textView.setText(s);/
					}
				}
			});
		}
	};
	
}
