package ReleaseI;

public class PixelSystemException extends Exception {
	public static int errc=0;
	public PixelSystemException(String e){
		System.out.println(e);
		errc++;
	}
}
