package tp;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class key0 implements KeyListener {
	public void keyPressed(KeyEvent arg0) {
		if(arg0.isControlDown()){
			if(arg0.getKeyCode()==79){
				main.w.open();
			}else if(arg0.getKeyCode()==83){
				main.w.save();
			}else if(arg0.getKeyCode()==65){
				main.w.saveas();
			}
		}
	}
	public void keyReleased(KeyEvent arg0) {
	}
	public void keyTyped(KeyEvent arg0) {
	}

}
