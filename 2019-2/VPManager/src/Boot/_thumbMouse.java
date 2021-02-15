package Boot;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class _thumbMouse implements MouseListener, MouseMotionListener {
	static Point dragPoint=new Point(),dpodr=new Point();
	static boolean drag=false;
	public void mouseDragged(MouseEvent arg0) {
		if(drag) {
			int dx=(int) (arg0.getX()+Main.g.tbp.getX()-dragPoint.getX()),
					dy=(int) (arg0.getY()+Main.g.tbp.getY()-dragPoint.getY());
			Main.g.tbp.setLocation((int)dragPoint.getX()+dx-dpodr.x
					,(int)dragPoint.getY()+dy-dpodr.y);
		}
	}
	public void mouseMoved(MouseEvent arg0) {}
	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {
		Main.g.mf.setCursor(Cursor.MOVE_CURSOR);
	}
	public void mouseExited(MouseEvent arg0) {
		Main.g.mf.setCursor(Cursor.DEFAULT_CURSOR);
	}
	public void mousePressed(MouseEvent arg0) {
		
		Point p=arg0.getPoint();
		dragPoint.setLocation(p.getX()+Main.g.tbp.getX()
			,p.getY()+Main.g.tbp.getY());
		dpodr.setLocation(arg0.getPoint());
		if((arg0.getX()>=0&&arg0.getX()<=50)
				&&(arg0.getY()>=0&&arg0.getY()<=10)) {
			drag=true;
		}
	}
	public void mouseReleased(MouseEvent arg0) {
		drag=false;
	}
}
