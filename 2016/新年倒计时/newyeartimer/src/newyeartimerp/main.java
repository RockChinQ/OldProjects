package newyeartimerp;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.util.Date;
import java.util.Timer;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class main {
	static Timer tim=new Timer();
	public static GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
	public static Rectangle rec=ge.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
	public static int xl=(int)rec.getWidth();
	public static int yl=(int)rec.getHeight();
	static gui g=new gui(500,200,xl/2-250,yl/2-120,true,"2017µ¹¼ÆÊ±",true,false);
	static JPanel jp=new JPanel();
	static JLabel jl0=new JLabel("18:70:03");
	public static void main(String[] args) {
		jp.setLayout(null);
		jl0.setSize(490,190);
		jl0.setLocation(29, -10);
		jl0.setFont(new Font("Serif",Font.BOLD,120));
		jp.add(jl0);
		g.add(jp);
		tim.schedule(new ti(), new Date(), 1000);
	}

}
