package cps;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class gui extends JFrame{
	Image img;
	gui(int x,int y,int lx,int ly,String s0,boolean b0/*可见性*/,boolean b1/*可改变大小*/,boolean b2/*关闭*/){
		this.setSize(x, y);
		this.setLocation(lx, ly);
		this.setTitle(s0);
		this.setVisible(b0);
		this.setResizable(b1);
		if(b2)
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		img=Toolkit.getDefaultToolkit().createImage("cps.jpg");
		this.setIconImage(img);
		this.requestFocus();
	}
}
