package SvvMain;

import java.util.Date;
import java.util.Timer;

public class Launcher {
	static Data d=new Data();
	static StartRunTimer strt=new StartRunTimer();
	static StopRunTimer sprt=new StopRunTimer();
	static SpeedTest st=new SpeedTest();
	static Timer timerSTRT=new Timer();
	static Timer timerSPRT=new Timer();
	static Timer timerST=new Timer();
	static Date dateSTRT=new Date(),dateSPRT=new Date(),dateST=new Date();
	static final long processCode=dateSTRT.getTime();        //本次运行的标记
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Start");
		timerST.schedule(st, dateST, d.speedTestPeriod);
		timerSTRT.schedule(strt, dateSTRT, d.checkStartPeriod);
		timerSPRT.schedule(sprt, dateSPRT, d.checkStopPeriod);
		
	}

}
