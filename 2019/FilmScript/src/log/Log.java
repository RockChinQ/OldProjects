package log;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;

public class Log {
	static ArrayList<String> log=new ArrayList<String>();
	static String lsClass="",lsMethod="";
	static int lsLine=-1;
	static long NO=0;
	static long lastTime=new Date().getTime();
	public static void record(String msg) {
		StackTraceElement[] ste=Thread.currentThread().getStackTrace();
		StackTraceElement a=(StackTraceElement)ste[2];
		log.add("["+a.getClassName()+"."+
				a.getMethodName()+"]"+msg);
		String tsClass=(a.getClassName().equals(lsClass)?"~":a.getClassName());
		String tsMethod=(tsClass.equals("~")&&a.getMethodName().equals(lsMethod)?"~":a.getMethodName());
		String tsLine=(tsMethod.equals("~")&&a.getLineNumber()==lsLine?"~":(""+a.getLineNumber()));
		long time=new Date().getTime();
		System.out.println((NO++)+"["+tsClass+"."+
					tsMethod+"."+
				tsLine+" ("+(time-lastTime)+")]---> "+msg);
		lastTime=time;
		lsClass=new String(a.getClassName());
		lsMethod=new String(a.getMethodName());
		lsLine=a.getLineNumber();
	}
	public static void saveLog() {
		new File("Log").mkdir();
		StringBuffer fstr=new StringBuffer();
		for(int i=0;i<log.size();i++) {
			fstr.append(log.get(i)+"\n");
		}
		Date d=new Date();
		write("Log\\"+(d.getYear()-100)+"-"+(d.getMonth()+1)+"-"+d.getDate()+"-"+d.getHours()+"-"+d.getMinutes()+"-"+d.getSeconds()+".txt",fstr.toString(),false);
	}
	public static void write(String s0,String s1,boolean b){
		try {
			FileWriter fw=new FileWriter(s0,b);
			fw.write(s1);
			fw.close();
		} catch (Exception e) {
		}
	}
}
