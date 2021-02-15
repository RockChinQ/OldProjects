package tp0;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class main {
	public static drawer dr=new drawer();
	static String cou="";
	public static tp1.makeque mq;
	public static void main(String[] args) {
		try {
			/*javax.swing.plaf.metal.MetalLookAndFeel
			javax.swing.plaf.nimbus.NimbusLookAndFeel
			com.sun.java.swing.plaf.motif.MotifLookAndFeel
			com.sun.java.swing.plaf.windows.WindowsLookAndFeel
			com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel
			 */
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			javax.swing.JOptionPane.showMessageDialog(null, "风格设置错误，将使用默认风格或重启程序！");
		}
		dr.g.setVisible(false);
		new welcome();
		//javax.swing.JOptionPane.showMessageDialog(null, "欢迎使用！");
		// TODO Auto-generated method stub
		dr.g.setVisible(true);
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
	public static void setStyle2(){
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
