package seatpa;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
public class gui extends JFrame{
	int i=0;
	static Image img;
	Thread t=new Thread();
	gui(int x,int y,int lx,int ly,boolean b,String til,boolean exb,boolean sizec){
		this.setTitle(til);
		this.setLocation(lx, ly);
		this.setSize(x, y);
		this.setVisible(b);
		img=Toolkit.getDefaultToolkit().createImage("file\\Í¼±ê.jpg");
		this.setIconImage(img);
		this.requestFocus();
		this.setResizable(sizec?true:false);
		if(exb){
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}
	public void setTitle2(String s){
		this.setTitle(s);
		/*
		try {
			t.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
	}
}
