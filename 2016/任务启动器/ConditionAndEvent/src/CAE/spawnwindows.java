package CAE;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class spawnwindows extends Thread implements ActionListener,KeyListener, WindowListener{
	String s=t1.getMessage();
	public static GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
	public static Rectangle rec=ge.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
	public static int xl=(int)rec.getWidth();
	public static int yl=(int)rec.getHeight();
	public static gui wi=new gui(630,300,xl/2-650/2,yl/2-225,false, "任务启动器"+main.v,false);
	JButton jbt0=new JButton("添加任务");
	JButton jbt2=new JButton("关于程序");
	JButton jbt3=new JButton("程序设置");
	JButton jbt4=new JButton("退出程序");
	String ss="<无任务 请点击\"添加任务\"以添加>";
	static JLabel jl1 = new JLabel();
	static JLabel jl2 = new JLabel();
	static JLabel jl3 = new JLabel();
	static JLabel jl4 = new JLabel();
	static JLabel jl5 = new JLabel();
	static JLabel jl6 = new JLabel();
	static JLabel jl7 = new JLabel();
	static JLabel jl8 = new JLabel();
	static JLabel jl9 = new JLabel();
	static JLabel jl10 = new JLabel();
	static JLabel[] jl = new JLabel[]{jl1,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9,jl10};
	static JLabel l=new JLabel();
	static boolean b1=true;
	boolean b=false;
	public void run(){
		wi.setVisible(true);
		wi.setResizable(false);
		
		jbt0.setSize(110,40);
		jbt0.setLocation(20,40);
		jbt0.addActionListener(this);
		
		jbt2.setSize(110,40);
		jbt2.setLocation(20,90);
		jbt2.addActionListener(this);
		
		jbt3.setSize(110,40);
		jbt3.setLocation(20,140);
		jbt3.addActionListener(this);
		
		jbt4.setSize(110,40);
		jbt4.setLocation(20,190);
		jbt4.addActionListener(this);
		
		l.setSize(450,15);
		l.setLocation(150,250);

		JPanel jp=new JPanel();
		jp.setLayout(null);
		jp.add(jbt0);
		jp.add(jbt2);
		jp.add(jbt3);
		jp.add(jbt4);
		jp.add(l);
		int ii=3;
		for(int i=0;i<10;){
			jl[i].setText(ss);
			jp.add(jl[i]);
			jl[i].setSize(650,15);
			jl[i].setLocation(170,ii);
			jl[i].addMouseListener(new MouseListener(){
				public void mouseClicked(MouseEvent e) {
					int in=0;
					for(int t=0;t<10;){
						if(e.getSource()==jl[t]){
							in=t;
						}
						t++;
					}
					if(!jl[in].getText().equals("<无任务 请点击\"添加任务\"以添加>")&&javax.swing.JOptionPane.showConfirmDialog(null,"你确定要删除此任务吗?")==0){
						main.c.del(in);
					}
				}
				public void mouseEntered(MouseEvent arg0) {
					l.setText("点击文字标签可以删除已设定的任务!");
				}
				public void mouseExited(MouseEvent arg0) {
					l.setText(null);
				}
				public void mousePressed(MouseEvent arg0) {}
				public void mouseReleased(MouseEvent arg0) {}
				
			});
			i++;
			ii=ii+25;
		}
		
		wi.addKeyListener(this);
		wi.add(jp);
		jp.setFocusable(true);
		wi.requestFocus();
		wi.addWindowListener(this);
		wi.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jbt0){
			if(b1){
				b1=false;
				new addevent();
			}else{
				addevent.gi.requestFocus();
			}
		}
		if(e.getSource()==jbt2){
			if(!b){
				main.gi.setLocation(spawnwindows.xl/2-260,spawnwindows.yl/2-190);
				main.gi.setSize(470,480);
				main.gi.setTitle("关于程序");
				main.gi.setIconImage(gui.img);
				b=!b;
			}
			main.gi.setVisible(true);
		}
		if(e.getSource()==jbt3){
			setting.gi.setVisible(true);
		}
		if(e.getSource()==jbt4){
			this.close();
		}
	}
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {
		try {
			main.c.keycheck(e.getKeyChar());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	public void keyTyped(KeyEvent e) {}
	public void windowDeiconified(WindowEvent e){}
	public void windowActivated(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {}
	public void windowClosing(WindowEvent e) {
		this.close();
	}
	public void windowDeactivated(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowOpened(WindowEvent e) {}
	void close(){
		File file=new File("file\\exit.txt");
		if(file.exists()){

			FileReader fr = null;
			try {
				fr = new FileReader(file);
			} catch (Exception e1) {
				javax.swing.JOptionPane.showMessageDialog(null,"出错:"+e1+" 程序退出!");
				System.exit(0);
				main.w.del("file\\cmdfile.bat");
			}
			BufferedReader br=new BufferedReader(fr);
			StringBuffer sb=new StringBuffer("");
			while(true){
				String str = null;
				try {
					str = br.readLine();
				} catch (Exception e1) {
					javax.swing.JOptionPane.showMessageDialog(null,"出错:"+e1+" 程序退出!");
					System.exit(0);
					main.w.del("file\\cmdfile.bat");
				}
				if(str==null){
					break;
				}
				sb=new StringBuffer(sb+str);
			}
			if(sb.toString().equals("询问")){
				int r=JOptionPane.showConfirmDialog(null,"是否最小化至系统托盘？点击\"否\"直接退出");
				if(r==0){
					spawnwindows.wi.setVisible(false);
				}else if(r==1){
					System.exit(0);
					main.w.del("file\\cmdfile.bat");
				}
			}else if(sb.toString().equals("直接退出")){
				System.exit(0);
				main.w.del("file\\cmdfile.bat");
			}else if(sb.toString().equals("最小化到托盘")){
				spawnwindows.wi.setVisible(false);
			}
		}else if(!file.exists()){
			System.exit(0);
		}
	}
}

