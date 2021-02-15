package CAE;

import javax.swing.JFrame;

public class test {
	JFrame jf=new JFrame("rock");
	draw2 d2=new draw2();
	test(){
		jf.setVisible(true);
		jf.setLocation(0, 0);
		jf.setSize(500,800);
		jf.add(d2);
	}
}
