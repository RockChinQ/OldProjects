package cpp;

import java.io.IOException;
import java.util.TimerTask;

public class as extends TimerTask{
	static long lo=0;
	public void run() {
		lo++;
		if(lo>=300){
			try {
				Runtime.getRuntime().exec("shutdown -s -t 60");
				lo=0;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
