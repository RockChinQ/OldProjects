package data;

import java.util.ArrayList;

public class Log {
	static ArrayList<String> log=new ArrayList<String>();
	public static void record(String msg) {
		StackTraceElement[] ste=Thread.currentThread().getStackTrace();
		StackTraceElement a=(StackTraceElement)ste[2];
		log.add("["+a.getClassName()+"."+
				a.getMethodName()+"]"+msg);
		System.out.println("["+a.getClassName()+"."+
					a.getMethodName()+"()."+a.getLineNumber()+"]"+msg);
	}
	/*public void saveLog(String fileName) {
		for(int i=0;i<log.size();i++) {
			
		}
	}*/
}
