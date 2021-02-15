package Pickaxe;

import java.awt.Color;

import Pickaxe2Dr1.PicLayer;
import Pickaxe2Dr1.Pick2DPanel;
import Pickaxe2Dr1.Pickaxe2DManager;

public class LayerManager {
	Pick2DPanel main;
	PicLayer bgLayer;
	LayerManager(int gridWidth,int gridHeight,int width,int height,Color preset){
		main=Pickaxe2DManager.getPanel(Pickaxe2DManager.addPanel(gridWidth, gridHeight, width, height, preset));
		bgLayer=main.addLayer();
	}
}
