package guiMgr.script;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import boot.Main;
import guiMgr.script.SceneTable.scene;

public class sceneMouse implements MouseListener {
	public void mouseClicked(MouseEvent arg0) {
	}
	public void mouseEntered(MouseEvent arg0) {
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int x=arg0.getX(),y=arg0.getY();
		int scuid=((SceneTable.scene) arg0.getSource()).uid,gpuid=((SceneTable.scene) arg0.getSource()).gp.uid;
		if(x>93&&x<113&&y>5&&y<25&&((SceneTable.scene) arg0.getSource()).uid!=-1) {
			Main.gm.groups.remove(Main.gm.getGroup(((SceneTable.scene) arg0.getSource()).gp.uid));
			Main.gui.sbp.st.removeScene(((SceneTable.scene) arg0.getSource()).uid);
			Main.gui.sbp.st.setAllUnselected();
			Main.gui.sbp.st.choose(-1, true);
			Main.gui.sbp.sb.changeScGpUID(scuid,-1, gpuid, -1);
			Main.gui.sbp.sb.updateShotTable();
			Main.gui.sbp.st.updateScene();
			Main.gui.sbp.st.repaint();
			Main.gui.sbp.sa.updateInfo();
			Main.gui.bgp.gp.updateGroupEntry();
			if(Main.gui.sbp.sb.fil.getFilterID()==scuid) {
				Main.gui.sbp.sb.fil.updateFilter(-1);
			}
			Main.showTip("已删除 场景"+((SceneTable.scene) arg0.getSource()).gp.name+" 其中镜头已被设为默认场景", Main.MESSAGE);
			
		}else if(range(x,70,110)&&range(y,4,24)){//颜色板
			if(Main.gui.sbp.cb.isVisible()) {
				Main.gui.sbp.cb.setVisible(null);
				return;
			}
			scene sc=(scene)arg0.getSource();
			Main.gui.sbp.cb.setLocation(sc.getX()+70, sc.getY()+Main.gui.sbp.st.getY()+85);
			Main.gui.sbp.st.tsc=sc;
			Main.gui.sbp.cb.setVisible(Main.gui.sbp.cbt);
		}else {
			Main.gui.sbp.cb.setVisible(null);
			Main.gui.sbp.st.setAllUnselected();
			Main.gui.sbp.st.choose(((SceneTable.scene) arg0.getSource()).uid,true);
			Main.gui.sbp.st.repaint();
			Main.gui.sbp.sa.updateInfo();
			if(arg0.getClickCount()==2||Main.gui.sbp.st.lin) {
				Main.gui.sbp.sb.fil.updateFilter(scuid);
				Main.gui.sbp.sb.fil.repaint();
			}
		}
	}
	public void mouseReleased(MouseEvent arg0) {
	}
	public boolean range(int num,int min,int max) {
		return num>=min&&num<max;
	}
}
