package FMMain;

import java.util.Date;
import java.util.TimerTask;

public class Test extends TimerTask{
	boolean running=false;
	long cycleps=0;
	long in=Boot.db.in,jn=Boot.db.jn;
	long temp=0;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(!running) {
			running=true;
			Date d=new Date();
			long t0=d.getTime();
			for(long i=0;i<in;i++) {
				for(int j=0;j<jn;j++) {
					temp+=1;
				}
			}
			cycleps=(in*jn)/(new Date().getTime()-t0)/1000;
			Boot.cf.write("cyclepsTested", cycleps+"");
			
			System.out.println("tested dt="+(cycleps-Boot.db.cycleps));
			running=false;
		}
	}

}
