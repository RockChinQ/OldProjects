package p0;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class firpan extends JPanel {
	static JButton jbt0=new JButton("新建");
	static JButton jbt1=new JButton("打开");
	static JButton jbt2=new JButton("设置");
	static JButton jbt3=new JButton("退出");
	static JLabel jl0=new JLabel("请选择您要执行的操作:");
	static JLabel jl1=new JLabel("-------------------------------");
	static JLabel jl2=new JLabel("历史记录:");
	static JLabel[] ll=new JLabel[ln()];
	static int xi;
	
	firpan(){
		this.setLayout(null);
		jbt0.setSize(180, 70);
		jbt0.setLocation(30, 80);
		jbt1.setSize(180, 70);
		jbt1.setLocation(230, 80);
		jbt2.setSize(180, 70);
		jbt2.setLocation(430, 80);
		jbt3.setSize(180, 70);
		jbt3.setLocation(630, 80);
		jl0.setSize(400, 50);
		jl0.setLocation(30, 10);
		jl0.setFont(new Font("Serif",Font.HANGING_BASELINE,30));
		jl1.setSize(400, 50);
		jl1.setLocation(30, 30);
		jl1.setFont(new Font("Serif",Font.PLAIN,30));
		this.add(jbt0);
		this.add(jbt1);
		this.add(jbt2);
		this.add(jbt3);
		this.add(jl0);
		this.add(jl1);
		lsjl();
	}
	void lsjl(){
		File file=new File("ls.txt");
		if(file.exists()&&!new file0().read("ls.txt").equals("")){
			jl2.setSize(180, 50);
			jl2.setLocation(30, 150);
			jl2.setFont(new Font("Serif",Font.ITALIC,30));
			this.add(jl2);
			String[] str=new file0().read("ls.txt").split(" ");
			xi=ln();
			int y=200;
			for(int i3=0;i3<xi;){
				ll[i3]=new JLabel(str[i3]);
				ll[i3].setSize(1000, 50);
				ll[i3].setLocation(30, y);
				ll[i3].setFont(new Font("Serif",Font.TRUETYPE_FONT,30));
				ll[i3].addMouseListener(new mlm());
				this.add(ll[i3]);
				i3+=1;
				y+=35;
			}
			return;
		}
		new file0().write("ls.txt", "", true);
	}
	static int ln(){
		String[] str=new file0().read("ls.txt").split(" ");
		return str.length;
	}
}
