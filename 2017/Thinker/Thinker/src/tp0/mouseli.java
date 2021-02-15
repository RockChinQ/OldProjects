package tp0;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class mouseli implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		main.dr.x=arg0.getX();
		main.dr.y=arg0.getY();
		if(main.dr.a0!=-1){
			main.dr.xe=main.dr.a0;
			main.dr.ye=main.dr.b0;
		}else if(main.dr.mode==0&&main.dr.a0==-1){
			main.dr.a0=arg0.getX();
			main.dr.b0=arg0.getY();
		}
		main.dr.jp.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

}
