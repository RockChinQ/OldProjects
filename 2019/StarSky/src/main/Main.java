package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame main=new JFrame("");
		main.setSize(1280, 720);
		main.setLayout(null);
		main.setLocation(200, 200);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Panel p=new Panel();
		p.setLocation(0, 0);
		p.setSize(main.getSize());
		
		p.count=200;
		p.radius.set(2, 10);
		p.red.set(190, 255);
		p.green.set(190, 255);
		p.blue.set(190, 255);
		main.add(p);
		
		main.setVisible(true);
	}

}
