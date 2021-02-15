package render.tool;

import javax.swing.JPanel;

import Pickaxe2D.PickButton;
import Pickaxe2D.PickImage;
import Pickaxe2D.PickPanel;
import core.Main;

public class ToolPanel extends JPanel {
	public String res="resources\\";
	public PickButton build;
	public ToolPanel() {
		this.setLayout(null);
		this.setSize(60, 500);
		this.setOpaque(false);
		//НЈдь
		build=new PickButton(4,15,15);
		build.loadPickImage(0, 0, new PickImage(res+"btn_build.png.pick"));
		build.setLocation(0, 0);
		build.addActionListener(new ToolAction());
		this.add(build);
	}
}
