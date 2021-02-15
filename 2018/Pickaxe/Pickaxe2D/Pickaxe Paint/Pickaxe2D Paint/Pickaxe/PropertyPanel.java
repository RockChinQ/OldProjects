package Pickaxe;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

public class PropertyPanel extends JPanel{
	Color bg=new Color(25,25,25);
	Font bFont=new Font("",Font.PLAIN,15);
	
	
	PropertyPanel(){
		//Ãæ°å²ÎÊý
		this.setBackground(bg);
		this.setLayout(null);
		this.setSize(430,140);
	}
}
