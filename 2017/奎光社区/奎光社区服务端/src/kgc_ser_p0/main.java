package kgc_ser_p0;

import java.net.Socket;

public class main {
	static String sou="E:\\Java\\jdk1.8.0_31\\bin\\2017";
	static boolean[] use=new boolean[25];
	static Socket[] soc=new Socket[25];
	static server sss=new server();
	public static void main(String[] args) {
		new window();
		sss.start();
	}

}
