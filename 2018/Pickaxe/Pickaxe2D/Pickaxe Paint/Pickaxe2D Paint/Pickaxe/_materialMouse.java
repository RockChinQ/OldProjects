package Pickaxe;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFileChooser;

import Pickaxe2Dr1.Pick2DPanel;

public class _materialMouse implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource().equals(Boot.guim.mtp.pdfLabel)) {
			JFileChooser jfc=new JFileChooser();
			jfc.setFileFilter(new PxdFileFilter());
			jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int state=jfc.showOpenDialog(null);
			if(state==JFileChooser.CANCEL_OPTION)
				return;
			String path=jfc.getSelectedFile().getAbsolutePath();
			Boot.guim.mtp.setPxdFile(Pick2DPanel.unpressPxdFile(path));
			Boot.guim.mtp.pdfLabel.setText(path);
			Boot.guim.cmdp.exec("echo ´ò¿ªËØ²Ä£º"+path);
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
