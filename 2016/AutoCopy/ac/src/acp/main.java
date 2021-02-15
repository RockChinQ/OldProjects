package acp;

import java.io.File;
import java.util.Date;
import java.util.Timer;

public class main {
	static String s=javax.swing.JOptionPane.showInputDialog("ÅÌ·û:");
	//static int time=Integer.parseInt(javax.swing.JOptionPane.showInputDialog("ÊýÁ¿:"));
	static int ch=0;
	static File f=new File(s+":");
	static int i=0;
	public static void main(String[] args) {
		File fi=new File("D:\\ac");
		fi.mkdir();
		Timer t=new Timer();
		t.schedule(new tt(), new Date(), 1000);
	}

}
