package FMMain;

import java.util.ArrayList;

public class Data {
	int checkPeriod;
	Task task;
	//Runnable taskThread;
	char[] localDisk;
	char[] ignoreDisk;
	int ldlen,idlen;
	char[] diskSet;
	int diskAmount;
	long in,jn,cycleps;
	boolean operationPause=false;
	long operatedFolderAmount,operatedFileAmount,operatedFileSize;
	Data(){
		this.checkPeriod=Integer.parseInt(Boot.cf.read("checkPeriod"));
		this.task=new Task(Boot.cf.read("task"));
		localDisk=Boot.cf.read("localDiskSet").toCharArray();
		ignoreDisk=Boot.cf.read("ignoreDisk").toCharArray();
		ldlen=localDisk.length;
		idlen=ignoreDisk.length;
		in=Integer.parseInt(Boot.cf.read("in"));
		jn=Integer.parseInt(Boot.cf.read("jn"));
		cycleps=Integer.parseInt(Boot.cf.read("cycleps"));

		operatedFolderAmount=Integer.parseInt(Boot.cf.read("operatedFolderAmount"));
		operatedFileAmount=Integer.parseInt(Boot.cf.read("operatedFileAmount"));
		operatedFileSize=Integer.parseInt(Boot.cf.read("operatedFileSize"));
		/*DiskSet*/
		StringBuffer tsbds=new StringBuffer();
		root:for(int i='A';i<='Z';i++) {
			for(int j=0;j<ldlen;j++) {
				if(i==localDisk[j]) {
					continue root;
				}
			}
			for(int j=0;j<idlen;j++) {
				if(i==ignoreDisk[j]) {
					continue root;
				}
			}
			tsbds.append((char)i);
		}
		diskSet=tsbds.toString().toCharArray();
		diskAmount=diskSet.length;
	}
}
