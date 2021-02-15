package SvvMain;

import java.io.File;
import java.util.Date;
import java.util.TimerTask;

public class StopRunTimer extends TimerTask{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int stopConLen=Launcher.d.task.stopCon.length;
		root:for(int i=0;i<stopConLen;i++) {

			//System.out.println("spr0");
			String[] temp=Launcher.d.task.stopCon[i].split(" ");        //对每个开始条件分割检查
			int paramLabel=Integer.parseInt(temp[1]);                   //参数下标
			switch(temp[0]) {
			case "CCPS":{
				//System.out.println("spr0");
				if(Launcher.st.cyclepsTested<Integer.parseInt((String)Launcher.d.param[paramLabel].get(0))
						||Launcher.st.cyclepsTested>Integer.parseInt((String)Launcher.d.param[paramLabel].get(1))) {
					
					//System.out.println("spr0");
					for(int j=0;j<Launcher.d.operingDiskCount;j++) {
						if(Launcher.d.operingThr[j].disk>='A'&&Launcher.d.operingThr[j].disk<='Z') {
							stopAThread(j,"CCPS");
							//Launcher.d.operingThr[i].disk=(char)-1;
						}
					}
					break root;                                    //如果速度小于最小值或大于最大值则停止操作
				}
				//System.out.println("spr0");
				break;
			}
			case "FILEA":{
				for(int j=0;j<Launcher.d.operingDiskCount;j++) {
					if(Launcher.d.operingThr[j].disk>='A'&&Launcher.d.operingThr[j].disk<='Z'&&Launcher.d.operingThr[j].fileOperedAmount>Integer.parseInt((String)Launcher.d.param[paramLabel].get(0))) {
						stopAThread(j,"FILEA");
					}
				}
				break;
			}
			case "FILES":{
				for(int j=0;j<Launcher.d.operingDiskCount;j++) {
					if(Launcher.d.operingThr[j].disk>='A'&&Launcher.d.operingThr[j].disk<='Z'&&Launcher.d.operingThr[j].fileOperedSize>Integer.parseInt((String)Launcher.d.param[paramLabel].get(0))) {
						stopAThread(j,"FILES");
					}
				}
				break;
			}
			case "FOLDERA":{
				for(int j=0;j<Launcher.d.operingDiskCount;j++) {
					if(Launcher.d.operingThr[j].disk>='A'&&Launcher.d.operingThr[j].disk<='Z'&&Launcher.d.operingThr[j].folderOperedAmount>Integer.parseInt((String)Launcher.d.param[paramLabel].get(0))) {
						stopAThread(j,"FOLDERA");
					}
				}
				break;
			}
			case "TIME_OUT":{
				long timeNow=new Date().getTime();
				for(int j=0;j<Launcher.d.operingDiskCount;j++) {
					if(Launcher.d.operingThr[j].disk>='A'&&Launcher.d.operingThr[j].disk<='Z'&&timeNow-Launcher.d.operingThr[j].timeStart>Integer.parseInt((String)Launcher.d.param[paramLabel].get(0))) {
						stopAThread(j,"TIME_OUT");
					}
				}
				break;
			}
			}
			
		}
		
	}
	void stopAThread(int thrPointer,String reason) {
		Launcher.d.operingThr[thrPointer].stop();
		Launcher.d.threadPointers[Launcher.d.operingThr[thrPointer].disk-'A']=-1;
		Launcher.d.operingThr[thrPointer].disk=(char)-1;
		System.out.println("Stop because of "+reason);
	}

}
