package com.zltd.demo.scan;

import com.zltd.demo.scan.R;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class BarcodeFactoty {
	private static Context mContext;

	
////	HashMAP��У�����ʾ��һ��
//	private static HashMap<Integer, Pattern> sPatternMap = new HashMap<Integer, Pattern>();
	public static BarcodeFactoty getInstance(Context context) {
		mContext = context;
		return new BarcodeFactoty();
	}

	public void checkBarcode(String barcode) {
//		String result = "";

		switch (barcode.length()) {
		case 4:
			Toast.makeText(mContext, "����4:" + barcode.length(), Toast.LENGTH_SHORT).show();
			SoundUtils.getInstance().warn();
			break;
		case 5:
			Toast.makeText(mContext, "����5:" + barcode.length(), Toast.LENGTH_SHORT).show();
			SoundUtils.getInstance().warn();
			break;
		case 6:
			Toast.makeText(mContext, "����6:" + barcode.length(), Toast.LENGTH_SHORT).show();
			setContent(R.id.siteno, barcode);
			break;
		case 8:
			Toast.makeText(mContext, "����8:" + barcode.length(), Toast.LENGTH_SHORT).show();
			SoundUtils.getInstance().warn();
			break;
		case 9:
			Toast.makeText(mContext, "����9:" + barcode.length(), Toast.LENGTH_SHORT).show();
			SoundUtils.getInstance().warn();
			break;
		case 10:
			Toast.makeText(mContext, "����10:" + barcode.length(), Toast.LENGTH_SHORT).show();
			SoundUtils.getInstance().warn();
			break;
		case 11:
			Toast.makeText(mContext, "����11:" + barcode.length(), Toast.LENGTH_SHORT).show();
			SoundUtils.getInstance().warn();
			break;
		case 12:
			Toast.makeText(mContext, "����12:" + barcode.length(), Toast.LENGTH_SHORT).show();
			SoundUtils.getInstance().warn();
			break;
		case 13:
			// Toast.makeText(mContext, "����13:" +RegexUtils.checkWayBillNo(barcode), Toast.LENGTH_SHORT).show();
			if (setContent(R.id.backno, barcode)) {
				return;
			}
			if (setContent(R.id.bigbagno, barcode)) {
				return;
			}
			setContent(R.id.waybillno, barcode);
			break;
		case 18:
			if (setContent(R.id.bigbagno, barcode)) {
				return;
			}
			Toast.makeText(mContext, "����18:" + RegexUtils.checkWayBillNo(barcode), Toast.LENGTH_SHORT).show();
			setContent(R.id.waybillno, barcode);
			break;
		case 24:
			if (setContent(R.id.bigbagno, barcode)) {
				return;
			}
			Toast.makeText(mContext, "����24:" + RegexUtils.checkWayBillNo(barcode), Toast.LENGTH_SHORT).show();
			setContent(R.id.waybillno, barcode);
			break;
		default:

			break;
		}
		// return result;
	}

	private void saveData() {
		Boolean result = true;
		for (View view : InputViewManager.getInstance().getmViews()) {
			// if(RegexUtils.check)
			switch (view.getId()) {
			case R.id.waybillno:
				result = RegexUtils.checkWayBillNo(((EditText) view).getText().toString());
				if (!result) {
					Toast.makeText(mContext, "���Ŵ���", Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.siteno:
				if (result) {
					result = RegexUtils.checkSiteNo(((EditText) view).getText().toString());
					if (!result) {
						Toast.makeText(mContext, "վ�����", Toast.LENGTH_SHORT).show();
					}
				}
				break;
			case R.id.backno:
				if (result) {
					result = RegexUtils.checkBackNo(((EditText) view).getText().toString());
					if (!result) {
						Toast.makeText(mContext, "�ص�����", Toast.LENGTH_SHORT).show();
					}
				}
				break;
			case R.id.bigbagno:
				if (result) {
					result = RegexUtils.checkBigBagNo(((EditText) view).getText().toString());
					if (!result) {
						Toast.makeText(mContext,"����Ŵ���", Toast.LENGTH_SHORT).show();
					}
				}
				break;	
			default:
				break;
			}
		}
		if(result){
			Toast.makeText(mContext, "ȫ����ȷ", Toast.LENGTH_SHORT).show();
			SoundUtils.getInstance().success();
		}else{
			SoundUtils.getInstance().warn();
		}
		// return false;
	}

	public EditText checkViews(int viewId) {
		View mView = null;
		for (View view : InputViewManager.getInstance().getmViews()) {
			if (view.getId() == viewId) {
				mView = view;
				return (EditText) mView;
			}
		}
		SoundUtils.getInstance().warn();
		return (EditText) mView;
	}

	public boolean setContent(int viewId, String barcode) {
		boolean result = false;
		if (checkViews(viewId) != null) {
			switch (viewId) {
			case R.id.bigbagno:
				if (RegexUtils.checkBigBagNo(barcode)) {
					checkViews(viewId).setText(barcode);
					SoundUtils.getInstance().success();
					result = true;
				}
				break;
			case R.id.backno:
				if (RegexUtils.checkBackNo(barcode)) {
					checkViews(viewId).setText(barcode);
					SoundUtils.getInstance().success();
					result = true;
				}
				break;

			case R.id.waybillno:
				checkViews(viewId).setText(barcode);
//				SoundUtils.getInstance().success();
				result = true;
				saveData();
				break;
			default:
				checkViews(viewId).setText(barcode);
				SoundUtils.getInstance().success();
				result = true;
				break;
			}
		}

		return result;
	}
}
