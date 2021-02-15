package Pickaxe;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class _editingMouse implements MouseMotionListener, MouseListener {
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		String t=Boot.guim.toolp.toolNow.toString();
		int x=arg0.getX()/EditingManager.main.data.getGridWidth()
				,y=arg0.getY()/EditingManager.main.data.getGridHeight();
		if(t.equals("point")) {
			
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
