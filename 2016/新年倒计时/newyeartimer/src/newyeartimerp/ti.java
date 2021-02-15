package newyeartimerp;

import java.util.Calendar;
import java.util.TimerTask;

public class ti extends TimerTask{
	public void run() {
		System.out.println(new getTime().getSecond());
	}
}
