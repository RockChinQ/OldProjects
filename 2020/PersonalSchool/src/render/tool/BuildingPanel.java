package render.tool;

import javax.swing.JPanel;

import Pickaxe2D.PickButton;
import Pickaxe2D.PickImage;
import core.Main;

public class BuildingPanel extends JPanel{
	public PickButton fence,teach,gate;
	public final static int FENCE=1,TEACH=2,GATE=3;
	public static int bldg=0;
	public BuildingPanel() {
		this.setLayout(null);
		this.setSize(48,500);
		this.setLocation(65, 150);
		this.setOpaque(false);
		
		//护栏
		fence=new PickButton(2,24,24);
		fence.loadPickImage(0, 0, new PickImage(Main.res+"bld_fence.png.pick"));
		fence.setLocation(0, 0);
		fence.addActionListener(new BuildAction());
		this.add(fence);
		//教学区
		teach=new PickButton(2,24,24);
		teach.loadPickImage(0, 0, new PickImage(Main.res+"bld_teach.png.pick"));
		teach.setLocation(0, 50);
		teach.addActionListener(new BuildAction());
		this.add(teach);
		//校门
		gate=new PickButton(2,24,24);
		gate.loadPickImage(0, 0, new PickImage(Main.res+"bld_gate.png.pick"));
		gate.setLocation(0, 100);
		gate.addActionListener(new BuildAction());
		this.add(gate);
	}
}
