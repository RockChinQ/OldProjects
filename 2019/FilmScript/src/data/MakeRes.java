package data;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MakeRes {
	public BufferedImage cut_left,cut_right;
	class img extends JPanel{
		public img() {
			this.setSize(15, 15);
			this.setBackground(null);
			this.setOpaque(false);
		}
		public void paint(Graphics g) {
		}
	}
	img imgDad=new img();
	//Color tranp=new Color(255,255,255,0);
	Color now=new Color(255,255,255);
	public MakeRes() {
		
	}
	public void  make() {
		 BufferedImage buffImg = new BufferedImage(imgDad.getWidth(), imgDad.getHeight()
				 , BufferedImage.TYPE_INT_RGB);  
         Graphics2D gd = buffImg.createGraphics();  
         //…Ë÷√Õ∏√˜  start  
         buffImg = gd.getDeviceConfiguration().createCompatibleImage(imgDad.getWidth()
        		 ,imgDad.getHeight(), Transparency.TRANSLUCENT);  
         gd=buffImg.createGraphics();  
		
         //cut_left
         cut_left=cloneImage(buffImg);
         now=Color.red;
         fillRect(cut_left,4,0,10,14);
         now=Color.white;
         fillRect(cut_left,4,2,6,12);
	}
	public void fillRect(BufferedImage img,int x,int y,int x1,int y1) {
		for(;x<=x1;x++) {
			for(;y<=y1;y++) {
				img.setRGB(x, y, now.getRGB());
			}
		}
	}
	public static BufferedImage cloneImage(BufferedImage image) {
		BufferedImage newImage = new BufferedImage(image.getWidth(),
        		image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = newImage.createGraphics();
		g.drawRenderedImage(image, null);
		g.dispose();
		return newImage;
	}
}
