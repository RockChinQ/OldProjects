package elep;

import java.util.TimerTask;

public class refresh extends TimerTask{
	public void run() {
		if(!software.run){
			for(int i=0;i<8;){
				if(hardware.down[i].getIcon().equals(hardware.j1)||hardware.up[i].getIcon().equals(hardware.i1)){
					software.task[software.taskc]=i+1;
					software.taskc++;
					hardware.down[i].setIcon(hardware.j0);
					hardware.up[i].setIcon(hardware.i0);
				}
				i++;
			}
			if(software.task[0]!=0){
				software.to=software.task[0];
				software.run=true;
			}
		}
	}
}
