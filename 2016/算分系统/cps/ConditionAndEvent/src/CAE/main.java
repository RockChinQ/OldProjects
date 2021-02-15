package CAE;

import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class main {
	//static StringBuffer path0 = new StringBuffer(System.getProperty("java.class.path"));
	//static String path = path0.delete(path0.indexOf("任务启动器v1.0(160417).jar"),path0.indexOf("任务启动器v1.0(160417).jar")+21).toString();
	static check c=new check();
	static write w=new write();
	static f fc=new f();
	static spawnwindows sp=new spawnwindows();
	static draw dr=new draw("作者.jpg");
	static gui gi=new gui(392,415,spawnwindows.xl/2-260,spawnwindows.yl/2-190,false,"关于程序",false);
	public static void main(String[] args) {
		//System.out.println(path0.indexOf("任务启动器v1.0(160417).jar"));
		c.start();
		fc.start();
		sp.start();
		gi.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		gi.addWindowListener(new WindowListener(){
			public void windowActivated(WindowEvent e) {}
			public void windowClosed(WindowEvent e) {}
			public void windowClosing(WindowEvent e) {
				gi.setVisible(false);
				spawnwindows.wi.requestFocus();
			}
			public void windowDeactivated(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowOpened(WindowEvent e) {}
		});
		gi.setResizable(false);
		gi.add(dr);
		new setting();
		spawnwindows.wi.requestFocus();
		Image img=Toolkit.getDefaultToolkit().createImage("任务启动器0.jpg");
		TrayIcon ti=new TrayIcon(img);
		ti.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if (e.getClickCount() == 1){
					spawnwindows.wi.setVisible(true);
				}
			}
		});
		try{
			if(true){
				SystemTray.getSystemTray().add(ti);
			}
		}catch(Exception e1){
			System.out.println("托盘出错："+e1);
		}
	}
}
