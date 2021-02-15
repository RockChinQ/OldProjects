package loadPic_testPlugin;

import java.util.Date;
import java.util.Timer;

public class MainClasss {
	public String label="";
	//以下为必包括字段
	public static final String author="Rock";
	public static final String version="1.0";
	public static final String pluginName="Test";
	public static final String pluginDetail="A test plugin in developing.";
	public void load() {
		System.out.println("loading...");
		System.out.println(label);
	}
	public void run() {
		for(int i=0;i<10;i++) {
			try {
				Thread.sleep(500);
				System.out.println(11);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		new Timer().schedule(new TimerTest(), new Date(), 1000);
		System.out.println("run end.");
		Lib.Sys.exit(label);
	}
}
