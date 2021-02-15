package FMMain;

import java.io.File;
import java.util.Date;
import java.util.Timer;

public class Boot {
	static Config cf=new Config("config.ini");
	static Data db=new Data();
	final static int size=1;
	static Timer ee=new Timer();
	static Timer test=new Timer();
	static public Log log=new Log();
	static Test te=new Test();
	public static void main(String[] args) {
		new File("exit").delete();
		new File("exit").mkdir();
		new file0().write("log.tmp", "", false);
		ee.schedule(new TimerManager(), new Date(), db.checkPeriod);
		test.schedule(te, new Date(), 5000);
		
		int start=0;
		String[] command;
		/*
		if(args[0].equals("java"))
			start=1;
		*/
		if(args.length+start==size&&new File("config.ini"/*args[1]*/).exists()) {
			
		}
		log.write("Boot", "start config:diskset="+db.diskSet.toString()+" task="+db.task+" ");
	}

}
