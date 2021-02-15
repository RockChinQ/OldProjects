package FMMain;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class Task {
	class Operation extends Thread{
		String[] command;
		String[][] para;
		int amountCmds=0;
		long operFileA=0,operFileS=0,operFolderA=0,depth=0;
		Operation(String oper){
			String[] cmds=oper.split("[|][|]");
			int len=cmds.length;
			amountCmds=len;
			command=new String[len];
			para=new String[len][3];
			for(int i=0;i<len;i++) {
				String[] ts=cmds[i].split(" ");
				command[i]=ts[0];
				for(int j=0;j<Math.min(para[0].length, ts.length-1);j++) {
					para[i][j]=ts[j+1];
				}
			}
		}
		public void run(char disk) {
			System.out.println("start at "+disk+":\\");
			for(int i=0;i<amountCmds;i++) {
				switch(command[i]) {
				case "TREE":
				case "tree":{
					Boot.log.write("Task", "start tree");
					System.out.println("Treeing");
					scanAndOper(disk+":",para[i][0],"TREE");
					Boot.log.write("Task", "finish tree");
					break;
				}
				case "COPY":
				case "copy":{
					Boot.log.write("Task", "start copy");
					new File(para[i][0]).mkdirs();
					scanAndOper(disk+":",para[i][0],"COPY");
					Boot.log.write("Task", "finish copy. FileA="+this.operFileA+"(setting:"+Boot.db.operatedFileAmount+") FileS="+this.operFileS+"(setting:"+Boot.db.operatedFileSize+") FolderA="+this.operFolderA+"(setting:"+Boot.db.operatedFolderAmount);
					break;
				}
				case "EXIT":
				case "exit":{
					Boot.log.write("Task", "exit task");
					System.exit(0);
				}
				}
			}
		}
		void scanAndOper(String path,String target,String oper) {
			System.out.println(oper+" from "+path+" to "+target);
			File[] fileList=new File(path).listFiles();
			//
			for(int j=0;j<fileList.length;j++) {
				System.out.print("    "+fileList[j]+(j%5==0?"\n":""));
			}
			//
			int len=fileList.length;
			for(int i=0;i<len;i++) {
				try {
				if(Boot.db.operationPause) {
					try {
						this.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					i--;
					continue;
				}
				if(fileList[i].isFile()) {
					System.out.println("   File "+fileList[i]);
					/*copy*/
					if(oper.equals("COPY")) {
						try {
							//new file0().write(target+"\\"+fileList[i].getName(), "", false);
							Files.copy(fileList[i].getAbsoluteFile().toPath(),new File(target+"\\"+fileList[i].getName()).getAbsoluteFile().toPath());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						this.operFileA++;
						this.operFileS+=fileList[i].length()/1000;
					}else if(oper.equals("TREE")) {
						StringBuffer tab=new StringBuffer();
						for(int j=0;j<depth;j++)
							tab.append("    |");
						new file0().write(target, tab.toString()+fileList[i].getName()+"\r\n", true);
					}
				}else {
					System.out.println("   Dir "+fileList[i]);
					this.operFolderA++;
					if(oper.equals("COPY")) {
						File targetFolder=new File(target+"\\"+fileList[i].getName());
						if(!targetFolder.isDirectory()) {
							targetFolder.mkdirs();
						}
						this.depth++;
						scanAndOper(path+"\\"+fileList[i].getName(),target+"\\"+fileList[i].getName(),"COPY");
					}else if(oper.equals("TREE")) {
						StringBuffer tab=new StringBuffer();
						for(int j=0;j<depth;j++)
							tab.append("    |");
						System.out.println("new dir  target="+target);
						new file0().write(target, tab.toString()+"[Dir]"+fileList[i].getName()+"\r\n", true);
						this.depth++;
						scanAndOper(path+"\\"+fileList[i].getName(),target,"TREE");
					}
				}
				}catch(Exception e) {
					Boot.log.write("Task.scanAndOper", "Error:"+e.getMessage());
				}
			}
			depth-=1;
			return;
		}
		
	}
	String startCondition;
	String stopCondition;
	Operation oper;
	Task(String task){
		String[] ts=task.split("#");
		String[] tsstart=ts[0].split("@");
		String[] tsstop=ts[1].split("@");
		String[] tsoper=ts[2].split("@");
		startCondition=tsstart[1];
		stopCondition=tsstop[1];
		oper=new Operation(tsoper[1]);
	}
}
