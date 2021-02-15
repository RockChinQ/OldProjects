package seatpa;

import javax.swing.JButton;

public class pic {
	class nnn{}
	static gui g=new gui(editgui.l0*60+15,editgui.l1*40+90,0,0,false,"座位表图片",false,false,true);
	static draw2 d=new draw2();
	static JButton jbt=new JButton("保存为图片");
	pic(){
		g.setLayout(null);
		jbt.setSize(100, 40);
		jbt.setLocation(0, 0);
		jbt.addActionListener(new actionli0());
		g.add(jbt);
		d.setSize(editgui.l0*60+15,editgui.l1*40+90);
		d.setLocation(0, 0);
		g.add(d);
		g.setVisible(true);
	}
}
