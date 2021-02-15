package Main;

import java.util.Timer;

import Display.DrawTimerTask;
import Display.Window;
import Kernel.ComputeImage;

public class Main {
	public static DataExch dat;
	public static Window w;
	public static Timer cpt,draw,wp;
	public static ComputeImage cpti;
	public static DrawTimerTask dtt;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		dat=new DataExch();
		w=new Window();
		cpt=new Timer();
		cpti=new ComputeImage();
		
		draw=new Timer();
		dtt=new DrawTimerTask();
	}

}
