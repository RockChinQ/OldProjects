package Pixel2D;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JWindow;

public class window {
	JFrame jf=new JFrame();
	Pixel2D p2d=new Pixel2D(3,3,400,250,Color.white,false,Color.gray,true,null);
	window(){
		jf.setSize(1220, 800);
		jf.setLocation(50,30);
		jf.setTitle("Pixel2D(Pixel2D展示程序)");
		jf.setLayout(null);
		jf.setAlwaysOnTop(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//测试数据
		
		JButton bt=new JButton("haha");
		bt.setFont(new Font(" ",Font.BOLD,2));
		bt.setSize(100,20);
		bt.setLocation(50, 50);
		bt.setBackground(Color.blue);
		//p2d.addButton(bt);
		//
		for(int a=0;a<00;a++){
			int w=(int)(Math.random()*1000)%200,x=(int)(Math.random()*1000)%200,y=(int)(Math.random()*1000)%200,z=(int)(Math.random()*1000)%200;
			p2d.drawRect(Math.min(w, x),Math.min(y, z),Math.max(w, x) ,Math.max(y, z),new Color((int)(Math.random()*1000)%255,(int)(Math.random()*1000)%255,(int)(Math.random()*1000)%255),true);
		}
		p2d.drawCircle(10, 10, 50, Color.BLUE,false);
		p2d.drawString(0, 0, "{", Color.DARK_GRAY, 3, "output.txt");
		jf.add(p2d);

		jf.setVisible(true);
	}
}
