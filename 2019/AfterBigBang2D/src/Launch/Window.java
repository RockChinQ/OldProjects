package Launch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Window {
	//
	JFrame main;
	Panel p;
	JButton pause;
	boolean run=false;
	Window(){
		main=new JFrame("AfterBigBang");
		main.setSize(500, 500);
		main.setLocation(200, 50);
		main.setVisible(true);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setLayout(null);
		
		pause=new JButton("false");
		pause.setSize(50,30);
		pause.setLocation(10, Main.H*3+10);
		main.add(pause);
		pause.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				run=!run;
				pause.setText(run+"");
			}
			
		});
		
		p=new Panel();
		p.setSize(Main.W*3, Main.H*3);
		p.setLocation(0, 0);
		main.add(p);
	}
}
