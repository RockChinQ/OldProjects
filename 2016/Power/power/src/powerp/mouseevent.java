package powerp;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.ImageIcon;

public class mouseevent implements MouseListener{
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==mainpanel.jl){
			mainpanel.jl.setIcon(new ImageIcon(main.sou+"main"+(new Random().nextInt(7)+1)+".png"));
		}
		if(e.getSource()==mainpanel.jbt0){
			window.gc.remove(window.mp);
			window.gc.add(window.sg);
			window.gc.setVisible(false);
			window.gc.setVisible(true);
		}
		if(e.getSource()==window.sg.bt){
			window.gc.remove(window.sg);
			window.gc.add(window.mp);
			window.gc.setVisible(false);
			window.gc.setVisible(true);
		}
	}
	public void mouseEntered(MouseEvent e) {
		if(e.getSource()==mainpanel.jbt0){
			mainpanel.jbt0.setIcon(new ImageIcon(main.sou+"rwms2.png"));
		}
		if(e.getSource()==mainpanel.jbt1){
			mainpanel.jbt1.setIcon(new ImageIcon(main.sou+"zyms2.png"));
		}
		if(e.getSource()==mainpanel.jbt2){
			mainpanel.jbt2.setIcon(new ImageIcon(main.sou+"yxsz2.png"));
		}
		if(e.getSource()==mainpanel.jbt3){
			mainpanel.jbt3.setIcon(new ImageIcon(main.sou+"tcyx2.png"));
		}
	}
	public void mouseExited(MouseEvent e) {
		if(e.getSource()==mainpanel.jbt0){
			mainpanel.jbt0.setIcon(new ImageIcon(main.sou+"rwms.png"));
		}
		if(e.getSource()==mainpanel.jbt1){
			mainpanel.jbt1.setIcon(new ImageIcon(main.sou+"zyms.png"));
		}
		if(e.getSource()==mainpanel.jbt2){
			mainpanel.jbt2.setIcon(new ImageIcon(main.sou+"yxsz.png"));
		}
		if(e.getSource()==mainpanel.jbt3){
			mainpanel.jbt3.setIcon(new ImageIcon(main.sou+"tcyx.png"));
		}
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
}
