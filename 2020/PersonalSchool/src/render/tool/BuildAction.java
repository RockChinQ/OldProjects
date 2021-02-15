package render.tool;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import core.Main;
import render.map.MapMouse;

public class BuildAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Main.gg.bp.setVisible(false);
		if(arg0.getSource()==Main.gg.bp.fence)
			BuildingPanel.bldg=BuildingPanel.FENCE;
		else if(arg0.getSource()==Main.gg.bp.teach)
			BuildingPanel.bldg=BuildingPanel.TEACH;
		else if(arg0.getSource()==Main.gg.bp.gate) 
			BuildingPanel.bldg=BuildingPanel.GATE;
		MapMouse.time=1;
		//System.out.println(BuildingPanel.bldg);
		Main.gg.mapp.readyToBuild();
	}

}
