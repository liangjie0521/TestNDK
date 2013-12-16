package liangjie.app;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
	private Bitmap bm1, bm2;
	private Utils utils;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		utils = new Utils();

		int img[] = { R.raw.img1, R.raw.img2, R.raw.img3, R.raw.img4, R.raw.img5 };
		int img1[] = { R.raw.fourth, R.raw.third, R.raw.first, R.raw.fifth, R.raw.second };
		for (int i = 0; i < img1.length; i++) {
			bm1 = readBitMap(this, img1[i]);
			List<Double> list = new ArrayList<Double>();
			for (int j = 0; j < img.length; j++) {	
				bm2 = readBitMap(this, img[j]);
				Long start = System.currentTimeMillis();
				Log.i("Time1", "" + start);
				double d = compareByInt(bm1, bm2);
				Log.i("TAG", "It takes:" + (System.currentTimeMillis() - start));
				list.add(d);
			}
			int index = 0;
			for (int k = 1; k < list.size(); k++) {
				if (list.get(k) >= list.get(index)) {
					index = k;
				}
			}
			Log.i("db>>>>>", "" + list);
			Log.i("PAGE", "is same with:img>>>>>>>>" + (index + 1));
		}

	}

	private double compareByInt(Bitmap bm1, Bitmap bm2) {
		int[] firstImgPixels = {}, secondImgPixels = {};
		int firstW = 0, firstH = 0, secondW = 0, secondH = 0;
		firstW = bm1.getWidth();
		firstH = bm1.getHeight();
		firstImgPixels = new int[firstW * firstH];
		bm1.getPixels(firstImgPixels, 0, firstW, 0, 0, firstW, firstH);
		secondW = bm2.getWidth();
		secondH = bm2.getHeight();
		secondImgPixels = new int[secondW * secondH];
		bm2.getPixels(secondImgPixels, 0, secondW, 0, 0, secondW, secondH);
		double d = utils.compareintBitmap(firstImgPixels, firstW, firstH, secondImgPixels, secondW, secondH);
		return d;
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
