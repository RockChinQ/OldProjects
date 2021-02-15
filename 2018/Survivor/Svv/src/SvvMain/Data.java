package SvvMain;

import java.util.ArrayList;

public class Data {
	//basic
	Config cf=new Config("Svv.ini");
	Config devLists=new Config("devLists.ini");
	//int operPeriod=Integer.parseInt(cf.read("operPeriod"));
	
	//program require
	int test_i=Integer.parseInt(cf.read("test_i")),test_j=Integer.parseInt(cf.read("test_j"));
	//int cycleps=Integer.parseInt(cf.read("cycleps"));
	int speedTestPeriod=Integer.parseInt(cf.read("speedTestPeriod"));
	int checkStartPeriod=Integer.parseInt(cf.read("checkStartPeriod"));
	int checkStopPeriod=Integer.parseInt(cf.read("checkStopPeriod"));
	
	//task
	String[] paramsGen=cf.read("paras").split(";");
	int paramCount=paramsGen.length;
	ArrayList[] param=new ArrayList[paramCount];
	String taskStr=cf.read("task");
	
	//Operating thread
	int operingDiskCount=Integer.parseInt(cf.read("operingDiskCount"));
	int[] threadPointers=new int[26];                             //标记了某个字母表示的盘符是否正在被操作
	Operate[] operingThr=new Operate[operingDiskCount];         //里面的disk标记了某个线程是否在运行
	//protect label
	String[] protectLabel=cf.read("protectLabel").split(":");
	//last oper label
	String lastOperLabel=cf.read("lastOperLabel");
	
	class Task{
		String[] startCon,stopCon,pauseCon,continueCon,oper;
		Task(String taskStr){
			String[] temp=taskStr.split("#");
			startCon=temp[0].split("@")[1].split("&");
			stopCon=temp[1].split("@")[1].split("[|]");
			pauseCon=temp[3].split("@")[1].split("[|]");
			continueCon=temp[4].split("@")[1].split("&");
			oper=temp[2].split("@")[1].split("&");
		}
		
	}
	Task task=new Task(taskStr);
	Data() {
		//task
		int count=0;
		for(int i=0;i<paramCount;i++) {
			String[] temp=paramsGen[i].split(" ");
			count=temp.length;
			param[i]=new ArrayList();
			for(int j=0;j<count;j++) {
				param[i].add(temp[j]);
			}
		}
		
		//thread preset
		for(int i=0;i<26;i++) {
			this.threadPointers[i]=-1;
		}
		for(int i=0;i<this.operingDiskCount;i++) {
			this.operingThr[i]=new Operate((char) -1);
		}
		System.out.println("Data done.");
	}
	
}









