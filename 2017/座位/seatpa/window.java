package seatpa;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class window {
	public static GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
	public static Rectangle rec=ge.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
	public static int xl=(int)rec.getWidth();
	public static int yl=(int)rec.getHeight();
	static gui g=new gui(300,200,xl/2-500/2,yl/2-250,true,"Seat",true,false);
	static JLabel jl0=new JLabel(new ImageIcon(main.sou+"新建.jpg"));
	static JLabel jl1=new JLabel(new ImageIcon(main.sou+"打开.jpg"));
	static JLabel jl2=new JLabel();
	JPanel jp=new JPanel();
	window(){
		jp.setLayout(null);
		jp.setBackground(new Color(210,210,210));
		
		jl0.setSize(75, 75);
		jl0.setLocation(50, 50);
		jl0.addMouseListener(new mouseli());
		
		jl1.setSize(75, 75);
		jl1.setLocation(150, 50);
		
		jp.add(jl0);
		jp.add(jl1);
		g.add(jp);
		g.setVisible(true);
	}
}
