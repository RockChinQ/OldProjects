package guiMgr.script;

import java.awt.Graphics;

import javax.swing.JPanel;

import boot.Main;
import guiMgr.script.SceneTable.scene;
import guiMgr.script.SceneTable.scene.seat;

public class seatSelector extends JPanel {
	public seatSelector() {
		this.setLayout(null);
		this.setSize(130, 85);
		
	}
	public void updateInfo() {
		this.removeAll();
		scene scofsh=Main.gui.sbp.st.getScene(Main.gui.sbp.sb.getSelectedShot().sceneid);
		for(seat s:scofsh.seats) {
			s.setSize(12, 10);
			s.setLocation((int)Math.round(s.lo.x*0.56521739), (int)Math.round(s.lo.y*0.56521739));
			this.add(s);
		}
		this.repaint();
	}
}
