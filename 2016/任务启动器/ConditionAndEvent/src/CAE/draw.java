package CAE;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class draw extends JPanel{
	Image img;
	draw(String s){
		img=Toolkit.getDefaultToolkit().createImage(s);
	}
	public void paint(Graphics g){
		g.drawImage(img,0,0,this);
		this.requestFocus();
		this.setFocusable(true);
	}
}
