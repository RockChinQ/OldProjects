package Pickaxe;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class SelectFilePanel extends JPanel{
	Color bg=new Color(25,25,25);
	Font bFont=new Font("",Font.PLAIN,15);
	MyButton newFile,openFile;
	SelectFilePanel(){
		this.setSize(300,140);
		this.setLayout(null);
		this.setBackground(bg);
		newFile=new MyButton(new ImageIcon(Boot.filePath+"newFile.png"));
		newFile.setSizex(92, 92).setLocation(40,25);
		newFile.addActionListener(new _selectFileAction());
		
		openFile=new MyButton(new ImageIcon(Boot.filePath+"openFile.png"));
		openFile.setSizex(92, 92).setLocation(170,25);
		openFile.addActionListener(new _selectFileAction());
		
		this.add(newFile);
		this.add(openFile);
	}
}
