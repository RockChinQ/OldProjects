package elep;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class hardware {
	static gui g=new gui(500,450,200,200,true,"电梯模拟-正在准备...",true,false);
	static gui g2=new gui(350,450,698,200,true,"电梯模拟-正在准备...",false,false);
	static JLabel loa=new JLabel("电梯高度:");
	static JLabel run=new JLabel("状态:已停止");
	static JLabel task=new JLabel("运行任务:");
	static JLabel task2=new JLabel("");
	static JLabel speed=new JLabel("运行频率:20Hz");
	static JLabel click=new JLabel("修改频率(1-1000之间)");
	
	static ImageIcon i0=new ImageIcon(main.sou+"up.jpg");
	static ImageIcon i1=new ImageIcon(main.sou+"up1.jpg");
	static ImageIcon j0=new ImageIcon(main.sou+"down.jpg");
	static ImageIcon j1=new ImageIcon(main.sou+"down1.jpg");
	static JPanel jp=new JPanel();
	static JButton[] jb0=new JButton[10];
	static JButton[] up=new JButton[8];
	static JButton[] down=new JButton[8];
	JPanel jp2=new JPanel();
	static JLabel ld0=new JLabel(new ImageIcon(main.sou+"door.jpg"));
	static JLabel ld1=new JLabel(new ImageIcon(main.sou+"door.jpg"));
	JLabel str=new JLabel(new ImageIcon(main.sou+"string.jpg"));
	JLabel str2=new JLabel(new ImageIcon(main.sou+"string.jpg"));
	JLabel jl0=new JLabel("电梯内部的楼层按钮:");
	JLabel[] flo=new JLabel[8];
	JLabel[] floorl=new JLabel[8];
	JLabel[] st2=new JLabel[9];
	Thread t=new Thread();
	static float floor=1;
	
	hardware(){
		g2.setTitle2("电梯模拟-等待另一窗口准备");
		g.setTitle2("电梯模拟-正在准备控件...");
		jp.setLayout(null);
		int x=50,y=80;
		g.setTitle2("电梯模拟-正在准备电梯按钮...");
		for(int i=0;i<8;i++){
			jb0[i]=new JButton(new ImageIcon(main.sou+i+".jpg"));
			jb0[i].setSize(40, 40);
			jb0[i].setLocation(x, y);
			jb0[i].addActionListener(new aceve2());
			jp.add(jb0[i]);
			x+=50;
			if(x>=140){
				x=50;
				y+=50;
			}
		}
		x=330;
		y=15;
		g.setTitle2("电梯模拟-正在准备外部按钮...");
		for(int i=7;i>=0;i--){                                   //up&down
			flo[i]=new JLabel("*第"+(i+1)+"层外部按钮:");
			flo[i].setFont(new Font("Serif",Font.PLAIN,18));
			floorl[i]=new JLabel("第"+(i+1)+"层");
			floorl[i].setFont(new Font("Serif",Font.PLAIN,18));
			up[i]=new JButton(i0);
			down[i]=new JButton(j0);
			flo[i].setSize(150, 20);
			floorl[i].setSize(150, 20);
			up[i].setSize(40, 40);
			down[i].setSize(40, 40);
			flo[i].setLocation(x-132, y+10);
			floorl[i].setLocation(20, 450-i*50-65);
			up[i].setLocation(x, y);
			down[i].setLocation(x+60, y);
			up[i].addActionListener(new aceve());
			down[i].addActionListener(new aceve());
			jp2.add(floorl[i]);
			jp.add(flo[i]);
			jp.add(up[i]);
			jp.add(down[i]);
			y+=50;
		}
		jl0.setLocation(8, 25);
		jl0.setSize(300, 30);
		jl0.setFont(new Font("Serif",Font.ITALIC,20));
		jp.add(jl0);
		g.add(jp);
		g.setVisible(true);
		g.setTitle2("电梯模拟-此窗口准备完毕");
		
		/*模拟动画*/

		g2.setTitle2("电梯模拟-正在准备控件...");
		g2.addWindowListener(new winevent());
		jp2.setLayout(null);
		ld0.setSize(20, 40);
		ld0.setLocation(80,375);
		jp2.add(ld0);
		
		ld1.setSize(20, 40);
		ld1.setLocation(100,375);
		jp2.add(ld1);
		g2.add(jp2);

		str.setSize(2, 500);
		str.setLocation(78, 18);
		jp2.add(str);
		str2.setSize(2, 500);
		str2.setLocation(120, 18);
		jp2.add(str2);
		
		for(int i0=0;i0<9;){
			st2[i0]=new JLabel(new ImageIcon(main.sou+"string2.jpg"));
			st2[i0].setSize(40, 2);
			st2[i0].setLocation(80,  450-i0*50-31);
			jp2.add(st2[i0]);
			i0++;
		}
		loadDataLabel();
		g2.setTitle2("电梯模拟-正在刷新窗口...");
		g2.setVisible(true);
		g2.setTitle2("电梯模拟-准备完毕");
		g.setTitle2("电梯模拟-控制窗口");
		g2.setTitle2("电梯动画");
		setFloor(1);
		g2.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		g2.setVisible(true);
	}
	void loadDataLabel(){
		loa.setSize(500, 30);
		run.setSize(500, 30);
		task.setSize(500, 30);
		task2.setSize(500, 30);
		speed.setSize(500, 30);
		click.setSize(500, 30);
		
		loa.setLocation(128, 50);
		run.setLocation(128, 90);
		task.setLocation(128, 130);
		task2.setLocation(128, 170);
		speed.setLocation(128, 210);
		click.setLocation(128, 250);
		
		Font f=new Font("Serif",Font.ROMAN_BASELINE,18);
		loa.setFont(f);
		run.setFont(f);
		task.setFont(f);
		task2.setFont(new Font("Serif",Font.ROMAN_BASELINE,16));
		speed.setFont(f);
		click.setFont(f);
		jp2.add(loa);
		jp2.add(run);
		jp2.add(speed);
		jp2.add(task);
		jp2.add(task2);
		jp2.add(click);
		click.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {
				try {
					Desktop.getDesktop().open(new File(main.sou+"运行频率.txt"));
					javax.swing.JOptionPane.showMessageDialog(null, "重启程序后生效");
					System.exit(0);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			public void mouseReleased(MouseEvent arg0) {}
		});
	}
	int setFloor(float i){
		floor=i;
		if(i>8||i<1)
			return 1;
		hardware.g2.setTitle2("电梯动画-第"+(Math.round(i*100)/100)+"层");
		hardware.ld0.setLocation(80, (int) (450-i*50-25));
		hardware.ld1.setLocation(100, (int) (450-i*50-25));
		return 0;
	}
	int openDoor(){
		try{
			String s=g2.getTitle();
			g2.setTitle("电梯动画-开门...");
			int i0=415-hardware.ld0.getHeight();
			int i=0;
			for(i=0;i<5;i++){
				try {
					t.sleep(main.speed/10+100);
				} catch (InterruptedException e) {}
				
				hardware.ld0.setLocation(80-i,(int) (450-floor*50-25));
				hardware.ld1.setLocation(100+i,(int) (450-floor*50-25));
			}
			for(i=0;i<5;i++){
				try {
					t.sleep(main.speed/10+100);
				} catch (InterruptedException e) {}
				hardware.ld0.setLocation(75+i,(int) (450-floor*50-25));
				hardware.ld1.setLocation(105-i,(int) (450-floor*50-25));
			}
			hardware.ld0.setLocation(80,(int) (450-floor*50-25));
			hardware.ld1.setLocation(100,(int) (450-floor*50-25));
			g2.setTitle(s);
		}catch(Exception e){
			return 1;
		}
		return 0;
	}
}
