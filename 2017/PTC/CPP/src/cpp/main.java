package cpp;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.Timer;

public class main {
	static getTime gt=new getTime();
	static boolean boo=true;
	static Image img=Toolkit.getDefaultToolkit().createImage("ͼ��.jpg");
	static TrayIcon ti3=new TrayIcon(img);
	public static void main(String[] args) {
		new file0().write("jl.txt",gt.getYear()+"��"+gt.getMonth()+"��"+gt.getDayOfMonth()+"��"+gt.getHourOfDay()+"ʱ"+gt.getMinute()+"��"+gt.getSecond()+"��   \n",true);
		try {
			new Thread().sleep(10000);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Timer ti=new Timer();
		ti.schedule(new check(), new Date(), 2000);
		Timer ti2=new Timer();
		ti2.schedule(new as(), new Date(), 60000);
		
		ti3.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if (e.getClickCount() == 1){
					System.exit(0);
				}
			}
		});
		try {
			SystemTray.getSystemTray().add(ti3);
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
