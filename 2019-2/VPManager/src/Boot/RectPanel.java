package Boot;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class RectPanel extends JPanel{
	public void paint(Graphics g) {
		/*
		g.setColor(new Color(26,56,200));
		g.fillRect(0, 0, this.getWidth(),this.getHeight());
		*/
		g.setColor(new Color(26,56,200,100));
		g.fillRect(2,2, this.getWidth()-4,this.getHeight()-4);
	}
}
