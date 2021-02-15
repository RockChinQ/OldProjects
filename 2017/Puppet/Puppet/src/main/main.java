package main;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;

public class main {
	public static String sou="";
	public static ArrayList<String> pgmc=new ArrayList<String>();
	public static ArrayList<String> pgms=new ArrayList<String>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] pgmtable=new file0().read("pgmTable.txt").split(";");
		for(int a=0;a<pgmtable.length;a++){
			String[] tmp=pgmtable[a].split(" ");
			pgmc.add(tmp[0]);
			pgms.add(tmp[1]);
		}
		
		Timer t=new Timer();
		t.schedule(new scanner(), new Date(),200);
	}

}








