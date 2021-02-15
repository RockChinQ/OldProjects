package Pickaxe;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class MenuPanel extends JPanel{
	Color bg=new Color(25,25,25);
	Font bFont=new Font("",Font.PLAIN,15);
	
	JMenuBar all;
	JMenu file,edit,setting;
	MenuPanel(){
		this.setSize(500, 30);
		this.setLayout(null);
		this.setBackground(bg);
		
		all=new JMenuBar();
		all.setSize(this.getWidth(),this.getHeight());
		all.setLocation(0,0);
		all.setLayout(null);

		file=new JMenu();
		file.setSize(70, 30);
		file.setLocation(0,0);
		file.setText("ÎÄ¼þ");
		all.add(file);
		
		edit=new JMenu();
		edit.setSize(70, 30);
		edit.setLocation(70,0);
		edit.setText("±à¼­");
		all.add(edit);
		
	}
}
