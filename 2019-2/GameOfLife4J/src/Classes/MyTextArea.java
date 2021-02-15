package Classes;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;

import javax.swing.ImageIcon;

public class MyTextArea extends TextArea {
	public MyTextArea setSizex(int w,int h) {
		this.setSize(w,h);
		return this;
	}
	public MyTextArea setLocationx(int x,int y) {
		this.setLocation(x, y);
		return this;
	}
	public MyTextArea setTextx(String s) {
		this.setText(s);
		return this;
	}
	public MyTextArea setFontx(Font font){
		this.setFont(font);
		return this;
	}
	public MyTextArea setBackgroundx(Color c) {
		this.setBackground(c);
		return this;
	}
	public MyTextArea setForegroundx(Color c) {
		this.setForeground(c);
		return this;
	}
}
