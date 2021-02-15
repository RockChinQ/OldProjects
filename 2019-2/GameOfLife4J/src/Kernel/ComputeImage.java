package Kernel;

import Main.*;
import java.util.TimerTask;

public class ComputeImage extends TimerTask{
	DataExch dat=Main.dat;
	public static int generation=0;
	public void run() {
		cptNext();
	}
	public void cptNext() {
		boolean newGen[][]=new boolean[Main.dat.pHeight][Main.dat.pWidth];
		//边界计算
		if(dat.boundCptType=='r') {  //初始随机值
			for(int i=0;i<dat.pHeight;i++) {  //列0 和列pWidth-1
				newGen[i][0]=dat.last[i][0];  //复制
				newGen[i][dat.pWidth-1]=dat.last[i][dat.pWidth-1];
			}
			for(int i=0;i<dat.pWidth;i++) {  //行0 和行pHeight-1
				newGen[0][i]=dat.last[0][i];  //复制
				newGen[dat.pHeight-1][i]=dat.last[dat.pHeight-1][i];
			}
		}else if(dat.boundCptType=='0'||dat.boundCptType=='1') {
			boolean label=dat.boundCptType=='0'?false:true;
			for(int i=0;i<dat.pHeight;i++) {  //列0 和列pWidth-1
				newGen[i][0]=label;
				newGen[i][dat.pWidth-1]=label;
			}
			for(int i=0;i<dat.pWidth;i++) {  //行0 和行pHeight-1
				newGen[0][i]=label;
				newGen[dat.pHeight-1][i]=label;
			}
		}
		//边界完成
		int count=0;  //周围活着的数量
		for(int i=1;i<dat.pHeight-1;i++) {
			for(int j=1;j<dat.pWidth-1;j++) {
				count=0;
				for(int k=i-1;k<=i+1;k++) {
					for(int l=j-1;l<=j+1;l++) {
						if(Main.dat.last[k][l])
							count++;
					}
				}
				count-=((dat.last[i][j]==true)?1:0);
				if(dat.last[i][j]) {
					if(count<=1||count>=4)
						newGen[i][j]=false;
					else
						newGen[i][j]=true;
				}else {
					if(count==3)
						newGen[i][j]=true;
				}
			}
		}
		Main.dat.last=newGen;
		generation++;
	}
}
