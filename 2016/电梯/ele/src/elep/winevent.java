package elep;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class winevent implements WindowListener {
	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	public void windowClosing(WindowEvent arg0) {
		hardware.g2.setVisible(false);
		int i=javax.swing.JOptionPane.showConfirmDialog(null, "你关闭了 电梯动画模拟窗口  \n              程序被暂停  \n打开(确定)退出程序(取消)", "程序暂停",2);
		//System.out.println(i);
		if(i==0) 
			hardware.g2.setVisible(true);
		else
			System.exit(0);
	}
	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}
}
