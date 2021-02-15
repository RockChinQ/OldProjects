package asp;

import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class main {
	static long l=0;
	static Thread tt=new Thread();
	public static void main(String[] args) {
		try{
		Image img=Toolkit.getDefaultToolkit().createImage("file\\图标.jpg");
		TrayIcon ti=new TrayIcon(img);
		ti.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if (e.getClickCount() == 1){
					System.exit(0);
				}
			}
		});
		SystemTray.getSystemTray().add(ti);
		
		for(;;){
			tt.sleep(60000);
			l++;
			System.out.println(l);
			if(l>=7200000){   //
				Runtime.getRuntime().exec("shutdown -s -t 300");
				int i=javax.swing.JOptionPane.showConfirmDialog(null, "五分钟后关机！");
				if(i==1||i==2){
					Runtime.getRuntime().exec("shutdown -a");
				}
				System.exit(0);
			}
		}}catch(Exception e){}
	}

}
