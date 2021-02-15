package seatpa;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class keylis implements KeyListener {
	class nnn{}
	public void keyPressed(KeyEvent arg0) {}
	public void keyReleased(KeyEvent arg0) {}
	public void keyTyped(KeyEvent arg0) {
		if(arg0.getKeyChar()=='n'||arg0.getKeyChar()=='N'){
			window.g.dispose();
			//try {new Thread().sleep(200);} catch (InterruptedException e) {}
			new addf();
			main.label=1;
		}
		if(arg0.getKeyChar()=='o'||arg0.getKeyChar()=='O'){
			window.g.dispose();
			new open();
			addf.g.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			addf.g.dispose();
		}
	}

}
