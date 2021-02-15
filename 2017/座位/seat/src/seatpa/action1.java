package seatpa;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

public class action1 implements ActionListener {
	public void actionPerformed(ActionEvent arg0) {
		for(int a=0;a<20;a++){
			if(arg0.getSource()==setting.color[a]){
				setting.color[setting.col].setBackground(Color.white);
				setting.col=a;
				setting.color[a].setBackground(Color.yellow);
				setting.choise.setIcon(new ImageIcon(main.sou+a+".jpg"));
			}
		}
	}

}
