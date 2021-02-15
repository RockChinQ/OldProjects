package Classes;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MyButton extends JButton{
	StringBuffer iconPath;
	public MyButton(String string) {
		// TODO Auto-generated constructor stub
		super(string);
	}
	public MyButton(ImageIcon ii) {
		super(ii);
	}
	public MyButton() {
		super();
	}
	public MyButton setSizex(int width,int height) {
		this.setSize(width, height);
		return this;
	}
	public MyButton setLocationx(int x,int y) {
		this.setLocation(x, y);
		return this;
	}
	public MyButton setTextx(String s) {
		this.setText(s);
		return this;
	}
	public MyButton setIconx(ImageIcon ii) {
		this.setIcon(ii);
		return this;
	}
	public MyButton setFontx(Font font){
		this.setFont(font);
		return this;
	}
	public MyButton setBackgroundx(Color bg) {
		this.setBackground(bg);
		return this;
	}
}
