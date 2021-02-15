package MainPkg;

import java.util.Date;
import java.util.Timer;


public class Main {
	public static Window w;
	public static final double rate=0.0005;
	public static void main(String[] args) {
			// TODO Auto-generated method stub
		w=new Window();
		spawnRandomly();
		/*
		try {
		Thread.sleep(1000);
		new Update().updateAllDust();
		Thread.sleep(1000);
		new Update().updateAllDust();
		Thread.sleep(1000);
		new Update().updateAllDust();
		Thread.sleep(1000);
		new Update().updateAllDust();
		}catch(Exception e) {
			;
		}
		*/
		Timer tim=new Timer();
		tim.schedule(new Update(), new Date(), 1000);
	}
	public static void spawnRandomly() {
		for(int i=0;i<100;i++) {
			for(int j=0;j<100;j++) {
				if(Math.random()<=rate) {
					w.dusts.add(new Dust(new Vector(i,j)));
					w.dusts.get(w.dusts.size()-1).points.add(new Vector(i,j));
				}
			}
		}
		w.p.repaint();
	}

}
