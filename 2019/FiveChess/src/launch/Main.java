package launch;

import data.DataBase;
import data.FileRW;
import ts.opt;
import ui.Window;

public class Main {
	public static Window w;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataBase.init();
		opt.init();
		System.out.print("Launch");
		opt.printTime();
		w=new Window();
		//FileRW.write("111111.txt","\n\r1\n\r2\n\r3\r\n4\r\n5",false);
	}

}
