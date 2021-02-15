package Pickaxe;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;

import javax.swing.ImageIcon;

public class MyTextArea extends TextArea {
	MyTextArea setSizex(int w,int h) {
		this.setSize(w,h);
		return this;
	}
	MyTextArea setLocationx(int x,int y) {
		this.setLocation(x, y);
		return this;
	}
	MyTextArea setTextx(String s) {
		this.setText(s);
		return this;
	}
	MyTextArea setFontx(Font font){
		this.setFont(font);
		return this;
	}
	MyTextArea setBackgroundx(Color c) {
		this.setBackground(c);
		return this;
	}
	MyTextArea setForegroundx(Color c) {
		this.setForeground(c);
		return this;
	}
}
