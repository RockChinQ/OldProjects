package FMMain;

import java.io.File;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimerManager extends TimerTask{
	File sign=new File("exit\\log.tmp");
	boolean running=false;
	public void run() {
		if(sign.exists()) {
			Boot.log.write("EnerExit", "Code:1");
			System.exit(0);
		}
		for(int i=0;i<Boot.db.diskAmount&&!running;i++) {   //开始操作
			//System.out.println(Boot.db.diskSet[i]+" "+(Boot.db.diskSet[i]+":\\")+" "+new File(Boot.db.diskSet[i]+":\\").exists());
			if(new File(Boot.db.diskSet[i]+":\\").exists()
					&&computeStartExp(Boot.db.task.startCondition)
					&&!new file0().read(Boot.db.diskSet[i]+":\\protect.p").equals("000112")) {
				running=true;        //标志
				Boot.log.write("Timer", "start operation");
				Boot.db.task.oper.run(Boot.db.diskSet[i]);
			}
		}
		String reason=computeStopExp(Boot.db.task.stopCondition);
		if(!reason.equals("false")&&!Boot.db.operationPause&&running) {     //停止操作
			Boot.log.write("Timer", "stop because "+reason);
			Boot.db.operationPause=true;
		}
	}
	boolean computeStartExp(String exp) {
		/*String[] splitedExp=exp.split("(?<=\\()[^\\)]+");
		Pattern pattern = Pattern.compile("(?<=\\()[^\\)]+");  
		Matcher matcher = pattern.matcher(exp);
		while(matcher.find()){
		   System.out.println(matcher.group());
		}*/
		String[] splitedExp=exp.split("&");
		int len=splitedExp.length;
		boolean label=true;
		for(int i=0;i<len;i++) {
			switch(splitedExp[i]) {
			case "SPE":
			case "spe":{
				//System.out.println(Boot.te.cycleps+" "+Boot.db.cycleps);
				if(Boot.te.cycleps<=Boot.db.cycleps) {
					label=false;
				}
				break;
			}
			}
		}
		return label;
	}
	String computeStopExp(String exp) {
		String[] splitedExp=exp.split("[|]");
		int len=splitedExp.length;
		for(int i=0;i<len;i++) {
			root:switch(splitedExp[i]) {
			case "SPE":
			case "spe":{
				if(Boot.te.cycleps<Boot.db.cycleps) {
					return "SPE";
				}
				break;
			}
			case "FILEA":
			case "filea":{
				if(Boot.db.task.oper.operFileA>Boot.db.operatedFileAmount) {
					return "FILEA";
				}
				break;
			}
			case "FILES":
			case "files":{
				if(Boot.db.task.oper.operFileS>Boot.db.operatedFileSize) {
					return "FILES";
				}
				break;
			}
			case "FOLDERA":
			case "foldera":{
				if(Boot.db.task.oper.operFolderA>Boot.db.operatedFolderAmount) {
					return "FOLDERA";
				}
				break;
			}
			}
		}
		return "false";
	}
}
