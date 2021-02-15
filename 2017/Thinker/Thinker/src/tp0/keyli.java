package tp0;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyli implements KeyListener {

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		int keyChar=arg0.getKeyChar();
		if (keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9) {
			
		} else {
			arg0.consume();  
		}
		if(keyChar!=8&&keyChar!=10){
			if((arg0.getSource()==main.dr.jtf0&&Integer.parseInt(main.dr.jtf0.getText()+(keyChar-48))>450)||(arg0.getSource()==main.dr.jtf1&&Integer.parseInt(main.dr.jtf1.getText()+(keyChar-48))>470)){
				arg0.consume();
			}
		}
	}

}