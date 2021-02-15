package powerp;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class selectg extends JPanel{
	static JButton[] jbt=new JButton[20];
	static JButton bt=new JButton(new ImageIcon(main.sou+"back.png"));
	static JLabel jl=new JLabel(new ImageIcon(main.sou+"sel.png"));
	selectg(){
		this.setLayout(null);
		bt.setSize(50, 45);
		bt.setLocation(5,5);
		bt.addMouseListener(new mouseevent());
		jl.setSize(630,45);
		jl.setLocation(55, 5);
		this.add(bt);
		this.add(jl);
		int x=35,y=100;
		for(int i=0;i<20;){
			jbt[i]=new JButton(""+(i+1));
			jbt[i].setSize(60, 60);
			jbt[i].setLocation(x, y);
			jbt[i].setFont(new Font("Serif",Font.ROMAN_BASELINE,25));
			jbt[i].addActionListener(new actevent());
			this.add(jbt[i]);
			x+=75;
			i++;
			if(i%5==0){
				x=35;
				y+=85;
			}
		}
	}
}
