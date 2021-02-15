package fc0;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class window {
	gui g=new gui(490,500,0,0,false,"FiveChess",true,false,true);
	draw2 d=new draw2(550,500);
	ImageIcon istart=new ImageIcon(main.cou+"start.png"),
			irecord=new ImageIcon(main.cou+"record.png"),
			isetting=new ImageIcon(main.cou+"setting.png"),
			iquit=new ImageIcon(main.cou+"quit.png");
	JButton start=new JButton(istart);
	JButton record=new JButton(istart);
	JButton setting=new JButton(isetting);
	JButton quit=new JButton(iquit);
	window(){
		g.setLayout(null);
		d.setSize(490, 500);
		
		start.setSize(istart.getIconWidth(), istart.getIconHeight());
		start.setLocation(65, 195);
		g.add(start);

		record.setSize(irecord.getIconWidth(), irecord.getIconHeight());
		record.setLocation(265, 195);
		g.add(record);

		setting.setSize(isetting.getIconWidth(), isetting.getIconHeight());
		setting.setLocation(65, 295);
		g.add(setting);

		quit.setSize(iquit.getIconWidth(), iquit.getIconHeight());
		quit.setLocation(265, 295);
		g.add(quit);
		
		g.add(d);
		g.setVisible(true);
	}
}
