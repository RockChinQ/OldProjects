package render.tool;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import core.Level;
import core.Main;
import render.map.MapMouse;

public class ToolAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==Main.gg.tp.build) {
			Main.gg.mapp.map.resetFront();
			Main.gg.mapp.map.repaint();
			Main.gg.bp.setVisible(!Main.gg.bp.isVisible());
			Level.resetFront();
			MapMouse.time=0;
		}
	}

}

