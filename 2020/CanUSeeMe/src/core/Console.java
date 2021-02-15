package core;

import java.util.ArrayList;

public class Console {
	static ArrayList<String> msg;
	public static void init() {
		msg=new ArrayList<String>();
	}
	public static void print(String msg) {
		Console.msg.add(msg);
	}
	
}
