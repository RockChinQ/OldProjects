package Boot;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Classes.FileRW;

public class Main {
	public static GUI g;
	public static String fileName="";
	public static boolean change=false;
	public static String ver="Build19.02.19";
	public static void main(String[] args) {
		/*javax.swing.plaf.metal.MetalLookAndFeel
		javax.swing.plaf.nimbus.NimbusLookAndFeel
		com.sun.java.swing.plaf.motif.MotifLookAndFeel
		com.sun.java.swing.plaf.windows.WindowsLookAndFeel
		com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel
		 */
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
			| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		g=new GUI();
	}

}
