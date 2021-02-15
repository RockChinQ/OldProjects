package Pickaxe;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

public class CommandPanel extends JPanel{
	MyTextArea cmd=new MyTextArea();
	Color bg=new Color(25,25,25);
	Font bFont=new Font("",Font.PLAIN,15);
	Color textc=new Color(5,204,226);
	CommandPanel(){
		//面板参数
		this.setBackground(bg);
		this.setLayout(null);
		this.setSize(480,140);
		//控件
		cmd.setSizex(this.getWidth()-10, this.getHeight()-30).setLocationx(5, 18).setTextx("[Main]welcome")
		.setForegroundx(textc).setBackgroundx(bg).setFontx(bFont);
		
		this.add(cmd);
	}
	void exec(String cmdstr) {
		String[] cmdspt=cmdstr.split(" ");
		switch(cmdspt[0]) {
		case "echo":{
			cmd.setText(cmd.getText()+"\n"+cmdspt[1]);
			break;
		}
		case "clear":{
			cmd.setText("");
			break;
		}
		}
	}
}
