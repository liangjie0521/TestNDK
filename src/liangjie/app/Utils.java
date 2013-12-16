package liangjie.app;

public class Utils {

	/**
	 * 
	 * @param b1
	 *            图片1
	 * @param width1
	 *            图片1的宽
	 * @param height1
	 *            图片1的
	 * @param b2
	 *            图片2
	 * @param width2
	 *            图片2的宽
	 * @param height2
	 *            图片2高
	 * @return
	 */
	public native double[] compareBitmap(byte[] b1, int width1, int height1, byte[] b2, int width2, int height2);

	static {
		System.loadLibrary("Utils");
	}

}
