package 倒计时;

import java.awt.Font;
import java.util.Date;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class main {
	static int day=20,hour=10,min=0,se=0;
	static JFrame jf=new JFrame("学前培训结束倒计时");
	static JLabel jl=new JLabel("");
	static Timer t=new Timer();
	
	public static void main(String[] args) {
		jf.setLocation(300, 300);
		jf.setSize(650,100);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setResizable(false);
		jf.setVisible(true);
		jl.setFont(new Font("Serif",Font.PLAIN,55));
		jf.add(jl);
		t.schedule(new th(),5,5);
	}
}
