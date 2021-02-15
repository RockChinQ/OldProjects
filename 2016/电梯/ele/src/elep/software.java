package elep;

import java.util.TimerTask;

import javax.swing.ImageIcon;

public class software extends TimerTask{
	static int taskc=0;
	static int[] task=new int[]{0,0,0,0,0,0,0,0};
	static int to=1;
	static boolean run=false;
	public void run() {
		if(run){
			hardware.g.setTitle2("电梯模拟-控制窗口 正在运行...");
			if(to*100<Math.round(hardware.floor*100)){
				main.h.setFloor((float) (hardware.floor-0.1));
				if(Math.round(hardware.floor)%1==0&&Math.round(hardware.floor+0.35)==(int)hardware.floor){
					int ft=(int) hardware.floor;
					if(hardware.down[ft-1].getIcon().equals(hardware.j1)){
						main.h.setFloor((float) (hardware.floor-0.11));
						main.h.openDoor();
						hardware.down[ft-1].setIcon(hardware.j0);
						hardware.up[ft-1].setIcon(hardware.i0);
						main.h.setFloor((float) (hardware.floor+0.11));
					}
				}
			}else if(to*100>Math.round(hardware.floor*100)){
				main.h.setFloor((float) (hardware.floor+0.1));
				if(Math.round(hardware.floor)%1==0){
					int ft=(int) hardware.floor;
					if(hardware.up[ft-1].getIcon().equals(hardware.i1)){
						main.h.setFloor((float) (hardware.floor-0.11));
						main.h.openDoor();
						hardware.down[ft-1].setIcon(hardware.j0);
						hardware.up[ft-1].setIcon(hardware.i0);
						main.h.setFloor((float) (hardware.floor+0.11));
					}
				}
			}
			if(to*100==Math.round(hardware.floor*100)){
				main.h.openDoor();
				hardware.jb0[to-1].setIcon(new ImageIcon(main.sou+(to-1)+".jpg"));
				//System.out.println(taskc+"1  "+software.task[0]+" "+software.task[1]+" "+software.task[2]+" "+software.task[3]+" "+software.task[4]+" "+software.task[5]+" "+software.task[6]+" "+software.task[7]);
				software.task[0]=software.task[7]=0;
				software.run=software.task[1]==0?false:true;
				for(int i=0;i<8;){
					if(software.task[i+1]!=0){
						software.task[i]=software.task[i+1];
					}else{
						software.task[i]=0;
						break;
					}
					i++;
				}
				//System.out.println(taskc+"2  "+software.task[0]+" "+software.task[1]+" "+software.task[2]+" "+software.task[3]+" "+software.task[4]+" "+software.task[5]+" "+software.task[6]+" "+software.task[7]);
				software.to=software.task[0];
				taskc--;
				//software.taskc=(software.taskc<=0)?software.taskc:software.taskc-1;
			}
		}else{hardware.g.setTitle2("电梯模拟-控制窗口 已停止");}
	}
}
