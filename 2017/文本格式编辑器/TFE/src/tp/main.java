package tp;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class main {
	/*javax.swing.plaf.metal.MetalLookAndFeel
	javax.swing.plaf.nimbus.NimbusLookAndFeel
	com.sun.java.swing.plaf.motif.MotifLookAndFeel
	com.sun.java.swing.plaf.windows.WindowsLookAndFeel
	com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel
	 */
	static win w=new win();
	static StringBuffer file=new StringBuffer();
	public static void main(String[] args){
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(args.length>=1&&!args[0].equals(null)){
			file=new StringBuffer(args[0]);
			w.jta.setText(new file0().read(file.toString()));
		}
	}
}
