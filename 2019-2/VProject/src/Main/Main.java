package Main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import GUI.GUIManager;

public class Main {
	public static GUIManager gui;
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
		gui=new GUIManager();
	}

}
