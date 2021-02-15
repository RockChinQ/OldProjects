package tp0;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class mouse2 implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		if(main.dr.start.isEnabled())
			main.dr.start.setText("开始命题=>");
	}
	public void mouseExited(MouseEvent arg0) {
		if(main.dr.start.isEnabled())
			main.dr.start.setText("开始");
	}
	public void mousePressed(MouseEvent arg0) {
		if(main.dr.start.isEnabled()){
			main.setStyle();
			main.mq=new tp1.makeque();
			main.dr.g.setVisible(false);
		}
	}
	public void mouseReleased(MouseEvent arg0) {
	}

}