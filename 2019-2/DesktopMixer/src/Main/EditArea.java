package Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class EditArea extends JPanel {
	public GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
	public Rectangle rec=ge.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
	public int sw=(int)rec.getWidth();
	public int sh=(int)rec.getHeight();
	
	class Picture{
		Image img;
		Point loca;
		int width,height;    //注意这里的坐标及大小均是在整个屏幕中的数据
		Picture(Image img){
			this.img=img;
			this.loca=new Point(0,0);
			this.width=new ImageIcon(img).getIconWidth();  //this.img.getWidth(null);
			this.height=new ImageIcon(img).getIconHeight();  //this.img.getHeight(null);
			System.out.println(img.toString()+" "+width+" "+height);
		}
	}
	ArrayList<Picture> pics=new ArrayList<Picture>();
	Color bg=new Color(100,100,100);
	EditArea(int w,int h){
		this.setSize(w,h);
		this.addMouseListener(new _editMouse());
		this.addMouseMotionListener(new _editMouse());
	}
	public void paint(Graphics g) {
		g.setColor(bg);
		g.fillRect(0, 0,this.getWidth(),this.getHeight());
		
		for(int i=0;i<pics.size();i++) {
			//System.out.println(i);
			Picture pic=pics.get(i);
			//Image img=pic.img.getScaledInstance(pic.width/3, pic.height/3, Image.SCALE_DEFAULT);
			g.drawImage(pic.img,pic.loca.x,pic.loca.y,null, this); //
		}
		
		g.setColor(Color.white);
		g.drawRect(1, 1, this.getWidth()-3, this.getHeight()-3);
	}
	void loadAll() {
		File[] imgs=new File("imgs").listFiles();
		for(int i=0;i<imgs.length;i++) {
			pics.add(new Picture(Toolkit.getDefaultToolkit().createImage
					(imgs[i].getAbsolutePath())));
		}
	}
}
