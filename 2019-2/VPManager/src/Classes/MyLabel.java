package Classes;

import java.awt.Color;
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
	public MyLabel setSizex(int width,int height) {
		this.setSize(width, height);
		return this;
	}
	public MyLabel setLocationx(int x,int y) {
		this.setLocation(x, y);
		return this;
	}
	public MyLabel setTextx(String s) {
		this.setText(s);
		return this;
	}
	public MyLabel setIconx(ImageIcon ii) {
		this.setIcon(ii);
		return this;
	}
	public MyLabel setFontx(Font font){
		this.setFont(font);
		return this;
	}
	public MyLabel setBackgroundx(Color cbg) {
		// TODO Auto-generated method stub
		this.setBackground(cbg);
		return null;
	}
}
