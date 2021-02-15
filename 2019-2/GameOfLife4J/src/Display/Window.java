package Display;

import javax.swing.JFrame;

import Preference.Prefere;

public class Window {
	JFrame mainWd;
	public Prefere pp;
	public DrawImage di;
	public Window(){
		mainWd=new JFrame();
		mainWd.setLayout(null);
		mainWd.setSize(1050,520);
		mainWd.setTitle("GameOfLife");
		mainWd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pp=new Prefere();
		mainWd.add(pp);
		di=new DrawImage();
		di.cptSize();
		di.setLocation(400, 10);
		mainWd.add(di);
		
		mainWd.setVisible(true);
	}
}
