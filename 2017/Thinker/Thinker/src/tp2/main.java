package tp2;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class main {
	static window w=new window();
	public static void main(String[] args) {
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
			javax.swing.JOptionPane.showMessageDialog(null, "������ô��󣬽�ʹ��Ĭ�Ϸ�����������");
		}
		w.g.setVisible(true);
	}
	static void setStyle(){
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void setStyle2(){
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
