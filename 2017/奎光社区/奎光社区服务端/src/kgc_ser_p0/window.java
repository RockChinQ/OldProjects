package kgc_ser_p0;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class window {
	static gui g=new gui(800,550,0,0,true,"KGCServer",true,true,true);
	static JLabel[] jl=new JLabel[25];
	JPanel jp=new JPanel();
	void showWindow(){
		jp.setLayout(null);
		for(int a=0;a<25;a++){
			main.use[a]=false;
			jl[a]=new JLabel(a+":нча╛╫с");
			jl[a].setSize(600, 15);
			jl[a].setLocation(20, a*20+10);
			jl[a].addMouseListener(new mouli());
			jp.add(jl[a]);
		}
		
		g.add(jp);
	}
	window(){
		showWindow();
	}
}
