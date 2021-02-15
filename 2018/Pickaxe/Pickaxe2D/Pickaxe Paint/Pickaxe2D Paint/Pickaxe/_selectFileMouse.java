package Pickaxe;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JColorChooser;

public class _selectFileMouse implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource().equals(Boot.guim.nfp.bgColorShow)) {
			Color c=JColorChooser.showDialog(null, "选择一个预设背景色", Boot.guim.nfp.bgColorShow.getForeground());
			//System.out.println(c.toString());
			if(c==null) {
				Boot.guim.nfp.bgColorShow.setText("□");
				Boot.guim.nfp.presetColor.setSelectedIndex(2);
				return;
			}
			Boot.guim.nfp.custom=c;
			Boot.guim.nfp.bgColorShow.setForeground(Boot.guim.nfp.custom);
			Boot.guim.nfp.presetColor.setSelectedIndex(3);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
