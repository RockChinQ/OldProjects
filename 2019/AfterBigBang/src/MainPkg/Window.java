package MainPkg;

import java.util.ArrayList;

import javax.swing.JFrame;

public class Window {
	//pgmData
	ArrayList<Dust> dusts=new ArrayList<Dust>();
	
	//
	JFrame main;
	Panel p;
	Window(){
		main=new JFrame("AfterBigBang");
		main.setSize(500, 500);
		main.setLocation(200, 50);
		main.setVisible(true);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		p=new Panel();
		main.add(p);
	}
}
