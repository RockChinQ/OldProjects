package seatpa;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.JOptionPane;

public class windowli implements WindowListener {
	class nnn{}
	public void windowActivated(WindowEvent arg0) {
	}
	public void windowClosed(WindowEvent arg0) {
	}
	public void windowClosing(WindowEvent arg0) {
		if(arg0.getSource()==editgui.g){
			if(editgui.corr){
				int i=JOptionPane.showConfirmDialog(null, "座位表已被修改，是否保存后再退出？");
				if(i==0){
					for(int a=0;a<editgui.l0;a++){
						for(int b=0;b<editgui.l1;b++){
							if(editgui.isseat[a][b]){
								editgui.name[a][b]=new StringBuffer(editgui.jbt[a][b].getText());
							}
						} 
					}
					if(new save().save0()){
						JOptionPane.showMessageDialog(null, "保存成功！");
						System.exit(0);
					}
				}else if(i==1){
					System.exit(0);
				}else{}
			}else if(!editgui.corr){
				System.exit(0);
			}
		}
	}
	public void windowDeactivated(WindowEvent arg0) {
	}

	public void windowDeiconified(WindowEvent arg0) {
	}

	public void windowIconified(WindowEvent arg0) {

	}

	public void windowOpened(WindowEvent arg0) {
	}

}
