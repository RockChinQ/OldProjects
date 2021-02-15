package Display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Main.*;
import javax.swing.JPanel;

public class DrawImage extends JPanel{
	DataExch dat=Main.dat;
	int lastGen=0;
	DrawImage(){
		this.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
				int x=(arg0.getX())/dat.cWidth,y=(arg0.getY())/dat.cHeight;
				dat.last[y][x]=dat.last[y][x]?false:true;;
				Main.w.di.repaint();
			}
			public void mouseEntered(MouseEvent arg0) {
			}
			public void mouseExited(MouseEvent arg0) {
			}
			public void mousePressed(MouseEvent arg0) {
			}
			public void mouseReleased(MouseEvent arg0) {
			}
		});
	}
	public void paint(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.white);
		for(int i=0;i<dat.pHeight;i++) {
			for(int j=0;j<dat.pWidth;j++) {
				if(dat.last[i][j])
					g.fillRect(j*dat.cWidth, i*dat.cHeight, dat.cWidth, dat.cHeight);
			}
		}
		g.setColor(Color.BLACK);
		g.fillRect(0, this.getHeight()-20, this.getWidth(), 20);
		g.setColor(Color.white);
		g.drawString("Generation:"+Main.cpti.generation, 5, this.getHeight()-8);
		int ge=Main.cpti.generation;
		double spd=((double)(ge-lastGen)/Main.dat.drawCycle);
		g.drawString("speed:"+spd+" gnr/s",120,this.getHeight()-8);
		lastGen=ge;
	}
	public void cptSize() {
		this.setSize(dat.cWidth*dat.pWidth,dat.cHeight*dat.pHeight+20);
	}
}
