package seatpa;

import java.util.TimerTask;

import javax.swing.JOptionPane;

public class autosave extends TimerTask{
	class nnn{}
	public void run() {
		if(main.st){
			for(int a=0;a<editgui.l0;a++){
				for(int b=0;b<editgui.l1;b++){
					if(editgui.isseat[a][b]){
						editgui.name[a][b]=new StringBuffer(editgui.jbt[a][b].getText());
					}
				}
			}
			log l=new log();
			if(new save().save0()){
				l.addlog("自动保存成功");
				editgui.corr=false;
			}else{
				l.addlog("自动保存失败...");
			}
		}
	}

}
