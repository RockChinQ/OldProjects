package Classes;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;

public class MyTextField extends JTextField{
	public MyTextField(){
		super();
	}
	public MyTextField setSizex(int width,int height) {
		this.setSize(width,height);
		return this;
	}
	public MyTextField setLocationx(int x,int y) {
		this.setLocation(x, y);
		return this;
	}
	public MyTextField setTextx(String s) {
		this.setText(s);
		return this;
	}
	public MyTextField setFontx(Font font){
		this.setFont(font);
		return this;
	}
	public MyTextField setBackgroundx(Color c) {
		this.setBackground(c);
		return this;
	}
	public MyTextField setForegroundx(Color c) {
		this.setForeground(c);
		return this;
	}
}
