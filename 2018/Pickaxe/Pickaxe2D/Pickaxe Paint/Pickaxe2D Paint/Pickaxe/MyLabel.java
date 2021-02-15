package Pickaxe;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MyLabel extends JLabel {
	public MyLabel(String str) {
		super(str);
	}
	public MyLabel(ImageIcon ii) {
		super(ii);
	}
	public MyLabel() {
		super();
	}
	MyLabel setSizex(int width,int height) {
		this.setSize(width, height);
		return this;
	}
	MyLabel setLocationx(int x,int y) {
		this.setLocation(x, y);
		return this;
	}
	MyLabel setTextx(String s) {
		this.setText(s);
		return this;
	}
	MyLabel setIconx(ImageIcon ii) {
		this.setIcon(ii);
		return this;
	}
	MyLabel setFontx(Font font){
		this.setFont(font);
		return this;
	}
}
