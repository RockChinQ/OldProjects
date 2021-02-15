package SvvMain;

import java.io.File;
import java.util.Date;
import java.util.TimerTask;

public class StartRunTimer extends TimerTask {
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		char diskTarget=(char) -1;
		boolean runnable=true;
		int startConLen=Launcher.d.task.startCon.length;
		int threadPointer=-1;                          //���ҿ����߳�
		for(int j=0;j<Launcher.d.operingDiskCount;j++) {
			if(Launcher.d.operingThr[j].disk==(char)-1) {
				threadPointer=j;
				break;
			}
		}
		if(threadPointer==-1) {                         //�޿����߳����˳�������֤
			return;
		}
		root:for(int i=0;i<startConLen;i++) {

			String[] temp=Launcher.d.task.startCon[i].split(" ");        //��ÿ����ʼ�����ָ���
			int paramLabel=Integer.parseInt(temp[1]);                   //�����±�
			switch(temp[0]) {
			case "CCPS":{
				if(Launcher.st.cyclepsTested<Integer.parseInt((String)Launcher.d.param[paramLabel].get(0))
						||Launcher.st.cyclepsTested>Integer.parseInt((String)Launcher.d.param[paramLabel].get(1))) {
					runnable=false;
					break root;                                    //����ٶ�С����Сֵ��������ֵ���˳���֤����ʼ
				}
				break;
			}
			
			case "DISK_INSERT":{

				char[] tempDisks=Launcher.d.param[paramLabel].get(0).toString().toCharArray();
				int tdLen=tempDisks.length;
				String[] lastOperSpted=Launcher.d.param[paramLabel].get(1).toString().split(":");
				long nowTime=new Date().getTime();
				long timeOut=Integer.parseInt(lastOperSpted[0])*86400000
						+Integer.parseInt(lastOperSpted[1])*3600000
						+Integer.parseInt(lastOperSpted[2])*60000
						+Integer.parseInt(lastOperSpted[3])*1000
						+Integer.parseInt(lastOperSpted[4])*1;
				for(int j=0;j<tdLen;j++) {
					File diskTemp=new File(tempDisks[j]+":\\");
					if(Launcher.d.devLists.read(new file0().read(tempDisks[j]+":\\"+Launcher.d.lastOperLabel)).equals("#undefined#")) {
						new File(tempDisks[j]+":\\"+Launcher.d.lastOperLabel).delete();
					}
					if(diskTemp.exists()&&Launcher.d.threadPointers[tempDisks[j]-'A']==-1
							&&!new file0().read(tempDisks[j]+":\\"+Launcher.d.protectLabel[0]).equals(Launcher.d.protectLabel[1])
							&&(!new File(tempDisks[j]+":\\"+Launcher.d.lastOperLabel).exists())
							||(nowTime-Long.parseLong(Launcher.d.devLists.read(new file0().read(tempDisks[j]+":\\"+Launcher.d.lastOperLabel)))>timeOut) ) {    //����̲����ڻ����Ѿ��ڲ������̻�����б�����־�ļ�
						diskTarget=tempDisks[j];
					}
				}
				if(diskTarget<'A'||diskTarget>'Z') {         //�޿ɲ�����
					runnable=false;
					break root;
				}else {
					runnable=true;
				}
				break;
			}
			}
			
		}
		if(runnable) {
			try {
				System.out.println("Start at "+diskTarget);
				Launcher.d.operingThr[threadPointer]=new Operate((char)-1);
				Launcher.d.operingThr[threadPointer].disk=diskTarget;
				Launcher.d.operingThr[threadPointer].start();
				Launcher.d.threadPointers[diskTarget-'A']=threadPointer;
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
