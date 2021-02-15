package Pickaxe2Dr1;

import java.awt.Color;

public class DataOfPanel {
	Size panelSize,gridSize;   //panelSize表示面板的大小单位是格子
	public Color preset=new Color(255,255,255,0);
	public int PANEL_LABEL=0;
	DataOfPanel(Size panelSize,Size gridSize){
		this.panelSize=panelSize;
		this.gridSize=gridSize;
	}
	DataOfPanel(Size panelSize,Size gridSize,Color preset,int PANEL_LABEL){
		this.panelSize=panelSize;
		this.gridSize=gridSize;
		this.preset=preset;
		this.PANEL_LABEL=PANEL_LABEL;
	}
	public void setGridSize(int w,int h) {
		gridSize.width=w;
		gridSize.height=h;
	}
	public void setPanelSize(int w,int h) {
		panelSize.width=w;
		panelSize.height=h;
	}
	public int getPanelWidth() {
		return panelSize.getWidth();
	}
	public int getPanelHeight() {
		return panelSize.getHeight();
	}
	public int getGridWidth() {
		return gridSize.getWidth();
	}
	public int getGridHeight() {
		return gridSize.getHeight();
	}
}
