package liangjie.app;


public class Utils {

	/**
	 * @param originImgPixels
	 * @param originW
	 * @param originH
	 * @param targetImgPixels
	 * @param targetW
	 * @param targetH
	 * @return
	 */
	public native double compareintBitmap(int[] firstImgPixels, int firstW, int firstH, int[] secondImgPixels,
			int secondW, int secondH);

//	/**
//	 * 
//	 * @param b1
//	 *            图片1
//	 * @param width1
//	 *            图片1的宽
//	 * @param height1
//	 *            图片1的
//	 * @param b2
//	 *            图片2
//	 * @param width2
//	 *            图片2的宽
//	 * @param height2
//	 *            图片2高
//	 * @return
//	 */
//	public native double[] compareBitmap(byte[] b1, int width1, int height1, byte[] b2, int width2, int height2);

	static {
		System.loadLibrary("Utils");
	}

}
