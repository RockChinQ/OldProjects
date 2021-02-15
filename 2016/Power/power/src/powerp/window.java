package powerp;

import javax.swing.JButton;
import javax.swing.JPanel;

public class window {
	static guicenter gc=new guicenter(200,200,700,530,true,true,false,"Power");
	static mainpanel mp=new mainpanel();
	static selectg sg=new selectg();
	static rwmsg rg=new rwmsg();
	static gamepanel gp=new gamepanel();
	static control[][] con=new control[50][50];
	window() throws Exception{
		gc.add(mp);
		gc.setVisible(true);
	}
}
