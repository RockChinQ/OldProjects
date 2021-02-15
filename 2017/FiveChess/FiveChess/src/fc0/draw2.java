package fc0;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class draw2 extends JPanel{
	int w;
	int h;
	BufferedImage bi;
	Graphics2D g;
	int mode=-1,x=0,y=0,xe=0,ye=0,la=0;
	StringBuffer str=new StringBuffer("");
	Color bg=new Color(245,245,245);
	Image title=Toolkit.getDefaultToolkit().createImage(main.cou+"title.png");
	draw2(int w,int h){
		this.w=w;
		this.h=h;
		bi=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
		g=bi.createGraphics();
		((Graphics2D) g).setStroke(new BasicStroke(2));
	}
	public void paint(Graphics g0){
		g.setColor(bg);
		g.fillRect(0, 0, 550, 500);
		Thread thr=new Thread();
		try{
			for(int a=0;a<12;a++){
				g.setColor(new Color((int)((Math.random()*1000)%256),(int)((Math.random()*1000)%256),(int)((Math.random()*1000)%256)));
				g.drawLine(20,a*40+20,460,a*40+20);
				//thr.sleep(200);
			}
			for(int a=0;a<12;a++){
				g.setColor(new Color((int)((Math.random()*1000)%256),(int)((Math.random()*1000)%256),(int)((Math.random()*1000)%256)));
				g.drawLine(a*40+20,20,a*40+20,460);
				//thr.sleep(200);
			}
			if(main.ga.state==0){
				g.setColor(bg);
				g.fillRect(0, 0, 550, 500);
				g.drawImage(title,-10, 0, this);
			}
		}catch(Exception e){
			
		}
		g0.drawImage(bi, 0, 0, this);
	}
}
