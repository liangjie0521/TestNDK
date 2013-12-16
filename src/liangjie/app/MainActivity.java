package liangjie.app;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
	private Bitmap bm1, bm2, bm3;
	Long start;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		start = System.currentTimeMillis();
		Log.i("Time1", "" + start);
		bm1 = readBitMap(this, R.raw.img4);// 将项目中的图片生成Bitmap对象
		bm2 = readBitMap(this, R.raw.img5);// 另一张图片
		bm3 = readBitMap(this, R.raw.img7);
		compareBitmap(bm1, bm3);
	}

	private double[] compareBitmap(Bitmap bm1, Bitmap bm2) {
		int width1 = bm1.getWidth();// 获取图片1的宽
		int height1 = bm1.getHeight();// 获取图片1的高
		int width2 = bm2.getWidth();// 获取图片2的宽
		int height2 = bm2.getHeight();// 获取图片2的高
		byte[] b1 = bmpToByteArray(bm1, true);// 将bitmap转成byte，并将bitmap回收
		byte[] b2 = bmpToByteArray(bm2, true);// 将bitmap转成byte，并将bitmap回收
		Long mid = System.currentTimeMillis();
		Long ca = (mid - start) / 1000;
		Log.i("jian", "当前：>>>>>>>>" + mid + "间隔>>>>>>>" + ca);
		double[] d = new Utils().compareBitmap(b1, width1, height1, b2, width2, height2);
		for (int i = 0; i < d.length; i++) {
			Log.i("TAG", i + 1 + ">>>>>>>>>>>>>>>>" + d[i]);
		}
		return d;
	}

	private byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		bmp.compress(CompressFormat.PNG, 100, output);
		if (needRecycle) {
			bmp.recycle();
		}

		byte[] result = output.toByteArray();
		try {
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}

	/**
	 * 
	 * 以最省内存的方式读取本地资源的图片
	 * 
	 * @param context
	 * 
	 * @param resId
	 * 
	 * @return
	 */

	public static Bitmap readBitMap(Context context, int resId) {

		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inTempStorage = new byte[8 * 1024 * 1024];
		// 获取资源图片
		InputStream is = context.getResources().openRawResource(resId);
		return BitmapFactory.decodeStream(is, null, opt);

	}

}
