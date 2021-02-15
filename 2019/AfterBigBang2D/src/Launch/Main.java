package Launch;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;

public class Main {
	public static final int W=100,H=100;
	public static final float RATE=(float) 0.0002;
	public static ArrayList<Dust> dusts=new ArrayList<Dust>();
	public static Window w;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		clear();
		SpawnRandomly();
		w=new Window();
		new Timer().schedule(new Update(), new Date(), 1000);
	}
	public static void SpawnRandomly() {
		int a=0;
		for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++) {
				if(Math.random()<RATE){
					System.out.println(i+" "+j+" "+(a++));
					dusts.add(new Dust(j,i));
				}
			}
		}
	}
	public static void clear() {
		dusts=new ArrayList<Dust>();
	}
}
