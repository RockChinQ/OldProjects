package powerp;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class mainpanel extends 	JPanel{
	static ImageIcon ic=new ImageIcon(main.sou+"main1.png");
	static ImageIcon rwms=new ImageIcon(main.sou+"rwms.png");
	static ImageIcon zyms=new ImageIcon(main.sou+"zyms.png");
	static ImageIcon yxsz=new ImageIcon(main.sou+"yxsz.png");
	static ImageIcon tcyx=new ImageIcon(main.sou+"tcyx.png");
	static JLabel jl=new JLabel(ic);
	static JButton jbt0=new JButton(rwms);
	static JButton jbt1=new JButton(zyms);
	static JButton jbt2=new JButton(yxsz);
	static JButton jbt3=new JButton(tcyx);
	mainpanel(){
		this.setLayout(null);
		jbt0.setSize(165,65);
		jbt0.setLocation(80, 75);
		jbt0.addMouseListener(new mouseevent());
		
		jbt1.setSize(165,65);
		jbt1.setLocation(80,175);
		jbt1.addMouseListener(new mouseevent());

		jbt2.setSize(165,65);
		jbt2.setLocation(80,275);
		jbt2.addMouseListener(new mouseevent());

		jbt3.setSize(165,65);
		jbt3.setLocation(80,375);
		jbt3.addMouseListener(new mouseevent());
		
		
		jl.setSize(700, 530);
		jl.setLocation(0, 0);
		jl.addMouseListener(new mouseevent());
		this.add(jbt0);
		this.add(jbt1);
		this.add(jbt2);
		this.add(jbt3);
		this.add(jl);
	}
}
