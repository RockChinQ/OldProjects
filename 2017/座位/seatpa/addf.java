package seatpa;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class addf {
	public static GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
	public static Rectangle rec=ge.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
	public static int xl=(int)rec.getWidth();
	public static int yl=(int)rec.getHeight();
	static gui g=new gui(500,560,xl/2-500/2,yl/2-250,true,"新建",false,false);
	static JButton[][] seat=new JButton[15][15];
	static ImageIcon ii2=new ImageIcon(main.sou+"seated.jpg");
	static ImageIcon ii=new ImageIcon(main.sou+"seat.jpg");
	JPanel jp=new JPanel();
	static JLabel jl=new JLabel("统计：0");
	static JLabel jl0=new JLabel("请点击方格以确定教室内课桌摆放，一个格为一个人位置：");
	static boolean[][] ba=new boolean[15][15];
	static int tj=0;
	static JButton jbt=new JButton("两点大范围选取:关");
	static int[] loa=new int[]{-1,-1};
	static boolean lab=false;
	static ImageIcon ii3=new ImageIcon(main.sou+"chose.jpg");
	void addf2(){
		System.gc();
		seat=new JButton[15][15];
		ii2=new ImageIcon(main.sou+"seated.jpg");
		ii=new ImageIcon(main.sou+"seat.jpg");
		jp=new JPanel();
		jl=new JLabel("统计：0");
		ba=new boolean[15][15];
		tj=0;
		jp.setLayout(null);
		g.addWindowListener(new windowli());
		jl.addMouseListener(new mouseli());
		for(int a=0;a<15;a++){
			for(int b=0;b<15;b++){
				ba[a][b]=false;
				seat[a][b]=new JButton(ii);
				seat[a][b].setSize(30, 30);
				seat[a][b].setLocation((a+1)*30-5, (b+1)*30+5);
				seat[a][b].addActionListener(new mouseli());
				jp.add(seat[a][b]);
			}
		}
		
		jl.setSize(500, 30);
		jl.setLocation(370, 490);
		jp.add(jl);
		
		jl0.setSize(500, 30);
		jl0.setLocation(30, 5);
		jp.add(jl0);
		

		jbt.setSize(150, 30);
		jbt.setLocation(25, 490);
		jp.add(jbt);
		jbt.addActionListener(new mouseli());
		
		g.add(jp);
		g.setVisible(true);
		//g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	addf(){
		addf2();
	}
}
