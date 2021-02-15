package p0;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.tools.JavaCompiler;

public class mlm implements MouseListener {
	public void mouseClicked(MouseEvent arg0) {
		for(int i=0;i<firpan.xi;){
			if(arg0.getSource().equals(firpan.ll[i])){
				new open().openfile(firpan.ll[i].getText());
			}
		}
	}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
}
