package drawerp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class draw2 extends JPanel{
	static int w=500;
	static int h=450;
	static BufferedImage bi=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
	static Graphics2D g=bi.createGraphics();
	public void paint(Graphics g0){
		((Graphics2D) g).setStroke(new BasicStroke(2));
		g.setColor(Color.BLACK);
		
		g0.drawImage(bi, 0, 0, this);
	}
}
