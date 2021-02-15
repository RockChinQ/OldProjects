package guiMgr;

import java.awt.Point;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JPanel;

import boot.Main;

public class DialogListner implements WindowListener {
	FloatPanel jp;
	Point p;

	public DialogListner(FloatPanel jp,Point locInMwd) {
		// TODO Auto-generated constructor stub
		this.jp=jp;
		this.p=locInMwd;
	}
	public void windowActivated(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {}
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		jp.setLocation(p);
		Main.gui.bgp.remove(Main.gui.bgp.ep);
		Main.gui.bgp.add(jp);
		Main.gui.bgp.add(Main.gui.bgp.ep);
		Main.gui.bgp.repaint();
		jp.wded=false;
	}
	public void windowDeactivated(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowOpened(WindowEvent e) {}

}
