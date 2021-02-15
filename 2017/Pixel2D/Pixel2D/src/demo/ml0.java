package demo;

import Pixel2D.PixelMouseListener;
import Pixel2D.p2dMouseEvent;

public class ml0 implements PixelMouseListener {

	@Override
	public void mousePressed(p2dMouseEvent e) {
		// TODO Auto-generated method stub

		System.out.println("pressed");
	}

	@Override
	public void mouseReleased(p2dMouseEvent e) {
		// TODO Auto-generated method stub

		System.out.println("released");
	}

	@Override
	public void mouseClicked(p2dMouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("clicked");
	}

	@Override
	public void mouseEntered(p2dMouseEvent e) {
		// TODO Auto-generated method stub

		System.out.println("entered");
	}

	@Override
	public void mouseExited(p2dMouseEvent e) {
		// TODO Auto-generated method stub

		System.out.println("exited");
	}

}
