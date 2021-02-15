package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Panel extends JPanel{
	public Range radius=new Range(0,5),red=new Range(0,255),green=new Range(0,255),blue=new Range(0,255),alpha=new Range(0,255);
	public int count=30;
	public class Range{
		public double min=0,max=0;
		public Range(double min,double max) {
			this.min=min;
			this.max=max;
		}
		public void set(double min,double max) {
			this.min=min;
			this.max=max;
		}
	}
	public void range(Range radius,Range red,Range green,Range blue,Range alpha) {
		this.radius=radius;
		this.red=red;
		this.green=green;
		this.blue=blue;
		this.alpha=alpha;
	}
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		for(int i=0;i<count;i++) {
			g.setColor(new Color(rand(red),rand(green),rand(blue),rand(alpha)));
			int radius=rand(this.radius)*2;
			g.fillOval(rand(new Range(0,this.getWidth())), rand(new Range(0,this.getHeight())), radius,radius);
		}
	}
	public int rand(Range rg) {
		return (int) ((Math.random()*10000)%(rg.max-rg.min)+rg.min);
	}
}
