package demo;

import java.awt.Color;
import java.util.Date;

import javax.swing.JFrame;

import Pickaxe2D.Pick2DPanel;
import Pickaxe2D.Pickaxe2DManager;

public class window {
	JFrame jf0=new JFrame();
	Pickaxe2DManager p2dm=new Pickaxe2DManager();
	Pick2DPanel panel;
	window(){
		this.jf0.setSize(400, 400);
		this.jf0.setLayout(null);
		this.jf0.setLocation(100, 100);
		this.jf0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		long start=new Date().getTime();
		int n=p2dm.addPanel(9, 9, 30,30, new Color(255,255,255,100));
		panel=Pickaxe2DManager.panel.get(n);
		//System.out.println(new Date().getTime()-start);
		this.jf0.add(panel);
		panel.addLayer();
		//panel.layer.get(0).fillCircle(0, 0, 10, Color.DARK_GRAY, false);
		panel.layer.get(0).addShapes("rect 1 9 2 9 1 1 1 true;line 3 9 5 9 1 1 1;line 3 13 5 13 1 1 1;rect 5 10 2 3 1 1 1 true;".split(";"));
		panel.setIfDrawLine(true);
		jf0.setVisible(true);
		//System.exit(0);
	}
}
