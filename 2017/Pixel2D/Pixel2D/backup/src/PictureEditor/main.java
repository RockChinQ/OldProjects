package PictureEditor;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class main {
	static window w;
	static painter p;
	public static void main(String[] args){
		// TODO Auto-generated method stub
		try {
			/*javax.swing.plaf.metal.MetalLookAndFeel
			javax.swing.plaf.nimbus.NimbusLookAndFeel
			com.sun.java.swing.plaf.motif.MotifLookAndFeel
			com.sun.java.swing.plaf.windows.WindowsLookAndFeel
			com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel
			 */
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			javax.swing.JOptionPane.showMessageDialog(null, "风格设置错误，将使用默认风格或重启程序！");
		}
		w=new window(); 
		p=new painter();
	}

}
