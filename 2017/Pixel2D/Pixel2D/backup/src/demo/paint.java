package demo;

import java.util.Timer;
import java.util.TimerTask;

public class paint extends TimerTask {

	String[] str=new String[]{"Here!","Rock","#$c%_&^..","Chin","@@_@","amazing...","F*ck it!","M","Youuuuuu","Pixel 2D!!","01000","000112","!@#$%^&*()_"};
	@Override
	public void run() {
		int x=(int)(Math.random()*1000),y=(int)(Math.random()*1000);
		main.w.p2d.drawString(x,y,"$"+(int)(Math.random()*10)+""+str[(int)(Math.random()*100%str.length)],null,1,"chars-7.txt");
	}
	
}
