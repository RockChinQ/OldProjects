package tp0;

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
	int w;
	int h;
	BufferedImage bi;
	Graphics2D g;
	int mode=-1,x=0,y=0,xe=0,ye=0,la=0;
	StringBuffer str=new StringBuffer("");
	public draw2(int w,int h){
		this.w=w;
		this.h=h;
		bi=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
		g=bi.createGraphics();
		((Graphics2D) g).setStroke(new BasicStroke(2));
	}
	public void paint(Graphics g0){
		if(la==0){
			g.fillRect(0, 0, 450, 500);
			la=1;
		}
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 450, 470);
		g.setColor(new Color(118,118,118));
		g.fillRect(447, 0, 450, 500);
		g.fillRect(0, 0, 3, 500);
		g.fillRect(0,0, 500,3);
		g.fillRect(0,467,500,450);
		g.setColor(Color.black);
		((Graphics2D) g).setStroke(new BasicStroke(5));
		for(int a=0;a<Math.min(main.dr.xayc,500);a++){

			g.setFont(new Font("Serif",Font.PLAIN,17));
			g.drawLine(main.dr.xay[a][0],main.dr.xay[a][1],main.dr.xay[a][0],main.dr.xay[a][1]);
			g.drawString((char)main.dr.xay[a][2]+"", main.dr.xay[a][0]-2, main.dr.xay[a][1]-5);
			g.setFont(new Font("Serif",Font.PLAIN,10));
			if(main.dr.xay[a][4]!=4097)
				g.drawString(""+main.dr.xay[a][4], main.dr.xay[a][0]+10, main.dr.xay[a][1]-5);
		}
		((Graphics2D) g).setStroke(new BasicStroke(2));
		for(int a=0;a<main.dr.papbc;a++){
			int x0=-1,x1=-1,y0=-1,y1=-1;
			//System.out.println("a="+a);
			for(int b=0;b<main.dr.xayc;b++){
				if(main.dr.xay[b][2]==main.dr.papb[a][0]&&main.dr.xay[b][4]==main.dr.papb[a][3]){
					x0=main.dr.xay[b][0];
					y0=main.dr.xay[b][1];
				}else if(main.dr.xay[b][2]==main.dr.papb[a][1]&&main.dr.xay[b][4]==main.dr.papb[a][4]){
					x1=main.dr.xay[b][0];
					y1=main.dr.xay[b][1];
				}
				if(x0!=-1&&x1!=-1){
					g.drawLine(x0, y0, x1, y1);
					break;
				}
			}
		}
		g0.drawImage(bi, 0, 0, this);
	}
}
