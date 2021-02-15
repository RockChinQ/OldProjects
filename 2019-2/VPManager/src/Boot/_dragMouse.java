package Boot;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class _dragMouse implements MouseListener, MouseMotionListener {
	static Point dragPoint=new Point(),dpodr=new Point();
	public void mouseClicked(MouseEvent arg0) {
	}
	public void mouseEntered(MouseEvent arg0) {
		Main.g.mf.setCursor(Cursor.MOVE_CURSOR);
	}
	public void mouseExited(MouseEvent arg0) {
		Main.g.mf.setCursor(Cursor.DEFAULT_CURSOR);
	}
	public void mousePressed(MouseEvent arg0) {
		Point p=arg0.getPoint();
		dragPoint.setLocation(p.getX()+Main.g.tlp.getX()
			,p.getY()+Main.g.tlp.getY());
		dpodr.setLocation(arg0.getPoint());
		
	}
	public void mouseReleased(MouseEvent arg0) {
	}
	public void mouseDragged(MouseEvent arg0) {
		int dx=(int) (arg0.getX()+Main.g.tlp.getX()-dragPoint.getX()),
				dy=(int) (arg0.getY()+Main.g.tlp.getY()-dragPoint.getY());
			Main.g.tlp.setLocation((int)dragPoint.getX()+dx-dpodr.x
					,(int)dragPoint.getY()+dy-dpodr.y);
	}
	public void mouseMoved(MouseEvent arg0) {
	}

}
