package PictureViewer;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;


public class window {
	gui g=new gui(400,400,0,0,false,"PictureViewer For Pixel2D",true,true,true);
	JPanel jp=new JPanel();
	JButton open=new JButton("Open");
	JLabel hk=new JLabel("Grid Size(40):");
	JSlider jsl=new JSlider(1,100,40);
	Pixel2D p2d=new Pixel2D(40,40,10,10,Color.white,true,Color.black,false,null);
	JButton up=new JButton("¡ü"),down=new JButton("¡ý"),left=new JButton("¡û"),right=new JButton("¡ú");
	JButton line=new JButton("Lines");
	window(){
		//g.setAlwaysOnTop(true);
		jp.setLayout(null);
		g.setLayout(null);
		p2d.setGridSize(jsl.getValue(),jsl.getValue());
		
		open.setSize(100, 30);
		open.setLocation(0, 0);
		open.addActionListener(new action0());
		jp.add(open);
		
		p2d.setSize(401,401);
		p2d.setLocation(10, 40);
		g.add(p2d);
		
		hk.setSize(116, 30);
		hk.setLocation(110, 0);
		hk.setFont(new Font("",Font.BOLD,15));
		jp.add(hk);
		
		jsl.setSize(150, 30);
		jsl.setLocation(220, 0);
		jsl.addChangeListener(new change());
		jp.add(jsl);

		up.setSize(50, 30);
		up.setLocation(390, 0);
		up.addActionListener(new action1());
		jp.add(up);
		down.setSize(50, 30);
		down.setLocation(440, 0);
		down.addActionListener(new action1());
		jp.add(down);
		left.setSize(50, 30);
		left.setLocation(490, 0);
		left.addActionListener(new action1());
		jp.add(left);
		right.setSize(50, 30);
		right.setLocation(540, 0);
		right.addActionListener(new action1());
		jp.add(right);
		
		line.setSize(100, 30);
		line.setLocation(600, 0);
		line.addActionListener(new action1());
		jp.add(line);
		
		jp.setSize(720, 30);
		jp.setLocation(0, 0);
		g.setSize(40*10+41,400+111);
		g.add(jp);
		g.setVisible(true);
	}
}
