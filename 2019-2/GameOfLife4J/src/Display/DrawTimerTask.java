package Display;

import java.util.TimerTask;

public class DrawTimerTask extends TimerTask {
	public void run() {
		// TODO Auto-generated method stub
		Main.Main.w.di.repaint();
	}

}
