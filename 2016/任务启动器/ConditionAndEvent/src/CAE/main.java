package CAE;

import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
public class main {
	static String v="V1.5(160524<update>)";//版本
	//static StringBuffer path0 = new StringBuffer(System.getProperty("java.class.path"));
	//static check c=new check();
	static write w=new write();
	static f fc=new f();
	static spawnwindows sp=new spawnwindows();
	static draw2 dr=new draw2();
	static gui gi=new gui(470,305,spawnwindows.xl/2-260,spawnwindows.yl/2-190,true,"任务启动器"+v,false);
	static int in=0;
	static check c=new check();
	public static void main(String[] args) throws Exception {
		//System.out.println(path0.indexOf("任务启动器v1.0(160417).jar"));
		File f=new File("file");
		if(!f.isDirectory()){
			f.mkdir();
			w.dow("file\\exit.txt","询问");
		}
		//c.start();
		fc.start();
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
		gi.setVisible(true);
		Thread.sleep(1200);
		gi.setVisible(false);
		new setting();
		spawnwindows.wi.requestFocus();
		Image img=Toolkit.getDefaultToolkit().createImage("file\\图标.jpg");
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
		String i=t1.getMessage();
		sp.start();
		c.start();
	}
}
