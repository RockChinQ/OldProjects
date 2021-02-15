package modpe0;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class gui extends JFrame{
	int i=0;
	static Image img;
	gui(int x,int y,int lx,int ly,boolean b,String til,boolean exb){
		this.set(x,y,lx,ly,b,til, exb);
	}
	gui(){
		;
	}
	void set(int x,int y,int lx,int ly,boolean b,String til,boolean exb){
		this.setTitle(til);
		this.setLocation(lx, ly);
		this.setSize(x, y);
		this.setVisible(b);
		img=Toolkit.getDefaultToolkit().createImage("file\\Í¼±ê.jpg");
		this.setIconImage(img);
		this.requestFocus();
		if(exb){
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}
}
