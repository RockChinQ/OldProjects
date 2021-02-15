package ts;

import java.util.Date;

public class opt {
	static Date d;
	static long last;
	static long begin;
	public static void init() {
		d=new Date();
		last=d.getTime();
		begin=last;
	}
	public static void printTime() {
		d=new Date();
		long ti=d.getTime();
		System.out.println("...Time:"+(ti-begin)+" Last:"+(ti-last));
		last=ti;
	}
}
