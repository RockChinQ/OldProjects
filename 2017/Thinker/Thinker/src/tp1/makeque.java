package tp1;
import java.awt.Color;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import tp0.*;
public class makeque {
	tp0.gui g=new tp0.gui(1050, 510, 0, 0, false,"Thinker-命题",false, false, true);
	draw2 d;
	JTextArea jta=new JTextArea();
	JButton start=new JButton("开始解题");
	JLabel line=new JLabel(new ImageIcon("line.jpg")),line0=new JLabel(new ImageIcon("line0.jpg"));
	JTextField index=new JTextField();
	JButton indexl=new JButton(new ImageIcon("index.png"));
	File dic=new File("theorem");
	public makeque(){
		g.setLayout(null);
		/*
		int maxx=0,minx=450,maxy=0,miny=470;
		for(int a=0;a<main.dr.xayc;a++){
			maxx=Math.max(main.dr.xay[a][0],maxx);
			minx=Math.min(main.dr.xay[a][0], minx);
			maxy=Math.max(main.dr.xay[a][1], maxy);
			miny=Math.min(main.dr.xay[a][1], miny);
		}*/
		d=new draw2(450,470);
		
		d.setSize(450, 470);
		d.setLocation(590, 5);
		g.add(d);
		/*
		jta.setSize(420, 400);
		jta.setLocation(10, 10);
		jta.setBorder(BorderFactory.createLineBorder(Color.black,2));
		g.add(jta);*/

		start.setSize(130, 40);
		start.setLocation(10, 435);
		g.add(start);
		
		line.setSize(1000, 3);
		line.setLocation(0,422);
		g.add(line);
		
		line0.setSize(3, 422);
		line0.setLocation(290,0);
		g.add(line0);
		
		index.setSize(250, 28);
		index.setLocation(5, 5);
		g.add(index);
		
		indexl.setSize(28, 28);
		indexl.setLocation(256, 5);
		g.add(indexl);
		
		g.setVisible(true);
		g.addWindowListener(new window0());
		if(dic.isDirectory()){
			File[] fili=dic.listFiles();
			int flen=fili.length;
			for(int a=0;a<flen;a++){
				
			}
		}else{
			dic.mkdir();
		}
	}
}
