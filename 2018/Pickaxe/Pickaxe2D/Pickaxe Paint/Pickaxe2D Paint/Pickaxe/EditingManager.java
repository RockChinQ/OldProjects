package Pickaxe;

import java.awt.Color;

import Pickaxe2Dr1.Pick2DPanel;

public class EditingManager {
	static LayerManager lm;
	static Pick2DPanel main;
	EditingManager(int fWidth,int fHeight,Color bgColor){
		int w=(Boot.guim.cvp.getWidth()-10)/fWidth,h=(Boot.guim.cvp.getHeight()-20)/fHeight;
		lm=new LayerManager(Math.min(w, h),Math.min(w, h),fWidth,fHeight,bgColor);
		lm.main.setLocation(10,20);
		lm.main.setIfDrawLine(true);
		main=lm.main;
		main.setBackground(new Color(255,255,255,0));
		main.setLineColor(new Color(128,128,128));
		Boot.guim.cvp.add(main);
		init();
	}
	EditingManager(String filePath){             //open
		Pick2DPanel p2dp=Pick2DPanel.unpressPxdFile(filePath);
		int fw=p2dp.data.getPanelWidth(),fh=p2dp.data.getPanelHeight();
		int w=(Boot.guim.cvp.getWidth()-10)/fw-1,h=(Boot.guim.cvp.getHeight()-20)/fh-1;
		lm=new LayerManager(Math.min(w, h),Math.min(w, h),fw,fh,p2dp.data.preset);
		p2dp.data.PANEL_LABEL=lm.main.data.PANEL_LABEL;
		lm.main=p2dp;
		lm.main.setLocation(10,20);
		lm.main.setIfDrawLine(true);
		main=lm.main;
		main.setBackground(new Color(255,255,255,0));
		main.setLineColor(new Color(128,128,128));
		main.data.setGridSize(Math.min(w, h),Math.min(w, h));
		main.setSize(fw*main.data.getGridWidth(),fh*main.data.getGridHeight());
		Boot.guim.cvp.add(main);
		init();
	}
	void init() {
		main.addMouseMotionListener(new _editingMouse());
		main.addMouseListener(new _editingMouse());
		Boot.guim.lmp.update(0);
	}
}
