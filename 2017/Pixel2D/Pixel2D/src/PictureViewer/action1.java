package PictureViewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class action1 implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		int x=main.w.p2d.getX(),y=main.w.p2d.getY();
		if(arg0.getSource()==main.w.up){
			main.w.p2d.setLocation(x, y+20);
		}else if(arg0.getSource()==main.w.down){
			main.w.p2d.setLocation(x, y-20);
		}else if(arg0.getSource()==main.w.left){
			main.w.p2d.setLocation(x+20, y);
		}else if(arg0.getSource()==main.w.right){
			main.w.p2d.setLocation(x-20, y);
		}else if(arg0.getSource()==main.w.line){
			main.w.p2d.showGridLine=!main.w.p2d.showGridLine;
			main.w.p2d.repaint();
		}
	}

}
