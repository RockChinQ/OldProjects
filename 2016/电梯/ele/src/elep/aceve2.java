package elep;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

public class aceve2 implements ActionListener {//µçÌÝ°´Å¥
	public void actionPerformed(ActionEvent arg0) {
		int i=0;
		label:for(i=0;i<8;i++){
			if(arg0.getSource()==hardware.jb0[i]&&software.taskc!=8){
				for(int ii=0;ii<8;ii++){
					if(software.task[ii]==i+1){
						break label;
					}
				}
				software.task[software.taskc]=i+1;
				software.taskc++;
				hardware.jb0[i].setIcon(new ImageIcon(main.sou+i+"2.jpg"));
			}
		}
	}
}

