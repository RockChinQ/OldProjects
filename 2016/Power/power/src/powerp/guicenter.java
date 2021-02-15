package powerp;

import javax.swing.JFrame;

public class guicenter extends JFrame{
	guicenter(int xl,int yl,int xz,int yz,boolean winv,boolean exwhcl,boolean winr,String title){
		this.setLocation(xl, yl);
		this.setSize(xz, yz);
		this.setVisible(winv);
		this.setResizable(winr);
		if(exwhcl)
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(title);
	}
}
