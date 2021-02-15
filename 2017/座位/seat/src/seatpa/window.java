package seatpa;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class window {
	class nnn{}
	public static GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
	public static Rectangle rec=ge.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
	public static int xl=(int)rec.getWidth();
	public static int yl=(int)rec.getHeight();
	static gui g=new gui(350,200,xl/2-500/2,yl/2-250,true,"Seat",true,false,true);
	static JButton jl0=new JButton(new ImageIcon(main.sou+"新建.jpg"));
	static JButton jl1=new JButton(new ImageIcon(main.sou+"打开.jpg"));
	static JLabel jl=new JLabel("欢迎使用座位表编辑器，请选择一个选项:");
	static JButton help=new JButton(new ImageIcon(main.sou+"help.jpg"));
	JPanel jp=new JPanel();
	window(){
		g.setTitle("座位表编辑器 来自:Rock");
		jp.setLayout(null);
		jp.setBackground(new Color(210,210,210));
		
		jl0.setSize(80, 80);
		jl0.setLocation(60, 50);
		jl0.addMouseListener(new mouseli());
		
		jl1.setSize(80, 80);
		jl1.setLocation(190, 50);
		jl1.addMouseListener(new mouseli());

		jl.setSize(390, 30);
		jl.setLocation(10, 15);
		jl.setFont(new Font("",Font.BOLD,17));

		help.setSize(35, 35);
		help.setLocation(305,135);
		help.addActionListener(new action4());
		jp.add(help);
		
		jp.add(jl0);
		jp.add(jl1);
		jp.add(jl);
		g.add(jp);
		g.setVisible(true);
		g.addKeyListener(new keylis());
	}
}
