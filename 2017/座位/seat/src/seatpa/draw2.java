package seatpa;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.plaf.nimbus.State;

public class draw2 extends JPanel{
	class nnn{}
	static int w=editgui.l0*62+40;
	static int h=editgui.l1*50+40;
	static BufferedImage bi=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
	static Graphics2D g=bi.createGraphics();
	public void paint(Graphics g0){
		((Graphics2D) g).setStroke(new BasicStroke(2));
		g.setFont(new Font("",Font.PLAIN,13));
		g.setColor(new Color(Integer.parseInt(main.s[3]),Integer.parseInt(main.s[4]),Integer.parseInt(main.s[5])));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.DARK_GRAY);
		for(int a=0;a<editgui.l0;a++){
			for(int b=0;b<editgui.l1;b++){
				if(editgui.isseat[a][b]){
					g.drawString(editgui.jbt[a][b].getText(),a*60+12,b*40+75);
					g.drawRect(a*60+5,b*40+50,60,40);
				}
			} 
		}
		g0.drawImage(bi, 0, 0, this);
	}
}
