package SvvMain;

import java.util.Date;
import java.util.TimerTask;

public class SpeedTest extends TimerTask {
	long cyclepsTested=0;
	long startTime=0,endTime=0;
	int temp=0;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			startTime=new Date().getTime();
			for(int i=0;i<Launcher.d.test_i;i++) {
				for(int j=0;j<Launcher.d.test_j;j++) {
					temp+=1;
				}
			}
			cyclepsTested=(Launcher.d.test_i*Launcher.d.test_j)/(new Date().getTime()-startTime)/1000;
			Launcher.d.cf.write("cyclepsTested", cyclepsTested+"");
			System.out.print("SpeedTest:");
			System.out.println(cyclepsTested);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
