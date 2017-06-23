package com.zltd.demo.scan

import java.util.ArrayList
import com.zltd.demo.scan.R
import com.zltd.industry.ScannerManager
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

/**
 *
 * @author zhoufulin
 * @time 2016/05/27
 * N2S000扫描demo
 */
class TestActivity : BaseScanActivity(), View.OnClickListener {
	
	override
	fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		initView()
	}

	override
	protected fun onStart() {
		super.onStart()
	}

     override
	protected fun onResume() {
		super.onResume()
	}

	override
	protected fun onStop() {
		super.onStop()
	}

	override
	protected fun onDestroy() {
	}

	private var textView: TextView? = null
//	 val mWayBilledit: EditText? = null
//	private var mBackBilledit: EditText? = null
//	private var mSiteedit: EditText? = null
//	private var mBigBagedit: EditText? = null
	  var mWayBilledit = findViewById(R.id.waybillno) as EditText
	  var mBackBilledit = findViewById(R.id.backno) as EditText
	  var mSiteedit = findViewById(R.id.siteno) as EditText
	  var mBigBagedit = findViewById(R.id.bigbagno) as EditText
	 val mViews : ArrayList<View>  = ArrayList<View>()
	 fun initView() {
		setContentView(R.layout.activity_test)
       textView = findViewById(R.id.tv1) as TextView
//	   var mWayBilledit = findViewById(R.id.waybillno) as EditText
//	  var mBackBilledit = findViewById(R.id.backno) as EditText
//	  var mSiteedit = findViewById(R.id.siteno) as EditText
//	  var mBigBagedit = findViewById(R.id.bigbagno) as EditText
		mViews!!.add(mWayBilledit)
		mViews!!.add(mBackBilledit)
		mViews!!.add(mSiteedit)
		mViews!!.add(mBigBagedit)
		
		InputViewManager.getInstance().setmViews(mViews)
	}
//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		// TODO Auto-generated method stub
//		if((keyCode != 0x4) && (keyCode == 0x6f)
//		 || event.getRepeatCount() == 0) {
//			 Toast.makeText(this,"oooooooo",500).show();
//		 }
//		 return true;
//	}


	override
	fun onClick(v: View?) {
// 按钮事件
		when (v!!.getId()) {
			R.id.btn_1 ->
// 单扫
				mScannerManager.setScanMode(ScannerManager.SCAN_SINGLE_MODE)
			R.id.btn_2 ->
// 连扫
				mScannerManager.setScanMode(ScannerManager.SCAN_CONTINUOUS_MODE)
//			mWayBilledit!!.setText("11")
			R.id.btn_3 -> {
// 跳转
				val intent = Intent(this@TestActivity, WebActivity::class.java)
				startActivity(intent)
			}
			else -> {
			}
		}
	}

	override
	 fun onScanned(barcode: String?) {
		BarcodeFactoty.getInstance(this@TestActivity).checkBarcode(barcode)
		 mWayBilledit!!.setText("11")
	}
}