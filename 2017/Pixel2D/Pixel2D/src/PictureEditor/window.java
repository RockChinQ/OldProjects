package PictureEditor;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class window {
	gui g=new gui(600,600,0,0,false,"PictureEditor For Pixel2D",true,true,true);
	JMenuBar jmb=new JMenuBar();
	JMenu file=new JMenu("文件");
	JMenuItem save=new JMenuItem("保存");
	JMenuItem open=new JMenuItem("打开");
	JMenuItem exit=new JMenuItem("退出");
	
	JMenu setting=new JMenu("设置");
	
	JPanel cp=new JPanel();

	JButton point=new JButton(".");
	JButton line=new JButton("—");
	JButton circle=new JButton("○");
	JButton rect=new JButton("□");
	JButton text=new JButton("A");
	JButton get=new JButton("$");
	JButton del=new JButton("×");
	JButton trans=new JButton(" ");
	
	JPanel op=new JPanel();
	JButton color=new JButton("前景色：█");
	JButton bg=new JButton("背景色：█");
	JButton set_wh=new JButton("显示比例");
	
	JLabel message=new JLabel("<消息>");
	
	int pw=Integer.parseInt(javax.swing.JOptionPane.showInputDialog(null, "长度")),ph=Integer.parseInt(javax.swing.JOptionPane.showInputDialog(null, "高度"));
	Pixel2D p2d=new Pixel2D(20,20,pw,ph,JColorChooser.showDialog(null, "选取背景颜色", null),true,Color.gray,true,new mouse0());
	public window(){
		g.setJMenuBar(jmb);
		g.setLayout(null);
		//g.setAlwaysOnTop(true);
		
		save.addActionListener(new action2());
		
		file.add(open);
		file.add(save);
		file.add(exit);
		
		open.addActionListener(new action2());
		
		exit.addActionListener(new action0());
		
		jmb.add(file);
		jmb.add(setting);
		
		cp.setLayout(null);
		point.setSize(50, 30);
		point.setLocation(0, 0);
		point.addActionListener(new action1());
		line.setSize(50, 30);
		line.setLocation(0, 30);
		line.addActionListener(new action1());
		circle.setSize(50, 30);
		circle.setLocation(0,60);
		circle.addActionListener(new action1());
		rect.setSize(50, 30);
		rect.setLocation(0, 90);
		rect.addActionListener(new action1());
		text.setSize(50, 30);
		text.setLocation(0, 120);
		text.addActionListener(new action1());
		get.setSize(50, 30);
		get.setLocation(0, 150);
		get.addActionListener(new action1());
		del.setSize(50, 30);
		del.setLocation(0, 180);
		del.addActionListener(new action1());
		trans.setSize(50, 30);
		trans.setLocation(0, 210);
		trans.addActionListener(new action1());
		
		cp.add(point);
		cp.add(line);
		cp.add(circle);
		cp.add(rect);
		cp.add(text);
		cp.add(get);
		cp.add(del);
		cp.add(trans);
		cp.setSize(60, 240);
		cp.setBackground(new Color(47,47,47));
		
		op.setLayout(null);
		op.setSize(300, 50);
		color.setSize(100, 30);
		color.setLocation(0, 0);
		color.setForeground(Color.GREEN);
		color.addActionListener(new action0());
		
		bg.setSize(100, 30);
		bg.setLocation(100, 0);
		bg.setForeground(Color.white);
		bg.addActionListener(new action0());	
		
		set_wh.setSize(100, 30);
		set_wh.setLocation(200, 0);
		set_wh.setForeground(Color.black);
		set_wh.addActionListener(new action0());
		op.setBackground(new Color(47,47,47));
		op.add(bg);
		op.add(color);
		op.add(set_wh);
		
		message.setSize(200, 30);
		message.setLocation(0, 25);
		message.setForeground(Color.WHITE);
		g.add(message);
		
		op.setLocation(0,0);
		g.add(op);
		cp.setLocation(0, 70);
		g.add(cp);
		p2d.setLocation(60, 70);
		g.add(p2d);
		g.setVisible(true);
		g.getContentPane().setBackground(new Color(47,47,47));
	}
}






















