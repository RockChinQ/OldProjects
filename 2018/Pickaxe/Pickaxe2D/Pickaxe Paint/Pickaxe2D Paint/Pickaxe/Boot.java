package Pickaxe;

import java.util.Date;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Boot {
	static GUIManager guim;
	static WelcomeWindow ww;
	static String filePath="";
	static String file=new String();
	static long bootTime=new Date().getTime();
	public static void main(String[] args) {
		System.out.print("Booting...");
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
		ww=new WelcomeWindow();
		guim=new GUIManager();
	}

}
