package PictureViewer;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class change implements ChangeListener {

	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub

		GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle rec=ge.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
		int xl=(int)rec.getWidth();
		int yl=(int)rec.getHeight();
		main.w.p2d.setGridSize(main.w.jsl.getValue(),main.w.jsl.getValue());
		main.w.p2d.setSize(main.w.jsl.getValue()*main.w.p2d.panelwidth+1,main.w.jsl.getValue()*main.w.p2d.panelHeight+1);
		main.w.g.setSize(((main.w.jsl.getValue()*main.w.p2d.panelwidth+41)<350?350:(main.w.jsl.getValue()*main.w.p2d.panelwidth+41))>xl-100?xl-100:((main.w.jsl.getValue()*main.w.p2d.panelwidth+41)<350?350:(main.w.jsl.getValue()*main.w.p2d.panelwidth+41)),
				(main.w.jsl.getValue()*main.w.p2d.panelHeight+111)>yl-100?yl-100:(main.w.jsl.getValue()*main.w.p2d.panelHeight+111));
		//main.w.p2d.setLocation(10, 40);
		main.w.hk.setText("Grid Size("+main.w.jsl.getValue()+"):");
		main.w.p2d.repaint();
	}

}
