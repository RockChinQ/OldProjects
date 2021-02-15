package guiMgr.panels;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import boot.Main;
import guiMgr.PnlColor;
import guiMgr.panels.EditingPnl.Clip;

public class epKey implements KeyListener {
	EditingPnl ep;
	public epKey(EditingPnl ep) {
		this.ep=ep;
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println(111111111);
		//System.out.println(arg0.getKeyCode());
		ArrayList<EditingPnl.Clip> rclp=new ArrayList<EditingPnl.Clip>();
		if(Main.gui.bgp.ep.viewMode==Main.gui.bgp.ep.TIMELINE_VIEW) {
			if(arg0.getKeyCode()==KeyEvent.VK_DELETE) {//del
				for(EditingPnl.Clip clp:Main.gui.bgp.ep.elms) {
					if(clp.chosen) {
						rclp.add(clp);
					}
				}
				if(rclp.size()!=0) {
					for(Clip vdo:rclp) {
						Main.gui.sbp.sb.removeShot(vdo);
					}
					Main.gui.bgp.ep.elms.removeAll(rclp);
					Main.gui.bgp.ep.updateElement();
				}
			}else if(arg0.getKeyCode()==arg0.VK_RIGHT) {
				Main.gui.bgp.ep.playNow++;
				Main.gui.bgp.ep.isPlayNowInPanel();
				Main.gui.bgp.ep.updateElement();
			}else if(arg0.getKeyCode()==arg0.VK_LEFT) {
				if(Main.gui.bgp.ep.playNow>0)
					Main.gui.bgp.ep.playNow--;
				Main.gui.bgp.ep.isPlayNowInPanel();
				Main.gui.bgp.ep.updateElement();
			}else if(arg0.getKeyCode()==77) {
				Main.gui.bgp.ep.addLabel(Main.gui.bgp.ep.playNow,"Label-"+
						(Main.gui.bgp.ep.labelIndex+1),null);
				Main.gui.bgp.ep.tmln.repaint();
			}else if(arg0.getKeyCode()==65&&arg0.isControlDown()) {//ctrl+a
				for(int i=0;i<Main.gui.bgp.ep.elms.size();i++) {
					Main.gui.bgp.ep.elms.get(i).chosen=true;
				}
				Main.gui.bgp.ep.updateElement();
			}else if(arg0.isControlDown()&&arg0.getKeyCode()==67) {//¸´ÖÆ
				Main.gui.bgp.ep.copy();
			}else if(arg0.isControlDown()&&arg0.getKeyCode()==86) {//Õ³Ìù
				//System.out.println(1111111);
				Main.gui.bgp.ep.paste(Main.gui.bgp.ep.playNow);
			}else if(arg0.isControlDown()&&arg0.getKeyCode()==88) {//¼ôÇÐ
				Main.gui.bgp.ep.cut();
			}else {
				return;
			}
		}else if(Main.gui.bgp.ep.viewMode==Main.gui.bgp.ep.GROUP_VIEW) {
			if(arg0.getKeyCode()==KeyEvent.VK_DELETE) {//del
				for(EditingPnl.Clip clp:Main.gui.bgp.ep.elms) {
					if(clp.chosen) {
						rclp.add(clp);
					}
				}
				if(rclp.size()!=0) {
					for(Clip vdo:rclp) {
						Main.gui.sbp.sb.removeShot(vdo);
					}
					Main.gui.bgp.ep.elms.removeAll(rclp);
					Main.gui.bgp.ep.updateElement();
				}
			}else if(arg0.getKeyCode()==65&&arg0.isControlDown()) {//ctrl+a
				for(int i=0;i<Main.gui.bgp.ep.elms.size();i++) {
					Main.gui.bgp.ep.elms.get(i).chosen=true;
				}
				Main.gui.bgp.ep.updateElement();
			}else {
				return;
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
