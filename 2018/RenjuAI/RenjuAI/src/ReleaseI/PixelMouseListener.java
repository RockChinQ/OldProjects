package ReleaseI;

import java.awt.event.MouseEvent;

public interface PixelMouseListener {
	void mousePressed(p2dMouseEvent e);
	void mouseReleased(p2dMouseEvent e);
	void mouseClicked(p2dMouseEvent e);
	void mouseEntered(p2dMouseEvent e);
	void mouseExited(p2dMouseEvent e);
	void mouseDragged(p2dMouseEvent e);
	void mouseMoved(p2dMouseEvent e);
}
