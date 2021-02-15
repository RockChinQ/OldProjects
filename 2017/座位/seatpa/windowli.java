package seatpa;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class windowli implements WindowListener {
	public void windowActivated(WindowEvent arg0) {
	}
	public void windowClosed(WindowEvent arg0) {
	}
	public void windowClosing(WindowEvent arg0) {
		if(arg0.getSource()==addf.g){
			main.label=0;
			addf.g.dispose();
		}
	}
	public void windowDeactivated(WindowEvent arg0) {
	}

	public void windowDeiconified(WindowEvent arg0) {
	}

	public void windowIconified(WindowEvent arg0) {

	}

	public void windowOpened(WindowEvent arg0) {
	}

}
