package com.zltd.demo.scan;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class DrawView extends View {

	// Բ�İ뾶
	private float mRadius;

	// ɫ���Ŀ��
	private float mStripeWidth;
	// �����С
	private int mHeight;
	private int mWidth;

	// Բ������
	private float x;
	private float y;

	// Ҫ���Ļ���
	private int mEndAngle;

	// СԲ����ɫ
	private int mSmallColor;
	// ��Բ��ɫ
	private int mBigColor;

	// ���İٷֱ����ִ�С
	private float mCenterTextSize;
	
	   //����λ�ðٷֱȽ���
    private int mCurPercent;

    //ʵ�ʰٷֱȽ���
    private int mPercent;

	public DrawView(Context context) {
		super(context, null);
		LogUtils.i("debug", "draw1");
	}

	public DrawView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		LogUtils.i("debug", "draw2");

	}

	
	
	public DrawView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		LogUtils.i("debug", "draw3");
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DrawView, defStyleAttr, 0);
		 mStripeWidth = a.getDimension(R.styleable.DrawView_stripeWidth, PxUtils.dpToPx(30, context));
		mSmallColor = a.getColor(R.styleable.DrawView_smallColor, 0xffafb4db);
		mBigColor = a.getColor(R.styleable.DrawView_bigColor, 0xff6950a1);
		mCenterTextSize = a.getDimensionPixelSize(R.styleable.DrawView_centerTextSize, PxUtils.spToPx(20, context));
		mRadius = a.getDimensionPixelSize(R.styleable.DrawView_radius, PxUtils.dpToPx(40, context));
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		LogUtils.i("debug", "draw4");
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);

		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);

		if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
			mRadius = widthSize / 2;
			x = widthSize / 2;
			y = heightSize / 2;
			mWidth = widthSize;
			mHeight = heightSize;
			LogUtils.i("debug", "w+h1==" + mWidth + "==" + mHeight);
		}
		if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
			mWidth = (int) (mRadius * 2);
			mHeight = (int) (mRadius * 2);
			x = mRadius;
			y = mRadius;
			LogUtils.i("debug", "w+h2==" + mWidth + "==" + mHeight);
		}
		LogUtils.i("debug", "w+h3==" + mWidth + "==" + mHeight);
		setMeasuredDimension(mWidth, mHeight);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		LogUtils.i("debug", "w+h0==" + x + "==" + y);
		mEndAngle = (int) (mCurPercent * 3.6);
		/*
		 * ���� ˵�� drawRect ���ƾ��� drawCircle ����Բ�� drawOval ������Բ drawPath ������������
		 * drawLine ����ֱ�� drawPoin ���Ƶ�
		 */
		// ��������
		Paint p = new Paint();
		p.setColor(mBigColor);// ���ú�ɫ
		p.setAntiAlias(true);
		canvas.drawCircle(x, y, mRadius, p);

		Paint sectorPaint = new Paint();
		sectorPaint.setColor(mSmallColor);
		sectorPaint.setAntiAlias(true);
		RectF rect = new RectF(0,(mHeight/2-mRadius), mWidth,mRadius*2);
		// ����˵����֪ʶ����
		canvas.drawRect(rect, sectorPaint);
		LogUtils.i("debug", "result=="+ mWidth+"=="+mRadius*2);
//		canvas.drawArc(rect, 270, mEndAngle, true, sectorPaint);

		
	    //����СԲ,��ɫ͸��
//        Paint smallCirclePaint = new Paint();
//        smallCirclePaint.setAntiAlias(true);
//        smallCirclePaint.setColor(mBigColor);
//        canvas.drawCircle(x, y, mRadius - mStripeWidth, smallCirclePaint);
		
		
		// canvas.drawText("��Բ��", 10, 20, p);// ���ı�
		// canvas.drawCircle(60, 20, 10, p);// СԲ
		// p.setAntiAlias(true);// ���û��ʵľ��Ч���� true��ȥ�������һ��Ч����������
		// canvas.drawCircle(120, 20, 20, p);// ��Բ
		//
		// canvas.drawText("���߼����ߣ�", 10, 60, p);
		// p.setColor(Color.GREEN);// ������ɫ
		// canvas.drawLine(60, 40, 100, 40, p);// ����
		// canvas.drawLine(110, 40, 190, 80, p);// б��
		// //��Ц������
		// p.setStyle(Paint.Style.STROKE);//���ÿ���
		// RectF oval1=new RectF(150,20,180,40);
		// canvas.drawArc(oval1, 180, 180, false, p);//С����
		// oval1.set(190, 20, 220, 40);
		// canvas.drawArc(oval1, 180, 180, false, p);//С����
		// oval1.set(160, 30, 210, 60);
		// canvas.drawArc(oval1, 0, 180, false, p);//С����
		//
		// canvas.drawText("�����Σ�", 10, 80, p);
		// p.setColor(Color.GRAY);// ���û�ɫ
		// p.setStyle(Paint.Style.FILL);//��������
		// canvas.drawRect(60, 60, 80, 80, p);// ������
		// canvas.drawRect(60, 90, 160, 100, p);// ������
		//
		// canvas.drawText("�����κ���Բ:", 10, 120, p);
		// /* ���ý���ɫ ��������ε���ɫ�Ǹı�� */
		// Shader mShader = new LinearGradient(0, 0, 100, 100,
		// new int[] { Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW,
		// Color.LTGRAY }, null, Shader.TileMode.REPEAT); //
		// һ������,�����һ�������ݶ�����һ���ߡ�
		// p.setShader(mShader);
		// // p.setColor(Color.BLUE);
		// RectF oval2 = new RectF(60, 100, 200, 240);// ���ø��µĳ����Σ�ɨ�����
		// canvas.drawArc(oval2, 200, 130, true, p);
		// // ��������һ��������RectF�������ǵڶ��������ǽǶȵĿ�ʼ�������������Ƕ��ٶȣ����ĸ����������ʱ�����Σ��Ǽٵ�ʱ�򻭻���
		// //����Բ����oval��һ��
		// oval2.set(210,100,250,130);
		// canvas.drawOval(oval2, p);
		//
		// canvas.drawText("�������Σ�", 10, 200, p);
		// // �������������,����Ի�����������
		// Path path = new Path();
		// path.moveTo(80, 200);// �˵�Ϊ����ε����
		// path.lineTo(120, 250);
		// path.lineTo(80, 250);
		// path.close(); // ʹ��Щ�㹹�ɷ�յĶ����
		// canvas.drawPath(path, p);
		//
		// // ����Ի��ƺܶ��������Σ��������滭������
		// p.reset();//����
		// p.setColor(Color.LTGRAY);
		// p.setStyle(Paint.Style.STROKE);//���ÿ���
		// Path path1=new Path();
		// path1.moveTo(180, 200);
		// path1.lineTo(200, 200);
		// path1.lineTo(210, 210);
		// path1.lineTo(200, 220);
		// path1.lineTo(180, 220);
		// path1.lineTo(170, 210);
		// path1.close();//���
		// canvas.drawPath(path1, p);
		// /*
		// * Path���װ����(����������ͼ�ε�·��
		// * ��ֱ�߶�*����������,�����η����ߣ�Ҳ�ɻ����ͻ���drawPath(·��������),Ҫô�����Ļ���
		// * (��������ķ��),���߿������ڼ��ϻ򻭻����ı���·����
		// */
		//
		// //��Բ�Ǿ���
		// p.setStyle(Paint.Style.FILL);//����
		// p.setColor(Color.LTGRAY);
		// p.setAntiAlias(true);// ���û��ʵľ��Ч��
		// canvas.drawText("��Բ�Ǿ���:", 10, 260, p);
		// RectF oval3 = new RectF(80, 260, 200, 300);// ���ø��µĳ�����
		// canvas.drawRoundRect(oval3, 20, 15, p);//�ڶ���������x�뾶��������������y�뾶
		//
		// //������������
		// canvas.drawText("������������:", 10, 310, p);
		// p.reset();
		// p.setStyle(Paint.Style.STROKE);
		// p.setColor(Color.GREEN);
		// Path path2=new Path();
		// path2.moveTo(100, 320);//����Path�����
		// path2.quadTo(150, 310, 170, 400); //���ñ��������ߵĿ��Ƶ�������յ�����
		// canvas.drawPath(path2, p);//��������������
		//
		// //����
		// p.setStyle(Paint.Style.FILL);
		// canvas.drawText("���㣺", 10, 390, p);
		// canvas.drawPoint(60, 390, p);//��һ����
		// canvas.drawPoints(new float[]{60,400,65,400,70,400}, p);//�������
		//
		// //��ͼƬ��������ͼ
		// Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
		// R.drawable.ic_launcher);
		// canvas.drawBitmap(bitmap, 250,360, p);
	}
	//�ⲿ���ðٷֱ���
    public void setPercent(int percent) {
        if (percent > 100) {
            throw new IllegalArgumentException("percent must less than 100!");
        }

        setCurPercent(percent);


    }

    //�ڲ����ðٷֱ� ���ڶ���Ч��
    private void setCurPercent(int percent) {

        mPercent = percent;

        new Thread(new Runnable() {
            @Override
            public void run() {
                int sleepTime = 1;
                for(int i =0;i<mPercent;i++){
                    if(i%20 == 0){
                        sleepTime+=2;
                    }
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mCurPercent = i;
                    DrawView.this.postInvalidate();
                }
                }

        }).start();

    }
}
