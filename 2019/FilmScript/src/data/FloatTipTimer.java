package data;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import boot.Main;

public class FloatTipTimer extends TimerTask {
	public Timer ti=new Timer();
	public FloatTipTimer() {
		ti.schedule(this,new Date(), 25);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(Main.gui.ft.time>0) {
			Main.gui.ft.time-=25;
			Main.gui.ft.resize();
			//System.out.println(Main.gui.bgp.ft.time);
		}
	}

}
