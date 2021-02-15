package main;

import java.util.TimerTask;

public class scanner extends TimerTask{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String[] mes=new file0().read("d:\\message.txt").split("^^");
		
	}

}
