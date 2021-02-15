package guiMgr.script;

import java.awt.Color;

import javax.swing.JPanel;

import boot.Main;
import guiMgr.PnlColor;
import guiMgr.colorBoard;
import guiMgr.colorBoardTarget;
import log.Log;

public class scriptBgp extends JPanel{
	public SceneTable st;
	public SceneAttri sa;
	public ShotAttri sh;
	public ShotTable sb;
	public colorBoard cb;
	
	colorBoardTarget cbt=c->{st.tsc.gp.cl=c;st.updateScene();this.repaint();};
	
	PnlColor pc=new PnlColor();
	
	public scriptBgp(PnlColor pc) {
		cb=new colorBoard();
		cb.setVisible(null);
		cb.setBackground(pc.getColor(pc.editingPnl*1.2));
		cb.setLocation(-200, -200);
		this.add(cb);
		
		Log.record("Loading script panels...");
		this.setSize(1000, 700);
		this.setLayout(null);
		this.pc=pc;
		this.setBackground(new Color(40,40,40));
		
		Log.record("Loading scene table...");
		st=new SceneTable();
		st.setLocation(2, this.getHeight()-200);
		this.add(st);
		
		Log.record("Loading scene attribute...");
		sa=new SceneAttri();
		sa.setLocation(2, 2);

		this.add(sa);

		Log.record("Loading shot attribute...");
		sh=new ShotAttri();
		sh.setLocation(2, 400);
		this.add(sh);
		
		Log.record("Loading shot table...");
		sb=new ShotTable();
		sb.setLocation(154,2);
		this.add(sb);
		
		this.setPanelColor();
	}
	public void setPanelColor() {
		//st.setBackgroudx(pc.getColor(pc.sceneTable));
		st.setBackgroudx(new Color(50,51,53));
		sa.setBackgroudx(pc.getColor(pc.sceneAttri));
		sa.updateCom();
		sh.setBackgroudx(pc.getColor(pc.shotAttri));
		sh.updateCom();
		sb.setBackgroudx(pc.getColor(pc.shotAttri));
		//sb.updateCom();
		this.repaint();
	}
	public void resize() {
		st.setSize(this.getWidth()-11, 160);
		st.setLocation(4, this.getHeight()-260);
		st.resize();

		sa.setSize(380
				, (int) ((this.getHeight()-250)*0.41)+3);
		sa.setLocation(4, 0);
		sa.resize();

		sh.setSize(380
				, (int) ((this.getHeight()-250-(sa.getHeight()))-13)-8);
		sh.setLocation(4,  (int) (sa.getHeight())+6);
		sh.resize();
		
		sb.setSize(this.getWidth()-300-95
				, sh.getY()+sh.getHeight());
		sb.setLocation(sa.getX()+sa.getWidth()+4, 0);
		sb.resize();
	}
}
