package p0;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

public class window {
	public static GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
	public static Rectangle rec=ge.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
	public static int xl=(int)rec.getWidth();
	public static int yl=(int)rec.getHeight();
	static guicenter g=new guicenter(xl/2-500,yl/2-400,1000,800,true,true,false,"Õ≥º∆÷˙ ÷");
	window() throws Exception{
		g.add(main.pa);
		Thread.sleep(2000);
	}
}
