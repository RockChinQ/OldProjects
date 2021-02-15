package zwbp;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

public class mouse0 implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource().equals(main.win.clo)){
			main.win.close();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		if(arg0.getSource().equals(main.win.clo)){
			main.win.clo.setIcon(new ImageIcon(main.sou+"close1.jpg"));
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		if(arg0.getSource().equals(main.win.clo)){
			main.win.clo.setIcon(new ImageIcon(main.sou+"close0.jpg"));
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
