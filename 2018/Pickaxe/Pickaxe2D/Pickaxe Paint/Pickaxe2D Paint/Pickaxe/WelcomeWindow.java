package Pickaxe;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;

public class WelcomeWindow {
	JWindow welw=new JWindow();
	JLabel jl=new JLabel(new ImageIcon("pickaxe.png"));
	JLabel message=new JLabel("正在加载界面...");
	Color textc=new Color(5,204,226);
	WelcomeWindow(){
		message.setSize(200, 30);
		message.setLocation(17,170);
		message.setForeground(textc);
		welw.add(message);
		jl.setSize(450,210);
		jl.setLocation(0,0);
		welw.add(jl);
		
		welw.setSize(450, 210);
		welw.setLocation(200, 200);
		welw.setAlwaysOnTop(true);
		welw.setLayout(null);
		
		welw.setVisible(true);
		try {
			Thread.sleep(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
