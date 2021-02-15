package CAE;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class gui extends JFrame{
	int i=0;
	Image img;
	gui(int x,int y,int lx,int ly,boolean b,String til,boolean exb){
		this.setTitle(til);
		this.setLocation(lx, ly);
		this.setSize(x, y);
		this.setVisible(b);
		img=Toolkit.getDefaultToolkit().createImage("ÈÎÎñÆô¶¯Æ÷.jpg");
		this.setIconImage(img);
		this.requestFocus();
		if(exb){
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}
}
