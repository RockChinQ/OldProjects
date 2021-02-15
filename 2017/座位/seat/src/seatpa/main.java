package seatpa;

import java.io.File;
import java.util.Date;
import java.util.Timer;

import javax.swing.UIManager;

public class main {
	class nnn{}
	static String sou="";//"G:\\计算机\\Java\\myprograms\\2017\\座位\\" E:\\Java\\jdk1.8.0_31\\bin\\2017\\座位\\
 	static int label=0;
 	static boolean bb=true;
 	static String[] s;
 	static StringBuffer ssb=new StringBuffer();
 	static boolean b=false;
 	static boolean st=false;
	public static void main(String[] args) throws Exception{
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		if(!new File(sou+"setting").exists()){
			new file0().write(sou+"setting","false 大 17 154 217 234",false);
			new Thread().sleep(200);
		}
		s=new file0().read(sou+"setting").split(" ");
		if(s[0].equals("true")){
			Timer t=new Timer();
			t.schedule(new autosave(),new Date(),30000);
		}
		new window();
	}

}
