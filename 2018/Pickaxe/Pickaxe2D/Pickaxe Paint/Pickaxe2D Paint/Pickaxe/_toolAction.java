package Pickaxe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

public class _toolAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(Boot.guim.toolp.tPoint)) {
			Boot.guim.toolp.setSelectedTool(Boot.guim.toolp.tPoint);
			Boot.guim.toolp.setAButtonVisible(false);
			Boot.guim.toolp.updateToolNow();
		}else if(arg0.getSource().equals(Boot.guim.toolp.tLine)){
			Boot.guim.toolp.setSelectedTool(Boot.guim.toolp.tLine);
			Boot.guim.toolp.setAButtonVisible(false);
			Boot.guim.toolp.updateToolNow();
		}else if(arg0.getSource().equals(Boot.guim.toolp.tShape)) {
			Boot.guim.toolp.setSelectedTool(Boot.guim.toolp.tShape);
			Boot.guim.toolp.setAButtonVisible(false);
			Boot.guim.toolp.updateToolNow();
			//2 3 4
			Boot.guim.toolp.aButton[2].setIconx(Boot.guim.toolp.rect).setVisible(true);
			Boot.guim.toolp.aButton[3].setIconx(Boot.guim.toolp.circle).setVisible(true);
			Boot.guim.toolp.aButton[4].setIconx(Boot.guim.toolp.poly).setVisible(true);
		}else if(arg0.getSource().equals(Boot.guim.toolp.tSelect)) {
			Boot.guim.toolp.setSelectedTool(Boot.guim.toolp.tSelect);
			Boot.guim.toolp.setAButtonVisible(false);
			Boot.guim.toolp.updateToolNow();
			//3 4 5 6
			Boot.guim.toolp.aButton[3].setIconx(Boot.guim.toolp.sRect).setVisible(true);
			Boot.guim.toolp.aButton[4].setIconx(Boot.guim.toolp.sCircle).setVisible(true);
			Boot.guim.toolp.aButton[5].setIconx(Boot.guim.toolp.sPoly).setVisible(true);
			Boot.guim.toolp.aButton[6].setIconx(Boot.guim.toolp.sMagic).setVisible(true);
		}else if(arg0.getSource().equals(Boot.guim.toolp.tGetColor)) {
			Boot.guim.toolp.setSelectedTool(Boot.guim.toolp.tGetColor);
			Boot.guim.toolp.setAButtonVisible(false);
			Boot.guim.toolp.updateToolNow();
			
		}else if(arg0.getSource().equals(Boot.guim.toolp.tClear)) {
			Boot.guim.toolp.setSelectedTool(Boot.guim.toolp.tClear);
			Boot.guim.toolp.setAButtonVisible(false);
			Boot.guim.toolp.updateToolNow();
			
		}else if(arg0.getSource().equals(Boot.guim.toolp.tFill)) {
			Boot.guim.toolp.setSelectedTool(Boot.guim.toolp.tFill);
			Boot.guim.toolp.setAButtonVisible(false);
			Boot.guim.toolp.updateToolNow();
			
		}else if(arg0.getSource().equals(Boot.guim.toolp.tText)) {
			Boot.guim.toolp.setSelectedTool(Boot.guim.toolp.tText);
			Boot.guim.toolp.setAButtonVisible(false);
			Boot.guim.toolp.updateToolNow();
			
		}else if(arg0.getSource().equals(Boot.guim.toolp.tEnlarge)) {
			Boot.guim.toolp.setSelectedTool(Boot.guim.toolp.tEnlarge);
			Boot.guim.toolp.setAButtonVisible(false);
			Boot.guim.toolp.updateToolNow();
		}else if(arg0.getSource().equals(Boot.guim.toolp.tMouse)) {
			Boot.guim.toolp.setSelectedTool(Boot.guim.toolp.tMouse);
			Boot.guim.toolp.setAButtonVisible(false);
			Boot.guim.toolp.updateToolNow();
		}
		for(int i=0;i<10;i++) {
			if(arg0.getSource().equals(Boot.guim.toolp.aButton[i])) {
				Boot.guim.toolp.selectTool.setIcon(((MyButton) arg0.getSource()).getIcon());
				if(Boot.guim.toolp.selectTool.equals(Boot.guim.toolp.tShape)) {
					Boot.guim.toolp.sshape=(ImageIcon) ((MyButton) arg0.getSource()).getIcon();
				}else {
					Boot.guim.toolp.sselect=(ImageIcon) ((MyButton) arg0.getSource()).getIcon();
				}
				Boot.guim.toolp.updateToolNow();
			}
		}
	}

}
