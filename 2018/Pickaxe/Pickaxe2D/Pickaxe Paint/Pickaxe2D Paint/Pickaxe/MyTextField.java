package Pickaxe;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;

public class MyTextField extends JTextField{
	public MyTextField(){
		super();
	}
	MyTextField setSizex(int width,int height) {
		this.setSize(width,height);
		return this;
	}
	MyTextField setLocationx(int x,int y) {
		this.setLocation(x, y);
		return this;
	}
	MyTextField setTextx(String s) {
		this.setText(s);
		return this;
	}
	MyTextField setFontx(Font font){
		this.setFont(font);
		return this;
	}
	MyTextField setBackgroundx(Color c) {
		this.setBackground(c);
		return this;
	}
	MyTextField setForegroundx(Color c) {
		this.setForeground(c);
		return this;
	}
}
