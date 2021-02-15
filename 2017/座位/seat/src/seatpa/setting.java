package seatpa;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class setting {
	class nnn{}
	static gui g=new gui(500,400,0,0,false,"设置",false,false,true);
	static JLabel jl=new JLabel(new ImageIcon(main.sou+"line2.jpg"));
	static JLabel jl0=new JLabel("编辑器字号:");
	static JComboBox jcb1=new JComboBox();
	static JPanel jp=new JPanel();
	static JButton jbt0=new JButton("保存设置");
	static JCheckBox jcb=new JCheckBox("自动保存(每30秒保存一次) ",Boolean.parseBoolean(main.s[0]));
	static JLabel cols=new JLabel("点选编辑器底色:");
	static JButton[] color=new JButton[20];
	static JLabel choise=new JLabel(new ImageIcon(main.sou+Integer.parseInt(main.s[2])+".jpg"));
	static int col=Integer.parseInt(main.s[2]);
	static int[] re=new int[]{0,127,136,237,255,255,34,0,63,163,255,195,185,255,255,239,181,153,112,200},gr=new int[]{0,127,0,28,127,242,177,162,72,73,255,195,122,174,201,228,230,217,146,191},bl=new int[]{0,127,21,36,39,0,76,232,204,164,255,195,87,201,14,176,29,234,190,231};
	setting(){
		g.setVisible(true);
		jp.setLayout(null);
		jl.setSize(1000, 3);
		jl.setLocation(0,45);
		//jp.add(jl);
		
		jcb.setSize(500,30);
		jcb.setLocation(25, 10);
		jcb.setFont(new Font("",Font.BOLD,20));
		
		jp.add(jcb);

		jl0.setSize(500,30);
		jl0.setLocation(25, 50);
		jl0.setFont(new Font("",Font.BOLD,20));
		jp.add(jl0);

		cols.setSize(500,30);
		cols.setLocation(25,90);
		cols.setFont(new Font("",Font.BOLD,20));
		jp.add(cols);
		
		choise.setSize(30,30);
		choise.setLocation(190,90);
		jp.add(choise);
		int x=25,y=125;
		for(int a=0;a<20;a++){
			color[a]=new JButton(new ImageIcon(main.sou+a+".jpg"));
			color[a].setSize(35, 35);
			color[a].setLocation(x,y);
			color[a].setBackground(Color.white);
			color[a].addActionListener(new action1());
			jp.add(color[a]);
			x+=48;
			if(a==9){
				y+=45;
				x=25;
			}
		}
		
		jcb1.setSize(70,30);
		jcb1.setLocation(145, 50);
		jcb1.setFont(new Font("",Font.ITALIC,20));
		jcb1.addItem("大");
		jcb1.addItem("中");
		jcb1.addItem("小");
		jcb1.setSelectedItem(jcb1.getItemAt(main.s[1].equals("大")?0:main.s[1].equals("中")?1:0));
		jp.add(jcb1);
		
		jbt0.setSize(110, 40);
		jbt0.setLocation(20,320);
		jbt0.setFont(new Font("",Font.BOLD,15));
		jbt0.addActionListener(new actionli0());
		jp.add(jbt0);
		g.add(jp);
	}
}
