package demo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Date;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JWindow;

import Pixel2D.*;

public class window {
	JFrame jf=new JFrame();
	Pixel2D p2d=new Pixel2D(7,7,1000,1000,Color.GRAY,null,false,Color.black);
	window(){
		jf.setSize(1000, 650);
		jf.setLocation(50,30);
		jf.setTitle("Pixel2D(Pixel2D展示程序)");
		jf.setLayout(null);
		//jf.setAlwaysOnTop(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//测试数据
		
		//
		for(int a=0;a<00;a++){
			int w=(int)(Math.random()*1000)%200,x=(int)(Math.random()*1000)%200,y=(int)(Math.random()*1000)%200,z=(int)(Math.random()*1000)%200;
			p2d.drawRect(Math.min(w, x),Math.min(y, z),Math.max(w, x) ,Math.max(y, z),new Color((int)(Math.random()*1000)%255,(int)(Math.random()*1000)%255,(int)(Math.random()*1000)%255),true);
		}
		//p2d.drawLine(1,10, 10, 1, Color.black);
		//p2d.drawByCode(7,7,Color.gray,new file0().read("C:\\Users\\asus\\Desktop\\cat2.p2d"));
		//p2d.drawPixelImage(7, 7, Color.GRAY, new PixelIcon(new PixelImage("C:\\Users\\asus\\Desktop\\cat2.p2d")).getIconCode(10));
		
		//Component testing
		
		//p2d.drawString(5,53, "Rock\nHere!", Color.BLACK, 1, "chars-7.txt");
		PButton pb1=new PButton("Start"/*,new PixelIcon(new PixelImage("C:\\Users\\asus\\Desktop\\p2d\\start.p2d"))*/);
		
		pb1.setSize(50, 10);
		pb1.setLocation(5, 40);
		pb1.background=new Color(128,64,0);
		pb1.setFontColor(Color.WHITE);
		pb1.addMouseListener(new ml0());

		PLabel pl1=new PLabel("Rock Chin！"/*,new PixelIcon(new PixelImage("C:\\Users\\asus\\Desktop\\p2d\\cat2.p2d"))*/);
		pl1.setSize(90, 10);
		pl1.setLocation(5,20);
		pl1.showBorderLine=true;

		PTextField ptf1=new PTextField("Field");
		ptf1.setSize(90, 10);
		ptf1.setLocation(5,60);
		
		PTextField ptf2=new PTextField("Field");
		ptf2.setSize(90, 10);
		ptf2.setLocation(5,75);
		
		PButton top=new PButton("Top?");
		top.setSize(22, 8);
		top.setLocation(10, 10);
		top.addMouseListener(new PixelMouseListener(){
			public void mousePressed(p2dMouseEvent e) {
			}
			public void mouseReleased(p2dMouseEvent e) {
			}
			public void mouseClicked(p2dMouseEvent e) {
				System.out.println("setted:"+jf.isAlwaysOnTop());
				jf.setAlwaysOnTop(!jf.isAlwaysOnTop());
			}
			public void mouseEntered(p2dMouseEvent e) {
			}
			public void mouseExited(p2dMouseEvent e) {
			}
		});
		
		p2d.drawLine(0, 0, 10, 10, Color.BLACK);
		p2d.addPixelComponent(top);
		
		p2d.addPixelComponent(pb1);
		p2d.addPixelComponent(pl1);
		p2d.addPixelComponent(ptf1);
		p2d.addPixelComponent(ptf2);
		p2d.updatePixelComponent();
		p2d.requestFocus();
		p2d.requestFocus();
		p2d.requestFocus();
		p2d.requestFocus();
		//p2d.drawString(90, 90, "interface life{\n    void future(){};\n}", Color.BLACK, 1, "chars-7.txt");
		
		//p2d.drawRect(0, 0, 1000, 1000, null, true);
		jf.add(p2d);

		jf.setVisible(true);
		jf.setAlwaysOnTop(true);
		/*
		Timer t=new Timer();
		t.schedule(new paint(), new Date(),1);*/
	}
}
