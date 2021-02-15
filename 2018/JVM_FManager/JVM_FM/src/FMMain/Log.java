package FMMain;

public class Log {
	getTime gt=new getTime();
	void write(String logSource,String logMessage) {
		new file0().write("JVM_FM.log", "#"+logSource+"# "+logMessage+" ["+gt.getTimeCode()+"]\r\n", true);
	}
}
